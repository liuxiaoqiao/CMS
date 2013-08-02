package com.foxconn.cms.videonews.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.foxconn.cms.interviewonline.pojo.InterviewOnline;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.videonews.pojo.VideoNews;
import com.foxconn.pojo.SelectBean;


/**
 * @author F3228761
 * @date : Jul 22, 2013 4:47:18 PM
 */
@Repository("videoNewsDao")
public class VideoNewsDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<VideoNews> getVideoNewsList(HashMap<String, String> map){
		return sqlMapClientTemplate.queryForList("VideoNewsSQL.getVideoNewsList",map);
		
	}
	
	public void deleteVideoNews(HashMap<String, String> map){
		sqlMapClientTemplate.update("VideoNewsSQL.deleteVideoNews",map);
	}
	public VideoNews getVideoNewsInfo(String videoNewsId){
		return (VideoNews) sqlMapClientTemplate.queryForObject("VideoNewsSQL.getVideoNewsInfo",videoNewsId);
	}
	public void saveVideoNews(VideoNews videoNews){
		if(videoNews.getOperFlag()=="edit"){
			sqlMapClientTemplate.update("VideoNewsSQL.saveVideoNewsByEdit",videoNews);
		}
		else{
			sqlMapClientTemplate.insert("VideoNewsSQL.saveVideoNewsByAdd",videoNews);
		}
	}
	public void reviewVideoNews(HashMap<String, String> map){
		sqlMapClientTemplate.update("VideoNewsSQL.reviewVideoNews",map);
	}
	public void sendBackVideoNews(HashMap<String, String> map){
		sqlMapClientTemplate.update("VideoNewsSQL.updateBackStatus",map);
		sqlMapClientTemplate.insert("VideoNewsSQL.recordBackReason",map);
	}
	public VideoNews getVideoNameByID(String newsID) {
		return (VideoNews) this.sqlMapClientTemplate.queryForObject("VideoNewsSQL.getVideoNameByID", newsID);
	}
}
