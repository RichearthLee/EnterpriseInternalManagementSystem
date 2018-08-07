package com.mftcc.interior.oa.leave.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.method.bean.Ipage;

public interface ApproveService {

	
	public Ipage getApproveListPage(Ipage ipage) throws Exception;
	public List<ApproveBean> findApprove(ApproveBean approveBean);
	public boolean updateApprove(ApproveBean approve);

}
