package com.mftcc.interior.report.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
import com.mftcc.interior.report.common.EntityUtil;
import com.mftcc.method.bean.Ipage;

public abstract class BaseDomain implements Serializable{

	private int startNum;
	private int endNum;
	
	
	public int getStartNum() {
		return startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	
	public void setStartNumAndEndNum(Ipage ipg) {
		this.startNum = ipg.getStartRow();
		this.endNum = ipg.getOldEndNum();
		
	}
	/**
	 * 自定义查询
	 */
	private List<String> customQuery;
	/**
	 * 自定义排序（排序数据库级别）
	 */
/*	private List<CustomSort> customSorts;
	public List<CustomSort> getCustomSorts() {
		return customSorts;
	}
	private List<List<Criteria>> criteriaLists;
	public void setCriteriaLists(List<List<Criteria>> criteriaLists) {
		this.criteriaLists=criteriaLists;
	}	
	public List<List<Criteria>> getCriteriaLists() {
		return criteriaLists;
	}*/
	/**
	 * setCriteriaList Set方法
	 * @param entitys 实体类集合
	 * @param jsonEntitys json对象
	 */
	/*@SuppressWarnings("unchecked")
	public void setCriteriaList(Object entity,String ajaxData) {
		 List<List<Criteria>> newCriteriaList = new ArrayList<List<Criteria>>();
		 JSONArray jsonarray = JSONArray.fromObject(ajaxData);
		 for(Object objArray:jsonarray.toArray()){
			 JSONArray objJsonArray = JSONArray.fromObject(objArray);
			 List<Criteria> criteriaList = (List<Criteria>)JSONArray.toList(objJsonArray, new Criteria(), new JsonConfig());
	    	 List<Criteria> outerList = new ArrayList<Criteria>();
	    	 int count = 0;
	    	 int len = criteriaList.size();
    		 for(int i = 0;i<len;i++){//for2 start
    			 Criteria jsonEntity = criteriaList.get(i);
				 Field[] fields = entity.getClass().getDeclaredFields();
				 if(jsonEntity!=null){
					 if(jsonEntity.isGroup()){
						 Criteria criteria = new Criteria();
						 if("1".endsWith(jsonEntity.getAndOr())){//0是and 1是or
							 criteria.setAndOr(" or ");
						 }else{
							 criteria.setAndOr(" and "); 
						 }
						 if(i!=0&&i==(len-1)){
							 criteria.setGroupStart(false);
							 criteria.setGroupEnd(true);
						 }
						 criteria.setGroup(true);
						 outerList.add(criteria);
						 count++;
					 }else{
						 for(Field field:fields){
							 String fieldName = field.getName();//实体类字段
							 String sqlCol = EntityUtil.changeColForSqlCol(fieldName);//sql对应字段
							 if(fieldName.equals(jsonEntity.getCondition())){
								 if(jsonEntity.isChecked()){//是否生效
									 Criteria criteria = new Criteria();
									 criteria.setGroup(false);
									 if(i==0&&i!=(len-1)){
										 criteria.setGroupStart(true);
										 criteria.setGroupEnd(false);
									 }else if(i!=0&&i==(len-1)){
										 criteria.setGroupStart(false);
										 criteria.setGroupEnd(true);
									 }else if(i==0&&i==(len-1)){
										 criteria.setGroupStart(false);
										 criteria.setGroupEnd(false);
									 }else{
										 criteria.setGroupStart(false);
										 criteria.setGroupEnd(false);
									 }
									 if(count>0){
										 if("1".endsWith(jsonEntity.getAndOr())){//0是and 1是or
											 criteria.setAndOr(" or ");
										 }else{
											 criteria.setAndOr(" and "); 
										 } 
									 }else{
										 criteria.setAndOr("");
									 }
									 if(jsonEntity.isNoValue()){//值为空
										 criteria.setNoValue(true);
										 criteria.setCondition(sqlCol+" IS NULL OR "+sqlCol+"=''");
									 }else if(jsonEntity.isSingleValue()){//单个值
										 switch (jsonEntity.getType()){//根据类型
										 	case 0://等于=
										 		sqlCol += " = "; 
										 		break;
											case 1://大于> &gt; 
												sqlCol += " > "; 
												break;
											case 11://大于等于 >=
												sqlCol += " >= "; 
												break;
											case 2://小于 < &lt;
												sqlCol += " < "; 
												break;
											case 21://大于等于 <=
												sqlCol += " <= ";
												break;
										 }
										 criteria.setSingleValue(true);
										 criteria.setCondition(sqlCol);
										 criteria.setValue(jsonEntity.getValue()); 
									 }else if(jsonEntity.isBetweenValue()){//两个值
										 criteria.setBetweenValue(true);
										 if(jsonEntity.getType()==3||jsonEntity.getType()==5){
											 criteria.setCondition(sqlCol+" between ");
										 }else if(jsonEntity.getType()==4){
											 criteria.setCondition(sqlCol+" not between ");
										 }
										 criteria.setValue(jsonEntity.getValue());
										 criteria.setSecondValue(jsonEntity.getSecondValue());
									 }else if(jsonEntity.isListValue()){//数组类型
										 criteria.setListValue(true);
										 switch (jsonEntity.getType()){//根据类型
										 	case 0://in
										 		sqlCol += " in ";
										 		break;
											case 99://not in; 
												sqlCol += " not in ";
												break;
											default://默认为in
												sqlCol += " in ";
										 }
										 criteria.setCondition(sqlCol);
										 criteria.setValue(jsonEntity.getValue().toString().split(","));
									 }else if(jsonEntity.isLikeValue()){
										 criteria.setLikeValue(true);
										 criteria.setCondition(sqlCol+" like ");
										 criteria.setValue(jsonEntity.getValue());
									 }
									 outerList.add(criteria);
									 count++;
									 break; 
								 }
							 }
						 }
					 }
					 
				 }
			 }//for2 end
	    	 if(outerList!=null&&outerList.size()>0){
	    		 newCriteriaList.add(outerList);
	    	 }
	     }//for1 end
		this.criteriaLists = newCriteriaList;
    }
	public List<String> getCustomQuery() {
		return customQuery;
	}
	public void setCustomQuery(Object ajaxData) throws Exception{
		List<String> customQueryList = new ArrayList<String>();
		JSONArray jsonarray = JSONArray.fromObject(ajaxData);
		outer:
		for(Object object:jsonarray.toArray()){
			JSONArray jsonarray2 = JSONArray.fromObject(object);
			for(Object object2:jsonarray2.toArray()){
				JSONObject jsonObject = JSONObject.fromObject(object2);
				if(jsonObject.get("customQuery")!=null){
					String value = jsonObject.get("customQuery").toString();
					for(String str:value.split(" ")){
						if(!"".equals(str)){
							customQueryList.add(str);
						}
					}
					break outer;
				}
			}
		}
		this.customQuery = customQueryList;
	}

	
	public void setCustomSorts(Object ajaxData) throws Exception{
		List<CustomSort> customSortList = new ArrayList<CustomSort>();
		JSONArray jsonarray = JSONArray.fromObject(ajaxData);
		outer:
		for(Object object:jsonarray.toArray()){
			JSONArray jsonarray2 = JSONArray.fromObject(object);
			for(Object object2:jsonarray2.toArray()){
				JSONObject jsonObject = JSONObject.fromObject(object2);
				if(jsonObject.get("customSorts")!=null){
					String value = jsonObject.get("customSorts").toString();
					JSONArray customStotsArray = JSONArray.fromObject(value);
					for(Object customObj:customStotsArray.toArray()){
						JSONObject jsonObj = JSONObject.fromObject(customObj);
						if(jsonObj!=null){
							CustomSort customSort = new CustomSort();
							customSort.setSortField(jsonObj.getString("sortField"));
							customSort.setSortType(jsonObj.getString("sortType"));
							customSortList.add(customSort);
						}
					}
					break outer;
				}
			}
		}
		this.customSorts = customSortList;
	}
	public void setCustomSorts(List<CustomSort> customSorts) {
		this.customSorts = customSorts;
	}
*/
}
