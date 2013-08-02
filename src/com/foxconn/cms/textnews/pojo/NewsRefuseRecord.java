package com.foxconn.cms.textnews.pojo;

import java.util.List;

public class NewsRefuseRecord {
   private String refuseReCordID;
   private String newsID;
   private String refuseDesc;
   private String entryUser;
   private String entryDate;
   private int newsType;
   private List<NewsRefuseRecord> newsRefuseRecordList; 
public String getRefuseReCordID() {
	return refuseReCordID;
}
public void setRefuseReCordID(String refuseReCordID) {
	this.refuseReCordID = refuseReCordID;
}
public String getNewsID() {
	return newsID;
}
public void setNewsID(String newsID) {
	this.newsID = newsID;
}
public String getRefuseDesc() {
	return refuseDesc;
}
public void setRefuseDesc(String refuseDesc) {
	this.refuseDesc = refuseDesc;
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
public int getNewsType() {
	return newsType;
}
public void setNewsType(int newsType) {
	this.newsType = newsType;
}
public List<NewsRefuseRecord> getNewsRefuseRecordList() {
	return newsRefuseRecordList;
}
public void setNewsRefuseRecordList(List<NewsRefuseRecord> newsRefuseRecordList) {
	this.newsRefuseRecordList = newsRefuseRecordList;
}
   
   
}
