package com.foxconn.cms.interviewonline.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foxconn.cms.interviewonline.dao.InterviewOnlineDao;
import com.foxconn.cms.interviewonline.pojo.InterviewOnline;
import com.foxconn.cms.interviewonline.service.InterviewOnlineService;
import com.foxconn.cms.magazinenews.dao.MagazineNewsDao;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.magazinenews.service.MagazineNewsService;
import com.foxconn.cms.yearbooks.dao.YearBooksDao;
import com.foxconn.cms.yearbooks.pojo.YearBooks;
import com.foxconn.cms.yearbooks.service.YearBooksService;
import com.foxconn.pojo.SelectBean;





/**
 * @author F3228761
 * @date : Jul 12, 2013 10:45:52 AM
 */
@Service("interviewOnlineServiceImpl")
public class InterviewOnlineServiceImpl implements InterviewOnlineService{

	@Autowired
	@Resource(name = "interviewOnlineDao")
	private InterviewOnlineDao interviewOnlineDao;
	
	@Override
	public List<InterviewOnline> getInterviewOnlineList(String interviewTitle){
		return interviewOnlineDao.getInterviewOnlineList(interviewTitle);
	}
	@Override
	public void deleteInterviewOnline(HashMap<String, String> map){
		interviewOnlineDao.deleteInterviewOnline(map);
	}
	@Override
	public InterviewOnline getInterviewOnlineInfo(String interviewID){
		return interviewOnlineDao.getInterviewOnlineInfo(interviewID);
	}
	@Override
	public void reviewInterviewOnline(HashMap<String, String> map){
		interviewOnlineDao.reviewInterviewOnline(map);
	}
	@Override
	public void sendBackInterviewOnline(HashMap<String, String> map){
		interviewOnlineDao.sendBackInterviewOnline(map);
	}
	@Override
	public void saveInterviewOnline(InterviewOnline interviewOnline){
		interviewOnlineDao.saveInterviewOnline(interviewOnline);
	}
	/*@Override
	public List<YearBooks> getYearBooksList(){
		return yearBooksDao.getYearBooksList();
	}
	
	@Override
	public void deleteYearBooks(HashMap<String, String> map){
		yearBooksDao.deleteYearBooks(map);
	}
	@Override
	public void publishYearBooks(HashMap<String, String> map){
		yearBooksDao.publishYearBooks(map);
	}*/
}
