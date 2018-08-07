package com.mftcc.interior.oa.briefing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.method.bean.Ipage;

@Repository
public interface BriefingInfoDao {

	   public List<BriefingInfo> selectBriefingInfo(BriefingInfo briefingInfo);
	    
	   /* 
	    public int updateEmployeeInfo(EmployeeInfo employeeInfo);
	  
	    public void insertPactInfo(EmployeeInfo employeeInfo);
	    */
	 
	    public List<BriefingInfo> selectBriefingInfo();
	    

	/*    public List<EmployeeInfo> getEmployeeInfoStaPage(Ipage ipage);

	    public List<EmployeeInfo> getEmployeeStaAll(Ipage ipage) throws Exception;*/
	    
	    public  List<BriefingInfo> getBriefingInfoPage(Ipage ipage);
	    public  List<BriefingInfo> getBriefingDraftInfoPage(Ipage ipage);
	    public  List<BriefingInfo> getBriefingReviewedInfoPage(Ipage ipage);
	    public  List<BriefingInfo> getBriefingResolvedInfoPage(Ipage ipage);
	    
	    public  List<BriefingInfo> getBriefingDraftInfoById(BriefingInfo briefingInfo);
	    public  List<BriefingInfo> getBriefingReviewedInfoById(BriefingInfo briefingInfo);
	    public  List<BriefingInfo> getBriefingUnreadInfoById(BriefingInfo briefingInfo);
	    
	    public String getBriefingInfoCount(Ipage ipage);
	    public String getBriefingDraftInfoCount(Ipage ipage);
	    public String getBriefingReviewedInfoCount(Ipage ipage);
	    public String getBriefingResolvedInfoCount(Ipage ipage);

	    public int updateBriefingInfo(BriefingInfo briefingInfo);
	    
	    public BriefingInfo selectBriefingInfoById(String briefingId);
	  
	    public void insertBriefingInfo(BriefingInfo briefingInfo);
	  
	    public int deleteBriefingInfo(String briefingId);
	    
	/*    public String getEmployeeInfoByNameCount(Ipage ipage);
	    public  List<EmployeeInfo> getEmployeeInfoByName(Ipage ipage  );*/
	    //public List<BriefingInfo> findBrief(BriefingInfo briefingInfo);
	    
	    
	}
