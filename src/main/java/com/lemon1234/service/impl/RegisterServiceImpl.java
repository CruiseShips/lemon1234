package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Register;
import com.lemon1234.mapper.RegisterMapper;
import com.lemon1234.service.RegisterService;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterMapper registerMapper;

	@Override
	public List<Register> getlist(Map<String, Object> param) {
		return registerMapper.getlist(param);
	}

	@Override
	public Integer addRegister(Register register) {
		return registerMapper.addRegister(register);		
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return registerMapper.getCount(param);
	}

	@Override
	public void delete(Integer id) {
		registerMapper.delete(id);
	}

	@Override
	public void update(Register register) {
		registerMapper.update(register);		
	}

}
