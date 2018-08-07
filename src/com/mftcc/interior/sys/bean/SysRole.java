package com.mftcc.interior.sys.bean;
/**
* Title: SysRole.java
* Description:
* @author： 
* @Wed  
* @version：1.0
**/

public class SysRole  {
	private String  roleNo;//角色编号
	private String  roleName;//角色名称
	private String  roleType;//角色类型
	private String  roleLev;//角色级别
	private String  roleSts;//角色级别


	/**
	 * @return 角色编号
	 */
	 public String getRoleNo() {
	 	return roleNo;
	 }
	 /**
	 * @设置 角色编号
	 * @param roleNo
	 */
	 public void setRoleNo(String roleNo) {
	 	this.roleNo = roleNo;
	 }
	/**
	 * @return 角色名称
	 */
	 public String getRoleName() {
	 	return roleName;
	 }
	 /**
	 * @设置 角色名称
	 * @param roleName
	 */
	 public void setRoleName(String roleName) {
	 	this.roleName = roleName;
	 }
	/**
	 * @return 角色类型
	 */
	 public String getRoleType() {
	 	return roleType;
	 }
	 /**
	 * @设置 角色类型
	 * @param roleType
	 */
	 public void setRoleType(String roleType) {
	 	this.roleType = roleType;
	 }
	/**
	 * @return 角色级别
	 */
	 public String getRoleLev() {
	 	return roleLev;
	 }
	 /**
	 * @设置 角色级别
	 * @param roleLev
	 */
	 public void setRoleLev(String roleLev) {
	 	this.roleLev = roleLev;
	 }
	/**
	 * @return the roleSts
	 */
	public String getRoleSts() {
		return roleSts;
	}
	/**
	 * @param roleSts the roleSts to set
	 */
	public void setRoleSts(String roleSts) {
		this.roleSts = roleSts;
	}
}
