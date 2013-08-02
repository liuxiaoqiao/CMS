package com.foxconn.cms.leaderinfo.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.cms.leaderinfo.pojo.LeaderInfo;
import com.foxconn.cms.leaderinfo.service.LeaderInfoService;
import com.foxconn.cms.leaderinfo.dao.LeaderInfoDao;

@Service("leaderInfoServiceImpl")
public class LeaderInfoServiceImpl implements  LeaderInfoService{
	@Autowired
	@Resource(name = "leaderInfoDao")
	private LeaderInfoDao leaderInfoDao;
	
	@Override
	public List<LeaderInfo> getLeaderInfoList(LeaderInfo leaderInfo) {
		// TODO Auto-generated method stub
		return leaderInfoDao.getLeaderInfoList(leaderInfo);
	}

	@Override
	public void insertLeaderInfo(LeaderInfo leaderInfo) {
		// TODO Auto-generated method stub
		leaderInfoDao.insertLeaderInfo(leaderInfo);
		
	}

	@Override
	public void updateLeaderInfo(LeaderInfo leaderInfo) {
		// TODO Auto-generated method stub
		leaderInfoDao.updateLeaderInfo(leaderInfo);
		
	}
	
	@Override
	public  void updateRefuseLeaderStatus(LeaderInfo leaderInfo){
		leaderInfoDao.updateRefuseLeaderStatus(leaderInfo);
	}

	@Override
	public void deleteLeaderInfo(LeaderInfo leaderInfo) {
		// TODO Auto-generated method stub
		leaderInfoDao.deleteLeaderInfo(leaderInfo);
		
	}

	@Override
	public LeaderInfo getLeaderInfoDetail(LeaderInfo leaderInfo) {
		// TODO Auto-generated method stub
		return leaderInfoDao.getLeaderInfoDetail(leaderInfo);
	}

}
