package com.lemon1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.OpenOS;
import com.lemon1234.entity.OpenOSType;
import com.lemon1234.service.OpenOSService;
import com.lemon1234.service.OpenOSTypeService;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/open")
public class OpenController {
	
	@Autowired
	private OpenOSService openOSService;
	@Autowired
	private OpenOSTypeService openOSTypeService;
	
	private static final int PAGESIZE = 10;

	@RequestMapping("/{id}")
	public ModelAndView getbyId(@PathVariable(value="id",required=false) int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		OpenOS openOS = openOSService.getById(id);
		this.updateView(id);
		if(openOS == null) {
			mav.addObject("url", "common/404");
			mav.addObject("port", "#error404");
			mav.addObject("title", "404 - Lemon1234");
		} else {
			// 查看的是 +1 之后的数
			openOS.setView(openOS.getView() + 1);
			
			mav.addObject("openOS", openOS);
			mav.addObject("url", "body/open/detail");
			mav.addObject("port", "#open");
			mav.addObject("title", openOS.getName() + " - Lemon1234");
		}
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/getlist/{page}")
	public ModelAndView getlist(@PathVariable(value="page",required=false) String page, @RequestParam(value = "status", required = false)String status,
			@RequestParam(value = "typeId", required = false)String typeId
			) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		param.put("page", this.getPageStart(page));
		param.put("limit", 10);
		if(StringUtil.isNotEmpty(typeId)) {
			param.put("typeId", typeId);
		}
		if(StringUtil.isNotEmpty(status)) {
			param.put("status", status);
		} else {
			param.put("status", 0);
		}
		
		List<OpenOS> openOSList = openOSService.getlist(param);
		Integer count = openOSService.getCount(param);
		StringBuffer sb = new StringBuffer();
		OpenOSType openOSType = null;
		if(StringUtil.isNotEmpty(typeId)) {
			sb.append("?typeId=" + typeId);
			openOSType = openOSTypeService.getById(new OpenOSType(typeId));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("openOSList", openOSList);
		mav.addObject("pageCode", PageUtil.genPagination("/open/getlist", count, Integer.parseInt(page), 10, sb.toString()));
		mav.addObject("openOSType", openOSType);
		mav.addObject("url", "body/open/list");
		mav.addObject("port", "#open");
		mav.addObject("title", "Open 开源组件 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	private void updateView(int id) throws Exception {
		OpenOS openOS = new OpenOS();
		openOS.setId(id);
		openOS.setView(1);
		openOSService.updateOpenOS(openOS);
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
	}
}
