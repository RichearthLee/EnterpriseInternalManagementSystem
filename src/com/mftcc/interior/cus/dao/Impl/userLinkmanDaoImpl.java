package com.mftcc.interior.cus.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.UserLinkman;
import com.mftcc.interior.cus.dao.UserLinkmanDao;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class userLinkmanDaoImpl extends BaseDao implements UserLinkmanDao{

	@Override
	public List<UserLinkman> selectUserLink(String userId){
		return getSqlMapClientTemplate().queryForList("selectLinkByUser",userId);
	}
	@Override
	public void insertUserLink(UserLinkman userLink){
		getSqlMapClientTemplate().insert("insertUserLink",userLink);
	}
	@Override
	public List<UserLinkman> searchLinkByMN(UserLinkman userLink){
		return getSqlMapClientTemplate().queryForList("searchLinkByMN",userLink);
	}
}
