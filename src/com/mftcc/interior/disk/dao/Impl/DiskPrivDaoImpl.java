package com.mftcc.interior.disk.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskPriv;
import com.mftcc.interior.disk.dao.DiskPrivDao;

@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class DiskPrivDaoImpl extends BaseDao implements DiskPrivDao{

	@Override
	public long inserDiskPriv(DiskPriv diskpriv) {
		// TODO Auto-generated method stub
		return (long)getSqlMapClientTemplate().insert("insertDiskPriv", diskpriv);
	}

	@Override
	public List<DiskPriv> getDiskPrivByUser(String userId) {
		// TODO Auto-generated method stub
		return (List<DiskPriv>) getSqlMapClientTemplate().queryForList("getDiskPrivByUser", userId);
	}

}
