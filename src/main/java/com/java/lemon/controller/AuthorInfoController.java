package com.java.lemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authorInfo")
public class AuthorInfoController {

	/**
	 * 返回静态页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAuthorInfo")
	public ModelAndView getAuthorInfo() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "body/authorInfo");
		mav.addObject("port", "#authorInfo");
		mav.addObject("title", "Lemon1234");
		mav.setViewName("index");
		return mav;
	}
}
