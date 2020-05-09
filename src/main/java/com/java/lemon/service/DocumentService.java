package com.java.lemon.service;

import java.util.List;

import com.java.lemon.model.entity.Document;

public interface DocumentService {

	List<Document> getAllDocument();
	
	Document getDocument(Integer id);
	
	List<Document> getTop9Document();
}
