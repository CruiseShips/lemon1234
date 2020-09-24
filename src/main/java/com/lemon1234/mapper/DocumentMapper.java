package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Document;

public interface DocumentMapper {

	List<Document> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	Document getById(Integer id);
	
	void update(Document document);
	
	void addDocument(Document document);
	
	void delete(Integer id);
}
