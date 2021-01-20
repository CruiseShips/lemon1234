package com.lemon1234.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class WxUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WxUtil.class);

	/**
	 * 检测文本信息内容
	 * 
	 * https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean checkText(String text, String accessToken) throws Exception {
		String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken;
		JSONObject jsonObject = HttpRequestUtil.httpRequestPost(url, "{\"content\":\"" + text + "\"}");
		
		logger.info(jsonObject.toJSONString());
		Integer code = (Integer) jsonObject.get("errcode");
		if (code == 0) {
			return false;
		} else {
			return true;
		}
	}
}
