package com.foxconn.cms.communication.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

import com.foxconn.cms.communication.pojo.Communication;
import com.foxconn.pojo.SelectBean;
import com.foxconn.cms.communication.service.CommunicationService;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PageListBean;
import com.foxconn.util.SystemContext;

@Controller
@RequestMapping("communication.do")
public class CommunicationController {
	@Autowired
	@Resource(name = "communicationServiceImpl")
	private CommunicationService communicationServiceImpl;
	private String strContent;

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	/**
	 * 獲取當前的頁碼
	 * 
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

	@SuppressWarnings("unused")
	/**
	 * 判断一个字体是否为空
	 * @param str
	 * @return 为空返回true,否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;

	}

	//
	// @RequestMapping(params = "action=getOutTextNewsListForLov")
	// public String getOutTextNewsListForLov(
	// @RequestParam("newsTitle") String newsTitle,
	// @RequestParam("dataSource") String dataSource,
	// @RequestParam("count") String count, HttpServletRequest request,
	// Model model) {
	// String page = request.getParameter("page");
	// TextNews textNews = new TextNews();
	// textNews.setNewsTitle(newsTitle);
	//
	// if(dataSource!=null&&!("").equals(dataSource)){
	// textNews.setDataSource(Integer.valueOf(dataSource));
	// textNews.setSDataSource(dataSource);
	// }
	// int pageNumber = getPage(page);
	// PageListBean pageList = new PageListBean();
	// pageList.setPageNumber(pageNumber);
	// pageList.setObjectsPerPage(PageListBean.DEFAULT_PAGE_SIZE);
	// pageList.setList(textNewsServiceImpl.getOutTextNewsListForLov(textNews,
	// pageNumber));
	//
	// if (!isEmpty(count)) {
	// pageList.setFullListSize(Integer.parseInt(count));
	// } else {
	// pageList.setFullListSize(textNewsServiceImpl.getOutTextNewsList(textNews).size());
	// }
	// List<SelectBean> dataSourceList = textNewsServiceImpl.selectDateSource();
	// dataSourceList.add(0, new SelectBean());
	//
	// model.addAttribute("outTextNewsList", pageList);
	// model.addAttribute("newsTitle", newsTitle);
	// model.addAttribute("dataSource", dataSource);
	// model.addAttribute("count", pageList.getFullListSize());
	// model.addAttribute("dataSourceList", dataSourceList);
	//
	// return "textnews/selectOutTextNews";
	// }
	//
	//
	//
	// /**
	// * 外部稿件清单页面
	// * @param request
	// * @param newsTitle
	// * @param dataSource
	// * @param model
	// * @return
	// */
	// @RequestMapping(params = "action=getOutTextNewsList")
	// public String getOutTextNewsList(
	// HttpServletRequest request,
	// @RequestParam("newsTitle") String newsTitle,
	// @RequestParam("dataSource") String dataSource,
	// Model model
	// ){
	// TextNews textNews = new TextNews();
	// textNews.setNewsTitle(newsTitle);
	// textNews.setDataSource(Integer.valueOf(dataSource));
	// List<TextNews> outTextNewsList=
	// textNewsServiceImpl.getOutTextNewsList(textNews);
	// Page page=listToPage(outTextNewsList, 1,15);
	// List<SelectBean> dataSourceList = textNewsServiceImpl.selectDateSource();
	//
	// model.addAttribute("paginations", page);
	// model.addAttribute("dataSourceList", dataSourceList);
	//
	// return "textnews/selectOutTextNews";
	// }
	//
	// /**
	// * 外部稿件查询
	// * @param pageIndex
	// * @param pageSize
	// * @param newsTitle
	// * @param dataSource
	// * @param response
	// */
	// @RequestMapping(params="action=getOutTextNewsListByPageIndex")
	// public void getOutTextNewsListByPageIndex(
	// @RequestParam("pageIndex") String pageIndex,
	// @RequestParam("pageSize") String pageSize,
	// @RequestParam("newsTitle") String newsTitle,
	// @RequestParam("dataSource") String dataSource,
	// HttpServletResponse response){
	// TextNews textNews = new TextNews();
	// textNews.setNewsTitle(newsTitle);
	// textNews.setDataSource(Integer.valueOf(dataSource));
	//
	//
	// List <TextNews> textNewsList =
	// textNewsServiceImpl.getOutTextNewsList(textNews);
	// Page
	// page=listToPage(textNewsList,Integer.valueOf(pageIndex),Integer.valueOf(pageSize));
	//
	// response.setCharacterEncoding("UTF-8");
	// try{
	// response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
	// }catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	/**
	 * 文字新闻清单页面
	 * 
	 * @param request
	 * @param newsTitle
	 * @param pressPerson
	 * @param pressDateS
	 * @param pressDateE
	 * @param programType
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getCommunicationList")
	public String getCommunicationList(HttpServletRequest request,
			@RequestParam("optionId") String optionId,
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("infoName") String infoName,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate, Model model) {
		HashMap<String, String> map = new HashMap<String, String>();

		if (optionTitle != "") {
			String optionTitle1 = optionTitle;
			map.put("optionId", optionId);
			map.put("optionTitle", optionTitle1);
		} else if (sDate != "" && eDate != "") {
			map.put("sDate", sDate);
			map.put("eDate", eDate);
		} else {
			map.put("sDate", sDate);
			map.put("eDate", eDate);
			map.put("optionId", optionId);
			map.put("optionTitle", optionTitle);
		}

		List<com.foxconn.cms.communication.pojo.Communication> communicationList = communicationServiceImpl
				.getCommunicationList(map);
		String currentUserNo = SystemContext.getUserNO().trim();

		List<Communication> communicationListProc = new ArrayList();

		if (communicationList != null && communicationList.size() > 0) {
			for (int i = 0; i < communicationList.size(); i++) {
				Communication tempCu = communicationList.get(i);
				if (tempCu.getOptionStatus() == 6
						&& tempCu.getApprovaler().trim()
								.equalsIgnoreCase(currentUserNo)) {
					tempCu.setBtnApproveEnable("true");// button enabled
				} else {
					tempCu.setBtnApproveEnable("false");
				}
				communicationListProc.add(tempCu);
			}
		}

		/*
		 * // for( int i = 0 ; i<length(communicationList);i++){ //
		 * if(((Communication) communicationList).getOptionStatus(i)==null) //
		 * btnArrpoveEnable=false; // else(((Communication)
		 * communicationList).getOptionStatus(i)=="6" &&
		 * strRoleName=="Approver") // btnArrpoveEnable=true; // } //
		 */

		Page page = listToPage(communicationListProc, 1, 15);
		String programType = "010201";
		model.addAttribute("paginations", page);
		model.addAttribute("programType", programType);
		System.out.println("ddd" + page);
		System.out.println(page.getTotalCount());
		return "communication/communicationManage";
	}

