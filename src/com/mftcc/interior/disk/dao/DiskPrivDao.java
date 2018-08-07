package com.mftcc.interior.disk.dao;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskPriv;



public interface DiskPrivDao {
	public long inserDiskPriv(DiskPriv diskpriv);
    public List<DiskPriv> getDiskPrivByUser(String userId);
}
