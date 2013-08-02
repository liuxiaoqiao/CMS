package com.foxconn.cms.magazinenews.service;

import java.util.HashMap;
import java.util.List;

import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.pojo.SelectBean;



/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:58 PM
 */
public interface MagazineNewsService {

	public List<MagazineNews> getMagazineNewsList(HashMap<String, String> map);

	public List<MagazineNews> getMagazineContentList(HashMap<String, String> map);

	public List<MagazineNews> getMagazineCatalogList(String magazineID);

	public MagazineNews getMagazineInfo(String magazineID);

	public void deleteMagazine(HashMap<String, String> map);

	public void reviewMagazine(HashMap<String, String> map);

	public MagazineNews getCatalogInfo(String catalogID);

	public void deleteCatalog(HashMap<String, String> map);

	public void saveAsDraft(HashMap<String, String> map);

	public void distributeMagazineNews(HashMap<String, String> map);

	public void saveCatalog(HashMap<String, String> map);

	public void deleteContent(HashMap<String, String> map);

	public MagazineNews getContentInfo(String contentID);

	public void saveContent(HashMap<String, String> map);

	public List<SelectBean> getContentSourceList();

	public List<MagazineNews> getMagazineDetailByID(String magazineID);

	public List<SelectBean> getMagazineNumList();

	/*public MagazineNews getContentInfoByPreview(String contentID);*/

	

	/*public List<SelectBean> getMagazineNumList();*/



	
	
}
