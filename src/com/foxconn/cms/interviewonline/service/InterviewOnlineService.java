package com.foxconn.cms.interviewonline.service;

import java.util.HashMap;
import java.util.List;

import com.foxconn.cms.interviewonline.pojo.InterviewOnline;
import com.foxconn.cms.magazinenews.pojo.MagazineNews;
import com.foxconn.cms.yearbooks.pojo.YearBooks;
import com.foxconn.pojo.SelectBean;



/**
 * @author F3228761
 * @date : Jul 12, 2013 10:45:52 AM
 */
public interface InterviewOnlineService {

	public List<InterviewOnline> getInterviewOnlineList(String interviewTitle);

	public void deleteInterviewOnline(HashMap<String, String> map);

	public InterviewOnline getInterviewOnlineInfo(String interviewID);

	public void reviewInterviewOnline(HashMap<String, String> map);

	public void sendBackInterviewOnline(HashMap<String, String> map);

	public void saveInterviewOnline(InterviewOnline interviewOnline);


	/*public List<YearBooks> getYearBooksList();

	public void deleteYearBooks(HashMap<String, String> map);

	public YearBooks getYearBooksInfo(String yearBooksID);

	public void publishYearBooks(HashMap<String, String> map);*/
	
}
