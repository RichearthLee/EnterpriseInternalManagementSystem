package com.mftcc.interior.report.bean;

public class PactReportBean {
	private String pactId;//合同编号 
	private String pactName;//合同名称
	private String pactFee;//合同金额
	private String customerMoney;//客户返款金额
	private String productName;//varchar
	private String customerId;//客户编号
	private String customerName;//客户名称
	private String customerLinkman;//客户方联系人
	private String customerLinkmanTitle;//客户方联系人职务
	private String customerPhone;//客户方联系电话
	private String customerEmail;//邮箱
	private String customerProvince;//省
	private String customerCity;//市
	private String customerCountry;//县
	private String customerLocation;//详细地址
	private String paycountYet;//确认支付笔数
	private String payfeeYet;//客户返款金额合计
	private String payPercent;//客户已支付金额所占合同金额百分比
	private String pactState;//合同状态
	private String pactStartDate;//合同开始时间
	private String pactOverDate;//合同结束时间
	private String description;//合同描述
	private String feeDate;//开始收费时间
	private String marketerNo;//客户经理编号
	private String marketerName;//客户经理姓名
	private String brNo1;//经理所属部门
	private String brName1;//部门名称
	private String brNo2;//部门所属分公司/办事处
	private String brName2;//分公司/办事处名称
	private String brProvince;//分公司/办事处所在省
	private String brCity;//分公司/办事处所在市
	private String brCountry;//分公司/办事处所在县
	private String brAddr;//机构（分公司/办事处）地址
	private String brPost;//机构（分公司/办事处）邮编
	private String beforeSale;//售前技术人员id  beforesale
	private String afterSale;//售后人员id  aftersale
	private String focusPerson;//合同关注人员
	private String executePerson;//实施人员id
	private String executeStartDate;//实施开始时间
	private String executeOverDate;//实施结束时间
	private String executeResult;//实施结果
	private String executeFilepath;//
	private String customerResult;//市场反馈结果
	private String invoiceAll;//是否已开具发票？1.是 2.否
	private String invoiceMoneyYet;//已开发票金额
	private String datePercent80;//合同收款比例第一次达到或者超过80%的时间
	private String datePercent100;//合同收款比例第一次达到或者超过100%的时间
	private String registerDate;//登记时间
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
	public String getCustomerMoney() {
		return customerMoney;
	}
	public void setCustomerMoney(String customerMoney) {
		this.customerMoney = customerMoney;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getCustomerLinkman() {
		return customerLinkman;
	}
	public void setCustomerLinkman(String customerLinkman) {
		this.customerLinkman = customerLinkman;
	}
	public String getCustomerLinkmanTitle() {
		return customerLinkmanTitle;
	}
	public void setCustomerLinkmanTitle(String customerLinkmanTitle) {
		this.customerLinkmanTitle = customerLinkmanTitle;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerProvince() {
		return customerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public String getCustomerCountry() {
		return customerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}
	public String getCustomerLocation() {
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}
	public String getPaycountYet() {
		return paycountYet;
	}
	public void setPaycountYet(String paycountYet) {
		this.paycountYet = paycountYet;
	}
	public String getPayfeeYet() {
		return payfeeYet;
	}
	public void setPayfeeYet(String payfeeYet) {
		this.payfeeYet = payfeeYet;
	}
	public String getPayPercent() {
		return payPercent;
	}
	public void setPayPercent(String payPercent) {
		this.payPercent = payPercent;
	}
	public String getPactState() {
		return pactState;
	}
	public void setPactState(String pactState) {
		this.pactState = pactState;
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
	public String getMarketerNo() {
		return marketerNo;
	}
	public void setMarketerNo(String marketerNo) {
		this.marketerNo = marketerNo;
	}
	public String getMarketerName() {
		return marketerName;
	}
	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
	}
	public String getBrNo1() {
		return brNo1;
	}
	public void setBrNo1(String brNo1) {
		this.brNo1 = brNo1;
	}
	public String getBrName1() {
		return brName1;
	}
	public void setBrName1(String brName1) {
		this.brName1 = brName1;
	}
	public String getBrNo2() {
		return brNo2;
	}
	public void setBrNo2(String brNo2) {
		this.brNo2 = brNo2;
	}
	public String getBrName2() {
		return brName2;
	}
	public void setBrName2(String brName2) {
		this.brName2 = brName2;
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
	public String getBrCountry() {
		return brCountry;
	}
	public void setBrCountry(String brCountry) {
		this.brCountry = brCountry;
	}
	public String getBrAddr() {
		return brAddr;
	}
	public void setBrAddr(String brAddr) {
		this.brAddr = brAddr;
	}
	public String getBrPost() {
		return brPost;
	}
	public void setBrPost(String brPost) {
		this.brPost = brPost;
	}
	public String getBeforeSale() {
		return beforeSale;
	}
	public void setBeforeSale(String beforeSale) {
		this.beforeSale = beforeSale;
	}
	public String getAfterSale() {
		return afterSale;
	}
	public void setAfterSale(String afterSale) {
		this.afterSale = afterSale;
	}
	public String getFocusPerson() {
		return focusPerson;
	}
	public void setFocusPerson(String focusPerson) {
		this.focusPerson = focusPerson;
	}
	public String getExecutePerson() {
		return executePerson;
	}
	public void setExecutePerson(String executePerson) {
		this.executePerson = executePerson;
	}
	public String getExecuteStartDate() {
		return executeStartDate;
	}
	public void setExecuteStartDate(String executeStartDate) {
		this.executeStartDate = executeStartDate;
	}
	public String getExecuteOverDate() {
		return executeOverDate;
	}
	public void setExecuteOverDate(String executeOverDate) {
		this.executeOverDate = executeOverDate;
	}
	public String getExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
	public String getExecuteFilepath() {
		return executeFilepath;
	}
	public void setExecuteFilepath(String executeFilepath) {
		this.executeFilepath = executeFilepath;
	}
	public String getCustomerResult() {
		return customerResult;
	}
	public void setCustomerResult(String customerResult) {
		this.customerResult = customerResult;
	}
	public String getInvoiceAll() {
		return invoiceAll;
	}
	public void setInvoiceAll(String invoiceAll) {
		this.invoiceAll = invoiceAll;
	}
	public String getInvoiceMoneyYet() {
		return invoiceMoneyYet;
	}
	public void setInvoiceMoneyYet(String invoiceMoneyYet) {
		this.invoiceMoneyYet = invoiceMoneyYet;
	}
	public String getDatePercent80() {
		return datePercent80;
	}
	public void setDatePercent80(String datePercent80) {
		this.datePercent80 = datePercent80;
	}
	public String getDatePercent100() {
		return datePercent100;
	}
	public void setDatePercent100(String datePercent100) {
		this.datePercent100 = datePercent100;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PactSumBean [pactId=");
		builder.append(pactId);
		builder.append(", pactName=");
		builder.append(pactName);
		builder.append(", pactFee=");
		builder.append(pactFee);
		builder.append(", customerMoney=");
		builder.append(customerMoney);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerType=");
		builder.append(", customerLinkman=");
		builder.append(customerLinkman);
		builder.append(", customerLinkmanTitle=");
		builder.append(customerLinkmanTitle);
		builder.append(", customerPhone=");
		builder.append(customerPhone);
		builder.append(", customerEmail=");
		builder.append(customerEmail);
		builder.append(", customerProvince=");
		builder.append(customerProvince);
		builder.append(", customerCity=");
		builder.append(customerCity);
		builder.append(", customerCountry=");
		builder.append(customerCountry);
		builder.append(", customerLocation=");
		builder.append(customerLocation);
		builder.append(", paycountYet=");
		builder.append(paycountYet);
		builder.append(", payfeeYet=");
		builder.append(payfeeYet);
		builder.append(", payPercent=");
		builder.append(payPercent);
		builder.append(", pactState=");
		builder.append(pactState);
		builder.append(", pactStartDate=");
		builder.append(pactStartDate);
		builder.append(", pactOverDate=");
		builder.append(pactOverDate);
		builder.append(", description=");
		builder.append(description);
		builder.append(", feeDate=");
		builder.append(feeDate);
		builder.append(", marketerNo=");
		builder.append(marketerNo);
		builder.append(", marketerName=");
		builder.append(marketerName);
		builder.append(", brNo1=");
		builder.append(brNo1);
		builder.append(", brName1=");
		builder.append(brName1);
		builder.append(", brNo2=");
		builder.append(brNo2);
		builder.append(", brName2=");
		builder.append(brName2);
		builder.append(", brProvince=");
		builder.append(brProvince);
		builder.append(", brCity=");
		builder.append(brCity);
		builder.append(", brCountry=");
		builder.append(brCountry);
		builder.append(", brAddr=");
		builder.append(brAddr);
		builder.append(", brPost=");
		builder.append(brPost);
		builder.append(", beforeSale=");
		builder.append(beforeSale);
		builder.append(", afterSale=");
		builder.append(afterSale);
		builder.append(", focusPerson=");
		builder.append(focusPerson);
		builder.append(", executePerson=");
		builder.append(executePerson);
		builder.append(", executeStartDate=");
		builder.append(executeStartDate);
		builder.append(", executeOverDate=");
		builder.append(executeOverDate);
		builder.append(", executeResult=");
		builder.append(executeResult);
		builder.append(", executeFilepath=");
		builder.append(executeFilepath);
		builder.append(", customerResult=");
		builder.append(customerResult);
		builder.append(", invoiceAll=");
		builder.append(invoiceAll);
		builder.append(", invoiceMoneyYet=");
		builder.append(invoiceMoneyYet);
		builder.append(", datePercent80=");
		builder.append(datePercent80);
		builder.append(", datePercent100=");
		builder.append(datePercent100);
		builder.append(", registerDate=");
		builder.append(registerDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
