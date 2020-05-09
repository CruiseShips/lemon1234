package com.java.lemon.model.entity;

import java.util.Date;

/**
 * 技术文档（分享一些文档等内容，比如阿里巴巴Java技术规范、SpringBoot讲解等文档）
 */
public class Document {
	
	private int id;
	
	// 文档名称
	private String name;
	
	// 原创作者
	private String author;
	
	// 介绍(300个字)
	private String summary;
	
	// 图片展示
	private String img;
	
	// 创建日期
	private Date createDt;
	
	// 百度网盘链接
	private String baiduUrl;
	
	// 百度网盘密码
	private String password;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getBaiduUrl() {
		return baiduUrl;
	}

	public void setBaiduUrl(String baiduUrl) {
		this.baiduUrl = baiduUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
