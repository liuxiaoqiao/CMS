package com.foxconn.cms.textnews.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.pojo.PageNewsRef;
import com.foxconn.pojo.SelectBean;
import com.foxconn.cms.textnews.service.TextNewsService;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PageListBean;
import com.foxconn.util.SystemContext;
import com.foxconn.service.UtilService;

@Controller
@RequestMapping("textNews.do")
public class TextNewsController {
	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;
	
	@Resource(name = "utilServiceImpl")
	private UtilService utilServiceImpl;

	private String strContent;

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	/**
	 * 獲取當前的頁碼
	 * @param page
	 * @return
	 */
	public static int getPage(String page) {
		int pageNumber = 0;
		if (!isEmpty(page)) {
			pageNumber = Integer.parseInt(page);
		} else {
			pageNumber = 1;
		}
		return pageNumber;
	}
	
	/**
	 * 判断一个字体是否为空
	 * @param str
	 * @return 为空返回true,否则返回false
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.trim().equals("")){
			return true;
		}
		return false;
		
	}
	
	@RequestMapping(params = "action=getOutTextNewsListForLov")
	public String getOutTextNewsListForLov(
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("dataSource") String dataSource,
			@RequestParam("count") String count, HttpServletRequest request,
			Model model) {
		String page = request.getParameter("page");
		TextNews textNews = new TextNews();
		textNews.setNewsTitle(newsTitle);	
		
		if(dataSource!=null&&!("").equals(dataSource)){
		textNews.setDataSource(Integer.valueOf(dataSource));
		textNews.setSDataSource(dataSource);
		}
		int pageNumber = getPage(page);
		PageListBean pageList = new PageListBean();
		pageList.setPageNumber(pageNumber);
		pageList.setObjectsPerPage(PageListBean.DEFAULT_PAGE_SIZE);
		pageList.setList(textNewsServiceImpl.getOutTextNewsListForLov(textNews, pageNumber));

		if (!isEmpty(count)) {
			pageList.setFullListSize(Integer.parseInt(count));
		} else {
			pageList.setFullListSize(textNewsServiceImpl.getOutTextNewsList(textNews).size());
		}
		List<SelectBean> dataSourceList = textNewsServiceImpl.selectDateSource();
		dataSourceList.add(0, new SelectBean());
		
		model.addAttribute("outTextNewsList", pageList);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("count", pageList.getFullListSize());
		model.addAttribute("dataSourceList", dataSourceList);

		return "textnews/selectOutTextNews";
	}
	
	
	
	/**
	 * 外部稿件清单页面
	 * @param request
	 * @param newsTitle
	 * @param dataSource
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getOutTextNewsList")
	public String getOutTextNewsList(
	  HttpServletRequest request,
		@RequestParam("newsTitle") String newsTitle,
		@RequestParam("dataSource") String dataSource,
		Model model
	){
		TextNews textNews = new TextNews();
		textNews.setNewsTitle(newsTitle);
		textNews.setDataSource(Integer.valueOf(dataSource));
		List<TextNews> outTextNewsList= textNewsServiceImpl.getOutTextNewsList(textNews);
		Page page=listToPage(outTextNewsList, 1,15);
		List<SelectBean> dataSourceList = textNewsServiceImpl.selectDateSource();
		 
		model.addAttribute("paginations", page);
		model.addAttribute("dataSourceList", dataSourceList);
		 
		return "textnews/selectOutTextNews";
	}
	
	/**
	 * 外部稿件查询
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param dataSource
	 * @param response
	 */
	@RequestMapping(params="action=getOutTextNewsListByPageIndex")
	public void getOutTextNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("dataSource") String dataSource,
			HttpServletResponse response){
		 TextNews textNews = new TextNews();
		 textNews.setNewsTitle(newsTitle);
		 textNews.setDataSource(Integer.valueOf(dataSource));

		 
		 List <TextNews> textNewsList = textNewsServiceImpl.getOutTextNewsList(textNews);		 
		 Page page=listToPage(textNewsList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文字新闻清单页面
	 * @param request
	 * @param newsTitle
	 * @param pressPerson
	 * @param pressDateS
	 * @param pressDateE
	 * @param programType
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getTextNewsList")
	public String getTextNewsList(HttpServletRequest request,
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("pressPerson") String pressPerson,
			@RequestParam("pressDateS") String pressDateS,
			@RequestParam("pressDateE") String pressDateE,
			@RequestParam("programType") String programType,
			Model model) {
		 TextNews textNews = new TextNews();
		 textNews.setNewsTitle(newsTitle);
		 textNews.setEntryUser(pressPerson);
		 textNews.setPressDateS(pressDateS);
		 textNews.setPressDateE(pressDateE);

		 textNews.setProgramType(programType);
		 
		 List <TextNews> textNewsList = textNewsServiceImpl.getTextNewsList(textNews);	 
		 Page page=listToPage(textNewsList, 1,15);
		 
		 model.addAttribute("paginations", page);
		 model.addAttribute("programType", programType);
		 
		 return "textnews/textNewsManage";
	}
	
	/**
	 * 打开新增页面
	 * @param request
	 * @param programType
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=insertTextNewsView")
	public String insertTextNewsView(HttpServletRequest request,
			@RequestParam("programType") String programType,
			Model model){
		List<SelectBean> newsSourceList = textNewsServiceImpl.selectNewsSource();
		List<SelectBean> approverList =utilServiceImpl.getApproverList();
		model.addAttribute("approverList", approverList);
		model.addAttribute("newsSourceList", newsSourceList);
		model.addAttribute("programType", programType);
		return "textnews/addTextNews";
	}
	

	
	/**
	 * 新增操作（存草稿(新增)/待审核/已发表）
	 * @param request
	 * @param programType
	 * @param newsTitle
	 * @param subNewsTitle
	 * @param sectionPosition
	 * @param pressDate
	 * @param pressPerson
	 * @param newsSource
	 * @param newsKeyWord
	 * @param newsSummary
	 * @param approvaler
	 * @param newsContent
	 * @param isPhotosShow
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=insertTextNews")
	public void insertTextNews(HttpServletRequest request,
			@RequestParam("programType") String programType,
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("subNewsTitle") String subNewsTitle,
			@RequestParam("sectionPosition") String sectionPosition,
			@RequestParam("entryDate") String entryDate,
			@RequestParam("writer") String writer,
			@RequestParam("newsSource") String newsSource,
			@RequestParam("newsKeyWord") String newsKeyWord,
			@RequestParam("newsSummary") String newsSummary,
			@RequestParam("approvaler") String approvaler,
			@RequestParam("newsContent") String newsContent,
			@RequestParam("isPhotosShow") String isPhotosShow,
			@RequestParam("flag") String flag,
			HttpServletResponse response){
		
		TextNews  textNews = new TextNews();
		PageNewsRef pageNewsRef = new PageNewsRef();
		UUID uuid = UUID.randomUUID();
		String newsID=String.valueOf(uuid);
		
		try{
		textNews.setNewsID(newsID);
	
		textNews.setNewsTitle(java.net.URLDecoder.decode(newsTitle,"UTF-8"));
		textNews.setSubNewsTitle(java.net.URLDecoder.decode(subNewsTitle,"UTF-8"));
		
		textNews.setNewsSummary(java.net.URLDecoder.decode(newsSummary,"UTF-8"));
		String strNewsContent=java.net.URLDecoder.decode(newsContent,"UTF-8");
		textNews.setNewsContent(strNewsContent.replaceAll("\"", "\'"));
		if("1".equals(flag)){
		textNews.setNewsStatusID(5);   //拟稿状态
		}else{
			if(java.net.URLDecoder.decode(approvaler,"UTF-8")!=null&&!("".equals(java.net.URLDecoder.decode(approvaler,"UTF-8")))){
				textNews.setNewsStatusID(6); //待审核状态
			}else{
				textNews.setNewsStatusID(7);  //已发表
			}
		}
		textNews.setNewsSource(Integer.valueOf(newsSource));
		textNews.setNewsKeyWord(java.net.URLDecoder.decode(newsKeyWord,"UTF-8"));
		//textNews.setIsKeyRelation(isKeyRelation); 此栏位是否应拿掉
		
		textNews.setEntryUser(SystemContext.getUserNO());// 当前用户
		textNews.setModifyUser(SystemContext.getUserNO());//当前用户
		
		textNews.setSectionPosition(Integer.valueOf(sectionPosition));
		textNews.setApprovaler(java.net.URLDecoder.decode(approvaler,"UTF-8"));
		textNews.setWriter(writer); //作者
		textNews.setDataSource(0);  //此处会确认修改，数据源入口在哪里，如何获取？
		textNews.setProgramType(programType);
		textNews.setIsPhotosShow(isPhotosShow);
		
		
		pageNewsRef.setPageID(programType);
		pageNewsRef.setNewsID(newsID);
		pageNewsRef.setCreateUser(SystemContext.getUserNO()); //当前用户
		pageNewsRef.setModifyUser(SystemContext.getUserNO()); //当前用户
		pageNewsRef.setIsFirst("1");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		try{
		textNewsServiceImpl.insertTextNews(textNews);  //新增新闻
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//try{
		//textNewsServiceImpl.insertPageNewsRef(pageNewsRef); //新增分发关系
		//}catch(Exception ex){
		//	ex.printStackTrace();
		//}
		
		response.setCharacterEncoding("UTF-8");
		
	}
	
	/**
	 * 删除文字类新闻(源文件/分发文件)
	 * @param model
	 * @param newsID
	 * @param programType
	 * @param response
	 */
	@RequestMapping(params="action=deleteTextNews")
	public void deleteTextNews(
			Model model,@RequestParam("newsID") String newsID,
			@RequestParam("programType") String programType,HttpServletResponse response
			){
		
		String msg="";
       try{
    	   PageNewsRef pageNewsRef = new PageNewsRef();
    	   pageNewsRef.setNewsID(newsID);
    	   pageNewsRef.setPageID(programType);
    	   TextNews textNews = new TextNews();
    	   textNews.setNewsID(newsID);
    	   textNews.setProgramType(programType);
    	   textNews.setModifyUser(SystemContext.getUserNO());
    	  /* int i= textNewsServiceImpl.validateIsFirstDis(pageNewsRef);
    	   if(i>0){  		 
    		
    		   textNewsServiceImpl.deleteTextNews(textNews);//删除源文件
    		   PageNewsRef pageNewsRef1 = new PageNewsRef();
        	   pageNewsRef1.setNewsID(newsID);
        	   textNewsServiceImpl.deletePageNewsRef(pageNewsRef1); //同时删除所有被分发的文件
    		   
    	   }else{
    		   textNewsServiceImpl.deletePageNewsRef(pageNewsRef); //删除分发文件
    	   }*/
    	   textNewsServiceImpl.deleteTextNews(textNews);//删除源文件
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
	 * 打开编辑页面
	 * @param request
	 * @param newsID
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=updateTextNewsView")
	public String updateTextNewsView(HttpServletRequest request,
			@RequestParam("newsID") String newsID,
			@RequestParam("programType") String programType,
			Model model){
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews textNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);
		
		String isPu="";
		String isTou="";
		String isCi="";
		int sectionPosition=textNews1.getSectionPosition();
		if(sectionPosition==2){
			isTou="checked";
		}else if(sectionPosition==3){
			isCi="checked";
		}else{
			isPu="checked";
		}
		List<SelectBean> newsSourceList = textNewsServiceImpl.selectNewsSource();
		
		List<SelectBean> approverList =utilServiceImpl.getApproverList();
		model.addAttribute("approverList", approverList);
		model.addAttribute("newsID",textNews1.getNewsID());
		model.addAttribute("newsTitle",textNews1.getNewsTitle());		
		model.addAttribute("subNewsTitle",textNews1.getSubNewsTitle());
		model.addAttribute("newsContent",textNews1.getNewsContent());
		model.addAttribute("entryDate",textNews1.getEntryDate());
		model.addAttribute("writer",textNews1.getWriter());
		model.addAttribute("newsSourceOld",textNews1.getNewsSource());
		model.addAttribute("newsSourceList",newsSourceList);
		model.addAttribute("newsSourceName",textNews1.getNewsSourceName());
		model.addAttribute("newsKeyWord",textNews1.getNewsKeyWord());
		model.addAttribute("newsSummary",textNews1.getNewsSummary());
		model.addAttribute("approvalerOld",textNews1.getApprovaler());
		model.addAttribute("isPhotosShow",textNews1.getIsPhotosShow());
		model.addAttribute("programType",programType);
		
		model.addAttribute("isPu",isPu);
		model.addAttribute("isTou",isTou);
		model.addAttribute("isCi",isCi);
		model.addAttribute("isPreViewHide","");
		model.addAttribute("isDraftHide", "");
		model.addAttribute("isPublishHide","");
		model.addAttribute("isRefuseHide", "display:none");
		model.addAttribute("isAppHide","display:none");
		
		return "textnews/updateTextNews";
	}

	
	/**
	 * 存草稿(编辑)/已发表(编辑)/审核通过
	 * @param request
	 * @param newsID
	 * @param newsTitle
	 * @param subNewsTitle
	 * @param sectionPosition
	 * @param pressDate
	 * @param pressPerson
	 * @param newsSource
	 * @param newsKeyWord
	 * @param newsSummary
	 * @param approvaler
	 * @param newsContent
	 * @param isPhotosShow
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=updateTextNews")
    public void updateTextNews(HttpServletRequest request,
			@RequestParam("newsID") String newsID,
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("subNewsTitle") String subNewsTitle,
			@RequestParam("sectionPosition") String sectionPosition,
			@RequestParam("entryDate") String entryDate,
			@RequestParam("writer") String writer,
			@RequestParam("newsSource") String newsSource,
			@RequestParam("newsKeyWord") String newsKeyWord,
			@RequestParam("newsSummary") String newsSummary,
			@RequestParam("approvaler") String approvaler,
			@RequestParam("newsContent") String newsContent,
			@RequestParam("isPhotosShow") String isPhotosShow,
			@RequestParam("programType") String programType,
			@RequestParam("flag") String flag,
			HttpServletResponse response){

		TextNews  textNews = new TextNews();
		try{
		textNews.setNewsID(newsID);
		textNews.setProgramType(programType);
		textNews.setNewsTitle(java.net.URLDecoder.decode(newsTitle,"UTF-8"));
		textNews.setSubNewsTitle(java.net.URLDecoder.decode(subNewsTitle,"UTF-8"));
		textNews.setNewsSummary(java.net.URLDecoder.decode(newsSummary,"UTF-8"));
		textNews.setNewsSummary(java.net.URLDecoder.decode(newsSummary,"UTF-8"));
		
		String strNewsContent=java.net.URLDecoder.decode(newsContent,"UTF-8");
		textNews.setNewsContent(strNewsContent.replaceAll("\"", "\'"));
		
		if("1".equals(flag)){   //flag=1:点击存草稿按钮
		textNews.setNewsStatusID(5);   //拟稿状态
		}else if("2".equals(flag)){ //flag=2:点击已发表按钮
			if(java.net.URLDecoder.decode(approvaler,"UTF-8")!=null&&!("".equals(java.net.URLDecoder.decode(approvaler,"UTF-8")))){
				textNews.setNewsStatusID(6); //待审核状态
			}else{
				textNews.setNewsStatusID(7);  //已发表
			}
		}else{//flag=3:点击审核通过按钮
			textNews.setNewsStatusID(7);  //已发表
		}
		
		
		textNews.setNewsSource(Integer.valueOf(newsSource));
		textNews.setNewsKeyWord(java.net.URLDecoder.decode(newsKeyWord,"UTF-8"));
		//textNews.setIsKeyRelation(isKeyRelation); 此栏位是否应拿掉
		
		textNews.setEntryUser(SystemContext.getUserNO());// 当前用户
		textNews.setModifyUser(SystemContext.getUserNO());//当前用户
		
		textNews.setSectionPosition(Integer.valueOf(sectionPosition));
		textNews.setApprovaler(java.net.URLDecoder.decode(approvaler,"UTF-8"));
		textNews.setWriter(writer); //作者
		textNews.setDataSource(0);  //此处会确认修改，数据源入口在哪里，如何获取？
		textNews.setIsPhotosShow(isPhotosShow);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		textNewsServiceImpl.updateTextNews(textNews);  //編輯新闻
		
		response.setCharacterEncoding("UTF-8");
    }
	
	
	/**
	 * 打開審核頁面
	 * @param request
	 * @param newsID
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=approvalTextNews")
	public String approvalTextNews(HttpServletRequest request,
			@RequestParam("newsID") String newsID,
			Model model){
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews textNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);
		
		String isPu="";
		String isTou="";
		String isCi="";
		int sectionPosition=textNews1.getSectionPosition();
		if(sectionPosition==2){
			isTou="checked";
		}else if(sectionPosition==3){
			isCi="checked";
		}else{
			isPu="checked";
		}
		List<SelectBean> newsSourceList = textNewsServiceImpl.selectNewsSource();
		
		List<SelectBean> approverList =utilServiceImpl.getApproverList();
		model.addAttribute("approverList", approverList);
			
		model.addAttribute("newsID",textNews1.getNewsID());
		model.addAttribute("newsTitle",textNews1.getNewsTitle());		
		model.addAttribute("subNewsTitle",textNews1.getSubNewsTitle());
		model.addAttribute("newsContent",textNews1.getNewsContent());
		model.addAttribute("entryDate",textNews1.getEntryDate());
		model.addAttribute("writer",textNews1.getWriter());
		model.addAttribute("newsSourceOld",textNews1.getNewsSource());
		model.addAttribute("newsSourceList",newsSourceList);
		model.addAttribute("newsSourceName",textNews1.getNewsSourceName());
		model.addAttribute("newsKeyWord",textNews1.getNewsKeyWord());
		model.addAttribute("newsSummary",textNews1.getNewsSummary());
		model.addAttribute("approvalerOld",textNews1.getApprovaler());
		model.addAttribute("isPhotosShow",textNews1.getIsPhotosShow());
		model.addAttribute("programType",textNews1.getProgramType());
		
		model.addAttribute("isPu",isPu);
		model.addAttribute("isTou",isTou);
		model.addAttribute("isCi",isCi);
		
		model.addAttribute("isPreViewHide","display:none");
		model.addAttribute("isDraftHide", "display:none");
		model.addAttribute("isPublishHide","display:none");
		model.addAttribute("isRefuseHide", "");
		model.addAttribute("isAppHide","");
		
		return "textnews/updateTextNews";
	}
	
	@RequestMapping(params = "action=previewFormList")
    public String previewFormList(HttpServletRequest request,
    		@RequestParam("newsID") String newsID,
			Model model){
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews textNews1 = textNewsServiceImpl.preViewTextNews(textNews);
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		String strCurrentDate=String.valueOf(c.get(Calendar.YEAR))+"年"+ StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH)), 2, '0')+"月"+ StringUtils.leftPad(String.valueOf(c.get(Calendar.DATE)), 2, '0')+"日";
		
		//model.addAttribute("readRecordCount",textNews1.getReadRecordCount());
		model.addAttribute("newsTitle",textNews1.getNewsTitle().toString());
		model.addAttribute("newsContent",textNews1.getNewsContent());
		model.addAttribute("newsSourceName",textNews1.getNewsSourceName().toString());
		model.addAttribute("currentDate",strCurrentDate);
    	return "textnews/preViewTextNews";
    }
	
	
	@RequestMapping(params = "action=preViewViewT")
    public String preViewViewT(HttpServletRequest request,
			Model model){
		try{
		String  strTmp=java.net.URLDecoder.decode(this.getStrContent(),"UTF-8");
		String arr[]=strTmp.split(";;");
		String newsSource = textNewsServiceImpl.selectNewSourceName(Integer.valueOf(arr[2]));
		model.addAttribute("newsTitle",arr[0]);
		model.addAttribute("newsContent",arr[1]);
		model.addAttribute("newsSourceName",newsSource);
		model.addAttribute("currentDate",arr[3]);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "textnews/preViewTextNews";
    }
	
	@RequestMapping(params="action=insertRefuseTextNews")
	public void insertRefuseTextNews(
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
		
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		textNews.setNewsStatusID(8);
		textNews.setModifyUser(SystemContext.getUserNO());
		textNewsServiceImpl.updateTextNewsStatus(textNews);
		response.setCharacterEncoding("UTF-8");
	}
	
	
	@RequestMapping(params="action=preViewViewS")
	public void preViewViewS(
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("newsContent") String newsContent,
			@RequestParam("newsSource") String newsSource,
			@RequestParam("isPhotosShow") String isPhotosShow,
			HttpServletResponse response){
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		String strCurrentDate=String.valueOf(c.get(Calendar.YEAR))+"年"+ StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH)), 2, '0')+"月"+ StringUtils.leftPad(String.valueOf(c.get(Calendar.DATE)), 2, '0')+"日";
		try{
			newsTitle=java.net.URLDecoder.decode(newsTitle,"UTF-8");
			newsContent=java.net.URLDecoder.decode(newsContent,"UTF-8");
			newsSource=java.net.URLDecoder.decode(newsSource,"UTF-8");
			String strBuf = newsTitle+";;"+newsContent+";;"+newsSource+";;"+strCurrentDate;
			this.setStrContent(strBuf);
			this.setStrContent(strBuf);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			
		
		response.setCharacterEncoding("UTF-8");
	//	try{
	//		response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
	//	}catch (Exception e) {
	//		e.printStackTrace();
	//	}
	}
	
	/***
	 * 清单查询
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param pressPerson
	 * @param pressDateS
	 * @param pressDateE
	 * @param programType
	 * @param response
	 */
	@RequestMapping(params="action=queryTextNewsListByPageIndex")
	public void queryTextNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("pressDateS") String pressDateS,
			@RequestParam("pressDateE") String pressDateE,
			@RequestParam("programType") String programType,
			HttpServletResponse response){
		 TextNews textNews = new TextNews();
		 try{
		 textNews.setNewsTitle(java.net.URLDecoder.decode(newsTitle,"UTF-8"));
		 textNews.setEntryUser(java.net.URLDecoder.decode(entryUser,"UTF-8"));
		 textNews.setPressDateS(pressDateS);
		 textNews.setPressDateE(pressDateE);
		 textNews.setProgramType(programType);
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }		 
			 
		 List <TextNews> textNewsList = textNewsServiceImpl.getTextNewsList(textNews);
		 Page page=listToPage(textNewsList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try{
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "action=openRefuseTextNews")
	public String openRefuseTextNews(
			@RequestParam("newsID") String newsID,
            HttpServletRequest request,
			Model model) {
		model.addAttribute("newsID", newsID);
		model.addAttribute("newsType","11");
		return "textnews/refuseTextNews";
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
