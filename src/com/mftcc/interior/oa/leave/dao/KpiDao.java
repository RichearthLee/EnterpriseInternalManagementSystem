package com.mftcc.interior.oa.leave.dao;

import java.util.List;

import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.KpiBean;
import com.mftcc.interior.oa.leave.bean.KpiFlagBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.PactKpiBean;
import com.mftcc.method.bean.Ipage;

public interface KpiDao {

	
	  public List<KpiBean> getKpiAttendenceListPage(Ipage ipage) throws Exception;
	  
	  public String getKpiAttendenceListCount(Ipage ipage) throws Exception;
	  
	  public List<KpiFlagBean> getFlag(KpiFlagBean kpiFlagBean);

	  public int updateflag(KpiFlagBean kpiFlagBean);
	  
	  public List<PactKpiBean> getPactKpiListPage(Ipage ipage) throws Exception;
	  
	  public String getPactKpiListCount(Ipage ipage) throws Exception;
}
