package com.mftcc.interior.oa.leave.dao.impl;



	
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.dao.LeaveDao;
import com.mftcc.method.bean.Ipage;


	/**
	 * 类名： UsersDaoImpl <br>
	 
	 */
	
	@Repository
	public class LeaveDaoImpl extends BaseDao implements LeaveDao {
		private Logger log=Logger.getLogger(LeaveDaoImpl.class);
		 
		@Override
		public String insertLeave(LeaveBean leaveBean) {

			@SuppressWarnings("unchecked")
			String leave = (String) getSqlMapClientTemplate().insert("insert_leave",leaveBean);
			
			return leave;
		}
		@Override
		public String insertApprove(ApproveBean approveBean) {
			@SuppressWarnings("unchecked")
			String approveId = (String) getSqlMapClientTemplate().insert("insert_approve",approveBean);
			
			return approveId;
		}
		
		
		
		@Override
		public int deleteLeave( LeaveBean leaveBean) {
			
			
			
			return  getSqlMapClientTemplate().delete("delete_leave", leaveBean);
		}
		
		
		
		
		
		public List<LeaveBean> getLeave(LeaveBean leaveBean) {
			@SuppressWarnings("unchecked")
			List<LeaveBean> leave = (List<LeaveBean>) getSqlMapClientTemplate().queryForList("leave_no", leaveBean);
			
			return leave;
		}
		public int updateLeave(LeaveBean leaveBean){


			return (int) getSqlMapClientTemplate().update("update_leave",leaveBean);
			
		}
		//获取员工列表
		@Override
		public List<LeaveBean> getLeaveListPage(Ipage ipage)
				throws Exception {
			List<LeaveBean> list = null;
			try {
				list =  getSqlMapClientTemplate().queryForList("selectLeave", ipage);
			} catch (Exception e) {
				log.error("获取员工列表失败" + e, e);
				throw new Exception(e);
			}
			return list;
		
		}
		//获取员工总数
	@Override
	public String getLeaveListCount(Ipage ipage) throws Exception {
		String str = null;
		try {
			str =(String)getSqlMapClientTemplate().queryForObject("selectLeaveListCount", ipage);
		} catch (Exception e) {
			log.error("获取员工总数失败");
			throw new Exception(e);
		}
		return str;
	}
	@Override
	public List<LeaveBean> getAllLeave() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deleteApprove(LeaveBean leaveBean) {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().delete("delete_approve", leaveBean);
	}
	
	

	}


