package com.lemon1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Message;
import com.lemon1234.service.BadWordsService;
import com.lemon1234.service.MessageService;
import com.lemon1234.util.HttpRequestUtil;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.SensitiveWordsUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private BadWordsService badWordsService;
	
	private static final int PAGESIZE = 10;
	
	@RequestMapping("/getlist/{page}")
	public ModelAndView getlist(@PathVariable(value="page",required=false) String page) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		param.put("page", this.getPageStart(page));
		param.put("limit", 20);
		
		List<Message> messageList = messageService.getlist(param);
		Integer count = messageService.getCount(param);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageList", messageList);
		mav.addObject("pageCode", PageUtil.genPagination("/message/getlist", count, Integer.parseInt(page), 10, ""));
		
		mav.addObject("url", "body/message/list");
		mav.addObject("port", "#message");
		mav.addObject("title", "留言板 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/addMessage")
	public Map<String, Object> addMessage(String message, HttpServletRequest request) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(StringUtil.isEmpty(message)) {
			result.put("errorInfo", "请输入留言内容");
			result.put("success", false);
			return result;
		}
		
		// 检测是不是有不良词汇
		ServletContext servletContext = request.getServletContext();
		@SuppressWarnings("unchecked")
		List<String> words = (List<String>) servletContext.getAttribute("wordList");
		if(words == null) {
			words = badWordsService.getwords();
		}
		boolean isFalse = SensitiveWordsUtil.badWordFind(words, message);
		if(isFalse) {
			result.put("errorInfo", "留言内容有不文明词汇");
			result.put("success", false);
			return result;
		}
		String ip = HttpRequestUtil.getIpAddress(request);
		messageService.addMessage(new Message(ip, message));
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
	}
	
}
