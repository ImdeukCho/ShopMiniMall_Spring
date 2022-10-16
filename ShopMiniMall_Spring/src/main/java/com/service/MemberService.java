package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	public void memberAdd(MemberDTO m) {
//		System.out.println(dao);
		dao.memberAdd(m);		
	}

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = dao.login(map);
		return dto;
	}

	public MemberDTO mypage(String userid) {
		MemberDTO dto = dao.mypage(userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) {
		dao.memberUpdate(m);
	}


}
