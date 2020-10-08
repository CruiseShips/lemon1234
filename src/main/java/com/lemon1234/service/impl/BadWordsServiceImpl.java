package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.BadWords;
import com.lemon1234.mapper.BadWordsMapper;
import com.lemon1234.service.BadWordsService;

@Service("badWordsService")
public class BadWordsServiceImpl implements BadWordsService {
	
	@Autowired
	private BadWordsMapper badWordsMapper;

	@Override
	public List<BadWords> getlist(Map<String, Object> param) {
		return badWordsMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return badWordsMapper.getCount(param);
	}

	@Override
	public List<String> getwords() {
		return badWordsMapper.getwords();
	}

	@Override
	public void delete(Integer id) {
		badWordsMapper.delete(id);		
	}

	@Override
	public BadWords getdetail(Integer id) {
		return badWordsMapper.getdetail(id);
	}

	@Override
	public void update(BadWords word) {
		badWordsMapper.update(word);
	}

	@Override
	public void addWords(BadWords word) {
		badWordsMapper.addWords(word);		
	}

}
