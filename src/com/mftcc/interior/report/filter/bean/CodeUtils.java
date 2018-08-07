package com.mftcc.interior.report.filter.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/*import app.base.SourceTemplate;
import app.component.nmd.entity.ParmDic;
import app.component.nmd.interfaceimpl.NmdInterfaceImpl;
import app.component.nmdinterface.NmdInterface;
import app.component.pms.bo.PmsBizRoleRelBo;
import app.component.pms.bo.PmsDataRangBo;
import app.component.pms.bo.PmsDataSubBo;
import app.component.pms.entity.PmsBizRoleRel;
import app.component.pms.entity.PmsDataRang;
import app.component.pms.entity.PmsDataSub;
import app.component.sec.bo.SecAuditConfigBo;
import app.component.sec.entity.SecAuditConfig;
import app.component.sys.bo.SysDescTempBo;
import app.component.sys.bo.SysMsgConfigBo;
import app.component.sys.bo.SysRoleButtonBo;
import app.component.sys.entity.SysDescTemp;
import app.component.sys.entity.SysMsgConfig;
import app.component.sys.entity.SysRoleButton;
import app.component.wkf.AppConstant;
import app.component.wkfBus.bean.WkfBusNode;
import app.tech.wkf.tools.ResolveProcessUtil;
import cn.mftcc.util.StringUtil;*/

import com.core.base.CoreConstant;
import com.core.domain.screen.OptionsList;
import com.core.util.oscache.ScreenBaseCache;
/*import com.opensymphony.oscache.base.NeedsRefreshException;*/

public class CodeUtils {

	public String key;
	private NmdInterface nmdInterface;
	/*private SysDescTempBo sysDescTempBo;
	private SysRoleButtonBo sysRoleButtonBo;
	private SysMsgConfigBo sysMsgConfigBo;*/

	public CodeUtils() {
	}

	public CodeUtils(String key) {
		this.key = key;
	}

	/*public void initCache() {
		BaseCache bc = BaseCache.getInstance();
		try {
			// nmdInterface =
			// SourceTemplate.getSpringContextInstance().getBean("nmdInterface",NmdInterface.class);
			List list = (List) nmdInterface.getParmDicList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 重新加载选项明细缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public boolean reLoadParmDicCache() throws Exception {
		BaseCache bc = BaseCache.getInstance();
		bc.flushByGroup(getGroups(CachecodeUtil.CACHE_DATADICT)[0]);
		return this.initParmDicCache();
	}*/

	/**
	 * @throws Exception
	 *             功能描述：初始化权限功能
	 * @param
	 */
	/*public void initPmsBizCache() throws Exception {
		PmsBizRoleRelBo pmsBizRoleRelBo = (PmsBizRoleRelBo) SourceTemplate.getSpringContextInstance().getBean(
				"pmsBizRoleRelBo", PmsBizRoleRelBo.class);
		List<PmsBizRoleRel> list = pmsBizRoleRelBo.getAll4Cache();
		BaseCache bc = BaseCache.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		for (PmsBizRoleRel pbrr : list){
			if(map.get(pbrr.getRoleNo())==null){
				map.put(pbrr.getRoleNo(), pbrr.getPmsBizNo());
			}else{
				map.put(pbrr.getRoleNo(), map.get(pbrr.getRoleNo())+","+pbrr.getPmsBizNo());
			}
		}
		for (Entry<String, String> entry : map.entrySet()) {
			bc.putBeanCacheAll(entry.getKey(),
					getGroups(CachecodeUtil.CACHE_PMSBIZ), entry.getValue());
		}
	}*/
	
	/**
	 * 重新加载功能权限缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public void reLoadPmsCache() throws Exception {
		BaseCache bc = BaseCache.getInstance();
		bc.flushByGroup(getGroups(CachecodeUtil.CACHE_PMSBIZ)[0]);
		this.initPmsBizCache();
	}*/
	
