package com.mftcc.method.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.method.bean.SysDictionary;
import com.mftcc.method.dao.ISysDictionaryDao;
import com.mftcc.method.service.ISysDictionaryService;

/**
 * @author shensh
 * @date 2018年4月12日  
 * @version 1.0
 */
@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService {
	@Autowired
	private ISysDictionaryDao iSysDictionary;

	/* (non-Javadoc)
	 * @see com.mftcc.method.service.ISysDictionaryService#selectAllSysDictionary(com.mftcc.method.bean.SysDictionary)
	 */
	@Override
	public List<SysDictionary> selectAllSysDictionary( SysDictionary sysDictionary) {
		// TODO Auto-generated method stub
		return iSysDictionary.selectAllSysDictionary(sysDictionary);
	}
	
}
