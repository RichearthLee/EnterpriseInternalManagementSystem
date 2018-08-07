
package com.mftcc.interior.employee.dao;

import java.util.List;
import java.util.Map;

import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.method.bean.Ipage;


public interface IEmployeeInfoDao {

/*   public List<EmployeeInfo> selectEmployeeInfo(EmployeeInfo employeeInfo);
    
    public List<EmployeeInfo> selectEmployeeInfo();*/
    
    public  List<EmployeeInfo> getEmployeeInfoPage(Ipage ipage);
    
    public String getEmployeeInfoCount(Ipage ipage);

    public int updateEmployeeInfo(EmployeeInfo employeeInfo);
    
  public List<EmployeeInfo> selectEmployeeInfoById(String employeeId);
  
    public void insertEmployeeInfo(EmployeeInfo employeeInfo);
  
    public int deleteEmployeeInfo(String employeeId);
    
}
