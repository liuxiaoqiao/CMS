package com.foxconn.cms.photos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.photos.pojo.PhotosNews;
import com.foxconn.cms.photos.pojo.PhotosInfo;

@Service("photosDao")
public class PhotosDao {
 @Resource(name="sqlMapClientTemplate")
 private SqlMapClientTemplate sqlMapClientTemplate;
 
 public List<PhotosNews> getPhotosNewsList(PhotosNews photosNews){
	 return this.sqlMapClientTemplate.queryForList("Photos.getPhotosNewsList",photosNews);
 }
 
 public List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo){
	 return this.sqlMapClientTemplate.queryForList("Photos.getPhotosInfoList",photosInfo);	 
 }
 
 public void insertPhotosNews(PhotosNews photosNews){
	 this.sqlMapClientTemplate.insert("Photos.insertPhotosNews",photosNews);
  }
 
 public void insertPhotosInfo(PhotosInfo photosInfo){
	 this.sqlMapClientTemplate.insert("Photos.insertPhotosInfo",photosInfo);
  }
 
 public void updatePhotosNews(PhotosNews photosNews){
	 this.sqlMapClientTemplate.insert("Photos.updatePhotosNews",photosNews);
  }
 
 public void deletePhotosNews(PhotosNews photosNews){
	 this.sqlMapClientTemplate.insert("Photos.deletePhotosNews",photosNews);
  }
 
 public void updatePhotosNewsStatus(PhotosNews photosNews){
	 this.sqlMapClientTemplate.update("Photos.updatePhotosNewsStatus",photosNews);
 }
 
}
