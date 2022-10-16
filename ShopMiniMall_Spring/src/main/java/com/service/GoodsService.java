package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Service
public class GoodsService {

	@Autowired
	GoodsDAO dao;

	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = dao.goodsList(gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto = dao.goodsRetrieve(gCode);
		return dto;
	}

	public int cartAdd(CartDTO cart) {
		int n = dao.cartAdd(cart);
		return n;
	}

	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = dao.cartList(userid);
		return list;
	}

	public void cartUpdate(Map<String, String> map) {
		dao.cartUpdate(map);
	}

	public void cartDelete(int num) {
		dao.cartDelete(num);
	}

	public void delAllCart(ArrayList<String> list) {
		dao.delAllCart(list);		
	}

	public CartDTO orderConfirm(int num) {
		CartDTO dto = dao.orderConfirm(num);	
		return dto;
	}
	@Transactional	// tx처리 , root-context.xml에  tx-Manager를 등록 필요
	public void orderDone(OrderDTO oDTO, int orderNum) {
		dao.orderDone(oDTO);	// 주문정보 저장 insert
		dao.cartDelete(orderNum); // 카트에서 삭제 후 처리를 tx처리함
		
	}

	


	
}
