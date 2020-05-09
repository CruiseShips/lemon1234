package com.java.lemon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.lemon.mapper.StudyRoadMapMapper;
import com.java.lemon.model.entity.StudyRoadMap;
import com.java.lemon.service.StudyRoadMapService;

@Service("studyRoadMapService")
public class StudyRoadMapServiceImpl implements StudyRoadMapService {
	
	@Autowired
	private StudyRoadMapMapper studyRoadMapMapper;

	@Override
	public List<StudyRoadMap> getAllStudyRoadMap() {
		return studyRoadMapMapper.getAllStudyRoadMap();
	}

}
