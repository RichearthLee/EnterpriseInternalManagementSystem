package com.mftcc.common.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mftcc.interior.oa.leave.bean.LeaveBean;

@SuppressWarnings("deprecation")
public class BaseDao extends SqlMapClientDaoSupport{
	@Resource(name="sqlMapClient")  //
	private SqlMapClient sqlMapClient; 
	@PostConstruct  //
	public void initSqlMapClient(){ 
		super.setSqlMapClient(sqlMapClient); 
	}
	public int deleteLeave(LeaveBean leaveBean) {
		// TODO Auto-generated method stub
		return 0;
	}
}
