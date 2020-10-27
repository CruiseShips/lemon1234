package com.lemon1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Doubt;
import com.lemon1234.entity.InterviewQuestions;
import com.lemon1234.entity.Question;
import com.lemon1234.service.DoubtService;
import com.lemon1234.service.InterviewQuestionsService;
import com.lemon1234.service.QuestionService;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/ivq")
public class IvqController {

	@Autowired
	private InterviewQuestionsService ivqService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private DoubtService doubtService;
	
	private static final int PAGESIZE = 10;
	
	@RequestMapping("/getlist/{page}")
	public ModelAndView getlist(@PathVariable(value = "page", required = false) String page
							   ) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		param.put("page", this.getPageStart(page));
		param.put("limit", 15);
		
		List<InterviewQuestions> ivqList = ivqService.getlist(param);
		Integer count = ivqService.getCount(param);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ivqList", ivqList);
		mav.addObject("pageCode", PageUtil.genPagination("/ivq/getlist", count, Integer.parseInt(page), 10, ""));
		
		mav.addObject("url", "body/ivq/list");
		mav.addObject("port", "#ivq");
		mav.addObject("title", "Java面试题 - Lemon1234");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView getbyId(@PathVariable(value="id",required=false) int id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("viewId", id);
		param.put("status", 1);
		ModelAndView mav = new ModelAndView();
		
		List<Question> questionList = questionService.getlistbyivqId(param);
		// 更新访问量
		InterviewQuestions interviewQuestions = new InterviewQuestions();
		interviewQuestions.setId(id);
		interviewQuestions.setView(1);
		ivqService.update(interviewQuestions);
		
		InterviewQuestions ivqInterviewQuestions = ivqService.getdetail(id);
		mav.addObject("ivqInterviewQuestions", ivqInterviewQuestions);
		mav.addObject("questionList", questionList);
		mav.addObject("url", "body/ivq/detail");
		mav.addObject("port", "#ivq");
		mav.addObject("title", ivqInterviewQuestions.getName() + " - Lemon1234");
		
		mav.setViewName("index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/addDoubt")
	public Map<String, Object> addDoubt(Doubt doubt) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		doubtService.addDoubt(doubt);
		
		// 把对应的问题下架
		// Question question = new Question();
		// question.setId(doubt.getqId());
		// question.setStatus(0);
		// questionService.update(question);
		
		resultMap.put("success", true);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/updAmount")
	public Map<String, Object> updAmount(
			@RequestParam(value = "id")Integer id,
			@RequestParam(value = "share", required = false)Integer share,
			@RequestParam(value = "star", required = false)Integer star
			) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		InterviewQuestions interviewQuestions = new InterviewQuestions();
		interviewQuestions.setId(id);
		if(share != null && share == 1) {
			interviewQuestions.setShare(1);
		} else {
			interviewQuestions.setStar(1);
		}
		ivqService.update(interviewQuestions);
		
		resultMap.put("success", true);
		return resultMap;
	}
	
	private int getPageStart(String page) {
		int pageInt = Integer.parseInt(page);
		return (pageInt-1) * PAGESIZE;
	}
	
}
