package com.demo.adress.domain;

public class GlobalResponse {
	private int code;
	
	private String msg;
	
	private Object data;
	
	protected GlobalResponse(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static GlobalResponse ok(Object data) {
		return new GlobalResponse(0, "", data);
	}
	
	public static GlobalResponse error(String msg) {
		return new GlobalResponse(1, msg, null);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
