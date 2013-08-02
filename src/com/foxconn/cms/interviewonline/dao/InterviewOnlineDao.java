package com.foxconn.cms.interviewonline.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.foxconn.cms.interviewonline.pojo.InterviewOnline;
import com.foxconn.cms.yearbooks.pojo.YearBooks;

/**
 * @author F3228761
 * @date : Jul 12, 2013 10:44:23 AM
 */
@Repository("interviewOnlineDao")
public class InterviewOnlineDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<InterviewOnline> getInterviewOnlineList(String interviewTitle){
		return sqlMapClientTemplate.queryForList("InterviewOnlineSQL.getInterviewOnlineList",interviewTitle);
		
	}
	public InterviewOnline getInterviewOnlineInfo(String interviewID){
		return (InterviewOnline) sqlMapClientTemplate.queryForObject("InterviewOnlineSQL.getInterviewOnlineInfo",interviewID);
		
	}
	public void deleteInterviewOnline(HashMap<String, String> map){
		
			sqlMapClientTemplate.update("InterviewOnlineSQL.deleteInterviewOnline",map);
	}
	public void publishYearBooks(HashMap<String, String> map){
		if(map.get("operType")=="edit"){
			sqlMapClientTemplate.update("YearBooksSQL.publishYearBooksByEdit",map);
		}
			else{
				sqlMapClientTemplate.insert("YearBooksSQL.publishYearBooksByAdd",map);
			}
		}
	public void reviewInterviewOnline(HashMap<String, String> map){
		sqlMapClientTemplate.update("InterviewOnlineSQL.reviewInterviewOnline",map);
	}
	public void sendBackInterviewOnline(HashMap<String, String> map){
		sqlMapClientTemplate.update("InterviewOnlineSQL.updateBackStatus",map);
		sqlMapClientTemplate.insert("InterviewOnlineSQL.recordBackReason",map);
	}
	public void saveInterviewOnline(InterviewOnline interviewOnline){
		if(interviewOnline.getOperFlag()=="edit"){
			sqlMapClientTemplate.update("InterviewOnlineSQL.saveInterviewOnlineByEdit",interviewOnline);
		}
		else{
			sqlMapClientTemplate.insert("InterviewOnlineSQL.saveInterviewOnlineByAdd",interviewOnline);
		}
	}
	
}
