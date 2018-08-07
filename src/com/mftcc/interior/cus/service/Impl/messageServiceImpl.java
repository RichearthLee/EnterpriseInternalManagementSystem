package com.mftcc.interior.cus.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.message;
import com.mftcc.interior.cus.dao.CusMessageDao;
import com.mftcc.interior.cus.service.MessageService;

@Service
public class messageServiceImpl extends BaseDao implements MessageService{

	@Autowired
	private CusMessageDao msgDao;
	@Override
	public List<message> selectByRec(String user){
		return msgDao.selectByRec(user);
	}
	@Override
	public void insertMsg(message msg){
		msgDao.insertMsg(msg);
	}
	@Override
	public void updateNewMsg(String userId){
		msgDao.updateNewMsg(userId);
	}
	@Override
	public message selectMsgByIT(message msg){
		List<message> list=new ArrayList<message>();
		list=msgDao.selectMsgByIT(msg);
		msg=list.get(0);
		return msg;
	}
}
