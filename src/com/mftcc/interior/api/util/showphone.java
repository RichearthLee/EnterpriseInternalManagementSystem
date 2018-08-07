package com.mftcc.interior.api.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.mftcc.interior.api.util.HttpUtils;

public class showphone {
	
	public String  selectphone(String phone)throws  Exception {
		
		 String host = "http://showphone.market.alicloudapi.com";
		    String path = "/6-1";
		    String method = "GET";
		    String appcode = "ac473a65ae5042a99f20b5e09717a58d";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("num", phone);
		   
		     
		    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
		   // 	System.out.println(response.toString());
		    	//获取response的body
		    	// System.out.println(EntityUtils.toString(response.getEntity()));
		    
			return EntityUtils.toString(response.getEntity());
	}

}
