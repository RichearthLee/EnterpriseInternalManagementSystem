package com.mftcc.interior.oa.leave.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.ApproveLowValueBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.LowValueAmountBean;
import com.mftcc.interior.oa.leave.bean.LowValueBean;
import com.mftcc.interior.oa.leave.dao.LowValueDao;
import com.mftcc.interior.oa.leave.service.LowValueService;
import com.mftcc.method.bean.Ipage;

@Service




public class LowValueServiceImpl implements LowValueService{

	@Autowired
	private LowValueDao lowValueDao;
	private Logger log=Logger.getLogger(LowValueServiceImpl.class);
	
	
	
	@Override
	public boolean addLowValue(LowValueBean lowValueBean) throws ServiceException {
		String temp=String.valueOf(System.currentTimeMillis());
		
		ApproveLowValueBean approveLowValueBean=new ApproveLowValueBean();
		
		
		lowValueBean.setLowValueNo(temp);
		lowValueDao.insertLowValue(lowValueBean);
		
		approveLowValueBean.setApproveLowValueNo('a'+temp);
		approveLowValueBean.setApplyType(lowValueBean.getLowValueType());
		approveLowValueBean.setOpNo(lowValueBean.getOpNo());
		approveLowValueBean.setOpName(lowValueBean.getOpName());
		approveLowValueBean.setLowValueApplyNo(lowValueBean.getLowValueNo());
		approveLowValueBean.setBrName(lowValueBean.getBrName());
		lowValueDao.insertLowValueApprove(approveLowValueBean);
		
		
		
		return true;
		
	}
	
	@Override
	public boolean deleteLowValue(LowValueBean lowValueBean) {
		// TODO Auto-generated method stub
    int leaveDeleteId = lowValueDao.deleteLowValue(lowValueBean);
	int deleteApprove = lowValueDao.deleteApproveLowValue(lowValueBean);	
		if(leaveDeleteId != 1)
		{
			return false;
		}
		return true;
	}

	@Override
	public List<LowValueBean> findLowValue(LowValueBean lowValueBean) {
		List<LowValueBean> lowValue=lowValueDao.getLowValue(lowValueBean);
		return lowValue;
	}

	@Override
	public boolean updateLowValue(LowValueBean lowValueBean) {
		int leaveUpId = lowValueDao.updateLowValue(lowValueBean);
		
		if(leaveUpId != 1)
		{
			return false;
		}
		return true;
	}

	@Override
	public Ipage getLowValueListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<LowValueBean> lowValue = lowValueDao.getLowValueListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (lowValue == null) {
			}else{
				allRecord = Integer.parseInt(lowValueDao.getLowValueListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(lowValue);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	
	@Override
	public Ipage getApproveLowValueListPage(Ipage ipage) {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<ApproveLowValueBean> approve = lowValueDao.getApproveLowValueListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (approve == null) {
			}else{
				allRecord = Integer.parseInt(lowValueDao.getApproveLowValueListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(approve);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw e;
		}
		return ipage;
	}
	
	@Override
	public List<ApproveLowValueBean> findApproveLowValue(ApproveLowValueBean approveLowValueBean) {
		List<ApproveLowValueBean> approve=lowValueDao.getApproveLowValue(approveLowValueBean);
		
		return approve;
	}
	@Override
	public boolean updateApproveLowValue(ApproveLowValueBean approveLowValueBean) {
		int approveUpId = lowValueDao.updateApproveLowValue(approveLowValueBean);
		int approveUpId1= lowValueDao.updateApproveLowValue1(approveLowValueBean);
		if(approveUpId != 1 && approveUpId1!= 1)
		{
			return false;
		}
		return true;
	}
	

	@Override
	public Ipage getManageListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<LowValueAmountBean> lowValue = lowValueDao.getManageListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (lowValue == null) {
			}else{
				allRecord = Integer.parseInt(lowValueDao.getManageListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(lowValue);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	

	@Override
	public List<LowValueAmountBean> findLowValueAmount(LowValueAmountBean lowValueAmountBean) {
		List<LowValueAmountBean> lowValue=lowValueDao.getLowValueAmount(lowValueAmountBean);
		return lowValue;
	}

	@Override
	public boolean updateLowValueAmount(LowValueAmountBean lowValueAmountBean) {
		int leaveUpId = lowValueDao.updateLowValueAmount(lowValueAmountBean);
		
		if(leaveUpId != 1)
		{
			return false;
		}
		return true;
	}
	
}
