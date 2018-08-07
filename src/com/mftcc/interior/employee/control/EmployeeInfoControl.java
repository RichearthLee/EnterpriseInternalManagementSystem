package com.mftcc.interior.employee.control;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.interior.employee.service.IEmployeeInfoService;
import com.mftcc.method.bean.Ipage;


@Controller
public class EmployeeInfoControl {

 	@Autowired
 	private IEmployeeInfoService iEmployeeInfoService;
	 
	Logger logger = Logger.getLogger(this.getClass());

	
	@RequestMapping(value = { "/employeeListInfo" }, method = RequestMethod.GET)
	public String queryEmployeeListInfo(ModelMap model) {
		return "employee/employeeListInfo";
	}
	
	

		@RequestMapping("/getEmployeeInfoPage")
		public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){
		
			ipage = iEmployeeInfoService.getEmployeeInfoPage(ipage);
			model.put("ipage", ipage);
		}


		@RequestMapping(value = { "/employeeInfoadd" }, method = RequestMethod.GET)
		public String addOneEmployeeInfo() {
			 
			return "employee/employeeInfoAdd";
		}
				/*
				 * 新员工添加 提交按钮
				 */
			@RequestMapping(value = { "/employeeinfoinsert" } )
			public void insertOneEmployeeInfo(EmployeeInfo employeeinfo, ModelMap model, HttpSession session) {
				// 从session获取user并设置pactinfo中customerId字段
				boolean result = false;
				try {
					result = iEmployeeInfoService.insertEmployeeInfoService(employeeinfo);
					if (result)
					{
						model.put(SystemParm.ERROR_CODE, "1");
					} else
					{
						throw new Exception("增加员工信息失败");
					}
				} catch (Exception e) {
					model.put(SystemParm.ERROR_CODE, "0");
					model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
				}
			}
			 
				/*
				 * 修改员工，通过ID查找数据的值
				 */
			@RequestMapping(value = { "/updateEmployeeInfoById/{employeeId}" } )
			public String queryEmployeeInfoById(@PathVariable("employeeId") String employeeId,ModelMap model) {
				 EmployeeInfo employeeInfo = iEmployeeInfoService.selectEmployeeInfoByIdService(employeeId);
				 model.put("employeeInfo", employeeInfo);
				return "employee/employeeInfoUpdate";
			}
			
			 @RequestMapping("/employeeinfoupdate")
			public void updateEmployeeInfo(EmployeeInfo employeeInfo ,  ModelMap model)
			{	
				 System.out.println("修改的ID是"+employeeInfo.getEmployeeId());
				 System.out.println("修改的姓名是"+employeeInfo.getEmployeeName());
				try {
					iEmployeeInfoService.updateEmployeeInfo(employeeInfo);
					model.put(SystemParm.ERROR_CODE, "1");
				} catch (Exception e) {
					model.put(SystemParm.ERROR_CODE, "0");
					e.printStackTrace();
				}
			} 
			 
			 @RequestMapping(value = { "/employeeinfodelete/{employeeId}" } )
			 public String deleteEmployeeInfo(@PathVariable("employeeId") String employeeId, ModelMap model) {
			 		boolean result = false;
					result = iEmployeeInfoService.deleteEmployeeInfo(employeeId);
					System.out.println("删除"+result);
			 	return "employee/employeeListInfo";
			 	
			 }

}