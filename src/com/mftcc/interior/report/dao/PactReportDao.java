package com.mftcc.interior.report.dao;

import java.util.List;

import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.report.bean.PactOrgRuarterReportBean;
import com.mftcc.interior.report.bean.PactRuarterReportBean;
import com.mftcc.interior.report.bean.PactReportBean;

public interface PactReportDao {

	List<PactReportBean> selectAllPactInfo() throws Exception;
	
	int updatePactInfoFlag()throws Exception;

	void insertPactInfo(List<PactReportBean> pactReportBeans) throws Exception;

	String selectEmployeeByNo(String string) throws Exception;

	List<PactRuarterReportBean> selectPactRuarter() throws Exception;

	boolean insertPactRuarter(List<PactRuarterReportBean> pDateReportBeans) throws Exception;

	int deletePactRuarter() throws Exception;

	List<PactRuarterReportBean> selectPactUserRuarter() throws Exception;

	List<PactRuarterReportBean> selectPactUserRuarterSum() throws Exception;

	boolean insertPactUserRuarterSum(List<PactRuarterReportBean> pDateReportBeans) throws Exception;

	List<PactOrgRuarterReportBean> selectPactOrgRuarter() throws Exception;

	

}
