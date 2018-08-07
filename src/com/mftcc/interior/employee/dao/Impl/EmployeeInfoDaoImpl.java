package com.mftcc.interior.employee.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.interior.employee.dao.IEmployeeInfoDao;
import com.mftcc.method.bean.Ipage;

 
@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class EmployeeInfoDaoImpl extends BaseDao implements IEmployeeInfoDao {

	
/*	@Override
	public List<EmployeeInfo> selectEmployeeInfo() {
		return getSqlMapClientTemplate().queryForList("selectEmployeeInfo");
	}
	@Override
	public List<EmployeeInfo> selectEmployeeInfo(EmployeeInfo employeeInfo) {
		List<EmployeeInfo> employee = (List<EmployeeInfo>) getSqlMapClientTemplate().queryForList("selectEmployeeInfo", employeeInfo);
		return employee;
	}*/

	//用于分页

	@Override
	public List<EmployeeInfo> getEmployeeInfoPage(Ipage ipage) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectEmployeeInfoPage", ipage);
	}
	@Override
	public String getEmployeeInfoCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectEmployeeInfoCount", ipage);
	}

	
 	@Override
	public int updateEmployeeInfo(EmployeeInfo employeeInfo) {
		return (int)getSqlMapClientTemplate().update("updateEmployeeInfo", employeeInfo);
	}
 
	@Override
	public void insertEmployeeInfo(EmployeeInfo employeeInfo) {
		getSqlMapClientTemplate().insert("insertEmployeeInfo", employeeInfo);
	}
	@Override
	 public List<EmployeeInfo> selectEmployeeInfoById(String employeeId){
		return   getSqlMapClientTemplate().queryForList("selectEmployeeInfoById",employeeId);
	}
	
	@Override
	public int deleteEmployeeInfo(String employeeId) {
		return (int)getSqlMapClientTemplate().delete("deleteEmployeeInfo", employeeId);
	}

}
