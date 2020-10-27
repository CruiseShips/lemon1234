package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.InterviewQuestions;
import com.lemon1234.mapper.InterviewQuestionsMapper;
import com.lemon1234.service.InterviewQuestionsService;

@Service("interviewQuestionsService")
public class InterviewQuestionsServiceImpl implements InterviewQuestionsService {
	
	@Autowired
	private InterviewQuestionsMapper ivqMapper;

	@Override
	public List<InterviewQuestions> getlist(Map<String, Object> param) {
		return ivqMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return ivqMapper.getCount(param);
	}

	@Override
	public InterviewQuestions getdetail(Integer id) {
		return ivqMapper.getdetail(id);
	}

	@Override
	public void update(InterviewQuestions interviewQuestions) {
		ivqMapper.update(interviewQuestions);		
	}

	@Override
	public void delete(Integer id) {
		ivqMapper.delete(id);		
	}

	@Override
	public void addIvq(InterviewQuestions interviewQuestions) {
		ivqMapper.addIvq(interviewQuestions);		
	}

}
