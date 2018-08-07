package  com.mftcc.interior.sys.service;

import java.util.List;

import javax.xml.rpc.ServiceException;

import com.mftcc.common.DAOException;
import com.mftcc.interior.sys.bean.SysOrg;
 
 /*
  *@
  */
 
public interface ISysOrgService {

	public List<SysOrg> getSysOrgAll() throws ServiceException ;
	
	public String getMaxBrNo() throws ServiceException;
	
	
	
	public SysOrg getTreeTop(String upOne,String brSts) throws ServiceException;
	
	public SysOrg getParent(String brNo,String brSts) throws ServiceException;
	
	public List<SysOrg> getChild(String upOne,String brSts) throws ServiceException ;
	
	public void insertSysOrg(SysOrg sysOrg) throws DAOException;


	/*public SysOrg getById(SysOrg sysOrg) throws DAOException;

	public SysOrg getByBrNo(String brNo) throws DAOException;
	
	public List<SysOrg> getByUpOne(String upOne) throws DAOException;
	
	public void del(SysOrg sysOrg) throws DAOException;

	public void update(SysOrg sysOrg) throws DAOException;
	
	public int getCount(SysOrg sysOrg) throws DAOException;
	*/ 
}