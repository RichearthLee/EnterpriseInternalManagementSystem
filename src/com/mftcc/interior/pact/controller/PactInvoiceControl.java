
package com.mftcc.interior.pact.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.common.WarningSmsExe;
import com.mftcc.common.util.GetWaterId;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;
import com.mftcc.interior.pact.service.IPactInvoiceService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;


@Controller
@RequestMapping("/pactInvoice")
public class PactInvoiceControl {
	@Autowired
	private IPactInvoiceService pactInvoiceService;
	
	@Autowired
	private ISysUserService userService;
	
	
	@RequestMapping("/invoiceAdd")
	public void invoiceAdd(PactInvoice pactInvoice,ModelMap model,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		pactInvoice.setMarketName(user.getOpName());
		pactInvoice.setMarketNo(user.getOpNo());
		String dateNow = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		pactInvoice.setMarketDate(dateNow);
		pactInvoice.setId(GetWaterId.getPK("invoice_"));
		try {
			pactInvoiceService.addInvoice(pactInvoice);
			model.put(SystemParm.ERROR_CODE, "1");
			model.put("dateNow", dateNow);
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			e.printStackTrace();
		}
		try {
			String message = "发票确认:"+ pactInvoice.getCustomerName()+"的"+ pactInvoice.getInvoiceMoney() +"元发票等待确认";
			String phone = "15638183936";
			//String phone ="18502751865,18739931507";userService.getWarnPersionPhone();
			if(StringUtil.isNotEmpty(phone)){
				//System.out.println("发票信息电话：" + phone);
				String[] result = WarningSmsExe.getSendResult(message, phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/invoiceConfirm")
	public void invoiceConfirm(PactInvoice pactInvoice,ModelMap model,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		pactInvoice.setConfirmName(user.getOpName());
		pactInvoice.setConfirmNo(user.getOpNo());
		pactInvoice.setComfirmDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		try {
			pactInvoiceService.updateInvoiceConfirm(pactInvoice);
			model.put(SystemParm.ERROR_CODE, "1");
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getInvoiceListView")
	public String getInvoiceListView(ModelMap model){
		return "pactManage/invoice/invoiceList";
	}
	
	@RequestMapping("/getInvoicePage")
	public void getInvoicePage(Ipage ipage,ModelMap model,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("sysuser");
		//当前角色是市场人员(002),只查询自己添加的记录
		if(user.getRoleNo().equals("002")){
			ipage.setCurUserNo(user.getOpNo());
		}
		try {
			ipage = pactInvoiceService.getInvoicePage(ipage);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		model.put("ipage", ipage);
	}
	/**
	 * 方法描述： 根据发票Id获取发票信息
	 * @param id
	 * @param requestId 请求来源  1.合同详情中的请求,这个请求会弹窗展示信息，不需要任何操作按钮。  2.发票记录列表中的请求。
	 * @param model
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-3-25 上午9:42:05
	 */
	@RequestMapping("/getOneInvoiceInfo")
	public String getOneInvoiceInfo(String id,String requestId,ModelMap model){
		PactInvoice pactInvoice = new PactInvoice();
		pactInvoice.setId(id);
		try {
			List<PactInvoice> invoiceList = pactInvoiceService.getPactInvoiceList(pactInvoice);
			if(invoiceList.size() >0){
				pactInvoice = invoiceList.get(0);
				if(StringUtil.isEmpty(pactInvoice.getInvoicePercent())){
					double pactFee = Double.parseDouble(pactInvoice.getPactFee());
					double invoiceMoney = Double.parseDouble(pactInvoice.getInvoiceMoney());
					double invoicePercent = (100*invoiceMoney)/pactFee;
					BigDecimal bg = new BigDecimal(invoicePercent);
					pactInvoice.setInvoicePercent(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}
				model.put("pactInvoice",pactInvoice);
			}else{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtil.isEmpty(requestId)){
			requestId = "2";
		}
		model.put("requestId", requestId);
		return "pactManage/invoice/invoiceInfo";
	}
	
	@RequestMapping("/updateInvoice")
	public void updatePactInvoice(PactInvoice pactInvoice,ModelMap model){
		try {
			pactInvoiceService.updateInvoice(pactInvoice);
			model.put(SystemParm.ERROR_CODE, "1");
		} catch (Exception e) {
			model.put(SystemParm.ERROR_CODE, "0");
			e.printStackTrace();
		}
	}
}
