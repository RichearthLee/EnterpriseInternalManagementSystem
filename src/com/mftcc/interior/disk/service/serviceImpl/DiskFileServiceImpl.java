package com.mftcc.interior.disk.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.dao.DiskFileDao;
import com.mftcc.interior.disk.dao.DiskInfoDao;
import com.mftcc.interior.disk.service.DiskFileService;
import com.mftcc.interior.disk.service.DiskInfoService;


@Service
public class DiskFileServiceImpl implements DiskFileService{
	@Autowired
	 private DiskInfoDao diskInfoDao;
	@Autowired
	 private DiskFileDao diskFileDao;
	
//已修改	
	@Override
	public List<DiskFile> deleteFolder(DiskFile folder) {
		System.out.print("用户id号"+folder.getUser_id());
		//删除文件夹
		DiskFile file=new DiskFile ();
		//获取文件夹的路径
		file.setId(folder.getId());
		file.setDisk_id(folder.getDisk_id());
		List<DiskFile> disks=diskFileDao.getDiskFile(file);
		//拼接这个文件夹的路径
		String path=disks.get(0).getPath()+folder.getId()+"/%";
		//根据路径模糊查找所有在路径下的文件
		List<DiskFile> myfiles=diskFileDao.getDiskFilePath(path);
		//删除文件夹
		diskFileDao.deleteDiskFile(folder);
//		//计算还有多少个文件
		int filenum=diskFileDao.getFileNumByDisk(file);
//		//计算文件大小
		int disksize=diskFileDao.getSizeSumByDisk(file);
		//更新网盘的数据
		DiskInfo disk=new DiskInfo();
		disk.setFileNumber(filenum);
		disk.setUsedSize(disksize);
		disk.setId(folder.getDisk_id());
		diskInfoDao.updateDiskById(disk);
		return myfiles;
	}

//已经修改
	@Override
	public void deleteFile(DiskFile file) {
		
		//删除单个文件
		diskFileDao.deleteDiskFile(file);
		//计算还有多少个文件
		int filenum=diskFileDao.getFileNumByDisk(file);
		//计算文件大小
		int disksize=diskFileDao.getSizeSumByDisk(file);
		//更新网盘的数据
		DiskInfo disk=new DiskInfo();
		disk.setFileNumber(filenum);
		disk.setUsedSize(disksize);
		disk.setId(file.getDisk_id());
		diskInfoDao.updateDiskById(disk);
		
	}

//没有用到userID，未修改
	@Override
	public void updateMoveFile(DiskFile sourceFile, DiskFile targetFile) {
		//移动文件 
		if(sourceFile.getType().equals("adir"))
		{
			
			diskFileDao.updateMoveFolder(sourceFile.getPath(), targetFile.getPath() + targetFile.getId() + "/", sourceFile.getPath() + sourceFile.getId() + "/%");
		}
		diskFileDao.updateMoveFile(targetFile.getId(), targetFile.getPath()+targetFile.getId()+"/",sourceFile.getId());
		
	}

//未用到userId
	@Override
	public long save(DiskFile myFile) {
		// 插入文件
		
		return diskFileDao.insertDiskFile(myFile);
	}

//已经修改
	@Override
	public long getHomeId(long disk_id) {
		// 获得文件根目录
		DiskFile vo=new DiskFile();
		vo.setName("#"+disk_id);
		List<DiskFile> file=diskFileDao.getDiskFile(vo);
		return file.get(0).getId();
	}

//已经修改
	@Override
	public DiskFile getMyFile(long id) {
		// 获得特定文件
		DiskFile vo=new DiskFile();
		vo.setId(id);
		DiskFile file=diskFileDao.getDiskFileById(vo);
		return file;
	}

//已经修改
	@Override
	public String getPath(long id) {
		// 获得指定文件路径
		DiskFile vo=new DiskFile();
		vo.setId(id);
		DiskFile file=diskFileDao.getDiskFileById(vo);
		return file.getPath();
	}

//已经修改
	@Override
	public String getFileName(long id) {
		// 获得指定文件的名称
		DiskFile vo=new DiskFile();
		vo.setId(id);
		DiskFile file=diskFileDao.getDiskFileById(vo);
		return file.getName();
	}

//未用到userID
	@Override
	public List<DiskFile> getFilesByFolderId(long folderId) {
		// 获得文件夹下面所有文件
		DiskFile vo=new DiskFile();
		vo.setParent_id(folderId);
		List<DiskFile> file=diskFileDao.getDiskFile(vo);
		return file;
	}

//未用到useId
	@Override
	public void deleteById(long id, String uId) {
		// 删除文件
		DiskFile vo=new DiskFile();
		vo.setId(id);
		diskFileDao.deleteDiskFile(vo);
		}
//未用到userid号	
	@Override
	public void  updatename(long fileId, String fileName) {
		//对文件名重命名
		DiskFile vo=new DiskFile();
		vo.setId(fileId);
		vo.setName(fileName);
		diskFileDao.updateDiskFile(vo);
	}

//未用到userid号	
	@Override
	public void addshare(DiskFile myFile) {
		// 分享文件
		DiskFile vo=new DiskFile();
		vo.setId(myFile.getId());
		vo.setIsShare("1");
		vo.setShareUrl(myFile.getShareUrl());
		diskFileDao.updateFileShare(vo);
	}

//未用到userid号
	@Override
	public void cancelShare(long fileId) {
		// 删除分享
		DiskFile vo=new DiskFile();
		vo.setId(fileId);
		vo.setIsShare("0");
		diskFileDao.updateFileShare(vo);
		
	}

