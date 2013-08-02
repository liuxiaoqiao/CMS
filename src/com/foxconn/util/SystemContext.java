package com.foxconn.util;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.foxconn.pojo.UserDetail;

public class SystemContext {

	// 用戶ID
	private static String userId;
	// 系統日期
	private static Date systemDate;
	// 人员ID
	// private static String personnelId;

	// added by by Cube @130724
	private static String userNO;

	public static String getUserNO() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userNO = userDetail.getUserNo();
		return userNO;
	}

	public static String getRoleID() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleID = userDetail.getRoleID();
		return roleID;
	}

	public static String getRoleName() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleName = userDetail.getRoleName();
		return roleName;
	}

	private static String roleID;
	private static String roleName;

	private static String roleDesc;

	public static String getRoleDesc() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleDesc = userDetail.getRoleDesc();
		return roleDesc;
	}

	public static String getUserId() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userId = userDetail.getUserId();
		return userId;
	}

	public static Collection<GrantedAuthority> getAuthorities() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userDetail.getAuthorities();
	}

	public static UserDetail getUserDetail() {
		return (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}

	public static void setUserId(String userId) {
		SystemContext.userId = userId;
	}

	public static Date getSystemDate() {
		systemDate = new Date();
		return systemDate;
	}

	public static void setSystemDate(Date systemDate) {
		SystemContext.systemDate = systemDate;
	}

/*	public static void setPersonnelId(String personnelId) {
		SystemContext.personnelId = personnelId;
	}*/

}
