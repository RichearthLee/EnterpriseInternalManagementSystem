package com.mftcc.interior.pact.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactExecuteBean;
import com.mftcc.interior.pact.bean.PactFileInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PactListInfo;
import com.mftcc.interior.pact.bean.PactWarningBean;
import com.mftcc.interior.pact.bean.PaymentPlan;
import com.mftcc.interior.pact.bean.listBean.TichengPactBean;
import com.mftcc.interior.pact.dao.IPactInfoDao;


@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class PactInfoDaoImpl extends BaseDao implements IPactInfoDao {

	@Override
	public List<PactInfo> selectPactInfo(PactInfo paramPactInfo) {
		List<PactInfo> pactInfos = (List<PactInfo>) getSqlMapClientTemplate().queryForList("selectPactInfo", paramPactInfo);
		return pactInfos;
	}

	@Override
	public int updatePactInfo(PactInfo pactinfo) {
		return (int)getSqlMapClientTemplate().update("updatePactInfo", pactinfo);

	}

	@Override
	public void insertPactInfo(PactInfo pactinfo) {
		getSqlMapClientTemplate().insert("insertPactInfo", pactinfo);
	}

	@Override
	public List<PactListInfo> selectPactListInfo() {
		//getSqlMapClientTemplate().queryForList("selectPactFullInfo");
		return getSqlMapClientTemplate().queryForList("selectPactListInfo");
	}


	@Override
	public List<PactFileInfo> selectPactFile(String pactId) {
		return getSqlMapClientTemplate().queryForList("selectPactFileInfo", pactId);
	}

	@Override
	public void insertPactFile(PactFileInfo pactFileInfo) {
		getSqlMapClientTemplate().insert("insertPactFile", pactFileInfo);
	}

	@Override
	public void insertPayment(PaymentPlan paymentPlan) {
		System.out.println("张凡");
		getSqlMapClientTemplate().insert("insertPactPaymentPlan", paymentPlan);
	}

	@Override
	public List<PaymentPlan> getPaymentPlan(PaymentPlan paymentPlan) {
		return (List<PaymentPlan>)getSqlMapClientTemplate().queryForList("selectPactPaymentPlan", paymentPlan);
	}

	@Override
	public void updatepayment(PaymentPlan paymentPlan) {
		getSqlMapClientTemplate().update("updatePactPaymentPlan", paymentPlan);
	}


	@Override
	public List<PactListInfo> getPactListPage(Ipage ipage) {
		
		return getSqlMapClientTemplate().queryForList("selectPactListPage", ipage);
	}

	@Override
	public String getPactListCount(Ipage ipage) {
		
		return (String)getSqlMapClientTemplate().queryForObject("selectPactListCount", ipage);
	}

	@Override
	public List<PactExecuteBean> getPactExecutePage(Ipage ipage) {
		
		return getSqlMapClientTemplate().queryForList("selectPactExecutePage", ipage);
	}

	@Override
	public String getPactExecuteCount(Ipage ipage) {
		return (String)getSqlMapClientTemplate().queryForObject("selectPactExecuteCount", ipage);
	}

	@Override
	public List<PactInfo> getPactStaPage(Ipage ipage) {
		
		return getSqlMapClientTemplate().queryForList("selectPactStaPage", ipage);
	}

	@Override
	public String getPactStaPageCount(Ipage ipage) {
		return (String)getSqlMapClientTemplate().queryForObject("selectPactStaPageCount", ipage);
	}

	@Override
	public List<PactInfo> getPactStaAll(Ipage ipage) throws Exception {
		
		return getSqlMapClientTemplate().queryForList("selectPactStaAll", ipage);
	}

	@Override
	public List<PactWarningBean> getPactWarningPage(Ipage ipage) throws Exception {
		
		return getSqlMapClientTemplate().queryForList("selectPactWarningPage", ipage);
	}

	@Override
	public String getPactWarningCount(Ipage ipage) throws Exception {

		return (String)getSqlMapClientTemplate().queryForObject("selectPactWarningCount", ipage);
	}

	@Override
	public List<PactWarningBean> getPactWarningAll(Ipage ipage) throws Exception {
		
		return getSqlMapClientTemplate().queryForList("selectPactWarningAll", ipage);
	}

	@Override
	public List<TichengPactBean> get80PercentPact(Ipage ipage) throws Exception {
		return getSqlMapClientTemplate().queryForList("get80PercentPact", ipage);
	}

	@Override
	public List<TichengPactBean> get100PercentPact(Ipage ipage) throws Exception {
		return getSqlMapClientTemplate().queryForList("get100PercentPact", ipage);
	}

}
