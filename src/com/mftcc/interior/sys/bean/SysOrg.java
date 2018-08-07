package com.mftcc.interior.sys.bean;

import java.util.Arrays;

/**
* Title: SysOrg.java
* Description:
* @author：沈帅虎
* @Tue Nov 
* @version：1.0
**/
public class SysOrg  {
	private String brNo;//机构号
	private String brName;//机构名称
	private String brLev;//机构级别
	private String upOne;//上一级机构
	private String upTwo;//丄两级机构
	private String brTel;//联系电话
	private String brType;//机构类型
	private String brAddr;//机构地址
	private String brPost;//邮政编码
	private String brSts;//机构状态
	private String brAliasNo;//机构别名编号
	private String brNoFull;//机构编号全称
	private String brArea;//行政区域
	private String bizType;//机构业务类型
	private String userPre;//用户号前缀
	private String conPre;//用户号前缀
	private String upOneName;//用户号前缀
	private String[] brNoArray;//部门编号数组,用于查询
	
	
	@Override
	public String toString() {
		return "SysOrg [brNo=" + brNo + ", brName=" + brName + ", brLev="
				+ brLev + ", upOne=" + upOne + ", upTwo=" + upTwo + ", brTel="
				+ brTel + ", brType=" + brType + ", brAddr=" + brAddr
				+ ", brPost=" + brPost + ", brSts=" + brSts + ", brAliasNo="
				+ brAliasNo + ", brNoFull=" + brNoFull + ", brArea=" + brArea
				+ ", bizType=" + bizType + ", userPre=" + userPre + ", conPre="
				+ conPre + ", upOneName=" + upOneName + ", brNoArray="
				+ Arrays.toString(brNoArray) + "]";
	}
	
	
	
	public String getUpOneName() {
		return upOneName;
	}
	public void setUpOneName(String upOneName) {
		this.upOneName = upOneName;
	}
	
	
	/**
	 * @return 机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 机构名称
	 */
	public String getBrName() {
	 	return brName;
	}
	/**
	 * @设置 机构名称
	 * @param brName
	 */
	public void setBrName(String brName) {
	 	this.brName = brName;
	}
	/**
	 * @return 机构级别
	 */
	public String getBrLev() {
	 	return brLev;
	}
	/**
	 * @设置 机构级别
	 * @param brLev
	 */
	public void setBrLev(String brLev) {
	 	this.brLev = brLev;
	}
	/**
	 * @return 上一级机构
	 */
	public String getUpOne() {
	 	return upOne;
	}
	/**
	 * @设置 上一级机构
	 * @param upOne
	 */
	public void setUpOne(String upOne) {
	 	this.upOne = upOne;
	}
	/**
	 * @return 丄两级机构
	 */
	public String getUpTwo() {
	 	return upTwo;
	}
	/**
	 * @设置 丄两级机构
	 * @param upTwo
	 */
	public void setUpTwo(String upTwo) {
	 	this.upTwo = upTwo;
	}
	/**
	 * @return 联系电话
	 */
	public String getBrTel() {
	 	return brTel;
	}
	/**
	 * @设置 联系电话
	 * @param brTel
	 */
	public void setBrTel(String brTel) {
	 	this.brTel = brTel;
	}
	/**
	 * @return 机构类型
	 */
	public String getBrType() {
	 	return brType;
	}
	/**
	 * @设置 机构类型
	 * @param brType
	 */
	public void setBrType(String brType) {
	 	this.brType = brType;
	}
	/**
	 * @return 机构地址
	 */
	public String getBrAddr() {
	 	return brAddr;
	}
	/**
	 * @设置 机构地址
	 * @param brAddr
	 */
	public void setBrAddr(String brAddr) {
	 	this.brAddr = brAddr;
	}
	/**
	 * @return 邮政编码
	 */
	public String getBrPost() {
	 	return brPost;
	}
	/**
	 * @设置 邮政编码
	 * @param brPost
	 */
	public void setBrPost(String brPost) {
	 	this.brPost = brPost;
	}
	/**
	 * @return 机构状态
	 */
	public String getBrSts() {
	 	return brSts;
	}
	/**
	 * @设置 机构状态
	 * @param brSts
	 */
	public void setBrSts(String brSts) {
	 	this.brSts = brSts;
	}
	public void setBrArea(String brArea) {
		this.brArea = brArea;
	}
	public String getBrArea() {
		return brArea;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBizType() {
		return bizType;
	}
	public String getUserPre() {
		return userPre;
	}
	public void setUserPre(String userPre) {
		this.userPre = userPre;
	}
	public String getConPre() {
		return conPre;
	}
	public void setConPre(String conPre) {
		this.conPre = conPre;
	}
	public String getBrAliasNo() {
		return brAliasNo;
	}
	public void setBrAliasNo(String brAliasNo) {
		this.brAliasNo = brAliasNo;
	}
	public String getBrNoFull() {
		return brNoFull;
	}
	public void setBrNoFull(String brNoFull) {
		this.brNoFull = brNoFull;
	}
	public String[] getBrNoArray() {
		return brNoArray;
	}
	public void setBrNoArray(String[] brNoArray) {
		this.brNoArray = brNoArray;
	}
}