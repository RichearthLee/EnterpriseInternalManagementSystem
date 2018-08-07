package com.mftcc.interior.cred.bean;

public class ServiceBean {
	
	private String serviceNo;
	private String serviceNumber;//服务编号
	private String serviceName;//服务名称
	private String serviceProvider;//服务商
	private String serviceState;//服务状态
	private String servicePrice;//服务价格
	private String serviceDate;//服务开通日期
	
	
	
	public String getServiceNo() {
		return serviceNo;
	}



	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}



	public String getServiceNumber() {
		return serviceNumber;
	}



	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}



	public String getServiceName() {
		return serviceName;
	}



	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



	public String getServiceProvider() {
		return serviceProvider;
	}



	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}



	public String getServiceState() {
		return serviceState;
	}



	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}



	public String getServicePrice() {
		return servicePrice;
	}



	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}



	public String getServiceDate() {
		return serviceDate;
	}



	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}



	@Override
	public String toString() {
		return "ServiceBean [serviceNo=" + serviceNo + ", serviceNumber=" + serviceNumber
				+ ", serviceName=" + serviceName + ", serviceProvider=" + serviceProvider
				+ ", serviceState=" + serviceState + ", servicePrice=" + servicePrice + ",serviceDate=" + serviceDate + "]";
	}
	
	
}
