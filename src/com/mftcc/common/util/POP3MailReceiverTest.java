package com.mftcc.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class POP3MailReceiverTest {
	  public POP3MailReceiverTest() {  
	        try {  
	             // 1. 设置连接信息, 生成一个 Session  
	            Properties props = new Properties();  
	            props.setProperty("mail.smtp.host", "smtp.genomics.cn");  
	            props.setProperty("mail.smtp.auth", "true");  
	            Session session = Session.getDefaultInstance(props);  
	            // 2. 获取 Store 并连接到服务器    
	            //  
	            URLName urlname = new URLName("pop3","pop.qq.com",110,null,"liupei@mftcc.cn","1q2w3e4r5t6y");  
	            Store store = session.getStore(urlname);  
	            store.connect();  
	            Folder folder = store.getDefaultFolder();// 默认父目录  
	            if (folder == null) {  
	                System.out.println("服务器不可用");  
	                return;  
	            }  
	          /* 
	           System.out.println("默认信箱名:" + folder.getName()); 
	 
	           Folder[] folders = folder.list();// 默认目录列表 
	           for(int i = 0; i < folders.length; i++) { 
	               System.out.println(folders[0].getName()); 
	           } 
	            System.out.println("默认目录下的子目录数: " + folders.length); 
	            */  
	            Folder popFolder = folder.getFolder("INBOX");// 获取收件箱  
	            popFolder.open(Folder.READ_WRITE);// 可读邮件,可以删邮件的模式打开目录  
	            // 4. 列出来收件箱 下所有邮件  
	            Message[] messages = popFolder.getMessages();  
	            // 取出来邮件数  
	            int msgCount = popFolder.getMessageCount();  
	            System.out.println("共有邮件: " + msgCount + "封");  
	            // FetchProfile fProfile = new FetchProfile();// 选择邮件的下载模式,  
	            // 根据网速选择不同的模式  
	            // fProfile.add(FetchProfile.Item.ENVELOPE);  
	            // folder.fetch(messages, fProfile);// 选择性的下载邮件  
	            // 5. 循环处理每个邮件并实现邮件转为新闻的功能  
	            for (int i = 0; i < msgCount; i++) {  
	                // 单个邮件  
	                System.out.println("第" + i +"邮件开始");  
	                mailReceiver(messages[i]);  
	                System.out.println("第" + i +"邮件结束");  
	               //邮件读取用来校验  
	               //  messages[i].writeTo(new FileOutputStream("D:/pop3MailReceiver"+ i +".eml"));  
	            }    
	            // 7. 关闭 Folder 会真正删除邮件, false 不删除  
	            popFolder.close(true);  
	            // 8. 关闭 store, 断开网络连接  
	            store.close();  
	        } catch (NoSuchProviderException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /** 
	     * 解析邮件 
	     *  
	     * @param messages 邮件对象 
	     * @param i 
	     * @return 
	     * @throws IOException 
	     * @throws MessagingException 
	     * @throws FileNotFoundException 
	     * @throws UnsupportedEncodingException 
	     */  
	    private void mailReceiver(Message msg)throws Exception{  
	        // 发件人信息  
	        Address[] froms = msg.getFrom();  
	        if(froms != null) {  
	            //System.out.println("发件人信息:" + froms[0]);  
	            InternetAddress addr = (InternetAddress)froms[0];  
	            System.out.println("发件人地址:" + addr.getAddress());  
	            System.out.println("发件人显示名:" + addr.getPersonal());  
	        }  
	        System.out.println("邮件主题:" + msg.getSubject());  
	        // getContent() 是获取包裹内容, Part相当于外包装  
	        Object o = msg.getContent();  
	        if(o instanceof Multipart) {  
	            Multipart multipart = (Multipart) o ;  
	            reMultipart(multipart);  
	        } else if (o instanceof Part){  
	            Part part = (Part) o;   
	            rePart(part);  
	        } else {  
	            System.out.println("类型" + msg.getContentType());  
	            System.out.println("内容" + msg.getContent());  
	        }  
	    }  
	      
	    /** 
	     * @param part 解析内容 
	     * @throws Exception 
	     */  
	    private void rePart(Part part) throws MessagingException,  
	            UnsupportedEncodingException, IOException, FileNotFoundException {  
	        if (part.getDisposition() != null) {  
	          
	            String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText解决附件名乱码问题  
	            System.out.println("发现附件: " +  MimeUtility.decodeText(part.getFileName()));  
	            System.out.println("内容类型: " + MimeUtility.decodeText(part.getContentType()));  
	            System.out.println("附件内容:" + part.getContent());  
	            InputStream in = part.getInputStream();// 打开附件的输入流  
	            // 读取附件字节并存储到文件中  
	            java.io.FileOutputStream out = new FileOutputStream(strFileNmae);  
	            int data;  
	            while((data = in.read()) != -1) {  
	                out.write(data);  
	            }  
	            in.close();  
	            out.close();  
	        } else {  
	            if(part.getContentType().startsWith("text/plain")) {  
	                System.out.println("文本内容：" + part.getContent());  
	            } else {  
	                //System.out.println("HTML内容：" + part.getContent());  
	            }  
	        }  
	    }  
	      
	    /** 
	     * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)） 
	     * @throws Exception 
	     */  
	    private void reMultipart(Multipart multipart) throws Exception {  
	        //System.out.println("邮件共有" + multipart.getCount() + "部分组成");  
	        // 依次处理各个部分  
	        for (int j = 0, n = multipart.getCount(); j < n; j++) {  
	            //System.out.println("处理第" + j + "部分");  
	            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,  
	            // 也可能是另一个小包裹(MultipPart)  
	            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative  
	            if (part.getContent() instanceof Multipart) {  
	                Multipart p = (Multipart) part.getContent();// 转成小包裹  
	                //递归迭代  
	                reMultipart(p);  
	            } else {  
	                rePart(part);  
	            }  
	         }  
	    }  
	      
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) {  
	        new POP3MailReceiverTest();  
	    }  
}