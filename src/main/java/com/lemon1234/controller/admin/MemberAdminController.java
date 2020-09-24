package com.lemon1234.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon1234.entity.Member;
import com.lemon1234.service.MemberService;

@Controller
@RequestMapping("/admin/member")
public class MemberAdminController {

	@Autowired
	private MemberService memberService;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder bin){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor cust = new CustomDateEditor(sdf, true);
		bin.registerCustomEditor(Date.class,cust);
	}
	
	@RequiresPermissions(value = {"查看所有会员"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Member> members = memberService.list(param);
		Integer count = memberService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", members);
		return result;
	}
	
	@RequiresPermissions(value = {"查看所有会员"})
	@ResponseBody
	@RequestMapping("/memberList")
	public Map<String, Object> memberList() throws Exception {
		List<Member> members = memberService.list(null);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", members);
		result.put("code", 0);
		return result;
	}
	
	@RequiresPermissions(value = {"修改会员"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Member member) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(member == null || member.getId() == 0) {
			result.put("code", -1);
			return result;
		}
		
		if("".equals(member.getQqNum())) {
			member.setQqNum(null);
		}
		if("".equals(member.getRealName())) {
			member.setRealName(null);
		}
		if("".equals(member.getWxNum())) {
			member.setWxNum(null);
		}
		memberService.update(member);
		result.put("code", 0);
		return result;
	}
	
	@RequiresPermissions(value = {"添加会员"})
	@ResponseBody
	@RequestMapping("/addMember")
	public Map<String, Object> addMember(Member member) throws Exception {
		memberService.addMember(member);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
