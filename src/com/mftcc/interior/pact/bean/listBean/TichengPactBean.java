
package com.mftcc.interior.pact.bean.listBean;


public class TichengPactBean {
	public TichengPactBean(){}
	private String pactId;
	private String customerName; 
	private String pactName;
	private String pactStartDate;
	private String pactOverDate;
	//
	private  String pactFee;

	/** 客户返款金额 */
	private  String customerMoney;
	//
	private  String productName;
	private  String payfeeYet;
	//百分比
	private  String paypercent;
	//最近一次收款金额
	private String lastMoney;
	//最近一次收款时间
	private String lastDate;
	private String date80Percent;
	private String date100Percent;
	private String marketer;
	
	private String marketerTicheng;
	private String beforesale1;
	private String beforesaleTicheng1;
	private String beforesale2;
	private String beforesaleTicheng2;
	private String aftersale;
	private String aftersaleTicheng;
	private String flag;
	
	
	public String getDate80Percent() {
		return date80Percent;
	}

	
	public void setDate80Percent(String date80Percent) {
		this.date80Percent = date80Percent;
	}

	public String getDate100Percent() {
		return date100Percent;
	}
	
	public void setDate100Percent(String date100Percent) {
		this.date100Percent = date100Percent;
	}


	public String getMarketer() {
		return marketer;
	}

	
	public void setMarketer(String marketer) {
		this.marketer = marketer;
	}

	public String getMarketerTicheng() {
		return marketerTicheng;
	}

	public void setMarketerTicheng(String marketerTicheng) {
		this.marketerTicheng = marketerTicheng;
	}
	
	public String getBeforesale1() {
		return beforesale1;
	}
	
	public void setBeforesale1(String beforesale1) {
		this.beforesale1 = beforesale1;
	}
	
	public String getBeforesaleTicheng1() {
		return beforesaleTicheng1;
	}
	
	public void setBeforesaleTicheng1(String beforesaleTicheng1) {
		this.beforesaleTicheng1 = beforesaleTicheng1;
	}

	public String getBeforesale2() {
		return beforesale2;
	}
	
	public void setBeforesale2(String beforesale2) {
		this.beforesale2 = beforesale2;
	}
	
	public String getBeforesaleTicheng2() {
		return beforesaleTicheng2;
	}
	
	public void setBeforesaleTicheng2(String beforesaleTicheng2) {
		this.beforesaleTicheng2 = beforesaleTicheng2;
	}
	
	public String getAftersale() {
		return aftersale;
	}

	public void setAftersale(String aftersale) {
		this.aftersale = aftersale;
	}
	
	public String getAftersaleTicheng() {
		return aftersaleTicheng;
	}
	
	public void setAftersaleTicheng(String aftersaleTicheng) {
		this.aftersaleTicheng = aftersaleTicheng;
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


	public String getCustomerName() {
		return customerName;
	}

	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	public String getPactId() {
		return pactId;
	}
	
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	
	public String getPactName() {
		return pactName;
	}
	
	public void setPactName(String pactName) {
		this.pactName = pactName;
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


	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	public String getLastMoney() {
		return lastMoney;
	}
	
	public void setLastMoney(String lastMoney) {
		this.lastMoney = lastMoney;
	}
	
	/**
	 * 方法描述： 最近一次收款时间
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-4-5 下午2:46:47
	 */
	public String getLastDate() {
		return lastDate;
	}
	
	/**
	 * 方法描述： 最近一次收款时间
	 * @param lastDate
	 * void
	 * @author Cuizk
	 * @date 2016-4-5 下午2:47:05
	 */
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	
	
}
