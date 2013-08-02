package com.foxconn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foxconn.dao.UserDetailDAO;
import com.foxconn.pojo.UserDetail;
import com.foxconn.service.UserDetailService;
//import com.foxconn.util.SystemContext;


@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailService {
	
	@Resource(name="userDetailDAO")
	private UserDetailDAO userDao;	

	public List<UserDetail> getUserByCode(String code){		
		return userDao.queryUserByCode(code);
	}

	public List<UserDetail> login(String username, String password) {	

		String md5Password = new Md5PasswordEncoder().encodePassword(password, username);
		UserDetail user = new UserDetail();
		user.setUserNo(username);
		user.setPassword(md5Password);
		return userDao.login(user);
	}

	@Override
	public void AddUser(UserDetail u) {
		userDao.AddUser(u);	
	}

	@Override
	public void UpdateUser(UserDetail u) {
		
		userDao.UpdateUser(u);
	}

	@Override
	public void DeleteUser(String userID) {
	
		userDao.DeleteUser(userID);
	}

	@Override
	public List<UserDetail> SelectUser(Map<String,String> map) {
		return userDao.SelectUser(map);
	}

/*	public void updateUser(UserDetail userDetail) {
		if(userDetail.getPassword().length() != 32) {
			String password=userDetail.getPassword();
			String username=userDetail.getUsername();
			String encodedPassword = new Md5PasswordEncoder().encodePassword(password, username);
			userDetail.setPassword(encodedPassword);
			SystemContext.getUserDetail().setPassword(encodedPassword);
		}
		//userDao.modifyUser(userDetail);			
	}
	
	//added by Cube @130724
	public String GenPwd(String username,String password){
		String encodedPassword = new Md5PasswordEncoder().encodePassword(password, username);
		return encodedPassword;
	}*/

}
