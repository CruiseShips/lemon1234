package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Question;
import com.lemon1234.mapper.QuestionMapper;
import com.lemon1234.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> getlistbyivqId(Map<String, Object> param) {
		return questionMapper.getlistbyivqId(param);
	}

	@Override
	public List<Question> getlistbyViewId(Integer viewId) {
		return questionMapper.getlistbyViewId(viewId);
	}

	@Override
	public Integer getCountbyViewId(Integer viewId) {
		return questionMapper.getCountbyViewId(viewId);
	}

	@Override
	public void update(Question question) {
		questionMapper.update(question);		
	}

	@Override
	public void delete(Integer id) {
		questionMapper.delete(id);		
	}

	@Override
	public void deletebyviewId(Integer id) {
		questionMapper.deletebyviewId(id);
	}

	@Override
	public void addQuestion(Question question) {
		questionMapper.addQuestion(question);		
	}

	@Override
	public Question getdetail(Integer id) {
		return questionMapper.getdetail(id);
	}
	
}
