package com.mftcc.interior.sys.dao;

import java.util.List;

import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年3月17日  
 * @version 1.0
 */
public interface ISysUserDao {
	
	/** 查询用户信息  用户登录 
	 */
	public List<SysUser> getSysUser(SysUser paramBean);
	/**更新用户信息
	 */
	public boolean updateSysUser(SysUser sysUser);
	
	/**
	 * 方法描述： 根据单个参数模糊查询得到用户信息
	 */
	public List<SysUser> getSysUserByParam(String param);
	
	/**增加新用户，并返回用户号
	 */
	public boolean insertSysUser(SysUser sysuser);
	
	
	public List<SysUser> getSysUserListPage(Ipage ipage) ;
	
	public String getSysUserListCount(Ipage ipage) ;
	

}
