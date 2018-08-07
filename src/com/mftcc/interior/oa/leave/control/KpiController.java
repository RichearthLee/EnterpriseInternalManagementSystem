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
import com.mftcc.interior.oa.leave.bean.KpiFlagBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.service.ApproveService;
import com.mftcc.interior.oa.leave.service.KpiService;
import com.mftcc.method.bean.Ipage;

@Controller

public class KpiController {
	
	
	@Autowired
	private KpiService kpiService;
	private Logger log=Logger.getLogger(ApproveController.class);
	
	@RequestMapping("/kpiattendence")
	public String kpiattendence()
	{
		return "leave/kpiattendence";
	}
	
	@RequestMapping("/kpipact")
	public String kpipact()
	{
		return "leave/kpipact";
	}
	
	@RequestMapping("/getKpiAttendenceListPage")
	public void getKpiAttendenceListPage(Ipage ipage, ModelMap model){

		try {

			ipage = kpiService.getKpiAttendenceListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/updateFlag")
	public String flagInfo(KpiFlagBean kpiFlagBean,ModelMap model)
	{
		
		List<KpiFlagBean> flaglist=findFlag(kpiFlagBean);
		model.put("flag", flaglist.get(0));
		
		return "leave/flagInfo";
	}
	
	@RequestMapping("/findFlag")
	public List<KpiFlagBean> findFlag(KpiFlagBean kpiFlagBean) {
		List<KpiFlagBean> flagList =  kpiService.findFlag(kpiFlagBean);
		
	    return flagList;
		
	}
	
	
	
	@RequestMapping("/updateflag")
	public String updateflag(KpiFlagBean kpiFlagBean,ModelMap model)
	{

		Boolean result = kpiService.updateflag(kpiFlagBean);
		System.err.print(result);

		if(result)
		{	//shibai
			model.put(SystemParm.ERROR_CODE, result);
		}
		else{//成功
			model.put(SystemParm.ERROR_CODE, "0");
		}
		return "leave/leaveList";
	} 
	@RequestMapping("/getPactKpiListPage")
	public void getPactKpiListPage(Ipage ipage, ModelMap model){

		try {

			ipage = kpiService.getPactKpiListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	
}
