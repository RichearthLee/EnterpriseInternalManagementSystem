package com.mftcc.interior.disk.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;

@Controller
@RequestMapping("/test")
public class testController {
	
	@Autowired
	 private DiskInfoService diskInfoService;
	@Autowired
	 private DiskFileService diskFileService;
	
	@RequestMapping("test1")
	public String test1(long diskId,HttpSession session)
	{
		int filenum=diskFileService.getFileNumByDisk(diskId);
		int filesize=diskFileService.getSizeSumByDisk(diskId);
		diskInfoService.updateDisk(filenum, filesize,diskId);
		session.setAttribute("filenum",filenum);
		session.setAttribute("filesize",filesize);
		return "disk/test";
	}

}
