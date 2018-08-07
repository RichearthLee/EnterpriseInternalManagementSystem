
package com.mftcc.interior.pact.dao;

import java.util.List;

import com.mftcc.interior.pact.bean.CustomerInfo;




public interface ICustomerInfoDao {

	/**
	 * 方法描述：  查询客户信息，CustomerInfo的字段不为空，增加查询条件
	 * @return
	 * CustomerInfo
	 * @author Cuizk
	 * @date 2015-11-17 上午10:05:07
	 */
	public List<CustomerInfo> selectCustomerInfo(CustomerInfo customerinfo);

	/**
	 * 方法描述： 根据id号更新客户信息
	 * @author Cuizk
	 * @date 2015-11-17 上午10:08:53
	 */
	public int updateCustomerInfo(CustomerInfo customerinfo);
	/**
	 * 方法描述： 增加客户信息，并返回此客户的主键，即customerId
	 * @author Cuizk
	 * @date 2015-11-17 上午10:08:53
	 */
	public void insertCustomerInfo(CustomerInfo customerInfo);
	
}
