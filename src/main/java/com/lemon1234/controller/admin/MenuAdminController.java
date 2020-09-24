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

import com.lemon1234.entity.Menu;
import com.lemon1234.service.MenuService;
import com.lemon1234.start.Lemon1234ServerStart;

@Controller
@RequestMapping("/admin/menu")
public class MenuAdminController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private Lemon1234ServerStart start;
	
	@RequiresPermissions(value = {"查看所有菜单"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getMenus(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Menu> menuList = menuService.getlist(param);
		Integer count = menuService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", menuList);
		return result;
	}
	
	@RequiresPermissions(value = {"添加菜单"})
	@ResponseBody
	@RequestMapping("/addMenu")
	public Map<String, Object> addMenu(Menu menu) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(menu != null) {
			menuService.addMenu(menu);
			result.put("success", true);
			start.initData();
		} else {
			result.put("success", false);
			result.put("errorInfo", "错误的数据");
		}
		return result;
	}
	
	@RequiresPermissions(value = {"修改菜单"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Menu menu) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(menu != null && menu.getId() != 0) {
			menuService.update(menu);
			start.initData();
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"删除菜单"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		menuService.delete(id);
		start.initData();
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"获取菜单"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Menu menu = menuService.getdetail(id);
		
		result.put("menu", menu);
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
