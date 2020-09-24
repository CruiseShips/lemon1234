package com.lemon1234.entity;

import java.util.Date;

/**
 * 回答
 */
public class Grit {
	
	public static final Integer PASS = 1;
	public static final Integer NO = 0;
	
	private int id;
	// 回答
	private String text;
	// 系统的话是 admin
	private String openId;
	// 使用日期
	private Date createDt;
	// 审核通过 0 未通过 1 通过
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
