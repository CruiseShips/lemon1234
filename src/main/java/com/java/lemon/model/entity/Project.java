package com.java.lemon.model.entity;

import java.util.Date;

/**
 * Java 开源项目 Model
 */
public class Project {
	
	// 是否发布 1 发布 0 没有发布
	public static final int NOTRELEASE = 0;
	public static final int RELEASE = 1;

	private int id;
	
	// 项目名称
	private String name;
	
	// 介绍
	private String summary;
	
	// 发布日期
	private Date releaseDate;
	
	// 具体内容
	private String content;
	
	// 视频链接
	private String videoUrl;
	
	// gitHub地址
	private String gitHubUrl;
	
	// 分享人名称
	private String shareName;
	
	// 查看次数
	private int clickHit;
	
	// 点赞量
	private int likeHit;
	
	// 是否发布 0 未发布，1 发布了
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getClickHit() {
		return clickHit;
	}

	public void setClickHit(int clickHit) {
		this.clickHit = clickHit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getGitHubUrl() {
		return gitHubUrl;
	}

	public void setGitHubUrl(String gitHubUrl) {
		this.gitHubUrl = gitHubUrl;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLikeHit() {
		return likeHit;
	}

	public void setLikeHit(int likeHit) {
		this.likeHit = likeHit;
	}
	
}
