package com.mftcc.interior.sys.control;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mftcc.common.util.SelectTable;
import com.mftcc.interior.sys.bean.SysMenu;
import com.mftcc.interior.sys.bean.SysOrg;
import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.service.ISysMenuService;
import com.mftcc.interior.sys.service.ISysRoleService;
import com.mftcc.method.bean.Ipage;
import com.mftcc.method.service.IMethodService;

/**
 * @author shensh
 * @date 2018年4月17日  
 * @version 1.0
 */
@Controller
public class SysRoleCotroller {
	@Autowired
	private ISysMenuService iSysMenuService;
	@Autowired
	private ISysRoleService iSysRoleService;
	@Autowired
	private IMethodService iMethodService;
	@RequestMapping(value = {"/sysrole"})
	public String SysRolePage(  ) {
		 return "sys/sysrole";
	}
	/*
	 *  查询所有的菜单树
	 */
	@RequestMapping(value = {"/sysroletree"})
	@ResponseBody
	public String GetSysRoleAll(){
		List<SysMenu> sysMenuList = iSysMenuService.getSysMenuListAll();
		List<Map<String, Object>> sysMenuMapList = new ArrayList<Map<String, Object>>(); // 创建primaplist用来放权限与list信息
		for (int i = 0; i < sysMenuList.size(); i++) {
			Map<String, Object> sysMenuMap = new HashMap<String, Object>(); // 创建map用来放权限信息
			sysMenuMap.put("id", sysMenuList.get(i).getMenuNo());
			sysMenuMap.put("name", sysMenuList.get(i).getMenuName());
			sysMenuMap.put("pId", sysMenuList.get(i).getMenuParent());
			// 树结构图标的显示
			if (sysMenuList.get(i).getMenuLev().equals("0")){
				sysMenuMap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/menutree2.png");
			}
			if (sysMenuList.get(i).getMenuLev().equals("1")){
				sysMenuMap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/menutree1.png");
			}
			
			sysMenuMapList.add(sysMenuMap);
		}
		
		Gson ggson = new Gson();
		String strrList = ggson.toJson(sysMenuMapList);
		return strrList;
	}
	
	@RequestMapping("/getSysRolePage")
	@ResponseBody
	public Map<String,Object> GetSysRoleAllPage(Ipage ipage ,ModelMap model){
		 Map<String, Object> dataMap = new HashMap<String,Object>();
		ipage = iSysRoleService.getSysRolePage(ipage);
		dataMap.put("ipage", ipage);
		return dataMap;
	}
	
	@RequestMapping("/getSysMenuBySysRole")
	@ResponseBody
	public String  GetSysMenuBySysRole(String roleNo){
		List<SysMenu> sysMenuList =	 iSysMenuService.getSysMenuListBySysRole(roleNo);
		List<Map<String, Object>> sysMenuMapList = new ArrayList<Map<String, Object>>();  

		for (int i = 0; i < sysMenuList.size(); i++) {
			Map<String, Object> sysMenuMap = new HashMap<String, Object>(); // 创建map用来放权限信息
			sysMenuMap.put("id", sysMenuList.get(i).getMenuNo());
			sysMenuMap.put("name", sysMenuList.get(i).getMenuName());
			sysMenuMap.put("pId", sysMenuList.get(i).getMenuParent());
			// 树结构图标的显示
			if (sysMenuList.get(i).getMenuLev().equals("0")){
				sysMenuMap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/menutree2.png");
			}
			if (sysMenuList.get(i).getMenuLev().equals("1")){
				sysMenuMap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/menutree1.png");
			}
			sysMenuMapList.add(sysMenuMap);
		}
		
		Gson ggson = new Gson();
		String strrList = ggson.toJson(sysMenuMapList);
		
		return strrList;
	}
	
	
	@RequestMapping(value = {"/insertSysRole"})
	@ResponseBody
	public Map<String,Object> insertSysRole(String roleName) throws Exception{
		
		Map<String,Object> datamap=new HashMap<String,Object>();
		SysRole sysRole = new SysRole();
		try{
		// 获取最大编号并加一
		Object string =  iMethodService.getTableMaxNo("ac_sys_role","role_no");
		int role_no =Integer.parseInt((String) string);
		 role_no++;
		 DecimalFormat df=new DecimalFormat("000");  // 不足三位字典补零
		sysRole.setRoleNo(df.format(role_no));
		sysRole.setRoleName(roleName);
		sysRole.setRoleSts("1");
			iSysRoleService.insertSysRole(sysRole);
			datamap.put("flag", "true");
		}
		catch(Exception e){
			datamap.put("flag", "false");
		}
		return datamap;
	}
}
