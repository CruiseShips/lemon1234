package com.java.lemon.model.entity;

import java.util.Date;

/**
 * 管理员 Model
 */
public class Admin{
	
	private int id;
	
	// 管理员名称
	private String name;
	
	// 用户名
	private String userName;
	
	// 密码
	private String password;
	
	// 邮箱
	private String email;
	
	// 头像图片
	private String imageName;
	
	// 创建日期
	private Date createDt;
	
	// 权限
	private int roleLevel;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	
}
