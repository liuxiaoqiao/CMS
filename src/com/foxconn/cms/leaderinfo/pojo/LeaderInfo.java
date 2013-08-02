package com.foxconn.cms.leaderinfo.pojo;

import java.util.List;

public class LeaderInfo {
 private String leaderID;
 private String leaderName;
 private String leaderPost;
 private int leaderOrder;
 private String leaderPhotosUrl;
 private String leaderPhotosName;
 private String approvaler;
 private String leaderResume;
 private String entryUser;
 private String modifyUser;
 private int statusID;
 private String statusName;
 
 private List<LeaderInfo> leaderInfo;

public String getLeaderID() {
	return leaderID;
}

public void setLeaderID(String leaderID) {
	this.leaderID = leaderID;
}

public String getLeaderName() {
	return leaderName;
}

public void setLeaderName(String leaderName) {
	this.leaderName = leaderName;
}

public String getLeaderPost() {
	return leaderPost;
}

public void setLeaderPost(String leaderPost) {
	this.leaderPost = leaderPost;
}

public int getLeaderOrder() {
	return leaderOrder;
}

public void setLeaderOrder(int leaderOrder) {
	this.leaderOrder = leaderOrder;
}

public String getLeaderPhotosUrl() {
	return leaderPhotosUrl;
}

public void setLeaderPhotosUrl(String leaderPhotosUrl) {
	this.leaderPhotosUrl = leaderPhotosUrl;
}

public String getLeaderPhotosName() {
	return leaderPhotosName;
}

public void setLeaderPhotosName(String leaderPhotosName) {
	this.leaderPhotosName = leaderPhotosName;
}

public String getApprovaler() {
	return approvaler;
}

public void setApprovaler(String approvaler) {
	this.approvaler = approvaler;
}

public String getLeaderResume() {
	return leaderResume;
}

public void setLeaderResume(String leaderResume) {
	this.leaderResume = leaderResume;
}

public String getEntryUser() {
	return entryUser;
}

public void setEntryUser(String entryUser) {
	this.entryUser = entryUser;
}

public String getModifyUser() {
	return modifyUser;
}

public void setModifyUser(String modifyUser) {
	this.modifyUser = modifyUser;
}

public int getStatusID() {
	return statusID;
}

public void setStatusID(int statusID) {
	this.statusID = statusID;
}

public String getStatusName() {
	return statusName;
}

public void setStatusName(String statusName) {
	this.statusName = statusName;
}

public List<LeaderInfo> getLeaderInfo() {
	return leaderInfo;
}

public void setLeaderInfo(List<LeaderInfo> leaderInfo) {
	this.leaderInfo = leaderInfo;
}
 
 

}
