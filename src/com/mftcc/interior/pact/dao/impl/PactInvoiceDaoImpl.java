
package com.mftcc.interior.pact.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;
import com.mftcc.interior.pact.dao.IPactInvoiceDao;


@Repository
public class PactInvoiceDaoImpl extends BaseDao implements IPactInvoiceDao {

	@Override
	public void addPactInvoice(PactInvoice pactInvoice) throws Exception {
		getSqlMapClientTemplate().insert("insertPactInvoice", pactInvoice);
	}

	@Override
	public void updatePactInvoice(PactInvoice pactInvoice) throws Exception {
		getSqlMapClientTemplate().update("updatePactInvoice", pactInvoice);
	}

	@Override
	public List<PactInvoice> getPactInvoicePage(Ipage ipage) throws Exception {
		return getSqlMapClientTemplate().queryForList("getInvoicePage", ipage);
	}

	@Override
	public String getPactInvoiceCount(Ipage ipage) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("getInvoiceCount", ipage);
	}

	@Override
	public List<PactInvoice> getPactInvoiceList(PactInvoice pactInvoice) throws Exception {
		return getSqlMapClientTemplate().queryForList("selectPactInvoice", pactInvoice);
	}

}
