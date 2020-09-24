package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Language;

public interface LanguageService {

	List<Language> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void addLanguage(Language language);
	
	void delete(Integer id);
	
	Language getById(Integer id);
	
	void update(Language language);
}
