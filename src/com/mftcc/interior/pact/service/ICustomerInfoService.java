
package com.mftcc.interior.pact.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.pact.bean.CustomerInfo;



public interface ICustomerInfoService {
	/**
	 * 方法描述：查询客户信息 
	 * @param prarm
	 * @return
	 * @throws ServiceException
	 * List<CustomerInfo>
	 * @author Cuizk
	 * @date 2015-11-18 下午5:46:05
	 */
	public List<CustomerInfo> getCustomerInfo  (CustomerInfo customerInfo)throws ServiceException;
	/**
	 * 方法描述：根据客户id更新客户信息 
	 * @param customerInfo
	 * @throws ServiceException
	 * void
	 * @author Cuizk
	 * @date 2015-11-18 下午5:46:29
	 */
	public boolean updateCustomerInfo  (CustomerInfo customerInfo)throws ServiceException;
	/**
	 * 方法描述：增加客户。客户ID不需要设置，数据库会进行自增。 
	 * @param customerInfo
	 * @throws ServiceException
	 * void
	 * @author Cuizk
	 * @date 2015-11-18 下午5:46:50
	 */
	public void insertCustomerInfo  (CustomerInfo customerInfo)throws ServiceException;
	
	/**
	 * 方法描述： 根据客户ID查询客户信息
	 * @param Id
	 * @return
	 * @throws ServiceException
	 * CustomerInfo
	 * @author Cuizk
	 * @date 2015-11-19 上午10:35:40
	 */
	public CustomerInfo getCustomerInfoById  (String Id)throws ServiceException;
}
