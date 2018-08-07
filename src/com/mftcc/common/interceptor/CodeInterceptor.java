package com.mftcc.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

	/*
	 * 拦截器
	 * 
	 */
public class CodeInterceptor implements HandlerInterceptor {
	protected final Log log = LogFactory.getLog(CodeInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView modelAndView) throws Exception {
//		Object errorCode =  modelAndView.getModel().get(SystemParm.ERROR_CODE); 
//		if(errorCode==null){
//			modelAndView.getModel().put(SystemParm.ERROR_CODE, "111111");
//		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		return true;
	}

}
