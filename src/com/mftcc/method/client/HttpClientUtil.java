package com.mftcc.method.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.common.exception.ServiceExceptionEnum;




/**
 * 功能:对restfull调用的封装
 * @author dx
 *
 */
//
public class HttpClientUtil {
	public static String sendPost(Map<String, String> parmMap,String url) throws Exception {
		String result = null;
		CloseableHttpClient httpclient = null;
		HttpPost httppost = null;
		try {
			httpclient = HttpClients.createDefault();
			httppost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : parmMap.entrySet()) {
				if(entry.getValue() == null||entry.getValue().equals("")) continue;
				BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(),(String) entry.getValue());
				params.add(pair);
			}
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			throw e;
		}finally {
			if(null!=httppost){
				httppost.abort();
			}
        }
		return result;
	}
}
