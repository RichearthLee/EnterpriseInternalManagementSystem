package com.mftcc.interior.report.bean;
/**
 * 细化每条请假信息至每天请假小时数
 * @author Administrator
 *
 */
public class LeaveDaysBean {

	private String op_no;//请假人编号
	private String op_name;//姓名
	private String br_no;//部门编号
	private String br_name;//部门名称
	private String mobile;//手机
	private String leave_no;//请假编号
	private String leave_type;//请假类型
	private String dic_value;//请假类型
	private String dateoftoday;//一条请假类型中的每天日期
	private double hours;//每天请假小时
	private String flag;//判断是否是节假日的标志
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLeave_no() {
		return leave_no;
	}
	public void setLeave_no(String leave_no) {
		this.leave_no = leave_no;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public String getDic_value() {
		return dic_value;
	}
	public void setDic_value(String dic_value) {
		this.dic_value = dic_value;
	}
	public String getDateoftoday() {
		return dateoftoday;
	}
	public void setDateoftoday(String dateoftoday) {
		this.dateoftoday = dateoftoday;
	}
	
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeaveDaysBean [op_no=");
		builder.append(op_no);
		builder.append(", op_name=");
		builder.append(op_name);
		builder.append(", br_no=");
		builder.append(br_no);
		builder.append(", br_name=");
		builder.append(br_name);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", leave_no=");
		builder.append(leave_no);
		builder.append(", leave_type=");
		builder.append(leave_type);
		builder.append(", dic_value=");
		builder.append(dic_value);
		builder.append(", dateoftoday=");
		builder.append(dateoftoday);
		builder.append(", hours=");
		builder.append(hours);
		builder.append("]");
		return builder.toString();
	}
	
	 
}
