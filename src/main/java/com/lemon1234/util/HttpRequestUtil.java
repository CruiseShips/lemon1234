package com.lemon1234.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	public static JSONObject httpRequestGet(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpGet = new HttpPost(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			
			String responseData = EntityUtils.toString(entity);
			jsonObject = JSON.parseObject(responseData);
		} else {
			return null;
		}
		
		return jsonObject;
	}
}
