
package com.mftcc.interior.pact.bean;


public class PactListInfo {

	public PactListInfo () { }
	private  String customerId;
	//
	private  String customerName;
	//
	private  String linkman;
	//
	private  String pactId;
	//百分比
	private  String paypercent;
	
	private  String pactStartDate;
	private String pactOverDate;
	private String flag;
	public String getPactOverDate() {
		return pactOverDate;
	}


	public void setPactOverDate(String pactOverDate) {
		this.pactOverDate = pactOverDate;
	}
	private String productName;
	
	/** 市场人员（客户经理） */
	private String marketerName;
	
	private String pactFee;
	/** 客户返款金额 */
	private  String customerMoney;
	
	
	public String getPactFee() {
		return pactFee;
	}

	
	public void setPactFee(String pactFee) {
		this.pactFee = pactFee;
	}

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
	
	public String getLinkman() {
		return linkman;
	}
	
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	public String getPactId() {
		return pactId;
	}
	
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	
	public String getPaypercent() {
		return paypercent;
	}
	
	public void setPaypercent(String paypercent) {
		this.paypercent = paypercent;
	}
	
	

	public String getPactStartDate() {
		return pactStartDate;
	}


	public void setPactStartDate(String pactStartDate) {
		this.pactStartDate = pactStartDate;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return 市场人员（客户经理）
	 */
	public String getMarketerName() {
		return marketerName;
	}

	/**
	 * @param 市场人员（客户经理）
	 */
	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
	}


	/**
	 * 客户返款金额
	 * @return the customerMoney
	 */
	public String getCustomerMoney() {
		return customerMoney;
	}


	/**
	 * 客户返款金额
	 * @param customerMoney the customerMoney to set
	 */
	public void setCustomerMoney(String customerMoney) {
		this.customerMoney = customerMoney;
	}
	
	
	
	
	
}
