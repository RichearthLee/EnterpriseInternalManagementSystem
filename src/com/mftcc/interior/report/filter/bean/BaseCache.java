package com.mftcc.interior.report.filter.bean;

import java.util.Date;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @对象说明 缓存对象类
 * @author wangshanfang
 * @date Sep 18, 2009
 * @修改说明
 */
public class BaseCache extends GeneralCacheAdministrator {
	private static final long serialVersionUID = -4397192926052141162L;
	// 过期时间(单位为秒);
	private int refreshPeriod; // 60*60*5
	// 关键字前缀字符;
	private String keyPrefix; // CMSII
	private static BaseCache instance;
	private BaseCache() {
	}
	public synchronized static BaseCache getInstance() {
		if (instance == null) {
			instance = new BaseCache(CachecodeUtil.REFRESHPERIOD,
					CachecodeUtil.KEYPREFIX);
		}
		return instance;
	}
	public BaseCache(int refreshPeriod, String keyPrefix) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	// 添加被缓存的对象--分组
	public void put(String key, Object value, String[] groups) {
		this.putInCache(this.keyPrefix + "_" + key, value, groups);
	}

	// 添加被缓存的对象;
	public void put(String key, Object value) {
		this.putInCache(this.keyPrefix + "_" + key, value,new String[] { "group_other" });
	}

	// 更新被缓存的对象--按key更新
	public void removeByKey(String key) {
		this.flushEntry(this.keyPrefix + "_" + key);
	}
	// 更新所有被缓存的更新;
	public void flushAll(Date date) {
		this.flushAll(date);
	}
	// 更新所有缓存对象
	public void flushAll() {
		this.flushAll();
	}
	// 更新被缓存的对象--按组更新
	public void flushByGroup(String group) {
		this.flushGroup(group);
	}

	public Object putBeanCacheAll(String key, String[] group, Object result) {
		String _key = this.keyPrefix + "_" + key;
		boolean updated = false;
		try {
			this.put(key, result, group);
			updated = true;
		} catch (Exception exception) {
			this.removeByKey(key);
		} finally {
			if (!updated) {// 防止找不到对象缓存，一直等待下去。
				this.cancelUpdate(_key);
			}
		}
		return result;
	}
	/**
	 * 
	
	 * @param key
	 * @author wangcong
	 * @date 2015年12月24日 上午11:00:22
	 * @return  Object
	 * @throws Exception 
	 */
	 public Object getCacheByKeyName(String key){
		try {
			return this.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
		} catch (Exception e) {
            this.cancelUpdate(this.keyPrefix+"_"+key);
            return null;
		}
	 }
	

	/**
	 * 功能描述：查询权限按钮缓存中的值
	 * 
	 * @param key
	 * @param roleOrUser
	 *            系统权限模式 user = 角色 role = 权限
	 * @return
	 */
	public boolean getBeanCacheForButton(String key, String orguser) {
		String _key = this.keyPrefix + "_" + key;
		Object result = "";
		boolean flag = false;
		try {
			result = getFromCache(_key, -1);
			if ("0".equals(result)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception ee) {
			flag = false;
			this.cancelUpdate(_key);
		}
		return flag;
	}
	
	/**
	 * 
	 * @方法说明： 取缓存实体
	 * @param key
	 *            关键字
	 * @param select
	 *            CachecodeUtil.CACHE_DATADICT 数据字典 CachecodeUtil.CACHE_ORG 处置机构
	 * @return ArrayList<CacheBean>
	 * @修改说明：
	 */ 
	public Object getBeanCache(String key, int select) {
		String _key = this.keyPrefix + "_" + key;
		Object result = null;
		try {
			result = getFromCache(_key, this.refreshPeriod);
		} catch (NeedsRefreshException e) {// 如果不存在，查询数据库
			boolean updated = false;
			try {
				result = new CodeUtils(key).getBeanCache(select);
				CodeUtils cu = new CodeUtils();
				this.put(key, result, cu.getGroups(select));
				updated = true;
			} catch (Exception exception) {
				this.removeByKey(key);
			} finally {
				if (!updated) {// 防止找不到对象缓存，一直等待下去。
					this.cancelUpdate(_key);
				}
			}
		}
		return result;
	}
}
