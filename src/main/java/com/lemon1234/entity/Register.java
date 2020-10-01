package com.lemon1234.entity;

import java.util.Date;

/**
 * 失效连接登记
 */
public class Register {
	
	public static final Integer NOTDO = 0;
	public static final Integer ISDO = 1;
	
	// type 类型
	public static final Integer DOCUMENT = 0;

	private int id;
	
	private String phoneNum;
	
	private String qqNum;
	
	private String nickName;
	
	// 创建日期
	private Date createDt;
	
	// 处理日期
	private Date processDt;
	
	// 是否处理 0 未处理 1 处理
	private int status;
	
	// 举报的类型，是document
	private int type;
	
	private int docId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getProcessDt() {
		return processDt;
	}

	public void setProcessDt(Date processDt) {
		this.processDt = processDt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}
	
}
