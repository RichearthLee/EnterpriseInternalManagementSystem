package com.mftcc.interior.sys.service.impl;

import java.util.List;

import javax.xml.rpc.ServiceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.DAOException;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.interior.sys.dao.ISysOrgDao;
import com.mftcc.interior.sys.service.ISysOrgService;

 

@Service
public class SysOrgServiceImpl implements ISysOrgService  {

	@Autowired
	private ISysOrgDao isysOrgDao;
	// 是否使用规则生成机构编号标识1使用0不使用

	@Override
	public List<SysOrg> getSysOrgAll() throws ServiceException {
		// TODO Auto-generated method stub
		return isysOrgDao.getSysOrgAll();
	}

	@Override
	public String getMaxBrNo() throws ServiceException {
		// TODO Auto-generated method stub
		return isysOrgDao.getMaxBrNo();
	}
	
	  //   插入
	@Override
	public void insertSysOrg(SysOrg sysOrg) throws DAOException {
		// TODO Auto-generated method stub
		isysOrgDao.insertSysOrg(sysOrg);
	}
	
	
	
	// 以下暂不使用
	@Override
	public SysOrg getTreeTop(String upOne,String brSts) {
		 SysOrg sysOrg = new SysOrg();
		try {
			sysOrg = isysOrgDao.getTreeTop( upOne,brSts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sysOrg ;
	}
	
	public SysOrg getParent(String brNo,String brSts) throws ServiceException {
		return isysOrgDao.getParent(brNo, brSts);
	}
	
	
	public List<SysOrg> getChild(String upOne,String brSts) throws ServiceException {
		List<SysOrg> sysOrgsList = null;
		try {
			sysOrgsList = isysOrgDao.getChild(upOne, brSts);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return sysOrgsList;
	}

	


 
}