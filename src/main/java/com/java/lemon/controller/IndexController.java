package com.java.lemon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.lemon.model.entity.Document;
import com.java.lemon.model.entity.Project;
import com.java.lemon.service.DocumentService;
import com.java.lemon.service.ProjectService;

@Controller
public class IndexController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DocumentService documentService;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		List<Project> projects = projectService.getTop9Project();
		List<Document> documents = documentService.getTop9Document();
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("projects", projects);
		mav.addObject("documents", documents);
		mav.addObject("url", "common/body");
		mav.addObject("port", "#body");
		mav.addObject("title", "Lemon1234");
		mav.setViewName("index");
		return mav;
	}
}
