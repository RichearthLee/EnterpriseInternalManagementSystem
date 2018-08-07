package com.mftcc.interior.pact.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.common.WarningSmsExe;
import com.mftcc.common.exception.ServiceException;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PayProgressInfo;
import com.mftcc.interior.pact.service.IPactInfoService;
import com.mftcc.interior.pact.service.IPayProgressService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;


@Controller
@RequestMapping("/payProgress")
public class PayProgressControl {

	@Autowired
	private IPayProgressService payProgressService;
	
	@Autowired
	private IPactInfoService pactInfoService;
	@Autowired
	private ISysUserService userService;

	/**
	 * 方法描述： 查询某次款项的信息和相关合同信息，返回视图
	 * @param pactId
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-19 下午3:34:30
	 */
	@RequestMapping(value = { "/payprogress/{pactId}/{payYet}" } )
	public String queryPayProgressFull(@PathVariable("pactId") String pactId, @PathVariable("payYet") String payYet, ModelMap model) {
		//List<PayProgressInfo> payProgressInfos = null;
		PayProgressInfo payProgress = new PayProgressInfo();
		PactInfo pactInfo = null;
		payProgress.setPactId(pactId);
		payProgress.setPayyet(payYet);
		try {
			payProgress = payProgressService.getPayProgress(payProgress).get(0);
			pactInfo = pactInfoService.getPactInfoById(pactId);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		model.put("payProgressData", payProgress);
		model.put("pactInfoData", pactInfo);

		return "pactManage/payProgressFull";
	}
	
	/**
	 * 方法描述： 获得款项信息信息列表的视图.这个视图在财务人员的款项确认时用到
	 * 可查询的字段有：客户名称、客户经理名称、汇款人、汇款时间
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-19 下午3:10:00
	 */
	@RequestMapping(value = { "/payprogressList" })
	public String queryPayProgress( ModelMap model) {
		
		return "pactManage/payProgressList";
	}
	
	@RequestMapping("/getPayProgressPage")
	public void getPayProgressPage(Ipage ipage, ModelMap model,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		if(user.getRoleNo().contains("002")){
			ipage.setCurUserNo(user.getOpNo());
		}
		try {
			ipage = payProgressService.getPayProgressPage(ipage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("ipage", ipage);
	}
	/**
	 * 方法描述：从前台获取新款项信息，交到service进行增加 
	 * @param payProgressInfo
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2015-11-30 下午2:17:40
	 */
	@RequestMapping(value = { "/payprogressinsert" })
	public void insertPayProgress( PayProgressInfo payProgressInfo, ModelMap model , HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("sysuser");
		payProgressInfo.setMarketerId(user.getOpNo());
		try {
			payProgressInfo = payProgressService.insertPayProgress(payProgressInfo);
			model.put(SystemParm.ERROR_CODE, "1");
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, "增加款项记录失败");
		}
		try {
			String message = "款项确认：" + payProgressInfo.getCustomerName()+"的"+ payProgressInfo.getProceedMoney() +"元款项等待确认";
			String phone = "15638183936";
			//String phone ="18502751865,18739931507";userService.getWarnPersionPhone();
			if(StringUtil.isNotEmpty(phone)){
				//System.out.println("款项信息电话：" + phone);
				String[] result = WarningSmsExe.getSendResult(message, phone);
			}
			//System.out.println(result[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述： 获得增加款项的视图
	 * @param pactId
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-30 上午11:45:00
	 */
	@RequestMapping(value = { "/payprogressadd/{pactId}" })
	public String insertpayProgress(@PathVariable("pactId") String pactId, ModelMap model) {
		PayProgressInfo payProgressInfo = new PayProgressInfo();
		payProgressInfo.setPactId(pactId);
		List<PayProgressInfo> payProgressInfos = null;
		try {
			payProgressInfos = payProgressService.getPayProgress(payProgressInfo);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		//新的第几笔支付等于款项信息表中该合同记录加1
		payProgressInfo.setPayyet(String.valueOf(payProgressInfos.size() + 1) );
		model.put("payProgressData", payProgressInfo);
		return "pactManage/payProgressAdd";
	}
	/**
	 * 方法描述：财务确认，从前台请求获得款项信息（即财务确认结果），并由service层更新，同时会更新合同表中的信息 
	 * @param payProgressInfo
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2015-11-25 下午5:09:30
	 */
	@RequestMapping(value = { "/confirmPayprogress" } )
	public void confirmPayProgress(PayProgressInfo payProgressInfo , ModelMap model, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("sysuser");
		payProgressInfo.setFincalpersion(user.getOpNo());
		try {
			boolean updatePayProgress = payProgressService.updatePayProgress(payProgressInfo);
			if(updatePayProgress){
				model.put(SystemParm.ERROR_CODE, "1");
			}
			else{
				throw new Exception("款项确认失败");
			}
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
		}
		//model.put("payProgressData", payProgressInfo);
		//return "pactManage/payProgressFull";
	}
	/**
	 * 方法描述： 获得款项信息列表，这个是市场人员查看自己客户的款项信息时用到
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-1-28 下午5:13:50
	 */
	@RequestMapping("/getpayProgressListView")
	public String getpayProgressListView(ModelMap model){
		return "pactManage/payProgressListForMarket";
	}
	/**
	 * 方法描述： 获得更新某次款项信息的视图
	 * @param pactId
	 * @param payyet
	 * @param requestId 请求来源  1.合同详情中的请求,这个请求会弹窗展示信息，不需要任何操作按钮。  2.‘合同管理’->‘收款记录’中中的请求。
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-1-28 下午5:14:36
	 */
	@RequestMapping("/getPayProgressView")
	public String updatePayProgressView(String pactId,String payyet,String requestId,ModelMap model){
		PayProgressInfo payProgressInfo = new PayProgressInfo();
		payProgressInfo.setPactId(pactId);
		payProgressInfo.setPayyet(payyet);
		List<PayProgressInfo> payProgressList = payProgressService.getPayProgress(payProgressInfo);
		if(payProgressList.size() >0)
		{
			payProgressInfo = payProgressList.get(0);
		}
		if(StringUtil.isEmpty(requestId)){
			requestId = "2";
		}
		model.put("requestId", requestId);
		model.put("payProgress", payProgressInfo);
		return "pactManage/payProgressInfo";
	}
	/**
	 * 方法描述： 更新款型信息。目前仅市场人员修改某次款项信息时用到。
	 * @param payProgressInfo
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2016-1-28 下午5:15:16
	 */
	@RequestMapping("/updatePayProgress")
	public void updatePayProgress(PayProgressInfo payProgressInfo , ModelMap model){
		try {
			payProgressService.updatePayProgress1(payProgressInfo);
			model.put(SystemParm.ERROR_CODE, "1");
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			e.printStackTrace();
		}
	}
}
