package com.mftcc.interior.cus.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.cus.bean.message;
import com.mftcc.interior.cus.dao.CusMessageDao;

@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class cusMessageDaoImpl extends BaseDao implements CusMessageDao{

	@Override
	public List<message> selectByRec(String user) {
		return getSqlMapClientTemplate().queryForList("selectByRec",user);
	}
	@Override
	public void insertMsg(message msg){
		getSqlMapClientTemplate().insert("cusInsertMsg",msg);
	}
	@Override
	public void updateNewMsg(String userId){
		getSqlMapClientTemplate().update("updateNewMsg", userId);
	}
	@Override
	public List<message> selectMsgByIT(message msg){
		return getSqlMapClientTemplate().queryForList("selectMsgByIT",msg);
	}
}
