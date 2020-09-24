package com.lemon1234.mapper;

import com.lemon1234.entity.Admin;

public interface AdminMapper {

	Admin findByUserName(String userName);
	
	void update(Admin admin);
}
