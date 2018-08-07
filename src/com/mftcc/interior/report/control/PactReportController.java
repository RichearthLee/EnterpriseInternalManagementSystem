package com.mftcc.interior.report.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mftcc.interior.report.bean.PactOrgRuarterReportBean;
import com.mftcc.interior.report.bean.PactRuarterReportBean;
import com.mftcc.interior.report.bean.PactReportBean;
import com.mftcc.interior.report.service.EmployeeLeaveDaysService;
import com.mftcc.interior.report.service.PactReportService;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.common.util.MathExtend;

@Controller
@RequestMapping(value="/pactReport")
public class PactReportController {
	
	@Autowired
	private PactReportService pactBaseService;
	@Autowired
	private EmployeeLeaveDaysService employeeLeaveDaysService;
	//合同表首次提取数据（合同、相关员工）
	@RequestMapping(value="/exchangePactInfo")
	public void selectPactInfo(){
		try {
			List<PactReportBean> pactReportBeans= pactBaseService.selectAllPactInfo();
			if(pactReportBeans.size()>0){
				pactBaseService.updatePactInfo();
				for(int i=0;i<pactReportBeans.size();i++){
					String[] temp=pactReportBeans.get(i).getBeforeSale().split(",");
					//修改编号为姓名
					if(temp[0]!=""){
						String name=pactBaseService.selectEmployeeByNo(temp[0]);
						pactReportBeans.get(i).setBeforeSale(name);
					}
					
					temp=pactReportBeans.get(i).getAfterSale().split(",");
					if(temp[0]!=""){
						String name=pactBaseService.selectEmployeeByNo(temp[0]);
						pactReportBeans.get(i).setAfterSale(name);
					}
					temp=pactReportBeans.get(i).getFocusPerson().split(",");
					if(temp[0]!=""){
						String name=pactBaseService.selectEmployeeByNo(temp[0]);
						pactReportBeans.get(i).setFocusPerson(name);
					}
				}
				pactBaseService.insertPactInfo(pactReportBeans);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//合同季度提取、计算数据
	@RequestMapping(value="/selectPactRuarter")
	public void selectPactQuarter(){
		try {
			List<PactRuarterReportBean> pDateReportBeans = pactBaseService.selectPactRuarter();
			for(PactRuarterReportBean obj:pDateReportBeans){
				String[] temp=obj.getPactStartDate().split("-");
				obj.setYear(temp[0]);
				obj.setMonth(temp[1]);
			}
			//删除季度表中的原有数据，再插入
			if(pactBaseService.deletePactRuarter()){
				pactBaseService.insertPactRuarter(pDateReportBeans);
			}
			//按季度计算员工合同业务量
			calculatePactQuarter();
			//按月计算部门合同业务量
			//calculateOrgPactQuarter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//按季度计算员工合同业务量
	public void calculatePactQuarter(){
		try {
			//计算员工每个季度合同总额度、总笔数
			List<PactRuarterReportBean> pDateReportBeans=pactBaseService.selectPactUserRuarter();
			//计算每个季度的合同总额度、总笔数
			List<PactRuarterReportBean> pDateReportBeans1=pactBaseService.selectPactUserRuarterSum();
			for(PactRuarterReportBean obj:pDateReportBeans){
				for(PactRuarterReportBean o:pDateReportBeans1){
					String objyear=obj.getYear();
					String oyear=o.getYear();
					if(objyear.equals(oyear)){
						
						String result=null;
						String temp=null;
						
						//季度一
						temp=o.getOne_sum();
						if(temp.equals("0")){
							obj.setOne_percent("0");
						}else{
							result=MathExtend.divide(obj.getOne(),temp,2);
							obj.setOne_percent(result);
						}
						temp=o.getOne_count_sum();
						if(temp.equals("0")){
							obj.setOne_count_percent("0");
						}else{
							result=MathExtend.divide(obj.getOne_count(),temp,2);
							obj.setOne_count_percent(result);
						}
						//季度二
						temp=o.getTwo_sum();
						if(temp.equals("0")){
							obj.setTwo_percent("0");
						}else{
							result=MathExtend.divide(obj.getTwo(),temp,2);
							obj.setTwo_percent(result);
						}
						temp=o.getTwo_count_sum();
						if(temp.equals("0")){
							obj.setTwo_count_percent("0");
						}else{
							result=MathExtend.divide(obj.getTwo_count(),temp,2);
							obj.setTwo_count_percent(result);
						}
						//季度三
						temp=o.getThree_sum();
						if(temp.equals("0")){
							obj.setThree_percent(result);
						}else{
							result=MathExtend.divide(obj.getThree(),temp,2);
							obj.setThree_percent(result);
						}
						temp=o.getThree_count_sum();
						if(temp.equals("0")){
							obj.setThree_count_percent("0");
						}else{
							result=MathExtend.divide(obj.getThree_count(),temp,2);
							obj.setThree_count_percent(result);
						}
						//季度四
						temp=o.getFour_sum();
						if(temp.equals("0")){
							obj.setFour_percent("0");
						}else{
							result=MathExtend.divide(obj.getFour(),temp,2);
							obj.setFour_percent(result);
						}
						temp=o.getFour_count_sum();
						if(temp.equals("0")){
							obj.setFour_count_percent("0");
						}else{
							result=MathExtend.divide(obj.getFour_count(),temp,2);
							obj.setFour_count_percent(result);
						}
						
					}
				}
			}
			boolean flag=pactBaseService.insertPactUserRuarterSum(pDateReportBeans);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//按月计算合同部门业务量、计算同比增长
	/*private void calculateOrgPactQuarter(){
		try {
			List<PactOrgRuarterReportBean> pactOrgRuarterReportBeans=pactBaseService.selectPactOrgRuarter();
			String now=pactOrgRuarterReportBeans.get(0).getYear();
			for(PactOrgRuarterReportBean obj:pactOrgRuarterReportBeans){
				//String temp1=obj.getYearmonth();
				String nowYear=obj.getYear();
				if(nowYear.equals(now)){
					obj.setFee_growth("1");
					obj.setCount_growth("1");
				}else{
					break;
				}
				//String lastYear=MathExtend.subtract(nowYear,"1");
				for(PactOrgRuarterReportBean o:pactOrgRuarterReportBeans){
					if
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	//合同预收款、收款计算
	//public void 
	
	
	
	
	
	//跳转到员工签约合同季度数量统计页面
	@RequestMapping(value="/pactUserQuarterNumReport")
	public ModelAndView MfReportEntranceOpen(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		try {
			List<SysOrg> orgs=employeeLeaveDaysService.selectAllOrg();
			request.setAttribute("orgs", orgs);//查询所有的部门
			//modelAndView.addObject("orgs", new Gson().toJson(orgs));
			modelAndView.addObject("orgs", orgs);
			request.setAttribute("uid", "4d48d4039a3720515a91da86458f637e");
			request.setAttribute("reporttype","D");
			modelAndView.setViewName("report/pact/pactUserQuarterNumReport");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	@ResponseBody//将函数返回值作为请求返回值
	@RequestMapping(value="/pactUserQuarterNumReportSelect")
	public Map<String,String> MfReportEntranceOpen(String quarter,String yearmonth){
		Map<String,String> map=new HashMap<String, String>();
		String sqlCondition="";
		try {
			//获取前台的筛选条件，进行拼接
			switch(quarter){
			case "1":map.put("uid", "4d48d4039a3720515a91da86458f637e");break;
			case "2":map.put("uid", "eb6c166c8ad24440ae83a96dca8c3b5f");break;
			case "3":map.put("uid", "4035a2cd36eaa8ce9d1d7f3c33bb79c2");break;
			case "4":map.put("uid", "c64ed1189df8fef89a04e2d9cdfd876b");break;
			default:map.put("uid", "4d48d4039a3720515a91da86458f637e");
			}
			sqlCondition=sqlCondition+yearmonth;
			map.put("mgs","true");
			map.put("sqlCondition", sqlCondition);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("mgs","false");
		return map;
	}
	
	
	
	//跳转到合同到期预警页面
		@RequestMapping(value="/pactEndWarning")
		public ModelAndView PactEndWarning(HttpServletRequest request){
			ModelAndView modelAndView=new ModelAndView();
			try {
				List<SysOrg> orgs=employeeLeaveDaysService.selectAllOrg();
				request.setAttribute("orgs", orgs);//查询所有的部门
				modelAndView.addObject("orgs", orgs);
				request.setAttribute("uid", "67e61ed771e5c4d909dc4826eb499049");
				request.setAttribute("reporttype","D");
				modelAndView.setViewName("report/pact/PactEndWarning");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modelAndView;
		}
		@ResponseBody//将函数返回值作为请求返回值
		@RequestMapping(value="/pactEndWarningFilter")
		public Map<String,String> pactEndWarningFilter(String brs,String dates){
			Map<String,String> map=new HashMap<String, String>();
			String sqlCondition="";
			try {
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
					if(dates!=""){
						if(sqlCondition!=""){
							sqlCondition=sqlCondition+"and";
						}
						sqlCondition=sqlCondition+" date_to_end";
						if(dates.equals("31")){
							sqlCondition=sqlCondition+">31";
						}else{
							sqlCondition=sqlCondition+"<="+dates;
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
}
