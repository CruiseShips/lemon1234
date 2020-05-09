package com.java.lemon.model.dto;

import java.util.ArrayList;
import java.util.List;

public class StudyRoadMapDTO {
	// 阶段名称
	private String name;
	
	// 学习目标
	private String aims;
	
	// 排序
	private int sort;
	
	// 技术介绍
	private List<TechnologyDTO> technologyDTOs = new ArrayList<TechnologyDTO>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAims() {
		return aims;
	}

	public void setAims(String aims) {
		this.aims = aims;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<TechnologyDTO> getTechnologyDTOs() {
		return technologyDTOs;
	}

	public void setTechnologyDTOs(List<TechnologyDTO> technologyDTOs) {
		this.technologyDTOs = technologyDTOs;
	}
	
	public static class TechnologyDTO{
		private int id;
		
		// 技术名称
		private String name;
		
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
}


