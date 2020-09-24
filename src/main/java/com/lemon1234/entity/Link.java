package com.lemon1234.entity;

/**
 * 友情链接
 */
public class Link {

	// id
	private int id;
	
	// 名称
	private String name;
	
	//请求地址
	private String url;
	
	//排序
	private int sort;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
