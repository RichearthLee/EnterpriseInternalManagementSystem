/**
 * Copyright (C) DXHM 版权所有
 * 文件名： BasicCifServiceItems.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.bean
 * 说明：
 * @author Javelin
 * @date 2016-11-10 下午2:34:15
 * @version V1.0
 */
package com.mftcc.interior.cred.bean;
/**
 * 类名： BasicCifServiceItems 描述：
 * 
 * @author Javelin
 * @date 2016-11-10 下午2:34:15
 * 
 * 
 */
public class AcCredServiceItems {
	public AcCredServiceItems() {
	}
	//
	private String serialno;
	// 账户名称
	private String cifAccount;
	// 公司名称
	private String companyname;
	// 服务商编号
	private String merchantNo;
	// 服务商名称
	private String merchantName;
	// 服务项类型
	private String itemType;
	// 服务项编号
	private String itemNo;
	// 服务项名称
	private String itemName;
	// 实际服务单价
	private String priceAmt;
	// 服务默认单价
	private String deftAmt;
	// 状态（0：停用；1：启用；）服务项类型相同时只能有一个启用状态的数据，优先级开发完成之后，支持通道切换
	private String sts;
	// 优先级别：备用字段，如果后续开启多个通道时，支持优先级处理
	private String level;
	// 登记时间
	private String createDate;
	// 操作员编号
	private String tlrno;
	// 操作员名称
	private String tlrname;
	// 服务账户号，第三方提供
	private String sAccount;
	// 服务账户密码，第三方提供
	private String sPasswd;
	//服务商key
	private String sKey;
	// 应用名称（目前同盾第三方使用）
	private String appName;
	// 短信后缀（目前漫道第三方使用短信后缀）
	private String smsSuffix;
	// 备注说明
	private String remarks;
	// 时间戳
	private String occDate;
	/**
  *
  */
	public String getSerialno() {
		return serialno;
	}
	/**
*	
  */
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	/**
	 * 账户名称
	 */
	public String getCifAccount() {
		return cifAccount;
	}
	/**
	 * 账户名称
	 */
	public void setCifAccount(String cifAccount) {
		this.cifAccount = cifAccount;
	}
	/**
	 * 公司名称
	 */
	public String getCompanyname() {
		return companyname;
	}
	/**
	 * 公司名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * 服务商编号
	 */
	public String getMerchantNo() {
		return merchantNo;
	}
	/**
	 * 服务商编号
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	/**
	 * 服务商名称
	 */
	public String getMerchantName() {
		return merchantName;
	}
	/**
	 * 服务商名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	/**
	 * 服务项类型
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * 服务项类型
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * 服务项编号
	 */
	public String getItemNo() {
		return itemNo;
	}
	/**
	 * 服务项编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	/**
	 * 服务项名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 服务项名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 实际服务单价
	 */
	public String getPriceAmt() {
		return priceAmt;
	}
	/**
	 * 实际服务单价
	 */
	public void setPriceAmt(String priceAmt) {
		this.priceAmt = priceAmt;
	}
	/**
	 * 服务默认单价
	 */
	public String getDeftAmt() {
		return deftAmt;
	}
	/**
	 * 服务默认单价
	 */
	public void setDeftAmt(String deftAmt) {
		this.deftAmt = deftAmt;
	}
	/**
	 * 状态（0：停用；1：启用；）服务项类型相同时只能有一个启用状态的数据，优先级开发完成之后，支持通道切换
	 */
	public String getSts() {
		return sts;
	}
	/**
	 * 状态（0：停用；1：启用；）服务项类型相同时只能有一个启用状态的数据，优先级开发完成之后，支持通道切换
	 */
	public void setSts(String sts) {
		this.sts = sts;
	}
	/**
	 * 优先级别：备用字段，如果后续开启多个通道时，支持优先级处理
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 优先级别：备用字段，如果后续开启多个通道时，支持优先级处理
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 登记时间
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 登记时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 操作员编号
	 */
	public String getTlrno() {
		return tlrno;
	}
	/**
	 * 操作员编号
	 */
	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}
	/**
	 * 操作员名称
	 */
	public String getTlrname() {
		return tlrname;
	}
	/**
	 * 操作员名称
	 */
	public void setTlrname(String tlrname) {
		this.tlrname = tlrname;
	}
	/**
	 * 服务账户号，第三方提供
	 */
	public String getSAccount() {
		return sAccount;
	}
	/**
	 * 服务账户号，第三方提供
	 */
	public void setSAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	/**
	 * 服务账户密码，第三方提供
	 */
	public String getSPasswd() {
		return sPasswd;
	}
	/**
	 * 服务账户密码，第三方提供
	 */
	public void setSPasswd(String sPasswd) {
		this.sPasswd = sPasswd;
	}
	/**
	 * 应用名称（目前同盾第三方使用）
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * 应用名称（目前同盾第三方使用）
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * 短信后缀（目前漫道第三方使用短信后缀）
	 */
	public String getSmsSuffix() {
		return smsSuffix;
	}
	/**
	 * 短信后缀（目前漫道第三方使用短信后缀）
	 */
	public void setSmsSuffix(String smsSuffix) {
		this.smsSuffix = smsSuffix;
	}
	/**
	 * 备注说明
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 备注说明
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 时间戳
	 */
	public String getOccDate() {
		return occDate;
	}
	/**
	 * 时间戳
	 */
	public void setOccDate(String occDate) {
		this.occDate = occDate;
	}
	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getsPasswd() {
		return sPasswd;
	}
	public void setsPasswd(String sPasswd) {
		this.sPasswd = sPasswd;
	}
	public String getsKey() {
		return sKey;
	}
	public void setsKey(String sKey) {
		this.sKey = sKey;
	}
	
}