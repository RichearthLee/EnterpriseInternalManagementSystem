package com.mftcc.interior.cred.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cred.bean.CredBean;
import com.mftcc.interior.cred.dao.CredDao;
import com.mftcc.method.bean.Ipage;

	@Repository
	public class CredDaoImpl extends BaseDao implements CredDao {
		
		private Logger log=Logger.getLogger(CredDaoImpl.class);
		
		@Override
		public String insertcred(CredBean credBean) {

			@SuppressWarnings("unchecked")
			String cred = (String) getSqlMapClientTemplate().insert("insert_cus",credBean);
			
			return cred;
		}
		
		public int updateCus(CredBean credBean){


			return (int) getSqlMapClientTemplate().update("update_cus",credBean);
			
		}
		
		public List<CredBean> getCus(CredBean credBean) {
			@SuppressWarnings("unchecked")
			List<CredBean> cus = (List<CredBean>) getSqlMapClientTemplate().queryForList("cusNo", credBean);
			
			return cus;
		}
		
		@Override
		public List<CredBean> getAllCred() {
			// TODO Auto-generated method stub
			return null;
		}
		@SuppressWarnings("deprecation")
		@Override
		public List<CredBean> getCusListPage(Ipage ipage)
				throws Exception {
			List<CredBean> list = null;
			try {
				list =  getSqlMapClientTemplate().queryForList("selectCus", ipage);
			} catch (Exception e) {
				log.error("获取员工列表失败" + e, e);
				throw new Exception(e);
			}
			return list;
		
		}
		//获取员工总数
	@SuppressWarnings("deprecation")
	@Override
	public String getCusListCount(Ipage ipage) throws Exception {
		String str = null;
		try {
			str =(String)getSqlMapClientTemplate().queryForObject("selectCusListCount", ipage);
		} catch (Exception e) {
			log.error("获取员工总数失败");
			throw new Exception(e);
		}
		return str;
	}
	
	
	@Override
	public int deleteCus(CredBean credBean) {
		
		
		
		return  getSqlMapClientTemplate().delete("delete_cus", credBean);
	}
		
}
