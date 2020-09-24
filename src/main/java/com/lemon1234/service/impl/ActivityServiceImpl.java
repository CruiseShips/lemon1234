package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Activity;
import com.lemon1234.mapper.ActivityMapper;
import com.lemon1234.service.ActivityService;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public List<Activity> list(Map<String, Object> param) {
		return activityMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return activityMapper.getCount(param);
	}

	@Override
	public void addActivity(Activity activity) {
		activityMapper.addActivity(activity);		
	}

	@Override
	public void update(Activity activity) {
		activityMapper.update(activity);		
	}

	@Override
	public void delete(Integer id) {
		activityMapper.delete(id);		
	}

	@Override
	public Activity getdetail(Integer id) {
		return activityMapper.getdetail(id);
	}
}
