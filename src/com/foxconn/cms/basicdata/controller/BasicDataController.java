package com.foxconn.cms.basicdata.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.foxconn.cms.basicdata.service.BasicDataService;
import com.foxconn.cms.basicdata.pojo.BasicType;
import com.foxconn.cms.basicdata.pojo.BasicInfo;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.SystemContext;

@Controller
@RequestMapping("basicData")
public class BasicDataController {
	@Autowired
	@Resource(name = "basicDataServiceImpl")
	private BasicDataService basicDataServiceImpl;
	
	@RequestMapping(params = "action=getBasicTypeList")
	public String getBasicTypeList(HttpServletRequest request,
			@RequestParam("typeName") String typeName,
			Model model) {
		BasicType basicType = new BasicType();
		basicType.setTypeName(typeName);
		 
		 List <BasicType> basicTypeList = basicDataServiceImpl.getBasicTypeList(basicType);	 
		 Page page=listToPage(basicTypeList, 1,15);
		 
		 model.addAttribute("paginations", page);
		 
		 return "basicdata/basicType";
	}
	
	@RequestMapping(params = "action=getBasicInfoList")
	public String getBasicInfoList(HttpServletRequest request,
			@RequestParam("typeID") String typeID,
			@RequestParam("infoName") String infoName,
			Model model) {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.setInfoName(infoName);
		
		if(typeID!=null&&!("".equals(typeID))){
			basicInfo.setsTypeID(typeID);
			basicInfo.setTypeID(Integer.parseInt(typeID));
		}
		 
		 List <BasicInfo> basicInfoList = basicDataServiceImpl.getBasicInfoList(basicInfo); 
		 Page page=listToPage(basicInfoList, 1,15);
		 
		 model.addAttribute("typeID", typeID);
		 model.addAttribute("paginations", page);
		 
		 return "basicdata/basicInfo";
	}
	
