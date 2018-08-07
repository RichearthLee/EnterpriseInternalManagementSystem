package com.mftcc.method.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.method.bean.SysDictionary;
import com.mftcc.method.bean.methodBean;
import com.mftcc.method.dao.ISysDictionaryDao;

/**
 * @author shensh
 * @date 2018年4月12日  
 * @version 1.0
 */
@Repository
public class SysDictionaryDaoImpl extends BaseDao implements ISysDictionaryDao {

	 
	@Override
	public List<SysDictionary> selectAllSysDictionary(SysDictionary sysDictionary) {
		// TODO Auto-generated method stub
		List<SysDictionary> list=(List<SysDictionary>)getSqlMapClientTemplate().queryForList("selectAllSysDictionary",sysDictionary);
		 
		return list;
	}

}
