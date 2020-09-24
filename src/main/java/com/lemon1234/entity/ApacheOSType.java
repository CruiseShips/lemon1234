package com.lemon1234.entity;

public class ApacheOSType {

	private int id;
	// 类型名称
	private String name;
	
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public ApacheOSType(String typeId) {
		this.id = Integer.parseInt(typeId);
	}

	public ApacheOSType() {
	}
}
