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

import com.lemon1234.entity.OpenOSType;
import com.lemon1234.service.OpenOSService;
import com.lemon1234.service.OpenOSTypeService;
import com.lemon1234.start.Lemon1234ServerStart;

@Controller
@RequestMapping("/admin/openOSType")
public class OpenOSTypeAdminController {

	@Autowired
	private OpenOSTypeService openOSTypeService;
	@Autowired
	private OpenOSService openOSService;
	@Autowired
	private Lemon1234ServerStart start;
	
	@RequiresPermissions(value={"查看所有opentype"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getlist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if(page != null && limit != null) {
			param.put("page", this.getPageStart(page, limit));
			param.put("limit", limit);	
		}
		
		List<OpenOSType> types = openOSTypeService.getlist(param);
		int count = openOSTypeService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", types);
		return result;
	}
	
	@RequiresPermissions(value = {"删除opentype"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		// 查看是不是能够删除
		Integer count = openOSService.findByTypeId(id);
		Map<String, Object> result = new HashMap<String, Object>();
		if(count > 0) {
			result.put("success", false);
		} else {
			openOSTypeService.delete(id);
			result.put("success", true);
		}
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"通过id获取openType"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		OpenOSType openOSType = new OpenOSType();
		openOSType.setId(id);
		openOSType = openOSTypeService.getById(openOSType);
		
		result.put("openOSType", openOSType);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改openType"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(OpenOSType openOSType) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(openOSType != null && openOSType.getId() != 0) {
			openOSTypeService.update(openOSType);
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"添加openType"})
	@ResponseBody
	@RequestMapping("/addOpenOSType")
	public Map<String, Object> addOpenOSType(OpenOSType openOSType) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		openOSTypeService.addOpenOSType(openOSType);
		result.put("success", true);
		start.initData();
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
