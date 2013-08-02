package com.foxconn.cms.reports.service;

import java.util.HashMap;
import java.util.List;

import com.foxconn.cms.magazinenews.pojo.MagazineNews;



/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:58 PM
 */
public interface ReportsService {

	public List<java.util.HashMap> getAnalysisUsingManuscript(HashMap<String, String> map) throws Exception;
	public List<java.util.HashMap> getAnalysisaccess(HashMap<String, String> map) throws Exception;
	
}
