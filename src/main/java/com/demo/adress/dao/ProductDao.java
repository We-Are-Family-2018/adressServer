package com.demo.adress.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.demo.adress.domain.ProductDo;

public interface ProductDao {
	
	@Select("select * from product where id = #{productId,jdbcType=INTEGER}")
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
	})
	ProductDo selectProduct(int productId);
	
	@Select({"select * from product"})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
	})
	List<ProductDo> selectAllProduct();
	
	@Select({"select * from product where product_name like '%${productName}%'"})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
	})
	List<ProductDo> searchProduct(@Param("productName") String productName);
	
}
