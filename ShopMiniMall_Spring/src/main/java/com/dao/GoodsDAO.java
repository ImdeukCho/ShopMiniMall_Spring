package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Repository
public class GoodsDAO {

	@Autowired
	SqlSessionTemplate template;	// 자동주입
	
	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = template.selectList("GoodsMapper.goodsList", gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto = template.selectOne("GoodsMapper.goodsRetrieve", gCode);
		return dto;
	}

	public int cartAdd(CartDTO cart) {
		int n = template.insert("CartMapper.cartAdd", cart);
		return n;
	}

	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = template.selectList("CartMapper.cartList", userid);
		return list;
	}

	public void cartUpdate(Map<String, String> map) {
		int n = template.update("CartMapper.cartUpdate", map);
		System.out.println("수정된 장바구니 갯수 : "+n);
	}

	public void cartDelete(int num) {
		int n = template.delete("CartMapper.cartDel", num);
		System.out.println("삭제된 장바구니 갯수 : "+n);
	}

	public void delAllCart(ArrayList<String> list) {
		int n = template.delete("CartMapper.cartAllDel", list);
		System.out.println("장바구니 전체삭제 갯수 : "+n);
	}

	public CartDTO orderConfirm(int num) {
		CartDTO dto = template.selectOne("CartMapper.cartbyNum", num);
		return dto;
	}

	public void orderDone(OrderDTO oDTO) {
		int n = template.insert("CartMapper.orderDone", oDTO);
		System.out.println("order insert 갯수 : "+n);
	}
}
