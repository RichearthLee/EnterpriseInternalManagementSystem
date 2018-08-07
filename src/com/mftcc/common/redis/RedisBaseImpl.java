package com.mftcc.common.redis;
 
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * @author shensh
 * @date 2018年4月23日  
 * @version 1.0
   		redisTemplate.opsForValue();//操作字符串
		redisTemplate.opsForHash();//操作hash
		redisTemplate.opsForList();//操作list
		redisTemplate.opsForSet();//操作set
		redisTemplate.opsForZSet();//操作有序set
 */
@Component("iRedisBase")
public class RedisBaseImpl implements IRedisBase{
    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;
    private Logger logger = Logger.getLogger(String.valueOf(RedisBaseImpl.class));
 
    
	@Override
	public void addHash(String str, String key, String value) {
		// TODO Auto-generated method stub
		if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
            redisTemplate.opsForHash().put(str, key, value);
        }
	}
	@Override
	public String getHash(String key, String hashKey) {
		// TODO Auto-generated method stub
	 return	(String) redisTemplate.opsForHash().get(key, hashKey);
	}

 
	@Override
	public void addString(String key, String value) {
		// TODO Auto-generated method stub
		if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
           redisTemplate.opsForValue().set(key,value);
        }
	}
	 
	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		String value = (String) redisTemplate.opsForValue().get(key);
        return value;
	}
	
	// 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
	@Override
	public void addList(String key, String value) {
		// TODO Auto-generated method stub
		if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
           redisTemplate.opsForList().leftPush(key, value);
        }
	}
	 
	@Override
	public String getList(String key) {
		// TODO Auto-generated method stub
		String value = (String) redisTemplate.opsForList().leftPop(key);
        return value;
	}
	/* (non-Javadoc)
	 * @see com.mftcc.common.redis.IRedisBase#leftPushAll(java.lang.String, java.lang.Object)
	 */
	@Override
	public void leftPushAll(String key, Object value) {
		// TODO Auto-generated method stub
		if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
        	String[] stringarrays = new String[]{"1","2","3"};
           redisTemplate.opsForList().leftPushAll(key, stringarrays);
        }
	}
	
	@Override
	public void addHashPutAll(String key, Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
           redisTemplate.opsForHash().putAll(key, map);
        }
	}
	
	@Override
	public void addObj(String objectKey, String key, Object object) {
		// TODO Auto-generated method stub
		 if(redisTemplate==null){
	            logger.warning("redisTemplate 实例化失败");
	            return;
	        }else{
	            redisTemplate.opsForHash().put(objectKey,key,object);
	        }
	}
	@Override
	public Object getObj(String objectKey, String key) {
		// TODO Auto-generated method stub
		
		Object object = redisTemplate.opsForHash().get(objectKey,key);

		return object;
	}
	
	@Override
	public Long getObjCount(String objectKey) {
		// TODO Auto-generated method stub  查询一共多少条
		Long num = redisTemplate.opsForHash().size(objectKey);
		return num;
	
	}
	
	 
	
	
	
	
	
}

	/*   
	@Override
	public void delete(String key) {
	}
	@Override
	public void delete(List<String> listKeys) {
	}
	@Override
	public void deletObj(String objecyKey, String key) {
	}
	@Override
	public void update(String key, String value) {
	}
	
	@Override
	public void updateObj(String objectKey, String key, SeeUser object) {
	
	}
	
	 */