
package com.mftcc.interior.pact.bean;


public class PayProgressInfo {

		public PayProgressInfo(){}
		//
		private  String pactId;
		//
		private  String payyet;
		//
		private  String customerName;
		//
		private  String productName;
		/* 市场实际收入金额 
		   * 这个字段好像没啥用了。提成计算用的是即时相减计算的。
		   * 20171010
		   */
		private  String marketMoney;
		// 本次收取金额
		private  String proceedMoney;
		// 客户返款金额
		private  String customerMoney;
		//
		private  String marketDate;
		//
		private  String marketerId;
		
		private String description;
		//1 款项到账记录            2 客户付款凭证 ，暂不用
		private  String financialtype;
		//
		private  String receiptPath;
		//，暂不用
		private  String financialMoney;
		//1 确认            2 不确认
		private  String financialResult;
		//
		private  String financialDate;
		//
		private  String fincalpersion;
		//是否开具发票    1此次收款的发票    2 未开发票  3开合同全额发票
		private  String invoice;
		//是否全额发票     1 是            2 否 ，暂不用
		private  String invoiceAll;
		//发票额，暂不用
		private  String invoiceMoney;
		//发票号，暂不用
		private  String invoiceId;
		//汇款日期		   
		private String remitDate;
		//汇款银行网点
		private String remitBank;
		//汇款人
		private String remitPersion;
		
		private String payPercentNow;
		
		
		public String getPayPercentNow() {
			return payPercentNow;
		}

		
		public void setPayPercentNow(String payPercentNow) {
			this.payPercentNow = payPercentNow;
		}

		public String getRemitDate() {
			return remitDate;
		}
		
		public void setRemitDate(String remitDate) {
			this.remitDate = remitDate;
		}
		
		public String getRemitBank() {
			return remitBank;
		}
		
		public void setRemitBank(String remitBank) {
			this.remitBank = remitBank;
		}
		
		public String getRemitPersion() {
			return remitPersion;
		}
		
		public void setRemitPersion(String remitPersion) {
			this.remitPersion = remitPersion;
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
		  /**
		  *
		  */
		  public String getPayyet(){
		    return payyet;
		  }
		  /**
		*	
		  */
		  public void setPayyet(String payyet){
		    this.payyet=payyet;
		  }
		  /**
		  *
		  */
		  public String getMarketMoney(){
		    return marketMoney;
		  }
		  /**
		   * 这个字段好像没啥用了。
		   * 20171010
		  */
		  public void setMarketMoney(String marketMoney){
		    this.marketMoney=marketMoney;
		  }
		  /**
		 * @return the proceedMoney
		 */
		public String getProceedMoney() {
			return proceedMoney;
		}


		/**
		 * @param proceedMoney the proceedMoney to set
		 */
		public void setProceedMoney(String proceedMoney) {
			this.proceedMoney = proceedMoney;
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


		/**
		  *
		  */
		  public String getMarketDate(){
		    return marketDate;
		  }
		  /**
		*	
		  */
		  public void setMarketDate(String marketDate){
		    this.marketDate=marketDate;
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
		  *1 款项到账记录            2 客户付款凭证
		  */
		  public String getFinancialtype(){
		    return financialtype;
		  }
		  /**
		*	1 款项到账记录            2 客户付款凭证
		  */
		  public void setFinancialtype(String financialtype){
		    this.financialtype=financialtype;
		  }
		  /**
		  *
		  */
		  public String getReceiptPath(){
		    return receiptPath;
		  }
		  /**
		*	
		  */
		  public void setReceiptPath(String receiptPath){
		    this.receiptPath=receiptPath;
		  }
		  /**
		  *
		  */
		  public String getFinancialMoney(){
		    return financialMoney;
		  }
		  /**
		*	
		  */
		  public void setFinancialMoney(String financialMoney){
		    this.financialMoney=financialMoney;
		  }
		  /**
		  *1 确认            2 不确认
		  */
		  public String getFinancialResult(){
		    return financialResult;
		  }
		  /**
		*	1 确认            2 不确认
		  */
		  public void setFinancialResult(String financialResult){
		    this.financialResult=financialResult;
		  }
		  /**
		  *
		  */
		  public String getFinancialDate(){
		    return financialDate;
		  }
		  /**
		*	
		  */
		  public void setFinancialDate(String financialDate){
		    this.financialDate=financialDate;
		  }
		  /**
		  *
		  */
		  public String getFincalpersion(){
		    return fincalpersion;
		  }
		  /**
		*	
		  */
		  public void setFincalpersion(String fincalpersion){
		    this.fincalpersion=fincalpersion;
		  }
		  /**
		  *1此次收款的发票    2 未开发票  3开合同全额发票
		  */
		  public String getInvoice(){
		    return invoice;
		  }
		  /**
		*	 1此次收款的发票    2 未开发票  3开合同全额发票
		  */
		  public void setInvoice(String invoice){
		    this.invoice=invoice;
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
		  /**
		  *
		  */
		  public String getInvoiceMoney(){
		    return invoiceMoney;
		  }
		  /**
		*	
		  */
		  public void setInvoiceMoney(String invoiceMoney){
		    this.invoiceMoney=invoiceMoney;
		  }
		  /**
		  *
		  */
		  public String getInvoiceId(){
		    return invoiceId;
		  }
		  /**
		*	
		  */
		  public void setInvoiceId(String invoiceId){
		    this.invoiceId=invoiceId;
		  }
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String prouctName) {
			this.productName = prouctName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
}
