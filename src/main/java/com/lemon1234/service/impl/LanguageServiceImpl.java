package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Language;
import com.lemon1234.mapper.LanguageMapper;
import com.lemon1234.service.LanguageService;

@Service("languageService")
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private LanguageMapper languageMapper;

	@Override
	public List<Language> getlist(Map<String, Object> param) {
		return languageMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return languageMapper.getCount(param);
	}

	@Override
	public void addLanguage(Language language) {
		languageMapper.addLanguage(language);
	}

	@Override
	public void delete(Integer id) {
		languageMapper.delete(id);
	}

	@Override
	public Language getById(Integer id) {
		return languageMapper.getById(id);
	}

	@Override
	public void update(Language language) {
		languageMapper.update(language);
	}
	
}
