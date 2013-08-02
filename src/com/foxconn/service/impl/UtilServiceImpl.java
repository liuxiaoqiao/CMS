package com.foxconn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foxconn.dao.UserDetailDAO;
import com.foxconn.dao.UtilDAO;
import com.foxconn.pojo.SelectBean;
import com.foxconn.pojo.UserDetail;
import com.foxconn.service.UserDetailService;
import com.foxconn.service.UtilService;
import com.foxconn.util.SystemContext;

/**
 * @author F3228761
 * @date : Jul 27, 2013 10:39:23 AM
 */
@Service("utilServiceImpl")
public class UtilServiceImpl implements UtilService {
	
	@Resource(name="utilDAO")
	private UtilDAO utilDAO;	

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@Override
	public  List<SelectBean> getContentSourceList(){
		//return sqlMapClientTemplate.queryForList("UtilSQL.getContentSourceList"); //modify by david on date 2013/07/30
		return utilDAO.getContentSourceList();
	}
	
	@Override
	public  List<SelectBean> getApproverList(){
		return utilDAO.getApproverList();
	}
	

}
