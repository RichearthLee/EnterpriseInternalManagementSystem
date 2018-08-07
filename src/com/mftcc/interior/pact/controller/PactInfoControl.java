package com.mftcc.interior.pact.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dingxin.util.DateUtil;
import com.mftcc.common.SystemParm;
import com.mftcc.common.WarningSmsExe;
import com.mftcc.common.exception.ServiceException;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactFileInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.Pact_Customer;
import com.mftcc.interior.pact.bean.PayProgressInfo;
import com.mftcc.interior.pact.bean.PaymentPlan;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;
import com.mftcc.interior.pact.service.ICustomerInfoService;
import com.mftcc.interior.pact.service.IPactInfoService;
import com.mftcc.interior.pact.service.IPactInvoiceService;
import com.mftcc.interior.pact.service.IPayProgressService;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;


@Controller
public class PactInfoControl {

	@Autowired
	private IPactInfoService pactInfoService;
	@Autowired
	private ICustomerInfoService customerService;
	@Autowired
	private IPayProgressService payProgressService;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private IPactInvoiceService pactInvoiceService;
	
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 方法描述： 根据合同号查询合同的全部信息 ,暂不用
	 * @param pactId
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-19 上午11:06:34
	 */
	 
	@RequestMapping(value = { "/pactinfo/{pactId}" }, method = RequestMethod.GET)
	public String queryOnePact(@PathVariable("pactId") String pactId,  ModelMap model) {
		PactInfo pactInfo = null;
		try {
			pactInfo = pactInfoService.getPactInfoById(pactId);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		model.put("data", pactInfo);
		
		System.out.println("-----LL");
		System.out.println(model.size());
		return "pactManage/pactInfo";
	}
	
	/**
	 * 方法描述：查询某合同的信息、相关客户的全部信息、相关款项信息 
	 * @param pactId
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-19 上午10:56:36
	 */
	@RequestMapping(value = { "/pactFullInfo/{pactId}/{customerId}" } )
	public String queryPactFullInfo(@PathVariable("pactId") String pactId, @PathVariable("customerId") String customerId, ModelMap model) {
		PactInfo pactInfo = new PactInfo();
		CustomerInfo customerInfo = new CustomerInfo();
		PayProgressInfo payProgress = new PayProgressInfo();
		payProgress.setPactId(pactId);
		List<PayProgressInfo> payProgressList = new ArrayList<PayProgressInfo>();
		List<PaymentPlan> paymentList = new ArrayList<PaymentPlan>();
		List<PactFileInfo> pactFileList =new ArrayList<PactFileInfo>();
		List<PactInvoice> pactInvoiceList = new ArrayList<PactInvoice>();
		List<SysUser> userList = userService.getAllSysUser(new SysUser());
		try {
			customerInfo = customerService.getCustomerInfoById( customerId );
			pactInfo = pactInfoService.getPactInfoById(pactId);
			payProgressList = payProgressService.getPayProgress(payProgress);
			paymentList = pactInfoService.getPaymentList(pactId);
			pactFileList = pactInfoService.getPactFile(pactId);
			PactInvoice pactInvoice = new PactInvoice();
			pactInvoice.setPactId(pactId);
			pactInvoiceList = pactInvoiceService.getPactInvoiceList(pactInvoice);
			
			
			// 用户No映射
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("", "");
			for (SysUser userBean : userList) {
				userMap.put(userBean.getUserNo(), userBean.getOpName());
			}
			model.put("userMap", userMap);
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, e);
		}
		model.put("userList", userList);
		model.put("pactInfo", pactInfo);
		model.put("customer", customerInfo);
		model.put("payProgressList", payProgressList);
		model.put("payment", paymentList);
		model.put("pactFile", pactFileList);
		model.put("pactInvoiceList", pactInvoiceList);
		return "pactManage/pactFullInfo";
	}
	
	/**
	 * 方法描述： 查询可实施的合同信息,返回视图
	 * 可查询的条件：客户名称、产品名称、客户经理名称
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-20 上午10:24:54
	 */
	@RequestMapping("/pactexecute")
	public String queryPactExecute( ModelMap model) {
		
		return "pactManage/pactExecuteList";
	}
	
	@RequestMapping("/getPactExecutePage")
	public void getPactExecutePage(Ipage ipage , ModelMap model){
		ipage = pactInfoService.getPactExecutePage(ipage);
		model.put("ipage", ipage);
	}
	
	/**
	 * 方法描述：获得实施确认的视图 
	 * @param pactId
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-26 下午5:10:57
	 */
	@RequestMapping("/executeConfirm/{pactId}")
	public String executeConfirm( @PathVariable String pactId , ModelMap model) {
		PactInfo  pactInfo = null;
		try {
			pactInfo  = pactInfoService.getPactInfoById(pactId);
		} catch (ServiceException e) {
			model.put(SystemParm.ERROR_CODE, e.getServiceExceptionEnum().getCode());
			model.put(SystemParm.ERROR_MESSAGE, e.getServiceExceptionEnum().getMessage());
		}
		model.put("data", pactInfo);
		return "pactManage/executeConfirm";
	}
	
