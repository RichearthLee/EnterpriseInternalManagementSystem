package com.mftcc.interior.cus.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.interior.cus.bean.customer;
import com.mftcc.interior.cus.bean.message;
import com.mftcc.interior.cus.service.CustomerService;
import com.mftcc.interior.cus.service.MessageService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;

@Controller
public class messageControl {

	@Autowired
	private MessageService msgService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ISysUserService iSysUserService;
	
	@RequestMapping(value = {"/selectMsgByRec"})
	@ResponseBody
	public List<message> selectByRec(String user){
		List<message> list=new ArrayList<message>();
		customer cus=new customer();
		SysUser user2=new SysUser();
		try{
			list=msgService.selectByRec(user);
			for(int i=0;i<list.size();i++){
				cus=customerService.selectCusById(list.get(i).getCusId());
				list.get(i).setCusName(cus.getCustomerName());
				user2.setOpNo(list.get(i).getUserId());
				user2=iSysUserService.getSysUser(user2);
				list.get(i).setUserName(user2.getOpName());
			}
			msgService.updateNewMsg(user);
		}
		catch(Exception e){
			
		}
		System.out.println(list.size());
		return list;
	}
}
