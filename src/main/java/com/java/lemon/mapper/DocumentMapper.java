package com.java.lemon.mapper;

import java.util.List;

import com.java.lemon.model.entity.Document;

public interface DocumentMapper {

	List<Document> getAllDocument();
	
	Document getDocument(Integer id);
	
	List<Document> getTop9Document();
}
