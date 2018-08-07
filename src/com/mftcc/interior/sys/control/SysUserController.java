package com.mftcc.interior.sys.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;
import com.mftcc.method.bean.Ipage;


@Controller
public class SysUserController {
	
	@Autowired
	private ISysUserService iSysUserService;
		/*	@Autowired
			private SysOrgService sysOrgService;
			*/
	@RequestMapping(value = {"/sysuser"})
	public String OrgPage(){
		return "sys/sysuser";
	}
	
	@RequestMapping("/getSysUserPage")
	public void getUserListPage(Ipage ipage ,ModelMap model, HttpSession session) throws Exception{
		ipage = iSysUserService.getSysUserInfoPage(ipage);
		model.put("ipage", ipage);
	}
	
	 //  插入用户
	@RequestMapping(value = {"/insertSysUser"})
	@ResponseBody
	public Map<String,Object> insertSysUser(SysUser sysUser) throws Exception{
		
		Map<String,Object> datamap=new HashMap<String,Object>();
		
		try{
			Boolean boo = iSysUserService.insertSysUser(sysUser);
			if(boo==true){
			datamap.put("flag", "true");
			}
			datamap.put("flag", "false");
		}
		catch(Exception e){
			datamap.put("flag", "false");
		}
		return datamap;
	}
	
	@RequestMapping(value = {"/getSysUseropNo"})
	@ResponseBody
	public Map<String,Object> getSysUseropNo(String opNo) throws Exception{
		Map<String,Object> datamap=new HashMap<String,Object>();
		
		try{
			SysUser sysUser = new SysUser();
			 sysUser.setOpNo(opNo);
			 int size = iSysUserService.getAllSysUser(sysUser).size();
				if(size!=0){
					datamap.put("data","true");
					}else {
						datamap.put("data","false");
					}
			datamap.put("flag", "true");
		}
		catch(Exception e){
			datamap.put("flag", "false");
		}
		return datamap;
	}
	
	
}
