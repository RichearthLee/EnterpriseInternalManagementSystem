package com.mftcc.interior.report.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.report.bean.CusReportBean;
import com.mftcc.interior.report.bean.LeaveDaysBean;
import com.mftcc.interior.report.dao.CusReportDao;

@Repository
public class CusReportDaoImpl extends BaseDao implements CusReportDao{

	@Override
	public List<CusReportBean> selectCusAndLinkMan() throws Exception {
		List<CusReportBean> cusReportBeans= getSqlMapClientTemplate().queryForList("selectCusAndLinkMan");
		return cusReportBeans;
	}

	@Override
	public boolean insertCusAndLinkMan(final List<CusReportBean> cusReportBeans)
			throws Exception {
		final int i=cusReportBeans.size();
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public LeaveBean doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				 executor.startBatch();  
	                int batch = 0;  
	                for (CusReportBean obj : cusReportBeans) {  
	                    executor.insert("insertCusAndLinkMan", obj);  
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

}
