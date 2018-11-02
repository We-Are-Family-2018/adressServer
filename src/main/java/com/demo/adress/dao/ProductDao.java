package com.demo.adress.dao;

import java.util.List;

import com.demo.adress.domain.ProductDo;

public interface ProductDao {
	ProductDo selectProduct(int productId);
	
	List<ProductDo> selectAllProduct();
	
	List<ProductDo> searchProduct(String productName);
	
}
