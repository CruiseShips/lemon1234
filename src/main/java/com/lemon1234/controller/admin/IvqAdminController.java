package com.lemon1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon1234.entity.InterviewQuestions;
import com.lemon1234.service.DoubtService;
import com.lemon1234.service.InterviewQuestionsService;
import com.lemon1234.service.QuestionService;

@Controller
@RequestMapping("/admin/ivq")
public class IvqAdminController {
	
	@Autowired
	private InterviewQuestionsService ivqService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private DoubtService doubtService;

	@RequiresPermissions(value = {"查看所有Java面试题"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		
		List<InterviewQuestions> ivqList = ivqService.getlist(param);
		Integer count = ivqService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", ivqList);
		return result;
	}
	
	@RequiresPermissions(value = {"删除Java面试题"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		doubtService.deletebyviewId(id);
		questionService.deletebyviewId(id);
		ivqService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequiresPermissions(value = {"修改Java面试题"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(InterviewQuestions questions) throws Exception {
		InterviewQuestions interviewQuestions = new InterviewQuestions();
		interviewQuestions.setId(questions.getId());
		if(questions.getName() != null) {
			interviewQuestions.setName(questions.getName());
		} else {
			interviewQuestions.setHard(questions.getHard());
		}
		ivqService.update(interviewQuestions);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequiresPermissions(value = {"添加Java面试题"})
	@ResponseBody
	@RequestMapping("/addIvq")
	public Map<String, Object> addIvq(InterviewQuestions interviewQuestions) throws Exception {
		ivqService.addIvq(interviewQuestions);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		return resultMap;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
