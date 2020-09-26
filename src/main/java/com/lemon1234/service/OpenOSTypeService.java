package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.OpenOSType;

public interface OpenOSTypeService {

	List<OpenOSType> getlist(Map<String, Object> param);
	
	OpenOSType getById(OpenOSType openOSType);
	
	Integer getCount(Map<String, Object> param);
	
	void delete(Integer id);
	
	void update(OpenOSType openOSType);
	
	void addOpenOSType(OpenOSType openOSType);
}
