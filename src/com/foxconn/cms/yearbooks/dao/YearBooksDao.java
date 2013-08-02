package com.foxconn.cms.yearbooks.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.foxconn.cms.yearbooks.pojo.YearBooks;

/**
 * @author F3228761
 * @date : Jul 12, 2013 10:44:23 AM
 */
@Repository("yearBooksDao")
public class YearBooksDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<YearBooks> getYearBooksList(){
		return sqlMapClientTemplate.queryForList("YearBooksSQL.getYearBooksList");
		
	}
	public YearBooks getYearBooksInfo(String yearBooksID){
		return (YearBooks) sqlMapClientTemplate.queryForObject("YearBooksSQL.getYearBooksInfo",yearBooksID);
		
	}
	public void deleteYearBooks(HashMap<String, String> map){
		
			sqlMapClientTemplate.update("YearBooksSQL.deleteYearBooks",map);
	}
	public void publishYearBooks(HashMap<String, String> map){
		if(map.get("operType")=="edit"){
			sqlMapClientTemplate.update("YearBooksSQL.publishYearBooksByEdit",map);
		}
			else{
				sqlMapClientTemplate.insert("YearBooksSQL.publishYearBooksByAdd",map);
			}
		}
	
}
