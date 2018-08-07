package com.mftcc.interior.disk.control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.disk.common.DownloadSupport;
import com.mftcc.interior.disk.common.UploadHelper;
import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;
import com.mftcc.interior.disk.service.DiskShareService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.service.ISysOrgService;
import com.mftcc.interior.sys.service.ISysUserService;
import com.mftcc.method.bean.Ipage;



@Controller
@RequestMapping("/share")
public class ShareController {
	 @Autowired
	 private DiskInfoService diskInfoService;
	 @Autowired
	 private DiskFileService diskFileService;
	 @Autowired
	 private ISysOrgService sysOrgService;
	 @Autowired
	 private DiskShareService diskShareService;
	 @Autowired
	 private ISysUserService iSysUserService;
	 /**
	 * 分享的判断
	 * @param shareId
	 * @return
	 */
	 @RequestMapping(value="/s/{shareId}")
	 public String shareEnter(Model model,@PathVariable String shareId)
	 {
		 //获取share的记录
		 DiskShare share=diskShareService.getShareById(shareId);
		 //获取用户的信息
		 SysUser user= new SysUser();
		 user.setOpNo(share.getUser_id());
		 SysUser shareUser = iSysUserService.getSysUser(user);

		 if(share.getShareStatus().equals("pwd"))
		 {
			 model.addAttribute("shareId", shareId);
			 model.addAttribute("shareUser", shareUser);
			 return "disk/shareTest";
		 }
		 model.addAttribute("shareUser", shareUser);
		 model.addAttribute("diskFile", diskFileService.getMyFile(share.getFileId()));
		 return"disk/share";
	 }
	 
	 /**
		 * 验证验证码
		 * @param shareId sharePwd
		 * @return
		 */ 
	 @RequestMapping(value="/shareTest")
	 public String sharetest(ModelMap model,String shareId,String sharePwd)
	 {
		 //获取share的记录
		 DiskShare share=diskShareService.getShareById(shareId);
		//获取用户的信息
		 SysUser user= new SysUser();
		 user.setOpNo(share.getUser_id());
		 SysUser shareUser = iSysUserService.getSysUser(user);
		 if(share.getSharePwd().equals(sharePwd))
			{	//成功
				model.put("shareResult", "1");
				model.put("shareUser", shareUser);
				model.put("diskFile", diskFileService.getMyFile(share.getFileId()));
				model.put("share", share);
				return "disk/share";
			}
			else 
			{   
				model.put("shareUser", shareUser);
				model.addAttribute("shareId", shareId);
				model.put("shareResult", "0");
				return "disk/shareTest";
				
			}
	 }
	/**
	 * 某用户的所有分享
	 * @param uId
	 * @return
	 */
/*	@RequestMapping(value="/u/{uId}")
	public String shareOfUser(@PathVariable String uId,Model model){
		
		//SysUser owner = ;
		DiskInfo ownerDisk = (diskInfoService.load(uId)).get(0); 
		List<DiskShare> shareRecord = diskShareService.getShareByUser(uId);
		
		//model.addAttribute("owner", owner);
		model.addAttribute("ownerDisk", ownerDisk);
		model.addAttribute("shareRecord", shareRecord);
		
		return "usershare";
	}*/
	
	/**
	 * 
	 * @param username
	 * @return
	 */                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	@RequestMapping(value="/{fileId}")
	public String shareDetail(@PathVariable int fileId,Model model){
		DiskFile shareFile = diskFileService.getMyFile(fileId);
		if(shareFile.getIsShare().equals("1") ){
			//User owner = UserDao.loadById(shareFile.getUser_id());
			DiskInfo	ownerDisk =( diskInfoService.load(shareFile.getUser_id())).get(0);
			
			//model.addAttribute("owner", owner);
			model.addAttribute("ownerDisk", ownerDisk);
			model.addAttribute("shareFile", shareFile);
		}
		return "share";
	}
	
	/**
	 * 分享下载
	 * @param response
	 * @param shareId
	 */
	@RequestMapping(value="/download/{shareId}")
	public void download(HttpServletResponse response,@PathVariable int shareId){
		DiskFile myFile = diskFileService.getMyFile(shareId);
		
		if(myFile.getIsShare().equals("1")){
			DownloadSupport.download(response, myFile);
			diskFileService.updateShareDownload(shareId);
		}
	}
	
	/**
	 * 前往分享管理页面
	 */
	@RequestMapping("/shareManager")
	public String op()
	{
		return "disk/shareManager";
	}
	
	@RequestMapping("/getShareListPage")
	public void getEmplyListPage(HttpSession session,Ipage ipage, ModelMap model){
		//获取用户id
		System.out.println("jiedian");
		SysUser user=(SysUser)session.getAttribute("sysuser");
		String userId=user.getOpNo();
         ipage.setParm0(userId);
		try {
			ipage = diskShareService.getShareByUserPage(ipage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("jiedian");
		System.out.println(ipage);
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/updataShare")
	public void updataShare(HttpSession session,ModelMap model,DiskShare share){
		//获取用户id
		int b=diskShareService.updateShareById(share);
		System.out.println("shijain:"+share.getShareEnTime());
		System.out.println("jieguo:"+b);
		if(b!=0)
		{
			model.put(SystemParm.ERROR_CODE, "1");
		}
		else{
			model.put(SystemParm.ERROR_CODE, "0");
		}
		
	}
	
	
}
