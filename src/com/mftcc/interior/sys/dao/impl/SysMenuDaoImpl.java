package com.mftcc.interior.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.sys.bean.SysMenu;
import com.mftcc.interior.sys.dao.ISysMenuDao;

/**
 * @author shensh
 * @date 2018年3月19日  
 * @version 1.0
 */

@SuppressWarnings(value = { "deprecation", "unchecked"})
@Repository
public class SysMenuDaoImpl extends BaseDao implements ISysMenuDao{

	/*  
	 * 查询菜单的数据
	 */
	@Override
	public List<SysMenu> getSysMenuList(SysMenu paramBean) {
		List<SysMenu> list =   getSqlMapClientTemplate().queryForList("selectSysMenu", paramBean);
		return list;
	}

	/* (non-Javadoc)
	 * @see 登录时根据权限查询菜单
	 */
	@Override
	public List<SysMenu> getSysMenuListBySysUser(String opNo) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectMenusBySysUser", opNo);

	}
	
	
	@Override
	public List<SysMenu> getSysMenuListBySysRole(String roleNo) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectMenusBySysRole", roleNo);

	}
 
	
	
}
