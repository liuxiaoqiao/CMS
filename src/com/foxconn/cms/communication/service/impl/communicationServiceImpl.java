package com.foxconn.cms.communication.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.cms.communication.dao.CommunicationDao;
import com.foxconn.cms.communication.pojo.Communication;
//import com.foxconn.pojo.Communication.UserReadRecord;
import com.foxconn.cms.communication.service.CommunicationService;
import com.foxconn.cms.textnews.pojo.TextNews;
import com.foxconn.pojo.SelectBean;

@Service("communicationServiceImpl")
public  class communicationServiceImpl implements CommunicationService {

	@Autowired
	@Resource(name = "CommunicationDao")
	private CommunicationDao CommunicationDao;
	
	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<com.foxconn.cms.communication.pojo.Communication> getCommunicationList(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return CommunicationDao.getCommunicationList(map);
	}

	@Override
	public void updateCommunication(Communication communication) {
		CommunicationDao.updateCommunication(communication);
	}
	public Communication getCommunicationDetail(Communication communication) {
	return	CommunicationDao.getCommunicationDetail(communication);
	}
	

	/**
	 * 获取重庆交通新闻列表记录总数 F3228777 2013-06-21
	 */
//	@Override
//	public int getCommunicationListCount() {
//
//		return CommunicationDao.getCommunicationListCount();
//		
//	}
//	
	@Override
    public Communication preViewcommunication(Communication communication){
		return CommunicationDao.preViewcommunication(communication);
		
	}
	
	public List<Communication> preViewcommunicationuser(Communication communication){
		return CommunicationDao.preViewcommunicationuser(communication);
		
	}

	@Override
	public void deleteCommunication(Communication communication) {
		CommunicationDao.deleteCommunication(communication);
	}
	public void deleteCommunicationUser(Communication communication) {
		CommunicationDao.deleteCommunicationUser(communication);
	}
	
	public void insertCommunication(Communication communication) {
		CommunicationDao.insertCommunication(communication);
	}
	
	
}
//
   
//	/**
//	 * 获取本周或者本月热点新闻列表
//	 * 按浏览人数倒序取前10条
//	 */
//	
//	
//	
//
//
//	/**
//	 * 獲取新聞詳情
//	 */
//	
//    
//	
//	/**
//	 * 瀏覽記錄記錄
//	 */
////
//   public void updatecommunication(Communication communication){
//		
//		CommunicationDao.insertReadRecord(communication);
//	}
//    
////	
//    
//
//
//
//    


