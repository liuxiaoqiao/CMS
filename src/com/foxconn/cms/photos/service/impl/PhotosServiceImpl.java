package com.foxconn.cms.photos.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.cms.photos.pojo.PhotosNews;
import com.foxconn.cms.photos.pojo.PhotosInfo;
import com.foxconn.cms.photos.service.PhotosService;
import com.foxconn.cms.photos.dao.PhotosDao;

@Service("photosServiceImpl")
public class PhotosServiceImpl implements PhotosService {
	@Autowired
	@Resource(name = "photosDao")
	private PhotosDao photosDao;

	@Override
	public List<PhotosNews> getPhotosNewsList(PhotosNews photosNews) {
		// TODO Auto-generated method stub
		return photosDao.getPhotosNewsList(photosNews);
	}
	
	@Override
	public List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo){
		return photosDao.getPhotosInfoList(photosInfo);
	}

	@Override
	public void insertPhotosNews(PhotosNews photosNews) {
		// TODO Auto-generated method stub
		photosDao.insertPhotosNews(photosNews);
		
	}

	@Override
	public void insertPhotosInfo(PhotosInfo photosInfo) {
		// TODO Auto-generated method stub
		photosDao.insertPhotosInfo(photosInfo);
		
	}

	@Override
	public void updatePhotosNews(PhotosNews photosNews) {
		// TODO Auto-generated method stub
		photosDao.updatePhotosNews(photosNews);
		
	}

	@Override
	public void deletePhotosNews(PhotosNews photosNews) {
		// TODO Auto-generated method stub
		photosDao.deletePhotosNews(photosNews);
	}
	
	@Override
	public void updatePhotosNewsStatus(PhotosNews photosNews){
		photosDao.updatePhotosNewsStatus(photosNews);
	}
	
}
