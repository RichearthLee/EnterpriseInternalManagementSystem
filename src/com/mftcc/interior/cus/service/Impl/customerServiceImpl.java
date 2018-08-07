package com.mftcc.interior.cus.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.cusLinkman;
import com.mftcc.interior.cus.bean.customer;
import com.mftcc.interior.cus.dao.CustomerDao;
import com.mftcc.interior.cus.service.CustomerService;

@Service
public class customerServiceImpl extends BaseDao implements CustomerService{

	@Autowired
	private CustomerDao customerdao;
	@Override
	public void insertCustomer(customer cus){
		customerdao.insertCustomer(cus);
	}
	@Override
	public List<customer> selectByMarketer(String marketer){
		return customerdao.selectByMarketer(marketer);
	}
	@Override
	public List<cusLinkman> selectLinkByCus(String cusId){
		return customerdao.selectLinkByCus(cusId);
	}
	@Override
	public void insertLinkman(cusLinkman linkman){
		customerdao.insertLinkman(linkman);
	}
	@Override
	public List<customer> searchByName(customer cus){
		return customerdao.searchByName(cus);
	}
	@Override
	public customer selectCusById(String cusId){
		List<customer> list=new ArrayList<customer>();
		customer cus=new customer();
		list=customerdao.selectCusById(cusId);
		cus=list.get(0);
		return cus;
	}
	@Override
	public List<cusLinkman> selectAllLink() {
		// TODO Auto-generated method stub
		return null;
	}
}
