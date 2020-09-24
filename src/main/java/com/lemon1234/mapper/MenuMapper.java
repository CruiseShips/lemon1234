package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Menu;

public interface MenuMapper {

	List<Menu> getlist(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void addMenu(Menu menu);
	
	void update(Menu menu);
	
	void delete(Integer id);
	
	Menu getdetail(Integer id);
}
