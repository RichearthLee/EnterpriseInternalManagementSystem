/**
 * Copyright (C) DXHM 版权所有
 * 文件名： BasicServiceGroup.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.bean
 * 说明：
 * @author lxb
 * @date 2017-07-26 下午2:35:02
 * @version V1.0
 */
package com.mftcc.interior.cred.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 类名： BasicServiceGroup 描述： 服务分组
 * 
 * @author lxb
 * @date
 * 
 * 
 */
public class AcCredServiceGroup {

	private String serialno;// '唯一编号',
	private String traceNo;// '流水号',
	private String businessCode;// '业务编码',
	private String businessName;// '业务名称',
	private String tlrno;// '创建人编号',
	private String tlrname;// '创建人名称',
	private String createDate;// '创建时间',
	private String occTime;// '时间戳',
	private String sts;// '状态(Y:启用,N:不可用)',
	private String pid;// '上级类别id',
	private String level;// '类别层级,第一级0第二级1'
	private AcCredServiceGroup parent;
	private List<AcCredServiceGroup> childen = new ArrayList<AcCredServiceGroup>();

	public void addServiceGroups(List<AcCredServiceGroup> bsgs) {

		if(this.level==null){//构造节点 根
			for (AcCredServiceGroup g : bsgs) {
				if(StringUtils.isEmpty(g.getPid())){
					this.childen.add(g);
				}
			}
		}else{
			for (AcCredServiceGroup g : bsgs) {
				if(this.serialno.equals(g.getPid())){
					this.childen.add(g);
				}
			}
		}
		
		for (AcCredServiceGroup c : childen) {
			c.addServiceGroups(bsgs);
		}
	}

	//先叶子结点最后是根
	public List<AcCredServiceGroup> treeToList(){
		if(this.childen==null || this.childen.size()<1 ){
			List<AcCredServiceGroup> list=new ArrayList<AcCredServiceGroup>();
			list.add(this);
			return list;
		}
		List<AcCredServiceGroup> list=new ArrayList<AcCredServiceGroup>();
		for(AcCredServiceGroup bs:this.childen){
			list.addAll(bs.treeToList());
		}
		list.add(this);
		return list;
	}
	@Override
	public String toString() {
		return "BasicServiceGroup [serialno=" + serialno + ", traceNo="
				+ traceNo + ", businessCode=" + businessCode
				+ ", businessName=" + businessName + ", tlrno=" + tlrno
				+ ", tlrname=" + tlrname + ", createDate=" + createDate
				+ ", occTime=" + occTime + ", sts=" + sts + ", pid=" + pid
				+ ", level=" + level + ", childen=" + childen + "]";
	}

	public AcCredServiceGroup(String serialno, String pid, String level) {
		this.serialno = serialno;
		this.pid = pid;
		this.level = level;
	}

	public AcCredServiceGroup() {
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public String getTlrname() {
		return tlrname;
	}

	public void setTlrname(String tlrname) {
		this.tlrname = tlrname;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOccTime() {
		return occTime;
	}

	public void setOccTime(String occTime) {
		this.occTime = occTime;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<AcCredServiceGroup> getChilden() {
		return childen;
	}

	public void setChilden(List<AcCredServiceGroup> childen) {
		this.childen = childen;
	}

	public AcCredServiceGroup getParent() {
		return parent;
	}

	public void setParent(AcCredServiceGroup parent) {
		this.parent = parent;
	}
	

}
