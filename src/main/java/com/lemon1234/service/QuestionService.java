package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Question;

public interface QuestionService {

	List<Question> getlistbyivqId(Map<String, Object> param);
	
	List<Question> getlistbyViewId(Integer viewId);
	
	Integer getCountbyViewId(Integer viewId);
	
	void update(Question question);
	
	void delete(Integer id);
	
	void deletebyviewId(Integer id);
	
	void addQuestion(Question question);
	
	Question getdetail(Integer id);
}