	/**
	 * 初始化数据权限配置
	 * @return
	 * @throws Exception
	 */
	/*public boolean initPmsDataRangCache() throws Exception {
		PmsDataRangBo pmsDataRangBo = (PmsDataRangBo) SourceTemplate.getSpringContextInstance().getBean(
				"pmsDataRangBo", PmsDataRangBo.class);
		PmsDataSubBo pmsDataSubBo = (PmsDataSubBo) SourceTemplate.getSpringContextInstance().getBean(
				"pmsDataSubBo", PmsDataSubBo.class);
		List<PmsDataRang> list = pmsDataRangBo.getAllList();
		BaseCache bc = BaseCache.getInstance();
		Map<String,String> dataSubMap=new HashMap<String,String>();
		PmsDataRang pmsDataRang =new PmsDataRang();
		for (int i = 0; i < list.size(); i++) {
			pmsDataRang=list.get(i);
			PmsDataSub pmsDataSub =new PmsDataSub();
			pmsDataSub.setFunNo(pmsDataRang.getFunNo());
			List<PmsDataSub> pmsDataSubList=pmsDataSubBo.findByPageForConf(pmsDataSub);
			if(pmsDataSubList!=null&&pmsDataSubList.size()>0){
				dataSubMap=new HashMap<String,String>();
				for (int j = 0; j < pmsDataSubList.size(); j++) {
					dataSubMap.put(pmsDataSubList.get(j).getPmsLv(), pmsDataSubList.get(j).getPmsField());
				}
			}
			bc.putBeanCacheAll(pmsDataRang.getFunMethod(),
					getGroups(CachecodeUtil.FUN_DATA_RANG), dataSubMap);
		}
		return true;
	}*/
	
	/**
	 * @throws Exception
	 *             功能描述：初始化字典项
	 * @param
	 * @author wangcong
	 * @date 2015年12月24日 上午10:03:45
	 * @return void
	 */
	/*public boolean initParmDicCache() throws Exception {
		boolean flag = false;
		nmdInterface = (NmdInterface) SourceTemplate.getSpringContextInstance().getBean(
				"nmdInterface", NmdInterfaceImpl.class);
		List<ParmDic> list = (List) nmdInterface.getParmDicList();
		List<ParmDic> list2 = list;
		List<ParmDic> cbList = null;
		Map<String, List<ParmDic>> map = new HashMap<String, List<ParmDic>>();
		for (ParmDic cb : list) {
			cbList = new ArrayList<ParmDic>();
			for (ParmDic cb2 : list2) {
				if (cb2.getKeyName().equals(cb.getKeyName())) {
					cbList.add(cb2);
				}
			}
			map.put(cb.getKeyName(), cbList);
		}
		BaseCache bc = BaseCache.getInstance();
		ScreenBaseCache screenBaseCache = ScreenBaseCache.getInstance();

		for (Entry<String, List<ParmDic>> entry : map.entrySet()) {
			bc.putBeanCacheAll(entry.getKey(),
					getGroups(CachecodeUtil.CACHE_DATADICT), entry.getValue());
			*//**
			 * 给展现引擎注入数据字典缓存
			 *//*
			List<OptionsList> ol = new ArrayList<OptionsList>();
			ol = this.changeList(entry.getValue(), ol);
			// screenBaseCache.putInCache(key, content, groups);
			screenBaseCache.putInCache(CoreConstant.SCREENBASECACHE_KEYPREFIX
					+ "_" + entry.getKey(), ol, new String[] { "1" });
		}
		list2.clear();
		list.clear();
		list2 = null;
		list = null;
		flag = true;
		return flag;
	}*/

	/**
	 * @throws Exception
	 *             功能描述：初始化按钮权限功能
	 * @param
	 */
	/*public void initSysRoleButtonCache() throws Exception {
		sysRoleButtonBo = (SysRoleButtonBo) SourceTemplate.getSpringContextInstance().getBean(
				"sysRoleButtonBo", SysRoleButtonBo.class);
		List<SysRoleButton> list = sysRoleButtonBo.findList();
		// 获取展现层的缓存类
		ScreenBaseCache screenBaseCache = ScreenBaseCache.getInstance();
		// 遍历权限数据，并放入展现层缓存中
		for (SysRoleButton button : list) {
			String _key = button.getBtnNo() + "@" + button.getRoleNo();
			screenBaseCache.putInCache(CoreConstant.SCREENBASECACHE_KEYPREFIX
					+ "_" + _key, "0", new String[] { "role" });
		}
	}*/

	/**
	 * @throws Exception
	 *             功能描述：清除按钮权限功能缓存
	 * @param
	 */
	/*public boolean clearSysRoleButtonCache() throws Exception {
		try {
			// 获取展现层的缓存类
			ScreenBaseCache screenBaseCache = ScreenBaseCache.getInstance();
			screenBaseCache.flushGroup("role");
			return true;
		} catch (Exception e) {
			System.out.println("清除按钮权限功能缓存失败！");
			return false;
		}
	}*/

