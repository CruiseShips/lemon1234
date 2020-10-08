package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.BadWords;

public interface BadWordsMapper {

	List<BadWords> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	List<String> getwords();

	void delete(Integer id);

	BadWords getdetail(Integer id);

	void update(BadWords word);

	void addWords(BadWords word);
}
