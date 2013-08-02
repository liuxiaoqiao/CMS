package com.foxconn.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.AuthorityResource;

@Repository("authorityResourceDAO")
public class AuthorityResourceDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings({ "unchecked" })
	public List<AuthorityResource> getAVResourceByType(String resourceType) {
		return sqlMapClientTemplate.queryForList(
				"AuthorityResource.getAVResourceByType", resourceType);
	}

	public boolean checkAuthority(Map<String, String> map) {
		if (sqlMapClientTemplate.queryForList(
				"AuthorityResource.checkAuthority", map).size() > 0)
			return true;
		else
			return false;
	}
}
