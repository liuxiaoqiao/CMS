package com.foxconn.cms.basicdata.pojo;

import java.util.List;

public class BasicInfo {
	 private int  infoID;
	 private String infoName;
	 private String infoDesc;
	 private int typeID;
	 private String sTypeID;
	 private String typeName;
	 private String entryUser;
	 private String entryDate;
	 private String modifyUser;
	 private String modifyDate;
	 
     private List<BasicInfo> basicInfoList;

	public int getInfoID() {
		return infoID;
	}

	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getInfoDesc() {
		return infoDesc;
	}

	public void setInfoDesc(String infoDesc) {
		this.infoDesc = infoDesc;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
		
	public String getsTypeID() {
		return sTypeID;
	}

	public void setsTypeID(String sTypeID) {
		this.sTypeID = sTypeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	

	public List<BasicInfo> getBasicInfoList() {
		return basicInfoList;
	}

	public void setBasicInfoList(List<BasicInfo> basicInfoList) {
		this.basicInfoList = basicInfoList;
	}
     
     

}
