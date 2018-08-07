package com.mftcc.interior.report.bean;

public class LeaveDaysFinalBean {
	
	String op_no;//员工号
	String op_name;//员工姓名
	String br_no;//部门编号
	String br_name;//部门名称 
//	String dic_key;//请假类型key
//	String dic_value;//请假类型value
//	String sumdays;//请假天数
	String mobile;//电话
	String yearmonth;//请假年月
	double casual;//事假天数
	double marriage;//婚假天数
	double bereavement;//丧假天数
	double annual;//带薪年假天数
	double paternity;//陪产假天数
	double sick;//病假天数
	double maternity;//产假天数
	double exchange;//倒休假天数
	double sumdays;//有效请假天数
	
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public double getCasual() {
		return casual;
	}
	public void setCasual(double casual) {
		this.casual = casual;
	}
	public double getMarriage() {
		return marriage;
	}
	public void setMarriage(double marriage) {
		this.marriage = marriage;
	}
	public double getBereavement() {
		return bereavement;
	}
	public void setBereavement(double bereavement) {
		this.bereavement = bereavement;
	}
	public double getAnnual() {
		return annual;
	}
	public void setAnnual(double annual) {
		this.annual = annual;
	}
	public double getPaternity() {
		return paternity;
	}
	public void setPaternity(double paternity) {
		this.paternity = paternity;
	}
	public double getSick() {
		return sick;
	}
	public void setSick(double sick) {
		this.sick = sick;
	}
	public double getMaternity() {
		return maternity;
	}
	public void setMaternity(double maternity) {
		this.maternity = maternity;
	}
	public double getExchange() {
		return exchange;
	}
	public void setExchange(double exchange) {
		this.exchange = exchange;
	}
	public double getSumdays() {
		return sumdays;
	}
	public void setSumdays(double sumdays) {
		this.sumdays = sumdays;
	}
	public String getOp_no() {
		return op_no;
	}
	public void setOp_no(String op_no) {
		this.op_no = op_no;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String br_no) {
		this.br_no = br_no;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String br_name) {
		this.br_name = br_name;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeaveDaysFinalBean [op_no=");
		builder.append(op_no);
		builder.append(", op_name=");
		builder.append(op_name);
		builder.append(", br_no=");
		builder.append(br_no);
		builder.append(", br_name=");
		builder.append(br_name);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", yearmonth=");
		builder.append(yearmonth);
		builder.append(", casual=");
		builder.append(casual);
		builder.append(", marriage=");
		builder.append(marriage);
		builder.append(", bereavement=");
		builder.append(bereavement);
		builder.append(", annual=");
		builder.append(annual);
		builder.append(", paternity=");
		builder.append(paternity);
		builder.append(", sick=");
		builder.append(sick);
		builder.append(", maternity=");
		builder.append(maternity);
		builder.append(", exchange=");
		builder.append(exchange);
		builder.append("]");
		return builder.toString();
	}
	 
	
	
}
