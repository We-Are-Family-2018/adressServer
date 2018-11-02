package com.demo.adress.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.adress.controller.form.UserInfoForm;
import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.GlobalResponse;
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
		AddressDo addressDo = shoppingService.addUserAddress(userId, address);
		
		return GlobalResponse.ok(addressDo);
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
	
	
}
