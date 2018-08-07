 package com.mftcc.interior.disk.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskPriv;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;
import com.mftcc.interior.disk.service.DiskPrivService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysUserDao;
import com.mftcc.interior.sys.service.ISysUserService;
import com.mftcc.method.bean.Ipage;

 @Controller
 @RequestMapping("/disk")
public class DiskInfoControl {
	 
	 /*网盘的初始容量*/
	 private static final int DEFAULT_TOTAL_SIZE = 1024*1024*5;	
	 @Autowired
	 private DiskInfoService diskInfoService;
	 @Autowired
	 private DiskFileService diskFileService;
	 @Autowired
	 private ISysUserService isysUserService;
	 @Autowired
	 private DiskPrivService diskPrivService;
	 
	 //跳转到页面diskInfo
	@RequestMapping("/diskInfo")
	public String diskInfojsp(HttpSession session,Model model)
	{
		SysUser user=(SysUser)session.getAttribute("sysuser");
		String userId=user.getOpNo();
		String userName=user.getOpName();
		System.out.print(userId);
		
		List<DiskInfo> disks=diskInfoService.getDiskByUserStatus(userId,"p");
		if(disks.size()!=0)
		{
			DiskInfo diskInfo=disks.get(0);
			session.setAttribute("diskInfo",diskInfo);
			session.setAttribute("homeId", diskFileService.getHomeId(diskInfo.getId()));
			return "disk/disk";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		long disk_id=diskInfoService.addDisk(userId,DEFAULT_TOTAL_SIZE,userName);
		
		session.setAttribute("diskInfo", diskInfoService.getDiskById(disk_id));
		session.setAttribute("homeId", diskFileService.getHomeId(disk_id));		
		return "disk/disk";
	}
	

	
	/**
	 * 初始化用户的一些默认信息（碗盘容量，教程文件）
	 * @param user
	 * @return
	 */
	//测试通过
	 @RequestMapping("/adddisk")
	public String adddisk(String diskname,HttpSession session){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		SysUser testuser =new SysUser() ;	
		testuser.setIdNo("11");
		long disk_id=diskInfoService.addDisk(testuser.getIdNo(),DEFAULT_TOTAL_SIZE,diskname);
		
		session.setAttribute("diskInfo", diskInfoService.getDiskById(disk_id));
		session.setAttribute("homeId", diskFileService.getHomeId(disk_id));
		session.setAttribute("testuser", testuser);
		
		return "disk/disk";
	 }
	 
	 @RequestMapping("/testdisk")
	public String testdisk(long diskid,HttpSession session){
		SysUser testuser =new SysUser() ;	
		testuser.setIdNo("id");
		System.out.print("ceshi");
        DiskInfo disk=diskInfoService.getDiskById(diskid);
        System.out.print(disk.getId());
		session.setAttribute("diskInfo", diskInfoService.getDiskById(diskid));
		session.setAttribute("homeId", diskFileService.getHomeId(diskid));
		session.setAttribute("testuser", testuser);
		return "disk/disk";
	 }
	 
	 /**
	  * 获取用户的可看的群组网盘
	 */
	 @RequestMapping("/getGroupDisk")
	 public String getGroupDisk(long diskid,HttpSession session){
		 return"";
	 }
	 
	 /**
 	* 前往添加群组网盘的页面
	 */
		@RequestMapping("/addDiskGroupJsp")
		public String addDiskGroupJsp(ModelMap model,HttpSession session)
		{
			SysUser user=(SysUser)session.getAttribute("sysuser");
			List<SysUser> userList=isysUserService.getAllSysUser(new SysUser());
			for(int i=0 ;i<userList.size(); i++)
			{
				if(userList.get(i).getOpNo().equals(user.getOpNo())){
					userList.remove(i);
				}
			}
			model.put("userList",isysUserService.getAllSysUser(new SysUser()));
			return "disk/addGroupDisk";
		}
		
		
       
		@RequestMapping("/diskGroupList")
		public String diskGroupJsp()
		{
			return"disk/diskGroupList";
		}	
	
		 
	 /**
 	* 获取群组网盘
	 */
	 @RequestMapping("/getGroupDiskListPage")
		public void getEmplyListPage(HttpSession session,Ipage ipage, ModelMap model){
			//获取用户id
			System.out.println("jiedian");
			SysUser user=(SysUser)session.getAttribute("sysuser");
			String userId=user.getOpNo();
	         ipage.setParm0(userId);
	         System.out.println("jiedian");
			try {
				ipage = diskInfoService.getGroupDiskByPrivPage(ipage);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DiskInfo> diskGroupList=new  ArrayList<DiskInfo>();
			diskGroupList=(List<DiskInfo>) ipage.getDataList();
			SysUser userOv =new  SysUser();
			for(int i=0 ;i<diskGroupList.size(); i++)
			{
				userOv.setOpNo(diskGroupList.get(i).getUser_id());
				diskGroupList.get(i).setCreatUserName(isysUserService.getSysUser(userOv).getOpName());
			}
			ipage.setDataList(diskGroupList);
			System.out.println("jiedian");
			System.out.println(ipage);
			model.put("ipage", ipage);
		}
       
	 /**
	 * 新建群组网盘
	 */
	 @RequestMapping("/addGroupDisk")
	 public void addGroupDisk(DiskInfo disk,HttpSession session,HttpServletRequest request,ModelMap model)
	 {
		 SysUser user=(SysUser)session.getAttribute("sysuser");
		 //增加用户
		 disk.setDiskStatus("g");
		 disk.setFileNumber(0);
		 disk.setUser_id(user.getOpNo());
		 long diskId=diskInfoService.addGroupDisk(disk);
		 
		 //获取用户ID号
		 String persons=request.getParameter("persons");
		 String [] result=persons.split(",");
         DiskPriv diskPriv=new DiskPriv();
         //循环增加用户权限
		 for(int i=0 ;i<result.length; i++)
			{
			 String priv=result[i];
			 String  priv1=request.getParameter(result[i]+"1");
			 String  priv2=request.getParameter(result[i]+"2");
			 String  privContext="";
			 if(priv1!=null)
			 {
				 privContext=privContext+"1";
			 }else{
				 privContext=privContext+"0";
			 }
			 if(priv2!=null)
			 {
				 privContext=privContext+"1";
			 }else{
				 privContext=privContext+"0";
			 }
			 diskPriv.setPrivContext(privContext);
			 diskPriv.setPrivUserId(priv);
			 diskPriv.setPrivDiskId(diskId);
			 diskPriv.setPrivType("d");
			 diskPrivService.insertDiskPriv(diskPriv);
			 
			}
		 
		 model.put(SystemParm.ERROR_CODE, "1");
		 
	 }
	 
	 /**
	 * 进入群组网盘
	 */
	 @RequestMapping("/enterGroupDisk")
	 public String enterGroupDisk(String diskVo,ModelMap model)
	 {
		 String [] result=diskVo.split(",");
		 long disk_id=Long.parseLong(result[0]);
		 String privContext=result[1];
		 model.put("homeId", diskFileService.getHomeId(disk_id));
		 model.put("privContext", privContext);
		 return "disk/diskGroup";
	 }
	 
	

}
