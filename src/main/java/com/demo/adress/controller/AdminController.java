/**
 * 
 */
package com.demo.adress.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.adress.controller.form.PageForm;
import com.demo.adress.controller.form.SearchOrderForm;
import com.demo.adress.controller.form.SearchProductForm;
import com.demo.adress.domain.GlobalResponse;
import com.demo.adress.domain.OrderDo;
import com.demo.adress.domain.PageData;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.UserDo;
import com.demo.adress.service.ShoppingService;
import com.github.pagehelper.PageInfo;

@Controller
@CrossOrigin
@RequestMapping("/managerWeb")
public class AdminController {
	
	@Autowired
	private ShoppingService service;
	
	/**
	 * 商品上架
	 * @param product
	 * @return
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	public Object addProduct(ProductDo product) {
		product = service.addProduct(product);
		return  GlobalResponse.ok(product);
	}
	
	/**
	 * 商品下架
	 * @param productId
	 * @return
	 */
	@RequestMapping("/deleteProduct")
	@ResponseBody
	public Object deleteProduct(int productId) {
		service.deleteProduct(productId);
		return GlobalResponse.ok(null);
	}
	
	/**
	 * 商品编辑
	 * @param bookInfo
	 * @return
	 */
	@RequestMapping("/updateProduct")
	@ResponseBody
	public Object editBook(ProductDo product) {
		product = service.editProduct(product);
		return GlobalResponse.ok(product);
	}
	
	/**
	 * 查询所有商品
	 * @return
	 */
	@RequestMapping("/searchProduct")
	@ResponseBody
	public Object searchBook(SearchProductForm form) {
		PageInfo<ProductDo> page = service.searchProduct(form);
		PageData data = new PageData(page.getTotal(), page.getList());
		return data;
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	@RequestMapping("/searchUser")
	@ResponseBody
	public Object selectAllUser(PageForm form) {
		PageInfo<UserDo> pageInfo = service.findAllUser(form);
		PageData data = new PageData(pageInfo.getTotal(), pageInfo.getList());
		return data;
	}
	
	/**
	 * 查找订单
	 * @return
	 */
	@RequestMapping("/searchOrder")
	@ResponseBody
	public Object searchOrder(SearchOrderForm form) {
		PageInfo<OrderDo> pageInfo = service.searchOrder(form);
		PageData data = new PageData(pageInfo.getTotal(), pageInfo.getList());
		return data;
	}
	
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public Object updateOrderStatus(int orderId, int orderStatus) {
		service.updateOrderStatus(orderStatus, orderId);
		return GlobalResponse.ok(null);
	}
	
	@RequestMapping("/uploadProductImg")
	@ResponseBody
	public Object uploadImg(@RequestParam(value="productId", required=false) Integer productId, 
			@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
		// 生成新名字
		String fileName = generateNewName(file.getOriginalFilename());
		
		// 保存到文件系统
		String imgPath = "external/img/" + fileName;
		Path path = Paths.get(imgPath);
		Files.write(path, file.getBytes());
		
		// 生成图片url
		String imgUrl = "/static/img/" + fileName;
		
		// 如果id存在则更新相应图书的封面url
		if (productId != null) {
			ProductDo product = service.findProduct(productId);
			product.setImg(imgUrl);
			service.editProduct(product);
		}
		
		// 返回数据
		Map<String, Object> map = new HashMap<>();
		map.put("img", imgUrl);
		
		return GlobalResponse.ok(map);
	}

	private String generateNewName(String fileName) throws NoSuchAlgorithmException {
		// 获取文件后缀名
		String[] tempArr = fileName.split("\\.");
		String imgType = tempArr[tempArr.length - 1];
		
		// 生成文件名
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String temp = LocalDateTime.now().toString() + fileName;
		String newName = DatatypeConverter.printHexBinary(md5.digest(temp.getBytes())) + "." + imgType;
		return newName;
	}
}
