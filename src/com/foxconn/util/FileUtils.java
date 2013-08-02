package com.foxconn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileUtils {
	
	public static void deleteFile(File oldPath) {
		if(oldPath.exists()) {
			if (oldPath.isDirectory()) {
				File[] files = oldPath.listFiles();
				for (File file : files) {
					deleteFile(file);		     
				}
				oldPath.delete();
			}else{
				oldPath.delete();
			}
		}
	}
	/**
	 * @author F3228761
	 * @date : Jul 11, 2013 2:22:48 PM
	 * @description 根据文件路径下载文件
	 */
	public static void downLoadFile(HttpServletResponse response,HttpServletRequest request,
			String filePath) throws IOException {
		//　转码，防止乱码
		filePath = URLDecoder.decode(filePath, "UTF-8");
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
	//		System.out.println("firefox");
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器

		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
	//		System.out.println("IE");
			fileName = URLEncoder.encode(fileName, "UTF-8");//IE浏览器
		}
		response.reset();		
		response.setContentType("application/msexcel;charset=UTF-8");
		
//		System.out.println("filePath==" + filePath);
//		System.out.println("fileName==" + fileName);
		response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Connection", "close");
		ServletOutputStream fos = response.getOutputStream();			
		File file1 = new File(filePath);
		FileInputStream is = null;		
		if(file1.exists()){
			is = new FileInputStream(file1);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				try {
					fos.write(buf, 0, len);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			is.close();
			fos.close();
		}
		
	}
}
