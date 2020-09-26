package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.OpenOSType;

public interface OpenOSTypeMapper {

	OpenOSType getById(OpenOSType openOSType);
	
	List<OpenOSType> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void delete(Integer id);
	
	void update(OpenOSType openOSType);
	
	void addOpenOSType(OpenOSType openOSType);
}
