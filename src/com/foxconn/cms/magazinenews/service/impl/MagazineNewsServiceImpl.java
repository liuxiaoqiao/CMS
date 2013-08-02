package com.foxconn.cms.magazinenews.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foxconn.cms.magazinenews.dao.MagazineNewsDao;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.MagazineNewsService;
import com.foxconn.pojo.SelectBean;





/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:41 PM
 */
@Service("magazineNewsImpl")
public class MagazineNewsServiceImpl implements MagazineNewsService{

	@Autowired
	@Resource(name = "magazineNewsDao")
	private MagazineNewsDao magazineNewsDao;
	
	@Override
	public List<MagazineNews> getMagazineNewsList(HashMap<String, String> map){
		return magazineNewsDao.getMagazineNewsList(map);
	}
	@Override
	public List<MagazineNews> getMagazineCatalogList(String magazineID){
		return magazineNewsDao.getMagazineCatalogList(magazineID);
	}
	@Override
	public List<MagazineNews> getMagazineContentList(HashMap<String, String> map){
		return magazineNewsDao.getMagazineContentList(map);
	}
	@Override
	 public MagazineNews getMagazineInfo(String magazineID){
		 return magazineNewsDao.getMagazineInfo(magazineID);
	 }
	@Override
	public void deleteMagazine(HashMap<String, String> map){
		magazineNewsDao.deleteMagazine(map);
	}
	@Override
	public void reviewMagazine(HashMap<String, String> map){
		magazineNewsDao.reviewMagazine(map);
	}
	@Override
	public MagazineNews getCatalogInfo(String catalogID){
		return magazineNewsDao.getCatalogInfo(catalogID);
	}
	@Override
	public void deleteCatalog(HashMap<String, String> map){
		magazineNewsDao.deleteCatalog(map);
	}
	@Override
	public void saveAsDraft(HashMap<String, String> map){
		magazineNewsDao.saveAsDraft(map);
	}
	@Override
	public void distributeMagazineNews(HashMap<String, String> map){
		magazineNewsDao.distributeMagazineNews(map);
	}
	@Override
	public void saveCatalog(HashMap<String, String> map){
		magazineNewsDao.saveCatalog(map);
	}
	@Override
	public MagazineNews getContentInfo(String contentID){
		return magazineNewsDao.getContentInfo(contentID);
	}
	@Override
	public void deleteContent(HashMap<String, String> map){
		magazineNewsDao.deleteContent(map);
	}
	@Override
	public void saveContent(HashMap<String, String> map){
		magazineNewsDao.saveContent(map);
	}
	@Override
	public List<SelectBean> getContentSourceList(){
	return magazineNewsDao.getContentSourceList();
	}
	@Override
	public List<MagazineNews> getMagazineDetailByID(String magazineID){
		return magazineNewsDao.getMagazineDetailByID(magazineID);
	}
	@Override
	public List<SelectBean> getMagazineNumList(){
		return magazineNewsDao.getMagazineNumList();
	}
	/*@Override
	public MagazineNews getContentInfoByPreview(String contentID){
		return magazineNewsDao.getContentInfoByPreview(contentID);
	}*/
}
