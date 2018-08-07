/**
 * Copyright (C) DXHM 版权所有
 * 文件名： ICifServiceAccService.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.service
 * 说明：
 * @author 李梦浩
 * @date 2016年11月10日 下午5:17:39
 * @version V1.0
 */ 
package com.mftcc.interior.cred.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mftcc.method.bean.Ipage;

/**
 * 类名： ICifServiceAccService
 * 描述：客户开通服务 以及服务项管理
 * @author 李梦浩
 * @date 2016年11月10日 下午5:17:39
 *
 *
 */
public interface GroupService {
	
	
	/**
	 * 获取服务分组不分页
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getServiceGroups(Map<String,String> parm) throws Exception;
	/**
	 * 获取服务组列表
	 * @param ipage
	 * @return
	 * @throws Exception
	 */
	public Ipage getserviceGroupListPage(Ipage ipage) throws Exception;
	/**
	 * 删除服务组
	 * @param serialno
	 * @throws Exception
	 */
	public void deleteServiceGroup(String serialno)  throws Exception;
	/**
	 * 查询服务组
	 * @param serialno
	 * @throws Exception
	 */
	public Map<String,Object> getSerGroupByid(String businessCode)  throws Exception;
	/**
	 * 新增服务组
	 * @param mp
	 * @return 
	 * @throws Exception
	 */
	public String addServiceGroup(Map<String, String> mp) throws Exception;
	/**
	 * 查询所有服务项
	 * @param searchFiled
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getServItemsBySearchFiled(String searchFiled) throws Exception;
	/**
	 * 修改服务组配置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void updateServiceGroup(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取服务项的评价总数
	 * @param itemNo
	 * @param score
	 * @return
	 */
	public int getScoreCount(String itemNo, String score) throws Exception;
	/**
	 * 获取服务评价列表
	 * @param ipage
	 * @return
	 * @throws Exception
	 */
	public Ipage getServiceScoreListPage(Ipage ipage) throws Exception;
	
}
