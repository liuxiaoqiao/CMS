package com.foxconn.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foxconn.dao.AuthorityResourceDAO;
import com.foxconn.pojo.AuthorityResource;
import com.foxconn.pojo.UserDetail;
import com.foxconn.security.SecurityManager;
import com.foxconn.service.UserDetailService;

@Service("securityManager")
public class SecurityManagerService implements UserDetailsService,
		SecurityManager {

	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;	
	
	@Resource(name = "authorityResourceDAO")
	private AuthorityResourceDAO resourceDAO;	

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		List<UserDetail> users = userService.getUserByCode(username);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("用戶 " + username + "没有权限");
		}
		return users.get(0);
	}

	public Map<String, String> loadUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		List<AuthorityResource> urlResources = resourceDAO.getAVResourceByType("URL");
		for (AuthorityResource resource : urlResources) {
			urlAuthorities.put(resource.getResourceValue(),
					resource.getRoleAuthorities());
		}
		return urlAuthorities;
	}

	public Map<String, String> loadMethodAuthorities() {
		Map<String, String> methodAuthorities = new HashMap<String, String>();
		List<AuthorityResource> methodResources = resourceDAO.getAVResourceByType("METHOD");
		for (AuthorityResource resource : methodResources) {
			methodAuthorities.put(resource.getResourceValue(),
					resource.getRoleAuthorities());
		}

		return methodAuthorities;

	}

	//added by Cube @130725
	public boolean checkAuthority(String roleID,String url) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleID", roleID);
		map.put("url", url);
		return resourceDAO.checkAuthority(map);
	}
}
