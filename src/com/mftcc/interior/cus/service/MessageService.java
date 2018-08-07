package com.mftcc.interior.cus.service;

import java.util.List;

import com.mftcc.interior.cus.bean.message;

public interface MessageService {

	List<message> selectByRec(String user);

	void insertMsg(message msg);

	void updateNewMsg(String userId);

	message selectMsgByIT(message msg);

}
