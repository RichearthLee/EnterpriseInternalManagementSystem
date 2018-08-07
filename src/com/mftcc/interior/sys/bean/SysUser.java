package com.mftcc.interior.sys.bean;

import java.util.Arrays;

/**
* Title: SysUser.java
* Description:
* @author：@沈帅虎
* @see
* @version：1.0
**/
public class SysUser {
	private String OpNo;//操作员登录号
	private String opName;//操作员名称
	private String loginUser;//登录账号
	private String password;//密码
	private String position;//职位
	private String sex;//性别
	private String birthday;//生日
	private String mobile;//手机
	private String email;//邮箱
	private String lastModDate;//上次密码修改日期
	private String idNo;//身份证
	private String opSts;//状态
	private String brNo;//机构号
	private String roleNo;//角色号
	private String roleName;//角色
	private String ifUploadHead;//是否上传头像
	private String headImg;//头像名称
	private String brName;//部门名称
	private String homePage;//首页参数，1为个人中心，2为待办任务
	private String userNo;//员工编号
	private String roleDataType;//数据权限类型1登记人2本机构3本机构及其向下4客户经理5上级机构及其向下9	查看全部
	private String buildInFlag;//是否内置账户（允许访问开发者配置页面，不允许删除）
	
	private String [] roleNoArray;//用于查询
	private String [] brNoArray;//用于查询

	

	@Override
	public String toString() {
		return "SysUser [OpNo=" + OpNo + ", opName=" + opName + ", loginUser="
				+ loginUser + ", password=" + password + ", position="
				+ position + ", sex=" + sex + ", birthday=" + birthday
				+ ", mobile=" + mobile + ", email=" + email + ", lastModDate="
				+ lastModDate + ", idNo=" + idNo + ", opSts=" + opSts
				+ ", brNo=" + brNo + ", roleNo=" + roleNo + ", roleName="
				+ roleName + ", ifUploadHead=" + ifUploadHead + ", headImg="
				+ headImg + ", brName=" + brName + ", homePage=" + homePage
				+ ", userNo=" + userNo + ", roleDataType=" + roleDataType
				+ ", buildInFlag=" + buildInFlag + ", roleNoArray="
				+ Arrays.toString(roleNoArray) + ", brNoArray="
				+ Arrays.toString(brNoArray) + "]";
	}
	public String getOpNo() {
		return OpNo;
	}
	public void setOpNo(String opNo) {
		OpNo = opNo;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getOpSts() {
		return opSts;
	}
	public void setOpSts(String opSts) {
		this.opSts = opSts;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIfUploadHead() {
		return ifUploadHead;
	}
	public void setIfUploadHead(String ifUploadHead) {
		this.ifUploadHead = ifUploadHead;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getRoleDataType() {
		return roleDataType;
	}
	public void setRoleDateType(String roleDataType) {
		this.roleDataType = roleDataType;
	}
	public String getBuildInFlag() {
		return buildInFlag;
	}
	public void setBuildInFlag(String buildInFlag) {
		this.buildInFlag = buildInFlag;
	}
	public String[] getRoleNoArray() {
		return roleNoArray;
	}
	public void setRoleNoArray(String[] roleNoArray) {
		this.roleNoArray = roleNoArray;
	}
	public String[] getBrNoArray() {
		return brNoArray;
	}
	public void setBrNoArray(String[] brNoArray) {
		this.brNoArray = brNoArray;
	}
	
	 
	
}
