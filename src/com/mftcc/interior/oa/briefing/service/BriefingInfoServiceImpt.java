package com.mftcc.interior.oa.briefing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.dao.BriefingInfoDao;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

@Service
public class BriefingInfoServiceImpt implements  BriefingInfoService {

	
	@Autowired
	BriefingInfoDao briefingInfoDao;
	@Override
	public List<BriefingInfo> getBriefingInfo() throws ServiceException {
		return briefingInfoDao.selectBriefingInfo();
	}
	 
	@Override
	public Ipage getBriefingInfoPage(Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<BriefingInfo> briefingInfo = briefingInfoDao.getBriefingInfoPage(ipage);
		int allRecord = Integer.parseInt(briefingInfoDao.getBriefingInfoCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefingInfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override
	public Ipage getBriefingDraftInfoPage(Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<BriefingInfo> briefingInfo = briefingInfoDao.getBriefingDraftInfoPage(ipage);
		int allRecord = Integer.parseInt(briefingInfoDao.getBriefingDraftInfoCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefingInfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override
	public Ipage getBriefingDraftInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
	
		List<BriefingInfo> briefinginfo = briefingInfoDao.getBriefingDraftInfoById(briefingInfo);
		/*int allRecord = Integer.parseInt(briefingInfoDao.getBriefingDraftInfoCount(ipage));*/
		int allRecord = 1;
		
		int totalpage = 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefinginfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override   
	public Ipage getBriefingReviewedInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
	
		List<BriefingInfo> briefinginfo = briefingInfoDao.getBriefingReviewedInfoById(briefingInfo);
		/*int allRecord = Integer.parseInt(briefingInfoDao.getBriefingDraftInfoCount(ipage));*/
		int allRecord = 1;
		
		int totalpage = 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefinginfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override   
	public Ipage getBriefingUnreadInfoById(BriefingInfo briefingInfo,Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
	
		List<BriefingInfo> briefinginfo = briefingInfoDao.getBriefingUnreadInfoById(briefingInfo);
		/*int allRecord = Integer.parseInt(briefingInfoDao.getBriefingDraftInfoCount(ipage));*/
		int allRecord = 1;
		
		int totalpage = 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefinginfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override
	public Ipage getBriefingReviewedInfoPage(Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<BriefingInfo> briefingInfo = briefingInfoDao.getBriefingReviewedInfoPage(ipage);
		int allRecord = Integer.parseInt(briefingInfoDao.getBriefingReviewedInfoCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefingInfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}
	
	@Override
	public Ipage getBriefingResolvedInfoPage(Ipage ipage) throws ServiceException {
		// TODO Auto-generated method stub
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<BriefingInfo> briefingInfo = briefingInfoDao.getBriefingResolvedInfoPage(ipage);
		int allRecord = Integer.parseInt(briefingInfoDao.getBriefingResolvedInfoCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(briefingInfo);
		ipage.setAllRecord(allRecord);
	
		return ipage;
	}




	@Override
	public boolean insertBriefingInfoService(BriefingInfo briefingInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		briefingInfoDao.insertBriefingInfo(briefingInfo);
		return true;
	}

	@Override
	public boolean updateBriefingInfo(BriefingInfo briefingInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		briefingInfoDao.updateBriefingInfo(briefingInfo);
		return false;
	}

	@Override
	public boolean deleteBriefingInfo(String briefingId)
			throws ServiceException {
		// TODO Auto-generated method stub
		briefingInfoDao.deleteBriefingInfo(briefingId);
		return false;
	}

	@Override
	public BriefingInfo selectBriefingInfoByIdService(String briefingId) {
		// TODO Auto-generated method stub
		BriefingInfo briefingInfo=briefingInfoDao.selectBriefingInfoById(briefingId);
		return briefingInfo;
	}
	






	

}
