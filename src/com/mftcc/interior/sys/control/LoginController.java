package com.mftcc.interior.sys.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.sys.bean.SysMenu;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysMenuService;
import com.mftcc.interior.sys.service.ISysUserService;

/**
 * @author shensh
 * @date 2018年3月17日  
 * @version 1.0
 */
@Controller
public class LoginController {

	@Autowired
	private ISysUserService  iSysUserService;
	@Autowired
	private ISysMenuService iSysMenuService;
	// 用户登录
	@RequestMapping(value={"/checkLogin"})
	@ResponseBody
	public Map<String,Object> checkLogin(String opNo, String password, ModelMap modelMap, HttpSession session) {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		try {
			
			if (StringUtil.isEmpty(opNo) || StringUtil.isEmpty(password)) {
				dataMap.put("msg", "用户名和密码不能为空");
				throw new Exception();
			}
			SysUser paramBean = new SysUser();
			paramBean.setOpNo(opNo);
			
			SysUser sysuser = iSysUserService.getSysUser(paramBean);
			String sysUserparam ;
			
			if (sysuser == null) {
				paramBean.setOpNo(null);
				paramBean.setMobile(opNo);     // 通过手机号登录
				sysuser = iSysUserService.getSysUser(paramBean);
				System.out.println("手机号登录"+sysuser);
				if (sysuser == null) {
					dataMap.put("msg", "用户不存在");
					throw new Exception();
				}
				sysUserparam = sysuser.getMobile(); 
				} else{
				sysUserparam =sysuser.getOpNo(); 
				System.out.println("用户名登录"+sysuser);
			 }
			System.out.println(sysuser.getPassword());
			System.out.println(sysuser.getPassword());
		/*	if (sysuser.getPassword().trim().isEmpty()) {  // trim去两边空格的方法
				dataMap.put("msg", "用户密码失效，不允许登录");
				throw new Exception();
			}*/
			if (sysuser.getPassword()==null||sysuser.getPassword().equals("")) {   
				dataMap.put("msg", "用户密码失效，不允许登录");
				throw new Exception();
			}
			if (!"1".equals(sysuser.getOpSts())) {
				dataMap.put("msg", "用户被禁用");
				throw new Exception();
			}
			// 严重账号或者手机号 密码 登录。
			if (opNo.equals(sysUserparam) && password.equals(sysuser.getPassword())) {
				session.setAttribute("sysuser", sysuser);
			} else {
				dataMap.put("msg", "登录密码错误");
				throw new Exception();
			}
			
			dataMap.put("flag", "success");
		} catch (Exception e) {
			dataMap.put("flag", "fail");
		}
		return dataMap;
	}
	
	
	/**
	 * 方法描述： 登录成功后进入主页面。
	 */
	@RequestMapping("/main")
	public String logined(ModelMap modelMap, HttpSession session) {
		// 主界面，需要加载用户个人信息、系统基础信息、菜单等。
		SysUser sysuser = (SysUser) session.getAttribute("sysuser");
		if (sysuser != null) {
			Map<String, List<SysMenu>> menuMap = iSysMenuService.getSysMenuMapByUser(sysuser);
			modelMap.put("menuMap", menuMap);
		}
		
		return "starter";
	}
	/**
	 * 方法描述： 登出账户，清空session中的标识。
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		/*session.removeAttribute("sysuser");
		session.invalidate();*/
		return "logout";
	}
	
	  @RequestMapping("/sysWarningView")
		public String warningView(ModelMap model,HttpSession session){
			/*SysUser user = (SysUser)session.getAttribute("user");
			Ipage ipage = new Ipage();
			String payProgressCount = "";
			String pactInvoiceCount = "";
			String pactExecuteCount = "";
			String pactCount = "";
			//003是财务
			if(user == null){
				
			}else{
				try{
					if(user.getRoles().contains("999")){
						payProgressCount = payProgressService.getPayProgressUnconfirm(ipage);
						pactInvoiceCount = pactInvoiceService.getInvoiceUnconfirm(ipage);
						pactExecuteCount = pactInfoService.getPactExecuteUnconfirm(ipage);
						pactCount = pactInfoService.getPact(ipage);
					}else if(user.getRoles().contains("004")){
						pactExecuteCount = pactInfoService.getPactExecuteUnconfirm(ipage);
					}else if(user.getRoles().contains("003")){
						payProgressCount = payProgressService.getPayProgressUnconfirm(ipage);
						pactInvoiceCount = pactInvoiceService.getInvoiceUnconfirm(ipage);
					}else if(user.getRoles().contains("002")){
						pactCount = pactInfoService.getPact(ipage);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			model.put("payProgressCount", payProgressCount);
			model.put("pactInvoiceCount", pactInvoiceCount);
			model.put("pactExecuteCount", pactExecuteCount);
			model.put("pactCount", pactCount);*/
			return "warning";
		}
	/*@RequestMapping("/getAllUser")
	public String getAllUser(ModelMap modelMap) {
		List<UserBean> userList = usersService.getAllUser();
		modelMap.put("userList", userList);
		return "pactManage/userSelect";
	}
	
	@RequestMapping("/UpdateUserPasswordView")
	public String getUpdateUserView(ModelMap modelMap, HttpSession session)
	{
		
		return "pactManage/userPasswordUpdate";
	}
	
	*//**
	 * 方法描述： 修改用户密码。
	 * @param oldpassword
	 * @param newpassword
	 * @param modelMap 0:失败 1：成功 2：验证不通过
	 * @param session
	 * @void
	 * @date 2015-12-8 上午11:02:17
	 *//*
	@RequestMapping("/UpdateUserPassword")
	public void updateUserPassword(String oldpassword ,String newpassword , ModelMap modelMap,HttpSession session){
		if (oldpassword.trim().isEmpty() || newpassword.trim().isEmpty()) {
			modelMap.put(SystemParm.ERROR_CODE, "2");
			return;
		}
		UserBean sessionUser = (UserBean) session.getAttribute("user");
		UserBean oriUser = usersService.getUser(sessionUser.getUserNo());
		String md5Hex = DigestUtils.md5Hex(oldpassword);
		if( !md5Hex.equals(oriUser.getPassword()))
		{
			//输入的旧密码验证不通过
			modelMap.put(SystemParm.ERROR_CODE, "2");
		} else {
			try {
				oriUser.setPassword(DigestUtils.md5Hex(newpassword));
				boolean result = usersService.updateUser(oriUser);
				if (result)
				{	//成功
					modelMap.put(SystemParm.ERROR_CODE, "1");
					session.setAttribute("user", oriUser);
				} else {//设置新密码失败
					modelMap.put(SystemParm.ERROR_CODE, "0");
				}

			} catch (Exception e) {
				modelMap.put(SystemParm.ERROR_CODE, "0");
			}
		}
	}
	*/

}
