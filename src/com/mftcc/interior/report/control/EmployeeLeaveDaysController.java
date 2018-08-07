package com.mftcc.interior.report.control;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.interior.report.common.CountDaysOfLeave;
import com.mftcc.interior.report.service.EmployeeLeaveDaysService;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.method.bean.Ipage;

//计算每月员工请假天数
@Controller
@RequestMapping(value="/employeeReport")
public class EmployeeLeaveDaysController extends HttpServlet{
    
	private Map<String,Object> dataMap;

	@Autowired
	private EmployeeLeaveDaysService employeeLeaveDaysService;
	
	@RequestMapping(value="/employeeLeaveReport")
	public ModelAndView MfReportEntranceOpen(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		try {
			List<SysOrg> orgs=employeeLeaveDaysService.selectAllOrg();
			request.setAttribute("orgs", orgs);//查询所有的部门
			//modelAndView.addObject("orgs", new Gson().toJson(orgs));
			modelAndView.addObject("orgs", orgs);
			request.setAttribute("uid", "bdcbb4ca4eb25b4aba342fc88262aaef");
			request.setAttribute("reporttype","D");
			modelAndView.setViewName("report/employee/MfReportEntranceOpen");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	@ResponseBody//将函数返回值作为请求返回值
	@RequestMapping(value="/MfReportEntrance1")
	public Map<String,String> MfReportEntranceOpen(String brs,String op_name,String yearmonth){
		Map<String,String> map=new HashMap<String, String>();
		String sqlCondition="";
		try {
			//获取前台的筛选条件，进行拼接
			if(brs!=""){//部门编号拼接
				sqlCondition="br_no in (";
				String[] brnos=brs.split(",");
				System.err.println(brnos);
				for(int i=1;i<=brnos.length-1;i++){
					sqlCondition=sqlCondition+"'"+brnos[i]+"'"+",";
				}
				sqlCondition=sqlCondition.substring(0,sqlCondition.length()-1);
				sqlCondition=sqlCondition+")";
				
			}
			if(op_name!=""){//员工姓名拼接
				if(sqlCondition!=""){
					sqlCondition=sqlCondition+"OR";
				}
				sqlCondition=sqlCondition+" op_name LIKE '%"+op_name+"%'";
				
			}
			if(yearmonth!=""){//年月拼接
				if(sqlCondition!=""){
					sqlCondition=sqlCondition+"AND";
				}
				sqlCondition=sqlCondition+"yearmonth='"+yearmonth+"'";
			}
			if(sqlCondition==""){//选择条件为空时
				sqlCondition=sqlCondition+"yearmonth='";
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH )+1;
				if(month<10){
					sqlCondition=sqlCondition+year+"-0"+month+"'";
				}else{
					sqlCondition=sqlCondition+year+"-"+month+"'";
				}
			}
			map.put("mgs","true");
			map.put("sqlCondition", sqlCondition);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("mgs","false");
		return map;
	}
	
	
	
	//通过员工请假信息，计算每天请假时间及天数（除去节假日）
	@RequestMapping(value="/countLeaveDay")
	public void countLeaveDay(){
		try {
			//删除数据库中的原有表信息
			employeeLeaveDaysService.deletesumLeaveDays();
				List<LeaveDaysBean> leaveDaysBeans=null;//用于存放计算出的请假数据
				LeaveDaysBean leaveDaysBean=new LeaveDaysBean();
				//提取原始请假数据
				List<LeaveBean> leaveBeans = employeeLeaveDaysService.sumLeaveDays();
				CountDaysOfLeave countDaysOfLeave=new CountDaysOfLeave();
				countDaysOfLeave.setTime("08:30:00", "12:00:00", "13:00:00", "17:30:00");
				for(LeaveBean lds:leaveBeans){
					leaveDaysBeans=countDaysOfLeave.calculateTimeHour(lds.getLeaveBeginTime(),lds.getLeaveEndTime(),leaveDaysBean);
					for(LeaveDaysBean ldbs:leaveDaysBeans){
						ldbs.setLeave_no(lds.getLeaveNo());
						ldbs.setOp_no(lds.getOpNo());
						ldbs.setOp_name(lds.getOpName());
						ldbs.setBr_no(lds.getBrNo());
						ldbs.setBr_name(lds.getBrName());
						ldbs.setLeave_type(lds.getLeaveType());
					}
					System.err.println(leaveDaysBeans);
					employeeLeaveDaysService.insertLeaveDays(leaveDaysBeans);
					
				}
				sumcountLeaveDay();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	//生成请假总表,插入到总表中
	public void sumcountLeaveDay(){
		try {
			List<LeaveDaysFinalBean> leaveDaysFinalBeans=employeeLeaveDaysService.sumcountLeaveDay();
			for(LeaveDaysFinalBean a:leaveDaysFinalBeans){
				a.setAnnual(a.getAnnual()/8);
				a.setBereavement(a.getBereavement()/8);
				a.setCasual(a.getCasual()/8);
				a.setExchange(a.getExchange()/8);
				a.setMarriage(a.getMarriage()/8);
				a.setMaternity(a.getMaternity()/8);
				a.setPaternity(a.getPaternity()/8);
				a.setSick(a.getSick()/8);
				a.setSumdays(a.getSumdays()/8);
			}
			employeeLeaveDaysService.insertsumcountLeaveDay(leaveDaysFinalBeans);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//分页
	@RequestMapping(value="/getLeaveDaysList")
	public void getLeaveDaysList(Ipage ipage ,ModelMap model, HttpSession session){

		try {
			ipage = employeeLeaveDaysService.getLeaveDaysList(ipage);
			model.put("ipage", ipage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//通过员工号查询单个员工的请假信息
	@RequestMapping(value="/searchLeaveDaysByno/{op_no}")
	public String selectLeaveDaysByno(@PathVariable String op_no,ModelMap model){
		try {
			List<LeaveBean> leaveBeans= employeeLeaveDaysService.selectLeaveDaysByno(op_no);
			model.put("leaveBeans", leaveBeans);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/employee/LeaveDaysOfOne";
	}
	
	
	
	
	
	
}