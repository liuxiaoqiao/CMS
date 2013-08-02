package com.foxconn.cms.leaderinfo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.leaderinfo.pojo.LeaderInfo;

@Service("leaderInfoDao")
public class LeaderInfoDao {
 @Resource(name="sqlMapClientTemplate")
 private SqlMapClientTemplate sqlMapClientTemplate;
 
 public List<LeaderInfo> getLeaderInfoList(LeaderInfo leaderInfo){
	return this.sqlMapClientTemplate.queryForList("LeaderInfo.getLeaderInfoList",leaderInfo);
 }
 
 public void insertLeaderInfo(LeaderInfo leaderInfo){
	 this.sqlMapClientTemplate.insert("LeaderInfo.insertLeaderInfo",leaderInfo);
}
 public void updateLeaderInfo(LeaderInfo leaderInfo){
	 	this.sqlMapClientTemplate.update("LeaderInfo.updateLeaderInfo",leaderInfo);
}
 
 public void updateRefuseLeaderStatus(LeaderInfo leaderInfo){
	 this.sqlMapClientTemplate.update("LeaderInfo.updateRefuseLeaderStatus",leaderInfo);
 }
 
 public void deleteLeaderInfo(LeaderInfo leaderInfo){
	 	this.sqlMapClientTemplate.update("LeaderInfo.deleteLeaderInfo",leaderInfo);
 }
 
 public LeaderInfo  getLeaderInfoDetail(LeaderInfo leaderInfo){
	  return (LeaderInfo)this.sqlMapClientTemplate.queryForObject("LeaderInfo.getLeaderInfoDetail", leaderInfo);
 }
 
}
