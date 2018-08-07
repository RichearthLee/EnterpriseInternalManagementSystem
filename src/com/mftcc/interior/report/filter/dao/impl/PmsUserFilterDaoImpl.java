package com.mftcc.interior.report.filter.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.DAOException;
import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.report.filter.bean.PmsUserFilter;
import com.mftcc.interior.report.filter.dao.PmsUserFilterDao;
/*@SuppressWarnings("deprecation")
@Repository*/
public class PmsUserFilterDaoImpl extends BaseDao implements PmsUserFilterDao{

	@Override
	public void delete(PmsUserFilter pmsUserFilter) throws DAOException {
		//getSqlMapClientTemplate().delete("delete", pmsUserFilter);
		
	}

	@Override
	public void update(PmsUserFilter pmsUserFilter) throws DAOException {
		//getSqlMapClientTemplate().update("update", pmsUserFilter);
	}

	@Override
	public void insert(PmsUserFilter pmsUserFilter) throws DAOException {
		//getSqlMapClientTemplate().insert("insert", pmsUserFilter);
	}

	@Override
	public PmsUserFilter getById(PmsUserFilter pmsUserFilter) throws DAOException {
		return null;//(PmsUserFilter) getSqlMapClientTemplate().queryForList("getById",pmsUserFilter);
	}

	@Override
	public List<PmsUserFilter> findByList(PmsUserFilter pmsUserFilter) throws DAOException {
		return null;//getSqlMapClientTemplate().queryForList("findByList",pmsUserFilter);
	}

	@Override
	public List<PmsUserFilter> getTableName() throws DAOException {
		return getSqlMapClientTemplate().queryForList("getTableName");
	}

	@Override
	public List<PmsUserFilter> getTableColumn(String filterName) throws DAOException {
		return getSqlMapClientTemplate().queryForList("getTableColumn", filterName);
	}

	@Override
	public List<PmsUserFilter> getParmDic(String filterName) throws DAOException {
		return getSqlMapClientTemplate().queryForList("getParmDic", filterName );
	}

}
