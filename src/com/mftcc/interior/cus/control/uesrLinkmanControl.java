package com.mftcc.interior.cus.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.interior.cus.bean.UserLinkman;
import com.mftcc.interior.cus.service.UserLinkService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;

@Controller
public class uesrLinkmanControl {

	@Autowired
	private UserLinkService userLinkService;
	@Autowired
	private ISysUserService  iSysUserService;
	@RequestMapping(value = {"/selectUserLink"})
	@ResponseBody
	public List<UserLinkman> selectUserLink(String userId){
		List<UserLinkman> list=new ArrayList<UserLinkman>();
		try{
			list=userLinkService.selectUserLink(userId);
		}
		catch(Exception e){
			
		}
		return list;
	}
	@RequestMapping(value = {"/insertUserLink"})
	@ResponseBody
	public Map<String,Object> insertUserLink(UserLinkman userLink){
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("flag","success");
		try{
			userLinkService.insertUserLink(userLink);
		}
		catch(Exception e){
			datamap.put("flag", "failed");
		}
		return datamap;
	}
	@RequestMapping(value = {"/searchLinkByMN"})
	@ResponseBody
	public List<UserLinkman> searchLinkByMN(UserLinkman userLink){
		List<UserLinkman> list=new ArrayList<UserLinkman>();
		try{
			list=userLinkService.searchLinkByMN(userLink);
		}
		catch(Exception e){
			
		}
		System.out.println(list.size());
		return list;
	}
	@RequestMapping(value = {"/getAllUser"})
	@ResponseBody
	public List<SysUser> selectAllUser(SysUser user){
		List<SysUser> list=new ArrayList<SysUser>();
		try{
			list=userLinkService.selectUser(user);
		}
		catch(Exception e){
			
		}
		System.out.println(list.size());
		return list;
	}
	@RequestMapping(value = {"/modifyPassword"})
	@ResponseBody
	public Map<String,Object> modifyPassword(SysUser user,String newPswd){
		Map<String,Object> map=new HashMap<String,Object>();
		SysUser paramBean=new SysUser();
		paramBean.setOpNo(user.getOpNo());
		SysUser sysuser = iSysUserService.getSysUser(paramBean);
		if(sysuser.getPassword().equals(user.getPassword())){
			map.put("flag","success");
			//这里应该有修改密码
		}
		else{
			map.put("flag", "fail");
		}
		return map;
	}
}
