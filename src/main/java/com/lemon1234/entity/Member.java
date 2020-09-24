package com.lemon1234.entity;

import java.util.Date;

/**
 * 会员
 */
public class Member {

	private int id;
	
	// 推荐人 默认是0
	private int parentId;
	
	// 真实姓名
	private String realName;
	
	private String qqNum;
	
	private String wxNum;
	
	// 支付日期
	private Date payDt;
	
	// 从哪里看到我的 1 csdn 2 bilibili 3 lemon1234 4 公众号 5 小程序 6 朋友推荐 7 其他 
	private int from;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getWxNum() {
		return wxNum;
	}

	public void setWxNum(String wxNum) {
		this.wxNum = wxNum;
	}

	public Date getPayDt() {
		return payDt;
	}

	public void setPayDt(Date payDt) {
		this.payDt = payDt;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}
	
}
