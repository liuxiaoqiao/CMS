package com.foxconn.cms.reports.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foxconn.cms.reports.dao.ReportsDao;
import com.foxconn.cms.reports.service.ReportsService;





/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:41 PM
 */
@Service("ReportsImpl")
public class ReportsServiceImpl implements ReportsService{

	@Autowired
	@Resource(name = "ReportsDao")
	private ReportsDao ReportsDao;
	
	@Override
	public List<java.util.HashMap> getAnalysisUsingManuscript(HashMap<String, String> map) throws Exception{
		try{
		return ReportsDao.getAnalysisUsingManuscript(map);
		}catch(Exception e){
			throw e;
		}
	}
	public List<java.util.HashMap> getAnalysisaccess(HashMap<String, String> map) throws Exception{
		try{
		return ReportsDao.getAnalysisaccess(map);
		}catch(Exception e){
			throw e;
		}
	}
	
}
