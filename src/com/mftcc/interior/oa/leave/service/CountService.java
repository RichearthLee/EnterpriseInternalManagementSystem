package com.mftcc.interior.oa.leave.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.method.bean.Ipage;

public interface CountService {

	List<LeaveDaysFinalBean> findCount(LeaveDaysFinalBean leaveDaysFinalBean);

	public Boolean addattendence(List<AttendenceBean> lAttendenceBeans)throws ServiceException;

	public Ipage getAttendenceListPage(Ipage ipage) throws Exception;
	
	public List<AttendenceBean> findAttendence(AttendenceBean attendenceBean);
	
	public List<AttendenceBean> findAttendence1(AttendenceBean attendenceBean);
	
	public boolean updateAttendence(AttendenceBean attendenceBean);
}
