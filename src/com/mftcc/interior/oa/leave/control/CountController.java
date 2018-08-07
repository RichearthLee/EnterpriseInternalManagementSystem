package com.mftcc.interior.oa.leave.control;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItem;

import com.mftcc.interior.oa.leave.bean.AttendenceBean;
import com.mftcc.common.SystemParm;
import com.mftcc.interior.oa.leave.service.CountService;
import com.mftcc.interior.report.bean.LeaveDaysFinalBean;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

@Controller
public class CountController {
	
	@Autowired
	private CountService countService;
	private Logger log=Logger.getLogger(CountController.class);
	private File userUploadFile;
	
	@RequestMapping("/myleave")
	public ModelAndView myLeave(HttpSession session)
	{
		ModelAndView modelAndView=new ModelAndView();
		SysUser user = (SysUser) session.getAttribute("sysuser");
		LeaveDaysFinalBean leaveDaysFinalBean=new LeaveDaysFinalBean();
		leaveDaysFinalBean.setBr_name(user.getBrName());
		leaveDaysFinalBean.setOp_name(user.getOpName());
		leaveDaysFinalBean.setBr_no(user.getBrNo());
		leaveDaysFinalBean.setOp_no(user.getOpNo());
		
		List<LeaveDaysFinalBean> countlist=findCount(leaveDaysFinalBean);
		modelAndView.addObject("countlist", countlist);
		modelAndView.setViewName("leave/myleave");
		return modelAndView;
	}
	
	@RequestMapping("/findCount")
	public List<LeaveDaysFinalBean> findCount(LeaveDaysFinalBean leaveDaysFinalBean) {
		List<LeaveDaysFinalBean> countlist =  countService.findCount(leaveDaysFinalBean);
		
	    return countlist;
		
	}
	@RequestMapping("/attendence")
	public String attendence()	
	{
		return "leave/attendence";
	}
	
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody  
	public String upload(MultipartFile file, HttpServletRequest request,ModelMap model)throws IOException	
	{	
	
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        
        File dir = fi.getStoreLocation();
        String yearmonth = request.getParameter("yearmonth");
        
        System.err.print(yearmonth);
        
		POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(dir));
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
		List<AttendenceBean> lAttendenceBeans=new ArrayList<AttendenceBean>();
		if(hssfSheet!=null){
			for(int rowNum=4;rowNum<=hssfSheet.getLastRowNum();rowNum++){
			HSSFRow hssfRow=hssfSheet.getRow(rowNum);
				if(hssfRow==null){
					continue;
				}
				AttendenceBean attendenceBean=new AttendenceBean();
				attendenceBean.setEmpNo(formatCell(hssfRow.getCell(0)));
				attendenceBean.setEmpName(formatCell(hssfRow.getCell(1)));
				attendenceBean.setBrName(formatCell(hssfRow.getCell(2)));
				attendenceBean.setWorkTime(formatCell(hssfRow.getCell(3)));
				attendenceBean.setWorkTimeTotal(formatCell(hssfRow.getCell(4)));
				attendenceBean.setLateTimes(formatCell(hssfRow.getCell(5)));
				attendenceBean.setLateMarks(formatCell(hssfRow.getCell(6)));
				attendenceBean.seteLeaveTimes(formatCell(hssfRow.getCell(7)));
				attendenceBean.seteLeaveMarks(formatCell(hssfRow.getCell(8)));
				attendenceBean.setnExtraWork(formatCell(hssfRow.getCell(9)));
				attendenceBean.setsExtraWork(formatCell(hssfRow.getCell(10)));
				attendenceBean.setAttendenceDays(formatCell(hssfRow.getCell(11)));
				attendenceBean.setGoOut(formatCell(hssfRow.getCell(12)));
				attendenceBean.setNeglect(formatCell(hssfRow.getCell(13)));
				attendenceBean.setYearMonth(yearmonth);
				lAttendenceBeans.add(attendenceBean);
				
				
				
			}
			Boolean result = countService.addattendence(lAttendenceBeans);
			
			System.err.println(result);
			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			
		}	
		/*JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(ServletActionContext.getResponse(), result);*/
		return null;
	}
	
	public static String formatCell(HSSFCell hssfCell){
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(hssfCell.getNumericCellValue());
			}else{
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
	
	@RequestMapping("/getAttendenceListPage")
	public void getAttendenceListPage(Ipage ipage, ModelMap model){

		try {

			ipage = countService.getAttendenceListPage(ipage);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息成功");
		} catch (Exception e) {
			log.error("查询分页失败",e);
			model.put(SystemParm.ERROR_MESSAGE, "查询分页信息失败");
		}
		model.put("ipage", ipage);
	}
	
	@RequestMapping("/myattendence")
	public ModelAndView myAttendence(HttpSession session)
	{
		ModelAndView modelAndView=new ModelAndView();
		SysUser user = (SysUser) session.getAttribute("sysuser");
		AttendenceBean attendenceBean=new AttendenceBean();
		attendenceBean.setEmpName(user.getOpName());
		
		List<AttendenceBean> attendencelist=findAttendence(attendenceBean);
		modelAndView.addObject("attendencelist", attendencelist);
		modelAndView.setViewName("leave/myattendence");
		return modelAndView;
	}
	
	@RequestMapping("/findAttendence")
	public List<AttendenceBean> findAttendence(AttendenceBean attendenceBean) {
		List<AttendenceBean> attendencelist =  countService.findAttendence(attendenceBean);
		
	    return attendencelist;
		
	}
	@RequestMapping("/attendenceInfo")
	public String attendenceInfo(AttendenceBean attendenceBean,ModelMap model)
	{	
		/*String emp =attendenceBean.getEmpNo();
	 	//2018-04-11 06:11
	 	String[] emps=emp.split(",");
	 	attendenceBean.setEmpNo(emps[0]);
	 	attendenceBean.setYearMonth(emps[1]);*/
	 	
		List<AttendenceBean> attendencelist=findAttendence1(attendenceBean);
		model.put("attendence", attendencelist.get(0));
		
		return "leave/attendenceInfo";
	}
	
	@RequestMapping("/findAttendence1")
	public List<AttendenceBean> findAttendence1(AttendenceBean attendenceBean) {
		List<AttendenceBean> attendenceList =  countService.findAttendence1(attendenceBean);
		
	    return attendenceList;
		
	}
	
	@RequestMapping("/updateAttendence")
	public String updateAttendence(AttendenceBean attendence,ModelMap model)
	{

			Boolean result = countService.updateAttendence(attendence);
			System.err.print(result);

			if(result)
			{	//shibai
				model.put(SystemParm.ERROR_CODE, result);
			}
			else{//成功
				model.put(SystemParm.ERROR_CODE, "0");
			}
			return "leave/attendence";
} 
}
