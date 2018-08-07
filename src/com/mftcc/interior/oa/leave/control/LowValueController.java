package com.mftcc.interior.oa.leave.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.oa.leave.bean.ApproveLowValueBean;
import com.mftcc.interior.oa.leave.bean.LowValueAmountBean;
import com.mftcc.interior.oa.leave.bean.LowValueBean;
import com.mftcc.interior.oa.leave.service.LowValueService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

@Controller


public class LowValueController {
	
	@Autowired
	private LowValueService lowValueService;
	private Logger log=Logger.getLogger(LowValueController.class);
	
	@RequestMapping("/listlowvalue")
	public String listlowvalue()
	{
		return "leave/listlowvalue";
	}
	@RequestMapping("/addlowvalue")
	public String addlowvalue()
	{
		return "leave/addlowvalue";
	}
	
	
	@RequestMapping("/AddLowValue")
	public void AddLowValue(LowValueBean lowValueBean,ModelMap model)
	{
		 
		 	
	
		 	
		 	
			Boolean result = lowValueService.addLowValue(lowValueBean);
		
			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}

	}

	@RequestMapping("/getLowValueListPage")
	public void getLowValueListPage(Ipage ipage, ModelMap model,HttpSession session){

		try {
			SysUser user = (SysUser) session.getAttribute("sysuser");
			String no=user.getOpNo();
			ipage.setParm0(no);
			ipage = lowValueService.getLowValueListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/lowValueInfo")
	public String lowValueInfo(LowValueBean lowValueBean,ModelMap model)
	{
		
		List<LowValueBean> lowValue=findLowValue(lowValueBean);
		model.put("lowvalue", lowValue.get(0));
		
		return "leave/lowValueInfo";
	}
	@RequestMapping("/deleteLowValue")
	public void deleteLowValue(LowValueBean lowValueBean,ModelMap modelMap)
	{
		
		Boolean result = lowValueService.deleteLowValue(lowValueBean);
		if(result==true){
			modelMap.put(SystemParm.ERROR_CODE, "0");
			modelMap.put(SystemParm.ERROR_MESSAGE, "删除成功！");
		}
		
	}
	
	
	@RequestMapping("/findLowValue")
	public List<LowValueBean> findLowValue(LowValueBean lowValueBean) {
		List<LowValueBean> lowvalueList =  lowValueService.findLowValue(lowValueBean);
		
	    return lowvalueList;
		
	}
	
	@RequestMapping("/updateLowValue")
	public String updateLowValue(LowValueBean lowValueBean,ModelMap model)
	{

			Boolean result = lowValueService.updateLowValue(lowValueBean);
			System.err.print(result);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/listlowvalue";
	
	}
	
	@RequestMapping("/approvelowvalue")
	public String approvelowvalue()	
	{
		return "leave/approvelowvalue";
	}

	@RequestMapping("/getApproveLowValueListPage")
	public void getApproveLowValueListPage(Ipage ipage, ModelMap model){

		try {
			ipage = lowValueService.getApproveLowValueListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	

	@RequestMapping("/approveLowValueInfo")
	public String approvelowvalueInfo(ApproveLowValueBean approveLowValueBean,ModelMap model,HttpSession session)
	{
		
		List<ApproveLowValueBean> approvelowvaluelist=findApproveLowValue(approveLowValueBean);
		model.put("approveLowValue", approvelowvaluelist.get(0));
		
		SysUser user = (SysUser) session.getAttribute("sysuser");
		session.setAttribute("brName1", user.getBrName());
		session.setAttribute("opName1", user.getOpName());
		session.setAttribute("brNo1", user.getBrNo());
		session.setAttribute("opNo1", user.getOpNo());
		
		
		return "leave/approveLowValueInfo";
	}
	



	@RequestMapping("/findApproveLowValue")
	public List<ApproveLowValueBean> findApproveLowValue(ApproveLowValueBean approveLowValueBean) {
		List<ApproveLowValueBean> approveList =  lowValueService.findApproveLowValue(approveLowValueBean);
		
	    return approveList;
		
	}
	
	
	@RequestMapping("/updateApproveLowValue")
	public String updateApproveLowValue(ApproveLowValueBean approveLowValueBean,ModelMap model)
	{
		   /* System.err.println("zhuagtai"+approve.getLeaveState());
		    System.err.println("id:"+approve.getApproveNo());*/
			String a="046";
			approveLowValueBean.setApproveState(a);
			Boolean result = lowValueService.updateApproveLowValue(approveLowValueBean);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/approvelowvalue";
} 
	
	@RequestMapping("/updateApproveLowValue1")
	public String updateApproveLowValue1(ApproveLowValueBean approveLowValueBean,ModelMap model)
	{
		   /* System.err.println("zhuagtai"+approve.getLeaveState());
		    System.err.println("id:"+approve.getApproveNo());*/
			String a="047";
			approveLowValueBean.setApproveState(a);
			Boolean result = lowValueService.updateApproveLowValue(approveLowValueBean);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/approvelowvalue";
} 
	
	@RequestMapping("/lowvaluemanage")
	public String lowvaluemanage()
	{
		return "leave/lowvaluemanage";
	}
	
	@RequestMapping("/getManageListPage")
	public void getManageListPage(Ipage ipage, ModelMap model,HttpSession session){

		try {
			
			ipage = lowValueService.getManageListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/lowvalueup")
	public String lowvalueup(LowValueAmountBean lowValueAmountBean,ModelMap model)
	{
		
		List<LowValueAmountBean> lowValue=findLowValueAmount(lowValueAmountBean);
		model.put("amount", lowValue.get(0));
		
		return "leave/lowvalueupinfo";
	}
	
	
	
	@RequestMapping("/findLowValueAmount")
	public List<LowValueAmountBean> findLowValueAmount(LowValueAmountBean lowValueAmountBean) {
		List<LowValueAmountBean> lowvalueList =  lowValueService.findLowValueAmount(lowValueAmountBean);
		
	    return lowvalueList;
		
	}
	
	@RequestMapping("/updatelowvalueamount")
	public String updatelowvalueamount(LowValueAmountBean lowValueAmountBean,ModelMap model)
	{

			Boolean result = lowValueService.updateLowValueAmount(lowValueAmountBean);
			System.err.print(result);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/lowvaluemanage";
	
	}
	
	
	
}