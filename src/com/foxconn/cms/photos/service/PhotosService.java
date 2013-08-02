package com.foxconn.cms.photos.service;

import java.util.List;

import com.foxconn.cms.photos.pojo.PhotosNews;
import com.foxconn.cms.photos.pojo.PhotosInfo;

public interface PhotosService {
	List<PhotosNews> getPhotosNewsList(PhotosNews photosNews);
	List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo);
	void insertPhotosNews(PhotosNews photosNews);
	void insertPhotosInfo(PhotosInfo photosInfo);
	void updatePhotosNews(PhotosNews photosNews);
	void deletePhotosNews(PhotosNews photosNews);
	void updatePhotosNewsStatus(PhotosNews photosNews);
}
