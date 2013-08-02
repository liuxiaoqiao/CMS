package com.foxconn.service;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.UserDetail;

/**
 * @Descrition 
 * 
 * @author 
 * 
 * 
 */
public interface UserDetailService {
	
	List<UserDetail> login(String username,String password);
	
	public List<UserDetail> getUserByCode(String code);
	
	public void AddUser(UserDetail u);
	public void UpdateUser(UserDetail u);
	public void DeleteUser(String userID);
	public List<UserDetail> SelectUser(Map<String,String> map);
	
}
