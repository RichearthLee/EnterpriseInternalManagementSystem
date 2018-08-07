package com.mftcc.method.control;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;  

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;  
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import org.apache.log4j.Logger;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.ResponseBody;  
import org.springframework.web.servlet.ModelAndView;  

import com.mftcc.common.util.FileMD5;
@Controller 
public class UploadControl {


	  
	 	
	    Logger log = Logger.getLogger(UploadControl.class);  
	      
	    /** 
	     * upload  上传文件 
	     * @param request 
	     * @param response 
	     * @return 
	     * @throws Exception 
	     */  
	    @RequestMapping(value = "/uploadasd", method = RequestMethod.POST)  
	    public String upload(HttpServletRequest request,  
	          ModelMap model) throws Exception {  
	        final HttpSession hs = request.getSession(); 
	 /*       boolean isMultipart = ServletFileUpload.isMultipartContent(request);  */
	/*        if(!isMultipart){  
	            return mv;  
	        }  */
	        // Create a factory for disk-based file items  
	        FileItemFactory factory = new DiskFileItemFactory();  
	  
	        // Create a new file upload handler  
	        ServletFileUpload upload = new ServletFileUpload(factory);  
	        upload.setProgressListener(new ProgressListener(){  
	               public void update(long pBytesRead, long pContentLength, int pItems) {  
	                   ProcessInfo pri = new ProcessInfo();  
	                   pri.itemNum = pItems;  
	                   pri.readSize = pBytesRead;  
	                   pri.totalSize = pContentLength;  
	              //     pri.show = pBytesRead+"/"+pContentLength+" byte";  
	                   pri.show = Math.round(new Float(pBytesRead) / new Float(pContentLength)*100)+"%"; 
	                   pri.rate = Math.round(new Float(pBytesRead) / new Float(pContentLength)*100);  
	                   hs.setAttribute("proInfo", pri);  
	               }  
	            });  
	        request.setCharacterEncoding("UTF-8");
			  try {
				  	List items = upload.parseRequest(request);
				/*	while(upload.getItemIterator(request).hasNext()){
					
							 upload.getItemIterator(request).next();
					}
					*/
				  	Iterator itr = items.iterator();
				  	while (itr.hasNext()) {
				  		FileItem item = (FileItem) itr.next();
				  		FileMD5 a =new FileMD5();
				  		
				  		System.out.println(a.getFileMD5String(item.getInputStream()));
				  		if (item.isFormField()) {
				  			System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
				  		} else {
				  			if (item.getName() != null && !item.getName().equals("")) {
				  				System.out.println("上传文件的大小:" + item.getSize());
				  				System.out.println("上传文件的类型:" + item.getContentType());
				  				// item.getName()返回上传文件在客户端的完整路径名称
				  				System.out.println("上传文件的名称:" + item.getName());

				  				File tempFile = new File(item.getName());
				  				File filePath = new File(request.getSession().getServletContext().getRealPath("/upload"));
				  				if (!filePath.exists() && !filePath.isDirectory()) {
				  					                     System.out.println(request.getSession().getServletContext().getRealPath("/upload")+"目录不存在，需要创建");
				  					                      //创建目录
				  					                   filePath.mkdir();
				  					                 }
				  				//上传文件的保存路径
				  				File file = new File(request.getSession().getServletContext().getRealPath("/upload"), tempFile.getName());
				  			
				  				/*		FileMD5 a =new FileMD5();
				  				
				  				System.out.println(a.getFileMD5String(file));*/
				  				item.write(file);
				  				
				  				request.setAttribute("upload.message", "上传文件成功！");
				  			}else{
				  				request.setAttribute("upload.message", "没有选择上传文件！");
				  			}
				  		}
				   }
			}catch(FileUploadException e){
				   e.printStackTrace();
			} catch (Exception e) {
				   e.printStackTrace();
				   request.setAttribute("upload.message", "上传文件失败！");
			}
	        return null;
	    }  
	    //
	         
	    /** 
	     * process 获取进度 
	     * @param request 
	     * @param response 
	     * @return 
	     * @throws Exception 
	     */  
	    @RequestMapping(value = "/process", method = RequestMethod.POST)  
	    public String process(HttpServletRequest request,ModelMap model) throws Exception {  
	        model.put("proInfo", (ProcessInfo)request.getSession().getAttribute("proInfo")) ;  
	         return null;
	    }  
	      
	    class ProcessInfo{  
	        public long totalSize = 1;  
	        public long readSize = 0;  
	        public String show = "";  
	        public int itemNum = 0;  
	        public int rate = 0;  
	    }  
	      
	}  
