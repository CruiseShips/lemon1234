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

import com.lemon1234.entity.Register;
import com.lemon1234.service.RegisterService;

@Controller
@RequestMapping("/admin/register")
public class RegisterAdminController {

	@Autowired
	private RegisterService registerService;
	
	@RequiresPermissions(value={"查看所有失效连接"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getlist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit
									  ) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		
		List<Register> registerList = registerService.getlist(param);
		Integer count = registerService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", registerList);
		return result;
	}
	
	@RequiresPermissions(value={"删除失效连接"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		registerService.delete(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"修改失效连接状态"})
	@ResponseBody
	@RequestMapping("/changeStatus")
	public Map<String, Object> changeStatus(Register register) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		registerService.update(register);
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
