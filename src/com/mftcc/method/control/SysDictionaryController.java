package com.mftcc.method.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mftcc.common.redis.IRedisBase;
import com.mftcc.method.bean.SysDictionary;
import com.mftcc.method.service.ISysDictionaryService;

/**
 * @author shensh
 * @date 2018年4月12日  
 * @version 1.0
 */
@Controller
//@RequestMapping("/method")
public class SysDictionaryController {
	@Autowired
	private ISysDictionaryService  iSysDictionaryService;
	
	@Resource(name="iRedisBase")
    private IRedisBase iRedisBase;
   
    @Autowired
    protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	
	@RequestMapping(value={"/getdic"})  
	@ResponseBody
	public  Map<String, Object>  selectAllSysDictionary( SysDictionary sysDictionary) {
		// TODO Auto-generated method stub
		 Map<String, Object> dataMap = new HashMap<String,Object>();
		 List<SysDictionary> sysDictionaryList = iSysDictionaryService.selectAllSysDictionary(sysDictionary);
		 dataMap.put("sysDictionaryList", sysDictionaryList);
			System.out.println(sysDictionaryList);
			dataMap.put("falg", "");
		return  dataMap;
				
	}
	
	
	@RequestMapping(value={"/getDicRedis"})  
	@ResponseBody
	public  Map<String, Object>  getDicRedis( SysDictionary sysDictionary) {
		// TODO Auto-generated method stub  存数据字典在redis中
		Map<String, Object> dataMap = new HashMap<String,Object>();
		Map<String, Object> dicmap = new HashMap<String,Object>();
	 	try {
			 List<SysDictionary> sysDicList = iSysDictionaryService.selectAllSysDictionary(sysDictionary);
			
			 for (int i = 0; i < sysDicList.size(); i++) {
				String  key = String.valueOf(i);
				SysDictionary dictionary = sysDicList.get(i);
				dicmap.put("dicKey", dictionary.getDicKey());
				dicmap.put("dicValue", dictionary.getDicValue());
				dicmap.put("dicType", dictionary.getDicType());
			  iRedisBase.addObj("sysdic",key,dicmap);
			  }
			dataMap.put("falg", "true");
		 } catch (Exception e) {
			dataMap.put("falg", "fail");
		} 
		return  dataMap;
	}
	
	
	public  List< Map<String, Object>> getAllSysDicInRedis() {
		// TODO 在redis中 取数据字典
		Map<String, Object> dataMap = new HashMap<String,Object>();
		Map<String, Object> dicmap = new HashMap<String,Object>();
		 List< Map<String, Object>> sysDicmap =  new ArrayList<Map<String, Object>>();;
		
	 	 try {
	 		 long l =iRedisBase.getObjCount("sysdic");
			 for (int i = 0; i < l; i++) { 
				 String  key = String.valueOf(i);
				 Map<String, Object> map =(Map<String, Object>)  iRedisBase.getObj("sysdic",key);
				 sysDicmap.add(map);
			    }
		    } catch (Exception e) {
		 }  
		return  sysDicmap;
	}
	
	
	@RequestMapping(value={"/getSysDicInRedis"})  
	@ResponseBody
	public  Map<String, Object>  getSysDicInRedis( SysDictionary sysDic) {
		// TODO 在redis中 取数据字典     1通过数据字典类型  dicType 2通过数据字典key  dicKey
		List< Map<String, Object>> listdic =	getAllSysDicInRedis();   // 取自redis 
		String sysDicType = "";
		String sysDicKey = "";
		if(sysDic.getDicKey()==null){
			// 传的类型  
			  sysDicType = sysDic.getDicType();
		}else {
			// 传的key  
			 sysDicKey = sysDic.getDicKey();
		}
		 
		Map<String, Object> dataMap = new HashMap<String,Object>();   // 
		Map<String, Object> dicmap = new HashMap<String,Object>();
		List< SysDictionary> sysDicList =  new ArrayList<SysDictionary>();;
		
	 	 try {
			 for (int i = 0; i < listdic.size(); i++) { 
				 Map<String, Object> dicMap2 = new HashMap<String,Object>();
					 String dicKey = (String) listdic.get(i).get("dicKey");
		 			String dicType = (String) listdic.get(i).get("dicType");
		 			String dicValue = (String) listdic.get(i).get("dicValue");
				
		 			if (sysDicType.equals(dicType)) {
		 				
					 SysDictionary sysDicd = new SysDictionary();
					 sysDicd.setDicKey(dicKey);
					 sysDicd.setDicValue(dicValue);
					 sysDicList.add(sysDicd);
				 	}
		 			
		 			if (sysDicKey.equals(dicKey)) {
						 SysDictionary sysDicd = new SysDictionary();
						 sysDicd.setDicValue(dicValue);
						 sysDicList.add(sysDicd);
					 	}
			    }
			 
			  dataMap.put("sysDictionaryList", sysDicList);
			  dataMap.put("falg", "true");
		    } catch (Exception e) {
		    	System.out.println(e.fillInStackTrace());
			  dataMap.put("falg", "fail");
		 }  
		return  dataMap;
	}
	
}
