package com.mftcc.interior.employee.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.interior.employee.dao.IEmployeeInfoDao;
import com.mftcc.interior.employee.service.IEmployeeInfoService;
import com.mftcc.method.bean.Ipage;
@Service
public class IEmployeeInfoServiceImpl implements IEmployeeInfoService{
	
	@Autowired
	IEmployeeInfoDao employeeInfoDao;

	@Override
	public Ipage getEmployeeInfoPage(Ipage ipage) throws ServiceException {
		 
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<EmployeeInfo> employeeInfo = employeeInfoDao.getEmployeeInfoPage(ipage);
		int allRecord = Integer.parseInt(employeeInfoDao.getEmployeeInfoCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(employeeInfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override
	public boolean insertEmployeeInfoService  (EmployeeInfo employeeInfo)throws ServiceException{
			employeeInfoDao.insertEmployeeInfo(employeeInfo);
		return true;
	}

	@Override
	public boolean updateEmployeeInfo(EmployeeInfo employeeInfo)
			throws ServiceException {
		employeeInfoDao.updateEmployeeInfo(employeeInfo);
		return false;
	}

	@Override
	public EmployeeInfo selectEmployeeInfoByIdService(String employeeId) {
		// TODO Auto-generated method stub
		return (EmployeeInfo) employeeInfoDao.selectEmployeeInfoById(employeeId).get(0);
	}
	
	 
	@Override
	public boolean deleteEmployeeInfo(String employeeId) throws ServiceException {
		employeeInfoDao.deleteEmployeeInfo(employeeId);
		return false;
	}

}
