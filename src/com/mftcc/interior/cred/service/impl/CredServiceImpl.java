package com.mftcc.interior.cred.service.impl;


import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.cred.bean.CredBean;
import com.mftcc.interior.cred.dao.CredDao;
import com.mftcc.interior.cred.service.CredService;
import com.mftcc.interior.cred.service.impl.CredServiceImpl;
import com.mftcc.method.bean.Ipage;

@Service
public class CredServiceImpl implements CredService{

	@Autowired
	private CredDao credDao;
	private Logger log=Logger.getLogger(CredServiceImpl.class);
	
	@Override
	public List<CredBean> getAllCred() {
		List<CredBean> cred=credDao.getAllCred();
		return cred;
	}
	
	
	@Override
	public boolean addCred(CredBean credBean) throws ServiceException {
		String temp=String.valueOf(System.currentTimeMillis());
		
	
		credBean.setCusNo(temp);
		String a=credDao.insertcred(credBean);
	
		return true;
	}
	
	@Override
	public boolean updateCus(CredBean credBean) {
		
		System.err.print(credBean);
		int cusUpId = credDao.updateCus(credBean);
		
	
		if(cusUpId != 1)
		{
			return false;
		}
		return true;
	}
	@Override
	public List<CredBean> findCus(CredBean credBean) {
		List<CredBean> cus=credDao.getCus(credBean);
		return cus;
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
	public Ipage getCusListPage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<CredBean> cus = new ArrayList<CredBean>();
		cus = credDao.getCusListPage(ipage);
		
		int allRecord = Integer.parseInt(credDao.getCusListCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(cus);
		ipage.setAllRecord(allRecord);
		return ipage;
	}
	
	@Override
	public boolean deleteCus(CredBean credBean) {
		// TODO Auto-generated method stub
    int credDeleteId = credDao.deleteCus(credBean);
		
		if(credDeleteId != 1)
		{
			return false;
		}
		return true;
	}
	
}

