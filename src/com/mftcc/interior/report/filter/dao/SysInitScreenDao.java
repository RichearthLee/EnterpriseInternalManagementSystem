package  com.mftcc.interior.report.filter.dao;

import java.util.List;

import com.core.domain.screen.OptionsList;


import com.mftcc.common.DAOException;


/**
* Title: SysInitScreenDao.java
* Description:
* 
**/
public interface SysInitScreenDao {

	public List<OptionsList> getListById() throws DAOException;
	
	public List<OptionsList> getMailHost() throws DAOException;
	
	/**
	 * 从库中选择待收明细
	 * @return
	 * @throws DAOException
	 */
	public List<OptionsList> getFeeDetail() throws DAOException;
	
	
	/**
	 * @return 从库中选择获取业务品种
	 * @throws DAOException
	 */
	public List<OptionsList> getListProdName() throws DAOException;
	
	/**
	 * @return 获取视角名
	 * @throws DAOException
	 */
	public List<OptionsList> getWkfVpNoName() throws DAOException;
	
	/**
	 * @return 获取视角菜单名称
	 * @throws DAOException
	 */
	public List<OptionsList> getWkfVpMenuName() throws DAOException; 
	
	
	/**
	 * @return 获取财报指标名称
	 * @throws DAOException
	 */
	public List<OptionsList> getFinFormNoName() throws DAOException; 
	
	/**
	 * @return 获取评级场景名称
	 * @throws DAOException
	 */
	public List<OptionsList> getEvalScenceName() throws DAOException; 
	
	/**
	 * @return 还款方式不包括自定义还款
	 * @throws DAOException
	 */
	public List<OptionsList> getRepayTypeIgnoreCustomRepayment() throws DAOException; 
	
	/**
	 * @return 还款方式包括自定义还款
	 * @throws DAOException
	 */
	public List<OptionsList> getRepayTypeIncludeCustomRepayment() throws DAOException; 
	
	/**
	 * @return 获取机构
	 * @throws DAOException
	 */
	public List<OptionsList> getBrName() throws DAOException; 
	
	/**
	 * @return 获取操作员
	 * @throws DAOException
	 */
	public List<OptionsList> getOpName() throws DAOException; 
	
	/**
	 * @return 获取角色
	 * @throws DAOException
	 */
	public List<OptionsList> getRoleName() throws DAOException; 
	
	/**
	 * @return 获取纸质归档模板
	 * @throws DAOException
	 */
	public List<OptionsList> getArchiveModel() throws DAOException;
	
	/**
	 * @return 获取分公司
	 * @throws DAOException
	 */
	public List<OptionsList> getBranchOrgNo() throws DAOException;
	
	/**
	 * @return 获取审批角色名称
	 * @throws DAOException
	 */
	public List<OptionsList> getWkfRoleName() throws DAOException;
	
}