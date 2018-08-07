package com.mftcc.interior.cred.control;

	import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.cred.bean.ServiceBean;
import com.mftcc.interior.cred.service.ServiceService;
import com.mftcc.method.bean.Ipage;

	@Controller
	public class ServiceController {
		@Autowired
		private ServiceService serviceService;
		private Logger log=Logger.getLogger(ServiceController.class);
		
		//返回客户列表页
		@RequestMapping("/servicelist")
		public String servicelist()
		{
			return "cred/service/servicelist";
		}
		
		//返回新增客户页
		@RequestMapping("/addservice")
		public String addservice()
		{
			return "cred/service/addservice";
		}
		
		//新增
		@RequestMapping("/AddService")
		public void AddService(ServiceBean serviceBean,ModelMap model)
		{
			
			
				Boolean result = serviceService.addService(serviceBean);
			
				System.err.println(result);
				if(result)
				{	//失败
					model.put(SystemParm.ERROR_CODE, result);
				}
				else{//成功
					model.put(SystemParm.ERROR_CODE, "0");
				}
				
		}	
				
				@RequestMapping("/getAllService")
				public String getAllService() {
					
					return "cred/service/servicelist";
					
				}
				
				@RequestMapping("/serviceInfo")
				public String serviceInfo(ServiceBean serviceBean,ModelMap model)
				{
					
					List<ServiceBean> servicelist=findService(serviceBean);
					model.put("service", servicelist.get(0));
					
					return "cred/service/serviceInfo";
				}
				@RequestMapping("/findService")
				public List<ServiceBean> findService(ServiceBean serviceBean) {
					List<ServiceBean> Service =  serviceService.findService(serviceBean);
					
				    return Service;
					
				}

				@RequestMapping("/getServiceListPage")
				public void getServiceListPage(Ipage ipage, ModelMap model){

					try {
						ipage = serviceService.getServiceListPage(ipage);
					} catch (Exception e) {
						e.printStackTrace();
					}
					model.put("ipage", ipage);
				}
				
				@RequestMapping("/deleteService")
				public void deleteService(ServiceBean serviceBean,ModelMap modelMap)
				{
					
					Boolean result = serviceService.deleteService(serviceBean);
					if(result==true){
						modelMap.put(SystemParm.ERROR_CODE, "0");
						modelMap.put(SystemParm.ERROR_MESSAGE, "删除成功！");
					}
					
				}
				
				@RequestMapping("/updateService")
				public String updateService(ServiceBean emp,ModelMap model)
				{
						System.out.println(emp.toString());
						Boolean result = serviceService.updateService(emp);
						System.err.print(result);

						if(result)
						{	//失败
							model.put(SystemParm.ERROR_CODE, result);
						}
						else{//成功
							model.put(SystemParm.ERROR_CODE, "0");
						}
						return "cred/service/servicelist";
			} 
				
				@RequestMapping("/serviceNumber")
				public String serviceNumber(ServiceBean serviceBean) {
					
					String aa=serviceBean.getServiceNumber();
					
					
					return "cred/service/"+aa;
					
				}
				
				
				
				
				
		}
	
				

		

