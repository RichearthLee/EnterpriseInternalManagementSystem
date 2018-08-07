package com.mftcc.interior.disk.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.disk.bean.DiskFile;
import com.mftcc.interior.disk.bean.DiskInfo;
import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.dao.DiskFileDao;
import com.mftcc.interior.disk.dao.DiskInfoDao;
import com.mftcc.interior.disk.service.DiskInfoService;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.method.bean.Ipage;

@Service
public class DiskInfoServiceImpl implements DiskInfoService{
    
	@Autowired
	 private DiskInfoDao diskInfoDao;
	@Autowired
	 private DiskFileDao diskFileDao;

//通过测试
	@Override
	public List<DiskInfo> load(String userId) {
		// 获取某个用户的网盘信息
		List<DiskInfo> disks=diskInfoDao.getDiskByUser(userId);
		return disks;
	}
	
	@Override
	public DiskInfo getDiskById(long Id) {
		// 获取某个id的网盘的信息
		return diskInfoDao.getDiskById(Id);
		
	}

	@Override
	public boolean isEnoughSpace(DiskFile myFile) {
		//检查网盘空间是不是足够
		DiskInfo dInfo=diskInfoDao.getDiskById(myFile.getDisk_id());
		
		if(dInfo.getTotalSize() > (dInfo.getUsedSize()+myFile.getSize()) ){
			return true;
		}
		return false;
	}

//通过测试
	@Override
	public long addDisk(String userId, long initSize,String diskname) {
		// 新建网盘
		DiskInfo disk=new DiskInfo();
		DiskFile file=new DiskFile();
		DiskFile file2=new DiskFile();
		disk.setUser_id(userId);
		disk.setUsedSize(0);
		disk.setTotalSize(initSize);
		disk.setDiskname(diskname);
		System.out.println(disk.getUser_id());
		long diskid=diskInfoDao.insertDiskInfo(disk);
		//创建根文件
		file.setDisk_id(diskid);
		file.setUser_id(userId);
		file.setName("#"+diskid);
		file.setPath("/");
		file.setType("adir");
		file.setSize(0);
		long homeID=diskFileDao.insertDiskFile(file);
//		//创建四个基本文件
		String path= "/" + homeID + "/";
		file2.setDisk_id(diskid);
		file2.setUser_id(userId);
		file2.setParent_id(homeID);
		file2.setPath(path);
		file2.setType("adir");
		file2.setSize(0);
//		//创建四个基本文件
		file2.setName("我的文档");
		diskFileDao.insertDiskFile(file2);
		file2.setName("我的音乐");
		diskFileDao.insertDiskFile(file2);
		file2.setName("我的相册");
		diskFileDao.insertDiskFile(file2);
		file2.setName("我的图书");
		diskFileDao.insertDiskFile(file2);
        System.out.print(diskid);
		return diskid;
		
	}

	@Override
	public boolean addUsedSize(long diskUsedZise,long id) {
		DiskInfo disk=new DiskInfo();
		disk.setUsedSize(diskUsedZise);
		disk.setId(id);
		int vo=diskInfoDao.updateDiskSizeById(disk);
		if(vo>0)
		{
			return true;
		}else
		{
			return false;
		}
		
	}

	@Override
	public boolean addFileNum(long diskFileNum,long id) {
		DiskInfo disk=new DiskInfo();
		disk.setId(id);
		int vo=diskInfoDao.updateFileNumById(disk);
		if(vo>0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public int updateDisk(int filenum,int disksize,long disk_id) {
		DiskInfo disk=new DiskInfo();
		disk.setFileNumber(filenum);
		disk.setUsedSize(disksize);
		disk.setId(disk_id);
		diskInfoDao.updateDiskById(disk);//需要修改
		return 0;
	}

	@Override
	public List<DiskInfo> getDisksByUser(String userId) {
		
		return diskInfoDao.getDiskByUser(userId);
	}

	@Override
	public List<DiskInfo> getDiskByUserStatus(String userId, String diskStatus) {
		// TODO Auto-generated method stub
		DiskInfo vo=new DiskInfo();
		vo.setDiskStatus(diskStatus);
		vo.setUser_id(userId);
		return diskInfoDao.getDiskByUserStatus(vo);
	}

	@Override
	public Ipage getGroupDiskByPrivPage(Ipage ipage) throws Exception {
		// 通过权限获得群组网盘（分页）
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<DiskInfo> groupDiskList = new ArrayList<DiskInfo>();
		groupDiskList=diskInfoDao.getGroupDiskByPrivIpage(ipage);
		int allRecord = Integer.parseInt(diskInfoDao.getGroupDiskByPrivCount(ipage));
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(groupDiskList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public long addGroupDisk(DiskInfo disk) {
		// 增加网盘
		return diskInfoDao.insertDiskInfo(disk);
	}

	



	
	

}
