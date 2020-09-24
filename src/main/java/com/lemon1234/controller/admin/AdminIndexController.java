package com.lemon1234.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AdminIndexController {

	@RequestMapping("/login")
	public ModelAndView loginForward() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
}
