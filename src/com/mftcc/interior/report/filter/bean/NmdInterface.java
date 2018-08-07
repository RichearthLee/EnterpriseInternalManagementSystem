package com.mftcc.interior.report.filter.bean;

import java.util.List;
import java.util.Map;

/*import app.component.app.entity.MfBusAppKind;
import app.component.app.entity.MfBusApply;
import app.component.nmd.censor.entity.MfBusCensorBase;
import app.component.nmd.censor.entity.MfBusCensorBiz;
import app.component.nmd.censor.entity.MfBusCensorData;
import app.component.nmd.entity.NmdArea;
import app.component.nmd.entity.NmdComInf;
import app.component.nmd.entity.ParmDic;
import app.component.nmd.entity.RateNorm;
import app.component.nmd.entity.SysLegalHolidayRecord;
import app.component.nmd.entity.WorkCalendar;
import app.util.toolkit.Ipage;*/

public interface NmdInterface {
	
	/**
	 * 获取公司信息
	 */
	/*public NmdComInf getById(NmdComInf nmdComInf)throws Exception;

	public RateNorm getRateNormForLoan(int termMon, String sts,String rateType, String sysDate);

	public List  getParmDicList()throws Exception;*/
	/**
	 * 根据key_name查询所有字典项
	 * @param parmDic
	 * @return
	 * @throws Exception
	 */
	//public List findAllParmDicByKeyName(ParmDic parmDic) throws Exception;

	/**
	 * 根据区域编号查找区域信息
	 * @param nmdArea
	 * @return
	 * @throws Exception
	 */
	//public NmdArea getNmdAreaByAreaNo(NmdArea nmdArea)throws Exception;
	/**
	 * 查询当天要提醒的信息
	 * @param nmdArea
	 * @return
	 * @throws Exception
	 */
	//public List<WorkCalendar> FullCalendarDaylist() throws Exception;
	/**
	 * 查询parm_dic_all数据字典想视图
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<ParmDic> findByParmDicAllByKename(String key) throws Exception;
	/**
	 * 
	 * 方法描述： 获得数据字典分页列表
	 * @param ipg
	 * @param parmDic
	 * @return
	 * @throws Exception
	 * Ipage
	 * @author 沈浩兵
	 * @date 2016-9-21 下午6:19:07
	 */
	//public Ipage findByPage(Ipage ipg, ParmDic parmDic) throws Exception;
	/**
	 * 
	 * 方法描述： 根据数据字典名和字典项编号获得字典项信息
	 * @param parmDic
	 * @return
	 * @throws Exception
	 * ParmDic
	 * @author 沈浩兵
	 * @date 2016-9-22 下午4:22:35
	 */
	//public ParmDic getParmDicById(ParmDic parmDic) throws Exception;
	/**
	 * 
	 * 方法描述： 添加数据字典项
	 * @param parmDic
	 * @return
	 * @throws Exception
	 * ParmDic
	 * @author 沈浩兵
	 * @date 2017-3-20 下午6:05:32
	 */
	//public void insertParmDic(ParmDic parmDic) throws Exception;
	/**
	 * 
	 * 方法描述： 更新数据字典项
	 * @param parmDic
	 * @throws Exception
	 * void
	 * @author 沈浩兵
	 * @date 2017-3-21 上午10:11:28
	 */
	//public void updateParmDic(ParmDic parmDic) throws Exception;
	/**
	 * 方法描述： 根据产品与节点获取对应审查表文件列表数据
	 * @param mfBusCensorBiz
	 * @return
	 * @throws Exception
	 * List<MfBusCensorBase>
	 * @author Javelin
	 * @date 2017-7-15 下午3:22:22
	 */
	//public List<MfBusCensorBase> getBusCensorBaseList(MfBusCensorBiz mfBusCensorBiz) throws Exception;
	
	/**
	 * 方法描述： 批量插入审查数据
	 * @param list
	 * @throws Exception
	 * void
	 * @author Javelin
	 * @date 2017-7-15 下午3:22:44
	 */
	//public void batchInsertCensorData(List<MfBusCensorData> list) throws Exception;

	/**
	 * 方法描述： 获取业务审查表数据
	 * @param mfBusApply
	 * @param mfBusCensorBiz
	 * @return
	 * @throws Exception
	 * List<MfBusCensorData>
	 * @author YuShuai
	 * @date 2017-8-10 下午3:59:44
	 */
	//public List<MfBusCensorData> getBusCensorDataList(MfBusApply mfBusApply)throws Exception;
	
	 /**
     * 
     * 方法描述：通过申请 期限 获取基准利率类型 和 基准利率值，基准利率日期
     * @param mfBusApply
     * @param mfBusAppKind
     * @return 
     * baseMap.put("baseRate",baseRate );//基准利率</br>
     * baseMap.put("baseRateDate",baseRateDate);//基准利率开始日期 </br>
     * baseMap.put("baseRateType",baseRateType);//基准利率类型 1-贷款基准利率 2-公积金贷款率 3-贴现基准利率</br>
     * Map<String,String>
     * @author WD
     * @date 2017-8-29 上午10:35:15
     */
	//public Map<String, String> getMfSysRateTypeMap(MfBusApply mfBusApply,
			//MfBusAppKind mfBusAppKind)throws Exception ;

	//public int getCount(String keyName);
    /**
     * 
     * 方法描述：根据条件获取法定假日结束后的第一个工作日
     * @param mapParm
     * @return
     * @throws Exception 
     * SysLegalHolidayRecord
     * @author WD
     * @date 2017-10-30 下午8:27:13
     */
	//public SysLegalHolidayRecord getFestivalSetBeanByWorkDay(Map<String, String> mapParm)throws Exception;
}