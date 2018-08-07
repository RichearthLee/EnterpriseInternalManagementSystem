/**
 * 
 */
package com.mftcc.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dingxin.util.StringUtil;
import com.dingxinsoft.phoneMessage.IPhoneMessage;
import com.dingxinsoft.util.Encryption;
import com.dingxinsoft.util.PhoneUtil;
import com.mftcc.common.util.PropertiesUtil;

/**
 * 
 * 类名： DingxinPhoneMessageImpl
 * 描述：
 * @author WuLiGang
 * @date 2016-2-25 下午6:56:09
 *
 */
public class DingxinPhoneMessage_newImpl implements IPhoneMessage {
	private static String username = "";
	private static String password = "";
	private static String urlString = "";
	private static Log log = LogFactory.getLog(DingxinPhoneMessage_newImpl.class);
	private final static Map<String, String> resultMap = new HashMap<String, String>();
	
	public  DingxinPhoneMessage_newImpl(File file){
			username = PropertiesUtil.getValue("username");
			password = PropertiesUtil.getValue("password");
			urlString = PropertiesUtil.getValue("url");
			
			
		  	resultMap.put("00001", "发送成功");
		    resultMap.put("00000", "正在发送");
		    resultMap.put("-00000", "系统未知异常");
		    resultMap.put("-00001", "传入参数格式错误");
		    resultMap.put("-00002", "用户名不存在");
		    resultMap.put("-00003", "用户密码错误");
		    resultMap.put("-00004", "短信长度过长");
		    resultMap.put("-00005", "账户余额不足");
		    resultMap.put("-00006", "相同流水号短信不能发送");
		    resultMap.put("-00007", "批量短信条");
		    resultMap.put("-00008", "查询流水状态失败");
		    resultMap.put("-00009", "发送失败");
		    resultMap.put("-00010", "发送失败");
		
	}

	public String[] sendMessage(Map<String, String> map) {
		if(map == null){
			return new String[]{"1111","参数为空"};
		}
		//phoneNum  text  companyName
		String phoneNum = map.get("phoneNum");
		String text = map.get("text");
		String companyName = map.get("companyName");
		if (companyName != null && !"".equals(companyName)) {
			text = text + " " + companyName;
		}
		
//		log.info("username="+username);
//		log.info("password="+password);
//		log.info("urlString="+urlString);
		log.info("phoneNum="+phoneNum);
//		log.info("companyName="+companyName);
		log.info("text="+text);
		return send(phoneNum, text, map);
	}


	private String[] send(String phoneNums,String text,Map<String, String> map){
		URL url;
		String uuid = "";
		
		if(map!=null &&StringUtils.isNotEmpty(map.get("uuid"))){
			uuid = map.get("uuid");
		}else{
			uuid = StringUtil.getPK("");
		}
				
		String[] result = new String[2];
		String soapAction = "http://tempuri.org/sendmsg";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<sendmsg xmlns=\"http://tempuri.org/\">";
		String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"," +
				"\"msgsn\":\""+uuid+"\",\"phone\":\""+phoneNums+"\"," +
				"\"msgcontent\":\""+PhoneUtil.xmlReplace(text)+"" +
				"\",\"custerId\":null,\"type\":\"dx\"}";
		json = Encryption.encode(json);
		//log.info("json=="+json);
		xml += "<msg>" + json + "</msg>";
		xml += "</sendmsg>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		try {
			url = new URL(urlString);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());

			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=UTF-8");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<sendmsgResult>(.*)</sendmsgResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					String msg = matcher.group(1).trim();
					msg = Encryption.decode(msg);
					if("00001".equals(msg)){
						result[0] = "0000";
						result[1] = "发送成功";
					}else{
						result[0] = "1111";
						result[1] = resultMap.get(msg);
					}
					
				}
			}
		} catch (Exception e) {
			log.error("发送失败", e);
		}
		return result;
	}
	
}
