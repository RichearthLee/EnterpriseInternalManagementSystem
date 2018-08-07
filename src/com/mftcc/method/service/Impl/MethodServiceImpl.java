package com.mftcc.method.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.DAOException;
import com.mftcc.method.bean.methodBean;
import com.mftcc.method.dao.IMethodDao;
import com.mftcc.method.service.IMethodService;
import com.mftcc.method.bean.Ipage;
@Service
public class MethodServiceImpl implements IMethodService {
	
	private static final Log log = LogFactory.getLog(MethodServiceImpl.class);

	@Autowired
	private IMethodDao iMethodDao;
	
	@Override
	public String getTableMaxNo(String tableName, String columnName)throws Exception {
		return iMethodDao.getTableMaxNo(tableName, columnName);
	}

	/* (non-Javadoc)
	 * @see com.mftcc.method.service.IMethodService#getTableNameByNo(java.util.Map)
	 */
	@Override
	public String getTableNameByNo(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return iMethodDao.getTableNameByNo(map);
	}
	 
}
