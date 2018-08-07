package com.mftcc.interior.cus.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.CusReview;
import com.mftcc.interior.cus.bean.cus_follow;
import com.mftcc.interior.cus.dao.FollowInfoDao;
import com.mftcc.interior.cus.service.FollowInfoService;

@Service
public class followInfoServiceImpl extends BaseDao implements FollowInfoService{
	@Autowired
	private FollowInfoDao followInfoDao;
	@Override
	public void insertFollowInfoService(cus_follow followInfo){
		followInfoDao.insertFollowInfo(followInfo);
	}
	@Override
	public List<cus_follow> selectInfoAllService(){
		return followInfoDao.selectInfoAll();
	}
	@Override
	public List<cus_follow> selectOneById(String followId){
		return followInfoDao.selectOneById(followId);
	}
	@Override
	public List<cus_follow> selectByUserCus(cus_follow cf){
		return followInfoDao.selectByUserCus(cf);
	}
	@Override
	public List<CusReview> selectByFeed(String feedId){
		return followInfoDao.selectByFeed(feedId);
	}
	@Override
	public void insertReview(CusReview review){
		followInfoDao.insertReview(review);
	}
}
