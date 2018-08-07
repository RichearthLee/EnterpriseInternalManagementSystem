package com.mftcc.method.control;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.mftcc.method.service.IMethodService;

@Controller
public class MethodController {
	private static final Comparator Date = null;
	protected final Log log = LogFactory.getLog(MethodController.class);
	 // 注入业务层
	 @Autowired
	private IMethodService methodService;
	
	 //测试获取数据
   
}
