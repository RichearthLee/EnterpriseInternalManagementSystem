
package com.mftcc.interior.pact.bean;


public class PactWarningBean {
	public PactWarningBean(){}
	private String pactId;
	private  String customerName;
	private String marketerName;
	private  String productName;
	private String pactFee;

	/** 客户返款金额 */
	private  String customerMoney;
	private  String payfeeYet;
	//合同已收百分比
	private  String paypercent;
	//合同某次计划需要收取的总金额的百分比
	private  String paypercentPlan;
	//某次收款计划的收款日期
	private  String paymentDay;
	//某次收款计划的状态
	private String paymentState;
	//第几次收款计划
	private String paymentCount;
	private String flag;
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	public String getMarketerName() {
		return marketerName;
	}

	
	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getPactFee() {
		return pactFee;
	}
	
	public void setPactFee(String pactFee) {
		this.pactFee = pactFee;
	}
	
	/**
	 * @return the customerMoney
	 */
	public String getCustomerMoney() {
		return customerMoney;
	}

	/**
	 * @param customerMoney the customerMoney to set
	 */
	public void setCustomerMoney(String customerMoney) {
		this.customerMoney = customerMoney;
	}

	public String getPayfeeYet() {
		return payfeeYet;
	}
	
	public void setPayfeeYet(String payfeeYet) {
		this.payfeeYet = payfeeYet;
	}
	
	public String getPaypercent() {
		return paypercent;
	}
	
	public void setPaypercent(String paypercent) {
		this.paypercent = paypercent;
	}
	
	public String getPaypercentPlan() {
		return paypercentPlan;
	}
	
	public void setPaypercentPlan(String paypercentPlan) {
		this.paypercentPlan = paypercentPlan;
	}
	


	public String getPactId() {
		return pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	
	public String getPaymentDay() {
		return paymentDay;
	}

	
	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}

	
	public String getPaymentState() {
		return paymentState;
	}

	
	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	public String getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(String paymentCount) {
		this.paymentCount = paymentCount;
	}
}
