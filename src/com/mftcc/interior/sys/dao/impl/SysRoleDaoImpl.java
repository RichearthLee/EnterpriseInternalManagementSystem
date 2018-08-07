package com.mftcc.interior.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.dao.ISysRoleDao;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年4月18日  
 * @version 1.0
 */
@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class SysRoleDaoImpl extends BaseDao implements ISysRoleDao  {
	/*  
	 *  查询角色内容
	 */
	@Override
	public List<SysRole> getSysRolePage(Ipage ipage) {
		// TODO Auto-generated method stub
		 return getSqlMapClientTemplate().queryForList("selectSysRolePage", ipage);
	}

	/*  
	 *  查询一共多少条数据
	 */
	@Override
	public String getSysRoleCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectSysRoleCount", ipage);
	}

	@Override
	public void insertSysRole(SysRole sysRole) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insertsysRole", sysRole);

	}

}