	private int length(List<Communication> communicationList) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 文字新闻清单页面
	 * 
	 * @param request
	 * @param newsTitle
	 * @param pressPerson
	 * @param pressDateS
	 * @param pressDateE
	 * @param programType
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=queryCommunicationList")
	public String queryCommunicationList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("optionId") String optionId,
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("infoName") String infoName,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate, Model model)
			throws UnsupportedEncodingException {
		HashMap<String, String> map = new HashMap<String, String>();

		System.out.println(optionId + "|" + optionTitle + "|" + infoName + "|"
				+ sDate + "|" + eDate + "|");

		if (optionTitle != "") {
			map.put("optionId", optionId);
			map.put("optionTitle",
					java.net.URLDecoder.decode(optionTitle, "UTF-8"));
			System.out.print("-------------"+java.net.URLDecoder.decode(optionTitle, "UTF-8"));
		} else if (sDate != "" && eDate != "") {
			map.put("sDate", sDate);
			map.put("eDate", eDate);
		} else {
			map.put("sDate", sDate);
			map.put("eDate", eDate);
			map.put("optionId", optionId);
			map.put("optionTitle", optionTitle);
		}

		List<com.foxconn.cms.communication.pojo.Communication> communicationList = communicationServiceImpl
				.getCommunicationList(map);

		List<Communication> communicationListProc = new ArrayList();
		String currentUserNo = SystemContext.getUserNO().trim();
		if (communicationList != null && communicationList.size() > 0) {
			for (int i = 0; i < communicationList.size(); i++) {
				Communication tempCu = communicationList.get(i);
				if (tempCu.getOptionStatus() == 6
						&& tempCu.getApprovaler().trim()
								.equalsIgnoreCase(currentUserNo)) {
					tempCu.setBtnApproveEnable("true");// button enabled
				} else {
					tempCu.setBtnApproveEnable("false");
				}
				communicationListProc.add(tempCu);
			}
		}

		Page page = listToPage(communicationListProc, Integer.parseInt(pageIndex), 15);
		String programType = "010201";
		model.addAttribute("paginations", page);
		model.addAttribute("programType", programType);
		System.out.println("ddd" + page);
		System.out.println(page.getTotalCount());
		return "communication/communicationManage";
		
		/*Page page = listToPage(communicationListProc, Integer.valueOf(pageIndex),
				Integer.valueOf(pageSize));

		System.out.println(page.getTotalCount());
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	/*
 * */

	/**
	 * 打开新增页面
	 * 
	 * @param request
	 * @param programType
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=insertcommunicationView")
	public String insertTextNewsView(HttpServletRequest request,
			@RequestParam("programType") String programType, Model model) {
		String programType1 = "010201";
		model.addAttribute("programType", programType1);
		return "communication/addCommunication";
	}

	//
	//
	//
	@RequestMapping(params = "action=insertCommunicationData")
	public void insertCommunicationData(HttpServletRequest request,
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("subTitle") String subTitle,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate,
			@RequestParam("optionContent") String optionContent,
			@RequestParam("optionSummary") String optionSummary,
			@RequestParam("approvaler") String approvaler,
			@RequestParam("flag") String flag, HttpServletResponse response) {
		Communication communication = new Communication();
		int optionStatus = 0;
		communication.setOptionId(java.util.UUID.randomUUID().toString());
		try {

			communication.setOptionTitle(java.net.URLDecoder.decode(optionTitle, "UTF-8"));
			communication.setSubTitle(java.net.URLDecoder.decode(subTitle,"UTF-8"));
			String strOptionContent=java.net.URLDecoder.decode(optionContent,"UTF-8");
			communication.setOptionContent(strOptionContent.replaceAll("\"", "\'"));
			if ("1".equals(flag)) { // flag=1:点击存草稿按钮
				optionStatus = 5; // 拟稿状态
			} else if ("2".equals(flag)) { // flag=2:点击已发表按钮
				if (java.net.URLDecoder.decode(approvaler, "UTF-8") != null
						&& !("".equals(java.net.URLDecoder.decode(approvaler,
								"UTF-8")))) {
					optionStatus = 6; // 待审核状态
				} else {
					optionStatus = 7; // 已发表
				}
			} else {// flag=3:点击审核通过按钮
				optionStatus = 7; // 已发表
			}

			communication.setSDate(sDate);
			communication.setEDate(eDate);
			communication.setOptionStatus(optionStatus);
			communication.setOptionSummary (java.net.URLDecoder.decode(optionSummary, "UTF-8"));
			communication.setApprovaler(java.net.URLDecoder.decode(approvaler, "UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		communicationServiceImpl.insertCommunication(communication); // 編輯新闻

		response.setCharacterEncoding("UTF-8");

	}

	/**
	 * 删除文字类新闻(源文件/分发文件)
	 * 
	 * @param model
	 * @param newsID
	 * @param programType
	 * @param response
	 */
	@RequestMapping(params = "action=deleteCommunication")
	public void deleteCommunication(Model model,
			@RequestParam("optionId") String optionId,
			HttpServletResponse response) {

		String msg = "";
		try {
			Communication communication = new Communication();
			communication.setOptionId(optionId);
			communicationServiceImpl.deleteCommunication(communication);// 删除源文件
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失敗！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(params = "action=deleteCommunicationUser")
	public void deleteCommunicationUser(Model model,
			@RequestParam("Id") String Id,
			HttpServletResponse response) {

		String msg = "";
		try {
			Communication communication = new Communication();
			communication.setID(Id);
			communicationServiceImpl.deleteCommunicationUser(communication);// 删除源文件
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失敗！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 打开编辑页面
	 * 
	 * @param request
	 * @param newsID
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=updateCommunicationView")
	public String updateCommunicationView(HttpServletRequest request,
			@RequestParam("optionId") String optionId,
			@RequestParam("pageType") String pageType,
			@RequestParam("programType") String programType, Model model) {
		Communication communication = new Communication();
		communication.setOptionId(optionId);
		Communication communication1 = communicationServiceImpl.getCommunicationDetail(communication);
		List<Communication> communication2 = communicationServiceImpl.preViewcommunicationuser(communication);
		
		System.out.println("qqqqq" + communication1.getOptionContent());
		model.addAttribute("approvalerList",communication2);
		model.addAttribute("optionId", communication1.getOptionId());
		model.addAttribute("optionTitle", communication1.getOptionTitle());
		model.addAttribute("subTitle", communication1.getSubTitle());
		model.addAttribute("sDate", communication1.getSDate());
		model.addAttribute("eDate", communication1.getEDate());
		model.addAttribute("optionSummary", communication1.getOptionSummary());
		model.addAttribute("approvaler", communication1.getApprovaler());
		model.addAttribute("pageType", pageType);		
		model.addAttribute("optionContent", communication1.getOptionContent());
		
		return "communication/updateCommunication";
	}

	/**
	 * 存草稿(编辑)/已发表(编辑)/审核通过
	 * 
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
	@RequestMapping(params = "action=updateCommunicationData")
	public void updateCommunicationData(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("optionId") String optionId,
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("subTitle") String subTitle,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate,
			@RequestParam("optionContent") String optionContent,
			@RequestParam("optionSummary") String optionSummary,
			@RequestParam("approvaler") String approvaler,
			@RequestParam("flag") String flag) {
		System.out.println("communication" + approvaler+ sDate);
		int optionStatus = 0;
		Communication communication = new Communication();
		
		try {
			communication.setOptionTitle(java.net.URLDecoder.decode(
					optionTitle, "UTF-8"));
			communication.setSubTitle(java.net.URLDecoder.decode(subTitle,
					"UTF-8"));
			String strOptionContent=java.net.URLDecoder.decode(optionContent,"UTF-8");
			communication.setOptionContent(strOptionContent.replaceAll("\"", "\'"));
			if ("1".equals(flag)) { // flag=1:点击存草稿按钮
				optionStatus = 5; // 拟稿状态
			} else if ("2".equals(flag)) { // flag=2:点击已发表按钮
				if (java.net.URLDecoder.decode(approvaler, "UTF-8") != null
						&& !("".equals(java.net.URLDecoder.decode(approvaler,
								"UTF-8")))) {
					optionStatus = 6; // 待审核状态
				} else {
					optionStatus = 7; // 已发表
				}
			} else {// flag=3:点击审核通过按钮
				optionStatus = 7; // 已发表
			}

			communication.setSDate(sDate);
			communication.setEDate(eDate);

			communication.setOptionStatus(optionStatus);
			communication.setOptionSummary(java.net.URLDecoder.decode(optionSummary, "UTF-8"));
			communication.setApprovaler(java.net.URLDecoder.decode(approvaler,"UTF-8"));
			communication.setOptionId(optionId);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		communicationServiceImpl.updateCommunication(communication); // 編輯新闻

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 打開審核頁面
	 * 
	 * @param request
	 * @param newsID
	 * @param model
	 * @return
	 */
	// @RequestMapping(params = "action=approvalcommunication")
	// public String approvalcommunication(HttpServletRequest request,
	// @RequestParam("optionId") String optionId,
	// Model model){
	// Communication communication = new Communication();
	// communication.setOptionId(optionId);
	// Communication communication1 =
	// communicationServiceImpl.getCommunicationDetail(communication);
	//
	// List<SelectBean> newsSourceList = textNewsServiceImpl.selectNewsSource();
	//
	//
	// model.addAttribute("newsID",textNews1.getNewsID());
	// model.addAttribute("newsTitle",textNews1.getNewsTitle());
	// model.addAttribute("subNewsTitle",textNews1.getSubNewsTitle());
	// model.addAttribute("newsContent",textNews1.getNewsContent());
	// model.addAttribute("entryDate",textNews1.getEntryDate());
	// model.addAttribute("writer",textNews1.getWriter());
	// model.addAttribute("newsSourceOld",textNews1.getNewsSource());
	// model.addAttribute("newsSourceList",newsSourceList);
	// model.addAttribute("newsSourceName",textNews1.getNewsSourceName());
	// model.addAttribute("newsKeyWord",textNews1.getNewsKeyWord());
	// model.addAttribute("newsSummary",textNews1.getNewsSummary());
	// model.addAttribute("approvaler",textNews1.getApprovaler());
	// model.addAttribute("isPhotosShow",textNews1.getIsPhotosShow());
	// model.addAttribute("programType",textNews1.getProgramType());
	//
	// model.addAttribute("isPu",isPu);
	// model.addAttribute("isTou",isTou);
	// model.addAttribute("isCi",isCi);
	//
	// model.addAttribute("isPreViewHide","display:none");
	// model.addAttribute("isDraftHide", "display:none");
	// model.addAttribute("isPublishHide","display:none");
	// model.addAttribute("isRefuseHide", "");
	// model.addAttribute("isAppHide","");
	//
	// return "textnews/updateTextNews";
	// }
	//
	@RequestMapping(params = "action=previewFormList")
	public String previewFormList(HttpServletRequest request,
			@RequestParam("optionId") String optionId, Model model) {
	    System.out.println("qqqq"+optionId);
		Communication communication = new Communication();
		communication.setOptionId(optionId);
		Communication communication1 = communicationServiceImpl.preViewcommunication(communication);
		List<Communication> communication2 = communicationServiceImpl.preViewcommunicationuser(communication);
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		String strCurrentDate = String.valueOf(c.get(Calendar.YEAR))
				+ "年"
				+ StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH)), 2,
						'0')
				+ "月"
				+ StringUtils.leftPad(String.valueOf(c.get(Calendar.DATE)), 2,
						'0') + "日";

		// model.addAttribute("readRecordCount",textNews1.getReadRecordCount());
		model.addAttribute("optionId", communication1.getOptionId());
		model.addAttribute("optionContent", communication1.getOptionContent().toString());
		model.addAttribute("optionTitle", communication1.getOptionTitle().toString());
		model.addAttribute("currentsDate", communication1.getSDate());
		model.addAttribute("currenteDate", communication1.getEDate().toString());
		model.addAttribute("readRecordCount", communication1.getReadRecordCount().toString());
		model.addAttribute("communication", communication2);
		return "communication/preViewCommunication";
	}

	
	
	@RequestMapping(params="action=preViewViewS")
	public void preViewViewS(
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("subTitle") String subTitle,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate,
			@RequestParam("optionContent") String optionContent,
			@RequestParam("optionSummary") String optionSummary,
			@RequestParam("approvaler") String approvaler,
			HttpServletResponse response){
		try{
			optionTitle=java.net.URLDecoder.decode(optionTitle,"UTF-8");
			subTitle=java.net.URLDecoder.decode(subTitle,"UTF-8");
			sDate=java.net.URLDecoder.decode(sDate,"UTF-8");
			eDate=java.net.URLDecoder.decode(eDate,"UTF-8");
			optionContent=java.net.URLDecoder.decode(optionContent,"UTF-8");
			optionSummary=java.net.URLDecoder.decode(optionSummary,"UTF-8");
			approvaler=java.net.URLDecoder.decode(approvaler,"UTF-8");
			String strBuf = optionTitle+";;"+subTitle+";;"+sDate+";;"+eDate+";;"+optionContent+";;"+optionSummary+";;"+approvaler;
			this.setStrContent(strBuf);
			this.setStrContent(strBuf);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		response.setCharacterEncoding("UTF-8");
	}	

	
	@RequestMapping(params = "action=preViewViewT")
    public String preViewViewT(HttpServletRequest request,
			Model model){
		try{
		String  strTmp=java.net.URLDecoder.decode(this.getStrContent(),"UTF-8");
		String arr[]=strTmp.split(";;");
		model.addAttribute("optionTitle",arr[0]);
		model.addAttribute("currentsDate",arr[2]);
		model.addAttribute("currenteDate",arr[3]);
		model.addAttribute("optionContent",arr[4]);
		model.addAttribute("optionId","0000000");
		model.addAttribute("readRecordCount","0");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "communication/preViewCommunication";
    }
	
	
	// @RequestMapping(params = "action=preViewViewT")
	// public String preViewViewT(HttpServletRequest request,
	// Model model){
	// try{
	// String strTmp=java.net.URLDecoder.decode(this.getStrContent(),"UTF-8");
	// String arr[]=strTmp.split(";;");
	// String newsSource =
	// textNewsServiceImpl.selectNewSourceName(Integer.valueOf(arr[2]));
	// model.addAttribute("newsTitle",arr[0]);
	// model.addAttribute("newsContent",arr[1]);
	// model.addAttribute("newsSourceName",newsSource);
	// model.addAttribute("currentDate",arr[3]);
	// }catch(Exception ex){
	// ex.printStackTrace();
	// }
	// return "textnews/preViewTextNews";
	// }
	//
	// @RequestMapping(params="action=preViewViewS")
	// public void preViewViewS(
	// @RequestParam("newsTitle") String newsTitle,
	// @RequestParam("newsContent") String newsContent,
	// @RequestParam("newsSource") String newsSource,
	// @RequestParam("isPhotosShow") String isPhotosShow,
	// HttpServletResponse response){
	// Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
	// String strCurrentDate=String.valueOf(c.get(Calendar.YEAR))+"年"+
	// StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH)), 2, '0')+"月"+
	// StringUtils.leftPad(String.valueOf(c.get(Calendar.DATE)), 2, '0')+"日";
	// try{
	// newsTitle=java.net.URLDecoder.decode(newsTitle,"UTF-8");
	// newsContent=java.net.URLDecoder.decode(newsContent,"UTF-8");
	// newsSource=java.net.URLDecoder.decode(newsSource,"UTF-8");
	// String strBuf =
	// newsTitle+";;"+newsContent+";;"+newsSource+";;"+strCurrentDate;
	// this.setStrContent(strBuf);
	// this.setStrContent(strBuf);
	// }catch(Exception ex){
	// ex.printStackTrace();
	// }
	//
	//
	//
	// response.setCharacterEncoding("UTF-8");
	// // try{
	// // response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
	// // }catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// }

	/***
	 * 清单查询
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param pressPerson
	 * @param pressDateS
	 * @param pressDateE
	 * @param programType
	 * @param response
	 */
	@RequestMapping(params = "action=queryCommunicationListByPageIndex")
	public void queryTextNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("optionTitle") String optionTitle,
			@RequestParam("infoname") String infoname,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate, HttpServletResponse response) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			map.put("optionTitle",
					java.net.URLDecoder.decode(optionTitle, "UTF-8"));
			map.put("infoname", java.net.URLDecoder.decode(infoname, "UTF-8"));
			map.put("sDate", sDate);
			map.put("eDate", eDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		@SuppressWarnings("rawtypes")
		List<com.foxconn.cms.communication.pojo.Communication> communicationList = communicationServiceImpl
				.getCommunicationList(map);
		Page page = listToPage(communicationList, Integer.valueOf(pageIndex),
				Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页转化
	 * 
	 * @param list
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page listToPage(List list, Integer pageIndex, Integer pageSize) {
		Page page = null;
		int index = pageIndex.intValue();
		int size = pageSize.intValue();
		long totalCount = list.size();
		int fromIndex = 0;
		int toIndex = 0;
		if (index * size < totalCount) {
			fromIndex = (index - 1) * size;
			toIndex = index * size;
		} else {
			fromIndex = (index - 1) * size;
			toIndex = list.size();
		}
		list = list.subList(fromIndex, toIndex);
		page = new Page(index, size, totalCount, list);
		return page;
	}
}
