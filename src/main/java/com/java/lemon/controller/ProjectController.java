package com.java.lemon.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.lemon.model.dto.ProjectDTO;
import com.java.lemon.model.entity.Project;
import com.java.lemon.service.ProjectService;
import com.java.lemon.util.StringUtil;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final int PAGESIZE = 10;

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/getAllProject/{page}")
	public ModelAndView getAllProject(@PathVariable(value="page",required=false)String page) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", this.getPageStart(page));
		map.put("size", 10);
		map.put("status", Project.RELEASE);
		List<Project> projectList = projectService.getAllProject(map);
		int listSize = projectService.getCount(map);
		int pageInt = Integer.parseInt(page);
		
		if(listSize == 0 || projectList.size() == 0) { // 没有数据
			mav.addObject("url", "body/empty");
			mav.addObject("port", "#empty");
			mav.addObject("title", "Lemon1234 - 页面不存在");
			mav.setViewName("index");
		} else if (listSize > 0 && listSize <= PAGESIZE) { // 正好就一页 也不需要这个上一页下一页
			
			List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
			for(Project project : projectList) {
				ProjectDTO dto = new ProjectDTO();
				dto.setId(project.getId());
				dto.setName(project.getName());
				dto.setSummary(project.getSummary());
				dto.setReleaseDateStr(sdf.format(project.getReleaseDate()));
				dto.setClickHit(project.getClickHit());
				dto.setLikeHit(project.getLikeHit());
				
				projectDTOs.add(dto);
			}
			
			mav.addObject("projectList", projectDTOs);
			mav.addObject("url", "body/getAllProject");
			mav.addObject("port", "#getAllProject");
			mav.addObject("title", "Lemon1234 - Java开源项目");
			mav.setViewName("index");
		} else if(listSize > PAGESIZE) { // 超过一页
			
			List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
			for(Project project : projectList) {
				ProjectDTO dto = new ProjectDTO();
				dto.setId(project.getId());
				dto.setName(project.getName());
				dto.setSummary(project.getSummary());
				dto.setReleaseDateStr(sdf.format(project.getReleaseDate()));
				dto.setClickHit(project.getClickHit());
				dto.setLikeHit(project.getLikeHit());
				
				projectDTOs.add(dto);
			}
			
			if(listSize <= 20) { // 如果是 11 12 13 页...20(判断是不是第一页或者最后一页)
				if("1".equals(page)) {
					mav.addObject("end", "/project/getAllProject/2");
				} else {
					mav.addObject("start", "/project/getAllProject/1");
				}
			} else { // 如果是 21 22 23页(判断是不是第一页或者最后一页)
				if("1".equals(page)) {
					mav.addObject("end", "/project/getAllProject/2");
				} else if(pageInt != listSize/PAGESIZE + 1) {
					// 上一页
					mav.addObject("start", "/project/getAllProject/" + (pageInt-1));
					// 下一页
					mav.addObject("end", "/project/getAllProject/" + (pageInt+1));
				} else {
					mav.addObject("start", "/project/getAllProject/" + (listSize/PAGESIZE));
				}
			}
			
			mav.addObject("projectList", projectDTOs);
			mav.addObject("url", "body/getAllProject");
			mav.addObject("port", "#getAllProject");
			mav.addObject("title", "Lemon1234 - Java开源项目-第"+page+"页");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/getProject/{id}")
	public ModelAndView getProject(@PathVariable(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav=new ModelAndView();
		Project project = projectService.getProject(id);
		// TODO 弄个全局错误页面
		// 缺少 点击后+1 查看
		
		ProjectDTO dto = new ProjectDTO();
		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setReleaseDateStr(sdf.format(project.getReleaseDate()));
		dto.setClickHit(project.getClickHit());
		dto.setLikeHit(project.getLikeHit());
		dto.setContent(project.getContent());
		dto.setGitHubUrl(project.getGitHubUrl());
		dto.setVideoUrl(project.getVideoUrl());
		// TODO 需要从管理员表中查找到，或者弄成缓存，从缓存中找
		dto.setShareName(project.getShareName());
		
		mav.addObject("project", dto);
		mav.addObject("url", "body/getProject");
		mav.addObject("port", "#getProject");
		if(project.getName().length() > 7) {
			mav.addObject("title", "Lemon1234 - " + project.getName().substring(0, 7)+"...");
		} else {
			mav.addObject("title", "Lemon1234 - " + project.getName());
		}
		// 上一篇 下一篇
		Project lastPorProject = projectService.getLastProject(id);
		Project nextPorProject = projectService.getNextProject(id);
		if(lastPorProject != null) {
			mav.addObject("lastPorProject", lastPorProject);
		}
		if(nextPorProject != null) {
			mav.addObject("nextPorProject", nextPorProject);
		}
		
		mav.setViewName("index");
		return mav;
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
		
	}
}
