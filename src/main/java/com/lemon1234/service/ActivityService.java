package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Activity;

public interface ActivityService {

	List<Activity> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void addActivity(Activity activity);
	
	void update(Activity activity);
	
	void delete(Integer id);
	
	Activity getdetail(Integer id);
}
