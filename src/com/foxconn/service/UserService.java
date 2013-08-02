package com.foxconn.service;

import com.foxconn.pojo.UserDetail;



public interface UserService {

	public void addUser(UserDetail user);
	
	public UserDetail findUserByPersonId(String personnelId);
	
	public UserDetail findByPersonnel(String personnelId);
	
	public void updateUser(UserDetail userInfo);
	
	public void delUser(UserDetail u);

	public UserDetail getUserByUserId(String userId);
}
