package com.mftcc.method.service;

import java.util.List;
import java.util.Map;

import com.mftcc.common.DAOException;
import com.mftcc.method.bean.methodBean;
import com.mftcc.method.bean.Ipage;

public interface IMethodService {
	 
	public String getTableMaxNo( String tableName, String columnName)  throws Exception;
	
	public String getTableNameByNo(Map<String, String> map)  throws Exception;

}
