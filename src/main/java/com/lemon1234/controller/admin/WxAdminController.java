package com.lemon1234.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon1234.entity.Grit;
import com.lemon1234.entity.Shout;
import com.lemon1234.entity.WxUserInfo;
import com.lemon1234.service.GritService;
import com.lemon1234.service.ShoutService;
import com.lemon1234.service.WxUserInfoService;

@Controller
@RequestMapping("/admin/wx")
public class WxAdminController {

	@Autowired
	private WxUserInfoService wxUserInfoService;
	@Autowired
	private ShoutService shoutService;
	@Autowired
	private GritService gritService;
	
	@RequiresPermissions(value = {"查看所有小程序用户"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<WxUserInfo> wxUserInfos = wxUserInfoService.list(param);
		Integer count = wxUserInfoService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", wxUserInfos);
		return result;
	}
	
	@RequiresPermissions(value = {"查看所有小程序qa"})
	@ResponseBody
	@RequestMapping("/qlist")
	public Map<String, Object> qlist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Shout> shouts = shoutService.list(param);
		Integer count = shoutService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", shouts);
		return result;
	}
	
	@RequiresPermissions(value = {"查看所有小程序qa"})
	@ResponseBody
	@RequestMapping("/alist")
	public Map<String, Object> alist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Grit> grits = gritService.list(param);
		Integer count = gritService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", grits);
		return result;
	}
	
	@RequiresPermissions(value = {"修改Grit状态"})
	@ResponseBody
	@RequestMapping("/changeStatus")
	public Map<String, Object> changeStatus(@RequestParam(value = "id", required = false)Integer id, @RequestParam(value = "status", required = false)Integer status) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Grit grit = new Grit();
		grit.setId(id);
		grit.setStatus(status);
		gritService.update(grit);
		
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改Grit名称"})
	@ResponseBody
	@RequestMapping("/changeName")
	public Map<String, Object> changeName(@RequestParam(value = "id", required = false)Integer id, @RequestParam(value = "text", required = false)String text) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Grit grit = new Grit();
		grit.setId(id);
		grit.setText(text);
		gritService.update(grit);
		
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"删除Grit"})
	@ResponseBody
	@RequestMapping("/deletegrit")
	public Map<String, Object> deletegrit(@RequestParam(value = "id", required = false)Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		gritService.delete(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"添加Grit"})
	@ResponseBody
	@RequestMapping("/addGrit")
	public Map<String, Object> addGrit(@RequestParam(value = "text", required = false)String text) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Grit grit = new Grit();
		grit.setText(text);
		grit.setOpenId("admin");
		grit.setStatus(Grit.PASS);
		
		gritService.addGrit(grit);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"获取男女比例"})
	@ResponseBody
	@RequestMapping("/proportion")
	public Map<String, Object> proportion() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> proportion = wxUserInfoService.proportion();
		
		List<Object> sex = new ArrayList<Object>();
		List<Object> count = new ArrayList<Object>();
		
		if(proportion != null && proportion.size() > 0) {
			// gender count
			Set<String> keys = proportion.get(0).keySet();
			for(Map<String, Object> map : proportion) {
				for(String key : keys) {
					Object num = map.get(key);
					if("gender".equals(key)) {
						// 性别 0：未知、1：男、2：女
						sex.add(num);
					}
					if("count".equals(key)) {
						count.add(num);
					}
					map.get(key);
				}
			}
		}
		
		result.put("sex", sex);
		result.put("count", count);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"按照每天获取前5的记录"})
	@ResponseBody
	@RequestMapping("/getTop5")
	public Map<String, Object> getTop5() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> proportion = shoutService.proportion();
		
		List<Object> dt = new ArrayList<Object>();
		List<Object> count = new ArrayList<Object>();
		
		if(proportion != null && proportion.size() > 0) {
			//count dt
			Set<String> keys = proportion.get(0).keySet();
			for(Map<String, Object> map : proportion) {
				for(String key : keys) {
					Object num = map.get(key);
					if("dt".equals(key)) {
						dt.add(num);
					}
					if("count".equals(key)) {
						count.add(num);
					}
					map.get(key);
				}
			}
		}
		
		result.put("dt", dt);
		result.put("count", count);
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
