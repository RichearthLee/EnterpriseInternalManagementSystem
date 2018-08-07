package com.mftcc.interior.cus.dao;

import java.util.List;

import com.mftcc.interior.cus.bean.UserLinkman;

public interface UserLinkmanDao {

	List<UserLinkman> selectUserLink(String userId);

	void insertUserLink(UserLinkman userLink);

	List<UserLinkman> searchLinkByMN(UserLinkman userLink);

}
