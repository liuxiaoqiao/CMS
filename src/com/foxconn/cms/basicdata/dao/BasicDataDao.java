package com.foxconn.cms.basicdata.dao;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.basicdata.pojo.BasicType;
import com.foxconn.cms.basicdata.pojo.BasicInfo;


@Service("basicDataDao")
public class BasicDataDao {
 @Resource(name="sqlMapClientTemplate")
 private SqlMapClientTemplate sqlMapClientTemplate;
 
 public List<BasicType> getBasicTypeList(BasicType basicType){
	 return  this.sqlMapClientTemplate.queryForList("BasicData.getBasicTypeList",basicType);
 }
 
 public List<BasicInfo> getBasicInfoList(BasicInfo basicInfo){
	 return  this.sqlMapClientTemplate.queryForList("BasicData.getBasicInfoList",basicInfo);
 }
 
 public void insertBasicType(BasicType basicType){
	 this.sqlMapClientTemplate.insert("BasicData.insertBasicType",basicType);
 }
 
 public void insertBasicInfo(BasicInfo basicInfo){
	 this.sqlMapClientTemplate.insert("BasicData.insertBasicInfo",basicInfo);
 }
 
 public void updateBasicType(BasicType basicType){
	 this.sqlMapClientTemplate.update("BasicData.updateBasicType",basicType);
 }
 
 public void updateBasicInfo(BasicInfo basicInfo){
	 this.sqlMapClientTemplate.update("BasicData.updateBasicInfo",basicInfo);
 }
 
 public void deleteBasicType(BasicType basicType){
	 this.sqlMapClientTemplate.update("BasicData.deleteBasicType",basicType);
 }
 
 public void deleteBasicInfo(BasicInfo basicInfo){
	 this.sqlMapClientTemplate.update("BasicData.deleteBasicInfo",basicInfo);
 }
 
 public int getNewBasicTypeID(){
	 return (Integer)this.sqlMapClientTemplate.queryForObject("BasicData.getNewBasicTypeID");
 }
 
 public int getNewBasicInfoID(){
	 return (Integer)this.sqlMapClientTemplate.queryForObject("BasicData.getNewBasicInfoID");
 }
 
 public BasicType getBasicTypeDetail(BasicType basicType){
	 return  (BasicType)this.sqlMapClientTemplate.queryForObject("BasicData.getBasicTypeDetail",basicType);
 }
 
 public BasicInfo getBasicInfoDetail(BasicInfo basicInfo){
	 return  (BasicInfo)this.sqlMapClientTemplate.queryForObject("BasicData.getBasicInfoDetail",basicInfo);
 }

}
