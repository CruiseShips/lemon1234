package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Message;

public interface MessageService {

	List<Message> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void addMessage(Message message);
	
	void delete(Integer id);
	
	Message getdetail(Integer id);
	
	void update(Message message);
}
