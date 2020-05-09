package com.java.lemon.model.dto;

public class ProjectDTO {
	
	private int id;
	
	// 项目名称
	private String name;
	
	// 介绍
	private String summary;
	
	// 发布日期
	private String releaseDateStr;
	
	// 内容
	private String content;
	
	// 视频连接
	private String videoUrl;
	
	// gitHub地址
	private String gitHubUrl;
	
	// 分享人ID（管理员还是其他人）
	private String shareName;
	
	// 查看次数
	private int clickHit;
	
	// 点赞量
	private int likeHit;

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

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
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

	public int getClickHit() {
		return clickHit;
	}

	public void setClickHit(int clickHit) {
		this.clickHit = clickHit;
	}

	public int getLikeHit() {
		return likeHit;
	}

	public void setLikeHit(int likeHit) {
		this.likeHit = likeHit;
	}
	
}
