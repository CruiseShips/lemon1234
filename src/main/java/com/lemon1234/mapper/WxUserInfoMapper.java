package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.WxUserInfo;

public interface WxUserInfoMapper {

	void addWxUser(WxUserInfo wxUserInfo);
	
	List<WxUserInfo> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	WxUserInfo findWxUser(String openId);
	
	void update(WxUserInfo wxUserInfo);
	
	List<Map<String, Object>> proportion();
}
