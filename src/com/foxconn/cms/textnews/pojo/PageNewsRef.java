package com.foxconn.cms.textnews.pojo;
import java.util.List;

public class PageNewsRef {
  private String pageID;
  private String newsID;
  private String createUser;
  private String createDate;
  private String modifyUser;
  private String modifyDate;
  private String isFirst;
  
  private  List<PageNewsRef> pageNewsRefList;

public String getPageID() {
	return pageID;
}

public void setPageID(String pageID) {
	this.pageID = pageID;
}

public String getNewsID() {
	return newsID;
}

public void setNewsID(String newsID) {
	this.newsID = newsID;
}

public String getCreateUser() {
	return createUser;
}

public void setCreateUser(String createUser) {
	this.createUser = createUser;
}

public String getCreateDate() {
	return createDate;
}

public void setCreateDate(String createDate) {
	this.createDate = createDate;
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

public String getIsFirst() {
	return isFirst;
}

public void setIsFirst(String isFirst) {
	this.isFirst = isFirst;
}

public List<PageNewsRef> getPageNewsRefList() {
	return pageNewsRefList;
}

public void setPageNewsRefList(List<PageNewsRef> pageNewsRefList) {
	this.pageNewsRefList = pageNewsRefList;
}
  
  
}
