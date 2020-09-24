package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Language;

public interface LanguageMapper {

	Language getById(Integer id);
	
	List<Language> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void addLanguage(Language language);
	
	void delete(Integer id);
	
	void update(Language language);
	
}
