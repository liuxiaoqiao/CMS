package com.foxconn.cms.textnews.service;

import java.util.List;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.pojo.PageNewsRef;
import com.foxconn.pojo.SelectBean;

public interface TextNewsService {
	 List<TextNews> getOutTextNewsList(TextNews textNews);
	 List<TextNews> getTextNewsList(TextNews textNews);
	 void insertTextNews(TextNews textNews);
	 void updateTextNews(TextNews textNews);
	 TextNews preViewTextNews(TextNews textNews);
	 List<NewsRefuseRecord> getNewsRefuseRecordList(NewsRefuseRecord newsRefuseRecord);
	 int validateIsFirstDis(PageNewsRef pageNewsRef);
	 void deleteTextNews(TextNews textNews);
	 void deletePageNewsRef(PageNewsRef pageNewsRef);
	 void insertRefuseTextNews(NewsRefuseRecord newsRefuseRecord);	
	 void updateTextNewsStatus(TextNews textNews);
	 TextNews getTextNewsDetail(TextNews textNews);
	 void insertPageNewsRef(PageNewsRef pageNewsRef);
	 List<SelectBean> selectDateSource();
	 List<SelectBean> selectNewsSource();
	 String selectNewSourceName(int infoID);
	 List<TextNews> getOutTextNewsListForLov(TextNews textNews,int pageNumber);
}
