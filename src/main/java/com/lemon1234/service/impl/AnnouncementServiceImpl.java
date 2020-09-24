package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Announcement;
import com.lemon1234.mapper.AnnouncementMapper;
import com.lemon1234.service.AnnouncementService;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Override
	public List<Announcement> getlist(Map<String, Object> param) {
		return announcementMapper.getlist(param);
	}

	@Override
	public Announcement getbyId(Integer id) {
		return announcementMapper.getbyId(id);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return announcementMapper.getCount(param);
	}

	@Override
	public void delete(Integer id) {
		announcementMapper.delete(id);		
	}

	@Override
	public void update(Announcement announcement) {
		announcementMapper.update(announcement);		
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		announcementMapper.addAnnouncement(announcement);		
	}

}
