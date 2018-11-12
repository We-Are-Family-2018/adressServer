package com.demo.adress.dao;

import org.apache.ibatis.jdbc.SQL;

import com.demo.adress.domain.UserDo;

public class UserSqlProvider {
	
	public String updateUserInfo(UserDo user) {
		SQL sql = new SQL();
		
		sql.UPDATE("user");
		
		sql.SET("id=#{id,jdbcType=INTEGER}");
		
		if (user.getPassword() != null) {
			sql.SET("password=#{password,jdbcType=VARCHAR}");
		}
		
		if (user.getTelephone() != null) {
			sql.SET("telephone=#{telephone,jdbcType=VARCHAR}");
		}
		
		if (user.getMail() != null) {
			sql.SET("mail=#{mail,jdbcType=VARCHAR}");
		}
		
		if (user.getBalance() != null) {
			sql.SET("balance=#{balance,jdbcType=DECIMAL}");
		}
		
		sql.WHERE("id=#{id,jdbcType=INTEGER}");
		
		return sql.toString();
	}
	
}
