package com.foxconn.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传测试服务端.
 *
 */
public class CodeSignServlet extends HttpServlet {
private static final long serialVersionUID = -1185375939464653684L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException,ServletException {
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws IOException,ServletException {
		System.out.println("doPosting....");
		response.setContentType("text/html;charset=utf-8");
		SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd");
		String date =   dateFormat.format(new Date()); 
		System.out.println("current date: " + date);
		FileUploader fu = new FileUploader(request, "");
		String success = fu.upload();
		System.out.println("success=======================:" + success);	
		
		if (!success.equals("")) {
			write(response, URLEncoder.encode(success, "UTF-8"));
		} else {
			write(response, "N");
		}
	}
	
	private void write(HttpServletResponse response, String message) throws IOException {
		response.getWriter().write(message);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
