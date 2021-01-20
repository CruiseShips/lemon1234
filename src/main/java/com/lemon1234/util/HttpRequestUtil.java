package com.lemon1234.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	public static JSONObject httpRequestGet(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			
			String responseData = EntityUtils.toString(entity);
			jsonObject = JSON.parseObject(responseData);
		} else {
			httpClient.close();
			return null;
		}
		httpClient.close();
		return jsonObject;
	}
	
	public static JSONObject httpRequestPost(String url, String data) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/json;charset=utf-8");
		Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("content", data);
        httpPost.setEntity(new StringEntity(JSONObject.toJSONString(paramMap), ContentType.create("application/json", "utf-8")));
        
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			String responseData = EntityUtils.toString(entity);
			jsonObject = JSON.parseObject(responseData);
		} else {
			httpClient.close();
			return null;
		}
		httpClient.close();
		return jsonObject;
	}
	
	public static String getIpAddress(HttpServletRequest request) {
         String ip = (String) request.getAttribute("X-real-ip");
         return ip;
     }
}
