package com.lemon1234.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Announcement;
import com.lemon1234.service.AnnouncementService;

@Controller
@RequestMapping("/anno")
public class AnnouncementController {
	
	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping("/{id}")
	public ModelAndView getbyId(@PathVariable(value="id",required=false) int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Announcement anno = announcementService.getbyId(id);
		if(anno == null) {
			// 去 404
			mav.addObject("url", "common/404");
			mav.addObject("port", "#error404");
			mav.addObject("title", "404 - Lemon1234");
		} else {
			// 去正确的页面
			// ${#dates.format(billingForm.startTime,'yyyy-MM-dd HH:mm:ss')}
			mav.addObject("anno", anno);
			mav.addObject("url", "body/other/anno");
			mav.addObject("port", "#anno");
			mav.addObject("title", anno.getTitle() + " - Lemon1234");
		}
		mav.setViewName("index");
		
		return mav;
	}
}
