package com.lemon1234.service;

import java.util.List;
import java.util.Map;

import com.lemon1234.entity.Member;

public interface MemberService {

	List<Member> list(Map<String, Object> param);
	
	Integer getCount(Map<String, Object> param);
	
	void update(Member member);
	
	void addMember(Member member);
}
