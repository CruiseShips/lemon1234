package com.java.lemon.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.java.lemon.model.entity.Project;

@Component
public interface ProjectMapper {

	/**
	 * 获取所有的项目
	 * @return
	 */
	List<Project> getAllProject(Map<String, Object> map);
	
	/**
	 * 获取单个所有的信息
	 * @param id
	 * @return
	 */
	Project getProject(Integer id);
	
	/**
	 * 配合分页，查询所有数量
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 上一篇 下一篇
	 * @param id
	 * @return
	 */
	Project getLastProject(Integer id);
	/**
	 * 上一篇 下一篇
	 * @param id
	 * @return
	 */
	Project getNextProject(Integer id);
	
	/**
	 * 主页获取top9
	 * @return
	 */
	List<Project> getTop9Project();
}
