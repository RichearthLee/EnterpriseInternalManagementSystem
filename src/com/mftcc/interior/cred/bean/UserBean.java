/**
 * Copyright (C) DXHM 版权所有
 * 文件名： UserBean.java
 * 包名： com.mftcc.login.bean
 * 说明： 
 * @author LiuYF
 * @date 2015-11-12 下午4:48:42
 * @version V1.0
 */
package com.mftcc.interior.cred.bean;

/**
 * 类名： UserBean <br>
 * 描述：
 * @author LiuYF
 * @date 2015-11-12 下午4:48:42
 */
public class UserBean {

	// 应限定6-18位（或系统分配）
	private String userNo;
	//
	private String name;
	//
	private String password;
	//
	private String phone;
	//
	private String email;
	// 每个角色3位，逗号间隔，最多5个角色。
	private String roles;
	//
	private String department;
	//
	private String useFlag;

	/**
	 * 应限定6-18位（或系统分配）
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * 应限定6-18位（或系统分配）
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	  *
	  */
	public String getName() {
		return name;
	}

	/**
	*	
	  */
	public void setName(String name) {
		this.name = name;
	}

	/**
	  *
	  */
	public String getPassword() {
		return password;
	}

	/**
	*	
	  */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	  *
	  */
	public String getPhone() {
		return phone;
	}

	/**
	*	
	  */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	  *
	  */
	public String getEmail() {
		return email;
	}

	/**
	*	
	  */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 每个角色3位，逗号间隔，最多5个角色。
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * 每个角色3位，逗号间隔，最多5个角色。
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	  *
	  */
	public String getDepartment() {
		return department;
	}

	/**
	*	
	  */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	  *
	  */
	public String getUseFlag() {
		return useFlag;
	}

	/**
	*	
	  */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	@Override
	public String toString() {
		return "用户：" + name +"/"+ userNo +"/"+ phone +"/"+ roles;
	}
	
}
