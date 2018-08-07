package com.mftcc.interior.sys.dao;

import java.util.List;

import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年4月18日  
 * @version 1.0
 */
public interface ISysRoleDao {
	
	
	// 用户分页查询
	public List<SysRole> getSysRolePage(Ipage ipage);
	
	public String getSysRoleCount(Ipage ipage) ;

	/**
	 * @param sysRole 
	 * c插入 角色
	 */
	public void insertSysRole(SysRole sysRole);
}
