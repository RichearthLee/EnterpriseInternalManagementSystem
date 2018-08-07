package com.mftcc.interior.disk.service;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskFile;

public interface DiskFileService {
	/**
	 * 删除文件夹
	 * @param folder
	 * @return
	 */
	public  List<DiskFile> deleteFolder(DiskFile folder);
	/**
	 * 删除单个文件（非文件夹）
	 * @param file
	 */
	public void deleteFile(DiskFile file);
	/**
	 * 移动文件
	 * @param sourceFile,targetFile
	 */
	public void updateMoveFile(DiskFile sourceFile,DiskFile targetFile);
	/**
	 * 持久化myFile
	 * @param myFile
	 * @return
	 */
	public long save(DiskFile myFile);
	/**
	 * 返回某网盘的根目录的id，该目录的目录名就是所属网盘的id好号
	 * @param userId
	 * @return
	 */
	public long getHomeId(long disk_id);
	/**
	 * 返回指定的myfile
	 * @param id
	 * @return
	 */
	public  DiskFile getMyFile(long id);
	/**
	 * 返回文件夹内的所有文件
	 * @param folderId
	 * @return
	 */
	public List<DiskFile> getFilesByFolderId(long folderId);
	
	/**获得路径
	 * @param id
	 * @return
	 */
	public  String getPath(long id);
	/**
	 * 返回指定id的fileName
	 * @param id
	 * @return
	 */
	public  String getFileName(long id);
	/**
	 * delete myfile by certain id
	 * @param id
	 */
	public  void deleteById(long id,String uId);
	/**
	 * 重命名指定id的myfile
	 * @param fileId
	 * @param fileName
	 */
	public  void updatename(long fileId,String fileName);
	
	/**
	 * 分享指定的文件
	 * @param fileId
	 */
	public void addshare(DiskFile myFile);
	/**
	 * 取消分享
	 * @param fileId
	 */
	public void cancelShare(long fileId);
	/**
	 * 返回某用户的所有共享文件
	 * @param userId
	 * @return
	 */
	public List<DiskFile> loadAllShareByUser(String userId);
	/**
	 * 更新分享下载数
	 * @param fileId
	 */
	public void updateShareDownload(long fileId);
	/**
	 * 给文件指定密码
	 * @param fileIId
	 * @param pwd
	 */
	public void addLock(long fileId,String pwd);
	
	/**
	 * 为文件解锁
	 * @param fileId
	 */
	public  void deleteLock(long fileId);
	/**
	 * 数据插入成功则返回一个myFIle，否则返回null
	 * @param myFile
	 * @return
	 */
	public  DiskFile addload(DiskFile myFile);
	public int getFileNumByDisk(long disk_id);
	public int getSizeSumByDisk(long disk_id);
	/**
	 * 获取父路径的所有文件
	 * @param myFile
	 * @return
	 */
	public List<DiskFile> getFatherFolder(long homeId);
	
}
