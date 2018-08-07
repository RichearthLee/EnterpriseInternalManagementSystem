package com.mftcc.interior.cus.bean;

public class UserLinkman {

	private String linkmanId;
	private String linkmanName;
	private String userId;
	private String mobile;
	private String email;
	private String position;
	public String getLinkmanId() {
		return linkmanId;
	}
	public void setLinkmanId(String linkmanId) {
		this.linkmanId = linkmanId;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public UserLinkman(String linkmanId, String linkmanName, String userId,
			String mobile, String email, String position) {
		super();
		this.linkmanId = linkmanId;
		this.linkmanName = linkmanName;
		this.userId = userId;
		this.mobile = mobile;
		this.email = email;
		this.position = position;
	}
	public UserLinkman() {
		super();
	}
}
