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

import com.lemon1234.entity.Doubt;
import com.lemon1234.service.DoubtService;

@Controller
@RequestMapping("/admin/doubt")
public class DoubtAdminController {

	@Autowired
	private DoubtService doubtService;
	
	@RequiresPermissions(value = {"查看所有有问题的面试题"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		
		List<Doubt> documents = doubtService.list(param);
		Integer count = doubtService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", documents);
		return result;
	}
	
	@RequiresPermissions(value = {"修改有问题的面试题状态"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(
			@RequestParam(value = "id", required = false)Integer id,
			@RequestParam(value = "status", required = false)Integer status) throws Exception {
		Doubt doubt = new Doubt();
		doubt.setId(id);
		doubt.setStatus(status);
		
		doubtService.update(doubt);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"删除有问题的面试题状态"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		doubtService.delete(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
