package com.mftcc.interior.report.service;

import java.util.List;

import com.mftcc.interior.report.bean.PactOrgRuarterReportBean;
import com.mftcc.interior.report.bean.PactRuarterReportBean;
import com.mftcc.interior.report.bean.PactReportBean;

public interface PactReportService {

	List<PactReportBean> selectAllPactInfo() throws Exception;

	void insertPactInfo(List<PactReportBean> pactReportBeans)throws Exception;

	void updatePactInfo() throws Exception;

	String selectEmployeeByNo(String no) throws Exception;

	List<PactRuarterReportBean> selectPactRuarter() throws Exception;

	boolean insertPactRuarter(List<PactRuarterReportBean> pDateReportBeans) throws Exception;

	boolean deletePactRuarter() throws Exception;

	List<PactRuarterReportBean> selectPactUserRuarter() throws Exception;

	List<PactRuarterReportBean> selectPactUserRuarterSum() throws Exception;


	boolean insertPactUserRuarterSum(List<PactRuarterReportBean> pDateReportBeans) throws Exception;

	List<PactOrgRuarterReportBean> selectPactOrgRuarter() throws Exception;

}
