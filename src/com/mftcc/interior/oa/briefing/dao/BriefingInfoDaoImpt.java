package com.mftcc.interior.oa.briefing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository

public class BriefingInfoDaoImpt extends BaseDao implements BriefingInfoDao {

	
	@Override
	public List<BriefingInfo> selectBriefingInfo(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub
		List<BriefingInfo> briefing = (List<BriefingInfo>) getSqlMapClientTemplate().queryForList("selectBriefingInfo", briefingInfo);
		return briefing;
	}

	@Override
	public List<BriefingInfo> selectBriefingInfo() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectBriefingInfo");
	}

	@Override
	public List<BriefingInfo> getBriefingInfoPage(Ipage ipage) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectBriefingInfoPage", ipage);
	}
	
	@Override
	public List<BriefingInfo> getBriefingDraftInfoPage(Ipage ipage) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectBriefingDraftInfoPage", ipage);
	}
	
	@Override
	public List<BriefingInfo> getBriefingDraftInfoById(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub		
		return getSqlMapClientTemplate().queryForList("selectBriefingDraftInfoById", briefingInfo);
	}
	
	@Override
	public List<BriefingInfo> getBriefingReviewedInfoById(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub		
		return getSqlMapClientTemplate().queryForList("selectBriefingReviewedInfoById", briefingInfo);
	}
	
	@Override
	public List<BriefingInfo> getBriefingUnreadInfoById(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub		
		return getSqlMapClientTemplate().queryForList("selectBriefingUnreadInfoById", briefingInfo);
	}
	
	
	
	@Override
	public List<BriefingInfo> getBriefingReviewedInfoPage(Ipage ipage) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectBriefingReviewedInfoPage", ipage);
	}
	
	@Override
	public List<BriefingInfo> getBriefingResolvedInfoPage(Ipage ipage) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectBriefingResolvedInfoPage", ipage);
	}




	@Override
	public String getBriefingInfoCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectBriefingInfoCount", ipage);
	}
	
	@Override
	public String getBriefingDraftInfoCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectBriefingDraftInfoCount", ipage);
	}
	
	@Override
	public String getBriefingReviewedInfoCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectBriefingReviewedInfoCount", ipage);
	}
	
	@Override
	public String getBriefingResolvedInfoCount(Ipage ipage) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectBriefingResolvedInfoCount", ipage);
	}


	@Override
	public int updateBriefingInfo(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub
		return (int)getSqlMapClientTemplate().update("updateBriefingInfo", briefingInfo);
	}

	@Override
	public BriefingInfo selectBriefingInfoById(String briefingId) {
		// TODO Auto-generated method stub
		BriefingInfo briefingInfo=(BriefingInfo) getSqlMapClientTemplate().queryForObject("selectBriefingInfoById",briefingId);
		return briefingInfo;
	}

	@Override
	public void insertBriefingInfo(BriefingInfo briefingInfo) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insertBriefingInfo", briefingInfo);
	}

	@Override
	public int deleteBriefingInfo(String briefingId) {
		// TODO Auto-generated method stub
		return (int)getSqlMapClientTemplate().delete("deleteBriefingInfo", briefingId);
	}
	/*public List<BriefingInfo> findBrief(BriefingInfo briefingInfo) {
		@SuppressWarnings("unchecked")
		List<BriefingInfo> leave = (List<BriefingInfo>) getSqlMapClientTemplate().queryForList("briefingId", briefingInfo);
		
		return leave;
	}*/


}
