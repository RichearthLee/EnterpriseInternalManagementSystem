package com.mftcc.interior.report.dao;

import java.util.List;

import com.mftcc.interior.report.bean.CusReportBean;

public interface CusReportDao {

	List<CusReportBean> selectCusAndLinkMan() throws Exception;

	boolean insertCusAndLinkMan(List<CusReportBean> cusReportBeans) throws Exception;

}
