package com.mftcc.interior.cred.dao;


import java.util.List;

import com.mftcc.interior.cred.bean.CredBean;
import com.mftcc.method.bean.Ipage;


public interface CredDao {

	public List<CredBean> getAllCred();
	public String insertcred(CredBean credBean);
	public List<CredBean> getCusListPage(Ipage ipage) throws Exception;
	String getCusListCount(Ipage ipage) throws Exception;
	public int deleteCus(CredBean credBean);
	public int updateCus(CredBean credBean);
	public List<CredBean> getCus(CredBean credBean);
}
