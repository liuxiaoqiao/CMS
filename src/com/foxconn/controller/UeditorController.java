package com.foxconn.controller;



import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.util.Uploader;

@Controller
@RequestMapping("/ueidtor.do")
public class UeditorController {
	
	
		
	@RequestMapping(params="action=fileUp")
	public void home(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");

	    Uploader up = new Uploader(request);
	    up.setSavePath("upload"); //保存路径
	    String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv"};  //允许的文件类型
	    up.setAllowFiles(fileType);
	    up.setMaxSize(10000);        //允许的文件最大尺寸，单位KB
	    up.upload();
	    response.getWriter().print("{'url':'"+up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

		/*return "ueditor/fileUp";*/
	}

	
}
