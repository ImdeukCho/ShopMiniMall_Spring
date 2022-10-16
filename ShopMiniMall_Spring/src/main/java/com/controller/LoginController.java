package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService service;

// 로그인 처리
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		MemberDTO dto = service.login(map);
		System.out.println(dto);		
		if (dto != null) {
			session.setAttribute("login", dto);
			return "redirect:/goodsList?gCategory=top";	// 로그인시 top카테고리를 보이도록 설정			
		} else {
			model.addAttribute("mesg", "아이디 또는 비번이 잘못되었습니다.");
			return "loginForm";
		}
		
	}
	
// 로그아웃 처리	
	@RequestMapping(value = "/loginCheck/Logout")
	private String logout(HttpSession session) {
		System.out.println("로그아웃 됨");
		session.invalidate();
//		return "main";			// 방법1. WEB-INF/views/main.jsp
		return "redirect:../";	// 방법2. 주의) redirect 시 ../ 
		//.xml에서 설정 main.jsp ../을 이용하여 /loginCheck의 상위 주소로 이동
	}
}
