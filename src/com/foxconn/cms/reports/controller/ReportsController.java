package com.foxconn.cms.reports.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
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

import com.foxconn.cms.reports.service.impl.ReportsServiceImpl;
import com.foxconn.util.FileUploader;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.PaginationRelated;
import com.sun.star.bridge.oleautomation.Date;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

@Controller
@RequestMapping("/reports.do")
public class ReportsController {	
	@Autowired
	@Resource(name="ReportsImpl")
	private ReportsServiceImpl ReportsImpl;
	
	//“稿件采用分析”左侧菜单链接，初始页面时间值取当月初到当月末，总数
	@RequestMapping(params="action=iniAnalysisUsingManuscript")
	public String iniAnalysisUsingManuscript(Model model 
			) throws Exception{
		Calendar c = Calendar.getInstance();
		int max = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH)+1;
		String sDate = y+"."+m+"."+01;
		String eDate = y+"."+m+"."+max;
//		String strPie="",strPercent="";
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("sDate",sDate);
		map.put("eDate",eDate );
		{ List<java.util.HashMap> AnalysisUsingData=ReportsImpl.getAnalysisUsingManuscript(map);
		Object totle= "";
		String getDate="";          
	    getDate=sDate+"~"+ eDate;
//		if(AnalysisUsingData!=null&&AnalysisUsingData.size()>0){  
//			totle = AnalysisUsingData.get(0).get("TOTLE");
//			for(int i=0;i<AnalysisUsingData.size();i++){
//				strPercent = AnalysisUsingData.get(i).get("PERCENT").toString().replace("%","");
//				strPie+="['"+AnalysisUsingData.get(i).get("SOURCENAME")+"',"+strPercent+"],";
//			}
//			strPie = strPie.substring(0,strPie.length()-1);
//			System.out.println("strPie="+strPie);
//		}
		    model.addAttribute("totle",totle);                            //总数
//		    model.addAttribute("strPie",strPie);                          //饼图
		    model.addAttribute("AnalysisUsingData", AnalysisUsingData);   
		    model.addAttribute("getDate",getDate);                        
			model.addAttribute("sDate",sDate);
			model.addAttribute("eDate",eDate );
		}
		return "reports/AnalysisUsingManuscript";
	}	
	
	//“稿件访问量分析”初始化页面时间值取当月初到当月末
	@RequestMapping(params="action=iniAnalysisaccess")
	public String iniAnalysisaccess(Model model 
			) throws Exception{
		Calendar c = Calendar.getInstance();
		int max = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH)+1;
		
		String sDate = y+"."+m+"."+01;
		String eDate = y+"."+m+"."+max;
		int i=10;
		String ranknum = Integer.toString(i);
		System.out.println("enter the control iniAnalysisaccess =>"+sDate+" "+eDate);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("sDate",sDate);
		map.put("eDate",eDate );
		map.put("ranknum", ranknum );
		{List<java.util.HashMap> AnalysisaccessData=ReportsImpl.getAnalysisaccess(map);
		    model.addAttribute("AnalysisaccessData", AnalysisaccessData);
			model.addAttribute("sDate",sDate);
			model.addAttribute("eDate",eDate );
			model.addAttribute("ranknum",ranknum );
		}
		System.out.println("enter the control iniAnalysisaccess");
		return "reports/Analysisaccess";
	}	
	//******************
	//“稿件采用分析”查询功能，饼图
	@RequestMapping(params="action=getAnalysisUsingManuscript")
	public String getAnalysisUsingManuscript(Model model			
			,@RequestParam("sDate") String sDate
			,@RequestParam("eDate") String eDate
			) throws Exception{
//		String strPie="",strPercent="";
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("sDate",sDate);
		map.put("eDate",eDate );
		{List<java.util.HashMap> AnalysisUsingData=ReportsImpl.getAnalysisUsingManuscript(map);
		
		Object totle= "";
		//饼图：
//		if(AnalysisUsingData!=null&&AnalysisUsingData.size()>0){
//			totle = AnalysisUsingData.get(0).get("TOTLE");
//			for(int i=0;i<AnalysisUsingData.size();i++){
//				strPercent = AnalysisUsingData.get(i).get("PERCENT").toString().replace("%","");
//				strPie+="['"+AnalysisUsingData.get(i).get("SOURCENAME")+"',"+strPercent+"],";
//			}
//			strPie = strPie.substring(0,strPie.length()-1);
//			System.out.println("strPie="+strPie);
//		}
		String getDate="";
		getDate=sDate+"~"+ eDate;
		model.addAttribute("totle",totle);
//		model.addAttribute("strPie",strPie);
		model.addAttribute("AnalysisUsingData", AnalysisUsingData);
		model.addAttribute("getDate",getDate);
		return "reports/AnalysisUsingManuscript";
		}
	}
	//“稿件访问量分析”查询功能
	@RequestMapping(params="action=getAnalysisaccess")
	public String getAnalysisaccess(Model model			
			,@RequestParam("sDate") String sDate
			,@RequestParam("eDate") String eDate
			,@RequestParam("ranknum") String ranknum
			
			) throws Exception{
HashMap<String, String> map=new HashMap<String, String>();
		
		map.put("sDate",sDate);
		map.put("eDate",eDate );
		map.put("ranknum",ranknum);
		
		{List<java.util.HashMap> AnalysisaccessData=ReportsImpl.getAnalysisaccess(map);
		
		System.out.println("AnalysisaccessData"+ranknum);
		model.addAttribute("AnalysisaccessData", AnalysisaccessData);
		return "reports/Analysisaccess";
	    }
	}
