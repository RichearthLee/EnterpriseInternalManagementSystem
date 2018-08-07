package com.mftcc.interior.sys.service;

import java.util.List;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年3月17日  
 * @version 1.0
 */
public interface ISysUserService {
	
	public SysUser getSysUser(SysUser userName);
	 
	public List<SysUser> getAllSysUser(SysUser userName);
	
	public Ipage getSysUserInfoPage(Ipage ipage) throws  Exception;

	public boolean insertSysUser(SysUser sysUser);
	 //  修改用户
	public boolean updataSysUser(SysUser sysUser);
	
}
