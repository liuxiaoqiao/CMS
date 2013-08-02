package com.foxconn.cms.videonews.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foxconn.cms.magazinenews.dao.MagazineNewsDao;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.MagazineNewsService;
import com.foxconn.cms.videonews.dao.VideoNewsDao;
import com.foxconn.cms.videonews.pojo.VideoNews;
import com.foxconn.cms.videonews.service.VideoNewsService;
import com.foxconn.pojo.SelectBean;





/**
 * @author F3228761
 * @date : Jul 22, 2013 4:47:34 PM
 */
@Service("videoNewsServiceImpl")
public class VideoNewsServiceImpl implements VideoNewsService{

	@Autowired
	@Resource(name = "videoNewsDao")
	private VideoNewsDao videoNewsDao;
	
	@Override
	public List<VideoNews> getVideoNewsList(HashMap<String, String> map){
		return videoNewsDao.getVideoNewsList(map);
	}
	@Override
	public void deleteVideoNews(HashMap<String, String> map){
	    videoNewsDao.deleteVideoNews(map);
	}
	@Override
	public VideoNews getVideoNewsInfo(String videoNewsId){
		return videoNewsDao.getVideoNewsInfo(videoNewsId);
	}
	@Override
	public void saveVideoNews(VideoNews videoNews){
		videoNewsDao.saveVideoNews(videoNews);
	}
	@Override
	public void reviewVideoNews(HashMap<String, String> map){
		videoNewsDao.reviewVideoNews(map);
	}
	@Override
	public void sendBackVideoNews(HashMap<String, String> map){
		videoNewsDao.sendBackVideoNews(map);
	}
	/**
	 * 播放视频
	 */
	@Override
	public VideoNews getVideoNameByID(String newsID) {
		
		return videoNewsDao.getVideoNameByID(newsID);
	}
}