	@Override
	public List<DiskFile> loadAllShareByUser(String userId) {
		// TODO Auto-generated method stub
		DiskFile vo=new DiskFile();
		vo.setUser_id(userId);
		vo.setIsShare("1");
		List<DiskFile> file=diskFileDao.getDiskFile(vo);
		return file;
	}

	@Override
	public void updateShareDownload(long fileId) {
		// TODO Auto-generated method stub
		
	}
	
	//未用到userid号
	@Override
	public void addLock(long fileId, String pwd) {
		// 文件加密
		DiskFile vo=new DiskFile();
		vo.setId(fileId);
		vo.setIsLock("1");
		vo.setPassword(pwd);
		diskFileDao.updateFileLock(vo);
		
	}

	//未用到userid号
	@Override
	public void deleteLock(long fileId) {
		// 删除锁
		DiskFile vo=new DiskFile();
		vo.setId(fileId);
		vo.setIsLock("0");
		vo.setPassword("");
		diskFileDao.updateFileLock(vo);		
	}
    
	//已经修改
	@Override
	public DiskFile addload(DiskFile myFile) {
		// 上传文件
		long id=diskFileDao.insertDiskFile(myFile);
		myFile.setId(id);
		DiskInfo vo=new DiskInfo();
		vo.setUsedSize(myFile.getSize());
		vo.setId(myFile.getDisk_id());
//		修改网盘文件大小
		diskInfoDao.updateDiskSizeById(vo);
//		修改网盘的文件数量
		diskInfoDao.updateFileNumById(vo);
		return myFile;
	}

	@Override
	public int getFileNumByDisk(long disk_id) {
		// TODO Auto-generated method stub
		DiskFile vo=new DiskFile();
		vo.setDisk_id(disk_id);
		return diskFileDao.getFileNumByDisk(vo);
	}

	@Override
	public int getSizeSumByDisk(long disk_id) {
		// TODO Auto-generated method stub
		DiskFile vo=new DiskFile();
		vo.setDisk_id(disk_id);
		return diskFileDao.getSizeSumByDisk(vo);
	}

	@Override
	public List<DiskFile> getFatherFolder(long homeId) {
		// TODO Auto-generated method stub
		return null;
	}



}
