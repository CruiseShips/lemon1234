package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.InterviewQuestions;

public interface InterviewQuestionsMapper {

	List<InterviewQuestions> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	InterviewQuestions getdetail(Integer id);
	
	void update(InterviewQuestions interviewQuestions);
	
	void delete(Integer id);
	
	void addIvq(InterviewQuestions interviewQuestions);
}
