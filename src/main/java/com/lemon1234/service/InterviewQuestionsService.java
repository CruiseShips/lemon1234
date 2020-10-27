package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.InterviewQuestions;

public interface InterviewQuestionsService {

	List<InterviewQuestions> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	InterviewQuestions getdetail(Integer id);
	
	void update(InterviewQuestions interviewQuestions);
	
	void delete(Integer id);
	
	void addIvq(InterviewQuestions interviewQuestions);
}
