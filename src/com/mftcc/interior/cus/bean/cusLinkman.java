package com.mftcc.interior.cus.bean;

public class cusLinkman {

	private String linkId;
	private String linkName;
	private String location;
	private String marketerId;
	//这张表的marketerId实际上是客户id
	private String title;
	private String phone;
	private String telphone;
	private String email;
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMarketerId() {
		return marketerId;
	}
	public void setMarketerId(String marketerId) {
		this.marketerId = marketerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public cusLinkman(String linkId, String linkName, String location,
			String marketerId, String title, String phone, String telphone,
			String email) {
		super();
		this.linkId = linkId;
		this.linkName = linkName;
		this.location = location;
		this.marketerId = marketerId;
		this.title = title;
		this.phone = phone;
		this.telphone = telphone;
		this.email = email;
	}
	public cusLinkman() {
		super();
	}
}
