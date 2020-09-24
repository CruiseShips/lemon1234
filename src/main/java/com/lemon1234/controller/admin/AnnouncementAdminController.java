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

import com.lemon1234.entity.Announcement;
import com.lemon1234.service.AnnouncementService;

@Controller
@RequestMapping("/admin/anno")
public class AnnouncementAdminController {

	@Autowired
	private AnnouncementService announcementService;
	
	@RequiresPermissions(value = {"查看所有公告"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
							@RequestParam(value = "page", required = false)Integer page,
							@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Announcement> annoList = announcementService.getlist(param);
		Integer count = announcementService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", annoList);
		return result;
	}
	
	@RequiresPermissions(value = {"删除公告"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		announcementService.delete(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"添加公告"})
	@ResponseBody
	@RequestMapping("/addAnno")
	public Map<String, Object> addAnno(Announcement announcement) throws Exception {
		announcementService.addAnnouncement(announcement);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"查看公告"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Announcement announcement = announcementService.getbyId(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("anno", announcement);
		return result;
	}
	
	@RequiresPermissions(value = {"更新公告"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Announcement announcement) throws Exception {
		announcementService.update(announcement);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
