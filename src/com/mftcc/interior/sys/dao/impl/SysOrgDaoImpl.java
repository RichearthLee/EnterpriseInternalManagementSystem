package com.mftcc.interior.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.DAOException;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.interior.sys.dao.ISysOrgDao;

/**
 * @author shensh
 * @date 2018年3月14日  
 * @version 1.0
 */


@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class SysOrgDaoImpl extends BaseDao implements ISysOrgDao {
	
	
	@Override
	public List<SysOrg> getSysOrgAll() throws DAOException {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForList("getSysOrgAll");
	}

	/* (non-Javadoc)
	 * 获取最大编号
	 */
	@Override
	public String getMaxBrNo() throws DAOException {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("getMaxBrNo");
	}
	
	/*  
	 *  插入
	 */
	@Override
	public void insertSysOrg(SysOrg sysOrg) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insertSysOrg", sysOrg);
	}
	
	
	
	//  以下内容没有使用
	@Override
	public List<SysOrg> getChild(String upOne, String brSts)
			throws DAOException {
		// TODO Auto-generated method stub
		return (List<SysOrg>) getSqlMapClientTemplate().queryForObject("getChild",  upOne , brSts);
	}
 
	 
	@Override
	public SysOrg getParent(String brNo, String brSts) throws DAOException {
		// TODO Auto-generated method stub
		return (SysOrg) getSqlMapClientTemplate().queryForObject("getPant", brNo,brSts);
	}

	 
	@Override
	public  SysOrg getTreeTop(String upOne,String brSts) throws Exception {
		// TODO Auto-generated method stub
		List<SysOrg> sysOrg =   getSqlMapClientTemplate().queryForList("getTreeTop",upOne);
	//	System.out.println(sysOrg);
		return sysOrg.get(0) ;
	}



 


}
