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
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.service.LeaveService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

@Controller
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	private Logger log=Logger.getLogger(LeaveController.class);
	
	@RequestMapping("/application")
	public String application(HttpSession session)
	{
		 
		SysUser user = (SysUser) session.getAttribute("sysuser");
		session.setAttribute("brName", user.getBrName());
		session.setAttribute("opName", user.getOpName());
		session.setAttribute("brNo", user.getBrNo());
		session.setAttribute("opNo", user.getOpNo());
		return "leave/application";
	}
	
	
	
	
	@RequestMapping("/listleave")
	public String listleave()
	{
		return "leave/listleave";
	}
	
	
	@RequestMapping("/addleave")
	public String addleave()
	{   
		return "leave/addleave";
	}
	
	
	
	
	
	@RequestMapping("/leaveInfo")
	public String leaveInfo(LeaveBean leaveBean,ModelMap model)
	{
		
		List<LeaveBean> leavelist=findLeave(leaveBean);
		model.put("leave", leavelist.get(0));
		
		return "leave/leaveInfo";
	}
	@RequestMapping("/deleteLeave")
	public void deleteLeave(LeaveBean leaveBean,ModelMap modelMap)
	{
		
		Boolean result = leaveService.deleteLeave(leaveBean);
		if(result==true){
			modelMap.put(SystemParm.ERROR_CODE, "0");
			modelMap.put(SystemParm.ERROR_MESSAGE, "删除成功！");
		}
		
	}
	@RequestMapping("/getAllLeave")
	public String getAllLeave() {
		
		return "leave/listleave";
		
	}
	@RequestMapping("/findleave")
	public List<LeaveBean> findLeave(LeaveBean leaveBean) {
		List<LeaveBean> leaveList =  leaveService.findLeave(leaveBean);
		
	    return leaveList;
		
	}

	@RequestMapping("/AddLeave")
	public void AddLeave(LeaveBean leaveBean,ModelMap model)
	{
		 	String begin =leaveBean.getLeaveBeginTime();
		 	//2018-04-11 06:11
		 	String[] begins=begin.split(" ");
		
		 	//02:20
		 	if(begins[1].compareTo("8:30")<0){
		 		begins[1]="8:30";
		 	}
		 	begin=begins[0]+' '+begins[1];
		 	leaveBean.setLeaveBeginTime(begin);
		 	String end=leaveBean.getLeaveEndTime();
		 	String[] ends =begin.split(" ");
		 	if(ends[1].compareTo("17:30")>0){
		 		ends[1]="17:30";
		 	}
		 	end=ends[0]+' '+ends[1];
		 	leaveBean.setLeaveEndTime(end);
		 	
		 	
		 	
		 	
			Boolean result = leaveService.addLeave(leaveBean);
		
			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}

	}

	

	
		
		@RequestMapping("/updateLeave")
	public String updateLeave(LeaveBean leave,ModelMap model)
	{

			Boolean result = leaveService.updateLeave(leave);
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
		@RequestMapping("/getLeaveListPage")
		public void getLeaveListPage(Ipage ipage, ModelMap model,HttpSession session){

			try {
				SysUser user = (SysUser) session.getAttribute("sysuser");
				String no=user.getOpNo();
				ipage.setParm0(no);
				ipage = leaveService.getLeaveListPage(ipage);
				model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
			} catch (Exception e) {
				log.error("查询分页失败",e);
				model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
			}
			model.put("ipage", ipage);
		}
}
