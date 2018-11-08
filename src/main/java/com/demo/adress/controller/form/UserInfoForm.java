package com.demo.adress.controller.form;

import javax.validation.constraints.NotBlank;

public class UserInfoForm {
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	private String telephone;
	
	private String mail;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