	/**
	 * 方法描述： 从前台请求获取实施人员确认的结果，并对数据库进行更新
	 * @param pactinfo
	 * @param model
	 * @param session
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-26 下午5:42:57
	 */
	@RequestMapping("/executeConfirm")
	public void executeConfirm1( PactInfo pactinfo , ModelMap model, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("sysuser");
		pactinfo.setExecutePersion(user.getUserNo());
		boolean result = false;
		try {
			result  = pactInfoService.updateExecuteConfirm(pactinfo);
			if(result)
			{
				model.put(SystemParm.ERROR_CODE, "1");
			}else
			{
				throw new Exception("实施确认失败");
			}
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
		}
		//model.put("data", pactInfo);
	}
	/**
	 * 方法描述： 用来查询所有客户和所有合同的部分信息，用以列表显示
	 * @param model ,requestId 代表请求的来源
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2015-11-19 上午9:44:32
	 */
	@RequestMapping("/pactListInfo")
	public String queryPactListInfo( String requestId,ModelMap model) {
		//如果requestId=2,说明请求来自warning.jsp中，需要把pactListInfo.jsp中的select查询条间设置成“收款逾期合同”
		//如果requestId=1或null,说明请求来自菜单“合同信息管理”->“合同管理”
		if(StringUtil.isEmpty(requestId)){
			requestId = "1";
		}
		model.put("requestId", requestId);
		return "pactManage/pactListInfo";
	}
	@RequestMapping("/getPactListPage")
	public void getPactListPage(Ipage ipage ,ModelMap model, HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		System.out.println(user.toString());
		System.out.println(user.getOpName());
		System.out.println(user.getRoleNo());
		if (user != null && !user.getRoleNo().contains("001")) {
			ipage.setCurUserNo(user.getUserNo());
		}
		System.out.println(user.getRoleNo());
		ipage = pactInfoService.getPactListPage(ipage);
		model.put("ipage", ipage);
	}
	/**
	 * 方法描述：修改合同信息，从前台获取数据，交service层处理，并向前台返回结果 
	 * @param pactInfo
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2015-12-2 上午10:28:27
	 */
	@RequestMapping(value = { "/pactinfoupdate" })
	public void updateOnePact(PactInfo pactInfo, ModelMap model) {
		boolean result = false;
		try {
			 result = pactInfoService.updatePactInfo(pactInfo);
			 if(result)
			 {
				 model.put(SystemParm.ERROR_CODE, "1");
			 }
			 else{
				 throw new Exception();
			 }
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			//model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
		}
	}

