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

import com.lemon1234.entity.ApacheOS;
import com.lemon1234.entity.ApacheOSType;
import com.lemon1234.service.ApacheOSService;
import com.lemon1234.service.ApacheOSTypeService;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/apache")
public class ApacheController {
	
	@Autowired
	private ApacheOSService apacheOSService;
	@Autowired
	private ApacheOSTypeService apacheOSTypeService;
	
	private static final int PAGESIZE = 10;

	@RequestMapping("/{id}")
	public ModelAndView getbyId(@PathVariable(value="id",required=false) int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		ApacheOS apacheOS = apacheOSService.getById(id);
		this.updateView(id);
		if(apacheOS == null) {
			mav.addObject("url", "common/404");
			mav.addObject("port", "#error404");
			mav.addObject("title", "404 - Lemon1234");
		} else {
			// 查看的是 +1 之后的数
			apacheOS.setView(apacheOS.getView() + 1);
			
			mav.addObject("apacheOS", apacheOS);
			mav.addObject("url", "body/apache/detail");
			mav.addObject("port", "#apache");
			mav.addObject("title", apacheOS.getName() + " - Lemon1234");
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
		}
		
		List<ApacheOS> apacheOSList = apacheOSService.getlist(param);
		Integer count = apacheOSService.getCount(param);
		StringBuffer sb = new StringBuffer();
		ApacheOSType apacheOSType = null;
		if(StringUtil.isNotEmpty(typeId)) {
			sb.append("?typeId=" + typeId);
			apacheOSType = apacheOSTypeService.getById(new ApacheOSType(typeId));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("apacheOSList", apacheOSList);
		mav.addObject("pageCode", PageUtil.genPagination("/apache/getlist", count, Integer.parseInt(page), 10, sb.toString()));
		mav.addObject("apacheOSType", apacheOSType);
		mav.addObject("url", "body/apache/list");
		mav.addObject("port", "#apache");
		mav.addObject("title", "Apache 开源组件 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	private void updateView(int id) throws Exception {
		ApacheOS apacheOS = new ApacheOS();
		apacheOS.setId(id);
		apacheOS.setView(1);
		apacheOSService.updateApacheOS(apacheOS);
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
	}
}
