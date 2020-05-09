package com.java.lemon.mapper;

import java.util.List;

import com.java.lemon.model.entity.Technology;

public interface TechnologyMapper {
	
	List<Technology> getAllTechnology();
	
	List<Technology> getStudyRoadTechnology(Integer roadId);

}
