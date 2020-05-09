package com.java.lemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.lemon.model.dto.DocumentDTO;
import com.java.lemon.model.entity.Document;
import com.java.lemon.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/getAllDocument")
	public ModelAndView getAllDocument() throws Exception {
		List<Document> documentList = documentService.getAllDocument();
		
		List<DocumentDTO> documentDTOs= new ArrayList<DocumentDTO>();
		
		for(Document document : documentList) {
			DocumentDTO dto = new DocumentDTO();
			dto.setId(document.getId());
			dto.setName(document.getName());
			dto.setSummary(document.getSummary());
			dto.setImg(document.getImg());
			
			documentDTOs.add(dto);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("documentList", documentDTOs);
		mav.addObject("url", "body/getAllDocument");
		mav.addObject("port", "#getAllDocument");
		mav.addObject("title", "Lemon1234 - 技术干货");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/getDocument/{id}")
	public ModelAndView getDocument(@PathVariable(value = "id", required = false)Integer id) throws Exception {
		Document document = documentService.getDocument(id);
		ModelAndView mav = new ModelAndView();
		
		if(document == null) {
			mav.addObject("url", "body/empty");
			mav.addObject("port", "#empty");
			mav.addObject("title", "Lemon1234 - 页面不存在");
			mav.setViewName("index");
		} else {
			mav.addObject("document", document);
			mav.addObject("url", "body/getDocument");
			mav.addObject("port", "#getDocument");
			mav.addObject("title", "Lemon1234 - " + document.getName() + " 介绍");
			mav.setViewName("index");
		}
		return mav;
	}
}
