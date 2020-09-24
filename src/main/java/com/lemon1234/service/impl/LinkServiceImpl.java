package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Link;
import com.lemon1234.mapper.LinkMapper;
import com.lemon1234.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkMapper linkmapper;

	@Override
	public List<Link> getlist(Map<String, Object> param) {
		return linkmapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return linkmapper.getCount(param);
	}

	@Override
	public Link getdetail(Integer id) {
		return linkmapper.getdetail(id);
	}

	@Override
	public void addLink(Link link) {
		linkmapper.addLink(link);		
	}

	@Override
	public void update(Link link) {
		linkmapper.update(link);
	}

	@Override
	public void delete(Integer id) {
		linkmapper.delete(id);
	}

}
