/**
 * @author LiuYF
 * @date 2015-11-11 上午9:08:29
 * @version V1.0
 */ 
package com.mftcc.common.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.sys.bean.SysUser;

/**
 * 类名： MyFilter <br>
 */
public class MyFilter implements Filter{
	
	/**
	 * 过滤器中允许直接通过的项的正则表达式。
	 */
	private Pattern filterPattern;

	@Override
	public void destroy() {
		filterPattern = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest) request;
		String servletPath = hsRequest.getServletPath();

		if (!filterPattern.matcher(servletPath).matches()) {
			// System.out.println("**sessionFilter-servletPath:" + servletPath);
			// 验证是否登录用户。
			HttpServletResponse hsResponse = (HttpServletResponse) response;
			HttpSession session = hsRequest.getSession();
			SysUser sysuser = (SysUser) session.getAttribute("sysuser");
			System.out.println("---->>>>请求URL "+hsRequest.getRequestURI());
		//	System.out.println("---->>>>登录用户： "+sysuser);
			if(!hsRequest.getRequestURI().equals("/excuteWarnSms")){
				if (sysuser == null || "0".equals(sysuser.getOpSts())) {
					// 用户未登录 或 已禁用。
					this.redirectTo(hsRequest.getContextPath(), hsResponse);
					return;
				} else {
					// TODO 此处可进一步验证用户是否正确。
				}
			}
		}
        //这里表示是正确的，也就是说，他回去找下一个链，但是它下面已经没有了，所以就会去跳转页面了，此跳转的页面就是action="output.jsp"了
		chain.doFilter(request, response);   
	}

	// 初始化
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		StringBuffer regexBuffer = new StringBuffer();
		regexBuffer.append("/index\\.jsp");
		regexBuffer.append("|").append("/logout");
		regexBuffer.append("|").append("/checkLogin.*");
		regexBuffer.append("|").append("/AdminLTE/.*");
		regexBuffer.append("|").append("/bootstrap/.*");
		regexBuffer.append("|").append("/js/.*");
		regexBuffer.append("|").append("/plugins/.*");
		regexBuffer.append("|").append("/ionicons-master/.*");
		regexBuffer.append("|").append("/favicon.ico");
		regexBuffer.append("|").append("/hessian");
		regexBuffer.append("|").append("/addBackUp");
		
		filterPattern = Pattern.compile(regexBuffer.toString());
		
		System.out.println("过滤器正则初始化完成！" + filterPattern);
	}
	
	private void redirectTo(String href, HttpServletResponse response) throws IOException {
		String script = "<script>alert('您长时间未操作，为确保安全，请重新登录。'); "
				+ "window.top.location.href='" + StringUtil.killEmpty(href,"/") + "';</script>";
		System.out.println("session过期，重定向到：" + StringUtil.killEmpty(href,"/"));
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(script.getBytes("UTF-8"));
		response.flushBuffer();
	}

}


	/*
			生命周期：init()->doFilter()->destroy()
			
			
	*/