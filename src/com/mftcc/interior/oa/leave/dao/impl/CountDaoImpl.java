package com.mftcc.interior.oa.leave.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.dao.CountDao;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.method.bean.Ipage;


@Repository
public class CountDaoImpl extends BaseDao implements CountDao {

	
	private Logger log=Logger.getLogger(CountDaoImpl.class);
	@Override
	public List<LeaveDaysFinalBean> getCount(LeaveDaysFinalBean leaveDaysFinalBean) {
		@SuppressWarnings("unchecked")
		List<LeaveDaysFinalBean> count = (List<LeaveDaysFinalBean>) getSqlMapClientTemplate().queryForList("count", leaveDaysFinalBean);
		
		return count;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertApprove(final List<AttendenceBean> lAttendenceBeans) {
		final int i=lAttendenceBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public AttendenceBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (AttendenceBean obj : lAttendenceBeans) {  
	                    executor.insert("insertlAttendenceBeans", obj);  
	                    batch++;  
	                    if (batch == i) {  
	                        executor.executeBatch();  
	                        batch = 0;  
	                    }  
	                }  
	                executor.executeBatch();  
	                return null;
				
			}
		});

	}

	//获取员工列表
			@Override
			public List<AttendenceBean> getAttendenceListPage(Ipage ipage)
					throws Exception {
				List<AttendenceBean> list = null;
				try {
					list =  getSqlMapClientTemplate().queryForList("selectAttendence", ipage);
				} catch (Exception e) {
					log.error("获取员工列表失败" + e, e);
					throw new Exception(e);
				}
				return list;
			
			}
			//获取员工总数
		@Override
		public String getAttendenceListCount(Ipage ipage) throws Exception {
			String str = null;
			try {
				str =(String)getSqlMapClientTemplate().queryForObject("selectAttendenceListCount", ipage);
			} catch (Exception e) {
				log.error("获取员工总数失败");
				throw new Exception(e);
			}
			return str;
		}
	
		
		public List<AttendenceBean> getAttendence(AttendenceBean attendenceBean) {
			@SuppressWarnings("unchecked")
			List<AttendenceBean> attendence = (List<AttendenceBean>) getSqlMapClientTemplate().queryForList("empName", attendenceBean);
			
			return attendence;
		}
		public List<AttendenceBean> getAttendence1(AttendenceBean attendenceBean) {
			@SuppressWarnings("unchecked")
			List<AttendenceBean> attendence = (List<AttendenceBean>) getSqlMapClientTemplate().queryForList("emp", attendenceBean);
			
			return attendence;
		}

		@Override
		public int updateAttendence(AttendenceBean attendenceBean) {
			return (int) getSqlMapClientTemplate().update("update_attendence",attendenceBean);
		}
		
		
		
}
