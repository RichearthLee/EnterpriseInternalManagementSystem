package com.mftcc.interior.oa.briefing.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.service.BriefingInfoService;
import com.mftcc.method.bean.Ipage;

import common.Logger;

@Controller
public class MembersBriefingControl {

	@RequestMapping(value = { "/membersBriefing" }, method = RequestMethod.GET)
	public String queryBriefingListInfo() {

		return "briefing/membersBriefing";
	}

	@Autowired
	private BriefingInfoService briefingInfoService;
	
	// private BriefingInfo briefingInfo;

	Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/getBriefingInfoPage")
	public void getPactListPage(Ipage ipage, ModelMap model, HttpSession session) {

		ipage = briefingInfoService.getBriefingInfoPage(ipage);
		model.put("ipage", ipage);
	}

	@RequestMapping(value = { "/queryBriefingInfoById/{briefingId}" })
	public String queryBriefingInfoById(
			@PathVariable("briefingId") String briefingId, ModelMap model) {
		BriefingInfo briefingInfo = briefingInfoService
				.selectBriefingInfoByIdService(briefingId);
		model.put("briefingInfo", briefingInfo);
		return "briefing/briefingReview";
	}
	
	@RequestMapping(value = { "/briefinginfodelete/{briefingId}" } )
	public String deleteBriefingInfo(@PathVariable("briefingId") String briefingId, ModelMap model) {
		// 从session获取user并设置pactinfo中customerId字段
		boolean result = false;
		result = briefingInfoService.deleteBriefingInfo(briefingId);
		System.out.println("删除"+result);
		return "briefing/membersBriefing";
		
	}
	
	@RequestMapping(value = { "/getBriefingUnreadInfoById"})
	public void getPactListPageById(BriefingInfo briefingInfo,Ipage ipage ,ModelMap model, HttpSession session){
		ipage = briefingInfoService.getBriefingUnreadInfoById(briefingInfo,ipage);
		model.put("ipage", ipage);
	}


//	@RequestMapping("/briefinginfoupdate")
//	public void updateBriefingInfo(ModelMap model) {
//		System.out.println("修改的简报编号是" + briefingInfo.getBriefingId());
//		System.out.println("修改的简报所属人是" + briefingInfo.getBriefingName());
//		try {
//			briefingInfoService.updateBriefingInfo(briefingInfo);
//			model.put(SystemParm.ERROR_CODE, "1");
//		} catch (Exception e) {
//			model.put(SystemParm.ERROR_CODE, "0");
//			e.printStackTrace();
//		}
//	}

}