	/**
	 * 功能描述：初始化业务描述模版
	 * 
	 * @return
	 * @throws Exception
	 */

	/*public boolean initDescTempCache() throws Exception {
		sysDescTempBo = (SysDescTempBo) SourceTemplate.getSpringContextInstance().getBean(
				"sysDescTempBo", SysDescTempBo.class);
		List<SysDescTemp> list = sysDescTempBo.getAll();
		BaseCache bc = BaseCache.getInstance();
		for (int i = 0; i < list.size(); i++) {
			bc.putBeanCacheAll(list.get(i).getViewpointNo(),
					getGroups(CachecodeUtil.DESCTEMP), list.get(i));
			bc.putBeanCacheAll(list.get(i).getDesctempNo(),
					getGroups(CachecodeUtil.DESCTEMP), list.get(i));
		}
		return true;
	}*/

	/*public boolean reLoadDescTempCache() {
		BaseCache bc = BaseCache.getInstance();
		bc.flushByGroup(getGroups(CachecodeUtil.DESCTEMP)[0]);
		try {
			return this.initDescTempCache();
		} catch (Exception e) {
			System.out.println("刷新业务描述模板失败！");
			e.printStackTrace();
			return false;
		}
	}*/

	/**
	 * 功能描述：初始化消息配置模版
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public boolean initMsgConfig() {
		sysMsgConfigBo = (SysMsgConfigBo) SourceTemplate.getSpringContextInstance().getBean(
				"sysMsgConfigBo", SysMsgConfigBo.class);
		List<SysMsgConfig> list = sysMsgConfigBo.getAll();
		BaseCache bc = BaseCache.getInstance();
		for (int i = 0, j = list.size(); i < j; i++) {
			bc.putBeanCacheAll(list.get(i).getMsgNo(),
					getGroups(CachecodeUtil.MSGCONFIG), list.get(i));
		}
		return true;
	}*/

	/**
	 * 重新加载消息配置缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public boolean reLoadMsgConfigCache() throws Exception {
		BaseCache bc = BaseCache.getInstance();
		bc.flushByGroup(getGroups(CachecodeUtil.MSGCONFIG)[0]);
		return this.initMsgConfig();
	}*/

	/**
	 * 刷新用户字典项
	 * 
	 * @param key
	 * @throws Exception
	 */
	/*public void flushEntryCacheByKey(String key) throws Exception {
		BaseCache bc = BaseCache.getInstance();
		bc.removeByKey(key);
		nmdInterface = (NmdInterface) SourceTemplate.getSpringContextInstance().getBean("nmdInterface", NmdInterfaceImpl.class);
		List<ParmDic> list = (List) nmdInterface.findByParmDicAllByKename(key);
		bc.putBeanCacheAll(key, getGroups(CachecodeUtil.CACHE_DATADICT), list);
		// 获取展现层的缓存类
		ScreenBaseCache screenBaseCache = ScreenBaseCache.getInstance();
		List<OptionsList> ol = new ArrayList<OptionsList>();
		ol = this.changeList(list, ol);
		screenBaseCache.putInCache(CoreConstant.SCREENBASECACHE_KEYPREFIX + "_"+ key, ol, new String[] { "1" });
	}*/

	/**
	 * 清除安全审计缓存,重新加载
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public void reloadSecurity() throws Exception {
		BaseCache bc = BaseCache.getInstance();
		bc.removeByKey("useSecurity");
		this.initSecurity();
	}*/

	/**
	 * 
	 * 
	 * @param key
	 * @throws NeedsRefreshException
	 *             功能描述：获取消息配置
	 * @author wangcong
	 * @date 2015年12月24日 上午11:00:22
	 * @return Object
	 */
	/*public static String getMsgConfigByKey(String key) throws Exception {
		BaseCache bc = BaseCache.getInstance();
		SysMsgConfig smc = (SysMsgConfig) bc.getCacheByKeyName(key);
		return smc.getMsgContent();
	}*/

	/**
	 * 
	 * 
	 * @param key
	 * @throws NeedsRefreshException
	 *             功能描述：获取被缓存对象
	 * @author wangcong
	 * @date 2015年12月24日 上午11:00:22
	 * @return Object
	 */
	public Object getCacheByKeyName(String key) {
		BaseCache bc = BaseCache.getInstance();
		return bc.getCacheByKeyName(key);
	}

