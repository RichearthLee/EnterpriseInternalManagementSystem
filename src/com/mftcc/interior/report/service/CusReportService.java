package com.mftcc.interior.report.service;

import java.util.List;

import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.report.bean.CusReportBean;

public interface CusReportService {

	List<CusReportBean> selectCusAndLinkMan() throws Exception;

	boolean insertCusAndLinkMan(List<CusReportBean> cusReportBeans) throws Exception;

	
}
