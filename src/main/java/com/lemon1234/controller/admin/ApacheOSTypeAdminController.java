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

import com.lemon1234.entity.ApacheOSType;
import com.lemon1234.service.ApacheOSService;
import com.lemon1234.service.ApacheOSTypeService;
import com.lemon1234.start.Lemon1234ServerStart;

@Controller
@RequestMapping("/admin/apacheOSType")
public class ApacheOSTypeAdminController {

	@Autowired
	private ApacheOSTypeService apacheOSTypeService;
	@Autowired
	private ApacheOSService apacheOSService;
	@Autowired
	private Lemon1234ServerStart start;
	
	@RequiresPermissions(value={"查看所有apachetype"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getlist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		
		List<ApacheOSType> types = apacheOSTypeService.getlist(param);
		int count = apacheOSTypeService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", types);
		return result;
	}
	
	@RequiresPermissions(value = {"删除apachetype"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		// 查看是不是能够删除
		Integer count = apacheOSService.findByTypeId(id);
		Map<String, Object> result = new HashMap<String, Object>();
		if(count > 0) {
			result.put("success", false);
		} else {
			apacheOSTypeService.delete(id);
			result.put("success", true);
		}
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"通过id获取apacheType"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		ApacheOSType apacheOSType = new ApacheOSType();
		apacheOSType.setId(id);
		apacheOSType = apacheOSTypeService.getById(apacheOSType);
		
		result.put("apacheOSType", apacheOSType);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改apacheType"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(ApacheOSType apacheOSType) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(apacheOSType != null && apacheOSType.getId() != 0) {
			apacheOSTypeService.update(apacheOSType);
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"添加apacheType"})
	@ResponseBody
	@RequestMapping("/addApacheOSType")
	public Map<String, Object> addApacheOSType(ApacheOSType apacheOSType) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		apacheOSTypeService.addApacheOSType(apacheOSType);
		result.put("success", true);
		start.initData();
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
