package com.mftcc.interior.report.filter.bo;

import java.util.List;

import javax.xml.rpc.ServiceException;

import com.mftcc.interior.report.filter.bean.ParmDic;
import com.mftcc.interior.report.filter.bean.PmsUserFilter;


public interface PmsUserFilterBo {
	
	public void delete(PmsUserFilter pmsUserFilter) throws ServiceException;
	
	public void update(PmsUserFilter pmsUserFilter) throws ServiceException;
	
	public void insert(PmsUserFilter pmsUserFilter) throws ServiceException;
	
	public PmsUserFilter getById(PmsUserFilter pmsUserFilter) throws ServiceException;
	
	public List<PmsUserFilter> findByList(PmsUserFilter pmsUserFilter) throws ServiceException;
	
	public List<PmsUserFilter> getTableName() throws ServiceException;
	
	public List<PmsUserFilter> getTableColumn(String filterName) throws ServiceException;
	
	public List<PmsUserFilter> getParmDic(String filterName) throws ServiceException;
	
	/**
	 * 筛选项为从库中选择
	 */
	public List<ParmDic> getCacheSelectFromDB(String filterName,String methodName) throws ServiceException;
}
