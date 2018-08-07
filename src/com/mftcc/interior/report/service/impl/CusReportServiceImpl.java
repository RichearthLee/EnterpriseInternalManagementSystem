package com.mftcc.interior.report.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.report.bean.CusReportBean;
import com.mftcc.interior.report.dao.CusReportDao;
import com.mftcc.interior.report.service.CusReportService;

@Service
public class CusReportServiceImpl implements CusReportService{

	@Autowired
	CusReportDao cusReportDao;
	@Override
	public List<CusReportBean> selectCusAndLinkMan() throws Exception {
		List<CusReportBean> cusReportBeans=cusReportDao.selectCusAndLinkMan();
		return cusReportBeans;
	}
	@Override
	public boolean insertCusAndLinkMan(List<CusReportBean> cusReportBeans)
			throws Exception {
		if(cusReportDao.insertCusAndLinkMan(cusReportBeans)){
			return true;
		}else{
			return false;
		}
	}


}
