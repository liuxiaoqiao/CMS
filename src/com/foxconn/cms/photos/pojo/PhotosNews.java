package com.foxconn.cms.photos.pojo;

import java.util.List;

public class PhotosNews {
	private String photosNewsID;
	private String photosNewsTitle;
	private int photosNewsStatus;
	private String statusName;
	private String entryUser;
	private String entryDate;
	private String modifyUser;
	private String modifyDate;
	private String sectionPostion;
	private String approvaler;
	private String writer;
	private String subPhotosNewsTitle;
	private int  photosNewsSource;
	private String sourceName;
	private String photosNewsDesc;
    private String mainPhotosName;
    private String mainPhotosUrl;
    private String mainPhotosID;
	private String pressDateS;
	private String pressDateE;
	
	private List<PhotosNews> photosNews ;

	public String getPhotosNewsID() {
		return photosNewsID;
	}

	public void setPhotosNewsID(String photosNewsID) {
		this.photosNewsID = photosNewsID;
	}

	public String getPhotosNewsTitle() {
		return photosNewsTitle;
	}

	public void setPhotosNewsTitle(String photosNewsTitle) {
		this.photosNewsTitle = photosNewsTitle;
	}

	public int getPhotosNewsStatus() {
		return photosNewsStatus;
	}

	public void setPhotosNewsStatus(int photosNewsStatus) {
		this.photosNewsStatus = photosNewsStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getSectionPostion() {
		return sectionPostion;
	}

	public void setSectionPostion(String sectionPostion) {
		this.sectionPostion = sectionPostion;
	}

	public String getApprovaler() {
		return approvaler;
	}

	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubPhotosNewsTitle() {
		return subPhotosNewsTitle;
	}

	public void setSubPhotosNewsTitle(String subPhotosNewsTitle) {
		this.subPhotosNewsTitle = subPhotosNewsTitle;
	}

	public int getPhotosNewsSource() {
		return photosNewsSource;
	}

	public void setPhotosNewsSource(int photosNewsSource) {
		this.photosNewsSource = photosNewsSource;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getPhotosNewsDesc() {
		return photosNewsDesc;
	}

	public void setPhotosNewsDesc(String photosNewsDesc) {
		this.photosNewsDesc = photosNewsDesc;
	}
	
	public String getMainPhotosName() {
		return mainPhotosName;
	}

	public void setMainPhotosName(String mainPhotosName) {
		this.mainPhotosName = mainPhotosName;
	}

	public String getMainPhotosUrl() {
		return mainPhotosUrl;
	}

	public void setMainPhotosUrl(String mainPhotosUrl) {
		this.mainPhotosUrl = mainPhotosUrl;
	}

	public String getMainPhotosID() {
		return mainPhotosID;
	}

	public void setMainPhotosID(String mainPhotosID) {
		this.mainPhotosID = mainPhotosID;
	}
	
	public String getPressDateS() {
		return pressDateS;
	}

	public void setPressDateS(String pressDateS) {
		this.pressDateS = pressDateS;
	}

	public String getPressDateE() {
		return pressDateE;
	}

	public void setPressDateE(String pressDateE) {
		this.pressDateE = pressDateE;
	}

	public List<PhotosNews> getPhotosNews() {
		return photosNews;
	}

	public void setPhotosNews(List<PhotosNews> photosNews) {
		this.photosNews = photosNews;
	}
	
	
}
