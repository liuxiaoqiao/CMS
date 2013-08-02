package com.foxconn.cms.photos.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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

import com.foxconn.cms.leaderinfo.pojo.LeaderInfo;
import com.foxconn.cms.photos.service.PhotosService;
import com.foxconn.cms.photos.pojo.PhotosInfo;
import com.foxconn.cms.photos.pojo.PhotosNews;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.service.TextNewsService;
import com.foxconn.util.FileUploader;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.Page;
import com.foxconn.util.SystemContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;
import java.util.Calendar;

import java.io.*; 
import java.util.*; 
import org.apache.commons.fileupload.*; 
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*; 


@Controller
@RequestMapping("photosNews")
public class PhotosController {
	@Autowired
	@Resource(name = "photosServiceImpl")
	private PhotosService photosServiceImpl;
	
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	private String fileFileName;

	private File file;

	private String contentId;

	private static final String PATH = "";// FilePathSptUtil.UPLOAD_ROOT_PATH;

	private static final String SPT = File.separator;

	// UPLOAD_ROOT_PATH在FilePathSptUtil类中定义，从配置文件读取的路径

	// UPLOAD_ROOT_PATH =prop.getProperty("upload.root.path","/");

	public String getContentId() {

		return contentId;

	}

	public void setContentId(String contentId) {

		this.contentId = contentId;

	}

	public File getFile() {

		return file;

	}

	public void setFile(File file) {

		this.file = file;

	}

	public String getFileFileName() {

		return fileFileName;

	}

	public void setFileFileName(String fileFileName) {

		this.fileFileName = fileFileName;

	}
	

	@RequestMapping(params = "action=saveAttach")
    public String saveAttach(
			Model model,@RequestParam("contentId") String contentId,
			HttpServletResponse response		
    ){

       // TopicContentAttach noticeAttachs=newTopicContentAttach();

       // TopicalContent obj=null;

       // if(msgContentId!=null&&StringUtils.isNotBlank(msgContentId)){

       // obj = topicalContentService.getTopicalContent(msgContentId);

       // }else{

    //    obj= new TopicalContent();

     //   }

     //  noticeAttachs.setTopicalcontent(obj);

       String nameflll=upload();

     //noticeAttachs.setAttachUrl(FilePathSptUtil.UPLOAD_CMS+FilePathSptUtil.URL_SPT+FilePathSptUtil.CMS_ZHUANTI+

        //       FilePathSptUtil.URL_SPT+DateUtil.formatDateToString(new Date(), "yyyy-MM")+FilePathSptUtil.URL_SPT+nameflll);

      // noticeAttachs.setAttachName(fileFileName);

    //   noticeAttachs.setUploadOriginName(fileFileName);

       String fileExtend = fileFileName.substring(fileFileName.lastIndexOf(".")+1,fileFileName.length());

       //noticeAttachs.setAttachType(fileExtend);

     //  topicContentAttachService.save(noticeAttachs);

       return "";
     // return renderText("{    attachId:\""+"noticeAttachs.getAttachId()"+"\" ,  attachName:\""+"noticeAttachs.getAttachName()"+"\" ,   attachUrl:\""+"noticeAttachs.getAttachUrl()"+"\" }"); 
    }

     private String upload(){

       SimpleDateFormat formater =new SimpleDateFormat("yyyyMMddhhmmssSSSS");

       String filename=formater.format(new Date());

       String fileExtend = fileFileName.substring(fileFileName.lastIndexOf(".")+1,fileFileName.length());

       String root= "";//FilePathSptUtil.UPLOAD_ROOT_PATH+FilePathSptUtil.UPLOAD_CMS+SPT+FilePathSptUtil.CMS_ZHUANTI+SPT+

       //DateUtil.formatDateToString(new Date(),"yyyy-MM");

       String fullName=filename+"."+fileExtend;

       String dstPath = root+SPT+fullName;   //全路径

 

        File dirFile = new File(root);

       boolean isDir = dirFile.isDirectory();

       if(!isDir){//目录不存在则先建目录

           try{

              dirFile.mkdirs();

           }catch (Exception e) {

              File delFile = new File(root+SPT +fullName );

              deleteExitsFile(delFile);

           }

       }

      //FileUtil.copyFile(file.getPath(), dstPath);//上传文件
   

       return fullName;

    }

    private void deleteExitsFile(File file) {

       if(file.exists() && file.isFile()){

           file.delete();

       }

    }
    
