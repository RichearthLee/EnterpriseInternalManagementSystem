package com.mftcc.interior.cus.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.UserLinkman;
import com.mftcc.interior.cus.dao.UserLinkmanDao;
import com.mftcc.interior.cus.service.UserLinkService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysUserDao;

@Service
public class userLinkServiceImpl extends BaseDao implements UserLinkService{

	@Autowired
	private UserLinkmanDao userLinkDao;
	@Autowired
	private ISysUserDao iSysUserDao;
	@Override
	public List<UserLinkman> selectUserLink(String user){
		return userLinkDao.selectUserLink(user);
	}
	@Override
	public void insertUserLink(UserLinkman userLink){
		userLinkDao.insertUserLink(userLink);
	}
	@Override
	public List<UserLinkman> searchLinkByMN(UserLinkman userLink){
		return  userLinkDao.searchLinkByMN(userLink);
	}
	@Override
	public List<SysUser> selectUser(SysUser user){
		return iSysUserDao.getSysUser(user);
	}
}
