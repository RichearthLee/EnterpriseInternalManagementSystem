
package com.mftcc.interior.pact.dao;

import java.util.List;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;




public interface IPactInvoiceDao {
	public void addPactInvoice(PactInvoice pactInvoice) throws Exception;
	
	public void updatePactInvoice(PactInvoice pactInvoice) throws Exception;
	
	public List<PactInvoice> getPactInvoicePage(Ipage ipage) throws Exception;
	
	public String getPactInvoiceCount(Ipage ipage) throws Exception;
	
	public List<PactInvoice> getPactInvoiceList(PactInvoice pactInvoice) throws Exception;
}
