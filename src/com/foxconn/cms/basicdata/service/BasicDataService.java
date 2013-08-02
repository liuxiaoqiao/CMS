package com.foxconn.cms.basicdata.service;

import java.util.List;

import com.foxconn.cms.basicdata.pojo.BasicInfo;
import com.foxconn.cms.basicdata.pojo.BasicType;

public interface BasicDataService {
	List<BasicType> getBasicTypeList(BasicType basicType);
	List<BasicInfo> getBasicInfoList(BasicInfo basicInfo);
	void insertBasicType(BasicType basicType);
	void insertBasicInfo(BasicInfo basicInfo);
	void updateBasicType(BasicType basicType);
	void updateBasicInfo(BasicInfo basicInfo);
	void deleteBasicType(BasicType basicType);
	void deleteBasicInfo(BasicInfo basicInfo);
	int getNewBasicTypeID();
	int getNewBasicInfoID();
	BasicType getBasicTypeDetail(BasicType basicType);
	BasicInfo getBasicInfoDetail(BasicInfo basicInfo);

}
