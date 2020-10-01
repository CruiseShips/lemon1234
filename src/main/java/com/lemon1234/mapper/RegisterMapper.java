package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Register;

public interface RegisterMapper {

	List<Register> getlist(Map<String, Object> param);
	
	Integer addRegister(Register register);
	
	Integer getCount(Map<String, Object> param);
	
	void delete(Integer id);
	
	void update(Register register);
}
