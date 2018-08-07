package com.mftcc.interior.disk.dao;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.method.bean.Ipage;

public interface DiskInfoDao {
//基本的增删改查
	
	public long insertDiskInfo(DiskInfo disk);	//---------通过测试
	public int updateDiskById(DiskInfo disk);	//更新（动态的拼接语句还没有做）
	public List<DiskInfo> getDiskByUser(String userid);//按用户id查询	//------通过测试
	public DiskInfo getDiskById(long DiskId);
	public List<DiskInfo> getDiskByUserStatus(DiskInfo disk);//根据用户的id和disk的状态找到网盘
	public List<DiskInfo> getDiskInfos();//查询所有不分页
	//public List<EmplyBean> getEmplyListPage(Ipage ipage) throws Exception;//分页模糊查询
	//public String getEmplyListCount(Ipage ipage) throws Exception;//模糊统计
	public int  deleteDiskFile(DiskInfo disk);//删除单个数据
	//更新网盘大小
	public int  updateDiskSizeByUser(DiskInfo disk);
	//更新网盘的文件数量
	public int  updateFileNumByUser(DiskInfo disk);
	//更新网盘大小根据网盘id
	public int  updateDiskSizeById(DiskInfo disk);
	//更新网盘的文件数量根据网盘id
	public int  updateFileNumById(DiskInfo disk);
	public List<DiskInfo> getGroupDiskByPrivIpage(Ipage ipage) throws Exception;//分页模糊查询
	public String getGroupDiskByPrivCount(Ipage ipage) throws Exception;//模糊统计
 

}
