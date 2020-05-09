package com.java.lemon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.lemon.mapper.DocumentMapper;
import com.java.lemon.model.entity.Document;
import com.java.lemon.service.DocumentService;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentMapper documentMapper;

	@Override
	public List<Document> getAllDocument() {
		return documentMapper.getAllDocument();
	}

	@Override
	public Document getDocument(Integer id) {
		return documentMapper.getDocument(id);
	}

	@Override
	public List<Document> getTop9Document() {
		return documentMapper.getTop9Document();
	}

}
