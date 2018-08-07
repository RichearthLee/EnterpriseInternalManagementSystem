package com.mftcc.interior.report.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.interior.report.dao.EmployeeLeaveDaysDao;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.method.bean.Ipage;

@SuppressWarnings("deprecation")
@Repository
public class EmployeeLeaveDaysDaoImpl extends BaseDao implements EmployeeLeaveDaysDao {

	//提取员工请假信息
	@Override
	public List<LeaveBean> sumLeaveDays() throws Exception {
		return getSqlMapClientTemplate().queryForList("extractleavedate");
	}

	//把计算得到的员工请假天数插入表中
	@Override
	public void insertLeaveDays(final List<LeaveDaysBean> leaveDaysBeans)throws Exception {
		final int i=leaveDaysBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public LeaveBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (LeaveDaysBean obj : leaveDaysBeans) {  
	                    executor.insert("insertsumLeaveDays1", obj);  
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
	//分页
	@Override
	public List<LeaveDaysFinalBean> getLeaveDaysList(Ipage ipage)throws Exception {
		return getSqlMapClientTemplate().queryForList("selectLeaveDaysFinal", ipage);
	}
	@Override
	public String getLeaveDaysListCount(Ipage ipage) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("selectLeaveDaysListCount", ipage);
	}
	//插入数据前清空数据表
	@Override
	public boolean deletesumLeaveDays() throws Exception {
		if(getSqlMapClientTemplate().delete("deletesumLeaveDays1")>0){
			return true;
		}
		else return false;
	}
	//查询单个员工请假信息
	@Override
	public List<LeaveBean> selectLeaveDaysByno(String op_no) throws Exception {
		return getSqlMapClientTemplate().queryForList("selectLeaveDaysByno", op_no);
	}
	//查询所有部门
	@Override
	public List<SysOrg> selectAllOrg() throws Exception {
		return getSqlMapClientTemplate().queryForList("selectAllOrgs");
	}

	@Override
	public List<LeaveDaysFinalBean> sumcountLeaveDay() throws Exception {
		return getSqlMapClientTemplate().queryForList("sumcountLeaveDay");
	}

	@Override
	public void insertsumcountLeaveDay(final List<LeaveDaysFinalBean> leaveDaysFinalBeans) throws Exception {
		final int i=leaveDaysFinalBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public LeaveBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (LeaveDaysFinalBean obj : leaveDaysFinalBeans) {  
	                    executor.insert("insertsumcountLeaveDay", obj);  
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

}
