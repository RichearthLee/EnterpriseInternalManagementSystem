
package com.mftcc.interior.pact.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.dao.ICustomerInfoDao;

@SuppressWarnings("deprecation")
@Repository
public class CustomerInfoDaoImpl extends BaseDao implements ICustomerInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerInfo> selectCustomerInfo(CustomerInfo customerinfo) {
		
		return  getSqlMapClientTemplate().queryForList("getPactCustomerInfo", customerinfo);
	}

	@Override
	public int updateCustomerInfo(CustomerInfo customerinfo) {
		System.out.println("我来了");
		return (int)getSqlMapClientTemplate().update("updatePactCustomerInfo", customerinfo);

	}

	
	@Override
	public void insertCustomerInfo(CustomerInfo customerInfo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		customerInfo.setCustomerId(df.format(new Date()));
	    getSqlMapClientTemplate().insert("insertPactCustomerInfo", customerInfo);
	}

}
