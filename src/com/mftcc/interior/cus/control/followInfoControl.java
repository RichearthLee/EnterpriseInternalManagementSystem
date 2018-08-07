package com.mftcc.interior.cus.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.interior.cus.bean.CusReview;
import com.mftcc.interior.cus.bean.cusLinkman;
import com.mftcc.interior.cus.bean.cus_follow;
import com.mftcc.interior.cus.bean.customer;
import com.mftcc.interior.cus.bean.message;
import com.mftcc.interior.cus.service.CustomerService;
import com.mftcc.interior.cus.service.FollowInfoService;
import com.mftcc.interior.cus.service.MessageService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysUserService;

@Controller
public class followInfoControl {

	@Autowired
	private FollowInfoService followInfoService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ISysUserService iSysUserService;
	
	@RequestMapping(value = {"/insertFollowInfo"})
	@ResponseBody
	public Map<String,Object> insertOneFollow(cus_follow followInfo,String receive){
		message msg=new message();
		msg.setUserId(followInfo.getUserid());
		msg.setCusId(followInfo.getCusid());
		msg.setUserId2(followInfo.getFeedid());
		msg.setContent(followInfo.getFeedback());
		msg.setReceive(receive);
		msg.setType("跟踪消息");
		msg.setDate(followInfo.getDate());
		msg.setNewMsg("true");
		msg.setLooked("false");
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("flag","success");
		try{
			followInfoService.insertFollowInfoService(followInfo);
			messageService.insertMsg(msg);
		}
		catch(Exception e){
			datamap.put("flag","failed");
		}
		return datamap;
	}
	@RequestMapping(value = {"/selectFollowInfo"})
	@ResponseBody
	public List<cus_follow> selectAll(){
		List<cus_follow> list=new ArrayList<cus_follow>();
		try{
			list=followInfoService.selectInfoAllService();
		}
		catch(Exception e)
		{
			
		}
		System.out.println(list.size());
		return list;
	}
	@RequestMapping(value = {"/selectOneFollow"})
	@ResponseBody
	public Map<String,Object> selectOneFollow(String followId){
		List<cus_follow> list=new ArrayList<cus_follow>();
		//List<SysUser> userList=new ArrayList<SysUser>();
		cus_follow follow=new cus_follow();
		SysUser user=new SysUser();
		customer cus=new customer();
		Map<String,Object> datamap=new HashMap<String,Object>();
		try{
			list=followInfoService.selectOneById(followId);
			follow=list.get(0);
			user.setOpNo(list.get(0).getUserid());
			user=iSysUserService.getSysUser(user);
			cus=customerService.selectCusById(follow.getCusid());
			//userList.add(user);
		}
		catch(Exception e){
			
		}
		System.out.println(follow.getCusid()+" "+cus.getCustomerName());
		datamap.put("follow", follow);
		datamap.put("user", user);
		datamap.put("cus", cus);
		return datamap;
	}
	@RequestMapping(value = {"/selectByUC"})
	@ResponseBody
	public Map<String,Object> selectByUC(cus_follow cf){
		SysUser user=new SysUser();
		user.setOpNo(cf.getUserid());
		List<SysUser> userlist=new ArrayList<SysUser>();
		List<cus_follow> list=new ArrayList<cus_follow>();
		List<cusLinkman> linklist=new ArrayList<cusLinkman>();
		Map<String,Object> listmap=new HashMap<String ,Object>();
		try{
			list=followInfoService.selectByUserCus(cf);
			linklist=customerService.selectLinkByCus(cf.getCusid());
			user=iSysUserService.getSysUser(user);
			userlist.add(user);
			listmap.put("follow", list);
			listmap.put("linkman", linklist);
			listmap.put("user", userlist);
		}
		catch(Exception e)
		{
			
		}
		System.out.println(user.getOpName());
		System.out.println(list.size());
		System.out.println(linklist.size());
		return listmap;
	}
	@RequestMapping(value = {"/selectRevByFeed"})
	@ResponseBody
	public List<CusReview> selectRevByFeed(String feedId){
		SysUser user=new SysUser();
		List<CusReview> list=new ArrayList<CusReview>();
		try{
			list=followInfoService.selectByFeed(feedId);
			for(int i=0;i<list.size();i++){
				user.setOpNo(list.get(i).getUserId());
				user=iSysUserService.getSysUser(user);
				list.get(i).setUserName(user.getOpName());
			}
		}
		catch(Exception e){
			
		}
		System.out.println(list.size());
		return list;
	}
	@RequestMapping(value = {"/insertReview"})
	@ResponseBody
	public SysUser insertReview(CusReview review){
		SysUser user=new SysUser();
		user.setOpNo(review.getUserId());
		message msg=new message();
		message msg2=new message();
		msg2.setUserId2(review.getFeedId());
		msg.setType("评论消息");
		msg.setDate(review.getEvaId());
		msg.setCusId(review.getCusId());
		msg.setUserId(review.getUserId());
		msg.setUserId2(review.getFeedId());
		msg.setContent(review.getEvaContent());
		msg.setNewMsg("true");
		msg.setLooked("false");
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("flag", "success");
		try{
			followInfoService.insertReview(review);
			msg2=messageService.selectMsgByIT(msg2);
			msg.setReceive(msg2.getReceive());
			messageService.insertMsg(msg);
			user=iSysUserService.getSysUser(user);
		}
		catch(Exception e){
			datamap.put("flag", "failed");
		}
		System.out.println(msg2.getReceive());
		return user;
	}
	@RequestMapping(value = {"/selectAllUser"})
	@ResponseBody
	public List<SysUser> selectAllUser(SysUser user){
		List<SysUser> userlist=new ArrayList<SysUser>();
		userlist=iSysUserService.getAllSysUser(user);
		System.out.println(userlist.size());
		return userlist;
	}
	/*public static void main(String[] args) {
		Map<String,Object> data1=new HashMap<String,Object>();
		cus_follow follow=new cus_follow();
		followInfoControl control=new followInfoControl();
		follow.setFeedid("a");
		data1=control.insertOneFollow(follow);
		System.out.println(data1.get("falg"));
	}*/
}
