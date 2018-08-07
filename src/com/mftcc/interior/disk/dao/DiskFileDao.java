package com.mftcc.interior.disk.dao;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskMsg;

public interface DiskFileDao {
	//基本的增删改查
	
	public long insertDiskFile(DiskFile file);//
	public int updateDiskFile(DiskFile file);//根据id号进行更新
	public List<DiskFile> getDiskFile(DiskFile file);//按关键字不分页查询(不包括size和下载次数)
	public List<DiskFile> getDiskFiles();//查询所有不分页
	//根据file的id进行查询
	public DiskFile getDiskFileById(DiskFile file);
	//public List<EmplyBean> getEmplyListPage(Ipage ipage) throws Exception;//分页模糊查询
	//public String getEmplyListCount(Ipage ipage) throws Exception;//模糊统计
	public int  deleteDiskFile(DiskFile file);//删除单个数据
	public List<DiskFile> getDiskFilePath(String path);//模糊根据路径查询文件
	//计算有多少个文件（文件夹）
	public int getFileNumByUser(DiskFile file);
	//计算文件的大小
	public int  getSizeSumByUser(DiskFile file);
	//计算有多少个文件（文件夹）
	public int getFileNumByDisk(DiskFile file);
	//计算文件的大小
	public int  getSizeSumByDisk(DiskFile file);
	//移动文件夹时修改子文件的路径
	public int updateMoveFolder(String soupath,String tarpath,String likepath);
	//移动文件
	public int updateMoveFile(long tarid,String tarpath,long souid);
	//修改文件的密码
	public int updateFileLock(DiskFile file);
	//修改文件的分享
	public int updateFileShare(DiskFile file);
	
	
}
