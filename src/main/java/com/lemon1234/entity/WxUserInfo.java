package com.lemon1234.entity;

import java.util.Date;

/**
 * 站长信息
 */
public class WxUserInfo {

	private int id;
	private String nickName;
	private String img;
	private String province;
	private String country;
	private String city;
	private int gender;
	private String openId;
	
	private Date createDt;
	private Date lastUpdateDt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}
	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	
	@Override
	public String toString() {
		return "WxUserInfo [id=" + id + ", nickName=" + nickName + ", img=" + img + ", province=" + province
				+ ", country=" + country + ", city=" + city + ", gender=" + gender + ", openId=" + openId
				+ ", createDt=" + createDt + ", lastUpdateDt=" + lastUpdateDt + "]";
	}
	
}
