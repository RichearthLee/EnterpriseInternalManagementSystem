package com.mftcc.interior.cred.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.mftcc.interior.cred.bean.AcCredServiceGroup;
import com.sun.org.apache.commons.beanutils.BeanUtils;


public class ServiceGroupsUtils {

	/**
	 * 根据 serialno 取出 group
	 * @param id
	 * @param groups
	 * @return
	 */
	public static Map<String,String> getServiceGroupByid(String id,List<Map<String,String>> groups){
		if(id==null){
			return null;
		}
		if(groups==null || groups.size()<1){
			return null;
		}
		Map<String,String> result=null;
		for(Map<String,String> g:groups){
			String serialno= g.get("serialno");
			if(id.equals(serialno)){
				result=g;
				break;
			}
		}
		
		return result;
	}
	
	
	/**
	 * 根据 ID 取出 所有父节点包含本身
	 * @param id
	 * @param groups
	 * @return
	 */
	public static List<Map<String,String>> getServiceGroupParents(String id,List<Map<String,String>> groups){
		List<Map<String,String>> result=null;
		if(id == null){
			return null;
		}
		if(groups == null || groups.size()<1){
			return null;
		}
		String tempid=id;
		Map<String,String> group=null;
		do{
			group=getServiceGroupByid(tempid, groups);
			if(group!=null){
				if(result==null){
					result=new ArrayList<Map<String,String>>();
				}
				result.add(group);
				tempid=group.get("pid");
			}else{
				tempid=null;
			}
			
		}while(StringUtils.isNotEmpty(tempid));
		
		return result;
	}
	
	/**
	 * 装换
	 * @param groups
	 * @return
	 */
	public static List<AcCredServiceGroup> mapsToBasicServiceGroups(List<Map<String,String>> groups){
		if(groups==null ){
			return null;
		}
		List<AcCredServiceGroup> bsgs=new ArrayList<AcCredServiceGroup>();
		AcCredServiceGroup bsg=null;
		for(Map<String,String> g: groups){
			try {
				bsg=new AcCredServiceGroup();
				BeanUtils.populate(bsg, g);
				bsgs.add(bsg);
			} catch (Exception e) {//转换失败
				e.printStackTrace();
			}
		}
		return bsgs;
	}
	
