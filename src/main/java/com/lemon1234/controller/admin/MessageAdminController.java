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

import com.lemon1234.entity.Message;
import com.lemon1234.service.MessageService;

@Controller
@RequestMapping("/admin/message")
public class MessageAdminController {

	@Autowired
	private MessageService messageService;
	
	@RequiresPermissions(value = {"查看所有留言"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getMenus(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Message> menuList = messageService.getlist(param);
		Integer count = messageService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", menuList);
		return result;
	}
	
	@RequiresPermissions(value = {"删除留言"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		messageService.delete(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"获取留言"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Message msg = messageService.getdetail(id);
		
		result.put("msg", msg);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改留言"})
	@ResponseBody
	@RequestMapping("/updateMsg")
	public Map<String, Object> update(Message mgs) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(mgs != null && mgs.getId() != 0) {
			messageService.update(mgs);
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
