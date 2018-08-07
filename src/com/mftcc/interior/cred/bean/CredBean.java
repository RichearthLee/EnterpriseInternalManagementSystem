package com.mftcc.interior.cred.bean;

public class CredBean {
	
	private String cusNo;
	private String cusName;//客户姓名
	private String cusPhone;//手机号码
	private String cusIdNumber;//身份证号
	private String cusSort;//客户分类
	private String cusReport;
	
	
	
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getCusIdNumber() {
		return cusIdNumber;
	}
	public void setCusIdNumber(String cusIdNumber) {
		this.cusIdNumber = cusIdNumber;
	}
	public String getCusSort() {
		return cusSort;
	}
	public void setCusSort(String cusSort) {
		this.cusSort = cusSort;
	}
	public String getCusReport() {
		return cusReport;
	}
	public void setCusReport(String cusReport) {
		this.cusReport = cusReport;
	}
	@Override
	public String toString() {
		return "CredBean [cusNo=" + cusNo + ", cusName=" + cusName
				+ ", cusPhone=" + cusPhone + ", cusIdNumber=" + cusIdNumber
				+ ", cusSort=" + cusSort + ", cusReport=" + cusReport + "]";
	}
	
	
}