	@RequestMapping(method=RequestMethod.POST,params = "action=mulFileUpload")
    public void mulFileUpload(
    		MultipartHttpServletRequest request,HttpServletResponse response,Model model	
    ) throws ServletException, IOException{
		if (ServletFileUpload.isMultipartContent(request)) {  

		    DiskFileItemFactory factory = new DiskFileItemFactory();  
		    factory.setSizeThreshold(1426);  
		    factory.setRepository(new File("C:/ftp/")); 
		    ServletFileUpload upload = new ServletFileUpload(factory);  
		    upload.setSizeMax(2000 * 1024);  
		    upload.setFileSizeMax(1000 * 1024);  
		    List<FileItem> items;  
		    try {  
		        items = upload.parseRequest(request);  
		    } catch (FileUploadException e) {  
		        throw new ServletException(e); 
		    }  

		    for (Object val : items) {  
		        FileItem item = (FileItem) val;  
		        if (item.isFormField()) {  

		          System.out.println(item.getFieldName());  
		        } else {  
  
		            File f = new File(item.getName());  
		            try {  
		                item.write(new File("C:/ftp/", f.getName()));  
		                System.out.println(f.getName());  
		            } catch (IOException e) {  
		                throw e;  
		            } catch (Exception e) {  
		                throw new ServletException(e);  
		            }  
		              
		        }  
		    }  
		} 
    }

	

	@RequestMapping(params = "action=getPhotosNewsList")
	public String getPhotosNewsList(HttpServletRequest request,
			@RequestParam("photosNewsTitle") String photosNewsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("pressDateS") String pressDateS,
			@RequestParam("pressDateE") String pressDateE, Model model) {
		PhotosNews photosNews = new PhotosNews();
		photosNews.setPhotosNewsTitle(photosNewsTitle);
		photosNews.setEntryUser(entryUser);
		photosNews.setPressDateS(pressDateS);
		photosNews.setPressDateE(pressDateE);
		List<PhotosNews> photosNewsList = photosServiceImpl
				.getPhotosNewsList(photosNews);
		Page page = listToPage(photosNewsList, 1, 15);

		model.addAttribute("paginations", page);

		return "photos/photosManage";
	}

	@RequestMapping(params = "action=queryPhotosNewsListByPageIndex")
	public void queryPhotosNewsListByPageIndex(
			@RequestParam("pageIndex") String pageIndex,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("photosNewsTitle") String photosNewsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("pressDateS") String pressDateS,
			@RequestParam("pressDateE") String pressDateE,
			HttpServletResponse response) {
		PhotosNews photosNews = new PhotosNews();
		try {
			photosNews.setPhotosNewsTitle(java.net.URLDecoder.decode(
					photosNewsTitle, "UTF-8"));
			photosNews.setEntryUser(java.net.URLDecoder.decode(entryUser,
					"UTF-8"));
			photosNews.setPressDateS(pressDateS);
			photosNews.setPressDateE(pressDateE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		List<PhotosNews> photosNewsList = photosServiceImpl
				.getPhotosNewsList(photosNews);
		Page page = listToPage(photosNewsList, Integer.valueOf(pageIndex),
				Integer.valueOf(pageSize));
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.transferPagedDataToJSon(page));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "action=deletePhotosNews")
	public void deletePhotosNews(Model model,
			@RequestParam("photosNewsID") String photosNewsID,
			HttpServletResponse response) {

		String msg = "";
		try {
			PhotosNews photosNews = new PhotosNews();
			photosNews.setPhotosNewsID(photosNewsID);
			photosNews.setModifyUser(SystemContext.getUserNO());
			photosServiceImpl.deletePhotosNews(photosNews);
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
	
	@RequestMapping(params = "action=openRefusePhotoNews")
	public String openRefusePhotoNews(
			@RequestParam("photosNewsID") String photosNewsID,
            HttpServletRequest request,
			Model model) {
		model.addAttribute("newsID", photosNewsID);
		model.addAttribute("newsType","22");
		return "textnews/refuseTextNews";
	}
	
	@RequestMapping(params="action=insertRefusePhotosNews")
	public void insertRefusePhotosNews(
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
		
		PhotosNews photosNews = new PhotosNews();
		photosNews.setPhotosNewsID(newsID);
		photosNews.setPhotosNewsStatus(8);
		photosNews.setModifyUser(SystemContext.getUserNO());
		photosServiceImpl.updatePhotosNewsStatus(photosNews);
		
		response.setCharacterEncoding("UTF-8");
	}	

	/**
	 * 
	 * @param photosNewsID
	 * @param sFlag
	 *            1:新增；2.編輯；3.審核
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=turnToAddPhotosNews")
	public String turnToAddPhotosNews(
			@RequestParam("photosNewsID") String photosNewsID,
			@RequestParam("sFlag") String sFlag, Model model) {
		String isPreViewHide = "display:none";
		String isDraftHide = "display:none";
		String isPublishHide = "display:none";
		String isRefuseHide = "display:none";
		String isAppHide = "display:none";

		if ("1".equals(sFlag)) {
			isPreViewHide = "";
			isDraftHide = "";
			isPublishHide = "";
		} else if ("2".equals(sFlag)) {
			isPreViewHide = "";
			isDraftHide = "";
			isPublishHide = "";
		} else {
			isRefuseHide = "";
			isAppHide = "";
		}

		model.addAttribute("isPreViewHide", isPreViewHide);
		model.addAttribute("isDraftHide", isDraftHide);
		model.addAttribute("isPublishHide", isPublishHide);
		model.addAttribute("isRefuseHide", isRefuseHide);
		model.addAttribute("isAppHide", isAppHide);

		model.addAttribute("photosNewsID", photosNewsID);

		return "photos/addPhotos";

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
