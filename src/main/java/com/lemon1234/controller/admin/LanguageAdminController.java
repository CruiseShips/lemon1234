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

import com.lemon1234.entity.Language;
import com.lemon1234.service.OpenOSService;
import com.lemon1234.service.LanguageService;

@Controller
@RequestMapping("/admin/language")
public class LanguageAdminController {

	@Autowired
	private LanguageService languageService;
	@Autowired
	private OpenOSService openOSService;
	
	@RequiresPermissions(value={"查看所有language"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getlist(@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if(page != null && limit != null) {
			param.put("page", this.getPageStart(page, limit));
			param.put("limit", limit);
		}
		
		List<Language> languages = languageService.getlist(param);
		Integer count = languageService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", languages);
		return result;
	}
	
	@RequiresPermissions(value = {"添加Language"})
	@ResponseBody
	@RequestMapping("/addLanguage")
	public Map<String, Object> addLanguage(Language language) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		languageService.addLanguage(language);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"删除Language"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer count = openOSService.findByLanguageId(id);
		
		if(count > 0) {
			result.put("success", false);
		} else {
			languageService.delete(id);
			result.put("success", true);
		}
		return result;
	}
	
	@RequiresPermissions(value = {"获取Language"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Language language = languageService.getById(id);
		
		result.put("success", true);
		result.put("language", language);
		return result;
	}
	
	@RequiresPermissions(value = {"修改Language"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Language language) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		languageService.update(language);
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
