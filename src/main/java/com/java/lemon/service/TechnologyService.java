package com.java.lemon.service;

import java.util.List;

import com.java.lemon.model.entity.Technology;

public interface TechnologyService {

	List<Technology> getAllTechnology();
	
	List<Technology> getStudyRoadTechnology(Integer roadId);
}
