/**
 * Copyright (C) DXHM 版权所有
 * 文件名： ServiceItemsDaoImpl.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.dao.impl
 * 说明：
 * @author 李梦浩
 * @date 2016年11月10日 下午6:09:53
 * @version V1.0
 */ 
package com.mftcc.interior.cred.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cred.dao.GroupDao;
import com.mftcc.method.bean.Ipage;

/**
 * 类名： ServiceItemsDaoImpl
 * 描述：用户能开通的服务
 * @author 李梦浩
 * @date 2016年11月10日 下午6:09:53
 *
 *
 */
@SuppressWarnings(value = { "deprecation", "unchecked"})
@Repository
public class GroupDaoImpl extends BaseDao implements GroupDao{
	Logger log=Logger.getLogger(GroupDaoImpl.class);
	
	
	@Override
	public List<Map<String, Object>> getAllSerGroups(Ipage ipage)
			throws Exception {
		try {
			return this.getSqlMapClientTemplate().queryForList("getAllSerGroups", ipage);
		} catch (Exception e) {
			log.error("getAllSerGroups方法出错；执行dao层失败，抛出异常，",e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	@Override
	public Integer getAllSerGroupsCnt(Ipage ipage) throws Exception {
		try {
			Integer count=(Integer)this.getSqlMapClientTemplate().queryForObject("getAllSerGroupsCnt", ipage);
			return count;
		} catch (Exception e) {
			log.error("getAllSerGroupsCnt方法出错；执行dao层失败，抛出异常，",e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	@Override
	public void deletServiceGroup(String businessCode) throws Exception {
		try {
			this.getSqlMapClientTemplate().delete("deletServiceGroup", businessCode);
		} catch (Exception e) {
			log.error("deletServiceGroup方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public void addServiceGroup(Map<String, String> mp) throws Exception {
		try {
			this.getSqlMapClientTemplate().insert("addServiceGroup", mp);
		} catch (Exception e) {
			log.error("addServiceGroup方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public List<Map<String, String>> getGroupServItemNos(String businessCode)
			throws Exception {
		try {
			return getSqlMapClientTemplate().queryForList("getGroupServItemNos", businessCode);
		} catch (Exception e) {
			log.error("getGroupServItemNos方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public void deleteServGroupDetail(String businessCode) throws Exception {
		try {
			this.getSqlMapClientTemplate().delete("deleteServGroupDetail", businessCode);
		} catch (Exception e) {
			log.error("deleteServGroupDetail方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public List<Map<String, String>> getServItemsBySearchFiled(
			Map<String,String> map) throws Exception {
		try {
			return this.getSqlMapClientTemplate().queryForList("getServItemsBySearchFiled", map);
		} catch (Exception e) {
			log.error("getServItemsBySearchFiled方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public void updateServiceGroup(Map<String, String> mp) throws Exception {
		try {
			this.getSqlMapClientTemplate().update("updateServiceGroup", mp);
		} catch (Exception e) {
			log.error("updateServiceGroup方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public void addServiceGroupDetail(Map<String, String> detailMp)
			throws Exception {
		try {
			this.getSqlMapClientTemplate().update("addServiceGroupDetail", detailMp);
		} catch (Exception e) {
			log.error("addServiceGroupDetail方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	
	@Override
	public Integer getScoreCount(String itemNo, String score) throws Exception {
		Map<String,String> mp=new HashMap<String, String>();
		mp.put("itemNo", itemNo);
		mp.put("score", score);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getScoreCount",mp);
	}
	@Override
	public List<Map<String, String>> selectAllServiceScore(Ipage ipage)
			throws Exception {
		
		return this.getSqlMapClientTemplate().queryForList("selectAllServiceScore",ipage);
	}
	@Override
	public Map<String, Object> getSerGroupByid(String id) throws Exception {
		Map<String, Object> result;
		try {
			result=(Map<String, Object>) this.getSqlMapClientTemplate().queryForObject("getSerGroupByid", id);
		} catch (Exception e) {
			log.error("getSerGroupByid方法出错；执行dao层失败，抛出异常，");
			throw new Exception(e);
		}
		return result;
	}
	@Override
	public List<Map<String, String>> getAllServiceGroups(
			Map<String, String> param) throws Exception {
		try {
			return this.getSqlMapClientTemplate().queryForList("getAllServiceGroups", param);
		} catch (Exception e) {
			log.error("getAllServiceGroups方法出错；执行dao层失败，抛出异常，",e);
			throw new Exception(e);
		}
	}

}
