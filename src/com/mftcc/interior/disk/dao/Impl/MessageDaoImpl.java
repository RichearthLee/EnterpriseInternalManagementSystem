package com.mftcc.interior.disk.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.DiskMsg;
import com.mftcc.interior.disk.dao.DiskMsgDao;
                       
@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class MessageDaoImpl extends BaseDao implements DiskMsgDao{
    
//基本增删改查------------------------------------------
	@Override
	public int insertMsg(DiskMsg msg) {
		// 插入数据
		return (int)getSqlMapClientTemplate().insert("insertMsg", msg);
	}

	@Override
	public int updateMsg(DiskMsg msg) {
		// 更新
		return (int)getSqlMapClientTemplate().update("update", msg);
	}

	@Override
	public List<DiskMsg> getMsg(DiskMsg msg) {
		// 根据关键字查询
		List<DiskMsg> msgs = (List<DiskMsg>) getSqlMapClientTemplate().queryForList("selectMsg", msg);
		return msgs;
	}

	@Override
	public List<DiskMsg> getMsgs() {
		// 根据关键字查询
		List<DiskMsg> msgs = (List<DiskMsg>) getSqlMapClientTemplate().queryForList("selectMsg");
		return msgs;
	}


	@Override
	public int deleteMsg(DiskMsg msg) {
		// 删除
		return (int)getSqlMapClientTemplate().update("deleteMsg", msg);
	}
	
	
//业务逻辑特别查询--------------------------------------
	
	@Override
	public void save(DiskMsg msg) {
		// TODO Auto-generated method stub
		
	}


}
