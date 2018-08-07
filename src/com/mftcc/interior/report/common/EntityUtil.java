package com.mftcc.interior.report.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

//import net.sf.json.JSONArray;

//import org.apache.struts2.ServletActionContext;

//import app.base.BaseDomain;
//import app.base.SourceTemplate;
//import app.util.toolkit.Ipage;

//import com.core.struts.ActionContext;
import com.ibatis.common.beans.ProbeFactory;
/**
 * ajax异步更新单个字段
 * @author jzh
 * @Date 2015-12-22
 */
public class EntityUtil {
	/**
	 * 反射赋值
	 * @param entity
	 * @param ajaxEntity
	 * @return 
	 * @throws Exception 
	 */
	public static Object reflectionSetVal(Object entity,Object ajaxEntity,Map keyMap) throws Exception{
		Field fields[] = entity.getClass().getDeclaredFields();
		for(Object fieldKey:keyMap.keySet()){
			for(Field field:fields){
				String fieldName = field.getName();
				if(fieldKey!=null&&fieldKey.equals(fieldName)){
					Object fieldValue = ProbeFactory.getProbe().getObject(ajaxEntity,fieldName);
					 if(fieldValue!=null){
						 ProbeFactory.getProbe().setObject(entity, fieldName, fieldValue);
					 }
				}
			}
		}
		return entity;
	}
	/**
	 * json 编码转换
	 * @param ajaxData
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String ajaxDecode(String ajaxData) throws UnsupportedEncodingException{
		if(ajaxData!=null){
			ajaxData = java.net.URLDecoder.decode(
					new String(ajaxData.getBytes("GBK"),"ISO-8859-1"),
					"UTF-8");
		}
		return ajaxData;
	}
	/***
	 * 实体类字段映射成数据库字段
	 * @param fieldName
	 * @return
	 */
	public static String changeColForSqlCol(String fieldName){
		if(fieldName!=null){
			StringBuffer sb = new StringBuffer();
			char fieldChar[] = fieldName.toCharArray();
			for(char letter:fieldChar){
				if(Character.isUpperCase(letter)){//判断是大写
					sb.append("_");
				}
				sb.append(Character.toUpperCase(letter));
			}
			fieldName = sb.toString();
		}
		return fieldName;
	}
	
	/***
	 * 自动下拉功能调用
	 * @param entity 实体对象
	 * @param ajaxData 字段名（类中属性即可） 
	 * @param query 填写内容（类中属性即可） 
	 * @param parms 可能需要的参数，若没有赋空即可（如：String parm = "prodType="+prodType+";prodNo=100011";） 
	 * @param parms 注：参数之间用分号分割";",参数和值之间用等号分割"="
	 * @param methodName 方法名,传""或者null时,默认执行findByPage方法,否则执行传的参数方法
	 * @return
	 */
	/*public JSONArray prodAutoMenu(Object entity,String ajaxData,String query,String parms,String methodName) throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		JSONArray array = null;
		try{
			String [] parm = parms.split(";");
			for(String str : parm){
				if(str!=null && !"".equals(str)){
					String parmName = str.split("=")[0].substring(0, 1).toUpperCase()+str.split("=")[0].replaceFirst("\\w","").trim(); 
					Method method = entity.getClass().getMethod("set"+parmName,String.class);
					String val = "";
					try{
						val = "null".equals(str.split("=")[1])?"":str.split("=")[1];
					}catch(Exception e){
						val = "";
					}
					
					method.invoke(entity,val);
				}
			}
			
			String fieldName = ajaxData.substring(0, 1).toUpperCase()+ajaxData.replaceFirst("\\w",""); 
			Method method = entity.getClass().getMethod("set"+fieldName,String.class);
			method.invoke(entity,query);
			Ipage ipage = new Ipage();
			ipage.setPageNo(1);
			ipage.setPageSize(8);
			String entityClassName = entity.getClass().toString().substring(6);
//			System.out.println(entityClassName);
			String boClassName = entityClassName.replace("entity", "bo")+"Bo";
			String boName = boClassName.split("\\.")[boClassName.split("\\.").length-1].substring(0, 1).toLowerCase()+boClassName.split("\\.")[boClassName.split("\\.").length-1].substring(1);
			Object bo = SourceTemplate.getSpringContextInstance().getBean(boName);
			
			if(methodName==null||"".equals(methodName)){
				methodName = "findByPage";
			}
			Method boMethod = Class.forName(boClassName).getMethod(methodName,Ipage.class,entity.getClass());
			
			ipage = (Ipage) boMethod.invoke(bo,ipage, entity);
			List<Object> list = (List<Object>)ipage.getResult();
			array = JSONArray.fromObject(list);
			for(int i =0;i<list.size();i++){
				method = list.get(i).getClass().getMethod("get"+fieldName);
				String lable = (String) method.invoke(list.get(i));
				array.getJSONObject(i).put("label", lable);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return array;
	}
	
	*//**
	 * 复制两个对象属性值
	 * @param oldUser
	 * @param newUser
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *//*
	public static void copyProperty(Object oldObject,Object newObject) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		//新的class
		Class newClass = newObject.getClass();
		//老的class
		Class oldClass = oldObject.getClass();
		//该类所有的属性
		Field[] oldFields = oldClass.getDeclaredFields();
		//新的属性
		Field newField = null;
		//老的属性
		Field oldField = null;
		for(Field f : oldFields){
			//类中的属性名称
			String fieldName = f.getName();
			//通过属性名称获取属性
			oldField = newClass.getDeclaredField(fieldName);
			//获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
			//值为 false 则指示反射的对象应该实施 Java 语言访问检查。 
			oldField.setAccessible(true);
			//根据属性获取对象上的值
			Object value = oldField.get(oldObject);
			//过滤空的属性或者一些默认值
			if (value == null || "".equals(value)) {
				continue;
			}
			newField = newClass.getDeclaredField(fieldName);
			newField.setAccessible(true);
			newField.set(newObject, value);
		}
		
		//父类属性
		oldFields = oldClass.getSuperclass().getDeclaredFields();
		
		for(Field f : oldFields){
			//类中的属性名称
			String fieldName = f.getName();
			//通过属性名称获取属性
			oldField = newClass.getSuperclass().getDeclaredField(fieldName);
			//获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
			//值为 false 则指示反射的对象应该实施 Java 语言访问检查。 
			oldField.setAccessible(true);
			//根据属性获取对象上的值
			Object value = oldField.get(oldObject);
			//过滤空的属性或者一些默认值
			if (value == null || "".equals(value)) {
				continue;
			}
			newField = newClass.getSuperclass().getDeclaredField(fieldName);
			newField.setAccessible(true);
			newField.set(newObject, value);
		}
	}*/
}
