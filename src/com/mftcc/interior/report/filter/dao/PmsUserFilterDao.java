package com.mftcc.interior.report.filter.dao;

import java.util.List;

import com.mftcc.common.DAOException;
import com.mftcc.interior.report.filter.bean.PmsUserFilter;



public interface PmsUserFilterDao {
	
	public void delete(PmsUserFilter pmsUserFilter) throws DAOException;
	
	public void update(PmsUserFilter pmsUserFilter) throws DAOException;
	
	public void insert(PmsUserFilter pmsUserFilter) throws DAOException;
	
	public PmsUserFilter getById(PmsUserFilter pmsUserFilter) throws DAOException;
	
	public List<PmsUserFilter> findByList(PmsUserFilter pmsUserFilter) throws DAOException;
	
	public List<PmsUserFilter> getTableName() throws DAOException;
	
	public List<PmsUserFilter> getTableColumn(String filterName) throws DAOException;
	
	public List<PmsUserFilter> getParmDic(String filterName) throws DAOException;
	
}
