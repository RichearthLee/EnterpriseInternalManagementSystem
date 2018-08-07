package com.mftcc.interior.cred.service;


import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.cred.bean.CredBean;
import com.mftcc.method.bean.Ipage;

public interface CredService {

	public List<CredBean> getAllCred();
	public boolean addCred(CredBean credBean) throws ServiceException;
	public Ipage getCusListPage(Ipage ipage) throws Exception;
	public boolean deleteCus(CredBean credBean);
	public boolean updateCus(CredBean credBean);
	public List<CredBean> findCus(CredBean credBean);
}
