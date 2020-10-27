package com.lemon1234.entity;

import java.util.Date;

/**
 * 面试题 
 */
public class InterviewQuestions {

	private int id;
	// 面试题名称
	private String name;
	// 困难程度 1 - 10
	private int hard;
	// 问题数量
	private int qCount;
	// 点赞
	private int star;
	// 分享量
	private int share;
	// 查看量
	private int view;
	// 创建日期
	private Date createDt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHard() {
		return hard;
	}
	public void setHard(int hard) {
		this.hard = hard;
	}
	public int getqCount() {
		return qCount;
	}
	public void setqCount(int qCount) {
		this.qCount = qCount;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
}
