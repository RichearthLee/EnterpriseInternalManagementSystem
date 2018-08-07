package com.mftcc.interior.oa.leave.dao;

import java.util.List;

import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.ApproveLowValueBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.LowValueAmountBean;
import com.mftcc.interior.oa.leave.bean.LowValueBean;
import com.mftcc.method.bean.Ipage;


public interface LowValueDao {

	
	
	public String insertLowValue(LowValueBean lowValueBean);
	
	public String insertLowValueApprove(ApproveLowValueBean approveLowValueBean);
	
	public int deleteLowValue(LowValueBean lowValueBean);
	public List<LowValueBean> getLowValue(LowValueBean lowValueBean);
	public int updateLowValue(LowValueBean lowValueBean);
	public List<LowValueBean> getLowValueListPage(Ipage ipage) throws Exception;
	String getLowValueListCount(Ipage ipage) throws Exception;
	public int deleteApproveLowValue(LowValueBean lowValueBean);
	
	public List<ApproveLowValueBean> getApproveLowValueListPage(Ipage ipage);

	public String getApproveLowValueListCount(Ipage ipage);
	public List<ApproveLowValueBean> getApproveLowValue(ApproveLowValueBean approveLowValueBean);

	public int updateApproveLowValue(ApproveLowValueBean approveLowValueBean);

	public int updateApproveLowValue1(ApproveLowValueBean approveLowValueBean);
	public List<LowValueAmountBean> getManageListPage(Ipage ipage);
	String getManageListCount(Ipage ipage) throws Exception;
	
	public List<LowValueAmountBean> getLowValueAmount(LowValueAmountBean lowValueAmountBean);
	public int updateLowValueAmount(LowValueAmountBean lowValueAmountBean);
	
}
