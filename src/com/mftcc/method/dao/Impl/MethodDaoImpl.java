package com.mftcc.method.dao.Impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mftcc.common.DAOException;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.method.dao.IMethodDao;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class MethodDaoImpl extends BaseDao implements IMethodDao{

	@Override
	public String getTableMaxNo( String tableName, String columnName) throws DAOException {
		// TODO Auto-generated method stub
		
		Map<String, String> param = new HashMap<String, String>();  
		param.put("tableName", tableName);  
		param.put("columnName", columnName);  
		//(String) getSqlMapClientTemplate().queryForObject("getTableMaxNo",param)
		return (String) getSqlMapClientTemplate().queryForObject("getTableMaxNo",param);
	}

	/* (non-Javadoc)
	 * @see com.mftcc.method.dao.IMethodDao#getTableNameByNo(java.util.Map)
	 */
	@Override
	public String getTableNameByNo(Map<String, String> map) throws DAOException {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<String, String>();  
		param.put("tableName", map.get("tableName"));   // 表名
		param.put("columnName", map.get("columnName"));  // 查询的列名
		param.put("columnNo", map.get("columnNo"));  	//  查询条件 no 名
		param.put("columnNoValue", map.get("columnNoValue"));  //  查询条件 no 值
		//(String) getSqlMapClientTemplate().queryForObject("getTableMaxNo",param)
		return (String) getSqlMapClientTemplate().queryForObject("getTableNameByNo",param);
	}  
	
}
