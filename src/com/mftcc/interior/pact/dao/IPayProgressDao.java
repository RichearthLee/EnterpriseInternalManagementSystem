
package com.mftcc.interior.pact.dao;

import java.util.List;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PayProgressInfo;




public interface IPayProgressDao {

	/**
	 * 方法描述：查询款项进度信息 ,PayProgressInfo字段非空，增加查询条件，为空则忽略
	 * @author Cuizk
	 * @date 2015-11-17 上午10:29:45
	 */
	public List<PayProgressInfo> selectPayProgress(PayProgressInfo payprogress);
	/**
	 * 方法描述：增加款项进度信息 
	 * @author Cuizk
	 * @date 2015-11-17 上午10:29:45
	 */
	public void insertPayProgress(PayProgressInfo payprogress);
	/**
	 * 方法描述：根据合同号和第几笔支付号 更新款项进度信息 
	 * @author Cuizk
	 * @date 2015-11-17 上午10:29:45
	 */
	public int updatePayProgress(PayProgressInfo payprogress);
	
	/**
	 * 方法描述： 根据条件模糊查询款项信息
	 * @param param
	 * @return
	 * List<PayProgressInfo>
	 * @author Cuizk
	 * @date 2015-12-8 下午12:36:05
	 */
	public List<PayProgressInfo> getPayProgressList(String param);
	
	public List<PayProgressInfo> getPayProgressPage(Ipage ipage);
	
	public String getPayProgressCount(Ipage ipage); 
}
