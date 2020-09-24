package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Link;

public interface LinkService {

	List<Link> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	Link getdetail(Integer id);
	
	void addLink(Link link);
	
	void update(Link link);
	
	void delete(Integer id);
}
