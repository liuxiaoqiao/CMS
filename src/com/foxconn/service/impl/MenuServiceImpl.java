/**
 * 
 */
package com.foxconn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.MenuDAO;
import com.foxconn.dao.UserDetailDAO;
import com.foxconn.pojo.Menu;
import com.foxconn.pojo.UserDetail;
import com.foxconn.service.MenuService;

/**
 * @author F3219058
 * 
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

	@Resource(name = "menuDAO")
	private MenuDAO menuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foxconn.service.MenuService#getMenu()
	 */
	@Override
	public List<Menu> getMenu(String roleID,String parentID) {
		// TODO Auto-generated method stub
		return menuDao.getMenu(roleID,parentID);
	}
	
	
	@Override
	public List<Menu> getTopMenu(String roleID,String parentID) {
		// TODO Auto-generated method stub
		return menuDao.getTopMenu(roleID,parentID);
	}
}
