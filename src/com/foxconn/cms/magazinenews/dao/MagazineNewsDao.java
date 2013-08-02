package com.foxconn.cms.magazinenews.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.pojo.SelectBean;


/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:03 PM
 */
@Repository("magazineNewsDao")
public class MagazineNewsDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<MagazineNews> getMagazineNewsList(HashMap<String, String> map){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineNewsList",map);
		
	}
	@SuppressWarnings("unchecked")
	public List<MagazineNews> getMagazineCatalogList(String magazineID){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineCatalogList",magazineID);
		
	}
	@SuppressWarnings("unchecked")
	public List<MagazineNews> getMagazineContentList(HashMap<String, String> map){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineContentList",map);
		
	}
	 public MagazineNews getMagazineInfo(String magazineID){
		return (MagazineNews) sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getMagazineInfo", magazineID);
	}
	/* public List<SelectBean> getMagazineNumList(){
		 return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineNumList");
	 }*/
	public MagazineNews getContent(String contentID){
		return (MagazineNews) sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getContent", contentID);
	}
	
	public void deleteMagazine(HashMap<String, String> map){
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteMagazine",map);
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteCatalogByMagazineID",map);
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteContentByMagazineID",map);
	}
	public void reviewMagazine(HashMap<String, String> map){
		sqlMapClientTemplate.update("MagazineNewsSQL.reviewMagazine",map);
	}
	
	public MagazineNews getCatalogInfo(String catalogID){
		return (MagazineNews) sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getCatalogInfo", catalogID);
	}
	public void deleteCatalog(HashMap<String, String> map){
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteCatalogByCatalogID",map);
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteContentByCatalogID",map);
	}
	
	public void saveAsDraft(HashMap<String, String> map){
		if(map.get("operType")=="edit"){
			sqlMapClientTemplate.update("MagazineNewsSQL.saveAsDraftByEdit",map);
		}
			else{
				sqlMapClientTemplate.insert("MagazineNewsSQL.saveAsDraftByAdd",map);
			}
		}
	public void distributeMagazineNews(HashMap<String, String> map){
		sqlMapClientTemplate.update("MagazineNewsSQL.distributeMagazineNews",map);
	}
	public void saveCatalog(HashMap<String, String> map){
		if(map.get("catalogID")==""){
			sqlMapClientTemplate.insert("MagazineNewsSQL.addCatalog",map);
			
		}
			else{
				sqlMapClientTemplate.update("MagazineNewsSQL.editCatalog",map);
			}
		}
	public MagazineNews getContentInfo(String contentID){
		return (MagazineNews) sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getContentInfo", contentID);
	}
	/*public MagazineNews getContentInfoByPreview(String contentID){
		return (MagazineNews) sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getContentInfoByPreview", contentID);
	}*/
	public void deleteContent(HashMap<String, String> map){
		sqlMapClientTemplate.update("MagazineNewsSQL.deleteContent",map);
	}
	public void saveContent(HashMap<String, String> map){
		if(map.get("contentID")==""){
			sqlMapClientTemplate.insert("MagazineNewsSQL.addContent",map);
			
		}
			else{
				sqlMapClientTemplate.update("MagazineNewsSQL.editContent",map);
			}
		}
	@SuppressWarnings("unchecked")
	public List<SelectBean> getContentSourceList(){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getContentSourceList");
	}
	@SuppressWarnings("unchecked")
	public List<MagazineNews> getMagazineDetailByID(String magazineID){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineDetailByID", magazineID);
		
	}
	@SuppressWarnings("unchecked")
	public List<SelectBean> getMagazineNumList(){
		return sqlMapClientTemplate.queryForList("MagazineNewsSQL.getMagazineNumList");
	}

}
