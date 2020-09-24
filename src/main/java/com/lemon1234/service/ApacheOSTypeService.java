package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.ApacheOSType;

public interface ApacheOSTypeService {

	List<ApacheOSType> getlist(Map<String, Object> param);
	
	ApacheOSType getById(ApacheOSType apacheOSType);
	
	Integer getCount(Map<String, Object> param);
	
	void delete(Integer id);
	
	void update(ApacheOSType apacheOSType);
	
	void addApacheOSType(ApacheOSType apacheOSType);
}
