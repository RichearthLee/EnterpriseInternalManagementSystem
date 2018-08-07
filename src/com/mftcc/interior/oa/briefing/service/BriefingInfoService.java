package com.mftcc.interior.oa.briefing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;



public interface BriefingInfoService {
	//public EmployeeInfo getEmployeeInfoById  (String id)throws ServiceException;
	

	public List<BriefingInfo> getBriefingInfo  () throws ServiceException;

	public Ipage getBriefingInfoPage(Ipage ipage) throws ServiceException;
	public Ipage getBriefingDraftInfoPage(Ipage ipage) throws ServiceException;
	public Ipage getBriefingReviewedInfoPage(Ipage ipage) throws ServiceException;
	public Ipage getBriefingResolvedInfoPage(Ipage ipage) throws ServiceException;
	
	public Ipage getBriefingDraftInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException;
	public Ipage getBriefingReviewedInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException;
	public Ipage getBriefingUnreadInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException;

	public boolean insertBriefingInfoService(BriefingInfo briefingInfo) throws ServiceException;
	

 	public boolean updateBriefingInfo  (BriefingInfo briefingInfo)throws ServiceException;
	
 	public boolean deleteBriefingInfo  (String briefingId)throws ServiceException;
 	public BriefingInfo selectBriefingInfoByIdService(String briefingId);
  /* 	public Ipage selectEmployeeInfoByNameService(Ipage ipage) throws ServiceException;
*/
 
	
}
