package com.mftcc.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dingxin.util.StringUtil;
import com.dingxinsoft.phoneMessage.IPhoneMessage;
import com.mftcc.common.util.PropertiesUtil;



/**
 * 
 * 类名： WarningSmsExe
 * 描述：
 * @author WuLiGang
 * @date 2016-1-20 下午5:13:09
 *
 */
public class WarningSmsExe{
	protected static final Log log = LogFactory.getLog(WarningSmsExe.class);
	
	public static String[]  getSendResult(String content,String phone) {
	
		 String[] msgRes = new String[2];
		 
		 Map<String, String> parmMap = new HashMap<String, String>();
		 if (StringUtil.isNotEmpty(phone)) {
			 	parmMap.put("phoneNum", phone);
		 }else{
			 msgRes[0] = "1111";
			 msgRes[1] = "电话号码为空";
			 return msgRes;
		 }
		 
		 parmMap.put("text", content);
		 if (StringUtil.isNotEmpty(PropertiesUtil.getValue("companyName"))) {
			parmMap.put("companyName", PropertiesUtil.getValue("companyName"));
		 }
		 //parmMap.put("uuid", StringUtil.getPK(null));// 短信发送流水号

		 IPhoneMessage phoneMessage = MessageFactory_new.getPhoneMessageImpl();

		 msgRes = phoneMessage.sendMessage(parmMap);
	 
		 return msgRes;
	 }
	
	public static void main(String[] args) {
		getSendResult("How are you?","15738340823");
	}
	 
}