	@RequestMapping(params="action=queryBasicInfoListByPageIndex")
	public void queryBasicInfoListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("typeID") String typeID,
			@RequestParam("infoName") String infoName,
			HttpServletResponse response){
		BasicInfo basicInfo = new BasicInfo();
		if(typeID!=null&&!("".equals(typeID))){
			basicInfo.setsTypeID(typeID);
			basicInfo.setTypeID(Integer.parseInt(typeID));
		}
		 try{
			 basicInfo.setInfoName(java.net.URLDecoder.decode(infoName,"UTF-8"));
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }		 
		 List <BasicInfo> basicInfoList = basicDataServiceImpl.getBasicInfoList(basicInfo); 	 
		Page page=listToPage(basicInfoList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="action=queryBasicTypeListByPageIndex")
	public void queryBasicTypeListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("typeName") String typeName,
			HttpServletResponse response){
		BasicType basicType = new BasicType();
		 try{
				basicType.setTypeName(java.net.URLDecoder.decode(typeName,"UTF-8"));
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }		 
		 List <BasicType> basicTypeList = basicDataServiceImpl.getBasicTypeList(basicType);	 
		Page page=listToPage(basicTypeList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="action=deleteBasicType")
	public void deleteBasicType(
			Model model,@RequestParam("typeID") String typeID,
			HttpServletResponse response
			){
		
		String msg="";
       try{
    		BasicType basicType = new BasicType();
    		basicType.setTypeID(Integer.parseInt(typeID));
    		basicType.setModifyUser(SystemContext.getUserNO());
    		basicDataServiceImpl.deleteBasicType(basicType);
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
	
	@RequestMapping(params="action=deleteBasicInfo")
	public void deleteBasicInfo(
			Model model,@RequestParam("infoID") String infoID,
			HttpServletResponse response
			){
		
		String msg="";
       try{
    	   BasicInfo basicInfo = new BasicInfo();
    	   basicInfo.setInfoID(Integer.parseInt(infoID));
    	   basicInfo.setModifyUser(SystemContext.getUserNO());
    		basicDataServiceImpl.deleteBasicInfo(basicInfo);
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
	
	@RequestMapping(params="action=turnToAddBasicType")
	public String turnToAddBasicType(
			@RequestParam("typeID") String typeID,
			Model model
			){
		BasicType basicType = new BasicType();
		BasicType basicType1 = new BasicType();
		
		if(typeID!=null&&!("".equals(typeID))){
		basicType.setTypeID(Integer.parseInt(typeID));
		basicType1 =basicDataServiceImpl.getBasicTypeDetail(basicType);
		}
		
		model.addAttribute("typeID", typeID);
		model.addAttribute("typeName",basicType1.getTypeName());
		model.addAttribute("typeDesc", basicType1.getTypeDesc());
		 return "basicdata/addBasicType";		
	}
	
	@RequestMapping(params="action=turnToAddBasicInfo")
	public String turnToAddBasicInfo(
			@RequestParam("typeID") String typeID,
			@RequestParam("infoID") String infoID,
			Model model
			){
		BasicInfo basicInfo = new BasicInfo();
		BasicInfo basicInfo1 = new BasicInfo();
		
		if(infoID!=null&&!("".equals(infoID))){
	    basicInfo.setInfoID(Integer.parseInt(infoID));
	    basicInfo1 =basicDataServiceImpl.getBasicInfoDetail(basicInfo);
		}
		
		model.addAttribute("infoID", infoID);
		model.addAttribute("typeID",typeID);
		model.addAttribute("infoName",basicInfo1.getInfoName());
		model.addAttribute("infoDesc", basicInfo1.getInfoDesc());
		 return "basicdata/addBasicInfo";		
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=addOrEditBasicInfo")
	public void addOrEditBasicInfo(
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		BasicInfo basicInfo = new BasicInfo();
		boolean isInsert=true;
		String infoID=request.getParameter("infoID");
		String typeID=request.getParameter("typeID");
	    String infoName=request.getParameter("infoName");
	    String infoDesc=request.getParameter("infoDesc");
	
		String msg="";
		Map<String, String> toJson = new HashMap<String, String>();
	
		if(infoID!=null&&!("".equals(infoID))){
			isInsert=false;
			basicInfo.setInfoID(Integer.parseInt(infoID));
		}
		else{			
			isInsert=true;
			basicInfo.setInfoID(basicDataServiceImpl.getNewBasicInfoID());
			basicInfo.setEntryUser(SystemContext.getUserNO());  //登錄用戶，後續修改
	      }
		basicInfo.setModifyUser(SystemContext.getUserNO());  //登錄用戶，後續修改
		basicInfo.setInfoName(infoName);
		basicInfo.setInfoDesc(infoDesc);
		basicInfo.setTypeID(Integer.parseInt(typeID));
		
		try{
			if(isInsert==true){
				basicDataServiceImpl.insertBasicInfo(basicInfo);
			}else{
				basicDataServiceImpl.updateBasicInfo(basicInfo);
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
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST,params="action=addOrEditBasicType")
	public void addOrEditBasicType(
		    Model model,
			MultipartHttpServletRequest request,
			HttpServletResponse response)throws IOException{
		BasicType basicType = new BasicType();
		boolean isInsert=true;
		String typeID=request.getParameter("typeID");
	    String typeName=request.getParameter("typeName");
	    String typeDesc=request.getParameter("typeDesc");
	
		String msg="";
		Map<String, String> toJson = new HashMap<String, String>();
	
		if(typeID!=null&&!("".equals(typeID))){
			isInsert=false;
			basicType.setTypeID(Integer.parseInt(typeID));
		}
		else{			
			isInsert=true;
			basicType.setTypeID(basicDataServiceImpl.getNewBasicTypeID());
			basicType.setEntryUser(SystemContext.getUserNO());  //登錄用戶，後續修改
	      }
		basicType.setModifyUser(SystemContext.getUserNO());  //登錄用戶，後續修改
		basicType.setTypeName(typeName);
		basicType.setTypeDesc(typeDesc);
		
		try{
			if(isInsert==true){
				basicDataServiceImpl.insertBasicType(basicType);
			}else{
				basicDataServiceImpl.updateBasicType(basicType);
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
