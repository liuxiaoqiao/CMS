package com.foxconn.cms.basicdata.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.cms.basicdata.pojo.BasicType;
import com.foxconn.cms.basicdata.pojo.BasicInfo;
import com.foxconn.cms.basicdata.service.BasicDataService;
import com.foxconn.cms.basicdata.dao.BasicDataDao;

@Service("basicDataServiceImpl")
public class BasicDataServiceImpl implements BasicDataService{
	@Autowired
	@Resource(name = "basicDataDao")
	private BasicDataDao basicDataDao;

	@Override
	public List<BasicType> getBasicTypeList(BasicType basicType) {
		// TODO Auto-generated method stub
		return basicDataDao.getBasicTypeList(basicType);
	}

	@Override
	public void insertBasicType(BasicType basicType) {
		// TODO Auto-generated method stub
		basicDataDao.insertBasicType(basicType);
		
	}

	@Override
	public void updateBasicType(BasicType basicType) {
		// TODO Auto-generated method stub
		basicDataDao.updateBasicType(basicType);
		
	}

	@Override
	public void deleteBasicType(BasicType basicType) {
		// TODO Auto-generated method stub
		basicDataDao.deleteBasicType(basicType);
		
	}

	@Override
	public int getNewBasicTypeID() {
		// TODO Auto-generated method stub
		return basicDataDao.getNewBasicTypeID();
	}

	@Override
	public BasicType getBasicTypeDetail(BasicType basicType) {
		// TODO Auto-generated method stub
		return basicDataDao.getBasicTypeDetail(basicType);
	}

	@Override
	public List<BasicInfo> getBasicInfoList(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		return basicDataDao.getBasicInfoList(basicInfo);
	}

	@Override
	public void insertBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		basicDataDao.insertBasicInfo(basicInfo);
		
	}

	@Override
	public void updateBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		basicDataDao.updateBasicInfo(basicInfo);
		
	}

	@Override
	public void deleteBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		basicDataDao.deleteBasicInfo(basicInfo);
		
	}

	@Override
	public int getNewBasicInfoID() {
		// TODO Auto-generated method stub
		return basicDataDao.getNewBasicInfoID();
	}

	@Override
	public BasicInfo getBasicInfoDetail(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		return basicDataDao.getBasicInfoDetail(basicInfo);
	}
	
	

}
