package com.mftcc.interior.report.filter.bo.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Service;

import com.core.domain.screen.OptionsList;
import com.mftcc.interior.report.filter.bean.ParmDic;
import com.mftcc.interior.report.filter.bean.PmsUserFilter;
import com.mftcc.interior.report.filter.bo.PmsUserFilterBo;
import com.mftcc.interior.report.filter.dao.PmsUserFilterDao;
import com.mftcc.interior.report.filter.dao.SysInitScreenDao;

/*import app.base.ServiceException;
import app.component.nmd.entity.ParmDic;
import app.component.pms.bo.PmsUserFilterBo;
import app.component.pms.dao.PmsUserFilterDao;
import app.component.pms.entity.PmsUserFilter;
import app.component.sys.dao.SysInitScreenDao;*/
//@Service
public class PmsUserFilterBoImpl implements PmsUserFilterBo {
	public PmsUserFilterDao pmsUserFilterDao;
	public SysInitScreenDao sysInitScreenDao;

	/* 
	 * @see app.component.pms.bo.PmsUserFilterBo#delete(app.component.pms.entity.PmsUserFilter)
	 */
	@Override
	public void delete(PmsUserFilter pmsUserFilter) throws ServiceException {
		// TODO Auto-generated method stub
		pmsUserFilterDao.delete(pmsUserFilter);
	}

	/* 
	 * @see app.component.pms.bo.PmsUserFilterBo#update(app.component.pms.entity.PmsUserFilter)
	 */
	@Override
	public void update(PmsUserFilter pmsUserFilter) throws ServiceException {
		pmsUserFilterDao.update(pmsUserFilter);
	}

	/* 
	 * @see app.component.pms.bo.PmsUserFilterBo#insert(app.component.pms.entity.PmsUserFilter)
	 */
	@Override
	public void insert(PmsUserFilter pmsUserFilter) throws ServiceException {
		pmsUserFilterDao.insert(pmsUserFilter);
	}

	/* 
	 * @see app.component.pms.bo.PmsUserFilterBo#getById(app.component.pms.entity.PmsUserFilter)
	 */
	@Override
	public PmsUserFilter getById(PmsUserFilter pmsUserFilter)
			throws ServiceException {
		pmsUserFilter = pmsUserFilterDao.getById(pmsUserFilter);
		return pmsUserFilter;
	}
	@Override
	public List<PmsUserFilter> findByList(PmsUserFilter pmsUserFilter)throws ServiceException {
		List<PmsUserFilter> list = pmsUserFilterDao.findByList(pmsUserFilter);
		return list;
	}
	@Override
	public List<PmsUserFilter> getTableName()throws ServiceException {
		List<PmsUserFilter> list = pmsUserFilterDao.getTableName();
		return list;
	}
	@Override
	public List<PmsUserFilter> getTableColumn(String filterName)throws ServiceException {
		List<PmsUserFilter> list = pmsUserFilterDao.getTableColumn(filterName);
		return list;
	}
	@Override
	public List<PmsUserFilter> getParmDic(String filterName)throws ServiceException {
		List<PmsUserFilter> list = pmsUserFilterDao.getParmDic(filterName);
		return list;
	}
	
	@Override
	public List<ParmDic> getCacheSelectFromDB(String filterName,String methodName) throws ServiceException {
		List<OptionsList> list = null;
		List<ParmDic> resultList = new ArrayList<ParmDic>();
		try {
			//反射调方法
			Method method = sysInitScreenDao.getClass().getMethod(methodName, null);
			list = (List<OptionsList>) method.invoke(sysInitScreenDao, null);
			for(OptionsList opt:list) {
				ParmDic parmDic = new ParmDic();
				parmDic.setKeyName(filterName);
				parmDic.setOptCode(opt.getOptionValue());
				parmDic.setOptName(opt.getOptionLabel());
				resultList.add(parmDic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return resultList;
	}
	
	public PmsUserFilterDao getPmsUserFilterDao() {
		return pmsUserFilterDao;
	}

	public void setPmsUserFilterDao(PmsUserFilterDao pmsUserFilterDao) {
		this.pmsUserFilterDao = pmsUserFilterDao;
	}

	public SysInitScreenDao getSysInitScreenDao() {
		return sysInitScreenDao;
	}

	public void setSysInitScreenDao(SysInitScreenDao sysInitScreenDao) {
		this.sysInitScreenDao = sysInitScreenDao;
	}

}
