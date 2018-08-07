package com.mftcc.interior.sys.service;

import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年4月18日  
 * @version 1.0
 */
public interface ISysRoleService {
	
	public Ipage getSysRolePage(Ipage ipage) ;

	/**
	 * @param sysRole
	 */
	public void insertSysRole(SysRole sysRole);

}
