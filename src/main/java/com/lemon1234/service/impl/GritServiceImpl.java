package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Grit;
import com.lemon1234.mapper.GritMapper;
import com.lemon1234.service.GritService;

@Service("gritService")
public class GritServiceImpl implements GritService {
	
	@Autowired
	private GritMapper gritMapper;

	@Override
	public Grit getRand() {
		return gritMapper.getRand();
	}

	@Override
	public List<Grit> list(Map<String, Object> param) {
		return gritMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return gritMapper.getCount(param);
	}

	@Override
	public void update(Grit grit) {
		gritMapper.update(grit);
	}

	@Override
	public void delete(Integer id) {
		gritMapper.delete(id);		
	}

	@Override
	public void addGrit(Grit grit) {
		gritMapper.addGrit(grit);		
	}

	@Override
	public List<Grit> getGrits(String openId) {
		return gritMapper.getGrits(openId);
	}

}
