package com.mftcc.interior.employee.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.method.bean.Ipage;

public interface IEmployeeInfoService {

	// 查询所有
	public Ipage getEmployeeInfoPage(Ipage ipage) throws ServiceException;
	// 插入
	public boolean insertEmployeeInfoService(EmployeeInfo employeeInfo) throws ServiceException;
	// 更新
 	public boolean updateEmployeeInfo  (EmployeeInfo employeeInfo)throws ServiceException;
	// 删除
 	public boolean deleteEmployeeInfo  (String employeeId)throws ServiceException;
 	// 通过ID查询
 	public EmployeeInfo selectEmployeeInfoByIdService(String employeeId);

}
