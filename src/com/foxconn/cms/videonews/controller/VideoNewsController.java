package com.foxconn.cms.videonews.controller;

import java.io.File;
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
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.impl.MagazineNewsServiceImpl;
import com.foxconn.cms.videonews.pojo.VideoNews;
import com.foxconn.cms.videonews.service.VideoNewsService;
import com.foxconn.pojo.SelectBean;
import com.foxconn.util.FileUploader;
import com.foxconn.util.FileUtils;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PaginationRelated;
import com.foxconn.util.SystemContext;

@Controller
@RequestMapping("/videoNews.do")
public class VideoNewsController {	
	@Autowired
	@Resource(name="videoNewsServiceImpl")
	private VideoNewsService videoNewsServiceImpl;
	
	@RequestMapping(params="action=turnToVideoNewsList")
	public String getVideoNewsList(Model model){
		
		
		model.addAttribute("roleName",  SystemContext.getRoleName());
		model.addAttribute("userNo",  SystemContext.getUserNO());
		return "videonews/videoNewsList";
	}
	@ResponseBody
	@RequestMapping(params="action=queryVideoNewsListByPageIndex")
	public Object getVideoNewsList(Model model
			,@RequestParam("pageIndex") String pageIndex
			,@RequestParam("pageSize") String pageSize
			,@RequestParam("videoNewsName") String videoNewsName
			,@RequestParam("entryUser") String entryUser
			,@RequestParam("startTime") String startTime
			,@RequestParam("endTime") String endTime
			
			) throws UnsupportedEncodingException{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("videoNewsName", videoNewsName);
		map.put("entryUser", entryUser);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		List<VideoNews> videoNewsList=videoNewsServiceImpl.getVideoNewsList(map);
		Page page=PaginationRelated.listToPage(videoNewsList,Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
		return page;
	}
	@RequestMapping(params="action=turnToVideoNewsSet")
	public String turnToAddInterviewOnline(
			@RequestParam("videoNewsId") String videoNewsId,
			Model model
			){
		VideoNews videoNews=new VideoNews();
		if(videoNewsId!=""){
			videoNews=videoNewsServiceImpl.getVideoNewsInfo(videoNewsId);
		}
		model.addAttribute("videoNews", videoNews);
		model.addAttribute("roleName",  SystemContext.getRoleName());
		model.addAttribute("userNo",  SystemContext.getUserNO());
		return "videonews/videoNewsSet";
		
	}
	
	@ResponseBody
	@RequestMapping(params="action=deleteVideoNews")
	public Object deleteVideoNews(@RequestParam("videoNewsId") String videoNewsId,
			@RequestParam("videoURL") String videoURL,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("videoNewsId", videoNewsId);
	    map.put("userID",  SystemContext.getUserNO());
try{
	videoNewsServiceImpl.deleteVideoNews(map);
	File file = new File(videoURL);
 
   	 file.delete(); 
	toJson.put("msg","删除成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","删除失败！");
		}
		
		
		
		return toJson;
		}
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=saveVideoNews")
	public void saveVideoNews(
			@ModelAttribute("videoNews") VideoNews videoNews,
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		MultipartFile mainPhoto = request.getFile("mainPhoto");
		MultipartFile video = request.getFile("video");
		String projectPath=request.getSession().getServletContext().getRealPath("/"); 
		//String projectPath=request.getSession().getServletContext().getRealPath("resource"); 发布后使用
		String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
		String path = diskSign+"/CMS/WebContent/resource/videonews/"+ FileUploader.getTimeFolder()+"/";//发布前使用
		String uuid=UUID.randomUUID().toString().replace("-", "");
		String msg="";
		String curPhotoUpload="";
		String guestPhotoUpload="";
		Map<String, String> toJson = new HashMap<String, String>();
		videoNews.setOperUser(SystemContext.getUserNO());
		if(!videoNews.getVideoNewsId().equals("")){
			videoNews.setOperFlag("edit");
		if(mainPhoto.getSize()==0 &&video.getSize()==0){//标记下：当使用chrome浏览器提交，没有file时，此处guestPhoto为null,使用getSize()方法就会报错
			videoNews.setVideoName("");
			videoNews.setVideoURL("");
			videoNews.setMainPhotosName("");
			videoNews.setMainPhotosUrl("");
		}
		else {
		if(mainPhoto.getSize()==0 ){
			videoNews.setMainPhotosName("");
			videoNews.setMainPhotosUrl("");
			videoNews.setVideoName(video.getOriginalFilename());
			videoNews.setVideoURL(FileUploader.uploadFile(request,video,path)); 
		}
		else {
		if(video.getSize()==0 ){
			videoNews.setVideoName("");
			videoNews.setVideoURL("");
			videoNews.setMainPhotosName(mainPhoto.getOriginalFilename());
			videoNews.setMainPhotosUrl(FileUploader.uploadFile(request,mainPhoto,path)); 
		}
		}
		}
		}
		else{
			videoNews.setOperFlag("add");
			videoNews.setVideoNewsId(uuid);
			
	}
		/*if(guestPhoto!=null&&curPhoto!=null){*/
		if(mainPhoto.getSize()!=0&&video.getSize()!=0) {
			videoNews.setMainPhotosName(mainPhoto.getOriginalFilename());
			videoNews.setMainPhotosUrl(FileUploader.uploadFile(request,mainPhoto,path)); 
			videoNews.setVideoURL(FileUploader.uploadFile(request,video,path));
			videoNews.setVideoName(video.getOriginalFilename());
			
		}
		/*}*/
		//map.put("yearBooksID", yearBooksID);
		try{
			
			videoNewsServiceImpl.saveVideoNews(videoNews);
			if(videoNews.getOperType().equals("5")){
			toJson.put("msg", "存草稿成功！");
			toJson.put("interviewID",videoNews.getVideoNewsId());
			}
			else
			{
				toJson.put("msg", "发表成功！");
			}
			
			
		}
		catch (Exception e) {
			if(videoNews.getOperType().equals("5")){
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
	@ResponseBody
	@RequestMapping(params="action=reviewVideoNews")
	public Object reviewInterviewOnline(@RequestParam("videoNewsId") String videoNewsId,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("videoNewsId", videoNewsId);
	    map.put("userID", SystemContext.getUserNO());
try{
	videoNewsServiceImpl.reviewVideoNews(map);
	toJson.put("msg","审核成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","审核失败！");
		}
		
		
		
		return toJson;
		}
	@ResponseBody
	@RequestMapping(params="action=sendBackVideoNews")
	public Object sendBackVideoNews(
			@RequestParam("videoNewsId") String videoNewsId,
			@RequestParam("refuseDesc") String refuseDesc,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
		map.put("refuseDesc", refuseDesc);
	    map.put("videoNewsId", videoNewsId);
	    map.put("userID", SystemContext.getUserNO());
try{
	videoNewsServiceImpl.sendBackVideoNews(map);
	toJson.put("msg","已退回！");
		}
		catch (Exception ex) {
			toJson.put("msg","退回失败！");
		}
		
		
		
		return toJson;
		}
	@RequestMapping(params = "action=previewVideoNews")
	public String showVideo(HttpServletRequest request,
			@RequestParam("newsID") String newsID,
			Model model) {
		//通过视频名称来播放视频
		VideoNews videoNews = videoNewsServiceImpl.getVideoNameByID(newsID);
		model.addAttribute("videoName", videoNews.getVideoNewsName());
		model.addAttribute("videoPath", videoNews.getVideoURL());
		
		return "videonews/playvideo";
	}
	
}
