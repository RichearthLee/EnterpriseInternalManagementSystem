package com.mftcc.interior.oa.briefing.control;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.oa.briefing.bean.BriefingInfo;
import com.mftcc.interior.oa.briefing.service.BriefingInfoService;
import com.mftcc.interior.sys.bean.SysUser;




@Controller
public class BriefingFillingControl {

	@RequestMapping(value = { "/briefingFilling" }, method = RequestMethod.GET)
	public String queryBriefingListInfo( ) {
		 
 		
		return "briefing/briefingFilling";
	}
	
	
@Autowired
 	private BriefingInfoService briefingInfoService;
    Logger logger = Logger.getLogger(this.getClass());

//	 * 新员工添加 提交按钮
	 
@RequestMapping(value = { "/briefinginfoinsert" } )
public void insertOneBriefingInfo(BriefingInfo briefinginfo,ModelMap model, HttpSession session) {
	// 从session获取user并设置pactinfo中customerId字段
	boolean result = false;
	try {
		SysUser sysUser = (SysUser) session.getAttribute("sysuser");	
		briefinginfo.setBriefingUser(sysUser.getOpName());		
		briefinginfo.setBriefingUserNo(sysUser.getOpNo());
	 
		result = briefingInfoService.insertBriefingInfoService(briefinginfo);
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




	
/* 	@Autowired
 	private BriefingInfoService briefingInfoService;
 	//private BriefingInfo briefingInfo;
	 
	Logger logger = Logger.getLogger(this.getClass());

	
	@RequestMapping(value = { "/briefingListInfo" }, method = RequestMethod.GET)
	public String queryBriefingListInfo(ModelMap model) {
		 
 		
		return "briefing/briefingListInfo";
	}
	
	
	@RequestMapping(value = { "/briefingFilling" }, method = RequestMethod.GET)
	public String ListInfo(ModelMap model) {
		 
 		 List<BriefingInfo> brflist= briefingInfoService.getBriefingInfo();
		 model.put("briefingInfo", brflist);
		 System.out.println("MFCC数据一共有"+brflist.size()); 
		return "briefing/briefingFilling";
	}
	

@RequestMapping("/getBriefingInfoPage")
public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){

	ipage = briefingInfoService.getBriefingInfoPage(ipage);
	model.put("ipage", ipage);
}


/*@RequestMapping(value = { "/briefingInfoadd" }, method = RequestMethod.GET)
public String addOneBriefingInfo() {
	 
	return "briefing/briefingInfoAdd";
}
	
	
//	 * 新员工添加 提交按钮
	 
@RequestMapping(value = { "/briefinginfoinsert" } )
public void insertOneEmployeeInfo(ModelMap model, HttpSession session) {
	// 从session获取user并设置pactinfo中customerId字段
	boolean result = false;
	try {
	//	result = iEmployeeInfoService.insertEmployeeInfoService(briefinginfo);
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
 
	
//	 * 修改员工，通过ID查找数据的值
	 
@RequestMapping(value = { "/updatebriefingInfoById/{briefingId}" } )
public String queryBriefingInfoById(@PathVariable("briefingId") String briefingId,ModelMap model) {
	
	return "briefing/briefingInfoUpdate";
}


 @RequestMapping("/briefinginfoupdate")
public void updateBriefingInfo( ModelMap model)
{	
	 System.out.println("修改的简报编号是"+briefingInfo.getBriefingId());
	 System.out.println("修改的简报所属人是"+briefingInfo.getBriefingName());
	try {
		briefingInfoService.updateBriefingInfo(briefingInfo);
		model.put(SystemParm.ERROR_CODE, "1");
	} catch (Exception e) {
		model.put(SystemParm.ERROR_CODE, "0");
		e.printStackTrace();
	}
} 
 
 
 @RequestMapping(value = { "/briefinginfodelete/{briefingId}" } )
 public String deleteBriefingInfo(@PathVariable("briefingId") String briefingId, ModelMap model) {
 	// 从session获取user并设置pactinfo中customerId字段
 	boolean result = false;
 	System.out.println("删除");
 	System.out.println(briefingId);
 

 
		result = briefingInfoService.deleteBriefingInfo(briefingId);
		System.out.println("删除"+result);
 	return "briefing/briefingListInfo";
 	
 }

*/
 
}