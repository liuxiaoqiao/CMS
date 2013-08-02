package com.foxconn.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 用戶角色類
 * 
 * @Title:RoleResource.java
 * @Descrition:TODO
 * @Author:F3221422
 * @Date:2011/4/13
 * @Version:V1.0
 */

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791472274105987964L;

	
	private String urId;


	private String 	userId;
	
	
	private String roleId;
	
	
	private Date createDate;
	

	private String createUserNo;
	
	
	private Date updateDate;
	
	
	private String 	updateUserNo;

	
	private String personName;
	
	public UserRole(){
		
	}
	
	public UserRole(String userId, String personName){
		this.userId = userId;
		this.personName = personName;
	}
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getUrId() {
		return urId;
	}

	public void setUrId(String urId) {
		this.urId = urId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}
	
	
}
