package com.mftcc.interior.cred.service;


import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.cred.bean.ServiceBean;
import com.mftcc.method.bean.Ipage;

public interface ServiceService {

	public List<ServiceBean> getAllService();
	public boolean addService(ServiceBean serviceBean) throws ServiceException;
	public Ipage getServiceListPage(Ipage ipage) throws Exception;
	public boolean deleteService(ServiceBean serviceBean);
	public boolean updateService(ServiceBean serviceBean);
	public List<ServiceBean> findService(ServiceBean serviceBean);
}
