package com.lemon1234.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Activity;
import com.lemon1234.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/{id}")
	public ModelAndView getbyId(@PathVariable(value="id",required=false) int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Activity activity = activityService.getdetail(id);
		
		if(activity == null) {
			mav.addObject("url", "common/404");
			mav.addObject("port", "#error404");
			mav.addObject("title", "404 - Lemon1234");
		} else {
			mav.addObject("activity", activity);
			mav.addObject("url", "body/activity/detail");
			mav.addObject("port", "#activity");
			mav.addObject("title", activity.getTitle() + " - Lemon1234");
		}
		mav.setViewName("index");
		return mav;
	}
}
