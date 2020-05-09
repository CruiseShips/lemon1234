package com.java.lemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.lemon.model.dto.StudyRoadMapDTO;
import com.java.lemon.model.entity.StudyRoadMap;
import com.java.lemon.model.entity.Technology;
import com.java.lemon.service.StudyRoadMapService;
import com.java.lemon.service.TechnologyService;

@Controller
@RequestMapping("/studyRoad")
public class StudyRoadMapController {

	@Autowired
	private StudyRoadMapService studyRoadMapService;
	
	@Autowired
	private TechnologyService technologyService;
	
	@RequestMapping("/getAllStudyRoad")
	public ModelAndView getAllStudyRoadMap() throws Exception {
		List<StudyRoadMap> studyRoadMaps = studyRoadMapService.getAllStudyRoadMap();
		
		List<StudyRoadMapDTO> studyRoadMapDTOs = new ArrayList<StudyRoadMapDTO>();
		for(StudyRoadMap studyRoadMap : studyRoadMaps) {
			StudyRoadMapDTO dto = new StudyRoadMapDTO();
			dto.setAims(studyRoadMap.getAims());
			dto.setName(studyRoadMap.getName());
			
			List<Technology> technologyDTOs = technologyService.getStudyRoadTechnology(studyRoadMap.getId());
			List<StudyRoadMapDTO.TechnologyDTO> studyTechnologyDTOs = new ArrayList<StudyRoadMapDTO.TechnologyDTO>();
			for(Technology technology : technologyDTOs) {
				StudyRoadMapDTO.TechnologyDTO technologyDTO = new StudyRoadMapDTO.TechnologyDTO();
				technologyDTO.setId(technology.getId());
				technologyDTO.setName(technology.getName());
				technologyDTO.setCsdnUrl(technology.getCsdnUrl());
				technologyDTO.setVideoUrl(technology.getVideoUrl());
				technologyDTO.setShareName(technology.getShareName());
				
				studyTechnologyDTOs.add(technologyDTO);
			}
			dto.setTechnologyDTOs(studyTechnologyDTOs);
			
			studyRoadMapDTOs.add(dto);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("studyRoadMapDTOs", studyRoadMapDTOs);
		
		mav.addObject("url", "body/getStudyRoad");
		mav.addObject("port", "#getStudyRoad");
		mav.addObject("title", "Lemon1234 - 学习路线图");
		mav.setViewName("index");
		return mav;
	}
}
