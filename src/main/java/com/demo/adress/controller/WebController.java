package com.demo.adress.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.adress.controller.form.UserInfoForm;
import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.GlobalResponse;
import com.demo.adress.domain.OrderDo;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.UserDo;
import com.demo.adress.service.ShoppingService;

@Controller
@RequestMapping("/adressingWeb")
public class WebController {
	
	private ShoppingService shoppingService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String userName, String password) {
		boolean res = shoppingService.userAuth(userName, password);
		if (res == false) {
			return GlobalResponse.error("用户名密码错误");
		}
		
		UserDo user = shoppingService.userLogin(userName);
		
		return GlobalResponse.ok(user);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Object register(UserInfoForm form) {
		boolean res = shoppingService.existUser(form.getUserName());
		if (res == true) {
			return GlobalResponse.error("用户名已存在");
		}
		
		UserDo user = new UserDo();
		user.setUserName(form.getUserName());
		user.setPassword(user.getPassword());
		user.setMail(form.getMail());
		user.setTelephone(form.getTelephone());
		
		user = shoppingService.addUser(user);
		
		return GlobalResponse.ok(user);
	}
	
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(int userId, UserInfoForm form) {
		UserDo user = new UserDo();
		user.setId(userId);
		user.setTelephone(form.getTelephone());
		user.setMail(form.getMail());
		
		shoppingService.userEdit(user);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public Object changePassword(int userId, String password) {
		UserDo user = new UserDo();
		user.setId(userId);
		user.setPassword(password);
		
		shoppingService.userEdit(user);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/addAddress")
	@ResponseBody
	public Object addAddress(int userId, String address) {
		shoppingService.addUserAddress(userId, address);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/myAddress")
	@ResponseBody
	public Object myAddress(int userId) {
		List<AddressDo> addressDos = shoppingService.findAllUserAddress(userId);
		
		return GlobalResponse.ok(addressDos);
	}
	
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Object deleteAddress(int addressId) {
		shoppingService.removeUserAddress(addressId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Object search(String searchContent) {
		List<ProductDo> products = shoppingService.searchProduct(searchContent);
		
		return GlobalResponse.ok(products);
	}
	
	@RequestMapping("/searchProductById")
	@ResponseBody
	public Object searchProductById(int productId) {
		ProductDo product = shoppingService.findProduct(productId);
		
		return GlobalResponse.ok(product);
	}
	
	@RequestMapping("/seleteAllProduct")
	@ResponseBody
	public Object seleteAllProduct() {
		List<ProductDo> products = shoppingService.findAllProduct();
		
		return GlobalResponse.ok(products);
	}
	
	@RequestMapping("/addCard")
	@ResponseBody
	public Object addCard(int userId, int productId) {
		shoppingService.addShoppingCart(userId, productId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/myCard")
	@ResponseBody
	public Object myCard(int userId) {
		List<ProductDo> carts = shoppingService.findAllCart(userId);
	
		return GlobalResponse.ok(carts);
	}
	
	@RequestMapping("/deleteCard")
	@ResponseBody
	public Object deleteCard(int userId, int productId) {
		shoppingService.removeShoppingCart(userId, productId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/addCollect")
	@ResponseBody
	public Object addCollect(int userId, int productId) {
		shoppingService.addUserCollect(userId, productId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/myCollect")
	@ResponseBody
	public Object myCollect(int userId) {
		List<ProductDo> productDos = shoppingService.findAllUserCollect(userId);
		
		return GlobalResponse.ok(productDos);
	}
	
	@RequestMapping("/deleteCollect")
	@ResponseBody
	public Object deleteCollect(int userId, int productId) {
		shoppingService.removeUserCollect(userId, productId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder(int userId, int productId, int number) {
		shoppingService.addOrder(userId, productId, number);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public Object deleteOrder(int orderId) {
		shoppingService.removeOrder(orderId);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/myOrder")
	@ResponseBody
	public Object myOrder(int userId) {
		List<OrderDo> orders = shoppingService.findAllUserOrder(userId);
		
		return GlobalResponse.ok(orders);
	}
	
	@RequestMapping("/saveMoney")
	@ResponseBody
	public Object saveMoney(int userId, BigDecimal money) {
		shoppingService.saveMoney(userId, money);
		
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/pay")
	@ResponseBody
	public Object pay(int userId, int orderId) {
		shoppingService.payOrder(userId, orderId);
		
		return GlobalResponse.ok(null);
	}
}
