package com.mftcc.interior.disk.service;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.method.bean.Ipage;

public interface DiskInfoService {
	/**
	 * 获取对应用户的网盘使用情况
	 * @param userId
	 * @return
	 */
	public List<DiskInfo> load(String userId);
	/**
	 * 根据id查询网盘的信息
	 * @param Id
	 * @return
	 */
	public DiskInfo getDiskById(long Id);
	/**
	 * @param myFile
	 * 检查用户的网盘空间是否耗尽
	 */
	public  boolean isEnoughSpace(DiskFile myFile);
	/**
	 * 新建网盘，初始化用户的一些默认信息（碗盘容量，教程文件）
	 * @param userId
	 * @return disk_id
	 */
	public  long addDisk(String userId,long initSize,String diskname); 
	/**
	 * 新建群组网盘
	 * @param diskInfo
	 * @return disk_id
	 */
	public  long addGroupDisk(DiskInfo disk); 
	/**
	 * 更新网盘的容量
	 * @param usedSize
	 * @return
	 */
	public boolean addUsedSize(long diskUsedZise,long id);
	/**
	 * 更新网盘的文件数量
	 * @param usedSize
	 * @return
	 */
	public boolean addFileNum(long diskFileNum,long id);
	/**
	 * 更新网盘的文件数量，和网盘大小
	 * @param usedSize
	 * @return
	 */
	public int updateDisk(int filenum,int disksize,long disk_id);
	/**
	 * 通过用户查网盘的记录
	 * @param usedSize
	 * @return
	 */
	public List<DiskInfo> getDisksByUser(String userId);
	/**
	 * 通过用户和网盘状态查个人网盘的记录
	 * @param usedSize
	 * @return
	 */
	public List<DiskInfo> getDiskByUserStatus(String userId,String diskStatus);
	/**
	 * 通过权限查找群组网盘
	 * @param usedSize
	 * @return
	 */
	public Ipage getGroupDiskByPrivPage(Ipage ipage) throws Exception;
}
