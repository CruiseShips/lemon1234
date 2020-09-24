package com.lemon1234.entity;

import java.util.Date;

/**
 * 技术文档
 */
public class Document {

	private int id;
	// 名称
	private String name;
	// 作者
	private String author;
	// 介绍(300个字)
	private String summary;
	// 内容(稍微多展示一些)
	private String content;
	// 图片展示
	private String img;
	// 收录日期
	private Date createDt;
	// 百度网盘链接
	private String baiduUrl;
	// 百度网盘密码
	private String password;
	// 状态 0 正常 1 过期
	private int status;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
