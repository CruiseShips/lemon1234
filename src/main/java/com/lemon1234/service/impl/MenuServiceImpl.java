package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Menu;
import com.lemon1234.mapper.MenuMapper;
import com.lemon1234.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getlist(Map<String, Object> param) {
		return menuMapper.getlist(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return menuMapper.getCount(param);
	}

	@Override
	public void addMenu(Menu menu) {
		menuMapper.addMenu(menu);
	}

	@Override
	public void update(Menu menu) {
		menuMapper.update(menu);
	}

	@Override
	public void delete(Integer id) {
		menuMapper.delete(id);
	}

	@Override
	public Menu getdetail(Integer id) {
		return menuMapper.getdetail(id);
	}

}
