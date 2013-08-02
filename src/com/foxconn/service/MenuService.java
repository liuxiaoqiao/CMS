/**
 * 
 */
package com.foxconn.service;

import java.util.List;

import com.foxconn.pojo.Menu;

/**
 * @author F3219058
 * 
 */
public interface MenuService {
	public List<Menu> getMenu(String roleID,String parentID);
	
	public List<Menu> getTopMenu(String roleID,String parentID);
}
