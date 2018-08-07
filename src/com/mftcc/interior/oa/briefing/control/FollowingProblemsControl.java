package com.mftcc.interior.oa.briefing.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.service.BriefingInfoService;
import com.mftcc.method.bean.Ipage;

import common.Logger;

@Controller
public class FollowingProblemsControl {
	@Autowired
	private BriefingInfoService briefingInfoService;
	@RequestMapping(value = { "/followingProblems" }, method = RequestMethod.GET)
	public String queryBriefingListInfo( ) {
		 
 		
		return "briefing/followingProblems";
	}
	
	// private BriefingInfo briefingInfo;

	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = { "/queryBriefingReviewedInfoById/{briefingId}" })
	public String queryBriefingInfoById(@PathVariable("briefingId") String briefingId, ModelMap model) {
		BriefingInfo briefingInfo = briefingInfoService.selectBriefingInfoByIdService(briefingId);
		model.put("briefingInfo", briefingInfo);
		return "briefing/problemsView";
	}
	
	@RequestMapping("/getBriefingResolvedInfoPage")
	public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){

		ipage = briefingInfoService.getBriefingResolvedInfoPage(ipage);
		model.put("ipage", ipage);
	}
	
	
}
