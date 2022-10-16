package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.GoodsDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class MainController {

	@Autowired
	GoodsService service;
	
	@RequestMapping(value = "/")
	public ModelAndView goodsList() {
		List<GoodsDTO> list = service.goodsList("top");
		ModelAndView mav = new ModelAndView();	
		mav.addObject("goodsList", list);
		mav.setViewName("main");
		return mav;
	}
}
