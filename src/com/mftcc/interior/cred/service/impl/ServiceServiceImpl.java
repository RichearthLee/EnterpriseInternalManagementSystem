package com.mftcc.interior.cred.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.cred.bean.ServiceBean;
import com.mftcc.interior.cred.dao.ServiceDao;
import com.mftcc.interior.cred.service.ServiceService;
import com.mftcc.interior.cred.service.impl.ServiceServiceImpl;
import com.mftcc.method.bean.Ipage;

@Service
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	private ServiceDao serviceDao;
	private Logger log=Logger.getLogger(ServiceServiceImpl.class);
	
	@Override
	public List<ServiceBean> getAllService() {
		List<ServiceBean> service=serviceDao.getAllService();
		return service;
	}
	
	
	@Override
	public boolean addService(ServiceBean serviceBean) throws ServiceException {
		String temp=String.valueOf(System.currentTimeMillis());
		
	
		serviceBean.setServiceNo(temp);
		String a=serviceDao.insertService(serviceBean);
	
		return true;
	}
	
	@Override
	public boolean updateService(ServiceBean serviceBean) {
		
		System.err.print(serviceBean);
		int serviceUpId = serviceDao.updateService(serviceBean);
		
	
		if(serviceUpId != 1)
		{
			return false;
		}
		return true;
	}
	@Override
	public List<ServiceBean> findService(ServiceBean serviceBean) {
		List<ServiceBean> Service=serviceDao.getService(serviceBean);
		return Service;
	}

	
	/*@Override
	public Ipage getCusListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			List<CredBean> cus = credDao.getCusListPage(ipage);//取数据
			int totalpage = 0,allRecord = 0;
			if (cus == null) {
			}else{
				allRecord = Integer.parseInt(credDao.getCusListCount(ipage));//员工总数
				totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
						/ ipage.getPageNumber() + 1;
			}
			ipage.setTotalPage(totalpage);//获取页数
			ipage.setDataList(cus);
			ipage.setAllRecord(allRecord);//每页显示的条数
		} catch (Exception e) {
			log.error("获取list失败，service层getEmployeeListPage方法报错");
			throw new Exception(e);
		}
		return ipage;
	}*/
	@Override
	public Ipage getServiceListPage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<ServiceBean> service = new ArrayList<ServiceBean>();
		service = serviceDao.getServiceListPage(ipage);
		
		int allRecord = Integer.parseInt(serviceDao.getServiceListCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(service);
		ipage.setAllRecord(allRecord);
		return ipage;
	}
	
	@Override
	public boolean deleteService(ServiceBean serviceBean) {
		// TODO Auto-generated method stub
    int serviceDeleteId = serviceDao.deleteService(serviceBean);
		
		if(serviceDeleteId != 1)
		{
			return false;
		}
		return true;
	}
	
}

