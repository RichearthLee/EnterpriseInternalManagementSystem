package com.mftcc.interior.oa.briefing.control;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.employee.bean.EmployeeInfo;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.service.BriefingInfoService;
import com.mftcc.interior.oa.leave.bean.LeaveBean;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

import common.Logger;


@Controller
public class MyBriefingControl {
	
	
	@Autowired
 	private BriefingInfoService briefingInfoService;
 		 
	Logger logger = Logger.getLogger(this.getClass());

	
/*	
	@RequestMapping(value = { "/briefingFilling" }, method = RequestMethod.GET)
	public String ListInfo(ModelMap model) {
		 
 		 List<BriefingInfo> brflist= briefingInfoService.getBriefingInfo();
		 model.put("briefingInfo", brflist);
		 System.out.println("MFCC数据一共有"+brflist.size()); 
		return "briefing/briefingFilling";
	}
	*/
	
	
	@RequestMapping(value = { "/myBriefing" }, method = RequestMethod.GET)
	public String queryBriefingListInfo( ) {	 	
		return "briefing/myBriefing";
	}
	

@RequestMapping("/getBriefingDraftInfoPage")
public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){

	ipage = briefingInfoService.getBriefingDraftInfoPage(ipage);
	model.put("ipage", ipage);
}

@RequestMapping(value = { "/getBriefingDraftInfoById"})
public void getPactListPageById(BriefingInfo briefingInfo,Ipage ipage ,ModelMap model, HttpSession session){
	ipage = briefingInfoService.getBriefingDraftInfoById(briefingInfo,ipage);
	model.put("ipage", ipage);
}


@RequestMapping(value = { "/queryBriefingDraftInfoById/{briefingId}" } )
public String queryBriefingInfoById(@PathVariable("briefingId") String briefingId,ModelMap model) {
	 BriefingInfo briefingInfo = briefingInfoService.selectBriefingInfoByIdService(briefingId);
	 model.put("briefingInfo", briefingInfo);
	return "briefing/briefingUpdate";
}


@RequestMapping(value = { "/briefingdraftinfodelete/{briefingId}" } )
public String deleteBriefingInfo(@PathVariable("briefingId") String briefingId, ModelMap model) {
	// 从session获取user并设置pactinfo中customerId字段
	boolean result = false;
	result = briefingInfoService.deleteBriefingInfo(briefingId);
	System.out.println("删除"+result);
	return "briefing/myBriefing";
	
}

@RequestMapping("/briefinginfoupdate")
public void updateBriefingInfo(BriefingInfo briefinginfo, ModelMap model,HttpSession session)
{	
	 
	boolean result = false;
	try {	 
		result = briefingInfoService.updateBriefingInfo(briefinginfo);
		if (result)
		{
			model.put(SystemParm.ERROR_CODE, "1");
		} else
		{
			throw new Exception("增加简报信息失败");
		}
	} catch (Exception e) {
		model.put(SystemParm.ERROR_CODE, "0");
		model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
	}
} 
/*@RequestMapping("/updateBriefingInfoById")
public String updateBriefingInfoById(BriefingInfo briefingInfo,ModelMap model)
{
	
	List<BriefingInfo> brief1=findBrief(briefingInfo);
	model.put("brief", brief1.get(0));
	
	return "briefing/briefingUpdate";
}
@RequestMapping("/findBrief")
public List<BriefingInfo> findBrief(BriefingInfo briefingInfo) {
	List<BriefingInfo> leaveList =  briefingInfoService.findBrief(briefingInfo);
	
    return leaveList;
	
}*/

/*@RequestMapping(value = { "/briefingInfoadd" }, method = RequestMethod.GET)
public String addOneBriefingInfo() {
	 
	return "briefing/briefingInfoAdd";
}
	*/

}
