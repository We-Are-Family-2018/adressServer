package com.demo.adress;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.demo.adress.dao"})
public class AdressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdressApplication.class, args);
	}
}
