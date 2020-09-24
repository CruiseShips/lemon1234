package com.lemon1234.mapper;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Member;

public interface MemberMapper {

	List<Member> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void update(Member member);
	
	void addMember(Member member);
}
