package com.lemon1234.entity;

import java.util.Date;

public class Message {

	private int id;
	
	private String ip;
	
	// 500 字
	private String message;
	private Date createDt;
	
	// 300字
	private String reply;
	private Date replyDt;
	
	public Message(String ip, String message) {
		this.ip = ip;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReplyDt() {
		return replyDt;
	}
	public void setReplyDt(Date replyDt) {
		this.replyDt = replyDt;
	}
	
}
