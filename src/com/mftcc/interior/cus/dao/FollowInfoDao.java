package com.mftcc.interior.cus.dao;

import java.util.List;
import java.util.Map;

import com.mftcc.interior.cus.bean.CusReview;
import com.mftcc.interior.cus.bean.cus_follow;

public interface FollowInfoDao {

	void insertFollowInfo(cus_follow followInfo);

	List<cus_follow> selectInfoAll();

	List<cus_follow> selectByUserCus(cus_follow cf);

	void insertReview(CusReview review);

	List<CusReview> selectByFeed(String feedId);

	List<cus_follow> selectOneById(String followId);

}
