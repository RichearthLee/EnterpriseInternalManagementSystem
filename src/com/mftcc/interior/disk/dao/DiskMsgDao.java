package com.mftcc.interior.disk.dao;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskMsg;

public interface DiskMsgDao {
	//基本的增删改查
	public int insertMsg(DiskMsg msg);
	public int updateMsg(DiskMsg msg);
	public List<DiskMsg> getMsg(DiskMsg msg);//按关键字不分页查询
	public List<DiskMsg> getMsgs();//查询所有不分页
	//public List<EmplyBean> getEmplyListPage(Ipage ipage) throws Exception;//分页模糊查询
	//public String getEmplyListCount(Ipage ipage) throws Exception;//模糊统计
	public int  deleteMsg(DiskMsg msg);//删除单个数据
	
	
	//移植过来的内容 
	public   void save(DiskMsg msg);
}