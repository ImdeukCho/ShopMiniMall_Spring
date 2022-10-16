package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/memberAdd")
	public String memberAdd(MemberDTO m, Model model) {
//		System.out.println(m);
//		System.out.println(model);
		model.addAttribute("success", m.getUserid()+"님 회원가입성공");
		service.memberAdd(m);
		return "main";	// main.jsp
	}


	
	@RequestMapping(value = "/idDuplicateCheck" , produces = "text/plain;charset=UTF-8")	// 한글처리
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam("id") String userid) {
//		System.out.println(userid);
		MemberDTO dto = service.mypage(userid);
//		System.out.println("idDuplicatedCheck : " +dto);
		String mesg="아이디 사용가능";
		if (dto != null) {
			mesg="아이디 중복"; 
		}
		return mesg;
	}
	
	@RequestMapping(value = "/loginCheck/memberUpdate")
	public String memberUpdate(MemberDTO m, HttpSession session) {
//		System.out.println(m);
		service.memberUpdate(m);
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		dto = service.mypage(userid);
		session.setAttribute("login", dto);	// 새 정보로 덮어쓰기
		return "redirect:../loginCheck/mypage";	// 다시 요청
	}
	
	@RequestMapping(value = "/loginCheck/mypage")
	public String myPage(HttpSession session) {
		// interceptor 인증 후
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();	// 세션에서 id얻기
		dto = service.mypage(userid);
//		System.out.println("mypage : " + dto);
		
		session.setAttribute("login", dto);
//		return "mypage";				// 방법1. 						servlet-context에서 주소설정
		return "redirect:../mypage";	// 방법2. 주의) redirect 시 ../  	servlet-context에서 주소설정
	}
}