	/**
	 * 方法描述：从前台请求中获取新增加的合同信息和客户信息，并交到service层处理 
	 * @param pactAndCustomer
	 * @param model
	 * @param session
	 * void
	 * @author Cuizk
	 * @date 2015-11-25 下午2:17:55
	 */
	@RequestMapping(value = { "/pactinfoinsert" } )
	public void insertOnePact(Pact_Customer pactAndCustomer, ModelMap model, HttpSession session) {
		// 从session获取user并设置pactinfo中customerId字段
		SysUser user = (SysUser) session.getAttribute("sysuser");
		pactAndCustomer.setMarketerId(user.getOpNo());
		boolean result = false;
		try {
			result = pactInfoService.insertPactInfo(pactAndCustomer);
			if (result)
			{
				model.put(SystemParm.ERROR_CODE, "1");
				//WarningSmsExe.getSendResult("新增合同", "18739931507");
			} else
			{
				throw new Exception("增加合同信息失败");
			}
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, e.getMessage());
		}
	}
	
	/**
	 * 方法描述：从前台获得需要更新的客户和合同信息，由service层处理，并向前台返回结果 
	 * @param pactInfo
	 * @param customerInfo
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2015-12-2 下午2:28:03
	 */
	@RequestMapping("/updatePactAndCus")
	public void updatePactAndCus(PactInfo pactInfo , CustomerInfo customerInfo , ModelMap model)
	{	
		try {
			pactInfoService.updatePactAndCus(pactInfo, customerInfo);
			model.put(SystemParm.ERROR_CODE, "1");
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			e.printStackTrace();
		}
	}
	/**
	 * 方法描述： 获得增加合同的视图
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-1-6 上午8:56:28
	 */
	@RequestMapping(value = { "/pactinfoadd" }, method = RequestMethod.GET)
	public String addOnePact( ModelMap model) {
		List<SysUser> userList = userService.getAllSysUser(new SysUser());
		model.put("userList", userList);
		return "pactManage/pactAdd";
	}
	/**
	 * 方法描述： 新增一个合同附件路径
	 * @param pactfile
	 * @param model
	 * void
	 * @author Cuizk
	 * @date 2016-1-6 上午8:56:43
	 */
	@RequestMapping("/addPactFilepath")
	public void addPactFile(PactFileInfo pactfile , ModelMap model){
		if(StringUtil.isEmpty(pactfile.getPactId()) && StringUtil.isEmpty(pactfile.getPactFilepath())){
			return ;
		}
		try{
			
			pactInfoService.insertPactFile(pactfile);
			model.put(SystemParm.ERROR_CODE, "1");
		}catch(Exception e){
			model.put(SystemParm.ERROR_CODE, "0");
		}
	}
	
	/**
	 * 方法描述： 获得合同统计的视图。
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-1-9 下午2:51:15
	 */
	@RequestMapping("/getPactStatistics")
	public String getPactStatistics(ModelMap model){
		return "pactManage/pactStatistics";
	}
	@RequestMapping("/getPactStaPage")
	public void getPactStaPage(Ipage ipage ,ModelMap model){
		try{
			
			ipage = pactInfoService.getPactStaPage(ipage);
		}catch(Exception e){
			
		}
		model.put("ipage", ipage);
	}
	/**
	 * 方法描述： 获得合同收款提示的视图
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-1-21 下午2:27:08
	 */
	@RequestMapping("/getPactWarningView")
	public String getPactWarningView(ModelMap model){
		return "pactManage/pactWarning";
	}
	@RequestMapping("/getPactWarningPage")
	public void getPactWarningPage(Ipage ipage,ModelMap model){
		try {
			ipage = pactInfoService.getPactWarningPage(ipage);
		} catch (Exception e) {
			
		}
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/getTichengMonthPactView")
	public String getTichengMonthPactView(ModelMap model){
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		String dateNow = df1.format(new Date());
		model.put("dateNow", dateNow);
		return "pactManage/tichengMonthPactList";
	}
	/**
	 * 方法描述： 当月提成合同的计算（销售）
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-2-27 上午10:55:19
	 */
	@RequestMapping("/getTichengMonthPactList")
	public void getTichengMonthPactList(Ipage ipage ,ModelMap model){
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		String startDate = ipage.getParm0();
		if(StringUtil.isEmpty(startDate)){
			startDate = df1.format(new Date());
		}
		String endDate = com.mftcc.common.util.DateUtil.getNextMonth(startDate);
		ipage.setParm0(startDate);
		ipage.setParm1(endDate);
		try {
			ipage = pactInfoService.getTichengPact(ipage,"month");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("ipage", ipage);
	}
	@RequestMapping("/getTichengQuarterPactView")
	public String getTichengQuarterPactView(ModelMap model){
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		String dateNow = df1.format(new Date());
		model.put("dateNow", dateNow);
		return "pactManage/tichengQuarterPactList";
	}
	/**
	 * 方法描述： 当季度提成合同的计算（售前/售后）
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-2-27 上午10:55:38
	 */
	@RequestMapping("/getTichengQuarterPactList")
	public void getTichengQuarterPactList(Ipage ipage,ModelMap model){
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		String startDate = ipage.getParm0();
		String endDate = ipage.getParm1();
		if(StringUtil.isEmpty(startDate)){
			startDate = df1.format(new Date());
		}
		if(StringUtil.isEmpty(endDate)){
			endDate = com.mftcc.common.util.DateUtil.getNextMonth(null);
		}else{
			endDate = com.mftcc.common.util.DateUtil.getNextMonth(endDate);
		}
		ipage.setParm0(startDate);
		ipage.setParm1(endDate);
		try {
			ipage = pactInfoService.getTichengPact(ipage,"quarter");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("ipage", ipage);
		
	}
	
	@RequestMapping("/warningView")
	public String warningView(ModelMap model,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		Ipage ipage = new Ipage();
		String payProgressCount = "";
		String pactInvoiceCount = "";
		String pactExecuteCount = "";
		String pactCount = "";
		//003是财务
		if(user == null){
			
		}else{
			try{
				if(user.getRoleNo().contains("999")){
					payProgressCount = payProgressService.getPayProgressUnconfirm(ipage);
					pactInvoiceCount = pactInvoiceService.getInvoiceUnconfirm(ipage);
					pactExecuteCount = pactInfoService.getPactExecuteUnconfirm(ipage);
					pactCount = pactInfoService.getPact(ipage);
				}else if(user.getRoleNo().contains("004")){
					pactExecuteCount = pactInfoService.getPactExecuteUnconfirm(ipage);
				}else if(user.getRoleNo().contains("003")){
					payProgressCount = payProgressService.getPayProgressUnconfirm(ipage);
					pactInvoiceCount = pactInvoiceService.getInvoiceUnconfirm(ipage);
				}else if(user.getRoleNo().contains("002")){
					pactCount = pactInfoService.getPact(ipage);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.put("payProgressCount", payProgressCount);
		model.put("pactInvoiceCount", pactInvoiceCount);
		model.put("pactExecuteCount", pactExecuteCount);
		model.put("pactCount", pactCount);
		return "pactManage/warning";
	}
}