	/**
	 * 
	 * @param pd
	 * @param op
	 * @return 功能描述：展现引擎注入数据字典项类型转换
	 * @param
	 * @author wangcong
	 * @date 2015年12月24日 上午10:18:07
	 * @return List<OptionsList>
	 */
	/*private List<OptionsList> changeList(List<ParmDic> pdl,
			List<OptionsList> opl) {
		for (int i = 0; i < pdl.size(); i++) {
			OptionsList op = new OptionsList();
			op.setOptionId(pdl.get(i).getKeyName());
			op.setOptionValue(pdl.get(i).getOptCode());
			op.setOptionLabel(pdl.get(i).getOptName());
			opl.add(op);
		}
		return opl;
	}*/

	/**
	 * 功能描述：初始化安全审计缓存
	 * 
	 * @throws NeedsRefreshException
	 */
	/*public void initSecurity() throws NeedsRefreshException {
		BaseCache bc = BaseCache.getInstance();
		SecAuditConfigBo secAuditConfigBo = (SecAuditConfigBo) SourceTemplate
				.getAc().getBean("secAuditConfigBo");
		SecAuditConfig secAuditConfig = new SecAuditConfig();
		secAuditConfig.setItemNo("10");
		String isUse = secAuditConfigBo.getById(secAuditConfig).getIsUse();
		bc.put("useSecurity", isUse);
	}*/

	// 初始化审批人员
	/*public void initApprover() throws NeedsRefreshException {
		BaseCache bc = BaseCache.getInstance();
		bc.put(CachecodeUtil.APPROVER_PAS_FST_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_PAS_FST_CHECK));
		bc.put(CachecodeUtil.APPROVER_PAS_DUE_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_PAS_DUE_CHECK));
		bc.put(CachecodeUtil.APPROVER_COM_FST_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_COM_FST_CHECK));
		bc.put(CachecodeUtil.APPROVER_COM_DUE_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_COM_DUE_CHECK));
		bc.put(CachecodeUtil.APPROVER_COM_SUP_FST_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_COM_SUP_FST_CHECK));
		bc.put(CachecodeUtil.APPROVER_COM_SUP_DUE_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_COM_SUP_DUE_CHECK));
		bc.put(CachecodeUtil.APPROVER_EXT_FST_CHECK_STR, "0",
				getGroups(CachecodeUtil.APPROVER_EXT_FST_CHECK));
	}*/

	public String[] getGroups(int select) {
		String value = "";
		if (1 == select) {
			value = "group_datadict";
		} else if (2 == select) {
			value = "group_org";
		} else if (3 == select) {
			value = "group_cmsorg";
		} else if (4 == select) {
			value = "group_dhcorg";
		} else if (5 == select) {
			value = "group_button";
		} else if (6 == select) {
			value = "group_fastmenu";
		} else if (7 == select) {
			value = "group_approvalOpSeq";
		} else if (8 == select) {
			value = "group_desctemp";
		} else if (9 == select) {
			value = "group_msgconfig";
		} else {
			value = "group_other";
		}
		return new String[] { value };
	}

	/**
	 * @方法说明： 取缓存，将List<object[]>转换为List<bean>
	 * @date Sep 19, 2009
	 * @return
	 * @修改说明：
	 */
	public Object getBeanCache(int select) {
		return this.selectCache(select);
	}

	/**
	 * @方法说明： 缓存选择
	 * @date Sep 19, 2009
	 * @param select
	 * @return
	 * @修改说明：
	 */
	private List<Object> selectCache(int select) {
		switch (select) {
		// 数据字典
		case 1:
			return this.getDatadict();
			// 处置机构
			// case 2:return this.getOrgList();
			// cms机构
			// case 3:return this.getCmsOrgList();
			// 核算机构
			// case 4:return this.getDhcOrgList();

		default:
			return null;
		}
	}

