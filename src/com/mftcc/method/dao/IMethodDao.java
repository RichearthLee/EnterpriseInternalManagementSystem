package com.mftcc.method.dao;

import java.util.Map;

import com.mftcc.common.DAOException;



public interface IMethodDao {
	
	public String getTableMaxNo( String tableName, String columnName)  throws DAOException;

	public String getTableNameByNo(Map<String, String> map)  throws DAOException;

}
