package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.OpenOS;

public interface OpenOSMapper {

	List<OpenOS> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	OpenOS getById(Integer id);
	
	void updateOpenOS(OpenOS openOS);
	
	void deleteOpenOS(Integer id);
	
	void addOpenOS(OpenOS openOS);
	
	Integer findByTypeId(Integer typeId);
	
	Integer findByLanguageId(Integer id);
}
