package com.mftcc.interior.sys.service;

import java.util.List;
import java.util.Map;

import com.mftcc.interior.sys.bean.SysMenu;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年3月19日  
 * @version 1.0
 */
public interface ISysMenuService {
	
	/**
	 * 方法描述： 获取所有菜单所构成的map。
	 * Map<String,List<MenuBean>> 1级菜单key值为""，其他级别key值为parentMenuNo。
	 */
	public Map<String, List<SysMenu>> getSysMenuMap(boolean containsUnused);

	/**
	 * 方法描述： 查询用户可用状态的菜单.
	 */
	public Map<String, List<SysMenu>> getSysMenuMapByUser(SysUser user);

	/**
	 * @param paramBean
	 * @return
	 */
	List<SysMenu> getSysMenuListAll(SysMenu paramBean);
	
	// 获取所有菜单，无参
	List<SysMenu> getSysMenuListAll();
	
	// 根据角色号 查询菜单
	List<SysMenu> getSysMenuListBySysRole(String roleNo);
	
}
