package com.mftcc.interior.report.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.bean.PactOrgRuarterReportBean;
import com.mftcc.interior.report.bean.PactRuarterReportBean;
import com.mftcc.interior.report.bean.PactReportBean;
import com.mftcc.interior.report.dao.PactReportDao;

@SuppressWarnings("deprecation")
@Repository
public class PactReportDaoImpl extends BaseDao implements PactReportDao{

	@Override
	public List<PactReportBean> selectAllPactInfo() throws Exception {
		List<PactReportBean> pactSumBeans=getSqlMapClientTemplate().queryForList("selectPactCusUser");
		return pactSumBeans;
	}

	@Override
	public int updatePactInfoFlag() throws Exception {
		int sum=getSqlMapClientTemplate().update("updatePactInfoFlag");
		return sum;
	}

	@Override
	public void insertPactInfo(final List<PactReportBean> pactReportBeans) throws Exception {
		final int i=pactReportBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public LeaveBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (PactReportBean obj : pactReportBeans) {  
	                    executor.insert("insertPactCusUser", obj);  
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

	@Override
	public String selectEmployeeByNo(String no) throws Exception {
		String name=(String) getSqlMapClientTemplate().queryForObject("selectEmployeeByNo", no);
		return name;
	}
	
	@Override
	public List<PactRuarterReportBean> selectPactRuarter() throws Exception {
		List<PactRuarterReportBean> pactDateReportBeans=getSqlMapClientTemplate().queryForList("selectPactDate");
		return pactDateReportBeans;
	}

	@Override
	public boolean insertPactRuarter(final List<PactRuarterReportBean> pDateReportBeans)
			throws Exception {
		final int i=pDateReportBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public LeaveBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (PactRuarterReportBean obj : pDateReportBeans) {  
	                    executor.insert("insertPactDate", obj);  
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
		return true;
	}

	@Override
	public int deletePactRuarter() throws Exception {
		int i=getSqlMapClientTemplate().delete("deletePactRuarter");
		return i;
	}

	@Override
	public List<PactRuarterReportBean> selectPactUserRuarter() throws Exception {
		List<PactRuarterReportBean> pactRuarterReportBeans=getSqlMapClientTemplate().queryForList("selectPactUserRuarter");
		return pactRuarterReportBeans;
	}

	@Override
	public List<PactRuarterReportBean> selectPactUserRuarterSum()throws Exception {
		List<PactRuarterReportBean> pactRuarterReportBeans=getSqlMapClientTemplate().queryForList("selectPactUserRuarterSum");
		return pactRuarterReportBeans;
	}

	@Override
	public boolean insertPactUserRuarterSum(final List<PactRuarterReportBean> pDateReportBeans) throws Exception {
		final int i=pDateReportBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public PactRuarterReportBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (PactRuarterReportBean obj : pDateReportBeans) {  
	                    executor.insert("insertPactUserRuarterSum", obj);  
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
		return true;
	}

	@Override
	public List<PactOrgRuarterReportBean> selectPactOrgRuarter() throws Exception {
		List<PactOrgRuarterReportBean> pactOrgRuarterReportBeans=getSqlMapClientTemplate().queryForList("selectPactOrgRuarter");
		return pactOrgRuarterReportBeans;
	}

	

}
