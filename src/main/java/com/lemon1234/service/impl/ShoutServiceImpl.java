package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Shout;
import com.lemon1234.mapper.ShoutMapper;
import com.lemon1234.service.ShoutService;

@Service("shoutService")
public class ShoutServiceImpl implements ShoutService {
	
	@Autowired
	private ShoutMapper shoutMapper;

	@Override
	public void addShout(Shout shout) {
		shoutMapper.addShout(shout);
	}

	@Override
	public List<Shout> getShout(String openId) {
		return shoutMapper.getShout(openId);
	}

	@Override
	public List<Shout> list(Map<String, Object> param) {
		return shoutMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return shoutMapper.getCount(param);
	}

	@Override
	public List<Map<String, Object>> proportion() {
		return shoutMapper.proportion();
	}

}
