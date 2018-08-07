package com.mftcc.interior.disk.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.dao.DiskInfoDao;
import com.mftcc.method.bean.Ipage;

@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class DiskInfoDaoImpl extends BaseDao implements DiskInfoDao{

	@Override
	public long insertDiskInfo(DiskInfo disk) {
		// 插入数据
		return (long)getSqlMapClientTemplate().insert("insertDisk", disk);
	}

	@Override
	public int updateDiskById(DiskInfo disk) {
		// 根据网盘ID进行更新
		return (int)getSqlMapClientTemplate().update("updateDiskById", disk);
	}

	@Override
	public List<DiskInfo> getDiskByUser(String user_id) {
		// 根据用户查询网盘情况
		List<DiskInfo> disks = (List<DiskInfo>) getSqlMapClientTemplate().queryForList("getDiskByUser", user_id);
		return disks;
	}
    
	@Override
	public DiskInfo getDiskById(long DiskId) {
		// 根据网盘id查询网盘情况
		return (DiskInfo)getSqlMapClientTemplate().queryForObject("getDiskById", DiskId);
	}
	
	@Override
	public List<DiskInfo> getDiskByUserStatus(DiskInfo disk) {
		// 根据用户的id和disk的状态找到网盘
		 return (List<DiskInfo>)getSqlMapClientTemplate().queryForList("getDiskByUserStatus", disk);
	}
	
	@Override
	public List<DiskInfo> getDiskInfos() {
		// 查询所有
		List<DiskInfo> disks = (List<DiskInfo>) getSqlMapClientTemplate().queryForList("selectDisk");
		return disks;
	}

	@Override
	public int deleteDiskFile(DiskInfo disk) {
		// 删除
		return (int)getSqlMapClientTemplate().update("deleteDisk", disk);
	}

	@Override
	public int updateDiskSizeByUser(DiskInfo disk) {
		// TODO Auto-generated method stub
		 return 0;
	}

	@Override
	public int updateFileNumByUser(DiskInfo disk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDiskSizeById(DiskInfo disk) {
		// TODO Auto-generated method stub
		 return (int)getSqlMapClientTemplate().update("updateDiskSize", disk);
	}

	@Override
	public int updateFileNumById(DiskInfo disk) {
		// TODO Auto-generated method stub
		return (int)getSqlMapClientTemplate().update("updateDiskFile", disk);
	}

	@Override
	public List<DiskInfo> getGroupDiskByPrivIpage(Ipage ipage) throws Exception {
		// 
		return (List<DiskInfo>)getSqlMapClientTemplate().queryForList("getGroupDiskByPrivIpage", ipage);
	}

	@Override
	public String getGroupDiskByPrivCount(Ipage ipage) throws Exception {
		// 
		return (String)getSqlMapClientTemplate().queryForObject("getGroupDiskByPrivCount", ipage);
	}

	

	

}
