package com.foxconn.cms.videonews.service;

import java.util.HashMap;
import java.util.List;

import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.videonews.pojo.VideoNews;
import com.foxconn.pojo.SelectBean;



/**
 * @author F3228761
 * @date : Jul 22, 2013 4:52:24 PM
 */
public interface VideoNewsService {

	public List<VideoNews> getVideoNewsList(HashMap<String, String> map);

	public void deleteVideoNews(HashMap<String, String> map);

	public VideoNews getVideoNewsInfo(String videoNewsId);

	public void saveVideoNews(VideoNews videoNews);

	public void reviewVideoNews(HashMap<String, String> map);

	public void sendBackVideoNews(HashMap<String, String> map);

	public VideoNews getVideoNameByID(String newsID);

	
	
}
