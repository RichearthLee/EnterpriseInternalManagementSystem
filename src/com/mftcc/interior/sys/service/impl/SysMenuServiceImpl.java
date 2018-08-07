package com.mftcc.interior.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.sys.bean.SysMenu;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysMenuDao;
import com.mftcc.interior.sys.service.ISysMenuService;

/**
 * @author shensh
 * @date 2018年3月19日  
 * @version 1.0
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
	@Autowired
	private ISysMenuDao iSysMenuDao;
	
	
	/*  
	 * @see  查询所有菜单   根据菜单属性
	 */
	@Override
	public List<SysMenu> getSysMenuListAll(SysMenu paramBean) {
		// 根据条件查询菜单
		//SysMenu paramBean = new SysMenu();
		List<SysMenu> list = iSysMenuDao.getSysMenuList(paramBean);
		return list;
	}

	/*  
	 * @see  查询所有菜单  可根据菜单状态
	 */
	@Override
	public Map<String, List<SysMenu>> getSysMenuMap(boolean containsUnused) {
		// TODO Auto-generated method stub

		SysMenu paramBean = new SysMenu();

		if (!containsUnused) {
			paramBean.setMenuSts("1");
		}
		List<SysMenu> menuList = iSysMenuDao.getSysMenuList(paramBean);

		Map<String, List<SysMenu>> menusMap = makeMapWithMenuList(menuList);
		return menusMap;
	}
	/**
	 * 方法描述： 将menu的List封装成map，顶级菜单的Key值为"".
	 */
	private Map<String, List<SysMenu>> makeMapWithMenuList(List<SysMenu> menuList) {
		Map<String, List<SysMenu>> menusMap = new HashMap<String, List<SysMenu>>();
		Set<String> handledMenuSet = new HashSet<String>();
		for (SysMenu sysMenu : menuList) {
			String parentMenuNo = StringUtil.killNull(sysMenu.getMenuParent());
			if (!menusMap.containsKey(parentMenuNo)) {
				// 尚没有这个父菜单，则新建一个List
				menusMap.put(parentMenuNo, new ArrayList<SysMenu>());
			}
			// 子菜单（含过滤）
			String subMenuNo = sysMenu.getMenuNo();
			if (!handledMenuSet.contains(subMenuNo)) {
				menusMap.get(parentMenuNo).add(sysMenu);
				handledMenuSet.add(subMenuNo);
			}
		}

		return menusMap;
	}
	
	@Override
	public Map<String, List<SysMenu>> getSysMenuMapByUser(SysUser user) {
		// TODO Auto-generated method stub
		List<SysMenu> menuList = iSysMenuDao.getSysMenuListBySysUser(user.getOpNo());
		return makeMapWithMenuList(menuList);
		}

	/* (non-Javadoc)
	 * @see com.mftcc.interior.sys.service.ISysMenuService#getSysMenuListAll()
	 */
	@Override
	public List<SysMenu> getSysMenuListAll() {
		// TODO Auto-generated method stub
		SysMenu sysMenu=new SysMenu();
		sysMenu.setChildren(null);
		sysMenu.setMenuLev(null);
		sysMenu.setMenuName(null);
		sysMenu.setMenuNo(null);
		sysMenu.setMenuUrl(null);
		sysMenu.setMenuSts(null);
		sysMenu.setMenuParent(null);
		return iSysMenuDao.getSysMenuList(sysMenu);
	}

	@Override
	public List<SysMenu> getSysMenuListBySysRole(String roleNo) {
		
		return iSysMenuDao.getSysMenuListBySysRole(roleNo);
	}

}
