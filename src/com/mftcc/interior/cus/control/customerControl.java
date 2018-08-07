package com.mftcc.interior.cus.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.common.SystemParm;
import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.cus.bean.cusLinkman;
import com.mftcc.interior.cus.bean.customer;
import com.mftcc.interior.cus.service.CustomerService;
import com.mftcc.interior.pact.bean.CustomerInfo;

@Controller
public class customerControl {

	@Autowired
	private CustomerService customerservice;
	
	@RequestMapping(value="/insertCus")
	@ResponseBody
	public Map<String,Object> insertCustomer(customer cus){
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("flag", "success");
		try{
			customerservice.insertCustomer(cus);
		}
		catch(Exception e){
			datamap.put("flag", "failed");
		}
		return datamap;
	}
	@RequestMapping(value="/selectByMarketer")
	@ResponseBody
	public List<customer> selectByMarketer(String marketer){
		List<customer> list=new ArrayList<customer>();
		list=customerservice.selectByMarketer(marketer);
		return list;
	}
	
	@RequestMapping(value="/selectLinkByCus")
	@ResponseBody
	//这个方法在前段是和查询记录一起执行，所以与查询跟踪信息合并到了一起
	public List<cusLinkman> selectLinkByCus(String cusId){
		List<cusLinkman> list=new ArrayList<cusLinkman>();
		list=customerservice.selectLinkByCus(cusId);
		System.out.println(list.size());
		return list;
	}
	@RequestMapping(value="/insertLink")
	@ResponseBody
	public Map<String,Object> insertLink(cusLinkman linkman){
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("falg", "success");
		try{
			customerservice.insertLinkman(linkman);
		}
		catch(Exception e){
			datamap.put("falg", "failed");
		}
		return datamap;
	}

/*	@RequestMapping(value = { "/pactinfo/{pactId}" }, method = RequestMethod.GET)
	public String queryOnePact(@PathVariable("pactId") String pactId,  ModelMap model)
	
	*/
	@RequestMapping(value="/getAllLink")
	@ResponseBody
	public Map<String,Object> selectAllLink(String customerId, Map<String,Object> map)
	{
		System.out.println("-----------"+customerId);
		List<cusLinkman> linkManList=new ArrayList<cusLinkman>();
		try {
			cusLinkman cusLinkman=new cusLinkman();
			cusLinkman.setMarketerId(customerId);
			linkManList = customerservice.selectLinkByCus(cusLinkman.getMarketerId());
			System.out.println(cusLinkman.getMarketerId());
			map.put("data", linkManList);
		} catch (ServiceException e) {
			map.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			map.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
	
		return map;
	}
	
	@RequestMapping(value="/getCusLinkById")
	@ResponseBody
	public Map<String,Object> getCusLinkById(String linkId, Map<String,Object> map)
	{
		List<cusLinkman> linkManList=new ArrayList<cusLinkman>();
		try {

			cusLinkman cusLinkman=new cusLinkman();
			cusLinkman.setLinkId(linkId); 
			linkManList = customerservice.selectLinkByCus(linkId);

			
			map.put("data", linkManList);
			
		} catch (ServiceException e) {
			 
			map.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
	
		return map;
	}

	@RequestMapping(value="/searchByName")
	@ResponseBody
	public List<customer> searchByName(customer cus){
		List<customer> list=new ArrayList<customer>();
		list=customerservice.searchByName(cus);
		System.out.println(list.size());
		return list;
	}
}
