package com.lemon1234.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Admin;
import com.lemon1234.mapper.AdminMapper;
import com.lemon1234.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin findByUserName(String userName) {
		return adminMapper.findByUserName(userName);
	}

	@Override
	public void update(Admin admin) {
		adminMapper.update(admin);		
	}

}
