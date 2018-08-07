package com.mftcc.interior.report.service.impl;

import java.util.List;
















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.report.bean.PactOrgRuarterReportBean;
import com.mftcc.interior.report.bean.PactRuarterReportBean;
import com.mftcc.interior.report.bean.PactReportBean;
import com.mftcc.interior.report.dao.PactReportDao;
import com.mftcc.interior.report.service.PactReportService;

@Service
public class PactReportServiceImpl implements PactReportService{

	@Autowired
	private PactReportDao pactBaseDao;
	@Override
	public List<PactReportBean> selectAllPactInfo() throws Exception {
	 
		List<PactReportBean> pactReportBeans=pactBaseDao.selectAllPactInfo();
		return pactReportBeans;
	}
	@Override
	public void insertPactInfo(List<PactReportBean> pactReportBeans)
			throws Exception {
		pactBaseDao.insertPactInfo(pactReportBeans);
	}
	@Override
	public void updatePactInfo() throws Exception {
		pactBaseDao.updatePactInfoFlag();
	}
	@Override
	public String selectEmployeeByNo(String no) throws Exception {
		String name=pactBaseDao.selectEmployeeByNo(no);
		return name;
	}
	@Override
	public List<PactRuarterReportBean> selectPactRuarter() throws Exception {
		List<PactRuarterReportBean> pactDateReportBeans=pactBaseDao.selectPactRuarter();
		return pactDateReportBeans;
	}
	@Override
	public boolean insertPactRuarter(List<PactRuarterReportBean> pDateReportBeans)
			throws Exception {
		if(pactBaseDao.insertPactRuarter(pDateReportBeans)){
			return true;
		}
		return false;
	}
	@Override
	public boolean deletePactRuarter() throws Exception {
		if(pactBaseDao.deletePactRuarter()>=0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<PactRuarterReportBean> selectPactUserRuarter() throws Exception {
		List<PactRuarterReportBean> pactRuarterReportBeans=pactBaseDao.selectPactUserRuarter();
		return pactRuarterReportBeans;
	}
	@Override
	public List<PactRuarterReportBean> selectPactUserRuarterSum()throws Exception {
		List<PactRuarterReportBean> pactRuarterReportBeans=pactBaseDao.selectPactUserRuarterSum();
		return pactRuarterReportBeans;
	}
	@Override
	public boolean insertPactUserRuarterSum(List<PactRuarterReportBean> pDateReportBeans) throws Exception{
		if(pactBaseDao.insertPactUserRuarterSum(pDateReportBeans)){
			return true;
		}
		return false;
	}
	@Override
	public List<PactOrgRuarterReportBean> selectPactOrgRuarter() throws Exception {
		 return pactBaseDao.selectPactOrgRuarter();
	}


}
