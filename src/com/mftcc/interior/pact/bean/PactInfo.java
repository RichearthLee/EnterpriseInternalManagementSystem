
package com.mftcc.interior.pact.bean;


public class PactInfo {
	public PactInfo(){}
	
	private  String pactId;
	
	private String pactName;
	
	

	//
	private  String pactFee;
	// 客户返款金额
	private  String customerMoney;
	//
	private  String productName;
	
	private  String customerId;
	//方便根据客户名称查询相关合同
	private  String customerName;
	//
	private  String paycountYet;
	//
	private  String payfeeYet;
	//百分比
	private  String paypercent;
	//1. 新增合同     2. 可以实施            3. 实施完成            4. 二批款已收            5. 尾款已收    6.收费服务
	private  String pactState;
	//
	private  String pactStartDate;
	private  String pactOverDate;
	//
	private  String description;
	//
	private  String feeDate;
	//
	private  String marketerId;
	//
	private  String beforesale;
	//
	private  String aftersale;
	//
	private  String focusPersion;
	//
	private  String executePersion;
	//
	private  String executeStartDate;
	private  String executeOverDate;
	//
	private  String executeResult;
	
	private  String executeFilepath;
	//
	private  String customerResult;
	//1 是            2 否
	private  String invoiceAll;
	//已开具发票金额
	private String invoiceMoneyYet;
	
	private String date80Percent;
	
	private String date100Percent;
	 
	private String registerDate;
	private String flag;
	


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getDate100Percent() {
		return date100Percent;
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


	public void setDate100Percent(String date100Percent) {
		this.date100Percent = date100Percent;
	}



	public String getRegisterDate() {
		return registerDate;
	}


	
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}


	public String getDate80Percent() {
		return date80Percent;
	}

	
	public void setDate80Percent(String date80Percent) {
		this.date80Percent = date80Percent;
	}

	public String getInvoiceMoneyYet() {
		return invoiceMoneyYet;
	}
	
	public void setInvoiceMoneyYet(String invoiceMoneyYet) {
		this.invoiceMoneyYet = invoiceMoneyYet;
	}
	/**
	  *
	  */
	  public String getPactId(){
	    return pactId;
	  }
	  /**
	*	
	  */
	  public void setPactId(String pactId){
	    this.pactId=pactId;
	  }
	  
	  public String getPactName() {
			return pactName;
		}
		
		public void setPactName(String pactName) {
			this.pactName = pactName;
		}
	  /**
	  *
	  */
	  public String getPactFee(){
	    return pactFee;
	  }
	  /**
	*	
	  */
	  public void setPactFee(String pactFee){
	    this.pactFee=pactFee;
	  }
	/**
	 * 客户返款金额
	 * @return the customerMoney 客户返款金额
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


	/**
	  *
	  */
	  public String getProductName(){
	    return productName;
	  }
	  /**
	*	
	  */
	  public void setProductName(String productName){
	    this.productName=productName;
	  }
	  
	  public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
	  /**
	  *方便根据客户名称查询相关合同
	  */
	  public String getCustomerName(){
	    return customerName;
	  }
	  /**
	*	方便根据客户名称查询相关合同
	  */
	  public void setCustomerName(String customerName){
	    this.customerName=customerName;
	  }
	  /**
	  *
	  */
	  public String getPaycountYet(){
	    return paycountYet;
	  }
	  /**
	*	
	  */
	  public void setPaycountYet(String paycountYet){
	    this.paycountYet=paycountYet;
	  }
	  /**
	  *
	  */
	  public String getPayfeeYet(){
	    return payfeeYet;
	  }
	  /**
	*	
	  */
	  public void setPayfeeYet(String payfeeYet){
	    this.payfeeYet=payfeeYet;
	  }
	  /**
	  *百分比
	  */
	  public String getPaypercent(){
	    return paypercent;
	  }
	  /**
	*	百分比
	  */
	  public void setPaypercent(String paypercent){
	    this.paypercent=paypercent;
	  }
	  /**
	  *1. 新增合同   2. 可以实施            3. 实施完成            4. 二批款已收            5. 尾款已收    6.收费服务
	  */
	  public String getPactState(){
	    return pactState;
	  }
	  /**
	*	1. 新增合同
            2. 可以实施            3. 实施完成            4. 二批款已收            5. 尾款已收    6.收费服务
	  */
	  public void setPactState(String pactState){
	    this.pactState=pactState;
	  }
	  /**
	  *
	  */
	
