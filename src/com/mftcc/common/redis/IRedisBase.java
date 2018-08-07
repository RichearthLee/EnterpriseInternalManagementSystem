package com.mftcc.common.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shensh
 * @date 2018年4月23日  
 * @version 1.0

 */

public interface IRedisBase  {
   
	//  增加 取  String 类型
	public void addString(String key,  String value);
	public String getString(String key);
	
	
	//  增加 取  Hash 类型
	public void addHash(String key,String hashKey, String hashValue);
	public String getHash(String key,String hashKey);
	
	public void addHashPutAll(String key,Map<String,Object> map);
	
	//  增加 取 List 类型
	public void addList(String key,String value);
	public String getList(String key);
	 
	//批量把一个数组插入到列表中
	public void leftPushAll(String key,Object value);
	
	//  增加 取 object 类型
	public void addObj(String objectKey,String key,Object object);
	public Object getObj(String objectKey,String key);
	public Long getObjCount(String objectKey);
	
/*	//增
    public void add(K key,String value);
    public void addObj(H objectKey,K key,V object);
    //删
    public void delete(K key);
    public void delete(List<K> listKeys);
    public void deletObj(H objecyKey,K key);
    //改
    public void update(K key,String value);
    public void updateObj(H objectKey,K key,V object);
    //查
    public String get(K key);
    public V getObj(H objectKey,K key);*/

	 
}