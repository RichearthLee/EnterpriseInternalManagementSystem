package com.mftcc.interior.disk.dao;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.method.bean.Ipage;

public interface DiskShareDao {
	//---------基本的怎删改查---------
	public void addDiskShare(DiskShare share);
	public DiskShare getShareById(String shareId);
	public List<DiskShare> getShareByUser(String userId);
	public List<DiskShare> getShareByStatus(String shareStatus);
	public int updateShareSatus(DiskShare share);
	public int deleteShareById(String shareId);
	public int updateSharePwd(String pwd);
	public int updateShareById(DiskShare share);//根据id更新分享信息(除创建时的信息)
	public List<DiskShare> getShareByUserPage(Ipage ipage) throws Exception;//分页模糊查询
	public String getShareByUserCount(Ipage ipage) throws Exception;//模糊统计

}
