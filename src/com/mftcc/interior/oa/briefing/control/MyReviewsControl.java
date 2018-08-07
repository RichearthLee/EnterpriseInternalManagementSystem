package com.mftcc.interior.oa.briefing.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.service.BriefingInfoService;
import com.mftcc.method.bean.Ipage;

import common.Logger;

@Controller
public class MyReviewsControl {

	@RequestMapping(value = { "/myReviews" }, method = RequestMethod.GET)
	public String queryBriefingListInfo( ) {
		 
 		
		return "briefing/myReviews";
	}
	
	@Autowired
 	private BriefingInfoService briefingInfoService;
 		 
	Logger logger = Logger.getLogger(this.getClass());
	
	

	@RequestMapping("/getBriefingReviewedInfoPage")
	public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){

		ipage = briefingInfoService.getBriefingReviewedInfoPage(ipage);
		model.put("ipage", ipage);
	}
	
	@RequestMapping(value = { "/getBriefingReviewedInfoById"})
	public void getPactListPageById(BriefingInfo briefingInfo,Ipage ipage ,ModelMap model, HttpSession session){
		ipage = briefingInfoService.getBriefingReviewedInfoById(briefingInfo,ipage);
		model.put("ipage", ipage);
	}

	
}
