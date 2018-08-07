
package com.mftcc.interior.pact.bean;


public class PaymentPlan {
		public PaymentPlan(){}	
		//
		private  String pactId;
		//
		private  String paymentCount;
		//
		private  String paymentPercent;
		//
		private  String paymentStartDate;
		//
		private  String paymentDay;
		//1 未收   2 已收  3 部分收取
		private  String paymentState;
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
		  public String getPaymentCount(){
		    return paymentCount;
		  }
		  /**
		*	
		  */
		  public void setPaymentCount(String paymentCount){
		    this.paymentCount=paymentCount;
		  }
		  /**
		  *
		  */
		  public String getPaymentPercent(){
		    return paymentPercent;
		  }
		  /**
		*	
		  */
		  public void setPaymentPercent(String paymentPercent){
		    this.paymentPercent=paymentPercent;
		  }
		  /**
		  *
		  */
		  public String getPaymentStartDate(){
		    return paymentStartDate;
		  }
		  /**
		*	
		  */
		  public void setPaymentStartDate(String paymentStartDate){
		    this.paymentStartDate=paymentStartDate;
		  }
		  /**
		  *
		  */
		  public String getPaymentDay(){
		    return paymentDay;
		  }
		  /**
		*	
		  */
		  public void setPaymentDay(String paymentDay){
		    this.paymentDay=paymentDay;
		  }

		  /**
		  *1 未收   2 已收  3 部分收取
		  */
		  public String getPaymentState(){
		    return paymentState;
		  }
		  /**
		*	1 未收   2 已收  3 部分收取
		  */
		  public void setPaymentState(String paymentState){
		    this.paymentState = paymentState;
		  }
		
}
