package com.mftcc.interior.cus.service;

import java.util.List;
import java.util.Map;

import com.mftcc.interior.cus.bean.CusReview;
import com.mftcc.interior.cus.bean.cus_follow;

public interface FollowInfoService {

	public void insertFollowInfoService(cus_follow followInfo);

	List<cus_follow> selectInfoAllService();

	List<cus_follow> selectByUserCus(cus_follow cf);

	List<CusReview> selectByFeed(String feedId);

	void insertReview(CusReview review);

	List<cus_follow> selectOneById(String followId);
}
