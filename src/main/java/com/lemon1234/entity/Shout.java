package com.lemon1234.entity;

import java.util.Date;

/**
 * 问题
 */
public class Shout {

	private int id;
	
	private String openId;
	
	private String text;
	
	private String grit;
	
	private Date createDt;
	
	private String yyyymmdd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGrit() {
		return grit;
	}

	public void setGrit(String grit) {
		this.grit = grit;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getYyyymmdd() {
		return yyyymmdd;
	}

	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}
	
}
