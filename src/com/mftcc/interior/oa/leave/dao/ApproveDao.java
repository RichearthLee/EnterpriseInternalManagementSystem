package com.mftcc.interior.oa.leave.dao;

import java.util.List;

import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;

import com.mftcc.method.bean.Ipage;

public interface ApproveDao {
	

	public List<ApproveBean> getApproveListPage(Ipage ipage);

	public String getApproveListCount(Ipage ipage);
	public List<ApproveBean> getApprove(ApproveBean approveBean);

	public int updateApprove(ApproveBean approveBean);

	public int updateApprove1(ApproveBean approveBean);


}
