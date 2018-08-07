package com.mftcc.interior.cus.dao;

import java.util.List;

import com.mftcc.interior.cus.bean.cusLinkman;
import com.mftcc.interior.cus.bean.customer;

public interface CustomerDao {

	void insertCustomer(customer cus);

	List<customer> selectByMarketer(String marketer);

	List<cusLinkman> selectLinkByCus(String cusId);

	void insertLinkman(cusLinkman linkman);

	List<cusLinkman> selectAllLink();

	List<customer> searchByName(customer cus);
	
	List<customer> selectCusById(String cusId);


}
