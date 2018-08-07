
package com.mftcc.interior.pact.service;

import java.util.List;
import java.util.Map;

import com.mftcc.common.exception.ServiceException;
import com.mftcc.interior.pact.bean.CustomerInfo;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactFileInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PactListInfo;
import com.mftcc.interior.pact.bean.Pact_Customer;
import com.mftcc.interior.pact.bean.PaymentPlan;

public interface IPactInfoService {
 
	/**
	 * 方法描述：根据合同号查询合同信息， 
	 * @throws ServiceException
	 * @author Cuizk
	 * @date 2015-11-17 上午10:14:57
	 */
	public PactInfo getPactInfoById  (String id)throws ServiceException;
	
	/**
	 * 方法描述：联合查询所有客户和所有合同的信息，查询结果用来列表显示 
	 * @return
	 * @throws ServiceException
	 * PactInfo
	 * @author Cuizk
	 * @date 2015-11-18 上午10:12:44
	 */
	public List<PactListInfo> getPactListInfo  () throws ServiceException;
	
	
	/**
	 * 方法描述：更新合同信息， 
	 * @throws ServiceException
	 * @author Cuizk
	 * @date 2015-11-17 上午10:14:57
	 */
	public boolean updatePactInfo  (PactInfo pactInfo)throws ServiceException;
	
	public void updatePactAndCus(PactInfo pactInfo,CustomerInfo cusInfo) throws Exception;
	
	/**
	 * 方法描述：通过从前台获取的数据，增加合同信息，客户信息，收款计划信息。   
	 * @throws ServiceException
	 * @author Cuizk
	 * @date 2015-11-17 上午10:14:57
	 */
	public boolean insertPactInfo  (Pact_Customer pactAndCustomer)throws ServiceException;
	
	/**
	 * 方法描述：增加合同附件存储路径 
	 * @param pactFielInfo
	 * void
	 * @author Cuizk
	 * @date 2015-11-19 上午11:30:08
	 */
	public void insertPactFile(PactFileInfo pactFielInfo) throws ServiceException;
	/**
	 * 方法描述： 根据合同号查询合同附件路径
	 * @param pactId
	 * void
	 * @author Cuizk
	 * @date 2015-11-19 上午11:30:36
	 */
	public List<PactFileInfo> getPactFile(String pactId);
	
	/**根据实施人员结果记录实施时间，并修改收款计划表的收款起始时间；
	 * 方法描述： 
	 * @param pactInfo
	 * @return
	 * boolean
	 * @author Cuizk
	 * @date 2015-11-26 下午5:20:38
	 */
	public boolean updateExecuteConfirm(PactInfo pactInfo);
	
	/**
	 * 方法描述： 根据合同号获得某合同的收款计划
	 * @param pactId
	 * @return
	 * List<PaymentPlan>
	 * @author Cuizk
	 * @date 2015-12-7 上午10:02:21
	 */
	public List<PaymentPlan> getPaymentList(String pactId);
	
	
	/**
	 * 方法描述： 获得一页的合同信息
	 * @param ipage
	 * @return
	 * @throws ServiceException
	 * Ipage
	 * @author Cuizk
	 * @date 2016-1-6 下午2:40:34
	 */
	public Ipage getPactListPage(Ipage ipage) throws ServiceException;
	
	public String getPact(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 获得一页的实施合同信息
	 * @param ipage
	 * @return
	 * @throws ServiceException
	 * Ipage
	 * @author Cuizk
	 * @date 2016-1-6 下午3:51:35
	 */
	public Ipage getPactExecutePage(Ipage ipage) throws ServiceException;
	
	public String getPactExecuteUnconfirm(Ipage ipage) throws Exception;
	/**
	 * 方法描述： 获得合同统计中，一页的合同信息
	 * @param ipage
	 * @return
	 * @throws ServiceException
	 * Ipage
	 * @author Cuizk
	 * @date 2016-1-21 下午1:42:16
	 */
	public Ipage getPactStaPage(Ipage ipage) throws ServiceException;
	
	/**
	 * 方法描述： 获得合同收款预警中，一页的合同信息
	 * @param ipage
	 * @return
	 * @throws Exception
	 * Ipage
	 * @author Cuizk
	 * @date 2016-1-21 下午1:42:37
	 */
	public Ipage getPactWarningPage(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 获得可以计算提成的合同，并计算提成。
	 * @param ipage
	 * @param requestId	请求来源，month(某月)：只计算销售（即市场人员），quarter(某几个月):1.达到100%且在这段时间达到80%，计算售前和售后；2.达到100%但其他时间达到80%，只计算售后。
	 * @return
	 * @throws Exception
	 * List<TichengPactBean>
	 * @author Cuizk
	 * @date 2016-2-27 上午11:14:34
	 */
	public Ipage getTichengPact(Ipage ipage,String requestId) throws Exception;
	
	
}
