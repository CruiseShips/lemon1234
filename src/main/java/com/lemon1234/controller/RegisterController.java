package com.lemon1234.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon1234.entity.Register;
import com.lemon1234.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("addRegister")
	@ResponseBody
	public Map<String, Object> addRegister(Register register) throws Exception {
		register.setType(Register.DOCUMENT);
		register.setStatus(Register.NOTDO);
		Integer count = registerService.addRegister(register);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(count > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorInfo", "系统错误，请稍后提交");
		}
		return map;
	}
}
