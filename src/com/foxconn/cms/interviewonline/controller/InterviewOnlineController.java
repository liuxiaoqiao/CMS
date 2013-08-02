package com.foxconn.cms.interviewonline.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.foxconn.cms.interviewonline.pojo.InterviewOnline;
import com.foxconn.cms.interviewonline.service.InterviewOnlineService;
import com.foxconn.cms.interviewonline.service.impl.InterviewOnlineServiceImpl;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.impl.MagazineNewsServiceImpl;
import com.foxconn.cms.yearbooks.pojo.YearBooks;
import com.foxconn.cms.yearbooks.service.impl.YearBooksServiceImpl;
import com.foxconn.pojo.SelectBean;
import com.foxconn.util.FileUploader;
import com.foxconn.util.FileUtils;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PaginationRelated;
import com.foxconn.util.SystemContext;
import com.sun.star.bridge.oleautomation.Date;

@Controller
@RequestMapping("/interviewOnline.do")
public class InterviewOnlineController {	
	@Autowired
	@Resource(name="interviewOnlineServiceImpl")
	private InterviewOnlineService interviewOnlineServiceImpl;
	
	@RequestMapping(params="action=turnToInterviewOnline")
	public String turnToInterviewOnline(
			){
		
		return "interviewonline/interviewOnlineList";
		
	}
	@ResponseBody
	@RequestMapping(params="action=queryInterviewOnlineListByPageIndex")
	public Object queryMagazineNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("interviewTitle") String interviewTitle,
			HttpServletResponse response){
		
		List<InterviewOnline> interviewOnline=interviewOnlineServiceImpl.getInterviewOnlineList(interviewTitle);
		Page page=PaginationRelated.listToPage(interviewOnline,Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
		
		return page;
	}
	@RequestMapping(params="action=turnToAddInterviewOnline")
	public String turnToAddInterviewOnline(
			@RequestParam("interviewID") String interviewID,
			Model model
			){
		InterviewOnline interviewOnlineInfo=new InterviewOnline();
		if(interviewID!=""){
		 interviewOnlineInfo=interviewOnlineServiceImpl.getInterviewOnlineInfo(interviewID);
		}
		model.addAttribute("interviewOnline", interviewOnlineInfo);
		return "interviewonline/addInterviewOnline";
		
	}
	@ResponseBody
	@RequestMapping(params="action=deleteInterviewOnline")
	public Object deleteInterviewOnline(@RequestParam("interviewID") String interviewID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("interviewID", interviewID);
	    map.put("userID", SystemContext.getUserNO());
try{
	interviewOnlineServiceImpl.deleteInterviewOnline(map);
	toJson.put("msg","删除成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","删除失败！");
		}
		
		
		
		return toJson;
		}
	@ResponseBody
	@RequestMapping(params="action=reviewInterviewOnline")
	public Object reviewInterviewOnline(@RequestParam("interviewID") String interviewID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("interviewID", interviewID);
	    map.put("userID", SystemContext.getUserNO());
try{
	interviewOnlineServiceImpl.reviewInterviewOnline(map);
	toJson.put("msg","审核成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","审核失败！");
		}
		
		
		
		return toJson;
		}
	
	
	@ResponseBody
	@RequestMapping(params="action=sendBackInterviewOnline")
	public Object sendBackInterviewOnline(
			@RequestParam("interviewID") String interviewID,
			@RequestParam("refuseDesc") String refuseDesc,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
		map.put("refuseDesc", refuseDesc);
	    map.put("interviewID", interviewID);
	    map.put("userID", SystemContext.getUserNO());
try{
	interviewOnlineServiceImpl.sendBackInterviewOnline(map);
	toJson.put("msg","已退回！");
		}
		catch (Exception ex) {
			toJson.put("msg","退回失败！");
		}
		
		
		
		return toJson;
		}
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=saveInterviewOnline")
	public void saveInterviewOnline(
			@ModelAttribute("interviewOnline") InterviewOnline interviewOnline,
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		MultipartFile curPhoto = request.getFile("curPhoto");
		MultipartFile guestPhoto = request.getFile("guestPhoto");
		String projectPath=request.getSession().getServletContext().getRealPath("/"); 
		//String projectPath=request.getSession().getServletContext().getRealPath("resource"); 发布后使用
		String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
		String path = diskSign+"/CMS/WebContent/resource/interviewonline/"+ FileUploader.getTimeFolder()+"/";//发布前使用
		String uuid=UUID.randomUUID().toString().replace("-", "");
		String msg="";
		String curPhotoUpload="";
		String guestPhotoUpload="";
		Map<String, String> toJson = new HashMap<String, String>();
		interviewOnline.setOperUser(SystemContext.getUserNO());
		if(!interviewOnline.getInterviewID().equals("")){
			interviewOnline.setOperFlag("edit");
		if(guestPhoto.getSize()==0 &&curPhoto.getSize()==0){//标记下：当使用chrome浏览器提交，没有file时，此处guestPhoto为null,使用getSize()方法就会报错
			interviewOnline.setCurPhotoName("");
			interviewOnline.setCurPhotoURL("");
			interviewOnline.setGuestPhotoName("");
			interviewOnline.setGuestPhotoURL("");
		}
		else {
		if(curPhoto.getSize()==0 ){
			interviewOnline.setCurPhotoName("");
			interviewOnline.setCurPhotoURL("");
			interviewOnline.setGuestPhotoName(guestPhoto.getOriginalFilename());
			interviewOnline.setGuestPhotoURL(FileUploader.uploadFile(request,guestPhoto,path)); 
		}
		else {
		if(guestPhoto.getSize()==0 ){
			interviewOnline.setGuestPhotoName("");
			interviewOnline.setGuestPhotoURL("");
			interviewOnline.setCurPhotoName(curPhoto.getOriginalFilename());
			interviewOnline.setCurPhotoURL(FileUploader.uploadFile(request,curPhoto,path)); 
		}
		}
		}
		}
		else{
			interviewOnline.setOperFlag("add");
			interviewOnline.setInterviewID(uuid);
			
	}
		/*if(guestPhoto!=null&&curPhoto!=null){*/
		if(guestPhoto.getSize()!=0&&curPhoto.getSize()!=0) {
			interviewOnline.setGuestPhotoName(guestPhoto.getOriginalFilename());
			interviewOnline.setGuestPhotoURL(FileUploader.uploadFile(request,guestPhoto,path)); 
			interviewOnline.setCurPhotoURL(FileUploader.uploadFile(request,curPhoto,path));
			interviewOnline.setCurPhotoName(curPhoto.getOriginalFilename());
			
		}
		/*}*/
		//map.put("yearBooksID", yearBooksID);
		try{
			
			interviewOnlineServiceImpl.saveInterviewOnline(interviewOnline);
			if(interviewOnline.getOperType().equals("5")){
			toJson.put("msg", "存草稿成功！");
			toJson.put("interviewID",interviewOnline.getInterviewID());
			}
			else
			{
				toJson.put("msg", "发表成功！");
			}
			
			
		}
		catch (Exception e) {
			if(interviewOnline.getOperType().equals("5")){
			toJson.put("msg", "存草稿失败！");
			}
			else
			{
				toJson.put("msg", "发表失败！");
			}
			e.printStackTrace();
			
		}
		
		try{
			
			response.setContentType("text/html; charset=utf-8"); //ajaxForm异步提交，关键语句
			response.getWriter().write(JsonUtil.toJsonString(toJson));//可用于转MAP
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
