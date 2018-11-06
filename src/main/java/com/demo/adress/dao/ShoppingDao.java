package com.demo.adress.dao;

import java.util.List;

import com.demo.adress.domain.OrderDo;

public interface ShoppingDao {
	List<OrderDo> selectUserOrder(int userId);
	
	OrderDo selectOrder(int orderId);
	
	int insertOrder(OrderDo order);
	
	int deleteOrder(int orderId);
}
