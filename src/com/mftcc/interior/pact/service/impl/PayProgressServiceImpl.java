
package com.mftcc.interior.pact.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.util.MathExtend;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PayProgressInfo;
import com.mftcc.interior.pact.bean.PaymentPlan;
import com.mftcc.interior.pact.dao.IPactInfoDao;
import com.mftcc.interior.pact.dao.IPayProgressDao;
import com.mftcc.interior.pact.service.IPayProgressService;

@Service               
public class PayProgressServiceImpl implements IPayProgressService {
   
	@Autowired
	private IPayProgressDao payProgressDao;
	@Autowired
	private IPactInfoDao pactInfoDao;
	
	

	@Override
	public String getPayProgressUnconfirm(Ipage ipage) throws Exception {
		//查询尚未确认的款项
		ipage.setSoftType("0");
		return payProgressDao.getPayProgressCount(ipage);
	}

	@Override
	public List<PayProgressInfo> getPayProgress(PayProgressInfo payprogress) {
		
		List<PayProgressInfo> payProgressInfos = payProgressDao.selectPayProgress(payprogress);
		return payProgressInfos;
	}

	@Override
	public boolean updatePayProgress(PayProgressInfo payprogress) {
		if(StringUtil.isEmpty(payprogress.getPactId()) || StringUtil.isEmpty(payprogress.getPayyet()) || StringUtil.isEmpty(payprogress.getProceedMoney()) )
		{
			return false;
		}
		//查询该笔款项信息，判断是否已经确认
		PayProgressInfo payprogress1 = new PayProgressInfo();
		payprogress1.setPactId(payprogress.getPactId());
		payprogress1.setPayyet(payprogress.getPayyet());
		payprogress1 = payProgressDao.selectPayProgress(payprogress1).get(0);
		//如果已经确认过，直接返回
		if(payprogress1.getFinancialResult().equals("1")){
			return true;
		}
		//根据合同号获取合同信息，并更新已收款金额和比例等信息
		PactInfo pactInfo = new PactInfo();
		pactInfo.setPactId(payprogress.getPactId());
		pactInfo = pactInfoDao.selectPactInfo(pactInfo).get(0);
		
		// 客户返款记录
		String cusMoney = String.valueOf(Double.parseDouble(pactInfo.getCustomerMoney()) + Double.parseDouble(payprogress1.getCustomerMoney()));
		pactInfo.setCustomerMoney(cusMoney);
		
		int payCountYet = Integer.parseInt(pactInfo.getPaycountYet());
		pactInfo.setPaycountYet(String.valueOf(payCountYet +1));
		//如果是第一笔款，且该合同状态仍为新合同（1）时，才会设置合同状态为可实施。  因为可能会存在首批款未付直接实施的合同，这类合同的状态已经是可实施或实施完成。
		if((payCountYet +1 ==1) && (pactInfo.getPactState().equals("1"))){
			pactInfo.setPactState("2");
		}
		
		
		double pactFee = Double.parseDouble(pactInfo.getPactFee());
		double payFeeYet = Double.parseDouble(pactInfo.getPayfeeYet()) + Double.parseDouble(payprogress.getProceedMoney());
		double oldPayPercent = Double.parseDouble(pactInfo.getPaypercent());
		double payPercent = 0;
		if(pactFee < 0.0000001 ){
			payPercent = 100;
		}else{
			payPercent = (double)(payFeeYet/pactFee)*100;
			BigDecimal bg = new BigDecimal(payPercent);
			payPercent = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = df1.format(new Date());
		//如果收款比例第一次达到80%的时间不存在，且原比例低于80%，则将这次的时间设置上
		/* 原因是之前没有date80Percent字段，老合同数据中，有的合同收款达到了80%但是没有记录，
		 * 现在就不是真正的第一次达到80%的时间，而且有可能这种合同线下已经计算过，所以这里不再设置*/
		if(StringUtil.isEmpty(pactInfo.getDate80Percent()) && (oldPayPercent < 80)){
			if(payPercent >= 80){
				pactInfo.setDate80Percent(dateNow);
			}
		}
		
		//达到100%时记录时间。
		if(payPercent >= 100 && StringUtil.isEmpty(pactInfo.getDate100Percent())){
			pactInfo.setDate100Percent(dateNow);
		}
		String payPercent1 = String.valueOf(payPercent);
		pactInfo.setPaypercent(payPercent1);
		BigDecimal bg1 = new BigDecimal(payFeeYet);
		pactInfo.setPayfeeYet(bg1.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
		
		pactInfoDao.updatePactInfo(pactInfo);
		
		//更新收款计划的状态
		PaymentPlan paymentPlan = new PaymentPlan();
		paymentPlan.setPactId(payprogress.getPactId());
		List<PaymentPlan> paymentLit = pactInfoDao.getPaymentPlan(paymentPlan);
		double currentPercent = 0;
		for(PaymentPlan paymentplan : paymentLit){
			
			double paymentPercent = Double.parseDouble(paymentplan.getPaymentPercent().replace("%", ""));
			currentPercent = paymentPercent + currentPercent;
			if(payPercent >= currentPercent)
			{
				paymentplan.setPaymentState("2");
			}else if((payPercent < currentPercent) && (payPercent > (currentPercent - paymentPercent))){
				paymentplan.setPaymentState("3");
			}
			pactInfoDao.updatepayment(paymentplan);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		payprogress.setFinancialResult("1");
		payprogress.setFinancialDate(df.format(new Date()));
		payprogress.setPayPercentNow(payPercent1);
		payProgressDao.updatePayProgress(payprogress);
		
		return true;
	}

	@Override
	public PayProgressInfo insertPayProgress(PayProgressInfo payprogress) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		payprogress.setMarketDate(df.format(new Date()));
		// 计算实际金额
		String subtract = MathExtend.subtract(payprogress.getProceedMoney(), payprogress.getCustomerMoney());
		payprogress.setMarketMoney(subtract);
		
		//表示财务未确认
		payprogress.setFinancialResult("2");
		//表示未开发票  
		payprogress.setInvoice("2");
		//表示非全额发票,暂不用
		payprogress.setInvoiceAll("2");
		PactInfo pactInfo = new PactInfo();
		pactInfo.setPactId(payprogress.getPactId());
		pactInfo = pactInfoDao.selectPactInfo(pactInfo).get(0);
		payprogress.setCustomerName(pactInfo.getCustomerName());
		payprogress.setProductName(pactInfo.getProductName());
		payProgressDao.insertPayProgress(payprogress);
		return payprogress;
	}

	@Override
	public Ipage getPayProgressPage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<PayProgressInfo> payProgressList = new ArrayList<PayProgressInfo>();
		payProgressList = payProgressDao.getPayProgressPage(ipage);
		int allRecord = Integer.parseInt(payProgressDao.getPayProgressCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(payProgressList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public void updatePayProgress1(PayProgressInfo payprogress) throws Exception {
		if(StringUtil.isEmpty(payprogress.getPactId()) || StringUtil.isEmpty(payprogress.getPayyet())){
			return;
		}
		payProgressDao.updatePayProgress(payprogress);
	}

	

}
