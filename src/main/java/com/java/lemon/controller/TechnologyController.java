package com.java.lemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.lemon.model.dto.TechnologyDTO;
import com.java.lemon.model.entity.Technology;
import com.java.lemon.service.TechnologyService;

@Controller
@RequestMapping("/technology")
public class TechnologyController {

	@Autowired
	private TechnologyService technologyService;
	
	@RequestMapping("/getAllTechno")
	public ModelAndView getAllTechnology() throws Exception {
		ModelAndView mav = new ModelAndView();
		List<Technology> technologyList = technologyService.getAllTechnology();
		
		List<TechnologyDTO> technologyDTOs = new ArrayList<TechnologyDTO>();
		for(Technology technology : technologyList) {
			TechnologyDTO dto = new TechnologyDTO();
			dto.setId(technology.getId());
			dto.setSummary(technology.getSummary());
			dto.setName(technology.getName());
			dto.setLogo(technology.getLogo());
			dto.setCodingPosition(technology.getCodingPosition());
			dto.setLevel(technology.getLevel());
			dto.setVersion(technology.getVersion());
			
			technologyDTOs.add(dto);
		}
		
		mav.addObject("technologyDTOs", technologyDTOs);
		
		mav.addObject("url", "body/getAllTechno");
		mav.addObject("port", "#getAllTechno");
		mav.addObject("title", "Lemon1234 - 技术干货");
		mav.setViewName("index");
		return mav;
	}
}
