package com.foxconn.cms.leaderinfo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.foxconn.cms.leaderinfo.service.LeaderInfoService;
import com.foxconn.cms.leaderinfo.pojo.LeaderInfo;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.cms.textnews.service.TextNewsService;
import com.foxconn.util.FileUploader;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.SystemContext;



@Controller
@RequestMapping("leaderInfo")
public class LeaderInfoController {
	@Autowired
	@Resource(name = "leaderInfoServiceImpl")
	private LeaderInfoService leaderInfoServiceImpl;
	
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;
	
	@RequestMapping(params = "action=getLeaderInfoList")
	public String getLeaderInfoList(HttpServletRequest request,
			Model model) {
		 LeaderInfo leaderInfo = new LeaderInfo();
		 List <LeaderInfo> leaderInfoList = leaderInfoServiceImpl.getLeaderInfoList(leaderInfo);	 
		 Page page=listToPage(leaderInfoList, 1,15);
		 
		 model.addAttribute("paginations", page);
		 
		 return "leaderinfo/leaderManage";
	}
	
	@RequestMapping(params="action=queryLeaderInfoListByPageIndex")
	public void queryLeaderInfoListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			HttpServletResponse response){
		 LeaderInfo leaderInfo = new LeaderInfo();	 
		 List <LeaderInfo> leaderInfoList = leaderInfoServiceImpl.getLeaderInfoList(leaderInfo);
		Page page=listToPage(leaderInfoList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="action=deleteLeaderInfo")
	public void deleteLeaderInfo(
			Model model,@RequestParam("leaderID") String leaderID,
			HttpServletResponse response
			){
		
		String msg="";
       try{
    	   LeaderInfo leaderInfo = new LeaderInfo();
    	   leaderInfo.setLeaderID(leaderID);
    	   leaderInfo.setModifyUser(SystemContext.getUserNO());
    	   leaderInfoServiceImpl.deleteLeaderInfo(leaderInfo);
			msg="删除成功！";
		}
		catch (Exception e) {
			msg="刪除失敗！";
		}		
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @param leaderID
	 * @param sFlag  1:新增；2.編輯；3.審核
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=turnToAddLeaderInfo")
	public String turnToAddLeaderInfo(
			@RequestParam("leaderID") String leaderID,
			@RequestParam("sFlag") String sFlag,
			Model model
			){
		LeaderInfo leaderInfo = new LeaderInfo();
		LeaderInfo leaderInfo1= new LeaderInfo();
		
		if(leaderID!=null&&!("".equals(leaderID))){
			leaderInfo.setLeaderID(leaderID);
	      leaderInfo1 = leaderInfoServiceImpl.getLeaderInfoDetail(leaderInfo);
		}
		
		String isPreViewHide="display:none";
		String isDraftHide="display:none";
		String isPublishHide="display:none";
		String isRefuseHide="display:none";
		String isAppHide="display:none";
		
		if("1".equals(sFlag)){
			isPreViewHide="";
			isDraftHide="";
			isPublishHide="";
		}else if("2".equals(sFlag)){
			isPreViewHide="";
			isDraftHide="";
			isPublishHide="";
		}else{
			isRefuseHide="";
			isAppHide="";
		}
		
		model.addAttribute("isPreViewHide", isPreViewHide);
		model.addAttribute("isDraftHide", isDraftHide);
		model.addAttribute("isPublishHide", isPublishHide);
		model.addAttribute("isRefuseHide", isRefuseHide);
		model.addAttribute("isAppHide", isAppHide);
		
		model.addAttribute("leaderID", leaderID);
		model.addAttribute("leaderName", leaderInfo1.getLeaderName());
		model.addAttribute("leaderPost", leaderInfo1.getLeaderPost());
		model.addAttribute("leaderOrder",leaderInfo1.getLeaderOrder());
		model.addAttribute("approvaler", leaderInfo1.getApprovaler());
		model.addAttribute("leaderResume", leaderInfo1.getLeaderResume());
		model.addAttribute("leaderPhotosNameOld", leaderInfo1.getLeaderPhotosName());
		model.addAttribute("leaderPhotosUrlOld", leaderInfo1.getLeaderPhotosUrl());
		return "leaderinfo/addLeaderInfo";
		
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=addOrEditLeaderInfo")
	public void addOrEditLeaderInfo(
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		LeaderInfo leaderInfo = new LeaderInfo();
		boolean isInsert=true;
		MultipartFile leaderPhotos = request.getFile("leaderPhotos");
		String leaderID=request.getParameter("leaderID");
		String actFlag =request.getParameter("actFlag");
	    String leaderName=request.getParameter("leaderName");
	    String leaderPost=request.getParameter("leaderPost");
	    int leaderOrder = Integer.parseInt(request.getParameter("leaderOrder"));
	    String approvaler=request.getParameter("approvaler");
	    String leaderResume = request.getParameter("leaderResume").replaceAll("\"", "\'");
		String projectPath=request.getSession().getServletContext().getRealPath("/"); 
		/*String projectPath=request.getSession().getServletContext().getRealPath("resource"); 发布后使用*/
		String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
		String path = diskSign+"/CMS/WebContent/resource/leaderinfo/"+ FileUploader.getTimeFolder()+"/";//发布前使用
		
		String msg="";
		Map<String, String> toJson = new HashMap<String, String>();
	
		if(leaderID!=null&&!("".equals(leaderID))){
			isInsert=false;
			leaderInfo.setLeaderID(leaderID);
		}
		else{			
			isInsert=true;
			leaderInfo.setEntryUser(SystemContext.getUserNO());  //登錄用戶，後續修改
	      }
		leaderInfo.setModifyUser(SystemContext.getUserNO());  //登錄用戶，後續修改
		leaderInfo.setLeaderOrder(leaderOrder);
		leaderInfo.setLeaderPost(leaderPost);
		leaderInfo.setLeaderName(leaderName);
		leaderInfo.setApprovaler(approvaler);
		leaderInfo.setLeaderResume(leaderResume);
		leaderInfo.setStatusID(Integer.parseInt(actFlag));
		
		if(leaderPhotos.getSize()!=0) {
			leaderInfo.setLeaderPhotosUrl(FileUploader.uploadFile(request,leaderPhotos,path));
			leaderInfo.setLeaderPhotosName(leaderPhotos.getOriginalFilename());
		}else{
			String leaderPhotosName = request.getParameter("leaderPhotosNameOld");
			String leaderPhotosUrl =request.getParameter("leaderPhotosUrlOld");
			leaderInfo.setLeaderPhotosUrl(leaderPhotosUrl);
			leaderInfo.setLeaderPhotosName(leaderPhotosName);
		}
		try{
			if(isInsert==true){
				leaderInfoServiceImpl.insertLeaderInfo(leaderInfo);
			}else{
				leaderInfoServiceImpl.updateLeaderInfo(leaderInfo);
			}
			toJson.put("msg", "保存成功！");		
		}
		catch (Exception e) {
			toJson.put("msg", "保存失败！");
			e.printStackTrace();
			
		}
		
		try{
			
			response.setContentType("text/html; charset=utf-8"); //ajaxForm异步提交，关键语句
			response.getWriter().write(JsonUtil.toJsonString(toJson));//可用于转MAP
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(params = "action=openRefuseLeaderInfo")
	public String openRefuseLeaderInfo(
			@RequestParam("leaderID") String leaderID,
            HttpServletRequest request,
			Model model) {
		model.addAttribute("newsID", leaderID);
		model.addAttribute("newsType","13");
		return "textnews/refuseTextNews";
	}
	
	@RequestMapping(params="action=insertRefuseLeaderInfo")
	public void insertRefuseLeaderInfo(
			@RequestParam("newsID") String newsID,
			@RequestParam("newsType") String newsType,
			@RequestParam("refuseDesc") String refuseDesc,
			HttpServletResponse response){
		NewsRefuseRecord newsRefuseRecord = new NewsRefuseRecord();
		newsRefuseRecord.setNewsID(newsID);
		newsRefuseRecord.setNewsType(Integer.parseInt(newsType));
		newsRefuseRecord.setEntryUser(SystemContext.getUserNO());
		newsRefuseRecord.setRefuseDesc(refuseDesc);
		textNewsServiceImpl.insertRefuseTextNews(newsRefuseRecord);
		
		LeaderInfo leaderInfo = new LeaderInfo();
		leaderInfo.setLeaderID(newsID);
		leaderInfo.setStatusID(8);
		leaderInfo.setModifyUser(SystemContext.getUserNO());
		leaderInfoServiceImpl.updateRefuseLeaderStatus(leaderInfo);
		
		response.setCharacterEncoding("UTF-8");
	}	
	
	
	@RequestMapping(params = "action=getLeaderRecentActList")
	public String getLeaderRecentActList(HttpServletRequest request,
			@RequestParam("programType") String programType,
			@RequestParam("leaderName") String leaderName,
			Model model) {
		 TextNews textNews = new TextNews();
		 textNews.setProgramType(programType);
		 
		 List <TextNews> textNewsList = textNewsServiceImpl.getTextNewsList(textNews);	 
		 Page page=listToPage(textNewsList, 1,15);
		 try{
			 leaderName=java.net.URLDecoder.decode(leaderName,"UTF-8");
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 
		 model.addAttribute("paginations", page);
		 model.addAttribute("programType", programType);
		 model.addAttribute("leaderName", leaderName);
		 
		return "leaderinfo/leaderRecentAct";
	}
	
	@RequestMapping(params="action=queryLeaderRecentActListByPageIndex")
	public void queryTextNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("programType") String programType,
			HttpServletResponse response){
		 TextNews textNews = new TextNews();
		 textNews.setProgramType(programType);	 
			 
		 List <TextNews> textNewsList = textNewsServiceImpl.getTextNewsList(textNews);
		 Page page=listToPage(textNewsList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 分页转化
	 * @param list
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page listToPage(List list,Integer pageIndex,Integer pageSize){
		Page page=null;
		int index=pageIndex.intValue();
		int size=pageSize.intValue();
		long totalCount=list.size();
		int fromIndex=0;
		int toIndex=0;
		if(index*size<totalCount){
			fromIndex=(index-1)*size;
			toIndex=index*size;
		}else{
			fromIndex=(index-1)*size;
			toIndex=list.size();
		}
		list=list.subList(fromIndex, toIndex);
		page=new Page(index, size, totalCount, list);
		return page;
	}

}
