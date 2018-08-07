package com.mftcc.interior.disk.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.disk.bean.DiskPriv;
import com.mftcc.interior.disk.dao.DiskPrivDao;
import com.mftcc.interior.disk.service.DiskPrivService;

@Service
public class DiskPrivServiceImpl implements DiskPrivService{
	@Autowired
	private DiskPrivDao diskPrivDao;
	
	@Override
	public long insertDiskPriv(DiskPriv diskpriv) {
		// 插入数据
		return diskPrivDao.inserDiskPriv(diskpriv);
	}

}
