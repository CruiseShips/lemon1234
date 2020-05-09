package com.java.lemon.service;

import java.util.List;
import java.util.Map;

import com.java.lemon.model.entity.Project;

public interface ProjectService {

	List<Project> getAllProject(Map<String, Object> map);
	
	Project getProject(Integer id);
	
	int getCount(Map<String, Object> map);
	
	Project getLastProject(Integer id);
	Project getNextProject(Integer id);
	
	List<Project> getTop9Project();
}
