package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.OpenOSType;
import com.lemon1234.mapper.OpenOSTypeMapper;
import com.lemon1234.service.OpenOSTypeService;

@Service("openOSTypeService")
public class OpenOSTypeServiceImpl implements OpenOSTypeService {
	
	@Autowired
	private OpenOSTypeMapper openOSTypeMapper;

	@Override
	public List<OpenOSType> getlist(Map<String, Object> param) {
		return openOSTypeMapper.getlist(param);
	}

	@Override
	public OpenOSType getById(OpenOSType openOSType) {
		return openOSTypeMapper.getById(openOSType);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return openOSTypeMapper.getCount(param);
	}

	@Override
	public void delete(Integer id) {
		openOSTypeMapper.delete(id);
	}

	@Override
	public void update(OpenOSType openOSType) {
		openOSTypeMapper.update(openOSType);
	}

	@Override
	public void addOpenOSType(OpenOSType openOSType) {
		openOSTypeMapper.addOpenOSType(openOSType);
	}

}