	  /**
	  *
	  */
	  public String getDescription(){
	    return description;
	  }
	  /**
	*	
	  */
	  public void setDescription(String description){
	    this.description=description;
	  }
	  /**
	  *
	  */
	  public String getFeeDate(){
	    return feeDate;
	  }
	  /**
	*	
	  */
	  public void setFeeDate(String feeDate){
	    this.feeDate=feeDate;
	  }
	  /**
	  *
	  */
	  public String getMarketerId(){
	    return marketerId;
	  }
	  /**
	*	
	  */
	  public void setMarketerId(String marketerId){
	    this.marketerId=marketerId;
	  }
	  /**
	  *
	  */
	  public String getBeforesale(){
	    return beforesale;
	  }
	  /**
	*	
	  */
	  public void setBeforesale(String beforesale){
	    this.beforesale=beforesale;
	  }
	  /**
	  *
	  */
	  public String getAftersale(){
	    return aftersale;
	  }
	  /**
	*	
	  */
	  public void setAftersale(String aftersale){
	    this.aftersale=aftersale;
	  }
	  /**
	  *
	  */
	  public String getFocusPersion(){
	    return focusPersion;
	  }
	  /**
	*	
	  */
	  public void setFocusPersion(String focusPersion){
	    this.focusPersion=focusPersion;
	  }
	  /**
	  *
	  */
	  public String getExecutePersion(){
	    return executePersion;
	  }
	  /**
	*	
	  */
	  public void setExecutePersion(String executePersion){
	    this.executePersion=executePersion;
	  }
	  /**
	  *
	  */
	 
	  /**
	  *
	  */
	  public String getExecuteResult(){
	    return executeResult;
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


	/**
	*	
	  */
	  public void setExecuteResult(String executeResult){
	    this.executeResult=executeResult;
	  }
	  /**
	  *
	  */
	  public String getCustomerResult(){
	    return customerResult;
	  }
	  /**
	*	
	  */
	  public void setCustomerResult(String customerResult){
	    this.customerResult=customerResult;
	  }
	  /**
	  *1 是            2 否
	  */
	  public String getInvoiceAll(){
	    return invoiceAll;
	  }
	  /**
	*	1 是            2 否
	  */
	  public void setInvoiceAll(String invoiceAll){
	    this.invoiceAll=invoiceAll;
	  }
	
	  public void setDefault(Pact_Customer pactAndCustomer ){
		  paycountYet = "0";
		  payfeeYet = "0";
		  paypercent = "0";
		  pactState = "1";
		  invoiceAll = "2";
		  aftersale = "";
		  customerResult = "";
		  invoiceMoneyYet = "0";
		  
		 // this.pactId = pactAndCustomer.getPactId();
		  this.pactId = pactAndCustomer.getPactId();
		  this.pactFee = pactAndCustomer.getPactFee();
		  this.customerMoney = pactAndCustomer.getCustomerMoney();
		  this.productName = pactAndCustomer.getProductName();
		  this.customerId = pactAndCustomer.getCustomerId();
		  this.customerName = pactAndCustomer.getCustomerName();
		  this.pactStartDate = pactAndCustomer.getPactStartDate();
		  this.pactOverDate = pactAndCustomer.getPactOverDate();
		  this.description = pactAndCustomer.getDescription();
		  this.feeDate = pactAndCustomer.getFeeDate();
		  this.marketerId = pactAndCustomer.getMarketerId();
		  this.beforesale = pactAndCustomer.getBeforesale();
		  this.focusPersion = pactAndCustomer.getFocusPersion();
		  this.pactName = pactAndCustomer.getPactName();
		  this.registerDate = pactAndCustomer.getEntryDate();
		  
	  }
	public String getExecuteFilepath() {
		return executeFilepath;
	}
	public void setExecuteFilepath(String executeFilepath) {
		this.executeFilepath = executeFilepath;
	}

}
