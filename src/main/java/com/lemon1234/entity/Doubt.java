package com.lemon1234.entity;

import java.util.Date;

/**
 * 面试问题疑问 t_doubt
 */
public class Doubt {

	private int id;
	private int qId;
	// 疑问是什么
	private String doubt;
	// 是否已经处理 0未处理 1处理
	private int status;
	private Date createDt;
	// 处理时间
	private Date processDt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getDoubt() {
		return doubt;
	}
	public void setDoubt(String doubt) {
		this.doubt = doubt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	
}
