package com.java.lemon.model.entity;

/**
 * 项目、技术关联表
 */
public class Project_Technology {
	
	private int id;

	private int pid;
	
	private int tid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
}
