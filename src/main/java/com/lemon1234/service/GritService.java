package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Grit;

public interface GritService {

	Grit getRand();
	
	List<Grit> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void update(Grit grit);
	
	void delete(Integer id);
	
	void addGrit(Grit grit);
	
	List<Grit> getGrits(String openId);
}
