
package com.mftcc.interior.pact.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.dao.ICustomerInfoDao;
import com.mftcc.interior.pact.service.ICustomerInfoService;


@Service
public class CustomerInfoServiceImpl implements ICustomerInfoService {
	@Autowired
	ICustomerInfoDao customerInfoDao;
	@Override
	public List<CustomerInfo> getCustomerInfo(CustomerInfo customerInfo) throws ServiceException {
	
		List<CustomerInfo> customerInfos = customerInfoDao.selectCustomerInfo(customerInfo);
		
		return customerInfos;
	}

	@Override
	public boolean updateCustomerInfo(CustomerInfo customerInfo) throws ServiceException {
		if(customerInfo.getCustomerId() == null)
			return false;
		if( customerInfoDao.updateCustomerInfo(customerInfo) == 1 )
			return true;
		return false;
	}

	@Override
	public void insertCustomerInfo(CustomerInfo customerInfo) throws ServiceException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		customerInfo.setEntryDate(df.format(new Date()));
		
		customerInfoDao.insertCustomerInfo(customerInfo);
		

	}

	@Override
	public CustomerInfo getCustomerInfoById(String Id) throws ServiceException {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerId(Id);
		return customerInfoDao.selectCustomerInfo(customerInfo).get(0);
	}

}
