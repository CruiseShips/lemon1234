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

import com.lemon1234.entity.BadWords;
import com.lemon1234.service.BadWordsService;
import com.lemon1234.start.Lemon1234ServerStart;

@Controller
@RequestMapping("/admin/badword")
public class BadWordsAdminController {

	@Autowired
	private Lemon1234ServerStart start;
	
	@Autowired
	private BadWordsService badWordsService;
	
	@RequiresPermissions(value = {"查看所有敏感词汇"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getMenus(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<BadWords> menuList = badWordsService.getlist(param);
		Integer count = badWordsService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", menuList);
		return result;
	}
	
	@RequiresPermissions(value = {"删除敏感词汇"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		badWordsService.delete(id);
		start.initBadWord();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"获取敏感词汇"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BadWords word = badWordsService.getdetail(id);
		
		result.put("word", word);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改敏感词汇"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(BadWords word) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(word != null && word.getId() != 0) {
			badWordsService.update(word);
			start.initBadWord();
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"添加敏感词汇"})
	@ResponseBody
	@RequestMapping("/addWords")
	public Map<String, Object> addWords(BadWords word) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		badWordsService.addWords(word);
		start.initBadWord();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
