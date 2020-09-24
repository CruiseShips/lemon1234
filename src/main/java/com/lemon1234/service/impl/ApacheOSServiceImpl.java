package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.ApacheOS;
import com.lemon1234.mapper.ApacheOSMapper;
import com.lemon1234.service.ApacheOSService;

@Service("apacheOSService")
public class ApacheOSServiceImpl implements ApacheOSService {

	@Autowired
	private ApacheOSMapper apacheOSMapper;
	
	@Override
	public List<ApacheOS> getlist(Map<String, Object> param) {
		return apacheOSMapper.getlist(param);
	}
	
	@Override
	public Integer getCount(Map<String, Object> param) {
		return apacheOSMapper.getCount(param);
	}

	@Override
	public ApacheOS getById(Integer id) {
		return apacheOSMapper.getById(id);
	}

	@Override
	public void updateApacheOS(ApacheOS apacheOS) {
		apacheOSMapper.updateApacheOS(apacheOS);
	}

	@Override
	public void deleteApacheOS(Integer id) {
		apacheOSMapper.deleteApacheOS(id);
	}

	@Override
	public void addApacheOS(ApacheOS apacheOS) {
		apacheOSMapper.addApacheOS(apacheOS);
	}

	@Override
	public Integer findByTypeId(Integer typeId) {
		return apacheOSMapper.findByTypeId(typeId);
	}

	@Override
	public Integer findByLanguageId(Integer id) {
		return apacheOSMapper.findByLanguageId(id);
	}

}
