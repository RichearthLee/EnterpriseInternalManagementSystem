
package com.mftcc.interior.pact.service;

import java.util.List;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PayProgressInfo;




public interface IPayProgressService {

	/**
	 * 方法描述：根据款项进度信息 
	 * @author Cuizk
	 * @date 2015-11-17 上午10:49:07
	 */
	public List<PayProgressInfo> getPayProgress(PayProgressInfo payprogress);
	
	/**
	 * 方法描述：(财务确认后)更新款项进度信息 ,同时更新合同信息表中的已收金额、已收款笔数和支付进度和合同状态， 还会更新该合同的收款计划的状态。
	 * @author Cuizk
	 * @date 2015-11-17 上午10:49:07
	 */
	public boolean updatePayProgress(PayProgressInfo payprogress);
	
	/**
	 * 方法描述： 更新款项进度表的信息，目前仅市场人员更新信息后调用此方法。
	 * @param payprogress
	 * @throws Exception
	 * void
	 * @author Cuizk
	 * @date 2016-1-28 下午3:22:00
	 */
	public void updatePayProgress1(PayProgressInfo payprogress) throws Exception;
	
	/**
	 * 方法描述： 增加款项进度信息 
	 * @param PayProgressInfo
	 * PayProgressInfo
	 * @author Cuizk
	 * @date 2015-11-17 上午10:51:40
	 */
	public PayProgressInfo insertPayProgress(PayProgressInfo payprogress);
	
	
	
	/**
	 * 方法描述： 获得一页的款项信息
	 * @param ipage
	 * @return
	 * @throws Exception
	 * Ipage
	 * @author Cuizk
	 * @date 2016-1-14 上午10:43:43
	 */
	public Ipage getPayProgressPage(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 获得未确认的款项总数
	 * @param ipage
	 * @return
	 * @throws Exception
	 * String
	 * @author Cuizk
	 * @date 2016-2-22 下午2:03:47
	 */
	public String getPayProgressUnconfirm(Ipage ipage) throws Exception;
	
}
