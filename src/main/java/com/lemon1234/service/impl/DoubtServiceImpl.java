package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Doubt;
import com.lemon1234.mapper.DoubtMapper;
import com.lemon1234.service.DoubtService;

@Service("doubtService")
public class DoubtServiceImpl implements DoubtService {
	
	@Autowired
	private DoubtMapper doubtMapper;

	@Override
	public void addDoubt(Doubt doubt) {
		doubtMapper.addDoubt(doubt);
	}

	@Override
	public void deletebyviewId(Integer id) {
		doubtMapper.deletebyviewId(id);
	}

	@Override
	public void deletebyqId(Integer id) {
		doubtMapper.deletebyqId(id);		
	}

	@Override
	public List<Doubt> list(Map<String, Object> param) {
		return doubtMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return doubtMapper.getCount(param);
	}

	@Override
	public void update(Doubt doubt) {
		doubtMapper.update(doubt);		
	}

	@Override
	public void delete(Integer id) {
		doubtMapper.delete(id);		
	}

}
