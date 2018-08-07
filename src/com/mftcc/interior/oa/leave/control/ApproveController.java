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
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.service.ApproveService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;
@Controller
public class ApproveController {
	
	@Autowired
	private ApproveService approveService;
	private Logger log=Logger.getLogger(ApproveController.class);
	
	@RequestMapping("/approve")
	public String approve()	
	{
		return "leave/approve";
	}

	@RequestMapping("/getApproveListPage")
	public void getApproveListPage(Ipage ipage, ModelMap model){

		try {
			ipage = approveService.getApproveListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	

	@RequestMapping("/approveInfo")
	public String approveInfo(ApproveBean approveBean,ModelMap model,HttpSession session)
	{
		
		List<ApproveBean> approvelist=findApprove(approveBean);
		model.put("approve", approvelist.get(0));
		
		SysUser user = (SysUser) session.getAttribute("sysuser");
		session.setAttribute("brName1", user.getBrName());
		session.setAttribute("opName1", user.getOpName());
		session.setAttribute("brNo1", user.getBrNo());
		session.setAttribute("opNo1", user.getOpNo());
		System.out.print("name"+approvelist.get(0).getOpName());
		
		
		return "leave/approveInfo";
	}
	



	@RequestMapping("/findApprove")
	public List<ApproveBean> findApprove(ApproveBean approveBean) {
		List<ApproveBean> approveList =  approveService.findApprove(approveBean);
		
	    return approveList;
		
	}
	
	
	@RequestMapping("/updateApprove")
	public String updateApprove(ApproveBean approve,ModelMap model)
	{
		   /* System.err.println("zhuagtai"+approve.getLeaveState());
		    System.err.println("id:"+approve.getApproveNo());*/
			String a="046";
			approve.setLeaveState(a);
			Boolean result = approveService.updateApprove(approve);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/approve";
} 
	
	@RequestMapping("/updateApprove1")
	public String updateApprove1(ApproveBean approve,ModelMap model)
	{
		   /* System.err.println("zhuagtai"+approve.getLeaveState());
		    System.err.println("id:"+approve.getApproveNo());*/
			String a="047";
			approve.setLeaveState(a);
			Boolean result = approveService.updateApprove(approve);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/approve";
} 

}
