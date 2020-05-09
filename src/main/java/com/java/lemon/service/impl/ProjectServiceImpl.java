package com.java.lemon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.lemon.mapper.ProjectMapper;
import com.java.lemon.model.entity.Project;
import com.java.lemon.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> getAllProject(Map<String, Object> map) {
		List<Project> projectList = projectMapper.getAllProject(map);
		return projectList;
	}

	@Override
	public Project getProject(Integer id) {
		return projectMapper.getProject(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return projectMapper.getCount(map);
	}

	@Override
	public Project getLastProject(Integer id) {
		return projectMapper.getLastProject(id);
	}

	@Override
	public Project getNextProject(Integer id) {
		return projectMapper.getNextProject(id);
	}

	@Override
	public List<Project> getTop9Project() {
		return projectMapper.getTop9Project();
	}

}
