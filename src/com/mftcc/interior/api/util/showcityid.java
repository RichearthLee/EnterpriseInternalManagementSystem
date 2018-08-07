package com.mftcc.interior.api.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.mftcc.interior.api.util.HttpUtils;

public class showcityid {
	
	public String  selectcityid(String cityid)throws  Exception {
		
		String host = "http://jisuidcard.market.alicloudapi.com";
	    String path = "/idcard/city2code";
	    String method = "GET";
	    String appcode = "3fc61fa3aca143ecb6d9f11eb509406c";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("city", cityid);


	   
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    
			return EntityUtils.toString(response.getEntity());
	}

}
