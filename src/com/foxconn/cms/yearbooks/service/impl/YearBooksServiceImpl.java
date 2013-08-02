package com.foxconn.cms.yearbooks.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
@Service("yearBooksServiceImpl")
public class YearBooksServiceImpl implements YearBooksService{

	@Autowired
	@Resource(name = "yearBooksDao")
	private YearBooksDao yearBooksDao;
	
	@Override
	public List<YearBooks> getYearBooksList(){
		return yearBooksDao.getYearBooksList();
	}
	@Override
	public YearBooks getYearBooksInfo(String yearBooksID){
		return  yearBooksDao.getYearBooksInfo(yearBooksID);
	}
	@Override
	public void deleteYearBooks(HashMap<String, String> map){
		yearBooksDao.deleteYearBooks(map);
	}
	@Override
	public void publishYearBooks(HashMap<String, String> map){
		yearBooksDao.publishYearBooks(map);
	}
}
