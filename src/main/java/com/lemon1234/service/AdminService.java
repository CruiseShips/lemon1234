package com.lemon1234.service;

import com.lemon1234.entity.Admin;

public interface AdminService {

	Admin findByUserName(String userName);
	
	void update(Admin admin);
}
