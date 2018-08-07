
package com.mftcc.interior.pact.dao;

import java.util.List;
import java.util.Map;

import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PactExecuteBean;
import com.mftcc.interior.pact.bean.PactFileInfo;
import com.mftcc.interior.pact.bean.PactInfo;
import com.mftcc.interior.pact.bean.PactListInfo;
import com.mftcc.interior.pact.bean.PactWarningBean;
import com.mftcc.interior.pact.bean.PaymentPlan;
import com.mftcc.interior.pact.bean.listBean.TichengPactBean;



public interface IPactInfoDao {
	
	
    /**
     * 方法描述： 查询合同信息，PactInfo的字段不为空，增加查询条件
     * @return
     * List<PactInfo>
     * @author Cuizk
     * @date 2015-11-17 上午10:02:45
     */
    public List<PactInfo> selectPactInfo(PactInfo pactInfo);
    
    /**
     * 方法描述： 根据合同号更新合同信息
     * @author Cuizk
     * @date 2015-11-17 上午10:03:20
     */
    public int updatePactInfo(PactInfo pactinfo);
    /**
     * 方法描述：增加合同信息
     * @author Cuizk
     * @date 2015-11-17 上午10:03:20
     */
    public void insertPactInfo(PactInfo pactInfo);
    
    /**
     * 方法描述： ：联合 查询所有合同和所有的客户信息. 
     * @return
     * List<PactFullInfo>
     * @author Cuizk
     * @date 2015-11-18 上午10:07:00
     */
    public List<PactListInfo> selectPactListInfo();
    
    
    /**
     * 方法描述： 根据合同号查询合同附件的存储路径
     * @param pactId
     * @return
     * List<PactFileInfo>
     * @author Cuizk
     * @date 2015-11-19 上午9:14:31
     */
    public List<PactFileInfo> selectPactFile(String pactId);
    
    /**
     * 方法描述： 增加合同附件存储路径
     * @param pactFileInfo
     * void
     * @author Cuizk
     * @date 2015-11-19 上午9:19:02
     */
    public void insertPactFile(PactFileInfo pactFileInfo);
    
    /**
     * 方法描述： 增加收款计划
     * @param paymentPlan
     * void
     * @author Cuizk
     * @date 2015-11-26 下午1:52:10
     */
    public void insertPayment(PaymentPlan paymentPlan);
    
    /**查找符合条件的收款计划
     * 方法描述： 
     * @param paymentPlan
     * @return
     * List<PaymentPlan>
     * @author Cuizk
     * @date 2015-11-26 下午5:27:03
     */
    public List<PaymentPlan> getPaymentPlan(PaymentPlan paymentPlan);
    
    /**
     * 方法描述： 更新收款计划。目前只用到更新状态
     * @param paymentPlan
     * void
     * @author Cuizk
     * @date 2015-12-7 上午9:51:32
     */
    public void updatepayment(PaymentPlan paymentPlan);
    
    
    public  List<PactListInfo> getPactListPage(Ipage ipage);
    
    /**
     * 方法描述： 获得合同的总数
     * @param ipage
     * @return
     * String
     * @author Cuizk
     * @date 2016-1-6 下午2:42:44
     */
    public String getPactListCount(Ipage ipage);
    
    /**
     * 方法描述： 获得一页可实施和已实施的合同
     * @param ipage
     * @return
     * List<PactExecuteBean>
     * @author Cuizk
     * @date 2016-1-21 下午1:39:29
     */
    public List<PactExecuteBean> getPactExecutePage(Ipage ipage);
    
    public String getPactExecuteCount(Ipage ipage);
    
    /**
     * 方法描述： 获得一页合同信息
     * @param ipage
     * @return
     * List<PactInfo>
     * @author Cuizk
     * @date 2016-1-21 下午1:40:13
     */
    public List<PactInfo> getPactStaPage(Ipage ipage);
    
    public String  getPactStaPageCount(Ipage ipage);
    
    /**
     * 方法描述： 获得所有符合条件的合同信息，用以导出。
     * @param ipage
     * @return
     * @throws Exception
     * List<PactInfo>
     * @author Cuizk
     * @date 2016-1-21 下午1:43:26
     */
    public List<PactInfo> getPactStaAll(Ipage ipage) throws Exception;
    
    /**
     * 方法描述： 获得一页的合同收款预警的信息
     * @return
     * @throws Exception
     * List<PactListInfo>
     * @author Cuizk
     * @date 2016-1-21 下午1:44:10
     */
    public List<PactWarningBean> getPactWarningPage(Ipage ipage) throws Exception;
    
    public String getPactWarningCount(Ipage ipage) throws Exception;
    
    /**
     * 方法描述： 获得所有合同收款预警的信息，目前仅导出时用到
     * @param ipage
     * @return
     * @throws Exception
     * List<PactWarningBean>
     * @author Cuizk
     * @date 2016-1-21 下午4:24:46
     */
    public List<PactWarningBean> getPactWarningAll(Ipage ipage) throws Exception;
    
    /**
     * 方法描述： 查询在某段日期内付款比例达到80%尚未到100%的合同，及该时间段内的最后一次付款时间和金额
     * @param ipage ipage.parm0是开始月，ipage.parm1是结束月的下一个月，比如查询2016-03到2016-05的合同，则parm0是2016-03，parm1是2016-06
     * @return
     * @throws Exception
     * List<TichengPactBean>
     * @author Cuizk
     * @date 2016-2-15 下午5:28:01
     */
    public List<TichengPactBean> get80PercentPact(Ipage ipage) throws Exception;
    
    /**
     * 方法描述：查询在某段日期内付款比例达到100%的合同（一种是既达到100%且该段日期达到80%，一种是达到100%但其他时间达到80%），及该时间段内的最后一次付款时间和金额
     * @param ipage ipage.parm0是开始月，ipage.parm1是结束月的下一个月，比如查询2016-03到2016-05的合同，则parm0是2016-03，parm1是2016-06
     * @return
     * @throws Exception
     * List<TichengPactBean>
     * @author Cuizk
     * @date 2016-2-15 下午5:29:13
     */
    public List<TichengPactBean> get100PercentPact(Ipage ipage) throws Exception;
    
 
}
