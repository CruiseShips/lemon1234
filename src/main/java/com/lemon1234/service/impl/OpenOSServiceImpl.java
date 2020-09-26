package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.OpenOS;
import com.lemon1234.mapper.OpenOSMapper;
import com.lemon1234.service.OpenOSService;

@Service("openOSService")
public class OpenOSServiceImpl implements OpenOSService {

	@Autowired
	private OpenOSMapper openOSMapper;
	
	@Override
	public List<OpenOS> getlist(Map<String, Object> param) {
		return openOSMapper.getlist(param);
	}
	
	@Override
	public Integer getCount(Map<String, Object> param) {
		return openOSMapper.getCount(param);
	}

	@Override
	public OpenOS getById(Integer id) {
		return openOSMapper.getById(id);
	}

	@Override
	public void updateOpenOS(OpenOS openOS) {
		openOSMapper.updateOpenOS(openOS);
	}

	@Override
	public void deleteOpenOS(Integer id) {
		openOSMapper.deleteOpenOS(id);
	}

	@Override
	public void addOpenOS(OpenOS openOS) {
		openOSMapper.addOpenOS(openOS);
	}

	@Override
	public Integer findByTypeId(Integer typeId) {
		return openOSMapper.findByTypeId(typeId);
	}

	@Override
	public Integer findByLanguageId(Integer id) {
		return openOSMapper.findByLanguageId(id);
	}

}