	/**
	 * 生成子树 根节点 id
	 * @param id
	 * @param groups
	 * @return
	 */
	public static AcCredServiceGroup getChildTree(String id,List<Map<String,String>> groups){
		AcCredServiceGroup root=new AcCredServiceGroup();
		try {
			Map<String, String> rootMap = ServiceGroupsUtils.getServiceGroupByid(id, groups);
			if(rootMap==null){
				
			}else{
				BeanUtils.populate(root, rootMap);
			}
			List<AcCredServiceGroup> bsgs = ServiceGroupsUtils.mapsToBasicServiceGroups(groups);
			root.addServiceGroups(bsgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	

	
	
	public static void main(String[] args) {
		//构造父级
		Map<String,String> m1_01=new HashMap<String, String>();
		m1_01.put("serialno", "1_01");
		m1_01.put("pid", "");
		m1_01.put("name", "a1");
		Map<String,String> m1_02=new HashMap<String, String>();
		m1_02.put("serialno", "1_02");
		m1_02.put("pid", "");
		m1_02.put("name", "a2");
		Map<String,String> m1_03=new HashMap<String, String>();
		m1_03.put("serialno", "1_03");
		m1_03.put("pid", "");
		m1_03.put("name", "a3");
		Map<String,String> m1_04=new HashMap<String, String>();
		m1_04.put("serialno", "1_04");
		m1_04.put("pid", "");
		m1_04.put("name", "a4");
		//构造子级
		Map<String,String> m2_01=new HashMap<String, String>();
		m2_01.put("serialno", "2_01");
		m2_01.put("pid", "1_01");
		m2_01.put("name", "b1");
		Map<String,String> m2_02=new HashMap<String, String>();
		m2_02.put("serialno", "2_02");
		m2_02.put("pid", "1_02");
		m2_02.put("name", "b2");
		Map<String,String> m2_03=new HashMap<String, String>();
		m2_03.put("serialno", "2_03");
		m2_03.put("pid", "1_03");
		m2_03.put("name", "b3");
		Map<String,String> m2_04=new HashMap<String, String>();
		m2_04.put("serialno", "2_04");
		m2_04.put("pid", "1_04");
		m2_04.put("name", "b4");
		Map<String,String> m2_05=new HashMap<String, String>();
		m2_05.put("serialno", "m2_05");
		m2_05.put("pid", "1_04");
		m2_05.put("name", "b5");
		
		Map<String,String> m3_01=new HashMap<String, String>();
		m3_01.put("serialno", "3_01");
		m3_01.put("pid", "2_01");
		m3_01.put("name", "c1");
		Map<String,String> m3_02=new HashMap<String, String>();
		m3_02.put("serialno", "3_02");
		m3_02.put("pid", "2_02");
		m3_02.put("name", "c2");
		Map<String,String> m3_03=new HashMap<String, String>();
		m3_03.put("serialno", "3_03");
		m3_03.put("pid", "2_03");
		m3_03.put("name", "c3");
		Map<String,String> m3_04=new HashMap<String, String>();
		m3_04.put("serialno", "3_04");
		m3_04.put("pid", "2_04");
		m3_04.put("name", "c4");
		
		Map<String,String> m4_01=new HashMap<String, String>();
		m4_01.put("serialno", "4_01");
		m4_01.put("pid", "3_01");
		m4_01.put("name", "d1");
		Map<String,String> m4_02=new HashMap<String, String>();
		m4_02.put("serialno", "4_02");
		m4_02.put("pid", "3_02");
		m4_02.put("name", "d2");
		Map<String,String> m4_03=new HashMap<String, String>();
		m4_03.put("serialno", "4_03");
		m4_03.put("pid", "3_03");
		m4_03.put("name", "d3");
		Map<String,String> m4_04=new HashMap<String, String>();
		m4_04.put("serialno", "4_04");
		m4_04.put("pid", "3_04");
		m4_04.put("name", "d4");
	
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		listMap.add(m1_01);
		listMap.add(m1_02);
		listMap.add(m1_03);
		listMap.add(m1_04);
		
		listMap.add(m2_01);
		listMap.add(m2_02);
		listMap.add(m2_03);
		listMap.add(m2_04);
		listMap.add(m2_05);
		
		listMap.add(m3_01);
		listMap.add(m3_02);
		listMap.add(m3_03);
		listMap.add(m3_04);
		
		listMap.add(m4_01);
		listMap.add(m4_02);
		listMap.add(m4_03);
		listMap.add(m4_04);
		//getChild(listMap);
		
	}
	
	public static void getChild(List<Map<String,Object>> listMap){
		//1.父级节点 
		List<String> fatherList = new ArrayList<String>();
		//2.构造父子对应关系
		Map<String,List<String>> mapFC = new HashMap<String, List<String>>();
		//3.构造对象集合
		Map<String,Object> maps = new HashMap<String, Object>();
		for(Map<String,Object> map:listMap)
		{
			String id = (String) map.get("id");
			String pid = (String) map.get("pid");
			if("".equals(pid)||null==pid)
			{
				fatherList.add(id);//1.父级节点 
			}
			else
			{
				//2.构造父子对应关系
				List<String> listChild = null;
				if(mapFC.containsKey(pid))
				{
					listChild = mapFC.get(pid);
					listChild.add(id);
				}
				else
				{
					listChild = new ArrayList<String>();
					listChild.add(id);
					mapFC.put(pid, listChild);
				}
			}
			maps.put(id, map);//3.构造对象集合
		}
		Gson gson = new Gson();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(String pid:fatherList)
		{
			Map<String,Object> resMap = findChild(pid, mapFC, maps);
			list.add(resMap);
		}
		System.out.println(gson.toJson(list));
	}

	
	public static Map<String,Object> findChild(String pid,Map<String,List<String>> mapFC,Map<String,Object> maps){
		Map<String,Object> resMap = null;
		//1.查询是否有子节点
		List<String> listC = mapFC.get(pid);
		if(null==listC||listC.isEmpty())//代表叶子节点
		{
			resMap = (Map<String, Object>) maps.get(pid);
		}
		else
		{
			Gson gson = new Gson();
			List<Map<String,Object>> childs = new ArrayList<Map<String,Object>>();
			Map<String,Object> resMapF = (Map<String, Object>) maps.get(pid);
			resMap = resMapF;
			for(String pidTemp:listC)
			{
				Map<String,Object> resMapC = findChild(pidTemp, mapFC, maps);
				childs.add(resMapC);
//				return findChild(pidTemp, mapFC, maps);
			}
			resMap.put("childs", childs);
		}
		return resMap;
	}
}
