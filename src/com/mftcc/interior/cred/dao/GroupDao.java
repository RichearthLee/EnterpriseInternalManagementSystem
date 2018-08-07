/**
 * Copyright (C) DXHM 版权所有
 * 文件名： IServiceItemsDao.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.dao
 * 说明：
 * @author 李梦浩
 * @date 2016年11月10日 下午6:07:37
 * @version V1.0
 */ 
package com.mftcc.interior.cred.dao;

import java.util.List;
import java.util.Map;
import com.mftcc.method.bean.Ipage;

/**
 * 类名： IServiceItemsDao
 * 描述：用户能开通的服务
 * @author 李梦浩
 * @date 2016年11月10日 下午6:07:37
 *
 *
 */
public interface GroupDao {
	

	/**
	 * 获取服务组列表
	 * @param ipage
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getAllSerGroups(Ipage ipage) throws Exception;
	/**
	 * 获取服务组列表 不分页
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getAllServiceGroups(Map<String,String> param) throws Exception;
	/**
	 * 获取服务 组信息
	 * @param id ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSerGroupByid(String id) throws Exception;
	/**
	 * 获取服务组总条数
	 * @param ipage
	 * @return
	 * @throws Exception
	 */
	public Integer getAllSerGroupsCnt(Ipage ipage) throws Exception;
	/**
	 * 删除服务组
	 * @param serialno
	 * @throws Exception
	 */
	public void deletServiceGroup(String businessCode) throws Exception;
	/**
	 * 新增服务组
	 * @param mp
	 * @throws Exception
	 */
	public void addServiceGroup(Map<String, String> mp) throws Exception;
	/**
	 *根据服务组编码查询所有的服务项编号
	 * @param businessCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getGroupServItemNos(String businessCode) throws Exception;
	/**
	 * 根据服务组编码删除服务组明细
	 * @param businessCode
	 * @throws Exception
	 */
	public void deleteServGroupDetail(String businessCode) throws Exception;
	/**
	 * 获取所有服务
	 * @param searchFiled
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getServItemsBySearchFiled(Map<String, String> map)
			throws Exception;
	/**
	 * 修改服务组信息
	 * @param mp
	 * @throws Exception
	 */
	public void updateServiceGroup(Map<String, String> mp) throws Exception;
	/**
	 * 新增服务组明细
	 * @param detailMp
	 * @throws Exception
	 */
	public void addServiceGroupDetail(Map<String, String> detailMp) throws Exception;
	
	/**
	 * 服务评价总数
	 * @param itemNo
	 * @param score
	 * @return
	 */
	public Integer getScoreCount(String itemNo, String score)  throws Exception;
	/**
	 * 获取服务评价列表
	 * @param ipage
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectAllServiceScore(Ipage ipage) throws Exception;
	
}
