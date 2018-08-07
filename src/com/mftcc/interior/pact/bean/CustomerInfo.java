
package com.mftcc.interior.pact.bean;


public class CustomerInfo {
		public CustomerInfo(){}
		//
		private  String customerId;
		//
		private  String customerName;
		private  String customerType;
		private  String province;
		private  String city;
		private  String area;
				//
		private  String location;
		//
		private  String linkman;
		
		private String linkmanTitle;
		
		
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
		  /**
		  *
		  */
		  public String getCustomerId(){
		    return customerId;
		  }
		  /**
		*	
		  */
		  public void setCustomerId(String customerId){
		    this.customerId=customerId;
		  }
		  /**
		  *
		  */public String getCustomerType() {
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

		  public String getCustomerName(){
		    return customerName;
		  }
		  /**
		*	
		  */
		  public void setCustomerName(String customerName){
		    this.customerName=customerName;
		  }
		  /**
		  *
		  */
		  public String getLocation(){
		    return location;
		  }
		  /**
		*	
		  */
		  public void setLocation(String location){
		    this.location=location;
		  }
		  /**
		  *
		  */
		  public String getLinkman(){
		    return linkman;
		  }
		  /**
		*	
		  */
		  public void setLinkman(String linkman){
		    this.linkman=linkman;
		  }
		  public String getLinkmanTitle() {
				return linkmanTitle;
			}
			
			public void setLinkmanTitle(String linkmanTitle) {
				this.linkmanTitle = linkmanTitle;
			}
		  /**
		  *
		  */
		  public String getTetephone(){
		    return tetephone;
		  }
		  /**
		*	
		  */
		  public void setTetephone(String tetephone){
		    this.tetephone=tetephone;
		  }
		  /**
		  *
		  */
		  public String getMobilephone(){
		    return mobilephone;
		  }
		  /**
		*	
		  */
		  public void setMobilephone(String mobilephone){
		    this.mobilephone=mobilephone;
		  }
		  /**
		  *
		  */
		  public String getEmail(){
		    return email;
		  }
		  /**
		*	
		  */
		  public void setEmail(String email){
		    this.email=email;
		  }
		  /**
		  *
		  */
		  public String getEntryDate(){
		    return entryDate;
		  }
		  /**
		*	
		  */
		  public void setEntryDate(String entryDate){
		    this.entryDate=entryDate;
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
		  
		  public void setDefault(Pact_Customer pactCustomer){
			  this.customerName = pactCustomer.getCustomerName();
			  this.customerType=pactCustomer.getCustomerType();
			  this.province=pactCustomer.getProvince();
			  this.city=pactCustomer.getCity();
			  this.area=pactCustomer.getArea();
			  this.location = pactCustomer.getLocation();
			  this.linkman = pactCustomer.getLinkman();
			  this.linkmanTitle = pactCustomer.getLinkmanTitle();
			  this.tetephone = pactCustomer.getTetephone();
			  this.mobilephone = pactCustomer.getMobilephone();
			  this.entryDate = pactCustomer.getEntryDate();
			  this.marketerId = pactCustomer.getMarketerId();
			  this.email = pactCustomer.getEmail();
		  }
}
