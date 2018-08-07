package com.mftcc.interior.pact.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.common.SystemParm;
import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.service.ICustomerInfoService;
import com.mftcc.interior.sys.bean.SysUser;



/**
 * 类名： CustomerInfoControl <br>
 * 描述：
 * @author Cuizk
 * @date 2015-11-17 下午2:40:21
 */
@Controller
public class CustomerInfoControl {

	@Autowired
	
	private ICustomerInfoService customerInfoService;

	/**
	 * 方法描述：根据查询某市场人员相关的客户信息
	 * @param name
	 * @param model
	 * @return String
	 * @author Cuizk
	 * @date 2015-11-17 下午2:39:25
	 */
	@RequestMapping(value = { "/getcustomerinfo" })
	public String queryCustomer( ModelMap model ,HttpSession session) {
		CustomerInfo customerInfo = new CustomerInfo();
		SysUser user = (SysUser)session.getAttribute("sysuser");
		customerInfo.setMarketerId(user.getOpNo());
		List<CustomerInfo> customerInfos = null;
		try {
			customerInfos = customerInfoService.getCustomerInfo(customerInfo);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		
		model.put("customerInfos", customerInfos);
		/*employee/employeeListInfo*/

		return "pactManage/customerSelect";
	}

	/**
	 * 方法描述： 根据客户ID更新客户信息
	 * @param id
	 * @param model
	 * @return String
	 * @author Cuizk
	 * @date 2015-11-17 下午2:39:54
	 */
	@RequestMapping(value = { "/customerinfoupdate/{id}" }, method = RequestMethod.GET)
	public String updateCustomer(@PathVariable("id") String id, ModelMap model) {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerId(id);
		customerInfo.setCustomerName(id);
		try {
			customerInfoService.updateCustomerInfo(customerInfo);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		model.put("customerdata", customerInfo);
		return "pactManage/customer";
	}

	/**
	 * 方法描述：增加客户
	 * @param name
	 * @param model
	 * @return String
	 * @author Cuizk
	 * @date 2015-11-17 下午2:40:25
	 */
	@RequestMapping(value = { "/customerinfoinsert" })
	public void insertCustomer(CustomerInfo customer, ModelMap model, HttpSession session) {
		boolean result = false;
		SysUser user = (SysUser)session.getAttribute("sysuser");
		customer.setMarketerId(user.getOpNo());
		try {
			 customerInfoService.insertCustomerInfo(customer);
			if(result)
			{
				model.put(SystemParm.ERROR_CODE, "1");
			}else
			{
				throw new Exception("增加客户信息失败");
			}
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
		}
	}
	
	/**
	 * 方法描述： 跳转至增加客户的jsp界面
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-25 下午2:03:32
	 */
	@RequestMapping(value = { "/customerAdd" } )
	public String AddCustomer(ModelMap model) {
		
		return "pactManage/customerAdd";
	}
}
