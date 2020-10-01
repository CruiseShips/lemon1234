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

import com.lemon1234.entity.Document;
import com.lemon1234.entity.Register;
import com.lemon1234.service.DocumentService;
import com.lemon1234.service.RegisterService;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private RegisterService registerService;
	
	private static final int PAGESIZE = 10;
	
	@RequestMapping("/getlist/{page}")
	public ModelAndView getlist(@PathVariable(value = "page", required = false) String page, 
								@RequestParam(value = "status", required = false, defaultValue = "0")String status,
								@RequestParam(value = "title", required = false) String title
							   ) throws Exception {
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page));
		param.put("limit", 10);
		param.put("status", status);
		if(title != null) {
			param.put("name", title);
		}
		List<Document> documentList = documentService.getlist(param);
		Integer count = documentService.getCount(param);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("documentList", documentList);
		StringBuffer sb = new StringBuffer();
		if(title != null) {
			sb.append("?title=" + title);
		}
		mav.addObject("pageCode", PageUtil.genPagination("/document/getlist", count, Integer.parseInt(page), 10, sb.toString()));
		mav.addObject("count", count);
		if(title != null) {
			mav.addObject("documentName", title);
		}
		mav.addObject("url", "body/document/list");
		mav.addObject("port", "#list");
		mav.addObject("title", "技术文档 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView getById(@PathVariable(value = "id", required = false) int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		Document document = documentService.getById(id);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", Register.DOCUMENT);
		param.put("docId", id);
		param.put("page", 0);
		param.put("limit", 5);
		List<Register> registerList = registerService.getlist(param);
		
		if(document == null) {
			mav.addObject("url", "common/404");
			mav.addObject("port", "#error404");
			mav.addObject("title", "404 - Lemon1234");
		} else {
			mav.addObject("document", document);
			mav.addObject("registerList", registerList);
			mav.addObject("url", "body/document/detail");
			mav.addObject("port", "#document");
			mav.addObject("title", document.getName() + " - Lemon1234");
		}
		mav.setViewName("index");
		return mav;
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
	}
}
