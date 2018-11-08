package com.demo.adress.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.demo.adress.domain.OrderDo;

public interface ShoppingDao {
	@Select({
		"select `order`.*, user_name, password, telephone, mail, product_name, img"
		,"from `order`"
		,"inner join user on user.id = uid"
		,"inner join product on product.id = pid"
		,"where uid = #{userId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="pid", property="productId", jdbcType=JdbcType.INTEGER)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
		,@Result(column="uid", property="userId", jdbcType=JdbcType.INTEGER)
		,@Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR)
		,@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
	})
	List<OrderDo> selectUserOrder(int userId);
	
	@Select({
		"select `order`.*, user_name, password, telephone, mail, product_name, img"
		,"from `order`"
		,"inner join user on user.id = uid"
		,"inner join product on product.id = pid"
		,"where id = #{orderId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="pid", property="productId", jdbcType=JdbcType.INTEGER)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
		,@Result(column="uid", property="userId", jdbcType=JdbcType.INTEGER)
		,@Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR)
		,@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
	})
	OrderDo selectOrder(int orderId);
	
	@Insert({
		"insert into `order` (uid, pid, price, count)"
		,"values (#{userId,jdbcType=INTEGER}"
		,",#{productId,jdbcType=INTEGER}"
		,",#{price,jdbcType=DECIMAL}"
		,",#{count,jdbcType=INTEGER})"
	})
	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	int insertOrder(OrderDo order);
	
	@Delete({
		"delete from `order` where id=#{orderId,jdbcType=INTEGER}"
	})
	int deleteOrder(int orderId);
}