package com.mftcc.interior.sys.control;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysMenuService;
import com.mftcc.interior.sys.service.ISysOrgService;
import com.mftcc.interior.sys.service.ISysUserService;
import com.mftcc.method.bean.Ipage;
import com.mftcc.method.service.IMethodService;

/**
 * @author shensh
 * @date 2018年3月13日  
 * @version 1.0
 */
@Controller
public class SysOrgController {

	@Autowired
	private ISysOrgService iSysOrgService;
	@Autowired
	private ISysMenuService iSysMenuService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IMethodService iMethodService;
	@RequestMapping(value = {"/sysorg"})
	public String SysOrgPage(HttpSession  session ) throws ServiceException{
		
			List<Map<String, Object>> sysorgmapList = new ArrayList<Map<String, Object>>(); // 创建primaplist用来放权限与list信息
			List<SysOrg> sysorgList = iSysOrgService.getSysOrgAll();

			for (int i = 0; i < sysorgList.size(); i++) {
				Map<String, Object> sysorgmap = new LinkedHashMap<String, Object>(); // 创建map用来放权限信息
				sysorgmap.put("id", sysorgList.get(i).getBrNo());
				sysorgmap.put("name", sysorgList.get(i).getBrName());
				sysorgmap.put("pId", sysorgList.get(i).getUpOne());
				sysorgmap.put("uid", sysorgList.get(i).getBrNo());
				// 树结构图标的显示
				if (sysorgList.get(i).getUpOne().equals("-1")){
					sysorgmap.put("open", true);
					sysorgmap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/org.png");
				}
				if (sysorgList.get(i).getBrType().equals("028")){  // 分公司
					sysorgmap.put("open", true);

					sysorgmap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/1_close.png");
				}
				if (sysorgList.get(i).getBrType().equals("029")){ // 办事处
					sysorgmap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/1_open.png");
				}
				if (sysorgList.get(i).getBrType().equals("030")){ // 部门
					sysorgmap.put("icon", "/mftccManager/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png");
				}
					
				sysorgmapList.add(sysorgmap);
			}
			System.out.println("部门树："+sysorgmapList);
			Gson ggson = new Gson();
			String strrList = ggson.toJson(sysorgmapList);
			session.setAttribute("sysorgjson", strrList);
			
		 return "sys/sysorg";
	}

	public void getUserListPage(Ipage ipage ,ModelMap model, HttpSession session) throws Exception{
	
		ipage = iSysUserService.getSysUserInfoPage(ipage);
		model.put("ipage", ipage);
	}
	
	//  添加用户  返回页面
	@RequestMapping(value = {"/addSysUser"})
	public String addSysUser() throws Exception{
		return "sys/sysuseradd";
	}
	
	//  添加部门  返回页面
	@RequestMapping(value = {"/addSysOrg"})
	public String addSysOrg() throws Exception{
		return "sys/sysorgadd";
	}

	//  查询部门编号最大值 
	public int getSysUseropNo() throws Exception{
		   int brno =Integer.parseInt(iSysOrgService.getMaxBrNo());
		   brno++;
		return brno; 
	}
	
	
	@RequestMapping(value = {"/insertSysOrg"})
	@ResponseBody
	public Map<String,Object> insertSysOrg(SysOrg sysOrg) throws Exception{
		
		Map<String,Object> datamap=new HashMap<String,Object>();
		SelectTable st = new SelectTable();
		 // 获取最大编号并加一
		String string =  iMethodService.getTableMaxNo("ac_sys_org","br_no");
		int brno =Integer.parseInt(string);
		brno++;
		DecimalFormat df=new DecimalFormat("00");
		sysOrg.setBrNo(df.format(brno));
		try{
			sysOrg.setBrSts("1");
			iSysOrgService.insertSysOrg(sysOrg);
			datamap.put("flag", "true");
		}
		catch(Exception e){
			datamap.put("flag", "false");
		}
		return datamap;
	}
	
}
