package com.mftcc.common.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class HttpPostUtil {

	private static Logger logger = Logger.getLogger(HttpPostUtil.class);

	public static String post(Map<String, String> parmMap, String strurl) {
		String result = null;
		try {
//			URL url = new URL(strurl);
//			URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
			HttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(strurl);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (parmMap != null && !parmMap.isEmpty()) {
				Set<String> keys = parmMap.keySet();
				for (String key : keys) {
					params.add(new BasicNameValuePair(key, parmMap.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			httppost.setConfig(RequestConfig.DEFAULT);
			HttpResponse response = client.execute(httppost);
			Reader reader = new InputStreamReader(response.getEntity().getContent(), "utf-8");
			result = IOUtils.toString(reader);
		} catch (Exception e) {
			logger.error("连接云平台:请求异常,参数" + parmMap + "请求地址" + strurl, e);
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String post = HttpPostUtil.post(null, "http://www.mftcc.cn/home.html");
		System.out.println(post);
	}
}
