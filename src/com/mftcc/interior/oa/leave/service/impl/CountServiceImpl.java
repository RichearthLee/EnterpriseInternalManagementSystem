package com.mftcc.interior.oa.leave.service.impl;

import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.dao.CountDao;
import com.mftcc.interior.oa.leave.dao.LeaveDao;
import com.mftcc.interior.oa.leave.service.CountService;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.method.bean.Ipage;
@Service


public class CountServiceImpl implements CountService {

	@Autowired
	private CountDao countDao;
	private Logger log=Logger.getLogger(CountServiceImpl.class);
	
	@Override
	public List<LeaveDaysFinalBean> findCount(LeaveDaysFinalBean leaveDaysFinalBean) {
		List<LeaveDaysFinalBean> count=countDao.getCount(leaveDaysFinalBean);
		return count;

	}
	@Override
	public Boolean addattendence(List<AttendenceBean> lAttendenceBeans)
			throws ServiceException {
		countDao.insertApprove(lAttendenceBeans);
		
		
		
		return true;
	}

	@Override
	public Ipage getAttendenceListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<AttendenceBean> attendence = countDao.getAttendenceListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (attendence == null) {
			}else{
				allRecord = Integer.parseInt(countDao.getAttendenceListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(attendence);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}
	
	@Override
	public List<AttendenceBean> findAttendence(AttendenceBean attendenceBean) {
		List<AttendenceBean> attendence=countDao.getAttendence(attendenceBean);
		return attendence;
	}
	@Override
	public List<AttendenceBean> findAttendence1(AttendenceBean attendenceBean) {
		List<AttendenceBean> attendence=countDao.getAttendence1(attendenceBean);
		return attendence;
	}
	@Override
	public boolean updateAttendence(AttendenceBean attendenceBean) {
        int attendenceUpId = countDao.updateAttendence(attendenceBean);
		
		if(attendenceUpId != 1)
		{
			return false;
		}
		return true;
	}
	
}
