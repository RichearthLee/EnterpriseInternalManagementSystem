package  com.mftcc.interior.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.DAOException;
import com.mftcc.interior.sys.bean.SysOrg;
 
/**
* Title: SysOrgDao.java
* Description:
* @author:沈帅虎
**/
@Repository
public interface ISysOrgDao {
	
	 //方法描述： 获得最大编号
	public List<SysOrg> getSysOrgAll() throws DAOException;
	 //   获得最大编号
	public String getMaxBrNo()  throws DAOException;

	public List<SysOrg> getChild(String upOne,String brSts) throws DAOException;

	public SysOrg getParent(String brNo,String brSts) throws DAOException;
	
	public SysOrg getTreeTop(String upOne,String brSts) throws Exception;
	
	public void insertSysOrg(SysOrg sysOrg) throws DAOException;

	
/*
	public SysOrg getById(SysOrg sysOrg) throws DAOException;

	public SysOrg getByBrNo(String brNo) throws DAOException;
	
	public List<SysOrg> getByUpOne(String upOne) throws DAOException;
	
	public void del(SysOrg sysOrg) throws DAOException;

	public void insert(SysOrg sysOrg) throws DAOException;

	public void update(SysOrg sysOrg) throws DAOException;
	
	public int getCount(SysOrg sysOrg) throws DAOException;
	 
	 */
	
}