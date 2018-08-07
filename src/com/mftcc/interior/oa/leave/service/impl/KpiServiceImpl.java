package com.mftcc.interior.oa.leave.service.impl;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.KpiBean;
import com.mftcc.interior.oa.leave.bean.KpiFlagBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.PactKpiBean;
import com.mftcc.interior.oa.leave.dao.KpiDao;
import com.mftcc.interior.oa.leave.service.KpiService;
import com.mftcc.method.bean.Ipage;
@Service


public class KpiServiceImpl implements KpiService {

	@Autowired
	private KpiDao kpiDao;
	private Logger log=Logger.getLogger(KpiServiceImpl.class);
	
	@Override
	public Ipage getKpiAttendenceListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<KpiBean> kpi = kpiDao.getKpiAttendenceListPage(ipage);//取数据
					
					for(int i=0 ; i< kpi.size();i++){
						String workTime = kpi.get(i).getWorkTimeMarks();
						String workTimeFlag= kpi.get(i).getWorkTimeMarksFlag();
						String workTimeMarks=changeType(workTime,workTimeFlag);
						kpi.get(i).setWorkTimeMarks(workTimeMarks);
						String leaveTime=kpi.get(i).getLeaveMarks();
						if(leaveTime==null){
						   leaveTime="0";
						}
						String leaveTime1=kpi.get(i).getLeaveMarksFlag();
						String leaveMarks=changeType1(leaveTime,leaveTime1);
						kpi.get(i).setLeaveMarks(leaveMarks);
						String extraWork = kpi.get(i).getExtraWorkMarks();
						String extraWork1=kpi.get(i).getExtraWorkMarksFlag();
						String extraWorkMarks=changeType(extraWork,extraWork1);
						kpi.get(i).setExtraWorkMarks(extraWorkMarks);
						String late = kpi.get(i).getLateMarks();
						String late1=kpi.get(i).getLateMarksFlag();
						String lateMarks=changeType1(late,late1);
						kpi.get(i).setLateMarks(lateMarks);
						String eLeave = kpi.get(i).getEleaveMarks();
						String eLeave1=kpi.get(i).getEleaveMarksFlag();
						String eLeaveMarks=changeType1(eLeave,eLeave1);
						kpi.get(i).setEleaveMarks(eLeaveMarks);
						String goOut = kpi.get(i).getGoOutMarks();
						String goOut1=kpi.get(i).getGoOutMarksFlag();
						String goOutMarks=changeType1(goOut,goOut1);
						kpi.get(i).setGoOutMarks(goOutMarks);
						String neglect = kpi.get(i).getNeglectMarks();
						String neglect1=kpi.get(i).getNeglectMarksFlag();
						String neglectMarks=changeType1(neglect,neglect1);
						kpi.get(i).setNeglectMarks(neglectMarks);
						float Marks1=Float.parseFloat(workTimeMarks);
						float Marks2=Float.parseFloat(leaveMarks);
						float Marks3=Float.parseFloat(extraWorkMarks);
						float Marks4=Float.parseFloat(lateMarks);
						float Marks5=Float.parseFloat(eLeaveMarks);
						float Marks6=Float.parseFloat(goOutMarks);
						float Marks7=Float.parseFloat(neglectMarks);
						float Marks=Marks1+Marks3+Marks6-Marks2-Marks4-Marks5-Marks7;
						DecimalFormat  fnum  =   new  DecimalFormat("##0.00");    
						String   marks=fnum.format(Marks);   
						kpi.get(i).setKpi(marks);
					}
					
			int totalpage = 0,allRecord = 0;
			if (kpi == null) {
			}else{
				allRecord = Integer.parseInt(kpiDao.getKpiAttendenceListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(kpi);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getKpiEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	
	
	 String changeType(String str1,String str2){
		    float  btn= Float.parseFloat(str1.substring(0,str1.length()-3));
			float btn1=Float.parseFloat(str1.substring(str1.length()-2,str1.length()));
			float b=btn1/60;
			float Time=btn+b;
			float Time1=Float.parseFloat(str2);
			float Marks1=Time*Time1;
			DecimalFormat  fnum  =   new  DecimalFormat("##0.00");    
			String   Marks=fnum.format(Marks1);   
		 
		 
		 return Marks;
				
	}
	 
	String changeType1(String str1,String str2){
		float Time=Float.parseFloat(str1);
		float Time1=Float.parseFloat(str2);
		float Marks1=Time*Time1;
		DecimalFormat  fnum  =   new  DecimalFormat("##0.00");    
		String   Marks=fnum.format(Marks1);   
		return Marks;
	}
	
	@Override
	public List<KpiFlagBean> findFlag(KpiFlagBean kpiFlagBean) {
		List<KpiFlagBean> flag=kpiDao.getFlag(kpiFlagBean);
		return flag;
	}
	
	@Override
	public boolean updateflag(KpiFlagBean kpiFlagBean) {
		int leaveUpId = kpiDao.updateflag(kpiFlagBean);
		
		if(leaveUpId != 1)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Ipage getPactKpiListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<PactKpiBean> pact = kpiDao.getPactKpiListPage(ipage);//取数据
			for(int i=0 ; i< pact.size();i++){
				String bonusFact1=pact.get(i).getBonusFact();
				String bonus1=pact.get(i).getBonus();
				String bonusFact=bonus(bonusFact1);
				String bonus=bonus(bonus1);
				pact.get(i).setBonusFact(bonusFact);
				pact.get(i).setBonus(bonus);
				
				
			}
			
					
			int totalpage = 0,allRecord = 0;
			if (pact == null) {
			}else{
				allRecord = Integer.parseInt(kpiDao.getPactKpiListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(pact);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getKpiEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	
	String bonus(String str){
		float a=Float.parseFloat(str);
		a=(float) (a*0.05);
		DecimalFormat  fnum  =   new  DecimalFormat("##0.00");    
		String   aa=fnum.format(a);   
		return aa;
	}
	
	
}
