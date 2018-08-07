package com.mftcc.interior.disk.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.dao.DiskFileDao;


@SuppressWarnings(value={"deprecation","unchecked"})
@Repository
public class DiskFileDaoImpl extends BaseDao implements DiskFileDao{

	@Override
	public long insertDiskFile(DiskFile file) {
		// 插入数据
		System.out.println("isLock"+file.getIsLock());
		return (long)getSqlMapClientTemplate().insert("insertFile", file);
	}

	@Override
	public int updateDiskFile(DiskFile file) {
		// 更新
		return (int)getSqlMapClientTemplate().update("updateFile", file);
	}

	@Override
	public List<DiskFile> getDiskFile(DiskFile file) {
		// 根据关键字查询
		List<DiskFile> files = (List<DiskFile>) getSqlMapClientTemplate().queryForList("selectFile", file);
		return files;
	}

	@Override
	public List<DiskFile> getDiskFiles() {
		// 查询所有
		List<DiskFile> files = (List<DiskFile>) getSqlMapClientTemplate().queryForList("selectFile");
		return files;
	}

	@Override
	public int deleteDiskFile(DiskFile file) {
		// 删除
		return (int)getSqlMapClientTemplate().update("deleteFile", file);
	}

	@Override
	public List<DiskFile> getDiskFilePath(String path) {
		// 根据路径模糊查询
		List<DiskFile> files = (List<DiskFile>) getSqlMapClientTemplate().queryForList("selectFilePath",path);
		return files;
	}

	@Override
	public int getFileNumByUser(DiskFile file) {
		// 查询文件的数量
		return (int)getSqlMapClientTemplate().queryForObject("selectFileCount", file);
	}

	@Override
	public int getSizeSumByUser(DiskFile file) {
		// 计算网盘大小
		return (int)getSqlMapClientTemplate().queryForObject("selectFileSizeSum", file);
	}
	
	@Override
	public int getFileNumByDisk(DiskFile file) {
		// 更具网盘id查找文件的数量
		 return (int)getSqlMapClientTemplate().queryForObject("getFileNumByDisk", file);
	}

	@Override
	public int getSizeSumByDisk(DiskFile file) {
		// 计算网盘的大小
		return (int)getSqlMapClientTemplate().queryForObject("getSizeSumByDisk", file);
	}

	@Override
	public int updateMoveFolder(String soupath, String tarpath, String likepath) {
		// 更新文件夹下的子文件的的路径
		Map map = new HashMap();
		 map.put("soupath",soupath);
		 map.put("tarpath",tarpath);
		 map.put("likepath",likepath);
		return (int)getSqlMapClientTemplate().update("updateFolder", map);
	}

	@Override
	public int updateMoveFile(long tarid, String tarpath, long souid) {
		// 更新文件的路径
		Map map = new HashMap();
		 map.put("tarid",tarid);
		 map.put("tarpath",tarpath);
		 map.put("souid",souid);
		return (int)getSqlMapClientTemplate().update("moveFile", map);
	}

	@Override
	public int updateFileLock(DiskFile file) {
		// 修改文件密码
		return (int)getSqlMapClientTemplate().update("updateFileLock", file);
	}

	@Override
	public int updateFileShare(DiskFile file) {
		// 修改文件的分享
		return (int)getSqlMapClientTemplate().update("updateFileShare", file);
	}

	@Override
	public DiskFile getDiskFileById(DiskFile file) {
		// 
		return (DiskFile)getSqlMapClientTemplate().queryForObject("getDiskFileById",file);
	}

	

}
