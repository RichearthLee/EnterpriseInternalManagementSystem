package com.mftcc.interior.disk.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;

@Controller
@RequestMapping("/operate")
public class DiskOptionController {
	
	@Autowired
	 private DiskInfoService diskInfoService;
	@Autowired
	 private DiskFileService diskFileService;
	
	@RequestMapping("op")
	public String op()
	{
		return "disk/opRecord";
	}

}
