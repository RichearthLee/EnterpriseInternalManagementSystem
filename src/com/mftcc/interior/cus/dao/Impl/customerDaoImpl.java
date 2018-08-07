package com.mftcc.interior.cus.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.cusLinkman;
import com.mftcc.interior.cus.bean.customer;
import com.mftcc.interior.cus.dao.CustomerDao;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class customerDaoImpl extends BaseDao implements CustomerDao{

	@Override
	public void insertCustomer(customer cus){
		getSqlMapClientTemplate().insert("insertCustomer",cus);
	}
	@Override
	public List<customer> selectByMarketer(String marketer){
		List<customer> list=new ArrayList<customer>();
		list=getSqlMapClientTemplate().queryForList("selectByMarketer", marketer);
		System.out.println(list.size());
		return list;
	}
	@Override
	public List<cusLinkman> selectLinkByCus(String cusId){
		return getSqlMapClientTemplate().queryForList("selectLinkByCus",cusId);
	}
	@Override
	public void insertLinkman(cusLinkman linkman){
		getSqlMapClientTemplate().insert("insertLink",linkman);
	}
	@Override
	public List<customer> searchByName(customer cus){
		return getSqlMapClientTemplate().queryForList("searchByName",cus);
	} 
	@Override
	public List<customer> selectCusById(String cusId){
		return getSqlMapClientTemplate().queryForList("selectCusById",cusId);
	}
	@Override
	public List<cusLinkman> selectAllLink() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
