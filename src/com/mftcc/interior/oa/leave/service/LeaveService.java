package com.mftcc.interior.oa.leave.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

public interface LeaveService {
	public List<LeaveBean> getAllLeave();
	
	public boolean addLeave(LeaveBean leaveBean) throws ServiceException;
	public boolean deleteLeave(LeaveBean leaveBean);
	public List<LeaveBean> findLeave(LeaveBean leaveBean);
	public boolean updateLeave(LeaveBean leaveBean);
	public Ipage getLeaveListPage(Ipage ipage) throws Exception;

	

}
