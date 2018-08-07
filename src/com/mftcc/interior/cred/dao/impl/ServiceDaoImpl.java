package com.mftcc.interior.cred.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cred.bean.ServiceBean;
import com.mftcc.interior.cred.dao.ServiceDao;
import com.mftcc.method.bean.Ipage;

	@Repository
	public class ServiceDaoImpl extends BaseDao implements ServiceDao {
		
		private Logger log=Logger.getLogger(ServiceDaoImpl.class);
		
		@Override
		public String insertService(ServiceBean serviceBean) {

			@SuppressWarnings("unchecked")
			String service = (String) getSqlMapClientTemplate().insert("insert_service",serviceBean);
			
			return service;
		}
		
		public int updateService(ServiceBean serviceBean){


			return (int) getSqlMapClientTemplate().update("update_service",serviceBean);
			
		}
		
		public List<ServiceBean> getService(ServiceBean serviceBean) {
			@SuppressWarnings("unchecked")
			List<ServiceBean> service = (List<ServiceBean>) getSqlMapClientTemplate().queryForList("serviceNo", serviceBean);
			
			return service;
		}
		
		@Override
		public List<ServiceBean> getAllService() {
			// TODO Auto-generated method stub
			return null;
		}
		@SuppressWarnings("deprecation")
		@Override
		public List<ServiceBean> getServiceListPage(Ipage ipage)
				throws Exception {
			List<ServiceBean> list = null;
			try {
				list =  getSqlMapClientTemplate().queryForList("selectService", ipage);
			} catch (Exception e) {
				log.error("获取员工列表失败" + e, e);
				throw new Exception(e);
			}
			return list;
		
		}
		//获取员工总数
	@SuppressWarnings("deprecation")
	@Override
	public String getServiceListCount(Ipage ipage) throws Exception {
		String str = null;
		try {
			str =(String)getSqlMapClientTemplate().queryForObject("selectServiceListCount", ipage);
		} catch (Exception e) {
			log.error("获取员工总数失败");
			throw new Exception(e);
		}
		return str;
	}
	
	
	@Override
	public int deleteService(ServiceBean serviceBean) {
		
		
		
		return  getSqlMapClientTemplate().delete("delete_service", serviceBean);
	}
		
}
