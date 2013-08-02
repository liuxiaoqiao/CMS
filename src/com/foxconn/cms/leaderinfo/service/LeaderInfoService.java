package com.foxconn.cms.leaderinfo.service;

import java.util.List;

import com.foxconn.cms.leaderinfo.pojo.LeaderInfo;

public interface LeaderInfoService {
	List<LeaderInfo> getLeaderInfoList(LeaderInfo leaderInfo);
	void insertLeaderInfo(LeaderInfo leaderInfo);
	void updateLeaderInfo(LeaderInfo leaderInfo);
	void updateRefuseLeaderStatus(LeaderInfo leaderInfo);
	void deleteLeaderInfo(LeaderInfo leaderInfo);
	LeaderInfo  getLeaderInfoDetail(LeaderInfo leaderInfo);
}
