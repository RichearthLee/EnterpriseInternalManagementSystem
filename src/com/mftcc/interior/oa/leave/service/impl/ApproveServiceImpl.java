package com.mftcc.interior.oa.leave.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.dao.ApproveDao;
import com.mftcc.interior.oa.leave.dao.LeaveDao;
import com.mftcc.interior.oa.leave.service.ApproveService;
import com.mftcc.interior.oa.leave.service.LeaveService;
import com.mftcc.method.bean.Ipage;
@Service
public class ApproveServiceImpl implements ApproveService{
	@Autowired
	private ApproveDao approveDao;
	
	private Logger log=Logger.getLogger(ApproveServiceImpl.class);
	@Override
	public Ipage getApproveListPage(Ipage ipage) {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<ApproveBean> approve = approveDao.getApproveListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (approve == null) {
			}else{
				allRecord = Integer.parseInt(approveDao.getApproveListCount(ipage));//员工总数
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
	public List<ApproveBean> findApprove(ApproveBean approveBean) {
		List<ApproveBean> approve=approveDao.getApprove(approveBean);
		
		return approve;
	}
	@Override
	public boolean updateApprove(ApproveBean approveBean) {
		int approveUpId = approveDao.updateApprove(approveBean);
		int approveUpId1= approveDao.updateApprove1(approveBean);
		if(approveUpId != 1 && approveUpId1!= 1)
		{
			return false;
		}
		return true;
	}
	
	
}