package com.lemon1234.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon1234.entity.Doubt;
import com.lemon1234.entity.InterviewQuestions;
import com.lemon1234.entity.Question;
import com.lemon1234.service.DoubtService;
import com.lemon1234.service.InterviewQuestionsService;
import com.lemon1234.service.QuestionService;

@Controller
@RequestMapping("/admin/question")
public class QuestionAdminController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private DoubtService doubtService;
	
	@Autowired
	private InterviewQuestionsService ivqService;
	
	@RequiresPermissions(value = {"查看所有面试问题"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "id", required = false)Integer id) throws Exception {
		
		List<Question> questionList = questionService.getlistbyViewId(id);
		Integer count = questionService.getCountbyViewId(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", questionList);
		return result;
	}
	
	@RequiresPermissions(value = {"更改面试问题"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(@RequestParam(value = "id", required = false)Integer id,
			@RequestParam(value = "status", required = false)Integer status) throws Exception {
		Question question = new Question();
		question.setId(id);
		question.setStatus(status);
		
		questionService.update(question);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"删除面试问题"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		doubtService.deletebyqId(id);
		Question question = questionService.getdetail(id);
		
		InterviewQuestions interviewQuestions = new InterviewQuestions();
		interviewQuestions.setId(question.getViewId());
		interviewQuestions.setqCount(-1);
		ivqService.update(interviewQuestions);
		
		questionService.delete(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"获取面试题目数量"})
	@ResponseBody
	@RequestMapping("/getCount")
	public Map<String, Object> getCount(@RequestParam(value = "id", required = false)Integer id) throws Exception {
		Integer count = questionService.getCountbyViewId(id);
		Map<String, Object> result = new HashMap<String, Object>();
		if(count >= 20) {
			result.put("success", false);
		} else {
			result.put("success", true);
		}
		return result;
	}
	
	@RequiresPermissions(value = {"添加面试问题"})
	@ResponseBody
	@RequestMapping("/addQuestion")
	public Map<String, Object> addQuestion(Question question) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(question.getViewId() == 0) {
			result.put("success", false);
			result.put("errorInfo", "服务器异常，请稍后再试");
			return result;
		} else {
			if(question.getIsChoose() == 1 || question.getIsChoose() == 2) {
				if(question.getOption() == "" || question.getOption() == null) {
					result.put("success", false);
					result.put("errorInfo", "请输入选项");
					return result;
				}
			}
			question.setFounder("admin");
			question.setStatus(1);
			questionService.addQuestion(question);
			
			InterviewQuestions interviewQuestions = new InterviewQuestions();
			interviewQuestions.setId(question.getViewId());
			interviewQuestions.setqCount(1);
			ivqService.update(interviewQuestions);
			result.put("success", true);
			return result;
		}
	}
	
	@RequiresPermissions(value = {"查看面试问题"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Question question = questionService.getdetail(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("question", question);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"更改面试问题"})
	@ResponseBody
	@RequestMapping("/updQuestion")
	public Map<String, Object> updQuestion(Question question, Integer doubtId) throws Exception {
		// 主要这个老弄成默认值 0
		question.setStatus(1);
		questionService.update(question);
		
		Doubt doubt = new Doubt();
		doubt.setId(doubtId);
		doubt.setStatus(1);
		doubt.setProcessDt(new Date());
		
		doubtService.update(doubt);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
}
