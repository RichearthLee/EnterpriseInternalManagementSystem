package com.mftcc.interior.disk.control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mftcc.common.SystemParm;
import com.mftcc.interior.disk.common.DownloadSupport;
import com.mftcc.interior.disk.common.UploadHelper;
import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskMsg;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.dao.DiskFileDao;
import com.mftcc.interior.disk.dao.DiskInfoDao;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;
import com.mftcc.interior.disk.service.DiskShareService;
import com.mftcc.interior.sys.bean.SysUser;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/disk")
public class DiskFileControl {
	private static final String FILEBASEPATH="d:/disk/temp_file/";
	
	@Autowired
	 private DiskInfoService diskInfoService;
	@Autowired
	 private DiskFileService diskFileService;
	@Autowired
	 private DiskShareService diskShareService;
	
	/**
	 * 列出文件夹的内的所有子文件
	 * @param id
	 * @return
	 */
	//测试通过
	@RequestMapping(value="/list_myfile",produces = "application/json; charset=utf-8")
	@ResponseBody
	public  String listFiles(long id,String pwd,Model model){
		System.out.println(id);
		DiskFile folder = diskFileService.getMyFile(id);
		List<DiskFile> myFiles = null;
		if(folder.getIsLock() == "1"){
			if(folder.getPassword().equals(pwd)){
				myFiles = diskFileService.getFilesByFolderId(id);
			}else{
				return "fail";
			}
		}else{
			myFiles = diskFileService.getFilesByFolderId(id);
		}
		return JSON.toJSONString(myFiles);
	}
	/**
	 * 群组网盘的列出文件夹的内的所有子文件
	 * @param id
	 * @return
	 */
	//测试通过
	@RequestMapping(value="/listGroupDiskFiles")
	public  void listGroupDiskFiles(long fatherId,String pwd, ModelMap model){
		DiskFile folder = diskFileService.getMyFile(fatherId);
		
		List<DiskFile> fatherFolder=new  ArrayList<DiskFile>();
		//获取父路径的文件
		if(folder.getParent_id()!=null)
		{
		
		fatherFolder.add(folder);
		DiskFile father=diskFileService.getMyFile(folder.getParent_id());
		while (father.getParent_id()!=null)
		{
			fatherFolder.add(father);
			father=diskFileService.getMyFile(father.getParent_id());
		}
		
		}
		
		
		//解密
		List<DiskFile> myFiles = null;
		if(folder.getIsLock() == "1"){
			if(folder.getPassword().equals(pwd)){
				model.put(SystemParm.ERROR_CODE, "1");
				myFiles = diskFileService.getFilesByFolderId(fatherId);
				model.put("myFiles", myFiles);
				model.put("fatherFolder", fatherFolder);
			}else{
				model.put(SystemParm.ERROR_CODE, "0");
				model.put("myFiles", myFiles);
			}
		}else{

			model.put(SystemParm.ERROR_CODE, "1");
			myFiles = diskFileService.getFilesByFolderId(fatherId);
			model.put("myFiles", myFiles);
			model.put("fatherFolder", fatherFolder);
		}
		
		model.put("fatherId", fatherId);
		
	}
	/**
	 * 上传文件
	 * @param request
	 * @param folderid
	 * @return
	 */
	@RequestMapping(value="/upload/{folderid}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String upload(HttpServletRequest request,@PathVariable long folderid,HttpSession session){
		UploadHelper upload = new UploadHelper();
		MultipartFile file = upload.getFiles(request).get(0);
		System.out.print(file);
		String result = "fail";
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
	
		SysUser user=(SysUser)session.getAttribute("sysuser");
		DiskFile myFile = new DiskFile();
		//获取用户id？
		DiskFile getFolder=diskFileService.getMyFile(folderid);
		myFile.setUser_id(user.getOpNo());
		myFile.setSize(file.getSize());
		myFile.setDisk_id(getFolder.getDisk_id());
		

		if(diskInfoService.isEnoughSpace(myFile)){
			String filePath = FILEBASEPATH + new Date().getTime() + "." + suffix;
			
			//String filePath = session.getServletContext().getRealPath("FILE") +"/"+ new Date().getTime() + "." + suffix;
			try {
				upload.upload(file, filePath);//文件没有成功保存返回失败信息
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				myFile.setDisk_id(getFolder.getDisk_id());
				myFile.setCreateDate(sdf.format(new Date()));
				myFile.setName(fileName);
				myFile.setParent_id(folderid);
				myFile.setType(suffix.toLowerCase());
				myFile.setPath(getFolder.getPath()+folderid+"/");
				myFile.setLocation(filePath);
				myFile.setIsShare("0");
				myFile.setDescription("");
				myFile.setIsLock("0");
				
				
				myFile = diskFileService.addload(myFile);
				/*同步网盘信息*/
				DiskInfo diskInfo = diskInfoService.getDiskById(getFolder.getDisk_id());
				session.setAttribute("diskInfo",diskInfo);
				
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("file", myFile);
				data.put("usedSize", diskInfo.getUsedSize());
				
				result = JSON.toJSONString(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 下载文件
	 * @param fileId
	 * @param response
	 */
	@RequestMapping("/download/{fileId}")
    public void download(@PathVariable int fileId,HttpServletResponse response){
		DiskFile myFile = diskFileService.getMyFile(fileId);
		DownloadSupport.download(response, myFile);
    }

//测试通过
	/**
	 * 新建文件夹
	 * @param folderId
	 * @return
	 */
	@RequestMapping("/mkdir/{folderId}")
	@ResponseBody
	public String mkdir(@PathVariable long folderId,String folderName,HttpSession session){
		DiskFile dir = new DiskFile();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//folderId根据获取文件
		DiskFile getFolder=diskFileService.getMyFile(folderId);
		dir.setDisk_id(getFolder.getDisk_id());
		dir.setCreateDate(sdf.format(new Date()));
		dir.setParent_id(folderId);
		dir.setName(folderName);
		dir.setPath(getFolder.getPath()+folderId+"/");
		dir.setType("adir");
		dir.setDescription("");
		dir.setUser_id("9");
		dir.setSize(0);
		dir.setIsLock("0");
		dir.setIsShare("0");
		dir.setId(diskFileService.save(dir));
		System.out.println("shenghuo"+dir.getId());
		
		return JSON.toJSONString(dir);
	}
	/**
	 * 重命名
	 * @param fileId
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/rename/{fileId}")
	@ResponseBody
	public String updatename(@PathVariable int fileId,String fileName,String pwd){
		DiskFile myFile = diskFileService.getMyFile(fileId);
		String result = "fail";

		if(myFile.getIsLock() == "1"){
			if(myFile.getPassword() == null && pwd == "" || myFile.getPassword().equals(pwd)){
				diskFileService.updatename(fileId, fileName);
				result = "success";
			}else{
				result = "fail";
			}
		}else{
			diskFileService.updatename(fileId, fileName);
			result = "success";
		}
		
		return result;
	}

//测试通过
	/**
	 * 分享文件
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/share/{fileId}")
	@ResponseBody
	public String addshare(HttpSession session,HttpServletRequest request,@PathVariable long fileId,String sharePwd,String shareEnTime){
		//获取用户id
		SysUser user=(SysUser)session.getAttribute("sysuser");
		String userId=user.getOpNo();
		//获取
		DiskFile myFile =new DiskFile();
		String result = "fail";
		String temp=String.valueOf(System.currentTimeMillis());
		DiskFile dir = new DiskFile();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//添加share
		DiskShare share=new DiskShare();
		share.setShareId(temp);
		share.setShareStTime(sdf.format(new Date()));
		share.setShareIsDel("0");
		share.setFileId(fileId);
		share.setUser_id(userId);
		share.setShareEnTime(shareEnTime);
		if(sharePwd!=null)
		{
			share.setSharePwd(sharePwd);
			share.setShareStatus("pwd");
			
		}else{
			share.setSharePwd("0");
			share.setShareStatus("no");
		}
		String url = (request.getRequestURL() + "").replace(request.getRequestURI(), "");
		url=url+"/mftccManager";
		share.setShareUrl(url+"/share/s/"+temp);
		int b=diskShareService.addShare(share);
		//
		if(b == 1){
			myFile.setShareUrl(url+"/share/s/"+temp);
			myFile.setId(fileId);
			diskFileService.addshare(myFile);
			
			result = url+"/share/s/"+temp;
		}
		
		return result;
	}


	/**
	 * 取消分享
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/cancelshare")
	@ResponseBody
	public String cancelShare(int fileId){
		diskFileService.cancelShare(fileId);
		return "success";
	}
	
	/**
	 * 文件的移动
	 * @param sourceId
	 * @param targetId
	 * @return
	 */
	@RequestMapping("/movefile")
	@ResponseBody
	public String moveFile(int sourceId,int targetId){
		DiskFile 	sourceFile = diskFileService.getMyFile(sourceId),
				targetFile = diskFileService.getMyFile(targetId);
		
		diskFileService.updateMoveFile(sourceFile, targetFile);
		
		return "success";
	}
	
	/**
	 * 给文件上密码
	 * @param fileId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("/addlock/{fileId}")
	@ResponseBody
	public String addLock(@PathVariable int fileId,String pwd){
		DiskFile myFile = diskFileService.getMyFile(fileId);
		if(myFile.getIsLock() != "1"){
			diskFileService.addLock(fileId, pwd);
		}	
		return "success";
	}
	
	/**
	 * 给加锁文件夹更换密码
	 * @param fileId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("/changefilepwd/{fileId}")
	@ResponseBody
	public String changeFilePwd(@PathVariable int fileId,String oldPwd,String newPwd){
		DiskFile myFile = diskFileService.getMyFile(fileId);

		String result = "fail";
		if(myFile.getPassword()==null && oldPwd == "" || myFile.getPassword().equals(oldPwd)){
			diskFileService.addLock(fileId, newPwd);
			result = "success";
		}	
		return result;
	}
	
	/**
	 * 给文件解锁
	 * @param fileId
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/deletelock/{fileId}")
	@ResponseBody
	public String deleteLock(@PathVariable int fileId,String pwd){
		DiskFile myFile = diskFileService.getMyFile(fileId);
		String result = "fail";
		
		if(myFile.getPassword().equals(pwd)){
			diskFileService.deleteLock(fileId);
			result = "success";
		}
		
		return result;
	}
	
	/**
	 * 删除文件（或文件夹），返回删除文件后的网盘容量
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/delete/{fileId}")
	@ResponseBody
	public String delete(@PathVariable int fileId,String pwd,HttpSession session){
		DiskFile myFile = diskFileService.getMyFile(fileId);
		String result = "fail";
		
		if(myFile.getPassword() == null && pwd == "" || myFile.getPassword().equals(pwd)){
			//String uId = ((SysUser)session.getAttribute("user")).getIdNo();
			if(!myFile.getType().equals("adir")){
				/*文件则直接删除*/
				diskFileService.deleteFile(myFile);
				new File(myFile.getLocation()).delete();
			}else{
				List<DiskFile> myFiles = diskFileService.deleteFolder(myFile);
				/*是文件夹就直接删除数据库记录，是文件就要把文件删除后才删除数据库记录*/
				if(myFiles != null){
					for(DiskFile myF:myFiles){
						System.out.println("删除文件："+myF.getLocation());
						new File(myF.getLocation()).delete();
					}
				}
			}
			
			DiskInfo diskInfo =diskInfoService.getDiskById(myFile.getDisk_id());
			session.setAttribute("diskInfo",diskInfo);
			result = diskInfo.getUsedSize()+"";
		}
		return result;
	}
	
//	/**
//	 * 以"/disk"路径访问网盘
//	 * @return
//	 */
//	@RequestMapping("/disk")
//	public String index(){
//		return "disk";
//	}
//	/**
//	 * 以"/"路径访问网盘
//	 */
//	@RequestMapping("/")
//	 * @return
///	public String index1(){
//		return "redirect:/disk/disk";
//	}
//	/**
//	 * 以""路径访问网盘
//	 * @return
//	 */
//	@RequestMapping("")
//	public String index2(){
//		return "redirect:/disk/disk";
//	}

}
