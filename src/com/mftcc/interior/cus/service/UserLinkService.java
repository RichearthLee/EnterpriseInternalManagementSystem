package com.mftcc.interior.cus.service;

import java.util.List;

import com.mftcc.interior.cus.bean.UserLinkman;
import com.mftcc.interior.sys.bean.SysUser;

public interface UserLinkService {

	List<UserLinkman> selectUserLink(String userId);

	void insertUserLink(UserLinkman userLink);

	List<UserLinkman> searchLinkByMN(UserLinkman userLink);

	List<SysUser> selectUser(SysUser user);

}
