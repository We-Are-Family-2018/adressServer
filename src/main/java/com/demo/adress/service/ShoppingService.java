package com.demo.adress.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.adress.dao.ProductDao;
import com.demo.adress.dao.ShoppingDao;
import com.demo.adress.dao.UserDao;
import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.OrderDo;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.UserDo;

@Service
public class ShoppingService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShoppingDao shoppingDao;
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserDo userLogin(String userName) {
		return userDao.selectUser(userName);
	}
	
	/**
	 * 用户认证
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean userAuth(String userName, String password) {
		UserDo userDo = userDao.selectUser(userName);
		if (userDo == null || !userDo.getPassword().equals(password)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 用户认证
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean userAuth(int userId, String password) {
		UserDo userDo = userDao.selectUserById(userId);
		if (userDo == null || !userDo.getPassword().equals(password)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 校验用户是否已存在
	 * @param userName
	 * @return
	 */
	public boolean existUser(String userName) {
		UserDo userDo = userDao.selectUser(userName);
		if (userDo == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public UserDo addUser(UserDo user) {
		userDao.insertUser(user);
		return user;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public UserDo userEdit(UserDo user) {
		userDao.updateUser(user);
		return user;
	}
	
	/**
	 * 添加收货地址
	 * @param userId
	 * @param address
	 */
	public void addUserAddress(int userId, String address) {
		AddressDo addressDo = new AddressDo();
		addressDo.setUid(userId);
		addressDo.setAddress(address);
		userDao.insertUserAddress(addressDo);
	}
	
	/**
	 * 查找用户所有收货地址
	 * @param userId
	 * @return
	 */
	public List<AddressDo> findAllUserAddress(int userId) {
		return userDao.selectUserAddress(userId);
	}
	
	/**
	 * 移除一个收货地址
	 * @param addressId
	 */
	public void removeUserAddress(int addressId) {
		userDao.deleteUserAddress(addressId);
	}
	
	/**
	 * 收藏商品
	 * @param userId
	 * @param productId
	 */
	public void addUserCollect(int userId, int productId) {
		userDao.insertUserCollectItem(userId, productId);
	}
	
	/**
	 * 查找用户所有收藏
	 * @param userId
	 * @return
	 */
	public List<ProductDo> findAllUserCollect(int userId) {
		return userDao.selectUserCollect(userId);
	}
	
	/**
	 * 移除一个收藏
	 * @param userId
	 * @param productId
	 */
	public void removeUserCollect(int userId, int productId) {
		userDao.deleteUserCollectItem(userId, productId);
	}
	
	/**
	 * 向购物车添加一个商品
	 * @param userId
	 * @param productId
	 */
	public void addShoppingCart(int userId, int productId) {
		userDao.insertUserCartItem(userId, productId);
	}
	
	/**
	 * 从购物车移除一件商品
	 * @param userId
	 * @param productId
	 */
	public void removeShoppingCart(int userId, int productId) {
		userDao.deleteUserCartItem(userId, productId);
	}
	
	/**
	 * 查找购物车所有商品
	 * @param userId
	 * @return
	 */
	public List<ProductDo> findAllCart(int userId) {
		return userDao.selectUserCart(userId);
	}
	
	/**
	 * 添加一个订单
	 * @param userId
	 * @param productId
	 * @param count
	 */
	public void addOrder(int userId, int productId, int count) {
		ProductDo productDo = productDao.selectProduct(productId);
		
		OrderDo order = new OrderDo();
		order.setUserId(userId);
		order.setProductId(productId);
		order.setCount(count);
		order.setPrice(productDo.getPrice());
		shoppingDao.insertOrder(order);
	}
	
	/**
	 * 移除一个订单
	 * @param orderId
	 */
	public void removeOrder(int orderId) {
		shoppingDao.deleteOrder(orderId);
	}
	
	/**
	 * 查找用户所有订单
	 * @param userId
	 * @return
	 */
	public List<OrderDo> findAllUserOrder(int userId) {
		return shoppingDao.selectUserOrder(userId);
	}
	
	/**
	 * 向用户账户存钱
	 * @param userId
	 * @param money
	 */
	public void saveMoney(int userId, BigDecimal money) {
		UserDo userDo = userDao.selectUserById(userId);
		BigDecimal balance = userDo.getBalance();
		userDo.setBalance(balance.add(money));
		userDao.updateUser(userDo);
	}
	
	/**
	 * 订单支付
	 * @param userId
	 * @param orderId
	 */
	public boolean payOrder(int userId, int orderId) {
		UserDo userDo = userDao.selectUserById(userId);
		OrderDo orderDo = shoppingDao.selectOrder(userId);
		
		BigDecimal total = orderDo.getPrice().multiply(BigDecimal.valueOf(orderDo.getCount()));
		
		BigDecimal balance = userDo.getBalance();
		balance = balance.subtract(total);
		
		// 余额为负数
		if (balance.signum() < 0) {
			return false;
		}
		
		userDo.setBalance(balance);
		userDao.updateUser(userDo);
		
		return true;
	}
	
	/**
	 * 模糊查询商品
	 * @param productName
	 * @return
	 */
	public List<ProductDo> searchProduct(String productName) {
		return productDao.searchProduct(productName);
	}
	
	/**
	 * 精确查找商品
	 * @param productId
	 * @return
	 */
	public ProductDo findProduct(int productId) {
		return productDao.selectProduct(productId);
	}
	
	/**
	 * 查找所有商品
	 * @return
	 */
	public List<ProductDo> findAllProduct() {
		return productDao.selectAllProduct();
	}
}
