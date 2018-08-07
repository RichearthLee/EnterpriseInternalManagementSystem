package com.mftcc.interior.cred.dao;


import java.util.List;

import com.mftcc.interior.cred.bean.ServiceBean;
import com.mftcc.method.bean.Ipage;


public interface ServiceDao {

	public List<ServiceBean> getAllService();
	public String insertService(ServiceBean serviceBean);
	public List<ServiceBean> getServiceListPage(Ipage ipage) throws Exception;
	String getServiceListCount(Ipage ipage) throws Exception;
	public int deleteService(ServiceBean serviceBean);
	public int updateService(ServiceBean credBean);
	public List<ServiceBean> getService(ServiceBean serviceBean);
}
