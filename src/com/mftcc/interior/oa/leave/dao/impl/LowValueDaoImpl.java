package com.mftcc.interior.oa.leave.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.leave.bean.ApproveBean;
import com.mftcc.interior.oa.leave.bean.ApproveLowValueBean;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.oa.leave.bean.LowValueAmountBean;
import com.mftcc.interior.oa.leave.bean.LowValueBean;
import com.mftcc.interior.oa.leave.dao.LowValueDao;
import com.mftcc.method.bean.Ipage;


@Repository
public class LowValueDaoImpl extends BaseDao implements LowValueDao{
	
	private Logger log=Logger.getLogger(LowValueDaoImpl.class);
	
	@Override
	public String insertLowValue(LowValueBean lowValueBean) {

		@SuppressWarnings("unchecked")
		String lowvalue = (String) getSqlMapClientTemplate().insert("insert_lowvalue",lowValueBean);
		
		return lowvalue;
	}
	@Override
	public String insertLowValueApprove(ApproveLowValueBean approveLowValueBean) {
		@SuppressWarnings("unchecked")
		String approvelowvalue = (String) getSqlMapClientTemplate().insert("insert_approvelowvalue",approveLowValueBean);
		
		return approvelowvalue;
	}
	@Override
	public int deleteLowValue( LowValueBean lowValueBean) {
		
		
		
		return  getSqlMapClientTemplate().delete("delete_lowvalue", lowValueBean);
	}
	
	
	
	
	
	public List<LowValueBean> getLowValue(LowValueBean lowValueBean) {
		@SuppressWarnings("unchecked")
		List<LowValueBean> lowvalue = (List<LowValueBean>) getSqlMapClientTemplate().queryForList("lowvalue_no", lowValueBean);
		
		return lowvalue;
	}
	public int updateLowValue(LowValueBean lowValueBean){


		return (int) getSqlMapClientTemplate().update("update_lowvalue",lowValueBean);
		
	}
	//获取员工列表
	@Override
	public List<LowValueBean> getLowValueListPage(Ipage ipage)
			throws Exception {
		List<LowValueBean> list = null;
		try {
			list =  getSqlMapClientTemplate().queryForList("selectLowValue", ipage);
		} catch (Exception e) {
			log.error("获取员工列表失败" + e, e);
			throw new Exception(e);
		}
		return list;
	
	}
	//获取员工总数
@Override
public String getLowValueListCount(Ipage ipage) throws Exception {
	String str = null;
	try {
		str =(String)getSqlMapClientTemplate().queryForObject("selectLowValueListCount", ipage);
	} catch (Exception e) {
		log.error("获取员工总数失败");
		throw new Exception(e);
	}
	return str;
}

@Override
public int deleteApproveLowValue(LowValueBean lowValueBean) {
	// TODO Auto-generated method stub
	return  getSqlMapClientTemplate().delete("delete_approvelowvalue", lowValueBean);
}

@Override
public List<ApproveLowValueBean> getApproveLowValueListPage(Ipage ipage) {
	List<ApproveLowValueBean> list = null;
	try {
		list =  getSqlMapClientTemplate().queryForList("selectApproveLowValue", ipage);
	} catch (Exception e) {
		log.error("获取员工列表失败" + e, e);
		throw e;
	}
	return list;
	
}

@Override
public String getApproveLowValueListCount(Ipage ipage) {
	String str = null;
	try {
		str =(String)getSqlMapClientTemplate().queryForObject("selectApproveLowValueListCount", ipage);
	} catch (Exception e) {
		log.error("获取员工总数失败");
		throw e;
	}
	return str;
}
public List<ApproveLowValueBean> getApproveLowValue(ApproveLowValueBean approveLowValueBean) {
	@SuppressWarnings("unchecked")
	List<ApproveLowValueBean> approve = (List<ApproveLowValueBean>) getSqlMapClientTemplate().queryForList("approvelowvalue_no", approveLowValueBean);
	return approve;
}
public int updateApproveLowValue(ApproveLowValueBean approveLowValueBean){


	return (int) getSqlMapClientTemplate().update("update_approvelowvalue",approveLowValueBean);
	
}
public int updateApproveLowValue1(ApproveLowValueBean approveLowValueBean){


	return (int) getSqlMapClientTemplate().update("update_approvelowvalue1",approveLowValueBean);
	
}
	

//获取员工列表
@Override
public List<LowValueAmountBean> getManageListPage(Ipage ipage) {
	List<LowValueAmountBean> list = null;
	try {
		list =  getSqlMapClientTemplate().queryForList("selectLowValueAmount", ipage);
	} catch (Exception e) {
		log.error("获取员工列表失败" + e, e);
		throw e;
	}
	return list;
	
}
//获取员工总数
@Override
public String getManageListCount(Ipage ipage) throws Exception {
String str = null;
try {
	str =(String)getSqlMapClientTemplate().queryForObject("selectLowValueAmountListCount", ipage);
} catch (Exception e) {
	log.error("获取员工总数失败");
	throw new Exception(e);
}
return str;
}

public List<LowValueAmountBean> getLowValueAmount(LowValueAmountBean lowValueAmountBean) {
	@SuppressWarnings("unchecked")
	List<LowValueAmountBean> lowvalue = (List<LowValueAmountBean>) getSqlMapClientTemplate().queryForList("lowvalueamount_no", lowValueAmountBean);
	
	return lowvalue;
}
public int updateLowValueAmount(LowValueAmountBean lowValueAmountBean){


	return (int) getSqlMapClientTemplate().update("update_lowvalueamount",lowValueAmountBean);
	
}
	
}
