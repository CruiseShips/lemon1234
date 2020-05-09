package com.java.lemon.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.lemon.service.ProjectService;

@Controller
@RequestMapping("/admin/project")
public class ProjectAdminController {

	private ProjectService projectService;
	
	
}
