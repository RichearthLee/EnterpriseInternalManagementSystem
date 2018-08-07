package com.mftcc.interior.cus.bean;

public class customer {

	private String customerId;
	private String customerName;
	private String customerType;
	/*private String linkman;
	private String linkmanTitle;
	private String phone;
	private String telphone;
	private String email;*/
	private String marketerId;
	private String province;
	private String city;
	private String country;
	private String entryDate;
	private String location;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getMarketerId() {
		return marketerId;
	}
	public void setMarketerId(String marketerId) {
		this.marketerId = marketerId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public customer(String customerId, String customerName,
			String customerType, String marketerId, String province,
			String city, String country, String entryDate, String location) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerType = customerType;
		this.marketerId = marketerId;
		this.province = province;
		this.city = city;
		this.country = country;
		this.entryDate = entryDate;
		this.location = location;
	}
	public customer() {
		super();
	}
}
