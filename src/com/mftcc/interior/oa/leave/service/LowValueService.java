package com.mftcc.interior.oa.leave.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.ApproveLowValueBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.LowValueAmountBean;
import com.mftcc.interior.oa.leave.bean.LowValueBean;
import com.mftcc.method.bean.Ipage;


public interface LowValueService {
	
	public boolean addLowValue(LowValueBean lowValueBean) throws ServiceException;
	public boolean deleteLowValue(LowValueBean lowValueBean);
	public List<LowValueBean> findLowValue(LowValueBean lowValueBean);
	public boolean updateLowValue(LowValueBean lowValueBean);
	public Ipage getLowValueListPage(Ipage ipage) throws Exception;
	
	public Ipage getApproveLowValueListPage(Ipage ipage) throws Exception;
	public List<ApproveLowValueBean> findApproveLowValue(ApproveLowValueBean approveLowValueBean);
	public boolean updateApproveLowValue(ApproveLowValueBean approveLowValueBean);
	public Ipage getManageListPage(Ipage ipage) throws Exception;
	
	public List<LowValueAmountBean> findLowValueAmount(LowValueAmountBean lowValueAmountBean);
	public boolean updateLowValueAmount(LowValueAmountBean lowValueAmountBean);
}
