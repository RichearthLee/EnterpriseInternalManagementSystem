package com.mftcc.interior.api.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.mftcc.interior.api.util.HttpUtils;

public class showweather {

	public String selectweather(String weather)throws  Exception {
	    String host = "https://ali-weather.showapi.com";
	    String path = "/area-to-weather";
	    String method = "GET";
	    String appcode = "3fc61fa3aca143ecb6d9f11eb509406c";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("area", weather);
	    querys.put("need3HourForcast", "0");
	    querys.put("needAlarm", "1");
	    querys.put("needHourData", "0");
	    querys.put("needIndex", "1");
	    querys.put("needMoreDay", "0");

 /*     area	            STRING	可选	地区名称。名称和id必须输入一个。如果都输入，以areaid为准。
	    areaid	            STRING	可选	地区id
	    need3HourForcast	STRING	可选	是否需要每小时数据的累积数组。由于本系统是半小时刷一次实时状态，因此实时数组最大长度为48。每天0点长度初始化为0. 1为需要 0为不
	    needAlarm	        STRING	可选	是否需要天气预警。1为需要，0为不需要。
	    needHourData	    STRING	可选	是否需要每小时数据的累积数组。由于本系统是半小时刷一次实时状态，因此实时数组最大长度为48。每天0点长度初始化为0.
	    needIndex	        STRING	可选	是否需要返回指数数据，比如穿衣指数、紫外线指数等。1为返回，0为不返回。
	    needMoreDay	        STRING	可选	是否需要返回7天数据中的后4天。1为返回，0为不返回。
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
