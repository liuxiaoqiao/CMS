package com.foxconn.service;

import java.util.List;

import com.foxconn.pojo.SelectBean;


/**
 * @author F3228761
 * @date : Jul 27, 2013 10:34:44 AM
 */
public interface UtilService {

	 List<SelectBean> getContentSourceList();
	 List<SelectBean> getApproverList();

	
}
