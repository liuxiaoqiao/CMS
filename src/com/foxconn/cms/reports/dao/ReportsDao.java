package com.foxconn.cms.reports.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.foxconn.cms.magazinenews.pojo.MagazineNews;


/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:03 PM
 */
@Repository("ReportsDao")
public class ReportsDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<java.util.HashMap> getAnalysisUsingManuscript(HashMap<String, String> map) throws Exception{
		try{
			List list = sqlMapClientTemplate.queryForList("ReportsSQL.getAnalysisUsingManuscript",map);
			return  list;
		}catch(Exception e){
			throw e;
		}
	}	
	public List<java.util.HashMap> getAnalysisaccess(HashMap<String, String> map) throws Exception{
		try{
			List list = sqlMapClientTemplate.queryForList("ReportsSQL.getAnalysisaccess",map);
			return  list;
		}catch(Exception e){
			throw e;
		}
	}	
}
