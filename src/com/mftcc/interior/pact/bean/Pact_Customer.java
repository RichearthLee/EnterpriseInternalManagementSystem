
package com.mftcc.interior.pact.bean;

import java.util.List;


public class Pact_Customer {
	public Pact_Customer(){}
	
	private  String customerId;
	//
	private  String customerName;
	private  String customerType;
	private  String province;
	private  String city;
	private  String area;
	
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

	//
	private  String location;
	//
	private  String linkman;
	
	private String linkmanTitle;
	
	public String getLinkmanTitle() {
		return linkmanTitle;
	}

	
	public void setLinkmanTitle(String linkmanTitle) {
		this.linkmanTitle = linkmanTitle;
	}

	//
	private  String tetephone;
	//
	private  String mobilephone;
	//
	private  String email;
	//
	private  String entryDate;
	//
	private  String marketerId;
	
	private  String pactId;
	
	private String pactName;
	
	

	//
	private  String pactFee;

	/** 客户返款金额 */
	private  String customerMoney;
	//
	private  String productName;
	
	private  String pactStartDate;
	private  String pactOverDate;
	//
	private  String description;
	//
	private  String feeDate;
	
	private  String beforesale;
	//
	private  String focusPersion;
	
	private List<PaymentPlan> paymentPlanList;
	
	private List<PactFileInfo> pactFileList;
	
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
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLinkman() {
		return linkman;
	}
	
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	public String getTetephone() {
		return tetephone;
	}
	
	public void setTetephone(String tetephone) {
		this.tetephone = tetephone;
	}
	
	public String getMobilephone() {
		return mobilephone;
	}
	
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public String getPactId() {
		return pactId;
	}
	
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	
	public String getPactFee() {
		return pactFee;
	}
	
	public void setPactFee(String pactFee) {
		this.pactFee = pactFee;
	}
	
	/**
	 * 客户返款
	 * @return the customerMoney
	 */
	public String getCustomerMoney() {
		return customerMoney;
	}


	/**
	 * 客户返款
	 * @param customerMoney the customerMoney to set
	 */
	public void setCustomerMoney(String customerMoney) {
		this.customerMoney = customerMoney;
	}


	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getPactStartDate() {
		return pactStartDate;
	}
	
	public void setPactStartDate(String pactStartDate) {
		this.pactStartDate = pactStartDate;
	}
	
	public String getPactOverDate() {
		return pactOverDate;
	}
	
	public void setPactOverDate(String pactOverDate) {
		this.pactOverDate = pactOverDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFeeDate() {
		return feeDate;
	}
	
	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}
	
	public String getBeforesale() {
		return beforesale;
	}
	
	public void setBeforesale(String beforesale) {
		this.beforesale = beforesale;
	}
	
	public String getFocusPersion() {
		return focusPersion;
	}
	
	public void setFocusPersion(String focusPersion) {
		this.focusPersion = focusPersion;
	}

	public List<PaymentPlan> getPaymentPlanList() {
		return paymentPlanList;
	}

	public void setPaymentPlanList(List<PaymentPlan> paymentPlanList) {
		this.paymentPlanList = paymentPlanList;
	}

	public List<PactFileInfo> getPactFileList() {
		return pactFileList;
	}

	public void setPactFileList(List<PactFileInfo> pactFileList) {
		this.pactFileList = pactFileList;
	}
	public String getPactName() {
		return pactName;
	}

	
	public void setPactName(String pactName) {
		this.pactName = pactName;
	}

}
