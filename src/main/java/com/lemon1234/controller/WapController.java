package com.lemon1234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 手机端请求转发
 * 
 * @date 2021年1月16日
 * @author lemon1234.zhihua
 */
@Controller
@RequestMapping("/wap")
public class WapController {

	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wap/index");
		mav.addObject("title", "Lemon1234");
		return mav;
	}
}
