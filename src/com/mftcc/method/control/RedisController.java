package com.mftcc.method.control;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mftcc.common.redis.IRedisBase;


/**
 * @author shensh
 * @date 2018年4月23日  
 * @version 1.0
 */
@Controller
public class RedisController {

    @Resource(name="iRedisBase")
    private IRedisBase iRedisBase;

    @Autowired
    protected RedisTemplate<Serializable, Serializable> redisTemplate;
    
    @RequestMapping("/redis")
    public String hello(){
     
        System.out.println("redis测试开始");
       
        return "sys/systest";
    }
    
    @RequestMapping("/redis2")
    public String redis(){
        System.out.println("hello redis2");
        String str =   iRedisBase.getString("redis");
        System.out.println(str);
        return "systest";
    }
    
 
    
    @RequestMapping("/hello3")
    @ResponseBody
    public Map<String, Object> hello3(HttpServletRequest request,Map<String, Object> map){
        ModelAndView mv = new ModelAndView();
       
        String str = iRedisBase.getString("name");
        mv.setViewName("hello");
        map.put("data",str );
        return map;
    }
 
     
     @RequestMapping("/testlist")
     @ResponseBody
    public HashMap<String, Object>  testlist( HashMap<String, Object> map ) throws DataAccessException {
      System.out.println("---");
        map.put("data","shen");
       
    	 iRedisBase.addHash("user","id","001");
    	 iRedisBase.addHash("user","name","沈帅虎");
    	 iRedisBase.addHash("user","age","18");
    	 
        return map;
    }
     
     @RequestMapping("/getlist")
     @ResponseBody
    public HashMap<String, Object>  getlist( HashMap<String, Object> map ) throws DataAccessException {
      System.out.println("---");
       
    	 
     String	Id = iRedisBase.getHash("user","id");
     String	name = iRedisBase.getHash("user","name");
     String	age =	 iRedisBase.getHash("user","age");
    	 
    	 System.out.println("ＩＤ  "+Id);
    	 System.out.println("name  "+name);
    	 System.out.println("age  "+age);
        return map;
    }
 
     @RequestMapping("/addMap")
     @ResponseBody
    public Map<Object, Object> addlist( HashMap<String, Object> map ) throws DataAccessException {
      System.out.println("---");
    	 //   redis  map的存取
      HashOperations<Serializable, Object, Object>  hash = redisTemplate.opsForHash();  
	      map.put("name", "孟静波");  
	      map.put("age", "26");  
	      hash.putAll("tmap", map); 
	      System.out.println(map.toString());  
	      Map<Object, Object> a=  hash.entries("tmap");
	      System.out.println(a.get("name"));  
        return a;
    }
     
     
     
     @RequestMapping("/addList")
     @ResponseBody
    public String addList( HashMap<String, Object> map, HashMap<String, Object> map2 ) throws DataAccessException {
      System.out.println("---");
    	 //   redis  map的存取
      map.put("name", "孟静波");  
      map.put("age", "26");  
      map2.put("name", "张凡");  
      map2.put("age", "28"); 
      
      	//添加 一个 list 列表  
	      ListOperations<Serializable, Serializable> list = redisTemplate.opsForList();  
	      list.rightPush("lpList", map.toString());  
	      list.rightPush("lpList",  map2.toString());  
      //输出 list  
	      List<Serializable>    l =   list.range("lpList", 0, -1);
      System.out.println(l);
	      System.out.println(l.get(3).toString());  
	      System.out.println(l.listIterator());  
        return "yes";
    }
     
     @RequestMapping("/addSet")
     @ResponseBody
    public String addSet() throws DataAccessException {
      System.out.println("---");
    //添加 一个 set 集合  
      SetOperations<Serializable, Serializable> set = redisTemplate.opsForSet();  
      set.add("lpSet", "lp");  
      set.add("lpSet", "26");  
      set.add("lpSet", "178cm");  
      //输出 set 集合  
      System.out.println(set.members("lpSet"));  
      //添加有序的 set 集合  
      ZSetOperations<Serializable, Serializable> zset = redisTemplate.opsForZSet();  
      zset.add("lpZset", "lp", 0);  
      zset.add("lpZset", "26", 1);  
      zset.add("lpZset", "178cm", 2);  
      //输出有序 set 集合  
      System.out.println(zset.rangeByScore("lpZset", 0, -1));  
     return "yes";
    }
     
     @RequestMapping("/addObject")
     @ResponseBody
    public String addObject(Map<String, Object> map, Map<String, Object> map2   ) throws DataAccessException {
      System.out.println("---");
    //添加 一个 Object 集合  
      
      map.put("name", "阮珂");  
      map.put("age", "16");  
      map2.put("name", "梦好");  
      map2.put("age", "18"); 
      
      iRedisBase.addObj("dic","0", map);
      iRedisBase.addObj("dic", "1", map2);

      //输出 
      System.out.println("---"); 
      
      Map<String, Object>  object =    (Map<String, Object>) iRedisBase.getObj("dic", "0");
      Map<String, Object>  object1 =    (Map<String, Object>) iRedisBase.getObj("dic", "1");
      System.out.println(object.get("name")); 
      System.out.println(object1.get("name")); 
      
     return "yes";
    }
     
}