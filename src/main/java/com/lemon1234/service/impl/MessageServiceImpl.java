package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Message;
import com.lemon1234.mapper.MessageMapper;
import com.lemon1234.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<Message> getlist(Map<String, Object> param) {
		return messageMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return messageMapper.getCount(param);
	}

	@Override
	public void addMessage(Message message) {
		messageMapper.addMessage(message);		
	}

	@Override
	public void delete(Integer id) {
		messageMapper.delete(id);		
	}

	@Override
	public Message getdetail(Integer id) {
		return messageMapper.getdetail(id);
	}

	@Override
	public void update(Message message) {
		messageMapper.update(message);		
	}

}
