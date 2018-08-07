package com.mftcc.interior.disk.dao.Impl;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.FilePriv;
import com.mftcc.interior.disk.dao.FilePrivDao;

@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class FilePrivDaoImpl extends BaseDao implements FilePrivDao{

	@Override
	public int insertFilePriv(FilePriv filePriv) {
		// 插入数据
		return 0;
	}

}
