package com.foxconn.cms.communication.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.communication.pojo.Communication;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.pojo.SelectBean;

@Service("CommunicationDao")
public class CommunicationDao {

	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<com.foxconn.cms.communication.pojo.Communication> getCommunicationList(HashMap<String, String> map){
	 	return this.sqlMapClientTemplate.queryForList("Communication.getCommunicationList",map);
	 }
	 public void updateCommunication(Communication communication){
		 this.sqlMapClientTemplate.update("Communication.updateCommunication", communication);
	 }
	 public Communication getCommunicationDetail(Communication communication){
		 @SuppressWarnings("unchecked")
		List <Communication> CommunicationList = this.sqlMapClientTemplate.queryForList("Communication.getCommunicationDetail",communication);
		   if(CommunicationList!=null&&CommunicationList.size()>0){
			   return CommunicationList.get(0);
		   }else{
			return new Communication(); 
		   }
		 
	 }
//	/**communicationSearch
//	 * 获取重庆交通新闻列表记录总数 F3228777 2013-06-21
//	 */
//
//	/**
//	 * 根据type获取热点新闻列表（type week:一周 month：一月）
//	 * @param type
//	 * @param size
//	 * @return
//	 */
//
//	
//	/**
//	 * 獲取新聞詳情
//	 * @param textNews
//	 * @return
//	 */
   public Communication preViewcommunication(Communication communication){
	   @SuppressWarnings("unchecked")
	List <Communication> CommunicationList = this.sqlMapClientTemplate.queryForList("Communication.preViewcommunication",communication);
	   if(CommunicationList!=null&&CommunicationList.size()>0){
		   return CommunicationList.get(0);
	   }else{
		return new Communication(); 
	   }
   }
	   
	   public List<Communication> preViewcommunicationuser(Communication communication){
		   @SuppressWarnings("unchecked")
		List <Communication> CommunicationList = this.sqlMapClientTemplate.queryForList("Communication.preViewcommunicationuser",communication);
		   if(CommunicationList!=null&&CommunicationList.size()>0){
			   return CommunicationList;
		   }else{
			return CommunicationList; 
		   }
   }
	   public void deleteCommunication(Communication communication){
			 this.sqlMapClientTemplate.update("Communication.deleteCommunication",communication);
		 }
	   
	   public void deleteCommunicationUser(Communication communication){
			 this.sqlMapClientTemplate.update("Communication.deleteCommunicationUser",communication);
		 }
//   
	   
	   public void insertCommunication(Communication communication){
		   this.sqlMapClientTemplate.insert("Communication.insertCommunication",communication);
		   }
//   /**
//    * 瀏覽記錄記錄insertCommunication
//    * @param textNews
//    */
//   
//   public void insertCommunication(Communication communication){
//	   this.sqlMapClientTemplate.insert("Communication.insertCommunication",communication);
//	   }
//   
   }
