package com.foxconn.cms.specialcolumn.controller;

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

import com.foxconn.cms.specialcolumn.service.SpecialColumnServie;
import com.foxconn.cms.specialcolumn.pojo.SpecialColumn;
import com.foxconn.util.FileUploader;
//import com.foxconn.util.FileUtils;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.SystemContext;
//import com.foxconn.util.PageListBean;




@Controller
@RequestMapping("specialColumn")
public class SpecialColumnController {
	@Autowired
	@Resource(name = "specialColumnServiceImpl")
	private SpecialColumnServie specialColumnServiceImpl;
	
	@RequestMapping(params = "action=getSpecialColumnList")
	public String getSpecialColumnList(HttpServletRequest request,
			@RequestParam("columnTitle") String columnTitle,
			Model model) {
		SpecialColumn specialColumn = new SpecialColumn();
		specialColumn.setColumnTitle(columnTitle);
		 
		 List <SpecialColumn> specialColumnList = specialColumnServiceImpl.getSpecialColumnList(specialColumn);	 
		 Page page=listToPage(specialColumnList, 1,15);
		 
		 model.addAttribute("paginations", page);
		 
		 return "specialcolumn/specialColumnManage";
	}
	
	@RequestMapping(params="action=querySpecialColumnListByPageIndex")
	public void querySpecialColumnListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("columnTitle") String columnTitle,
			HttpServletResponse response){
		 SpecialColumn specialColumn = new SpecialColumn();	
		 try{
			specialColumn.setColumnTitle(java.net.URLDecoder.decode(columnTitle,"UTF-8"));
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }		 
		List <SpecialColumn> specialColumnList = specialColumnServiceImpl.getSpecialColumnList(specialColumn);
		Page page=listToPage(specialColumnList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(params="action=deleteSpecialColumn")
	public void deleteSpecialColumn(
			Model model,@RequestParam("columnID") String columnID,
			HttpServletResponse response
			){
		
		String msg="";
       try{
    	   SpecialColumn specialColumn = new SpecialColumn();
    	   specialColumn.setColumnID(columnID);
    	   specialColumn.setModifyUser(SystemContext.getUserNO());
    	   specialColumnServiceImpl.deleteSpecialColumn(specialColumn);
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
	
	@RequestMapping(params="action=turnToAddSpecialColumn")
	public String turnToAddSpecialColumn(
			@RequestParam("columnID") String columnID,
			Model model
			){
		SpecialColumn specialColumn = new SpecialColumn();
		SpecialColumn specialColumn1= new SpecialColumn();
		
		if(columnID!=null&&!("".equals(columnID))){
		specialColumn.setColumnID(columnID);
	    specialColumn1 = specialColumnServiceImpl.getSpecialColumnDetail(specialColumn);
		}
		
		model.addAttribute("columnID", columnID);
		model.addAttribute("columnTitle", specialColumn1.getColumnTitle());
		model.addAttribute("entryDate", specialColumn1.getEntryDate());
		model.addAttribute("columnUrl", specialColumn1.getColumnUrl());
		model.addAttribute("isShow", specialColumn1.getIsShow());
		return "specialcolumn/addSpecialColumn";
		
	}
	

	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=publishSpecialColumn")
	public void publishSpecialColumn(
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		SpecialColumn specialColumn = new SpecialColumn();
		boolean isInsert=true;
		MultipartFile columnPhotos = request.getFile("columnPhotos");
		String columnID=request.getParameter("columnID");
	    String columnTitle=request.getParameter("columnTitle");
	    String columnUrl=request.getParameter("columnUrl");
		String projectPath=request.getSession().getServletContext().getRealPath("/"); 
		/*String projectPath=request.getSession().getServletContext().getRealPath("resource"); 发布后使用*/
		String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
		String path = diskSign+"/CMS/WebContent/resource/specialcolumn/"+ FileUploader.getTimeFolder()+"/";//发布前使用
		
		String msg="";
		Map<String, String> toJson = new HashMap<String, String>();
	
		if(columnID!=null&&!("".equals(columnID))){
			isInsert=false;
			specialColumn.setColumnID(columnID);
		}
		else{			
			isInsert=true;
			specialColumn.setEntryUser(SystemContext.getUserNO());  //登錄用戶，後續修改
	      }
		specialColumn.setModifyUser(SystemContext.getUserNO());  //登錄用戶，後續修改
		specialColumn.setColumnTitle(columnTitle);
		specialColumn.setColumnUrl(columnUrl);
		specialColumn.setIsShow("0");
		
		if(columnPhotos.getSize()!=0) {
			specialColumn.setColumnPhotosUrl(FileUploader.uploadFile(request,columnPhotos,path));
			specialColumn.setColumnPhotosName(columnPhotos.getOriginalFilename());
		}
		try{
			if(isInsert==true){
				specialColumnServiceImpl.insertSpecialColumn(specialColumn);
			}else{
				specialColumnServiceImpl.updateSpecialColumn(specialColumn);
			}
			toJson.put("msg", "发表成功！");		
		}
		catch (Exception e) {
			toJson.put("msg", "发表失败！");
			e.printStackTrace();
			
		}
		
		try{
			
			response.setContentType("text/html; charset=utf-8"); //ajaxForm异步提交，关键语句
			response.getWriter().write(JsonUtil.toJsonString(toJson));//可用于转MAP
			
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
