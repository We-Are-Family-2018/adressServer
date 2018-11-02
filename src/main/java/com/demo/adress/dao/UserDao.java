package com.demo.adress.dao;

import java.math.BigDecimal;
import java.util.List;

import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.UserDo;

public interface UserDao {
	UserDo selectUser(String userName);
	
	int insertUser(UserDo user);
	
	int updateUser(UserDo user);
	
	List<AddressDo> selectUserAddress(int userId);
	
	int insertUserAddress(int userId, String address);
	
	int deleteUserAddress(int addressId);
	
	int updateUserBalance(int userId, BigDecimal balance);
	
	int selectUserBalance(int userId);
	
	List<ProductDo> selectUserCart(int userId);
	
	int insertUserCartItem(int userId, int productId);
	
	int deleteUserCartItem(int userId, int productId);
	
	List<ProductDo> selectUserCollect(int userId);
	
	int insertUserCollectItem(int userId, int productId);
	
	int deleteUserCollectItem(int userId, int productId);
}
