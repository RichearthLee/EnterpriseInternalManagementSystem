
package com.mftcc.interior.pact.service;

import java.util.List;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;




public interface IPactInvoiceService {
	
	/**
	 * 方法描述：新增一个发票申请记录 
	 * @param pactInvoice
	 * @throws Exception
	 * void
	 * @author Cuizk
	 * @date 2016-2-1 上午10:02:28
	 */
	public void addInvoice(PactInvoice pactInvoice) throws Exception;
	
	/**
	 * 方法描述：财务确认后的更新 
	 * @param pactInvoice
	 * @throws Exception
	 * void
	 * @author Cuizk
	 * @date 2016-2-1 上午10:02:49
	 */
	public void updateInvoiceConfirm(PactInvoice pactInvoice) throws Exception;
	
	public Ipage getInvoicePage(Ipage ipage) throws Exception;
	
	public List<PactInvoice> getPactInvoiceList(PactInvoice pactInvoice) throws Exception;
	
	/**
	 * 方法描述：获取尚未确认的发票总数 
	 * @param ipage
	 * @return
	 * @throws Exception
	 * String
	 * @author Cuizk
	 * @date 2016-2-22 下午2:13:44
	 */
	public String getInvoiceUnconfirm(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 更新发票信息
	 * @param pactInvoice
	 * @throws Exception
	 * void
	 * @author Cuizk
	 * @date 2016-3-24 下午3:58:46
	 */
	public void updateInvoice(PactInvoice pactInvoice) throws Exception;
	
}