	private List<Object> getDatadict() {
		 //没在action中调用，需要手动的注入该类 add by loudw 
		List<Object> result = null;
		try {
			result = (List) nmdInterface.findByParmDicAllByKename(this.key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*@SuppressWarnings("unchecked")
	public Map<String, String> getMapByKeyName(String key) throws Exception {
		List<ParmDic> pdl = (List<ParmDic>) getCacheByKeyName(key);
		Map<String, String> map = new HashMap<String, String>();
		if(pdl != null){
			for (ParmDic pd : pdl) {
				map.put(pd.getOptCode(), pd.getOptName());
			}
		}
		return map;
	}*/
	/**
	 * 
	 * 方法描述： 获得缓存中数据字典实体
	 * @param key
	 * @return
	 * @throws Exception
	 * Map<String,String>
	 * @author 沈浩兵
	 * @date 2017-3-21 下午1:47:33
	 */
	/*public Map<String, ParmDic> getMapObjByKeyName(String key) throws Exception {
		List<ParmDic> pdl = (List<ParmDic>) getCacheByKeyName(key);
		Map<String, ParmDic> map = new HashMap<String, ParmDic>();
		if(pdl != null){
			for (ParmDic pd : pdl) {
				map.put(pd.getOptCode(), pd);
			}
		}
		return map;
	}*/
	
	/**
	 * 方法描述：根据key获得缓存字典的第一项的optCode。（用于获取系统全局公共参数等）
	 * @param key
	 * @return String optCode，获取不到会返回null。
	 * @author LiuYF
	 * @data 2017年8月1日 17:39:41
	 */
	/*public String getSingleValByKey(String key) {
		List<ParmDic> pdl = (List<ParmDic>) getCacheByKeyName(key);
		if (pdl != null && pdl.size() > 0) {
			return pdl.get(0).getOptCode();
		}
		return null;
	}*/
	
	/**
	 * 获取审批意见选择项列表。用于公共的为表单指定审批意见选项时使用。  e.g.:
	 * <pre>
	 * this.changeFormProperty(<i>formapply0003</i>, "opinionType", "optionArray", opinionTypeList);
	 * </pre>
	 * @param activityType 审批页面的全局属性，由工作流传递参数，通过框架赋值，传入到此方法中。
	 * @param taskCouldRollBack 从流程节点task中获取CouldRollBack属性。
	 * @param map 根据传过来的hideOpinionType值隐藏审批意见选项 ,多个值之间用@分隔
	 * @return List<OptionsList> 审批意见选项List。
	 * @author LiuYF
	 * @throws Exception 
	 * @since 2017-6-15 17:59:20
	 */
	/*public List<OptionsList> getOpinionTypeList(String activityType, String taskCouldRollBack, Map<String, String> map) throws Exception {
		if (map == null) {
			map = new HashMap<String, String>();
		}

		List<OptionsList> opinionTypeList = new ArrayList<OptionsList>();
		List<ParmDic> opinionTypes = (List<ParmDic>) getCacheByKeyName("OPINION_TYPE");
		//隐藏审批意见选项
		String[] codeArray = null;
		if(map!=null){
			String hideCode = map.get("hideOpinionType");
			if(StringUtil.isNotEmpty(hideCode)){
				codeArray = hideCode.split("@");
			}
		}
		if(opinionTypes != null && opinionTypes.size()>0){
			for(ParmDic parmDic:opinionTypes){
				OptionsList s= new OptionsList();
				if ("signtask".equals(activityType)) {
					if(!AppConstant.OPINION_TYPE_ARREE.equals(parmDic.getOptCode())&&!AppConstant.OPINION_TYPE_DISARREE.equals(parmDic.getOptCode())){//1-同意 5-不同意
						continue;
					}
				}else{
					if(!"Y".equals(taskCouldRollBack)){
						if(AppConstant.OPINION_TYPE_ROLLBACK.equals(parmDic.getOptCode())||AppConstant.OPINION_TYPE_RESTART.equals(parmDic.getOptCode())){//3-退回上一环节 4-发回初审
							continue;
						}
					}
					// 非会签节点过滤掉“不同意”类型。
					if (AppConstant.OPINION_TYPE_DISARREE.equals(parmDic.getOptCode())) {
						continue;
					}
				}
				//隐藏审批意见选项
				if(codeArray!=null && StringUtil.arrayContainsString(codeArray,parmDic.getOptCode())){
					continue;
				}
	
				String nodeNo = map.get("nodeNo");
				String processDefinitionId = map.get("processDefinitionId");
				if (StringUtil.isNotEmpty(processDefinitionId)) {
					// 获取此流程的所有节点
					JSONArray nodes = ResolveProcessUtil.getDeploymentById(processDefinitionId);
					List<WkfBusNode> nodeList = JSONArray.toList(nodes, new WkfBusNode(), new JsonConfig());
	
					Boolean isOk = true;
	
					// 退回补充资料
					isOk = isOk && supplementForOption(parmDic, nodeNo, nodeList);
	
					// 流程第一节点
					isOk = isOk && firstForOption(parmDic, nodeNo, nodeList);
	
					if (!isOk) {
						continue;
					}
				}
	
				s.setOptionLabel(parmDic.getOptName());
				s.setOptionValue(parmDic.getOptCode());
				opinionTypeList.add(s);
			}
		}
		return opinionTypeList;
	}*/

	/**
	 * 退回补充资料相关选项排除
	 * @param parmDic
	 * @param nodeNo
	 * @param nodeList
	 * @return true需要此选项(不排除), false不需要此选项(需排除)
	 * @throws Exception
	 * @author WangChao
	 * @date 2017-12-22 下午5:20:35
	 */
	/*private Boolean supplementForOption(ParmDic parmDic, String nodeNo, List<WkfBusNode> nodeList) throws Exception {
		Boolean result = true;

		if ("6".equals(parmDic.getOptCode())) {// 6退回补充资料
			result = false;// 退回补充资料选项默认不添加
			// 循环判断是否存在退回补充资料节点
			for (WkfBusNode node : nodeList) {
				if ("supplement_data".equals(node.getId()) || "pact_supplement_data".equals(node.getId()) || "finc_supplement_data".equals(node.getId())) {
					result = true;// 当有退回补充资料节点时才添加补充资料选项
					break;
				}
			}
		}

		if ("supplement_data".equals(nodeNo) || "pact_supplement_data".equals(nodeNo) || "finc_supplement_data".equals(nodeNo)) {// 当前是补充资料节点, 下面意见项不添加
			if ("3".equals(parmDic.getOptCode()) || "4".equals(parmDic.getOptCode()) || "6".equals(parmDic.getOptCode())) {// 3退回上一环节, 4发回重审, 6退回补充资料
				result = false;
			}
		}

		return result;
	}*/

	/**
	 * 流程第一节点相关选项排除
	 * 
	 * @param parmDic
	 * @param nodeNo
	 * @param nodeList
	 * @return true需要此选项(不排除), false不需要此选项(需排除)
	 * @throws Exception
	 * @author WangChao
	 * @date 2017-12-25 上午9:34:42
	 */
	/*private Boolean firstForOption(ParmDic parmDic, String nodeNo, List<WkfBusNode> nodeList) throws Exception {
		Boolean result = true;

		for (WkfBusNode node : nodeList) {
			if (node.getNextName().equals(nodeNo) && ("start".equals(node.getId()) || "supplement_data".equals(node.getId()) || "pact_supplement_data".equals(node.getId()) || "finc_supplement_data".equals(node.getId()))) {// 第一节点
				if ("3".equals(parmDic.getOptCode()) || "4".equals(parmDic.getOptCode())) {// 3退回上一环节, 4发回重审
					result = false;
				}
			}
		}

		return result;
	}
*/
	/**
	 * 获得数据字典的JsonArray形式。多用于前台的自定义筛选组件。
	 * @param keyName
	 * @return JSONArray
	 * @author LiuYF
	 * @throws Exception
	 */
	/*public JSONArray getJSONArrayByKeyName(String keyName) throws Exception {
		JSONArray jArray = new JSONArray();
		List<ParmDic> parmDicList = (List<ParmDic>) getCacheByKeyName(keyName);
		if (parmDicList != null) {
			for (ParmDic p : parmDicList) {
				JSONObject json = new JSONObject();
				json.put("optName", p.getOptName());
				json.put("optCode", p.getOptCode());
				jArray.add(json);
			}
		}
		return jArray;
	}*/
	

	public NmdInterface getNmdInterface() {
		return nmdInterface;
	}

	public void setNmdInterface(NmdInterface nmdInterface) {
		this.nmdInterface = nmdInterface;
	}

	/*public SysDescTempBo getSysDescTempBo() {
		return sysDescTempBo;
	}

	public void setSysDescTempBo(SysDescTempBo sysDescTempBo) {
		this.sysDescTempBo = sysDescTempBo;
	}

	public SysMsgConfigBo getSysMsgConfigBo() {
		return sysMsgConfigBo;
	}

	public void setSysMsgConfigBo(SysMsgConfigBo sysMsgConfigBo) {
		this.sysMsgConfigBo = sysMsgConfigBo;
	}

	public SysRoleButtonBo getSysRoleButtonBo() {
		return sysRoleButtonBo;
	}

	public void setSysRoleButtonBo(SysRoleButtonBo sysRoleButtonBo) {
		this.sysRoleButtonBo = sysRoleButtonBo;
	}
*/
}
