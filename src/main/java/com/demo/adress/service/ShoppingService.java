package com.demo.adress.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.OrderDo;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.ShoppingCartDo;
import com.demo.adress.domain.UserDo;

@Service
public class ShoppingService {
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserDo userLogin(String userName) {
		return null;
	}
	
	/**
	 * 用户认证
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean userAuth(String userName, String password) {
		return false;
	}
	
	/**
	 * 校验用户是否已存在
	 * @param userName
	 * @return
	 */
	public boolean existUser(String userName) {
		return true;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public UserDo addUser(UserDo user) {
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public UserDo userEdit(UserDo user) {
		return null;
	}
	
	/**
	 * 添加收货地址
	 * @param userId
	 * @param address
	 */
	public AddressDo addUserAddress(int userId, String address) {
		return null;
	}
	
	/**
	 * 查找用户所有收货地址
	 * @param userId
	 * @return
	 */
	public List<AddressDo> findAllUserAddress(int userId) {
		return null;
	}
	
	/**
	 * 移除一个收货地址
	 * @param addressId
	 */
	public void removeUserAddress(int addressId) {
		
	}
	
	/**
	 * 收藏商品
	 * @param userId
	 * @param productId
	 */
	public void addUserCollect(int userId, int productId) {
		
	}
	
	/**
	 * 查找用户所有收藏
	 * @param userId
	 * @return
	 */
	public List<ProductDo> findAllUserCollect(int userId) {
		return null;
	}
	
	/**
	 * 移除一个收藏
	 * @param userId
	 * @param productId
	 */
	public void removeUserCollect(int userId, int productId) {
		
	}
	
	/**
	 * 向购物车添加一个商品
	 * @param userId
	 * @param productId
	 */
	public void addShoppingCart(int userId, int productId) {
		
	}
	
	/**
	 * 从购物车移除一件商品
	 * @param userId
	 * @param productId
	 */
	public void removeShoppingCart(int userId, int productId) {
		
	}
	
	/**
	 * 查找购物车所有商品
	 * @param userId
	 * @return
	 */
	public List<ShoppingCartDo> findAllCart(int userId) {
		return null;
	}
	
	/**
	 * 添加一个订单
	 * @param userId
	 * @param productId
	 * @param count
	 */
	public void addOrder(int userId, int productId, int count) {
		
	}
	
	/**
	 * 移除一个订单
	 * @param orderId
	 */
	public void removeOrder(int orderId) {
		
	}
	
	/**
	 * 查找用户所有订单
	 * @param userId
	 * @return
	 */
	public List<OrderDo> findAllUserOrder(int userId) {
		return null;
	}
	
	/**
	 * 向用户账户存钱
	 * @param userId
	 * @param money
	 */
	public void saveMoney(int userId, BigDecimal money) {
		
	}
	
	/**
	 * 订单支付
	 * @param userId
	 * @param orderId
	 */
	public void payOrder(int userId, int orderId) {
		
	}
	
	/**
	 * 模糊查询商品
	 * @param productName
	 * @return
	 */
	public List<ProductDo> searchProduct(String productName) {
		return null;
	}
	
	/**
	 * 精确查找商品
	 * @param productId
	 * @return
	 */
	public ProductDo findProduct(int productId) {
		return null;
	}
	
	/**
	 * 查找所有商品
	 * @return
	 */
	public List<ProductDo> findAllProduct() {
		return null;
	}
}
