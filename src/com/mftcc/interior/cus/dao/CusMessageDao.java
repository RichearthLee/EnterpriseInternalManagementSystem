package com.mftcc.interior.cus.dao;

import java.util.List;

import com.mftcc.interior.cus.bean.message;

public interface CusMessageDao {

	List<message> selectByRec(String user);

	void insertMsg(message msg);

	void updateNewMsg(String userId);

	List<message> selectMsgByIT(message msg);

}
