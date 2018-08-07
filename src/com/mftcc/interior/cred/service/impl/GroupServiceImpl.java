/**
 * Copyright (C) DXHM 版权所有
 * 文件名： CifServiceAccService.java
 * 包名： com.mftcc.mftccmanage.cifaccount_new.service.impl
 * 说明：
 * @author 李梦浩
 * @date 2016年11月10日 下午5:18:14
 * @version V1.0
 */ 
package com.mftcc.interior.cred.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.util.DateUtil;
import com.mftcc.common.util.GetWaterId;
import com.mftcc.common.util.MathExtend;
import com.mftcc.common.util.StringUtil;
import com.mftcc.interior.cred.bean.UserBean;
import com.mftcc.interior.cred.bean.AcCredServiceItems;
import com.mftcc.interior.cred.bean.AcCredServiceGroup;
//import com.mftcc.interior.cred.bean.AcCredServiceSource;
import com.mftcc.interior.cred.dao.GroupDao;
import com.mftcc.interior.cred.service.GroupService;
import com.mftcc.interior.cred.util.ServiceGroupsUtils;
import com.mftcc.method.bean.Ipage;
/**
 * 类名： CifServiceAccService
 * 描述：客户开通服务 以及服务项管理
 * @author 李梦浩
 * @date 2016年11月10日 下午5:18:14
 *
 *
 */
