package com.mftcc.interior.cus.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.CusReview;
import com.mftcc.interior.cus.bean.cus_follow;
import com.mftcc.interior.cus.dao.FollowInfoDao;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class followInfoDaoImpl extends BaseDao implements FollowInfoDao{

	@Override
	public void insertFollowInfo(cus_follow followinfo) {
		getSqlMapClientTemplate().insert("insertFollowInfo", followinfo);
	}
	@Override
	public List<cus_follow> selectInfoAll(){
		return getSqlMapClientTemplate().queryForList("selectInfoAll");
	}
	@Override
	public List<cus_follow> selectOneById(String followId){
		return getSqlMapClientTemplate().queryForList("selectOneById",followId);
	}
	@Override
	public List<cus_follow> selectByUserCus(cus_follow cf){
		return getSqlMapClientTemplate().queryForList("selectByUserCus",cf);
	}
	@Override
	public void insertReview(CusReview review){
		getSqlMapClientTemplate().insert("insertReview",review);
	}
	@Override
	public List<CusReview> selectByFeed(String feedId){
		return getSqlMapClientTemplate().queryForList("selectByFeed",feedId);
	}
}
