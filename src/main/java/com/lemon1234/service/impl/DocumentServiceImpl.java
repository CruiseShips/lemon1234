package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Document;
import com.lemon1234.mapper.DocumentMapper;
import com.lemon1234.service.DocumentService;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentMapper documentMapper;

	@Override
	public List<Document> getlist(Map<String, Object> param) {
		return documentMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return documentMapper.getCount(param);
	}

	@Override
	public Document getById(Integer id) {
		return documentMapper.getById(id);
	}

	@Override
	public void update(Document document) {
		documentMapper.update(document);
	}

	@Override
	public void addDocument(Document document) {
		documentMapper.addDocument(document);
	}

	@Override
	public void delete(Integer id) {
		documentMapper.delete(id);
	}

}
