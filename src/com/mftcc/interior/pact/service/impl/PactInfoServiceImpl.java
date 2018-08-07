
package com.mftcc.interior.pact.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.common.util.DateUtil;
import com.mftcc.common.util.GetWaterId;
import com.mftcc.common.util.MathExtend;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactExecuteBean;
import com.mftcc.interior.pact.bean.PactFileInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PactListInfo;
import com.mftcc.interior.pact.bean.PactWarningBean;
import com.mftcc.interior.pact.bean.Pact_Customer;
import com.mftcc.interior.pact.bean.PaymentPlan;
import com.mftcc.interior.pact.bean.listBean.TichengPactBean;
import com.mftcc.interior.pact.dao.ICustomerInfoDao;
import com.mftcc.interior.pact.dao.IPactInfoDao;
import com.mftcc.interior.pact.service.IPactInfoService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysUserDao;

@Service
public class PactInfoServiceImpl implements IPactInfoService {
    @Autowired
	IPactInfoDao pactInfoDao;
    @Autowired
    ICustomerInfoDao customerInfoDao;
    @Autowired
    ISysUserDao usersDao;
	@Override
	public PactInfo getPactInfoById(String id) throws ServiceException {
		PactInfo param = new PactInfo();
		param.setPactId(id);
        List<PactInfo> pactinfos = pactInfoDao.selectPactInfo(param);
        if (pactinfos.size() > 0) {
        	return pactinfos.get(0);
		}
        return null;
	}
	
	@Override
	public boolean updatePactInfo  (PactInfo pactInfo)throws ServiceException{
		if(pactInfo.getPactId() == null )
			return false;
		if(pactInfoDao.updatePactInfo(pactInfo) == 1)
			return true;
		return false;
	}
	
	
	
	@Override
	public boolean insertPactInfo  (Pact_Customer pactAndCustomer)throws ServiceException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		pactAndCustomer.setEntryDate(df.format(new Date()));
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setDefault(pactAndCustomer);
		customerInfo.setProvince("河南省");
		//如果customerID为空，说明是新增客户
		if (StringUtil.isEmpty(pactAndCustomer.getCustomerId()))
		{
			pactAndCustomer.setCustomerId(df.format(new Date()));
			customerInfoDao.insertCustomerInfo(customerInfo);
			System.out.println(customerInfo.getProvince());
			System.out.println(pactAndCustomer.getCustomerId());
			
		}else{
			//如果客户号存在，说明是已存在的客户，只需要更新客户信息即可。
			customerInfo.setCustomerId(pactAndCustomer.getCustomerId());
		
			
			customerInfoDao.updateCustomerInfo(customerInfo);
			
			System.out.println(customerInfo.toString());
		}

		List<PactInfo> pactInfoList = null;
		// 生成合同号
		/*String pactId = GetWaterId.getPK("pact_");
		PactInfo pactinfo = new PactInfo();
		pactinfo.setPactId(pactId);
		pactInfoList = pactInfoDao.selectPactInfo(pactinfo);
		if (pactInfoList.size() > 0)
		{	//合同号存在，则重新生成一次。
			pactId = GetWaterId.getPK("pact_");
			pactinfo.setPactId(pactId);
		} else 
		{*/
		 PactInfo pactinfo = new PactInfo();
			pactinfo.setDefault(pactAndCustomer);
			pactInfoDao.insertPactInfo(pactinfo);
			
		
		//将收款计划存入数据库
		
		System.out.println(pactAndCustomer.getPaymentPlanList().get(0).getPaymentDay());
		for(PaymentPlan paymentPlan : pactAndCustomer.getPaymentPlanList()){
			//收款比例为0的直接跳过
			if(paymentPlan.getPaymentPercent().equals("0")){
				continue;
			}
			paymentPlan.setPactId(pactinfo.getPactId());
			System.out.println("史");
			System.out.println(paymentPlan.getPactId());
			String paymentPercent = paymentPlan.getPaymentPercent() + "%";
			paymentPlan.setPaymentPercent(paymentPercent);
			paymentPlan.setPaymentState("1");

			System.out.println("国");
			System.out.println(pactAndCustomer.getPactStartDate());
			
			//从前台获取到的起始时间paymentStartDate，“1”代表合同签订时间，现在可以确定 ；“2”代表实施确认时间，现在无法确定，继续将“2”存入数据库
			if(paymentPlan.getPaymentStartDate().equals("1")){
				SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				paymentPlan.setPaymentStartDate(ds.format(new Date()));
				String date = DateUtil.addByDay1(paymentPlan.getPaymentStartDate(), Integer.parseInt(paymentPlan.getPaymentDay()));
				if(StringUtil.isNotEmpty(date)){
					paymentPlan.setPaymentDay(date);
				}
			}
			System.out.println("梅");
			pactInfoDao.insertPayment(paymentPlan);
			
			
		}
		
