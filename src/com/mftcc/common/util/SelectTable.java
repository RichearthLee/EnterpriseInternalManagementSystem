package com.mftcc.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.method.service.IMethodService;

/**
 * @author shensh
 * @date 2018年4月26日  
 * @version 1.0
 */
public class SelectTable {
	@Autowired
	private IMethodService iMethodService;
	
	//  查询 编号最大值  并 加一  
	@ResponseBody
	public int getTableMaxNo(String tableName, String columnName) throws Exception{
		Object string =   iMethodService.getTableMaxNo(tableName, columnName);
		  System.out.println(string);
		int brno =Integer.parseInt((String) string);
		   brno++;
		return brno; 
	}
}
