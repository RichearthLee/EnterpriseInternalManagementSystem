package com.mftcc.interior.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.dao.ISysRoleDao;
import com.mftcc.interior.sys.service.ISysRoleService;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年4月18日  
 * @version 1.0
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

	@Override
	public void insertSysRole(SysRole sysRole) {
		// TODO Auto-generated method stub
		iSysRoleDao.insertSysRole(sysRole);

	}

	@Autowired
	private ISysRoleDao iSysRoleDao; 
	
	@Override
	public Ipage getSysRolePage(Ipage ipage)  {
		// TODO Auto-generated method stub
		System.out.println("ROLESER"+ipage.toString());
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<SysRole> sysRoleList = iSysRoleDao.getSysRolePage(ipage);  // 查询出来的每页的数据
		
		int allRecord = Integer.parseInt(iSysRoleDao.getSysRoleCount(ipage));  // 查询出一共多少条数据
		
		//  总数  除以 每页显示多少条 
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? 
				allRecord / ipage.getPageNumber() : allRecord/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(sysRoleList);
		ipage.setAllRecord(allRecord);
	
		return ipage;
		
	}

	
}