		//将合同附件的路径保存
		if(pactAndCustomer.getPactFileList() != null)
		{
			for(PactFileInfo pactFile : pactAndCustomer.getPactFileList()){
				pactFile.setPactId(pactinfo.getPactId());
				pactInfoDao.insertPactFile(pactFile);
			}
		}
		return true;
	}

	@Override
	public List<PactListInfo> getPactListInfo() throws ServiceException {
		return pactInfoDao.selectPactListInfo();
	}

	

	@Override
	public void insertPactFile(PactFileInfo pactFielInfo) throws ServiceException{
		pactInfoDao.insertPactFile(pactFielInfo);
	}

	@Override
	public List<PactFileInfo> getPactFile(String pactId) {
		return pactInfoDao.selectPactFile(pactId);
	}

	@Override
	public boolean updateExecuteConfirm(PactInfo pactInfo) {
		if(pactInfo.getExecuteStartDate() == "" || pactInfo.getPactId() == "")
			return false;
		pactInfo.setPactState("3");
		if(pactInfoDao.updatePactInfo(pactInfo) != 1){
			return false;
		}
		PaymentPlan paymentPlan = new PaymentPlan();
		paymentPlan.setPactId( pactInfo.getPactId() );
		List<PaymentPlan> paymentList = pactInfoDao.getPaymentPlan(paymentPlan);
		for(PaymentPlan paymentplan : paymentList)
		{
			if(paymentplan.getPaymentStartDate().equals("2"))
			{
				paymentplan.setPaymentStartDate(pactInfo.getExecuteStartDate());
				String date = DateUtil.addByDay1(pactInfo.getExecuteStartDate(), Integer.parseInt(paymentplan.getPaymentDay() ));
				if(StringUtil.isNotEmpty(date)){
					paymentplan.setPaymentDay(date);
				}
				pactInfoDao.updatepayment(paymentplan);
			}
		}
		return true;
	}

	@Override
	public List<PaymentPlan> getPaymentList(String pactId) {
		PaymentPlan paymentPlan = new PaymentPlan();
		paymentPlan.setPactId(pactId);
		return pactInfoDao.getPaymentPlan(paymentPlan);
	}


	@Override
	public Ipage getPactListPage(Ipage ipage) throws ServiceException {
		if(StringUtil.isNotEmpty(ipage.getSoftType())){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			ipage.setSoftType(df.format(new Date()));
		}
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<PactListInfo> pactList = pactInfoDao.getPactListPage(ipage);
		int allRecord = Integer.parseInt(pactInfoDao.getPactListCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(pactList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public Ipage getPactExecutePage(Ipage ipage) throws ServiceException {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<PactExecuteBean> pactExecuteList = pactInfoDao.getPactExecutePage(ipage);
		int allRecord = Integer.parseInt(pactInfoDao.getPactExecuteCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(pactExecuteList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public Ipage getPactStaPage(Ipage ipage) throws ServiceException {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<PactInfo> pactList = new ArrayList<PactInfo>();
		pactList = pactInfoDao.getPactStaPage(ipage);
		int allRecord = Integer.parseInt(pactInfoDao.getPactStaPageCount(ipage));
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(pactList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public Ipage getPactWarningPage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		try{
			
			List<PactWarningBean> pactWarningList = pactInfoDao.getPactWarningPage(ipage);
			ipage.setDataList(pactWarningList);
		}catch(Exception e){
			e.printStackTrace();
		}
		int allRecord = Integer.parseInt(pactInfoDao.getPactWarningCount(ipage));
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public Ipage getTichengPact(Ipage ipage,String requestId) throws Exception {
		SysUser paramBean = new SysUser();
		List<SysUser> userList = usersDao.getSysUser(paramBean);
		Map<String,String> userMap = new HashMap<String, String>();
		for(int i=0; i<userList.size(); i++){
			paramBean = userList.get(i);
			userMap.put(paramBean.getUserNo(), paramBean.getOpName());
		}
		String marketerName = "";
		String[] beforesaleList ;
		String beforesale = "";
		String aftersale = "";
		String lastDate = "";
		String date100Percent = "";
		double pactFee = 0;
		double ticheng = 0;
		
		BigDecimal bg =  new BigDecimal(ticheng);
		TichengPactBean tichengPact = new TichengPactBean();
		
		//收款达到80%,尚未达到100%的合同
		List<TichengPactBean> list1 = pactInfoDao.get80PercentPact(ipage);
		for(int i=0;i<list1.size();i++){
			
			tichengPact = list1.get(i);
//			pactFee = Double.parseDouble(tichengPact.getPactFee());
			// 实际市场收入-合同额减去客户返额。
			String realFee = MathExtend.subtract(tichengPact.getPactFee(), tichengPact.getCustomerMoney());
			pactFee = Double.parseDouble(realFee);
			lastDate = tichengPact.getLastDate();
			tichengPact.setLastDate(lastDate.substring(0, 10));
			date100Percent = tichengPact.getDate100Percent();
			date100Percent = StringUtil.killNull(date100Percent);
			tichengPact.setDate100Percent(date100Percent);
			//当月合同只计算销售
			if(requestId.equals("month")){
				
				//销售
				marketerName = userMap.get(tichengPact.getMarketer());
				if(StringUtil.isEmpty(marketerName)){
					marketerName = "销售";
				}
				tichengPact.setMarketer(marketerName);
				ticheng = pactFee * 0.02;
				bg = new BigDecimal(ticheng);
				tichengPact.setMarketerTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				
			}else{//当季合同计算售前/售后
				//售前
				beforesaleList = tichengPact.getBeforesale1().split(",");
				tichengPact.setBeforesale1("");
				tichengPact.setBeforesaleTicheng1("");
				tichengPact.setBeforesale2("");
				tichengPact.setBeforesaleTicheng2("");
				if(beforesaleList.length == 0 || beforesaleList.length == 1){
					if(beforesaleList.length == 0){
						beforesale = "售前";
					}
					if(beforesaleList.length == 1){
						beforesale = userMap.get(beforesaleList[0]);
						if(StringUtil.isEmpty(beforesale)){
							beforesale = "售前";
						}
					}
					tichengPact.setBeforesale1(beforesale);
					ticheng = pactFee * 0.01;
					bg = new BigDecimal(ticheng);
					tichengPact.setBeforesaleTicheng1(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}else if(beforesaleList.length == 2){
					beforesale = userMap.get(beforesaleList[0]);
					if(StringUtil.isEmpty(beforesale)){
						beforesale = "售前";
					}
					tichengPact.setBeforesale1(beforesale);
					ticheng = (pactFee * 0.01)/2;
					bg = new BigDecimal(ticheng);
					tichengPact.setBeforesaleTicheng1(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
					beforesale = userMap.get(beforesaleList[1]);
					if(StringUtil.isEmpty(beforesale)){
						beforesale = "售前";
					}
					tichengPact.setBeforesale2(beforesale);
					tichengPact.setBeforesaleTicheng2(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}
				
				//售后
				aftersale = tichengPact.getAftersale();
				if(StringUtil.isEmpty(aftersale)){
					aftersale = "无售后";
				}
				tichengPact.setAftersale(aftersale);
				ticheng = pactFee * 0.01;
				bg = new BigDecimal(ticheng);
				tichengPact.setAftersaleTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
			}

		}
		
		//查询在某段日期内付款比例达到100%的合同（一种是既达到100%且该段日期达到80%，一种是达到100%但其他时间达到80%）
		List<TichengPactBean> list2 = pactInfoDao.get100PercentPact(ipage);
		for(int i=0 ;i<list2.size();i++){
			
			tichengPact = list2.get(i);
			pactFee = Double.parseDouble(tichengPact.getPactFee());

			beforesaleList = tichengPact.getBeforesale1().split(",");
			tichengPact.setBeforesale1("");
			tichengPact.setBeforesaleTicheng1("");
			tichengPact.setBeforesale2("");
			tichengPact.setBeforesaleTicheng2("");
			lastDate = tichengPact.getLastDate();
			tichengPact.setLastDate(lastDate.substring(0, 10));
			
			int j = 0;
			/*可能有的合同存在100%时间，不存在80%的时间。原因：date80Percent和date100Percent都是新加字段，在加字段之前，
			可能有的合同达到了80%但未到100%，加完字段后，这类合同再有款项确认时，也不会再添加80%的时间，因为这次收款不是真正的第一次到达80%的时间，
			达到100%是会记录100%的时间*/			
			if(StringUtil.isEmpty(tichengPact.getDate80Percent())){
				j = -1;
			}else{
				j = com.mftcc.common.util.DateUtil.compareTo( ipage.getParm0(),tichengPact.getDate80Percent(), "yyyy-MM");
			}
			if(requestId.equals("month")){
				
				marketerName = userMap.get(tichengPact.getMarketer());
				if(StringUtil.isEmpty(marketerName)){
					marketerName = "销售";
				}
				tichengPact.setMarketer(marketerName);
				
			}
			if( j <0){//当月达到100%但其他月达到80%的合同  或者老合同（不存在达到80%的时间）,不需要计算售前
			
				if(requestId.equals("month")){
					//销售
					ticheng = pactFee * 0.01;
					bg = new BigDecimal(ticheng);
					tichengPact.setMarketerTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
					
				}else{
					//售后
					
					aftersale = tichengPact.getAftersale();
					if(StringUtil.isEmpty(aftersale)){
						aftersale = "无售后";
					}
					tichengPact.setAftersale(aftersale);
					ticheng = pactFee * 0.01;
					bg = new BigDecimal(ticheng);
					tichengPact.setAftersaleTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}

			}else{//当月达到100%且本月达到80%的合同，需要计算售前
			
				if(requestId.equals("month")){
					//销售
					ticheng = pactFee * 0.03;
					bg = new BigDecimal(ticheng);
					tichengPact.setMarketerTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}else{
					
					//售前
					/*beforesaleList = tichengPact.getBeforesale1().split(",");
					tichengPact.setBeforesale1("");
					tichengPact.setBeforesaleTicheng1("");
					tichengPact.setBeforesale2("");
					tichengPact.setBeforesaleTicheng2("");
					*/
					if(beforesaleList.length == 0 || beforesaleList.length == 1){
						if(beforesaleList.length == 0){
							beforesale = "售前";
						}
						if(beforesaleList.length == 1){
							beforesale = userMap.get(beforesaleList[0]);
							if(StringUtil.isEmpty(beforesale)){
								beforesale = "售前";
							}
						}
						tichengPact.setBeforesale1(beforesale);
						ticheng = pactFee * 0.01;
						bg = new BigDecimal(ticheng);
						tichengPact.setBeforesaleTicheng1(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
					}else if(beforesaleList.length == 2){
						beforesale = userMap.get(beforesaleList[0]);
						if(StringUtil.isEmpty(beforesale)){
							beforesale = "售前";
						}
						tichengPact.setBeforesale1(beforesale);
						ticheng = (pactFee * 0.01)/2;
						bg = new BigDecimal(ticheng);
						tichengPact.setBeforesaleTicheng1(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
						beforesale = userMap.get(beforesaleList[1]);
						if(StringUtil.isEmpty(beforesale)){
							beforesale = "售前";
						}
						tichengPact.setBeforesale2(beforesale);
						tichengPact.setBeforesaleTicheng2(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
					}
					
					//售后
					aftersale = tichengPact.getAftersale();
					if(StringUtil.isEmpty(aftersale)){
						aftersale = "无售后";
					}
					ticheng = pactFee * 0.02;
					bg = new BigDecimal(ticheng);
					tichengPact.setAftersaleTicheng(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
		}
		list1.addAll(list2);
		ipage.setDataList(list1);
		return ipage;
	}

	@Override
	public void updatePactAndCus(PactInfo pactInfo, CustomerInfo cusInfo) throws Exception {
		if(pactInfo.getPactId() == null ){
			throw new Exception("合同号为空"); 
		}
		pactInfoDao.updatePactInfo(pactInfo);
		customerInfoDao.updateCustomerInfo(cusInfo);
		
	}

	@Override
	public String getPactExecuteUnconfirm(Ipage ipage) throws Exception {
		//只要sofyType字段不为空就可以
		ipage.setSoftType("0");
		return pactInfoDao.getPactExecuteCount(ipage);
	}

	@Override
	public String getPact(Ipage ipage) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		ipage.setSoftType(df.format(new Date()));
		return pactInfoDao.getPactListCount(ipage);
	}
}
