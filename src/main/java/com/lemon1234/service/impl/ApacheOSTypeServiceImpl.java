package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.ApacheOSType;
import com.lemon1234.mapper.ApacheOSTypeMapper;
import com.lemon1234.service.ApacheOSTypeService;

@Service("apacheOSTypeService")
public class ApacheOSTypeServiceImpl implements ApacheOSTypeService {
	
	@Autowired
	private ApacheOSTypeMapper apacheOSTypeMapper;

	@Override
	public List<ApacheOSType> getlist(Map<String, Object> param) {
		return apacheOSTypeMapper.getlist(param);
	}

	@Override
	public ApacheOSType getById(ApacheOSType apacheOSType) {
		return apacheOSTypeMapper.getById(apacheOSType);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return apacheOSTypeMapper.getCount(param);
	}

	@Override
	public void delete(Integer id) {
		apacheOSTypeMapper.delete(id);
	}

	@Override
	public void update(ApacheOSType apacheOSType) {
		apacheOSTypeMapper.update(apacheOSType);
	}

	@Override
	public void addApacheOSType(ApacheOSType apacheOSType) {
		apacheOSTypeMapper.addApacheOSType(apacheOSType);
	}

}
