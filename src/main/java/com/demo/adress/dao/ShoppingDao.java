package com.demo.adress.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.demo.adress.domain.OrderDo;

public interface ShoppingDao {
	@Select({
		""
	})
	List<OrderDo> selectUserOrder(int userId);
	
	OrderDo selectOrder(int orderId);
	
	int insertOrder(OrderDo order);
	
	int deleteOrder(int orderId);
}