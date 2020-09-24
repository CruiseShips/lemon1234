package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Shout;

public interface ShoutService {

	void addShout(Shout shout);
	
	List<Shout> getShout(String openId);
	
	List<Shout> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	List<Map<String, Object>> proportion();
}
