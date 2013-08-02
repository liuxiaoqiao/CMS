/**
 * 
 */
package com.foxconn.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.Menu;

/**
 * @author F3219058
 *
 */
@Repository("menuDAO")
public class MenuDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenu(String roleID,String parentID){
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleID", roleID);
		map.put("parentID",parentID);
		return sqlMapClientTemplate.queryForList("Menu.getMenu",map);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Menu> getTopMenu(String roleID,String parentID){
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleID", roleID);
		map.put("parentID",parentID);
		return sqlMapClientTemplate.queryForList("Menu.getTopMenu",map);
	}
}
