package com.mftcc.interior.report.filter.bean;

import com.mftcc.interior.report.common.BaseDomain;



/**
* Title: ParmDic.java
* Description:
* @author：jiangyunxin@dhcc.com.cn
* @Thu Apr 10 09:11:38 GMT 2014
* @version：1.0
**/
public class ParmDic extends BaseDomain {
	private String keyName;//key_name
	private String optCode;//opt_code
	private String optName;//opt_name
	private Integer seqn;//seqn
	private String sts;//sts
	private String remark;//描述

	/**
	 * @return key_name
	 */
	public String getKeyName() {
	 	return keyName;
	}
	/**
	 * @设置 key_name
	 * @param keyName
	 */
	public void setKeyName(String keyName) {
	 	this.keyName = keyName;
	}
	/**
	 * @return opt_code
	 */
	public String getOptCode() {
	 	return optCode;
	}
	/**
	 * @设置 opt_code
	 * @param optCode
	 */
	public void setOptCode(String optCode) {
	 	this.optCode = optCode;
	}
	/**
	 * @return opt_name
	 */
	public String getOptName() {
	 	return optName;
	}
	/**
	 * @设置 opt_name
	 * @param optName
	 */
	public void setOptName(String optName) {
	 	this.optName = optName;
	}
	/**
	 * @return seqn
	 */
	public Integer getSeqn() {
	 	return seqn;
	}
	/**
	 * @设置 seqn
	 * @param seqn
	 */
	public void setSeqn(Integer seqn) {
	 	this.seqn = seqn;
	}
	/**
	 * @return sts
	 */
	public String getSts() {
	 	return sts;
	}
	/**
	 * @设置 sts
	 * @param sts
	 */
	public void setSts(String sts) {
	 	this.sts = sts;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}