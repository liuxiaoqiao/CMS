package com.foxconn.cms.textnews.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.pojo.PageNewsRef;
import com.foxconn.cms.textnews.service.TextNewsService;
import com.foxconn.cms.textnews.dao.TextNewsDao;
import com.foxconn.pojo.SelectBean;

@Service("textNewsServiceImpl")
public class TextNewsServiceImpl implements  TextNewsService{
	@Autowired
	@Resource(name = "textNewsDao")
	private TextNewsDao textNewsDao;
	
	/**
	 * 数据来源下拉框绑定
	 */
	@Override
	public List<SelectBean> selectDateSource(){
		return textNewsDao.selectDateSource();
	}
	
	/**
	 * 文檔来源下拉框绑定
	 */
	@Override
	public  List<SelectBean> selectNewsSource(){
		return textNewsDao.selectNewsSource();
	}
	
	@Override
	public String selectNewSourceName(int infoID){
		return textNewsDao.selectNewSourceName(infoID);
	}
	
	 /**
	  * 获取解析过来外部稿件
	  * @param textNews
	  * @return
	  */
	@Override
	 public List<TextNews> getOutTextNewsList(TextNews textNews){
		 return textNewsDao.getOutTextNewsList(textNews);
	 }
	
	 /**
	  * 获取解析过来外部稿件(分页)
	  * @param textNews
	  * @param pageNumber
	  * @return
	  */
	 public List<TextNews> getOutTextNewsListForLov(TextNews textNews,int pageNumber){
		 return textNewsDao.getOutTextNewsListForLov(textNews, pageNumber);
	 }

	@Override
	public List<TextNews> getTextNewsList(TextNews textNews) {
		// TODO Auto-generated method stub
		return textNewsDao.getTextNewsList(textNews);
	}

	@Override
	public void insertTextNews(TextNews textNews) {
		textNewsDao.insertTextNews(textNews);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTextNews(TextNews textNews) {
		textNewsDao.updateTextNews(textNews);
		// TODO Auto-generated method stub
		
	}

	@Override
	public TextNews preViewTextNews(TextNews textNews) {
		// TODO Auto-generated method stub
		return textNewsDao.preViewTextNews(textNews);
	}

	@Override
	public List<NewsRefuseRecord> getNewsRefuseRecordList(
			NewsRefuseRecord newsRefuseRecord) {
		// TODO Auto-generated method stub
		return textNewsDao.getNewsRefuseRecordList(newsRefuseRecord);
	}
	

	 /**
	  * 判断是否第一次分发
	  * @param pageNewsRef
	  * @return
	  */
	@Override
	public int validateIsFirstDis(PageNewsRef pageNewsRef){
		return textNewsDao.validateIsFirstDis(pageNewsRef);
	}

	@Override
	public void deleteTextNews(TextNews textNews) {
		// TODO Auto-generated method stub
		textNewsDao.deleteTextNews(textNews);
		
	}

	@Override
	public void deletePageNewsRef(PageNewsRef pageNewsRef) {
		// TODO Auto-generated method stub
		textNewsDao.deletePageNewsRef(pageNewsRef);
		
	}

	@Override
	public void insertRefuseTextNews(NewsRefuseRecord newsRefuseRecord) {
		// TODO Auto-generated method stub
		textNewsDao.insertRefuseTextNews(newsRefuseRecord);
		
	}
	
	@Override
	public void updateTextNewsStatus(TextNews textNews){
		textNewsDao.updateTextNewsStatus(textNews);
	}
	
	 /**
	  * 提供给编辑页面初始化
	  * @param textNews
	  * @return
	  */
	@Override
	 public TextNews getTextNewsDetail(TextNews textNews){
		return (TextNews)textNewsDao.getTextNewsDetail(textNews);
	}
	
	 /**
	  * 新增分发关系
	  * @param pageNewsRef
	  */
	 @Override
	 public void insertPageNewsRef(PageNewsRef pageNewsRef){
		 textNewsDao.insertPageNewsRef(pageNewsRef);
	 }

}
