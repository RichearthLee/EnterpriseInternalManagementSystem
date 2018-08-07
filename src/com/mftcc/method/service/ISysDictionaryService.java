package com.mftcc.method.service;

import java.util.List;

import com.mftcc.method.bean.SysDictionary;

/**
 * @author shensh
 * @date 2018年4月12日  
 * @version 1.0
 */
public interface ISysDictionaryService {

	public List<SysDictionary> selectAllSysDictionary(SysDictionary sysDictionary);
}
