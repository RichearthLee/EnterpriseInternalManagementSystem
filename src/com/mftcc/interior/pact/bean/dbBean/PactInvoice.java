package com.mftcc.interior.pact.bean.dbBean;

public class PactInvoice {

	public PactInvoice() {
	}
	
	private String id;
	
	
	//
	private String pactId;
	//
	private String customerName;
	// 发票金额
	private String invoiceMoney;
	// 邮寄地址
	private String location;
	// 联系人
	private String linkman;
	// 联系电话
	private String phone;
	// 发票理由
	private String reasion;
	// 已开具发票金额
	private String invoiceMoneyYet;
	// 合同名称
	private String pactName;
	//
	private String pactFee;
	// 发票抬头
	private String invoiceTitle;
	
	private  String marketName;
	//
	private  String marketDate;
	//
	private  String confirmName;
	//
	private  String confirmDate;
	private  String marketNo;
	private  String confirmNo;
	private String payOrNot;
	private String invoiceCompany;
	private String customerId;
	private String invoicePercent;
	
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}


	public String getPayOrNot() {
		return payOrNot;
	}



	
	public void setPayOrNot(String payOrNot) {
		this.payOrNot = payOrNot;
	}



	
	public String getInvoiceCompany() {
		return invoiceCompany;
	}



	
	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}



	
	public String getCustomerId() {
		return customerId;
	}



	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	
	public String getInvoicePercent() {
		return invoicePercent;
	}



	
	public void setInvoicePercent(String invoicePercent) {
		this.invoicePercent = invoicePercent;
	}



	
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	
	
	public String getId() {
		return id;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getMarketName() {
		return marketName;
	}



	
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}



	
	public String getConfirmName() {
		return confirmName;
	}



	
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}



	
	public String getMarketNo() {
		return marketNo;
	}



	
	public void setMarketNo(String marketNo) {
		this.marketNo = marketNo;
	}



	
	public String getConfirmNo() {
		return confirmNo;
	}



	
	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}

	

	
	public String getMarketDate() {
		return marketDate;
	}

	
	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}

	
	

	
	public String getConfirmDate() {
		return confirmDate;
	}

	
	public void setComfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	/**
  *
  */
	public String getPactId() {
		return pactId;
	}

	/**
*	
  */
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	/**
  *
  */
	public String getCustomerName() {
		return customerName;
	}

	/**
*	
  */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 发票金额
	 */
	public String getInvoiceMoney() {
		return invoiceMoney;
	}

	/**
	 * 发票金额
	 */
	public void setInvoiceMoney(String invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	/**
	 * 邮寄地址
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 邮寄地址
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 联系人
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * 联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * 联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 发票理由
	 */
	public String getReasion() {
		return reasion;
	}

	/**
	 * 发票理由
	 */
	public void setReasion(String reasion) {
		this.reasion = reasion;
	}

	/**
	 * 已开具发票金额
	 */
	public String getInvoiceMoneyYet() {
		return invoiceMoneyYet;
	}

	/**
	 * 已开具发票金额
	 */
	public void setInvoiceMoneyYet(String invoiceMoneyYet) {
		this.invoiceMoneyYet = invoiceMoneyYet;
	}

	/**
	 * 合同名称
	 */
	public String getPactName() {
		return pactName;
	}

	/**
	 * 合同名称
	 */
	public void setPactName(String pactName) {
		this.pactName = pactName;
	}

	/**
  *
  */
	public String getPactFee() {
		return pactFee;
	}

	/**
*	
  */
	public void setPactFee(String pactFee) {
		this.pactFee = pactFee;
	}

	/**
	 * 发票抬头
	 */
	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	/**
	 * 发票抬头
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
}