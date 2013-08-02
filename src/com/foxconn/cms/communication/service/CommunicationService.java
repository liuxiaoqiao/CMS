package com.foxconn.cms.communication.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.foxconn.cms.communication.pojo.Communication;
import com.foxconn.cms.textnews.pojo.TextNews;

public interface CommunicationService {
    Communication preViewcommunication(Communication communication);
    List<Communication> preViewcommunicationuser(Communication communication);
    void insertCommunication(Communication communication);

    void updateCommunication(Communication communication);
	public List<com.foxconn.cms.communication.pojo.Communication> getCommunicationList(HashMap<String, String> map);
	Communication getCommunicationDetail(Communication communication);
	void deleteCommunication(Communication communication);
	void deleteCommunicationUser(Communication communication);
}

