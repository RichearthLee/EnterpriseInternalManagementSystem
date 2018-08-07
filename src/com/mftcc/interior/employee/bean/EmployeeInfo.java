package com.mftcc.interior.employee.bean;

public class EmployeeInfo {
	private String EmployeeId; 
	private String EmployeeName; 
	private String EmployeePhone; 
	private String EmployeeEmail;
	private String EmployeeDepartment; 
	private String EmployeeDepartmentId; 
	private String EmployeeMarks;
	public String getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public String getEmployeePhone() {
		return EmployeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		EmployeePhone = employeePhone;
	}
	public String getEmployeeEmail() {
		return EmployeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		EmployeeEmail = employeeEmail;
	}
	public String getEmployeeDepartment() {
		return EmployeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		EmployeeDepartment = employeeDepartment;
	}
	public String getEmployeeDepartmentId() {
		return EmployeeDepartmentId;
	}
	public void setEmployeeDepartmentId(String employeeDepartmentId) {
		EmployeeDepartmentId = employeeDepartmentId;
	}
	public String getEmployeeMarks() {
		return EmployeeMarks;
	}
	public void setEmployeeMarks(String employeeMarks) {
		EmployeeMarks = employeeMarks;
	}
	public EmployeeInfo(String employeeId, String employeeName,
			String employeePhone, String employeeEmail,
			String employeeDepartment, String employeeDepartmentId,
			String employeeMarks) {
		super();
		EmployeeId = employeeId;
		EmployeeName = employeeName;
		EmployeePhone = employeePhone;
		EmployeeEmail = employeeEmail;
		EmployeeDepartment = employeeDepartment;
		EmployeeDepartmentId = employeeDepartmentId;
		EmployeeMarks = employeeMarks;
	} 
	
	public EmployeeInfo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmployeeInfo [EmployeeId=" + EmployeeId + ", EmployeeName="
				+ EmployeeName + ", EmployeePhone=" + EmployeePhone
				+ ", EmployeeEmail=" + EmployeeEmail + ", EmployeeDepartment="
				+ EmployeeDepartment + ", EmployeeDepartmentId="
				+ EmployeeDepartmentId + ", EmployeeMarks=" + EmployeeMarks
				+ "]";
	}



}
