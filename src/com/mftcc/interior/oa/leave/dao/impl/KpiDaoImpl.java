package com.mftcc.interior.oa.leave.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.interior.oa.leave.bean.KpiBean;
import com.mftcc.interior.oa.leave.bean.KpiFlagBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.PactKpiBean;
import com.mftcc.interior.oa.leave.dao.KpiDao;
import com.mftcc.method.bean.Ipage;


@Repository
public class KpiDaoImpl extends BaseDao implements KpiDao {

	
	private Logger log=Logger.getLogger(KpiDaoImpl.class);
	
	//获取员工列表
	@Override
	public List<KpiBean> getKpiAttendenceListPage(Ipage ipage)
			throws Exception {
		List<KpiBean> list = null;
		try {
			list =  getSqlMapClientTemplate().queryForList("selectkpi", ipage);
		} catch (Exception e) {
			log.error("获取员工列表失败" + e, e);
			throw new Exception(e);
		}
		return list;
	
	}
	//获取员工总数
@Override
public String getKpiAttendenceListCount(Ipage ipage) throws Exception {
	String str = null;
	try {
		str =(String)getSqlMapClientTemplate().queryForObject("selectKpiListCount", ipage);
	} catch (Exception e) {
		log.error("获取员工总数失败");
		throw new Exception(e);
	}
	return str;
}
	
public List<KpiFlagBean> getFlag(KpiFlagBean kpiFlagBean) {
	@SuppressWarnings("unchecked")
	List<KpiFlagBean> flag = (List<KpiFlagBean>) getSqlMapClientTemplate().queryForList("flag", kpiFlagBean);
	
	return flag;
}
public int updateflag(KpiFlagBean kpiFlagBean){


	return (int) getSqlMapClientTemplate().update("update_flag",kpiFlagBean);
	
}


//获取员工列表
@Override
public List<PactKpiBean> getPactKpiListPage(Ipage ipage)
		throws Exception {
	List<PactKpiBean> list = null;
	try {
		list =  getSqlMapClientTemplate().queryForList("selectpactkpi", ipage);
	} catch (Exception e) {
		log.error("获取员工列表失败" + e, e);
		throw new Exception(e);
	}
	return list;

}
//获取员工总数
@Override
public String getPactKpiListCount(Ipage ipage) throws Exception {
String str = null;
try {
	str =(String)getSqlMapClientTemplate().queryForObject("selectPactKpiListCount", ipage);
} catch (Exception e) {
	log.error("获取员工总数失败");
	throw new Exception(e);
}
return str;
}


		
}
