package com.mftcc.method.dao;

import java.util.List;

import com.mftcc.method.bean.SysDictionary;

/**
 * @author shensh
 * @date 2018年4月12日  
 * @version 1.0
 * @category 数据字典
 */ 
public interface ISysDictionaryDao {
	public List<SysDictionary> selectAllSysDictionary(SysDictionary sysDictionary);

}
