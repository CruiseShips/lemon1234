package com.lemon1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon1234.entity.Member;
import com.lemon1234.mapper.MemberMapper;
import com.lemon1234.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<Member> list(Map<String, Object> param) {
		return memberMapper.list(param);
	}

	@Override
	public Integer getCount(Map<String, Object> param) {
		return memberMapper.getCount(param);
	}

	@Override
	public void update(Member member) {
		memberMapper.update(member);		
	}

	@Override
	public void addMember(Member member) {
		memberMapper.addMember(member);		
	}

}
