
package com.mftcc.interior.pact.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.dbBean.PactInvoice;
import com.mftcc.interior.pact.dao.IPactInfoDao;
import com.mftcc.interior.pact.dao.IPactInvoiceDao;
import com.mftcc.interior.pact.service.IPactInvoiceService;



@Service
public class PactInvoiceServiceImpl implements IPactInvoiceService {
	@Autowired
	private IPactInvoiceDao pactInvoiceDao;
	@Autowired
	private IPactInfoDao pactInfoDao;
	
	@Override
	public void addInvoice(PactInvoice pactInvoice) throws Exception {
		double pactFee = Double.parseDouble(pactInvoice.getPactFee());
		double invoiceMoney = Double.parseDouble(pactInvoice.getInvoiceMoney());
		double invoicePercent = (100*invoiceMoney)/pactFee;
		BigDecimal bg = new BigDecimal(invoicePercent);
		pactInvoice.setInvoicePercent(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
		pactInvoiceDao.addPactInvoice(pactInvoice);
	}

	@Override
	public void updateInvoiceConfirm(PactInvoice pactInvoice) throws Exception {
		PactInfo pactInfo = new PactInfo();
		pactInfo.setPactId(pactInvoice.getPactId());
		pactInfo = pactInfoDao.selectPactInfo(pactInfo).get(0);
		double invoiveMoneyYet = Double.parseDouble(pactInfo.getInvoiceMoneyYet()) + Double.parseDouble(pactInvoice.getInvoiceMoney());
		BigDecimal bg = new BigDecimal(invoiveMoneyYet);
		pactInfo.setInvoiceMoneyYet(bg.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
		pactInfoDao.updatePactInfo(pactInfo);
		pactInvoiceDao.updatePactInvoice(pactInvoice);
	}

	@Override
	public Ipage getInvoicePage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<PactInvoice> pactInvoiceList= pactInvoiceDao.getPactInvoicePage(ipage);
		int allRecord = Integer.parseInt(pactInvoiceDao.getPactInvoiceCount(ipage));
		
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(pactInvoiceList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public List<PactInvoice> getPactInvoiceList(PactInvoice pactInvoice) throws Exception {
		return pactInvoiceDao.getPactInvoiceList(pactInvoice);
	}

	@Override
	public String getInvoiceUnconfirm(Ipage ipage) throws Exception {
		ipage.setSoftType("0");
		return pactInvoiceDao.getPactInvoiceCount(ipage);
	}

	@Override
	public void updateInvoice(PactInvoice pactInvoice) throws Exception {
		double pactFee = Double.parseDouble(pactInvoice.getPactFee());
		double invoiceMoney = Double.parseDouble(pactInvoice.getInvoiceMoney());
		double invoicePercent = (100*invoiceMoney)/pactFee;
		BigDecimal bg = new BigDecimal(invoicePercent);
		pactInvoice.setInvoicePercent(bg.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
		pactInvoiceDao.updatePactInvoice(pactInvoice);
	}
	
	

}
