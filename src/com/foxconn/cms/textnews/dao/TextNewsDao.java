package com.foxconn.cms.textnews.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.cms.textnews.pojo.NewsRefuseRecord;
import com.foxconn.cms.textnews.pojo.PageNewsRef;
import com.foxconn.pojo.SelectBean;
import com.foxconn.util.PageListBean;




@Service("textNewsDao")
public class TextNewsDao {
 @Resource(name="sqlMapClientTemplate")
 private SqlMapClientTemplate sqlMapClientTemplate;
 

 /**
  * 数据来源下拉框查询
  * @return
  */
 public List<SelectBean> selectDateSource(){
	 return this.sqlMapClientTemplate.queryForList("TextNews.selectDateSource");
 }
 
 /**
  * 文檔来源下拉框查询
  * @return
  */
 public List<SelectBean> selectNewsSource(){
	 return this.sqlMapClientTemplate.queryForList("TextNews.selectNewsSource");
 }
 
 public String selectNewSourceName(int infoID){
	 TextNews textNews=new TextNews();
	 textNews.setNewsSource(infoID);
	 return  this.sqlMapClientTemplate.queryForObject("TextNews.selectNewSourceName", textNews).toString();
 }
 
 /**
  * 获取解析过来外部稿件
  * @param textNews
  * @return
  */
 public List<TextNews> getOutTextNewsList(TextNews textNews){
	 return this.sqlMapClientTemplate.queryForList("TextNews.getOutTextNewsList",textNews);
 }

 
 /**
  * 获取解析过来外部稿件(分页)
  * @param textNews
  * @param pageNumber
  * @return
  */
 public List<TextNews> getOutTextNewsListForLov(TextNews textNews,int pageNumber) {
		return this.sqlMapClientTemplate.queryForList(
				"TextNews.getOutTextNewsList", textNews,
				(pageNumber - 1) * PageListBean.DEFAULT_PAGE_SIZE,
				PageListBean.DEFAULT_PAGE_SIZE);
	} 
 
 /**
  * 文字新闻管理页面新闻列表查询
  * @param textNews
  * @return
  */
 public List<TextNews> getTextNewsList(TextNews textNews){
 	return this.sqlMapClientTemplate.queryForList("TextNews.getTextNewsList",textNews);
 }
 
 /**
  * 新增文字新闻
  * @param textNews
  */
 public void insertTextNews(TextNews textNews){
 	this.sqlMapClientTemplate.insert("TextNews.insertTextNews",textNews);
 }
 
 /**
  * 编辑修改文字新闻
  * @param textNews
  */
 public void updateTextNews(TextNews textNews){
	 this.sqlMapClientTemplate.update("TextNews.updateTextNews", textNews);
 }
 
 /**
  * 提供给编辑页面初始化
  * @param textNews
  * @return
  */
 public TextNews getTextNewsDetail(TextNews textNews){
	 return (TextNews)this.sqlMapClientTemplate.queryForObject("TextNews.getTextNewsDetail", textNews);
 }
 
 /**
  * 预览
  * @param textNews
  * @return
  */
 public TextNews preViewTextNews(TextNews textNews){
	 List<TextNews> textNewsList = this.sqlMapClientTemplate.queryForList("TextNews.preViewTextNews",textNews);
	 if(textNewsList!=null&&textNewsList.size()>0){
		 return textNewsList.get(0);
	 }else{
		 return new TextNews();
	 }
 }
 
 /**
  * 拒绝记录查询
  * @param newsRefuseRecord
  * @return
  */
 public List<NewsRefuseRecord> getNewsRefuseRecordList(NewsRefuseRecord newsRefuseRecord){
	 return this.sqlMapClientTemplate.queryForList("TextNews.getNewsRefuseRecordList",newsRefuseRecord);
 }
 
 /**
  * 判断是否第一次分发
  * @param pageNewsRef
  * @return
  */
  public int validateIsFirstDis(PageNewsRef pageNewsRef) {		
	return (Integer) this.sqlMapClientTemplate.queryForObject("TextNews.validateIsFirstDis",pageNewsRef);		
  }
 
 /**
  * 删除文字新闻 （注：删除源文件；逻辑删除）
  * @param textNews
  */
 public void deleteTextNews(TextNews textNews){
	 this.sqlMapClientTemplate.update("TextNews.deleteTextNews",textNews);
 }

 /**
  * 删除文字新闻（注：删除被分发的文件；逻辑删除）
  * @param pageNewsRef
  */
 public void deletePageNewsRef(PageNewsRef pageNewsRef){
	 this.sqlMapClientTemplate.update("TextNews.deletePageNewsRef",pageNewsRef);
 }
 
 public void insertRefuseTextNews(NewsRefuseRecord newsRefuseRecord){
	 this.sqlMapClientTemplate.insert("TextNews.insertRefuseTextNews",newsRefuseRecord);
 }
 
 public void updateTextNewsStatus(TextNews textNews){
	 this.sqlMapClientTemplate.update("TextNews.updateTextNewsStatus",textNews);
 }
 
 /**
  * 新增分发关系
  * @param pageNewsRef
  */
 public void insertPageNewsRef(PageNewsRef pageNewsRef){
	 this.sqlMapClientTemplate.insert("TextNews.insertPageNewsRef",pageNewsRef);
 }
 
}
