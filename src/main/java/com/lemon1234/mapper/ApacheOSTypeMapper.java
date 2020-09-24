package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.ApacheOSType;

public interface ApacheOSTypeMapper {

	ApacheOSType getById(ApacheOSType apacheOSType);
	
	List<ApacheOSType> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void delete(Integer id);
	
	void update(ApacheOSType apacheOSType);
	
	void addApacheOSType(ApacheOSType apacheOSType);
}