@Service
public class GroupServiceImpl implements GroupService{
	//日志
	private Logger log=Logger.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupDao groupDao;

	
	@Override
	public Ipage getserviceGroupListPage(Ipage ipage) throws Exception {
		try {
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			List<Map<String,Object>> serviceGroups=groupDao.getAllSerGroups(ipage);
			for(Map<String,Object> mp:serviceGroups){
				List<String> itemNos=new ArrayList<String>();
				String businessCode=(String)mp.get("businessCode");
				List<Map<String,String>> lst=groupDao.getGroupServItemNos(businessCode);
				for(Map<String,String> items:lst){
					itemNos.add(items.get("itemNo"));
				}
				mp.put("itemNos", itemNos);
			}
			Integer count=groupDao.getAllSerGroupsCnt(ipage);
			int allRecord=count.intValue();
			int totalpage=0;
			totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
					/ ipage.getPageNumber() + 1;
			ipage.setTotalPage(totalpage);//总页数
			ipage.setDataList(serviceGroups);//结果集 
			ipage.setAllRecord(allRecord);//总条数
			return ipage;
		} catch (Exception e) {
			log.error("getserviceGroupListPage方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
		
	}
	@Override
	public void deleteServiceGroup(String serialno) throws Exception {
		try {
			//查询
			List<Map<String,String>> groups = this.getServiceGroups(null);
			AcCredServiceGroup root = ServiceGroupsUtils.getChildTree(serialno, groups);
			List<AcCredServiceGroup> list = root.treeToList();
			for(AcCredServiceGroup bsg:list){
				groupDao.deletServiceGroup(bsg.getBusinessCode());
				groupDao.deleteServGroupDetail(bsg.getBusinessCode());
			}
			
		} catch (Exception e) {
			log.error("deleteServiceGroup方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public String addServiceGroup(Map<String, String> mp) throws Exception {
		try {
			String businessCode=mp.get("businessCode");
			Ipage ipage=new Ipage();
			ipage.setParm0(businessCode);
			Integer count=groupDao.getAllSerGroupsCnt(ipage);
			if(count.compareTo(0)!=0){
				return "1111";
			}
			groupDao.addServiceGroup(mp);
			return "0000";
		} catch (Exception e) {
			log.error("addServiceGroup方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	@Override
	public List<Map<String, String>> getServItemsBySearchFiled(
			String searchFiled) throws Exception {
		List<Map<String, String>> list=null;
		try {
			Map<String,String> mp=new HashMap<String, String>();
			mp.put("searchFiled", searchFiled);
			list=groupDao.getServItemsBySearchFiled(mp);
		} catch (Exception e) {
			log.error("getServItemsBySearchFiled方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
		return list;
	}
	@Override
	public void updateServiceGroup(HttpServletRequest request)
			throws Exception {
		try {
			String serialno=request.getParameter("serialno");
			String businessName=request.getParameter("businessName");
			String businessCode=request.getParameter("businessCode");
			String sts=request.getParameter("sts");
			Map<String,String> mp=new HashMap<String, String>();
			mp.put("businessName", businessName);
			mp.put("sts", sts);
			mp.put("serialno", serialno);
			mp.put("occTime", DateUtil.getDateTime());
			//更新服务组
			groupDao.deleteServGroupDetail(businessCode);
			groupDao.updateServiceGroup(mp);
			String itemNos=request.getParameter("itemNos");
			if(StringUtil.isNotEmpty(itemNos)){
				String[] itemArray=itemNos.split(",");
				for(int i=0;i<itemArray.length;i++){
					String itemNo=itemArray[i];
					Map<String,String> detailMp=new HashMap<String, String>();
					detailMp.put("serialno", GetWaterId.getWaterId());
					detailMp.put("traceNo", detailMp.get("serialno"));
					detailMp.put("businessCode", businessCode);
					detailMp.put("itemNo", itemNo);
					HttpSession session=request.getSession();
					UserBean user=(UserBean)session.getAttribute("user");
					detailMp.put("tlrno", user.getUserNo());
					detailMp.put("tlrname", user.getName());
					detailMp.put("openDate",DateUtil.getDateTime());
					groupDao.addServiceGroupDetail(detailMp);
				}
			}
		} catch (Exception e) {
			log.error("updateServiceGroup方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
	}
	
	@Override
	public int getScoreCount(String itemNo, String score) throws Exception {
		Integer count=groupDao.getScoreCount(itemNo, score);
		if(null==count){
			count=0;
		}
		return count.intValue();
	}
	@Override
	public Ipage getServiceScoreListPage(Ipage ipage) throws Exception {
		try {
			if(ipage.getCurrPageNo() > 0){
				ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
			}
			if("0".equals(ipage.getParm1())){//差评
				ipage.setParm1("0,1,2");
			}else if("1".equals(ipage.getParm1())){
				ipage.setParm1("3,4");
			}else if("2".equals(ipage.getParm1())){
				ipage.setParm1("5");
			}
			List<Map<String,String>> scoreList=groupDao.selectAllServiceScore(ipage);
			for(Map<String,String> mp:scoreList){
				//处理时间格式
				String date=mp.get("create_date");
				if(date!=null&&date!=""){
					String[] arr=date.split(" ");
					date=date.substring(0, 4)+"-"+date.substring(4, 6)+"-"+date.substring(6, 8)+" "+arr[1];
					mp.put("create_date", date);
				}
			}
			//查询出所有的服务条数allRecord
			int allRecord=0;
			Integer count=groupDao.getScoreCount(ipage.getParm0(), ipage.getParm1());
			allRecord=count.intValue();
			//设置返回页面的ipage参数
			int totalpage=0;
			totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
					/ ipage.getPageNumber() + 1;
			ipage.setTotalPage(totalpage);//总页数
			ipage.setDataList(scoreList);//结果集 
			ipage.setAllRecord(allRecord);//总条数
		} catch (Exception e) {
			log.error("getServiceItemsListPage方法出错；执行service层失败，抛出异常，",e);
			throw new Exception(e);
		}
		return ipage;
	}
	@Override
	public Map<String, Object> getSerGroupByid(String businessCode)
			throws Exception {
		Map<String, Object> result=null;
		try {
			result = groupDao.getSerGroupByid(businessCode);
		} catch (Exception e) {
			log.error("getSerGroup方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
		return result;
	}
	@Override
	public List<Map<String, String>> getServiceGroups(Map<String, String> param) throws Exception {
		List<Map<String, String>> result=null;
		try {
			result = groupDao.getAllServiceGroups(param);
		} catch (Exception e) {
			log.error("getServiceGroups方法出错，执行service层失败，抛出异常，");
			throw new Exception(e);
		}
		return result;
	}
}
