package com.mftcc.interior.sys.dao;

import java.util.List;

import com.mftcc.interior.sys.bean.SysMenu;

/**
 * @author shensh
 * @date 2018年3月19日  
 * @version 1.0
 */
public interface ISysMenuDao {
	
	/**
	 * 方法描述： 查询用户可用状态的菜单  根据任意条件
	 */
	public List<SysMenu> getSysMenuList(SysMenu paramBean);

	/**
	 * 方法描述： 查询用户可用状态的菜单
	 */
	public List<SysMenu> getSysMenuListBySysUser(String opNo);
	
	/**
	 * 方法描述： 查询角色可用状态的菜单
	 */
	public List<SysMenu> getSysMenuListBySysRole(String roleNo);
}