//               导出功能
//	@RequestMapping(params="action=exportexl")
//	public  String exportexl(Model model			
//			,@RequestParam("sDate") String sDate
//			,@RequestParam("eDate") String eDate
//			) throws Exception{HashMap<String, String> map=new HashMap<String, String>();
//	   		map.put("sDate",sDate);
//	   		map.put("eDate",eDate );
//	   		{
//	   			List<java.util.HashMap> getExport=ReportsImpl.getAnalysisaccess(map);
//	   			System.out.println("AnalysisaccessData"+getExport);
//	   			try{
//	   				WritableWorkbook book  =  Workbook.createWorkbook( new  File( "test.xls" ));
//	   				WritableSheet sheet  =  book.createSheet( " 第一页 " ,  0 );
//	   				 
//	   				 Label label  =   new  Label( 0 ,  0 ,  " 排序 " );
//	   				 sheet.addCell(label);
//	   			
//	   				 
//	   				
//	   				
//	   				 label = new  Label(1,0,"稿件名称");    //NEWS_TITLE
//	   				sheet.addCell(label);
//	   			     label= new  Label(2,0,"栏目名称");    //TYPE
//	   			 sheet.addCell(label);
//	   			     label= new  Label(3,0,"发稿人");      //PUBLISHER
//	   			 sheet.addCell(label);
//	   			     label= new  Label(4,0,"作者");        //WRITER
//	   			 sheet.addCell(label);
//	   			     label= new  Label(5,0,"稿件来源");    //NEWS_ID
//	   			 sheet.addCell(label);
//	   			     label= new  Label(6,0,"点击数");      //READ_COUNT
//	   			 sheet.addCell(label);
//	   			 for (int j=0;j<10;j++)
//	   			 {
//	   				Label xm= new  Label(j,1,(String) getExport.get(j).get("count"));  
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,2,(String) getExport.get(j).get("NEWS_TITLE"));
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,3,(String) getExport.get(j).get("TYPE"));
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,4,(String) getExport.get(j).get("PUBLISHER"));
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,5,(String) getExport.get(j).get("WRITER"));
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,6,(String) getExport.get(j).get("NEWS_ID"));
//	   				sheet.addCell(xm);
//	   				xm = new Label(j,7,(String) getExport.get(j).get("READ_COUNT"));
//	   				sheet.addCell(xm);
//	   				
//	   			 }
//	   		         
//	   		         book.write();
//	   		            book.close();
//
//	   		        }   catch  (Exception e)  {
//	   		            System.out.println(e);
//	   		        }
//	   	}
//	   		return "reports/Analysisaccess";
//	}
}
	

