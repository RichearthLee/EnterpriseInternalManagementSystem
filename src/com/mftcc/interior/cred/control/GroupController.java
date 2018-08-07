/**
 * Copyright (C) DXHM 版权所有
 * 文件名： ServiceItemsController.java
 * 包名： com.mftcc.interior.cred.control
 * 说明：
 * @author limenghao
 * @date 2016年11月30日 上午9:23:46
 * @version V1.0
 */ 
package com.mftcc.interior.cred.control;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dingxinsoft.util.GetWaterId;
import com.mftcc.common.SystemParm;
import com.mftcc.common.util.DateUtil;
import com.mftcc.interior.cred.bean.UserBean;
import com.mftcc.interior.cred.service.GroupService;
import com.mftcc.interior.cred.util.ServiceGroupLevalComparator;
import com.mftcc.interior.cred.util.ServiceGroupsUtils;
import com.mftcc.method.bean.Ipage;


/**
 * 类名： GroupController
 * 描述：服务项管理
 * @author 李梦浩
 * @date 2016年11月30日 上午9:23:46
 *
 *
 */
@Controller
public class GroupController {
		//日志
	Logger log=Logger.getLogger(GroupController.class);
		//注入Service
	@Autowired
	private GroupService groupService;
	
	
	@RequestMapping("/getServiceScoreListPage")
	public void getServiceScoreListPage(Ipage ipage,ModelMap model) throws Exception{
		try {
			ipage=groupService.getServiceScoreListPage(ipage);
			model.put("ipage", ipage);
		} catch (Exception e) {
			log.error("获取服务评价列表失败",e);
			throw new Exception("获取服务列表失败");
		}
	}
	
	
	/**
	 * 
	 * 方法描述： 跳转到服务商列表页面
	 * @return
	 * String
	 * @author 李梦浩
	 * @date 2016年12月1日 下午8:22:39
	 */
	@RequestMapping("/jumpMerchantSelectList")
	public String jumpMerchantSelectList(){
		return "cred/merchantSelect";
	}
	/**
	 * 
	 * 方法描述： 获取服务商列表
	 * @param model
	 * @param ipage
	 * void
	 * @author 李梦浩
	 * @date 2016年12月2日 下午4:05:46
	 */
	@RequestMapping("/serviceGroupList")
	public String serviceGroupList(){
		System.out.println("serviceGroupList--");
		return "cred/serviceGroupList";
	}
	
	
	/**
	 * 方法描述：服务分组管理页面
	 * @return
	 */
	@RequestMapping("/serviceGroupManage")
	public String jumpserviceGroupList(String pid,ModelMap model){
		//根据id查询服务
		
		try {
			if(StringUtils.isNotEmpty(pid)){
				//所有服务组
				List<Map<String,String>> groups = groupService.getServiceGroups(null);
				Map<String, String> group = ServiceGroupsUtils.getServiceGroupByid(pid, groups);
				model.put("pgroup", group);
				if(group!=null){
					//所有父分组
					List<Map<String,String>> parents = ServiceGroupsUtils.getServiceGroupParents(pid, groups);
					if(parents!=null){
						Collections.sort(parents, new ServiceGroupLevalComparator());
						model.put("parents", parents);
					}
				}
			}
		} catch (Exception e) {
			log.error("jumpserviceGroupList方法出错，执行controller层失败，抛出异常，",e);
		}
		return "cred/serviceGroupList";
	}
	/**
	 * 获取服务分组列表
	 * @param model
	 * @param ipage
	 */
	@RequestMapping("/getCurServiceGroupListPage")
	public void getserviceGroupListPage(Ipage ipage,ModelMap model){
		System.out.println("CC"+ipage);
		try {
			System.out.println("CC"+ipage);
			ipage=groupService.getserviceGroupListPage(ipage);
			model.put("ipage", ipage);
		} catch (Exception e) {
			log.error("getserviceGroupListPage方法出错，执行controller层失败，抛出异常，",e);
		}
	}
	/**
	 * 删除服务组
	 * @param model
	 * @param serialno
	 */
	@RequestMapping("/deleteServiceGroup")
	public void deleteServiceGroup(ModelMap model,String serialno){
		try {
			groupService.deleteServiceGroup(serialno);
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, "删除成功！");
		} catch (Exception e) {
			log.error("deleteServiceGroup方法出错，执行controller层失败，抛出异常，",e);
			model.put(SystemParm.ERROR_CODE, "1");
			model.put(SystemParm.ERROR_MESSAGE, "系统异常，删除失败！");
		}
	}
	/**
	 * 新增服务组
	 * @param model
	 * @param request
	 */
	@RequestMapping("/addServiceGroup")
	public void addServiceGroup(ModelMap model,HttpServletRequest request){
		try {
			String businessName=request.getParameter("businessName");
			String businessCode=request.getParameter("businessCode");
			String sts=request.getParameter("sts");
			String pid=request.getParameter("pid");//上级分组id
			String level=request.getParameter("level");//上级分组层级
			if(StringUtils.isEmpty(pid)){
				level="0";
			}else{
				if(StringUtils.isEmpty(level)){
					level="1";
				}else{
					int levelInt=Integer.parseInt(level);
					level=levelInt+1+"";
				}
			}
			Map<String,String> mp=new HashMap<String, String>();
			mp.put("businessName", businessName);
			mp.put("businessCode", businessCode);
			mp.put("sts", sts);
			mp.put("pid", pid);
			mp.put("level", level);
			HttpSession session=request.getSession();
			UserBean user=(UserBean)session.getAttribute("user");
			mp.put("serialno", GetWaterId.getPK("WJSD"));
			mp.put("traceNo", mp.get("serialno"));
			mp.put("tlrno", user.getUserNo());
			mp.put("tlrname", user.getName());
			mp.put("createDate",DateUtil.getDateTime());
			mp.put("occTime", mp.get("createDate"));
			String code=groupService.addServiceGroup(mp);
			if("0000".equals(code)){
				model.put(SystemParm.ERROR_CODE, "0");
				model.put(SystemParm.ERROR_MESSAGE, "保存成功！");
			}else{
				model.put(SystemParm.ERROR_CODE, "2");
				model.put(SystemParm.ERROR_MESSAGE, "保存失败，此服务组编码已被使用！");
			}
		} catch (Exception e) {
			log.error("addServiceGroup方法出错，执行controller层失败，抛出异常，",e);
			model.put(SystemParm.ERROR_CODE, "1");
			model.put(SystemParm.ERROR_MESSAGE, "系统异常，保存失败！");
		}
	}
	/**
	 * 跳转到配置服务组页面
	 * @return
	 */
	@RequestMapping("/getServiceListForGroup")
	public String getServiceListForGroup(String itemNoStr,HttpServletRequest request){
		request.setAttribute("itemNoStr", itemNoStr);
		return "cred/getServiceListForGroup";
	}
	/**
	 * 获取服务项不分页
	 * @param model
	 * @param searchFiled
	 */
	@RequestMapping("/getServiceItemsForGroup")
	public void getServiceItemsForGroup(ModelMap model,String searchFiled){
		try {
			List<Map<String,String>> list=groupService.getServItemsBySearchFiled(searchFiled);
			model.put("dataList", list);
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, "保存成功！");
		} catch (Exception e) {
			log.error("getServiceItemsForGroup方法出错，执行controller层失败，抛出异常，",e);
			model.put(SystemParm.ERROR_CODE, "1");
			model.put(SystemParm.ERROR_MESSAGE, "系统异常，保存失败！");
		}
	}
	/**
	 * 修改服务组配置
	 * @param model
	 * @param request
	 */
	@RequestMapping("/updateServiceGroup")
	public void updateServiceGroup(ModelMap model,HttpServletRequest request){
		try {
			groupService.updateServiceGroup(request);
			model.put(SystemParm.ERROR_CODE, "0");
			model.put(SystemParm.ERROR_MESSAGE, "保存成功！");
		} catch (Exception e) {
			log.error("updateServiceGroup方法出错，执行controller层失败，抛出异常，",e);
			model.put(SystemParm.ERROR_CODE, "1");
			model.put(SystemParm.ERROR_MESSAGE, "系统异常，保存失败！");
		}
	}
	
}
