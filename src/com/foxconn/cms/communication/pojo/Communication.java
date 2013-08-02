package com.foxconn.cms.communication.pojo;

import java.util.List;


public class Communication {
 private String optionId;
 private String optionTitle;
 private String optionContent;
 private int optionStatus;
 private String Id;
 private String userName;
 private String userTel;
 private String userDept;
 private String userMail;
 private String userCommend;
 private String userIp;
 private String newsSummary;
 private String sDate;
 private String eDate;
 private String subTitle;
 private int sectionPosition;
 private String infoname;
 private String entryUser;
 private String optionSummary;
 private String approvaler;
 private int nonsense;
 private int boring;
 private int fear;
 private int sad;
 private int angry;
 private int effectiveCount;
 private int emotionalCount;
 private int happyCount;
 private int nonsenseCount;
 private int boringCount;
 private int fearCount;
 private int sadCount;
 private int angryCount;
 private String readRecordCount;
 private String btnApproveEnable;
 
 
 private List<Communication> CommunicationList;
public String setOptionSummary;
public String setApprovaler;
public void setApprovaler(String approvaler) {
	this.approvaler = approvaler;
};
public void setOptionSummary(String optionSummary) {
	this.optionSummary = optionSummary;
};
 
public String getOptionId() {
	return optionId;
}
public void setOptionId(String optionId) {
	this.optionId = optionId;
}
public String getOptionTitle() {
	return optionTitle;
}
public void setOptionTitle(String optionTitle) {
	this.optionTitle = optionTitle;
}
public String getEDate() {
	return eDate;
}
public void setEDate(String eDate) {
	this.eDate = eDate;
}
public String getSDate() {
	return sDate;
}
public void setSDate(String sDate) {
	this.sDate = sDate;
}
public String getReadRecordCount() {
	if(readRecordCount==null || readRecordCount==""){
		return "0";
	}
	return readRecordCount;
}
public void setReadRecordCount(String readRecordCount) {
	this.readRecordCount = readRecordCount;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserIp() {
	return userIp;
}
public void setUserIp(String userIp) {
	this.userIp = userIp;
}
public String getInfoName() {
	return infoname;
}
public void setInfoName(String infoname) {
	this.infoname = infoname;
}
public String getOptionContent() {
	return optionContent;
}
public void setOptionContent(String optionContent) {
	this.optionContent = optionContent;
}
public String getUserTel() {
	return userTel;
}
public void setUserTel(String userTel) {
	this.userTel = userTel;
}
public String getUserDept() {
	return userDept;
}
public void setUserDept(String userDept) {
	this.userDept = userDept;
}
public String getUserMail() {
	return userMail;
}
public void setUserMail(String userMail) {
	this.userMail = userMail;
}
public String getUserCommend() {
	return userCommend;
}
public void setUserCommend(String userCommend) {
	this.userCommend = userCommend;
}
public int getOptionStatus() {
		return optionStatus;
}
public void setOptionStatus(int optionStatus) {
	this.optionStatus = optionStatus;
}
public String getSubTitle() {
	return subTitle;
}
public void setSubTitle(String subTitle) {
	this.subTitle = subTitle;
}
public String getEntryUser() {
	return entryUser;
}
public void setEntryUser(String entryUser) {
	this.entryUser = entryUser;
}
public String getOptionSummary() {
	return optionSummary;
}
public String getApprovaler() {
	if(approvaler==null){
		return "";
	}
	return approvaler;
}
public int getFear() {
	return fear;
}
public void setFear(int fear) {
	this.fear = fear;
}
public int getSad() {
	return sad;
}
public void setSad(int sad) {
	this.sad = sad;
}
public int getAngry() {
	return angry;
}
public void setAngry(int angry) {
	this.angry = angry;
}
public int getEffectiveCount() {
	return effectiveCount;
}
public void setEffectiveCount(int effectiveCount) {
	this.effectiveCount = effectiveCount;
}
public int getEmotionalCount() {
	return emotionalCount;
}
public void setEmotionalCount(int emotionalCount) {
	this.emotionalCount = emotionalCount;
}
public int getHappyCount() {
	return happyCount;
}
public void setHappyCount(int happyCount) {
	this.happyCount = happyCount;
}
public int getNonsenseCount() {
	return nonsenseCount;
}
public void setNonsenseCount(int nonsenseCount) {
	this.nonsenseCount = nonsenseCount;
}
public int getBoringCount() {
	return boringCount;
}
public void setBoringCount(int boringCount) {
	this.boringCount = boringCount;
}
public int getFearCount() {
	return fearCount;
}
public void setFearCount(int fearCount) {
	this.fearCount = fearCount;
}
public int getSadCount() {
	return sadCount;
}
public void setSadCount(int sadCount) {
	this.sadCount = sadCount;
}
public int getAngryCount() {
	return angryCount;
}
public void setAngryCount(int angryCount) {
	this.angryCount = angryCount;
}
public List<Communication> getCommunicationList() {
	return CommunicationList;
}
public void setTextNewsList(List<Communication> CommunicationList) {
	this.CommunicationList = CommunicationList;
}
public String getNewsSummary() {
	return newsSummary;
}
public void setNewsSummary(String newsSummary) {
	this.newsSummary = newsSummary;
}
public int getSectionPosition() {
	return sectionPosition;
}
public void setSectionPosition(int sectionPosition) {
	this.sectionPosition = sectionPosition;
}
public String getBtnApproveEnable() {
	return btnApproveEnable;
}
public void setBtnApproveEnable(String btnApproveEnable) {
	this.btnApproveEnable = btnApproveEnable;
}
public String getID() {
	return Id;
}
public void setID(String id) {
	Id = id;
}

}
