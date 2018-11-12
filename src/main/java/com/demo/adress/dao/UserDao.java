package com.demo.adress.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.demo.adress.domain.AddressDo;
import com.demo.adress.domain.ProductDo;
import com.demo.adress.domain.UserDo;

public interface UserDao {
	@Select({"select * from user"})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
		,@Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR)
		,@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
		,@Result(column="balance", property="balance", jdbcType=JdbcType.VARCHAR)
	})
	List<UserDo> selectAllUser();
	
	@Select({"select * from user where user_name=#{userName,jdbcType=VARCHAR}"})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
		,@Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR)
		,@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
		,@Result(column="balance", property="balance", jdbcType=JdbcType.VARCHAR)
	})
	UserDo selectUser(String userName);
	
	@Select({"select * from user where id=#{userId,jdbcType=INTEGER}"})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
		,@Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR)
		,@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
		,@Result(column="balance", property="balance", jdbcType=JdbcType.VARCHAR)
	})
	UserDo selectUserById(int userId);
	
	@Insert({
		"insert into user (user_name, password, telephone, mail)"
		,"values (#{userName,jdbcType=VARCHAR}"
		,",#{password,jdbcType=VARCHAR}"
		,",#{telephone,jdbcType=VARCHAR}"
		,",#{mail,jdbcType=VARCHAR})"
	})
	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	int insertUser(UserDo user);
	
	@UpdateProvider(type=UserSqlProvider.class, method="updateUserInfo")
	int updateUser(UserDo user);
	
	@Select({
		"select * from address where uid=#{userId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER)
		,@Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
	})
	List<AddressDo> selectUserAddress(int userId);
	
	@Insert({
		"insert into address (uid, address)"
		,"values (#{uid,jdbcType=INTEGER}, #{address,jdbcType=VARCHAR})"
	})
	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	int insertUserAddress(AddressDo address);
	
	@Delete({
		"delete from address where id=#{addressId,jdbcType=INTEGER}"
	})
	int deleteUserAddress(int addressId);
	
	@Update({
		"update user set balance=#{balance,jdbcType=DECIMAL} where id=#{userId,jdbcType=INTEGER}"
	})
	int updateUserBalance(@Param("userId") int userId, @Param("balance") BigDecimal balance);
	
	@Select({
		"select product.* from product, shopping_cart"
		,"where shopping_cart.pid=product.id and uid=#{userId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
	})
	List<ProductDo> selectUserCart(int userId);
	
	@Insert({
		"insert into shopping_cart values (#{userId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER})"
	})
	int insertUserCartItem(@Param("userId") int userId, @Param("productId") int productId);
	
	@Delete({
		"delete from shopping_cart where uid=#{userId,jdbcType=INTEGER} and pid=#{productId,jdbcType=INTEGER}"
	})
	int deleteUserCartItem(@Param("userId") int userId, @Param("productId") int productId);
	
	@Select({
		"select product.* from product, collect"
		,"where collect.pid=product.id and uid=#{userId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
		,@Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
		,@Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
		,@Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
	})
	List<ProductDo> selectUserCollect(int userId);
	
	@Insert({
		"insert into collect values (#{userId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER})"
	})
	int insertUserCollectItem(@Param("userId") int userId, @Param("productId") int productId);
	
	@Delete({
		"delete from collect where uid=#{userId,jdbcType=INTEGER} and pid=#{productId,jdbcType=INTEGER}"
	})
	int deleteUserCollectItem(@Param("userId") int userId, @Param("productId") int productId);
}
