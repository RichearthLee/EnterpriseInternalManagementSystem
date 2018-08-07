package com.mftcc.common.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能:收发邮件的工具类
 * 
 * @author 乾之轩
 */
public class MailUtil {
	protected final Log log = LogFactory.getLog(MailUtil.class);

	private String saveAttachPath = ""; // 附件下载后的存放目录
	private StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象
	private String dateFormat = "yy-MM-dd HH:mm"; // 默认的日前显示格式
	 // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.qq.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String SEND_USER = "624462392@qq.com";
    private String SEND_UNAME = "624462392";
    private String SEND_PWD = "zheng123!!";
    // 建立会话
    private MimeMessage message;
    private Session s;
    /*
     * 初始化方法
     */

    public MailUtil() {
	 Properties props = System.getProperties();
     props.setProperty(KEY_SMTP, VALUE_SMTP);
     props.put(KEY_PROPS, "true");
     //props.put("mail.smtp.auth", "true");
     s =  Session.getDefaultInstance(props, new Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
           }});
     s.setDebug(true);
     message = new MimeMessage(s);

    }
    /**
     * 发送邮件
     * 
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public void doSendHtmlEmail(String headName, StringBuilder sendHtml,
            String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
        } catch (AddressException e) {
           
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

	public static void receiveEmail(String host, String account, String password)
			throws Exception {
		Message[] message = null;
		message = getMessage(host, account, password);
		StringBuffer sb = new  StringBuffer();
	//	for (int i = 0; i < message.length; i++) {
		for (int i = 0; i < 20; i++) {
			MimeMessage mimeMessage = (MimeMessage) message[i];
			String sendDate =getSentDate(mimeMessage);
			if(!StringUtils.equals("2015-04-20", sendDate)){
				continue;
			}
			String sender = getSenderInf(mimeMessage);
			sb.append(sender);
			sb.append("\r\n");
			String subject =getSubject(mimeMessage);
			sb.append(subject);
			sb.append("\r\n");
			sb.append(sendDate);
			sb.append("\r\n");
		//	String mailContent =  getMailContent(mimeMessage);
			getMailContent(mimeMessage);
			
			
			sb.append("\r\n");
			break;
		}
		
		System.out.println(sb.toString());
	}
	
	public static void getMailContent(MimeMessage mimeMessage) throws Exception{
		 Multipart multipart = (Multipart) mimeMessage.getContent();
		 int count = multipart.getCount();    // 部件个数
         for(int i=0; i<count; i++) {
             // 单个部件     注意：单个部件有可能又为一个Multipart，层层嵌套
             BodyPart part = multipart.getBodyPart(i);
             // 单个部件类型
             String type = part.getContentType().split(";")[0];
             /**
              * 类型众多，逐一判断，其中TEXT、HTML类型可以直接用字符串接收，其余接收为内存地址
              * 可能不全，如有没判断住的，请自己打印查看类型，在新增判断
              */
             if(type.equals("multipart/alternative")) {        // HTML （文本和超文本组合）
                 System.out.println("超文本:" + part.getContent().toString());
             }else if(type.equals("text/plain")) {    // 纯文本
                 System.out.println("纯文本:" + part.getContent().toString());
             }else if(type.equals("text/html")){    // HTML标签元素
                 System.out.println("HTML元素:" + part.getContent().toString());
             }else if(type.equals("multipart/related")){    // 内嵌资源 (包涵文本和超文本组合)
                 System.out.println("内嵌资源:" + part.getContent().toString());
             }else if(type.contains("application/")) {        // 应用附件 （zip、xls、docx等）
                 System.out.println("应用文件：" + part.getContent().toString());
             }else if(type.contains("image/")) {            // 图片附件 （jpg、gpeg、gif等）
                 System.out.println("图片文件：" + part.getContent().toString());
             }
              
