package com.foxconn.cms.specialcolumn.pojo;

import java.util.List;

public class SpecialColumn {
  private String columnID;
  private String columnTitle;
  private String columnPhotosName;
  private String columnPhotosUrl;
  private String columnUrl;
  private String isShow;
  private String entryUser;
  private String entryDate;
  private String modifyDate;
  private String modifyUser;
  private String isDel;
  
  private List<SpecialColumn> specialColumn;

public String getColumnID() {
	return columnID;
}

public void setColumnID(String columnID) {
	this.columnID = columnID;
}

public String getColumnTitle() {
	return columnTitle;
}

public void setColumnTitle(String columnTitle) {
	this.columnTitle = columnTitle;
}

public String getColumnPhotosName() {
	return columnPhotosName;
}

public void setColumnPhotosName(String columnPhotosName) {
	this.columnPhotosName = columnPhotosName;
}

public String getColumnPhotosUrl() {
	return columnPhotosUrl;
}

public void setColumnPhotosUrl(String columnPhotosUrl) {
	this.columnPhotosUrl = columnPhotosUrl;
}

public String getColumnUrl() {
	return columnUrl;
}

public void setColumnUrl(String columnUrl) {
	this.columnUrl = columnUrl;
}

public String getIsShow() {
	return isShow;
}

public void setIsShow(String isShow) {
	this.isShow = isShow;
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

public String getModifyDate() {
	return modifyDate;
}

public void setModifyDate(String modifyDate) {
	this.modifyDate = modifyDate;
}

public String getModifyUser() {
	return modifyUser;
}

public void setModifyUser(String modifyUser) {
	this.modifyUser = modifyUser;
}

public String getIsDel() {
	return isDel;
}

public void setIsDel(String isDel) {
	this.isDel = isDel;
}

public List<SpecialColumn> getSpecialColumn() {
	return specialColumn;
}

public void setSpecialColumn(List<SpecialColumn> specialColumn) {
	this.specialColumn = specialColumn;
}
  
  

}
