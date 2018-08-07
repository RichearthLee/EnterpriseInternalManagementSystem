package com.mftcc.interior.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysUserDao;
import com.mftcc.interior.sys.service.ISysUserService;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年3月17日  
 * @version 1.0
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

	@Autowired
	private ISysUserDao iSysUserDao;
	
	
	/* 登录 
	 *  
	 */
	@Override
	public SysUser getSysUser(SysUser sysUser) {
		
		List<SysUser> user = iSysUserDao.getSysUser(sysUser);
		if (user.size() > 0) {
			return user.get(0);
		}
		return null;
	}
	 
	/*  
	 * 查询所有用户
	 */
	@Override
	public List<SysUser> getAllSysUser(SysUser paramBean) {
		// TODO Auto-generated method stub
		return iSysUserDao.getSysUser(paramBean);
	}
	@Override
	public Ipage getSysUserInfoPage(Ipage ipage) throws Exception {
		
		 
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<SysUser> sysUser = iSysUserDao.getSysUserListPage(ipage);
		
		int allRecord = Integer.parseInt(iSysUserDao.getSysUserListCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(sysUser);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	/* 添加用户
	 * @see com.mftcc.interior.sys.service.ISysUserService#insertSysuser(com.mftcc.interior.sys.bean.SysUser)
	 */
	@Override
	public boolean insertSysUser(SysUser sysUser) {
		 
		return iSysUserDao.insertSysUser(sysUser);
	}


	/* 修改用户
	 *  成功返回true
	 */
	@Override
	public boolean updataSysUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return iSysUserDao.updateSysUser(sysUser);
	};



}
