package com.foxconn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.SelectBean;
import com.foxconn.pojo.UserDetail;


@Repository("utilDAO")
public class UtilDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<SelectBean> getContentSourceList(){
		return sqlMapClientTemplate.queryForList("UtilSQL.getContentSourceList");
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectBean> getApproverList(){
		return sqlMapClientTemplate.queryForList("UtilSQL.getApproverList");
	}
	
}
