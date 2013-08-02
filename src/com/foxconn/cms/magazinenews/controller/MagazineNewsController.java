package com.foxconn.cms.magazinenews.controller;

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

import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.impl.MagazineNewsServiceImpl;
import com.foxconn.pojo.SelectBean;
import com.foxconn.util.FileUploader;
import com.foxconn.util.FileUtils;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PaginationRelated;
import com.foxconn.util.SystemContext;
import com.sun.star.bridge.oleautomation.Date;

@Controller
@RequestMapping("/magazineNews.do")
public class MagazineNewsController {	
	@Autowired
	@Resource(name="magazineNewsImpl")
	private MagazineNewsServiceImpl magazineNewsImpl;
	
	@RequestMapping(params="action=getMagazineNewsList")
	public String getMagazineNewsList(Model model
			,@RequestParam("magazineTitle") String magazineTitle
			,@RequestParam("pressPerson") String pressPerson
			,@RequestParam("startTime") String startTime
			,@RequestParam("endTime") String endTime
			
			) throws UnsupportedEncodingException{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("magazineTitle", magazineTitle);
		map.put("pressPerson", pressPerson);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		List<MagazineNews> magazineNewsList=magazineNewsImpl.getMagazineNewsList(map);
		Page paginations=PaginationRelated.listToPage(magazineNewsList, 1, 15);
		model.addAttribute("paginations", paginations);
		return "magazinenews/magazineNewsList";
	}
	@ResponseBody
	@RequestMapping(params="action=queryMagazineNewsListByPageIndex")
	public Object queryMagazineNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("magazineTitle") String magazineTitle,
			@RequestParam("pressPerson") String pressPerson,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			HttpServletResponse response){
		
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("magazineTitle", magazineTitle);
		map.put("pressPerson", pressPerson);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<MagazineNews> magazineNewsList=magazineNewsImpl.getMagazineNewsList(map);
		Page page=PaginationRelated.listToPage(magazineNewsList,Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
		
		return page;
		/*response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
 @RequestMapping(params="action=turnToMagazineDetail")
	public String turnToMagazineDetail(Model model,
			@RequestParam("magazineID") String magazineID
			) throws UnsupportedEncodingException{
	 List<MagazineNews> magazineCatalogList=new ArrayList<MagazineNews>();
		List<MagazineNews> magazineContentList=new ArrayList<MagazineNews>();
		MagazineNews magazineInfo=new MagazineNews();
		Page catalogPaginations=new Page(0);
		Page contentPaginations=new Page(0);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("magazineID", magazineID);
		if(magazineID==""){
			magazineCatalogList=null;
			magazineContentList=null;
			magazineInfo=null;
			catalogPaginations=null;
			contentPaginations=null;
		}
		else{
			magazineInfo=magazineNewsImpl.getMagazineInfo(magazineID);
			magazineCatalogList=magazineNewsImpl.getMagazineCatalogList(magazineID);
			magazineContentList=magazineNewsImpl.getMagazineContentList(map);
			catalogPaginations=PaginationRelated.listToPage(magazineCatalogList, 1, 15);
			contentPaginations=PaginationRelated.listToPage(magazineContentList, 1, 15);
		}
		model.addAttribute("magazineInfo", magazineInfo);
		model.addAttribute("catalogPaginations", catalogPaginations);
		model.addAttribute("contentPaginations", contentPaginations);
		
		return "magazinenews/magazineDetail";
	}
	/*@RequestMapping(params="action=turnToMagazineDetail")
	public ModelAndView turnToMagazineDetail(
			@RequestParam("magazineID") String magazineID
			) throws UnsupportedEncodingException{
		List<MagazineNews> magazineCatalogList=new ArrayList<MagazineNews>();
		List<MagazineNews> magazineContentList=new ArrayList<MagazineNews>();
		MagazineNews magazineInfo=new MagazineNews();
		Page catalogPaginations=new Page(0);
		Page contentPaginations=new Page(0);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("magazineID", magazineID);
		if(magazineID==""){
			magazineCatalogList=null;
			magazineContentList=null;
			magazineInfo=null;
			catalogPaginations=null;
			contentPaginations=null;
		}
		else{
			magazineInfo=magazineNewsImpl.getMagazineInfo(magazineID);
			magazineCatalogList=magazineNewsImpl.getMagazineCatalogList(magazineID);
			magazineContentList=magazineNewsImpl.getMagazineContentList(map);
			catalogPaginations=PaginationRelated.listToPage(magazineCatalogList, 1, 10);
			contentPaginations=PaginationRelated.listToPage(magazineContentList, 1, 10);
		}
		
	    
		ModelAndView mav=new ModelAndView("magazinenews/magazineDetail");
		mav.addObject("magazineInfo", magazineInfo);
		mav.addObject("catalogPaginations", catalogPaginations);
		mav.addObject("contentPaginations", contentPaginations);
		
		return mav;
	}*/
	/*@RequestMapping(params="action=deleteMagazine")
	public void deleteOutline(@RequestParam("magazineID") String magazineID,
			HttpServletResponse response
			){
		
		String msg="";
		HashMap<String, String > map=new HashMap<String, String>();
	    map.put("userID",LdapContext.getUserName());
	    map.put("outlineID", outlineID);
try{
	courseOutlineServiceImpl.deleteOutline(map);
			msg="删除成功！";
		}
		catch (Exception e) {
			msg="刪除失敗！";
		}
			// TODO: handle exception
		
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(msg);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
	@ResponseBody
	@RequestMapping(params="action=deleteMagazine")
	public Object deleteMagazine(@RequestParam("magazineID") String magazineID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	   /* map.put("userID",LdapContext.getUserName());*/
	    map.put("magazineID", magazineID);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.deleteMagazine(map);
	toJson.put("msg","删除成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","删除失败！");
		}
		
		
		
		return toJson;
		}
	
	@ResponseBody
	@RequestMapping(params="action=reviewMagazine")
	public Object reviewMagazine(@RequestParam("magazineID") String magazineID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	   /* map.put("userID",LdapContext.getUserName());*/
	    map.put("magazineID", magazineID);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.reviewMagazine(map);
	toJson.put("msg","审核通过！");
		}
		catch (Exception ex) {
			toJson.put("msg","操作失败！");
		}
		
		
		
		return toJson;
		}
		
	@RequestMapping(params="action=turnToCatalogSet")
	public ModelAndView turnToCatalogSet(
			@RequestParam("catalogID") String catalogID,
			@RequestParam("magazineID") String magazineID,
			@RequestParam("magazineNum") String magazineNum
			){
		ModelAndView mav=new ModelAndView("magazinenews/catalogSet");
		if(catalogID!=""){
			MagazineNews catalogInfo=magazineNewsImpl.getCatalogInfo(catalogID);
			mav.addObject("catalogInfo", catalogInfo);
		}
		mav.addObject("magazineID", magazineID);
		mav.addObject("magazineNum", magazineNum);
		return mav;
		
	}
		
	@ResponseBody
	@RequestMapping(params="action=deleteCatalog")
	public Object deleteCatalog(@RequestParam("catalogID") String catalogID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	   /* map.put("userID",LdapContext.getUserName());*/
	    map.put("catalogID", catalogID);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.deleteCatalog(map);
	toJson.put("msg","删除成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","删除失败！");
		}
		
		
		
		return toJson;
		}
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=saveAsDraft")
	public String saveAsDraft(
			/*@ModelAttribute("lectureNote") LectureNote lectureNote,*/
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		MultipartFile photo = request.getFile("mainPhotoURL");
		MultipartFile file = request.getFile("magazineFileURL");
	    String magazineTitle=request.getParameter("magazineTitle");
		String magazineNum=request.getParameter("magazineNum");
		String approvaler=request.getParameter("approvaler");
		String magazineID=request.getParameter("magazineID");
		
		String projectPath=request.getSession().getServletContext().getRealPath("/"); 
		/*String projectPath=request.getSession().getServletContext().getRealPath("resource"); 发布后使用*/
		String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
		String path = diskSign+"/CMS/WebContent/resource/magazinenews/"+FileUploader.getTimeFolder()+"/";//发布前使用
		
		String msg="";
		String photoUpload="";
		String fileUpload="";
		Map<String, String> toJson = new HashMap<String, String>();
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("magazineTitle", magazineTitle);
		map.put("magazineNum", magazineNum);
		map.put("approvaler", approvaler);
		map.put("userID", SystemContext.getUserNO());
		if(!magazineID.equals("")){
			map.put("operType","edit");
		if(file.getSize()==0&&photo.getSize()==0){
			map.put("mainPhotosURL","");
			map.put("mainPhotosName", "");
			map.put("magazineFileURL","");
			map.put("magazineFileName", "");
		}
		else {
		if(photo.getSize()==0){
			map.put("mainPhotosURL","");
			map.put("mainPhotosName", "");
			map.put("magazineFileName", file.getOriginalFilename());
			map.put("magazineFileURL",FileUploader.uploadFile(request,file,path)); 
		}
		else {
		if(file.getSize()==0){
			map.put("magazineFileURL","");
			map.put("magazineFileName", "");
			map.put("mainPhotosName", photo.getOriginalFilename());
			map.put("mainPhotosURL",FileUploader.uploadFile(request,photo,path)); 
		}
		}
		}
		/*else {
			map.put("magazineFileUrl",photo.getOriginalFilename());
			map.put("magazineFileName", FileUploader.uploadFile(request,photo));
			map.put("mainPhotosUrl", file.getOriginalFilename());
			map.put("magazineFileName",FileUploader.uploadFile(request,file)); 
			
		}*/
		/*try{
			map.put("operType", "edit");
			magazineNewsImpl.saveAsDraft(map);
			
			toJson.put("msg","编辑成功！");
			
			
		}
		catch (Exception e) {
			toJson.put("msg","编辑失败！");
			e.printStackTrace();
			
		}*/
		}
		else{
			map.put("operType","add");
		    magazineID=UUID.randomUUID().toString().replace("-", "");
			/*map.put("magazineFileUrl",photo.getOriginalFilename());
			map.put("magazineFileName", FileUploader.uploadFile(request,photo));
			map.put("mainPhotosUrl", file.getOriginalFilename());
			map.put("magazineFileName",FileUploader.uploadFile(request,file)); 
			photoUpload=FileUploader.uploadFile(request,photo);
			fileUpload=FileUploader.uploadFile(request,file);*/
			
			
			/*toJson.put("msg","上传成功！");
			toJson.put("magazineID", magazineID);*/
			
	}
		if(file.getSize()!=0&&photo.getSize()!=0) {
			map.put("magazineFileName",file.getOriginalFilename());
			map.put("mainPhotosURL", FileUploader.uploadFile(request,photo,path));
			map.put("mainPhotosName", photo.getOriginalFilename());
			map.put("magazineFileURL",FileUploader.uploadFile(request,file,path)); 
			/*photoUpload=FileUploader.uploadFile(request,photo,"");
			fileUpload=FileUploader.uploadFile(request,file,"");*/
		}
		map.put("magazineID", magazineID);
		try{
			
			magazineNewsImpl.saveAsDraft(map);
			
			msg="已保存为草稿！";
			
			
		}
		catch (Exception e) {
			msg="保存草稿失败！";
			e.printStackTrace();
			
		}
		model.addAttribute("msg", msg);
		return turnToMagazineDetail(model,magazineID);
	}
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,params="action=distributeMagazineNews")
	public Object distributeMagazineNews(
		    @RequestParam("magazineID") String magazineID
			)throws IOException{
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("magazineID", magazineID);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.distributeMagazineNews(map);
	toJson.put("msg","发稿成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","发稿失败！");
		}
		
		
		
		return toJson;
		
	}
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,params="action=saveCatalog")
	public Object saveCatalog(
		    @RequestParam("magazineID") String magazineID,
		    @RequestParam("catalogID") String catalogID,
		    @RequestParam("catalogName") String catalogName,
		    @RequestParam("catalogOrder") String catalogOrder,
		    @RequestParam("magazineNum") String magazineNum
			)throws IOException{
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	    map.put("magazineID", magazineID);
	    map.put("catalogID",catalogID );
	    map.put("catalogName",catalogName );
	    map.put("catalogOrder", catalogOrder);
	    map.put("magazineNum",magazineNum );
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.saveCatalog(map);
	toJson.put("msg","保存成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","保存失败！");
		}
		
		
		
		return toJson;
		
	}
	
	@RequestMapping(params="action=turnToContentSet")
	public String turnToContentSet(Model model,
			@RequestParam("contentID") String contentID,
			@RequestParam("catalogID") String catalogID,
			@RequestParam("magazineID") String magazineID
			) throws UnsupportedEncodingException{
		if(contentID!=""){
			MagazineNews contentInfo=magazineNewsImpl.getContentInfo(contentID);
			model.addAttribute("contentInfo", contentInfo);
		}
		
		model.addAttribute("catalogID", catalogID);
		model.addAttribute("magazineID", magazineID);
		return "magazinenews/contentSet";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,params="action=saveContent")
	public Object saveContent(
			@RequestParam("contentID") String contentID,
			@RequestParam("catalogID") String catalogID,
		    @RequestParam("contentTitle") String contentTitle,
		    @RequestParam("subContentTitle") String subContentTitle,
		    @RequestParam("pressPerson") String pressPerson,
		    @RequestParam("recordDate") String recordDate,
		    @RequestParam("contentOrder") String contentOrder,
		    @RequestParam("contentSource") String contentSource,
		    @RequestParam("keyWord") String keyWord,
		    @RequestParam("summary") String summary,
		    @RequestParam("content") String content
			)throws IOException{
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
		map.put("contentID", contentID);
	    map.put("catalogID", catalogID);
	    map.put("contentTitle",contentTitle );
	    map.put("subContentTitle",subContentTitle );
	    map.put("pressPerson", pressPerson);
	    map.put("recordDate",recordDate );
	    map.put("contentOrder", contentOrder);
	    map.put("contentSource",contentSource);
	    map.put("keyWord",keyWord);
	    map.put("summary", summary);
	    map.put("content",content);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.saveContent(map);
	toJson.put("msg","保存成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","保存失败！");
		}
		
		
		
		return toJson;
		
	}
	@ResponseBody
	@RequestMapping(params="action=deleteContent")
	public Object deleteContent(@RequestParam("contentID") String contentID,
			HttpServletResponse response
			){
		HashMap<String, String > map=new HashMap<String, String>();
		Map<String, String> toJson = new HashMap<String, String>();
	   /* map.put("userID",LdapContext.getUserName());*/
	    map.put("contentID", contentID);
	    map.put("userID", SystemContext.getUserNO());
try{
	magazineNewsImpl.deleteContent(map);
	toJson.put("msg","删除成功！");
		}
		catch (Exception ex) {
			toJson.put("msg","删除失败！");
		}
		
		
		
		return toJson;
		}
	@ResponseBody
	@RequestMapping(params="action=getContentSourceList")
	public List<SelectBean> getContentSourceList(
			){
		
		
		return magazineNewsImpl.getContentSourceList();
		}
	@ResponseBody
	@RequestMapping(params="action=getMagazineNumList")
	public List<SelectBean> getMagazineNumList(
			){
		
		
		return magazineNewsImpl.getMagazineNumList();
		}
	 @RequestMapping(params="action=previewMagazineNews")
		public String previewMagazine(Model model,
				@RequestParam("magazineID") String magazineID
				) throws UnsupportedEncodingException{
		 
		 List<MagazineNews> magazineDetailList=magazineNewsImpl.getMagazineDetailByID(magazineID);
		 MagazineNews firstContent=new MagazineNews();
		if(magazineDetailList.size()!=0){
			firstContent=magazineDetailList.get(0);
		}
		else{
			firstContent=null;
		}
			MagazineNews magazineInfo=magazineNewsImpl.getMagazineInfo(magazineID);
			/*model.addAttribute("photoURL",new String(photoURL.getBytes("ISO-8859-1"),"utf-8"));*/
			/*List<SelectBean> magazineNumList=magazineNewsImpl.getMagazineNumList();*/
			/*model.addAttribute("magazineNum",magazineInfo.getMagazineNum());
			model.addAttribute("magazineTitle",magazineInfo.getMagazineTitle());
			model.addAttribute("photoURL",magazineInfo.getPhotoURL());
			model.addAttribute("fileURL",magazineInfo.getFileURL());*/
			model.addAttribute("firstContent",firstContent);
			model.addAttribute("magazineInfo", magazineInfo);
			model.addAttribute("magazineNewsList",magazineDetailList);
			/*model.addAttribute("magazineNumList",magazineNumList);*/
			return "magazinenews/magazineNews";
		}
	 @RequestMapping(params="action=getContentInfo")
		public void getContentInfo(Model model,
				@RequestParam("contentID") String contentID,
				HttpServletResponse response
				){
			MagazineNews content=magazineNewsImpl.getContentInfo(contentID);
			response.setCharacterEncoding("UTF-8");
			try{
				response.getWriter().write(JsonUtil.transferObjectToJSon(content));
			}catch (Exception e) {
				e.printStackTrace();
			}
			;
		}
	 @RequestMapping(params="action=downloadMagazine")
		public void downloadMagazine(
				Model model,
				@RequestParam("magazineURL") String magazineURL,
				HttpServletResponse response,HttpServletRequest request
				){
			String path=request.getSession().getServletContext().getRealPath("/");//项目相对路径取法
			try {
				FileUtils.downLoadFile(response, request, path+magazineURL);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			 
			
		}
	
}
