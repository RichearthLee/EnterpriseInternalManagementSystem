package com.mftcc.interior.report.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面跳转控制
 * @author Administrator
 *
 */
@Controller
public class PageChange {
	//跳转到即时查询主页面
	@RequestMapping(value={"/instantQuery"})
	public String InstantQuery(){
		return "report/selectList";
	}
	//跳转到统计报表主页面
	@RequestMapping(value={"/reportForm"})
	public String ReportForm(){
		return "report/reportList";
	}
	//跳转到分析主页面
	@RequestMapping(value="/analysis")
	public String Analysis(){
		return "report/analysisList";
	}
	//跳转到分析报告主页面
	@RequestMapping(value="/analysisReport")
	public String analysisReport(){
		return "report/employee";/*analysisReport*/
	}
	//跳转到员工请假查询页面
	@RequestMapping(value="/employeeList")
	public String employeeList(/*HttpSession session*/){
		//取当前登陆用户信息
//		UserBean user = (UserBean) session.getAttribute("user");
//		System.err.println(user);
		return "report/employee/employeeLeaveList";
	}
	//跳转到部门员工占比页面
	@RequestMapping(value="/orgPercent")
	public String orgPercent(){
		return "report/employee/orgPercent";
	}
	
	@RequestMapping(value="/newFile")
	public String newFile(){
		return "report/pact/NewFile";
	}
}
