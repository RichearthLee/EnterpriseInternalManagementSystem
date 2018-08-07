package com.mftcc.interior.oa.leave.dao;

import java.util.List;

import com.mftcc.method.bean.Ipage;
import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;

public interface CountDao {

  public List<LeaveDaysFinalBean> getCount(LeaveDaysFinalBean leaveDaysFinalBean);

  public void insertApprove(List<AttendenceBean> lAttendenceBeans);

  public List<AttendenceBean> getAttendenceListPage(Ipage ipage) throws Exception;
  
  public String getAttendenceListCount(Ipage ipage) throws Exception;
  
  public List<AttendenceBean> getAttendence(AttendenceBean attendenceBean);
	
  public List<AttendenceBean> getAttendence1(AttendenceBean attendenceBean);
  
  public int updateAttendence(AttendenceBean attendenceBean);
}
