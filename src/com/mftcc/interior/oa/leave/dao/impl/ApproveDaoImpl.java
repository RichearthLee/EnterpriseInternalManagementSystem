package com.mftcc.interior.oa.leave.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.dao.ApproveDao;
import com.mftcc.interior.oa.leave.dao.LeaveDao;
import com.mftcc.method.bean.Ipage;


	/**
	 * 类名： UsersDaoImpl <br>
	
	 */
	
@Repository
public class ApproveDaoImpl extends BaseDao implements ApproveDao {

		private Logger log=Logger.getLogger(ApproveDaoImpl.class);
		@SuppressWarnings("deprecation")
		@Override
		public List<ApproveBean> getApproveListPage(Ipage ipage) {
			List<ApproveBean> list = null;
			try {
				list =  getSqlMapClientTemplate().queryForList("selectApprove", ipage);
			} catch (Exception e) {
				log.error("获取员工列表失败" + e, e);
				throw e;
			}
			return list;
			
		}

		@Override
		public String getApproveListCount(Ipage ipage) {
			String str = null;
			try {
				str =(String)getSqlMapClientTemplate().queryForObject("selectApproveListCount", ipage);
			} catch (Exception e) {
				log.error("获取员工总数失败");
				throw e;
			}
			return str;
		}
		public List<ApproveBean> getApprove(ApproveBean approveBean) {
			@SuppressWarnings("unchecked")
			List<ApproveBean> approve = (List<ApproveBean>) getSqlMapClientTemplate().queryForList("approve_no", approveBean);
			return approve;
		}
		public int updateApprove(ApproveBean approveBean){


			return (int) getSqlMapClientTemplate().update("update_approve",approveBean);
			
		}
		public int updateApprove1(ApproveBean approveBean){


			return (int) getSqlMapClientTemplate().update("update_approve1",approveBean);
			
		}

}
