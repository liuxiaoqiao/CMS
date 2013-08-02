package com.foxconn.cms.basicdata.pojo;

import java.util.List;


public class BasicType {
	 private int  typeID;
	 private String typeName;
	 private String typeDesc;
	 private String entryUser;
	 private String entryDate;
	 private String modifyUser;
	 private String modifyDate;
	 
     private List<BasicType> basicTypeList;

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	

	public List<BasicType> getBasicTypeList() {
		return basicTypeList;
	}

	public void setBasicTypeList(List<BasicType> basicTypeList) {
		this.basicTypeList = basicTypeList;
	}
    
     
}
