package com.foxconn.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.UserDetail;

/**
 * @author F3228761
 * @date : Jul 27, 2013 10:34:35 AM
 */
@Repository("userDetailDAO")
public class UserDetailDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> queryUserByCode(String userNo){
		return sqlMapClientTemplate.queryForList("Users.queryUserByCode",
				userNo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> login(UserDetail user){
		return sqlMapClientTemplate.queryForList("Users.Login",
				user);
	}
	
	public void AddUser(UserDetail user){
		sqlMapClientTemplate.insert("Users.AddUser",user);
	}
	
	public void UpdateUser(UserDetail user){
		sqlMapClientTemplate.update("Users.UpdateUser",user);
	}
	
	public void DeleteUser(String userID){
		sqlMapClientTemplate.update("Users.DeleteUser",userID);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> SelectUser(Map<String,String> map){
		return sqlMapClientTemplate.queryForList("Users.SelectUser",map);
	}
}
