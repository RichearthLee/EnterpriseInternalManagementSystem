package com.mftcc.interior.cred.control;

	import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.cred.bean.CredBean;
import com.mftcc.interior.cred.service.CredService;
import com.mftcc.method.bean.Ipage;

	@Controller
	public class CredController {
		@Autowired
		private CredService credService;
		private Logger log=Logger.getLogger(CredController.class);
		
		//返回客户列表页
		@RequestMapping("/customerlist")
		public String customerlist()
		{
			return "cred/customerlist";
		}
		
		//返回新增客户页
		@RequestMapping("/addcus")
		public String addcus()
		{
			return "cred/addcus";
		}
		
		//新增
		@RequestMapping("/AddCred")
		public void AddCred(CredBean credBean,ModelMap model)
		{
			
			
				Boolean result = credService.addCred(credBean);
			
				System.err.println(result);
				if(result)
				{	//失败
					model.put(SystemParm.ERROR_CODE, result);
				}
				else{//成功
					model.put(SystemParm.ERROR_CODE, "0");
				}
				
		}	
				
				@RequestMapping("/getAllCred")
				public String getAllCred() {
					
					return "cred/customerlist";
					
				}
				
				@RequestMapping("/cusInfo")
				public String cusInfo(CredBean credBean,ModelMap model)
				{
					
					List<CredBean> customerlist=findCus(credBean);
					model.put("cus", customerlist.get(0));
					
					return "cred/cusInfo";
				}
				@RequestMapping("/findcus")
				public List<CredBean> findCus(CredBean credBean) {
					List<CredBean> customerList =  credService.findCus(credBean);
					
				    return customerList;
					
				}

				@RequestMapping("/getCusListPage")
				public void getCusListPage(Ipage ipage, ModelMap model){

					try {
						ipage = credService.getCusListPage(ipage);
					} catch (Exception e) {
						e.printStackTrace();
					}
					model.put("ipage", ipage);
				}
				
				@RequestMapping("/deleteCus")
				public void deleteCus(CredBean credBean,ModelMap modelMap)
				{
					
					Boolean result = credService.deleteCus(credBean);
					if(result==true){
						modelMap.put(SystemParm.ERROR_CODE, "0");
						modelMap.put(SystemParm.ERROR_MESSAGE, "删除成功！");
					}
					
				}
				
				@RequestMapping("/updateCus")
				public String updateCus(CredBean emp,ModelMap model)
				{
						System.out.println(emp.toString());
						Boolean result = credService.updateCus(emp);
						System.err.print(result);

						if(result)
						{	//失败
							model.put(SystemParm.ERROR_CODE, result);
						}
						else{//成功
							model.put(SystemParm.ERROR_CODE, "0");
						}
						return "cred/customerlist";
			} 
				
		
		}
	
				

		