/*****************************************获取邮件内容方法***************************************************/
             /**
              * 附件下载
              * 这里针对image图片类型附件做下载操作，其他类型附件同理
              */
             if(type.contains("image/")) {
                 // 打开附件的输入流
                 DataInputStream in = new DataInputStream(part.getInputStream());
                 // 一个文件输出流
                 FileOutputStream out = null;
                 // 获取附件名
                 String fileName = part.getFileName();
                 // 文件名解码
                 fileName = MimeUtility.decodeText(fileName);
                 // 根据附件名创建一个File文件
                 File file = new File("d:/data/" + fileName);
                 // 查看是否有当前文件
                 Boolean b = file.exists();
                 if(!b) {
                    out = new FileOutputStream(file);
                    int data;
                     // 循环读写
                     while((data=in.read()) != -1) {
                         out.write(data);
                     }
                     System.out.println("附件：【" + fileName + "】下载完毕，保存路径为：" + file.getPath());
                 }
                  
                 // 关流
                 if(in != null) {
                    in.close();
                 }
                 if(out != null) {
                    out.close();
                 }
             }
         }
	}
	
	
	
	
    /**
     * 功能:获取发件日期
     * @return
     * @throws Exception
     */
    private static String getSentDate(MimeMessage mimeMessage) throws Exception {
        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strSentDate = format.format(sentDate);
        return strSentDate;
    }
 
	
	
	

	/**
	 * 功能:获取发件人和地址
	 * @return
	 * @throws Exception
	 */
	private static String getSenderInf(MimeMessage mimeMessage)
			throws Exception {
		InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
		// String from = address[0].getAddress();
		String sender = address[0].getAddress();
		if (sender == null) {
			sender = "";
			// log.debug("无法知道发送者");
		}
		// 获取发送者姓名
		String name = address[0].getPersonal();
		if (name == null) {
			name = "";
			// log.debug("无法知道发送者的姓名.");
		}

		String fromAddr = null;
		if (name != null || sender != null) {
			fromAddr = name + "<" + sender + ">";
			// log.debug("发送者是：" + fromAddr);
		} else {
			// log.debug("无法获得发送者信息.");
		}
		return fromAddr;
	}

	/**
	 * 功能:获取邮件主题
	 * @param mimeMessage
	 * @return
	 * @throws Exception
	 */
	private static String getSubject(MimeMessage mimeMessage) throws Exception {
		String subject = "";
		//System.out.println("转换前的subject：" + mimeMessage.getSubject());
		if(mimeMessage.getSubject()==null){
			return "";
		}else{
			subject = mimeMessage.getSubject();
		}
		return subject;
	}

	/**
	 * 功能:获取收件箱的信息,一条Message代表一封邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Message[] getMessage(String host, String username,
			String password)  {
		/**
		 * String host = "pop.sina.com"; String username = "***"; String
		 * password = "***";
		 **/
		Folder folder = null;
		Store store = null;
		Message[] message  = null;
		try{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		store = session.getStore("pop3");
		store.connect(host, username, password);

		folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		message = folder.getMessages();
		int length = message.length;
		
		for (int i=0; i<length; i++) { 
			MimeMessage mimeMessage = (MimeMessage)message[i];
			Multipart multipart = (Multipart) mimeMessage.getContent(); 
			String contentType =  multipart.getContentType();
			if(StringUtils.equals("text/html", contentType) || StringUtils.equals("text/plain", contentType)){
				String content =  mimeMessage.getContent().toString();
				System.out.println(content);
				break;
			}else{
				continue;
			}
		}  
		
		
		}catch(Exception e){
		    try{
                if(folder!=null)
                    folder.close(true); //退出收件箱时,删除做了删除标识的邮件
               if (store != null)
                  store.close();
              }catch(Exception bs){
               bs.printStackTrace();
              }             
		}
		return message;
	}

	public static void main(String... strings) throws Exception {
	//	MailUtil.getMessage("pop.qq.com", "liupei@mftcc.cn", "1q2w3e4r5t6y");
		 MailUtil se = new MailUtil();		
		 StringBuilder a =new StringBuilder();
		 a.append("邮件内容:");
	
		
		
	     se.doSendHtmlEmail("邮件头文件名", a, "624462392@qq.com");

	}

}
