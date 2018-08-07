package com.mftcc.interior.report.service;

import java.util.List;

import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.method.bean.Ipage;

public interface EmployeeLeaveDaysService {

	List<LeaveBean> sumLeaveDays() throws Exception;
	
    void insertLeaveDays(List<LeaveDaysBean> leaveDaysBeans) throws Exception;

	Ipage getLeaveDaysList(Ipage ipage) throws Exception;

	boolean deletesumLeaveDays() throws Exception;

	List<LeaveBean> selectLeaveDaysByno(String op_no) throws Exception;

	List<SysOrg> selectAllOrg() throws Exception;

	List<LeaveDaysFinalBean> sumcountLeaveDay() throws Exception;

	void insertsumcountLeaveDay(List<LeaveDaysFinalBean> leaveDaysFinalBeans) throws Exception;

}
