package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Announcement;

public interface AnnouncementMapper {

	List<Announcement> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);

	Announcement getbyId(Integer id);
	
	void delete(Integer id);
	
	void update(Announcement announcement);
	
	void addAnnouncement(Announcement announcement);
}
