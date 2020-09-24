package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.WxUserInfo;
import com.lemon1234.mapper.WxUserInfoMapper;
import com.lemon1234.service.WxUserInfoService;

@Service("wxUserInfoService")
public class WxUserInfoServiceImpl implements WxUserInfoService {
	
	@Autowired
	private WxUserInfoMapper wxUserInfoMapper;

	@Override
	public void addWxUser(WxUserInfo wxUserInfo) {
		wxUserInfoMapper.addWxUser(wxUserInfo);		
	}

	@Override
	public List<WxUserInfo> list(Map<String, Object> param) {
		return wxUserInfoMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return wxUserInfoMapper.getCount(param);
	}

	@Override
	public WxUserInfo findWxUser(String openId) {
		return wxUserInfoMapper.findWxUser(openId);
	}

	@Override
	public void update(WxUserInfo wxUserInfo) {
		wxUserInfoMapper.update(wxUserInfo);		
	}

	@Override
	public List<Map<String, Object>> proportion() {
		return wxUserInfoMapper.proportion();
	}

}
