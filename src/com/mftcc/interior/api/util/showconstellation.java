package com.mftcc.interior.api.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.mftcc.interior.api.util.HttpUtils;

public class showconstellation {
	
	public String  selectconstellation(String constellation)throws  Exception {
		
		String host = "http://ali-star-lucky.showapi.com";
	    String path = "/star";
	    String method = "GET";
	    String appcode = "3fc61fa3aca143ecb6d9f11eb509406c";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("needMonth", "1");
	    querys.put("needTomorrow", "1");
	    querys.put("needWeek", "1");
	    querys.put("needYear", "1");
	    querys.put("star", constellation);

	   /* needMonth	    STRING	可选	是否需要本月运势的数据，1为需要，其他不需要
	      needTomorrow	STRING	可选	是否需要明天的数据，1为需要，其他不需要
	      needWeek	    STRING	可选	是否需要本周运势的数据，1为需要，其他不需要
	      needYear	    STRING	可选	是否需要本年运势的数据，1为需要，其他不需要
	      star	        STRING	必选	十二星座，其值分别为 baiyang jinniu shuangzi juxie shizi chunv tiancheng tianxie sheshou mojie shuiping shuangyu
	   */
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
