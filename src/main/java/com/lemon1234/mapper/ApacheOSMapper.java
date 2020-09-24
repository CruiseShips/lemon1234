package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.ApacheOS;

public interface ApacheOSMapper {

	List<ApacheOS> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	ApacheOS getById(Integer id);
	
	void updateApacheOS(ApacheOS apacheOS);
	
	void deleteApacheOS(Integer id);
	
	void addApacheOS(ApacheOS apacheOS);
	
	Integer findByTypeId(Integer typeId);
	
	Integer findByLanguageId(Integer id);
}
