package com.mftcc.interior.oa.leave.dao;

import java.util.List;

import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

public interface LeaveDao {
	public List<LeaveBean> getAllLeave();
	/**
	 * 员工列表
	
	 */
	public String insertLeave(LeaveBean leaveBean);
	public int deleteLeave(LeaveBean leaveBean);
	public List<LeaveBean> getLeave(LeaveBean leaveBean);
	public int updateLeave(LeaveBean leaveBean);
	public List<LeaveBean> getLeaveListPage(Ipage ipage) throws Exception;
	String getLeaveListCount(Ipage ipage) throws Exception;
	public String insertApprove(ApproveBean approveBean);
	public int deleteApprove(LeaveBean leaveBean);



}
