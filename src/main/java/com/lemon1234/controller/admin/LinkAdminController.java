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

import com.lemon1234.entity.Link;
import com.lemon1234.service.LinkService;
import com.lemon1234.start.Lemon1234ServerStart;

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {
	
	@Autowired
	private LinkService linkService;
	@Autowired
	private Lemon1234ServerStart start;

	@RequiresPermissions(value = {"查看所有链接"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Link> linkList = linkService.getlist(param);
		Integer count = linkService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", linkList);
		return result;
	}
	
	@RequiresPermissions(value = {"查看链接"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(id != 0) {
			Link link = linkService.getdetail(id);
			result.put("success", true);
			result.put("link", link);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"增加链接"})
	@ResponseBody
	@RequestMapping("/addLink")
	public Map<String, Object> addLink(Link link) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		linkService.addLink(link);
		start.initData();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"更新链接"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Link link) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(link != null && link.getId() != 0) {
			linkService.update(link);
			result.put("success", true);
			start.initData();
		} else {
			result.put("success", false);
			result.put("errorInfo", "服务器异常，请从新修改");
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"删除链接"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		linkService.delete(id);
		result.put("success", true);
		start.initData();
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
