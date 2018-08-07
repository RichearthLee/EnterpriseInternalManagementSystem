package com.mftcc.interior.report.control;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mftcc.interior.report.bean.CusReportBean;
import com.mftcc.interior.report.service.CusReportService;
import com.mftcc.interior.report.service.EmployeeLeaveDaysService;
import com.mftcc.interior.sys.bean.SysOrg;
/**
 * 客户基本信息处理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/cusReport")
public class CusReportController extends HttpServlet{
	@Autowired
	private CusReportService cusBaseInfoService;
	@Autowired
	private EmployeeLeaveDaysService employeeLeaveDaysService;
	
	@RequestMapping(value="/selectAllCusInfo")
	public String selectAllCusInfo(){
		
		return "report/cus_baseinfoList";
	}
	/*@RequestMapping(value="/PmsUserFilter")
	public String PmsUserFilter(){
		
		return "report/mySearch";
	}*/
	
	
	//提取客户信息关联客户联系人(批量)
	@RequestMapping(value="/selectCusAndLinkMan")
	public void selectCusAndLinkMan(){
		try {
			List<CusReportBean> cusReportBeans = cusBaseInfoService.selectCusAndLinkMan();
			//把查询到的客户与联系人的数据关联
			if(cusBaseInfoService.insertCusAndLinkMan(cusReportBeans)){
				System.err.println("insertCusAndLinkMan sucess!");
			}
			for(CusReportBean obj:cusReportBeans){
				System.err.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/cusInfoReport")
	public ModelAndView cusInfoReport(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		try {
			List<SysOrg> orgs=employeeLeaveDaysService.selectAllOrg();
			System.err.println(orgs);
			request.setAttribute("orgs", orgs);//查询所有的部门
			modelAndView.addObject("orgs", orgs);
			request.setAttribute("uid", "d0385cd663719a0f0e1c3f01be6a5401");
			request.setAttribute("reporttype","D");
			modelAndView.setViewName("report/cus/cusInfoReport");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

}
