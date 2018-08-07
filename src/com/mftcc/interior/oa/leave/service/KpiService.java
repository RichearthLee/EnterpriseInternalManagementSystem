package com.mftcc.interior.oa.leave.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.KpiFlagBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

public interface KpiService {

	public Ipage getKpiAttendenceListPage(Ipage ipage) throws Exception;
	public List<KpiFlagBean> findFlag(KpiFlagBean kpiFlagBean);
	public boolean updateflag(KpiFlagBean kpiFlagBean);
	public Ipage getPactKpiListPage(Ipage ipage) throws Exception;
}
