package com.lemon1234.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Activity;
import com.lemon1234.entity.Announcement;
import com.lemon1234.entity.OpenOS;
import com.lemon1234.entity.Document;
import com.lemon1234.service.ActivityService;
import com.lemon1234.service.AnnouncementService;
import com.lemon1234.service.OpenOSService;
import com.lemon1234.service.DocumentService;

@Controller
public class IndexController {
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private DocumentService documentService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private OpenOSService openOSService;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/")
	public ModelAndView index() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", 0);
		param.put("limit", 10);
		param.put("status", 0);
		List<Document> documentList = documentService.getlist(param);
		param.put("limit", 5);
		List<OpenOS> openOSList = openOSService.getlist(param);
		param.clear();
		param.put("page", 0);
		param.put("limit", 6);
		List<Announcement> announcementList = announcementService.getlist(param);
		param.clear();
		param.put("status", 0);
		List<Activity> activityList = activityService.list(param);
		
		for (Announcement a:announcementList) {
			a.setYyyyMMdd(sdf.format(a.getCreateDt()));
		}
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("documentList", documentList);
		mav.addObject("announcementList", announcementList);
		mav.addObject("openOSList", openOSList);
		mav.addObject("activityList", activityList);
		mav.addObject("url", "common/body");
		mav.addObject("port", "#body");
		mav.addObject("title", "Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/getOSCode")
	public ModelAndView getOSCode() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "body/other/getOSCode");
		mav.addObject("port", "#getOSCode");
		mav.addObject("title", "获取源码 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "body/other/aboutMe");
		mav.addObject("port", "#aboutMe");
		mav.addObject("title", "关于站长 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/route")
	public ModelAndView route() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "body/other/route");
		mav.addObject("port", "#route");
		mav.addObject("title", "Java学习路线图 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
}
