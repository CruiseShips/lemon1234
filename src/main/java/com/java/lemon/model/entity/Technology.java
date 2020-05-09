package com.java.lemon.model.entity;

import java.util.Date;

/**
 * 项目使用技术 Model
 */
public class Technology {
	
	// 中立 0 前端1   后端 2
	public static final int BETWEEN = 0;
	public static final int BEFORE = 1;
	public static final int AFTER = 2;
	
	// 难易程度 0 简单  1 一般、中等   2难  3复杂
	public static final int EASY = 0;
	public static final int GENERAL = 1;
	public static final int DIFFICULT = 2;
	public static final int COMPLEX = 3;

	private int id;
	
	// 技术名称
	private String name;
	
	// 编码位置(前后端)
	private int codingPosition;
	
	// 技术简介
	private String summary;
	
	// 技术介绍
	private String content;
	
	// 技术难度 0 - 3
	private int level;
	
	// 学习版本
	private String version;
	
	// 官网链接
	private String officialLink;
	
	// 创建日期
	private Date createDt;
	
	// logo 图片地址
	private String logo;
	
	// 技术所属路线
	private int roadId;
	
	// CSDN学习 博客
	private String csdnUrl;
	
	// 视频连接
	private String videoUrl;
	
	// 分享人ID（管理员还是其他人）
	private String shareName;

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

	public int getCodingPosition() {
		return codingPosition;
	}

	public void setCodingPosition(int codingPosition) {
		this.codingPosition = codingPosition;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOfficialLink() {
		return officialLink;
	}

	public void setOfficialLink(String officialLink) {
		this.officialLink = officialLink;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getRoadId() {
		return roadId;
	}

	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCsdnUrl() {
		return csdnUrl;
	}

	public void setCsdnUrl(String csdnUrl) {
		this.csdnUrl = csdnUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	
}
