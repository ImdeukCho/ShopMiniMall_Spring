package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {
	
	@Autowired
	GoodsService service;
	@Autowired
	MemberService mService;

	@RequestMapping(value = "/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) {	// 카테고리별 상품목록보기 단 로그인 된 경우만
//		System.out.println(gCategory);		
		if (gCategory == null) {
			gCategory = "top";
		}
		List<GoodsDTO> list = service.goodsList(gCategory);
//		System.out.println("goodsList : " + list);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		// request.setAttribute("goodsList", list); 와 동일
		mav.setViewName("main");	// main.jsp => goodsList.jsp에서 목록을 뿌려줌
		return mav;
	}
	  
	
	@RequestMapping(value = "/goodsRetrieve")	// goodsRetrieve.jsp , view에 대한 지정이 없음 url = 뷰페이지
	@ModelAttribute("goodsRetrieve")	// key값 goodsRetrieve 설정
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) {
//		System.out.println(gCode);
		GoodsDTO dto = service.goodsRetrieve(gCode);
//		System.out.println("goodsRetrieve : " + dto);
		return dto;
	}
	
	
	@RequestMapping(value = "/loginCheck/cartAdd")
	public String goodRetrieveForm(CartDTO cart, HttpSession session) {
		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
//		System.out.println("회원정보 : " + mdto);
		cart.setUserid(mdto.getUserid());
//		System.out.println("cart : " + cart);
		int n = service.cartAdd(cart);
//		System.out.println("insert 갯수 : " + n);
		session.setAttribute("mesg", cart.getgCode());
		return "redirect:../goodsRetrieve?gCode="+cart.getgCode();
	}

// RedirectAttributes : 리다이렉트 사용해도 데이터 유지
	@RequestMapping(value = "/loginCheck/CartList")
	public String CartList(HttpSession session, RedirectAttributes attr) {
		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
		String userid = mdto.getUserid();
		List<CartDTO> list = service.cartList(userid);

		attr.addFlashAttribute("cartList",list);	// 리다이렉트시 데이터 유지
//			return "cartList";
		return "redirect:../cartList";	// 재요청해도 데이터 유지.  test함수 사용안하고 servlet-context에 등록해도 됨
	}
		
//	@RequestMapping(value = "/cartList")
//	public String test() {
//		System.out.println("/cartList 함수 실행 ===");
//		return "cartList";
//	}
	
	
//// Model 사용	
//	@RequestMapping(value = "/loginCheck/CartList")
//	public String CartList(HttpSession session, Model m) {
//		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
//		String userid = mdto.getUserid();
//		List<CartDTO> list = service.cartList(userid);
//
//		m.addAttribute("cartList",list);
//		return "cartList";
////		return "redirect:../cartList";	// 재요청하므로 request 데이터 삭제됨.
//	}
	
//// ModelAndView 사용		
//	@RequestMapping(value = "/loginCheck/CartList")
//	public ModelAndView CartList(HttpSession session) {
//		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
//		String userid = mdto.getUserid();
//		List<CartDTO> list = service.cartList(userid);
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("cartList",list);
//		mav.setViewName("cartList");
//		return mav;
//	}
	
//// request 사용	
//	@RequestMapping(value = "/loginCheck/CartList")
//	public String CartList(HttpSession session, HttpServletRequest request) {
//		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
//		String userid = mdto.getUserid();
//		List<CartDTO> list = service.cartList(userid);
//		
//		request.setAttribute("cartList", list);
////		return "cartList";
//		return "redirect:../cartList";	// 재요청하므로 request 데이터 삭제됨.
//	}	

	// ajax 비동기 사용
	@RequestMapping(value = "/loginCheck/cartUpdate", method = RequestMethod.GET)
	@ResponseBody	// 비동기처리 : view에서 메서드를 호출한 곳으로 데이터를 리턴함
	public void cartUpdate(@RequestParam Map<String, String> map) {
		System.out.println("업데이트 할 주문 : "+map); 	
		service.cartUpdate(map);
	}

	// ajax 비동기 사용
	@RequestMapping(value = "/loginCheck/cartDelete", method = RequestMethod.GET)
	@ResponseBody
	public void cartDelete(@RequestParam("num") int num) {
		System.out.println("삭제할 주문번호 : "+num); 	
		service.cartDelete(num);
	}

// 장바구니 전체삭제
	// 1.request로 받기
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	public String delAllCart(HttpServletRequest request) {
//		String[] nums = request.getParameterValues("check");	// 주의) Values
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//		return "redirect:../cartList";
//	}
	// 2.@값 받아서 배열로 저장
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	public String delAllCart(@RequestParam("check") String[] nums) {
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//		return "redirect:../cartList";
//	}
	// 3.배열로 받기
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	public String delAllCart(String[] check) {
//		for (int i = 0; i < check.length; i++) {
//			System.out.println(check[i]);
//		}
//		return "redirect:../cartList";
//	}
	// 4.@값 받아서 list로 저장
	@RequestMapping(value = "/loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check") ArrayList<String> list) {
		// System.out.println("전체삭제 리스트 : "+list);
		service.delAllCart(list);
		return "redirect:../cartList";
	} 
	
	// 주문하기
	@RequestMapping(value = "/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") int num, HttpSession session, RedirectAttributes attr) {
		// System.out.println("주문번호 : "+num);
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		String userid = mDTO.getUserid();
		mDTO=mService.mypage(userid);	// 사용자 정보 갱신
		CartDTO cDTO = service.orderConfirm(num);
		// System.out.println("mDTO : "+mDTO);
		// System.out.println("cDTO : "+cDTO);
		
		attr.addFlashAttribute("mDTO",mDTO);
		attr.addFlashAttribute("cDTO",cDTO);
		return "redirect:../orderConfirm";	//servlet-context에 등록
	}
	
	
	
	@RequestMapping(value = "/loginCheck/orderDone")
	public String orderDone(OrderDTO oDTO, int orderNum, HttpSession session, RedirectAttributes attr) {
//		System.out.println("OrderDTO : "+oDTO);		
//		System.out.println("orderNum : "+orderNum);
		
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		oDTO.setUserid(mDTO.getUserid());
		service.orderDone(oDTO, orderNum);	// insert, delete
		 
		attr.addFlashAttribute("oDTO",oDTO);
		return "redirect:../orderDone";	// servlet-context에 주소 등록
	}
	
}
