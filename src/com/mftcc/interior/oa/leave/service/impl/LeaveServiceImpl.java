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
import com.mftcc.interior.oa.leave.dao.LeaveDao;
import com.mftcc.interior.oa.leave.service.LeaveService;
import com.mftcc.method.bean.Ipage;

@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private LeaveDao leaveDao;
	private Logger log=Logger.getLogger(LeaveServiceImpl.class);
	@Override
	public List<LeaveBean> getAllLeave() {
		List<LeaveBean> leave=leaveDao.getAllLeave();
		return leave;
	}
	
	@Override
	public boolean addLeave(LeaveBean leaveBean) throws ServiceException {
		String temp=String.valueOf(System.currentTimeMillis());
		
		ApproveBean approveBean=new ApproveBean();
		
		
		leaveBean.setLeaveNo(temp);
		leaveDao.insertLeave(leaveBean);
		
		approveBean.setApproveNo('a'+temp);
		approveBean.setApproveType(leaveBean.getLeaveType());
		approveBean.setOpNo(leaveBean.getOpNo());
		approveBean.setOpName(leaveBean.getOpName());
		approveBean.setLeaveNo(leaveBean.getLeaveNo());
		approveBean.setBrName(leaveBean.getBrName());
		leaveDao.insertApprove(approveBean);
		
		
		
		return true;
		
	}
	

	@Override
	public boolean deleteLeave(LeaveBean leaveBean) {
		// TODO Auto-generated method stub
    int leaveDeleteId = leaveDao.deleteLeave(leaveBean);
	int deleteApprove = leaveDao.deleteApprove(leaveBean);	
		if(leaveDeleteId != 1)
		{
			return false;
		}
		return true;
	}

	@Override
	public List<LeaveBean> findLeave(LeaveBean leaveBean) {
		List<LeaveBean> leave=leaveDao.getLeave(leaveBean);
		return leave;
	}

	@Override
	public boolean updateLeave(LeaveBean leaveBean) {
		int leaveUpId = leaveDao.updateLeave(leaveBean);
		
		if(leaveUpId != 1)
		{
			return false;
		}
		return true;
	}

	@Override
	public Ipage getLeaveListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<LeaveBean> leave = leaveDao.getLeaveListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (leave == null) {
			}else{
				allRecord = Integer.parseInt(leaveDao.getLeaveListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(leave);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	}

