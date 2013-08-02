package com.foxconn.cms.photos.pojo;

import java.util.List;
public class PhotosInfo {
	private String photosID;
	private String photosName;
	private String photosDesc;
	private String photosUrl;
	private int photosOrder;
	private String photosNewsID;
	private String isMainPhotos;
	private String entryUser;
	private String entryDate;
	private String modifyUser;
	private String modifyDate;
	
    private List<PhotosInfo> photosInfoList;

	public String getPhotosID() {
		return photosID;
	}

	public void setPhotosID(String photosID) {
		this.photosID = photosID;
	}

	public String getPhotosName() {
		return photosName;
	}

	public void setPhotosName(String photosName) {
		this.photosName = photosName;
	}

	public String getPhotosDesc() {
		return photosDesc;
	}

	public void setPhotosDesc(String photosDesc) {
		this.photosDesc = photosDesc;
	}

	public String getPhotosUrl() {
		return photosUrl;
	}

	public void setPhotosUrl(String photosUrl) {
		this.photosUrl = photosUrl;
	}

	public int getPhotosOrder() {
		return photosOrder;
	}

	public void setPhotosOrder(int photosOrder) {
		this.photosOrder = photosOrder;
	}

	public String getPhotosNewsID() {
		return photosNewsID;
	}

	public void setPhotosNewsID(String photosNewsID) {
		this.photosNewsID = photosNewsID;
	}

	public String getIsMainPhotos() {
		return isMainPhotos;
	}

	public void setIsMainPhotos(String isMainPhotos) {
		this.isMainPhotos = isMainPhotos;
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

	public List<PhotosInfo> getPhotosInfoList() {
		return photosInfoList;
	}

	public void setPhotosInfoList(List<PhotosInfo> photosInfoList) {
		this.photosInfoList = photosInfoList;
	}
    
    

}
