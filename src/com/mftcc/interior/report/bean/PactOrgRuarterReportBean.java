package com.mftcc.interior.report.bean;
//部门月合同统计
public class PactOrgRuarterReportBean {
	private String brNo;
	private String brName;
	private String year;
	private String month;
	private String yearmonth;//年月
	private String fee;//月总额度
	private String fee_growth;//较上月同比增长
	private String count;//月数量
	private String count_growth;//月数量同比增长
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getFee_growth() {
		return fee_growth;
	}
	public void setFee_growth(String fee_growth) {
		this.fee_growth = fee_growth;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCount_growth() {
		return count_growth;
	}
	public void setCount_growth(String count_growth) {
		this.count_growth = count_growth;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PactOrgRuarterReportBean [brNo=");
		builder.append(brNo);
		builder.append(", brName=");
		builder.append(brName);
		builder.append(", year=");
		builder.append(year);
		builder.append(", month=");
		builder.append(month);
		builder.append(", yearmonth=");
		builder.append(yearmonth);
		builder.append(", fee=");
		builder.append(fee);
		builder.append(", fee_growth=");
		builder.append(fee_growth);
		builder.append(", count=");
		builder.append(count);
		builder.append(", count_growth=");
		builder.append(count_growth);
		builder.append("]");
		return builder.toString();
	}
	
}
