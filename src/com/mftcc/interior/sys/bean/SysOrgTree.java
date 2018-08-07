package com.mftcc.interior.sys.bean;
import java.util.ArrayList;
import java.util.List;

import com.mftcc.interior.sys.dao.ISysOrgDao;
 
/**
* Title: SysOrg.java
* Description:
* @author： 沈帅虎
* @Tue  
**/
public class SysOrgTree {
	/*public SysOrg getTreeTop(){
		SysOrg sysOrg = new SysOrg();
		SysOrgDao sysOrgBo = (SysOrgDao) SourceTemplate.getSpringContextInstance().getBean("sysOrgBo");
		sysOrg = sysOrgBo.getTreeTop();
		return sysOrg;
	}
	
	public List<SysOrg> getChildren(String brNo){
		List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
		SysOrgDao sysOrgBo = (SysOrgDao) SourceTemplate.getSpringContextInstance().getBean("sysOrgBo");
		SysOrg sysOrg = new SysOrg();
		sysOrg.setUpOne(brNo);
		sysOrgList = sysOrgBo.getChildren(sysOrg);
		return sysOrgList;
		
	}*/
}