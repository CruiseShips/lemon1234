package com.lemon1234.entity;

import java.util.Date;

/**
 *  Open Source
 */
public class OpenOS {

	private int id;
	// 开源项目名字
	private String name;
	// 简介
	private String summary;
	// img
	private String img;
	// 内容介绍
	private String content;
	// 官网地址
	private String url;
	// 创建日期
	private Date createDt;
	// 状态 0 存活 1 不存活2正在培养
	private int status;
	// 学习博客连接
	private String studyUrl;
	// demo下载地址
	private String demoUrl;
	// 查看次数
	private int view;
	// 分享次数
	private int share;
	
	// 类别 id
	private OpenOSType openOSType;
	// 使用语言id
	private Language language;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getStudyUrl() {
		return studyUrl;
	}
	public void setStudyUrl(String studyUrl) {
		this.studyUrl = studyUrl;
	}
	public String getDemoUrl() {
		return demoUrl;
	}
	public void setDemoUrl(String demoUrl) {
		this.demoUrl = demoUrl;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public OpenOSType getOpenOSType() {
		return openOSType;
	}
	public void setOpenOSType(OpenOSType openOSType) {
		this.openOSType = openOSType;
	}
	
}
