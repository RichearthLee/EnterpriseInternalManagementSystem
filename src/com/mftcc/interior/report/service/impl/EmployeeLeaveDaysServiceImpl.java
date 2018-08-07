package com.mftcc.interior.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.interior.report.dao.EmployeeLeaveDaysDao;
import com.mftcc.interior.report.service.EmployeeLeaveDaysService;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.method.bean.Ipage;

@Service
public class EmployeeLeaveDaysServiceImpl implements EmployeeLeaveDaysService{

	@Autowired
	private EmployeeLeaveDaysDao employeeLeaveDaysDao;
	@Override
	//分类计算员工请假总天数
	public List<LeaveBean> sumLeaveDays() throws Exception {
		List<LeaveBean> leaveBeans = employeeLeaveDaysDao.sumLeaveDays();
		return leaveBeans;
	}
	//跑批量前清空表数据
		@Override
		public boolean deletesumLeaveDays() throws Exception {
			return employeeLeaveDaysDao.deletesumLeaveDays();
		}
	//把计算出的请假数据插入到数据库
	public void insertLeaveDays(List<LeaveDaysBean> leaveDaysBeans) throws Exception{
		employeeLeaveDaysDao.insertLeaveDays(leaveDaysBeans);
	
	}
	//分页
	@Override
	public Ipage getLeaveDaysList(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<LeaveDaysFinalBean> leaveDaysFinalBeans = employeeLeaveDaysDao.getLeaveDaysList(ipage);
		int allRecord = Integer.parseInt(employeeLeaveDaysDao.getLeaveDaysListCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(leaveDaysFinalBeans);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	//查询单个员工的请假信息
	@Override
	public List<LeaveBean> selectLeaveDaysByno(String op_no) throws Exception {
		return employeeLeaveDaysDao.selectLeaveDaysByno(op_no);
	}
	//查询所有部门
	@Override
	public List<SysOrg> selectAllOrg() throws Exception {
		List<SysOrg> orgs=employeeLeaveDaysDao.selectAllOrg();
		return orgs;
	}
	//计算出请假总表
	@Override
	public List<LeaveDaysFinalBean> sumcountLeaveDay() throws Exception {
		List<LeaveDaysFinalBean> leaveDaysFinalBeans=employeeLeaveDaysDao.sumcountLeaveDay();
		return leaveDaysFinalBeans;
	}
	//计算出的请假总表插入数据
	@Override
	public void insertsumcountLeaveDay(List<LeaveDaysFinalBean> leaveDaysFinalBeans) throws Exception {
		
		employeeLeaveDaysDao.insertsumcountLeaveDay(leaveDaysFinalBeans);
	}
	

}
