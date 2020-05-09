package com.java.lemon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.lemon.mapper.TechnologyMapper;
import com.java.lemon.model.entity.Technology;
import com.java.lemon.service.TechnologyService;

@Service("technologyService")
public class TechnologyServiceImpl implements TechnologyService {
	
	@Autowired
	private TechnologyMapper technologyMapper;

	@Override
	public List<Technology> getAllTechnology() {
		return technologyMapper.getAllTechnology();
	}

	@Override
	public List<Technology> getStudyRoadTechnology(Integer roadId) {
		return technologyMapper.getStudyRoadTechnology(roadId);
	}

}
