/**
 * 
 */
package com.demo.adress.domain;

import java.util.List;

public class PageData {
	private int code;
	
	private String msg;
	
	private long count;
	
	private List<?> data;

	public PageData(long totalCount, List<?> data) {
		this.count = totalCount;
		this.data = data;
		this.code = 0;
		this.msg = "";
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
