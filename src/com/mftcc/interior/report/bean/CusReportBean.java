package com.mftcc.interior.report.bean;

public class CusReportBean {//客户信息
	private String customerId;
	private String customerName;
	private String customerType;//类型
	private String province;//省
	private String city;//市
	private String area;//县
	private String location;//详细地址
	private String entryDate;//登记时间
	private String marketerId;//客户所属员工
	private String marketerName;//客户所属员工
	private String brNo;//客户所属员工
	private String brName;//客户所属员工
	private String brProvince;//客户所属员工
	private String brCity;//客户所属员工
	private String brArea;//客户所属员工
	private String linkName;//客户方联系人
	private String title;//职位
	private String phone;
	private String telphone;
	private String email;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getMarketerId() {
		return marketerId;
	}
	public void setMarketerId(String marketerId) {
		this.marketerId = marketerId;
	}
	public String getMarketerName() {
		return marketerName;
	}
	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
	}
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getBrProvince() {
		return brProvince;
	}
	public void setBrProvince(String brProvince) {
		this.brProvince = brProvince;
	}
	public String getBrCity() {
		return brCity;
	}
	public void setBrCity(String brCity) {
		this.brCity = brCity;
	}
	public String getBrArea() {
		return brArea;
	}
	public void setBrArea(String brArea) {
		this.brArea = brArea;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CusReportBean [customerId=");
		builder.append(customerId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerType=");
		builder.append(customerType);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", area=");
		builder.append(area);
		builder.append(", location=");
		builder.append(location);
		builder.append(", entryDate=");
		builder.append(entryDate);
		builder.append(", marketerId=");
		builder.append(marketerId);
		builder.append(", marketerName=");
		builder.append(marketerName);
		builder.append(", brNo=");
		builder.append(brNo);
		builder.append(", brName=");
		builder.append(brName);
		builder.append(", brProvince=");
		builder.append(brProvince);
		builder.append(", brCity=");
		builder.append(brCity);
		builder.append(", brArea=");
		builder.append(brArea);
		builder.append(", linkName=");
		builder.append(linkName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", telphone=");
		builder.append(telphone);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
	
	
}
