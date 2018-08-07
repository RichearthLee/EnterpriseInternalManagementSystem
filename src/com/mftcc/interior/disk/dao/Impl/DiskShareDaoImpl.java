package com.mftcc.interior.disk.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.dao.DiskInfoDao;
import com.mftcc.interior.disk.dao.DiskShareDao;
import com.mftcc.method.bean.Ipage;

@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class DiskShareDaoImpl extends BaseDao implements DiskShareDao {

	@Override
	public void addDiskShare(DiskShare share) {
		getSqlMapClientTemplate().insert("addDiskShare", share);
		
	}

	@Override
	public DiskShare getShareById(String shareId) {
		DiskShare share=new DiskShare();
		share.setShareId(shareId);
		return (DiskShare)getSqlMapClientTemplate().queryForObject("getShareById", share);
	}

	@Override
	public List<DiskShare> getShareByUser(String userId) {
		DiskShare share=new DiskShare();
		share.setUser_id(userId);
		return (List<DiskShare>)getSqlMapClientTemplate().queryForList("getShareByUser", share);
	}

	@Override
	public List<DiskShare> getShareByStatus(String shareStatus) {
		DiskShare share=new DiskShare();
		share.setShareStatus(shareStatus);
		return (List<DiskShare>)getSqlMapClientTemplate().queryForList("getShareByStatus", share);
		
	}

	@Override
	public int updateShareById(DiskShare share) {
		System.out.println("daoceng:"+share.getShareEnTime());
		return (int)getSqlMapClientTemplate().update("updateShareById", share);
	}

	@Override
	public int updateShareSatus(DiskShare share) {
		
		return (int)getSqlMapClientTemplate().update("updateShareSatus", share);
	}

	@Override
	public int deleteShareById(String shareId) {
	
		return (int)getSqlMapClientTemplate().update("deleteShareById", shareId);
	}

	@Override
	public int updateSharePwd(String pwd) {
		// TODO Auto-generated method stub
		return (int)getSqlMapClientTemplate().update("updateSharePwd", pwd);
	}

	@Override
	public List<DiskShare> getShareByUserPage(Ipage ipage) throws Exception {
		// TODO Auto-generated method stub
		 return  (List<DiskShare>)getSqlMapClientTemplate().queryForList("getShareByUserPage", ipage);
	}

	@Override
	public String getShareByUserCount(Ipage ipage) throws Exception {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("getShareByUserCount", ipage);
	}



	

}
