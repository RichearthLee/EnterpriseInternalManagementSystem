/**
 * Copyright (C) DXHM 版权所有
 * 文件名： UploadControllor.java
 * 包名： com.mftcc.common.controllor
 * 说明： 
 * @author LiuYF
 * @date 2015-12-3 下午7:03:13
 * @version V1.0
 */
package com.mftcc.common.controllor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mftcc.common.SystemParm;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * 类名： UploadControllor <br>
 * 描述：用于上传文件。通过将form表单提交到/mftccManager/upload即可完成上传。上传成功后返回文件输入框name和保存后的文件名。
 * form（enctype="multipart/form-data"）下一个或多个<input type = "file" name = "file1"
 * @author LiuYF
 * @date 2015-12-3 下午7:03:13
 */
@Controller
public class UploadControllor {
	/**
	 * Gson：转换map数据->json。
	 */
	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	
	@RequestMapping(value="/upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String upload(HttpServletRequest request) {
		Map<String, String> savedFilesMap = new LinkedHashMap<String, String>();
		try {
			String filePath = getUploadDIr(request);
			
			System.out.println(filePath);// 输出存放上传文件所到的路径
			File uploadPath = new File(filePath);
			// 检查文件夹是否存在 不存在 创建一个
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			// 文件最大容量 20M
			int fileMaxSize = 20 * 1024 * 1024;
			// 存放文件描述
			@SuppressWarnings("unused")
			String[] fileDiscription = { null, null };
			// 文件名
			String fileName = null;
			// 上传文件数
			int fileCount = 0;
			// 重命名策略
			// RandomFileRenamePolicy rfrp = new RandomFileRenamePolicy();
			FileRenamePolicy rfrp = new DefaultFileRenamePolicy();
			// 上传文件
			MultipartRequest mulit = new MultipartRequest(request, filePath,
					fileMaxSize, "UTF-8", rfrp);// 取得上传文件

			@SuppressWarnings("rawtypes")
			Enumeration filesname = mulit.getFileNames();// 取得上传的所有文件(相当于标识)
			while (filesname.hasMoreElements()) {
				String name = (String) filesname.nextElement();// 标识
				System.out.println(name);
				fileName = mulit.getFilesystemName(name); // 取得文件名
				String contentType = mulit.getContentType(name);// 工具标识取得的文件类型
				if (fileName != null) {
					fileCount++;
				}
				System.out.println("文件名：" + fileName);
				System.out.println("文件类型： " + contentType);
				// "file1":"test.txt"
				savedFilesMap.put(name, fileName);
			}
			System.out.println("共上传" + fileCount + "个文件！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			savedFilesMap.put("fail", e.getMessage());
		}
		System.out.println(gson.toJson(savedFilesMap));
		// {"file1":"test.txt"}
		return gson.toJson(savedFilesMap);
	}

	/**
	 * 方法描述：存储路径：存到tomcat目录下的上传用文件夹。
	 * <pre>
	 * 通过System.getenv没能获得CATALINA_HOME，只能通过截取字符串来处理了。如果项目不是部署在webapps下，就可能失效了。
	 * </pre>
	 * @param request
	 * @return String filePath:以文件分隔符<strong>"/"结尾</strong>。
	 * @author LiuYF
	 * @date 2015-12-5 上午10:30:45
	 */
	private String getUploadDIr(HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/");
		filePath = filePath.substring(0, filePath.lastIndexOf(File.separator + "webapps" + File.separator) + 1);
		filePath = filePath + SystemParm.UPLOAD_DIR;
		
		return filePath;
	}
	
	/**
	 * 方法描述： 下载文件，访问"/mftccManager/download?filePath=文件相对路径"即可。
	 * @param filePath
	 * @param modelMap
	 * @param request
	 * @param response
	 * void
	 * @author LiuYF
	 * @date 2015-12-5 上午11:14:54
	 */
	@RequestMapping(value = "/download")
	public void download(String filePath, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		InputStream fis = null;
		OutputStream outputStream = null;
		try {
			// 拼接文件实际路径。
			String path = getUploadDIr(request) + File.separator + filePath;
			
			File file = new File(path);
			if (!file.exists()) {
				responseOut(response, "请求的文件不存在！");
				return;
			}
			String filename = file.getName();
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			
			outputStream = new BufferedOutputStream(response.getOutputStream());
			
			outputStream.write(buffer);// 输出文件
			outputStream.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(outputStream);
		}
	}

	/**
	 * 方法描述： 
	 * @param response
	 * void
	 * @author LiuYF
	 * @param arg 
	 * @date 2015-12-5 下午2:02:05
	 */
	private void responseOut(HttpServletResponse response, String arg) {
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(arg.getBytes("GBK"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

}
