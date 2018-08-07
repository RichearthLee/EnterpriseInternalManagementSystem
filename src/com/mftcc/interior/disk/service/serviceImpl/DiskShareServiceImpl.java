package com.mftcc.interior.disk.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.interior.disk.dao.DiskFileDao;
import com.mftcc.interior.disk.dao.DiskShareDao;
import com.mftcc.interior.disk.service.DiskShareService;
import com.mftcc.method.bean.Ipage;

@Service
public class DiskShareServiceImpl implements DiskShareService{
	@Autowired
	 private DiskShareDao diskShareDao;
	
	@Override
	public int addShare(DiskShare share) {
		diskShareDao.addDiskShare(share);
		return 1;
	}

	@Override
	public int updateShareUrl(String url) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShareStatus(String Status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DiskShare getShareById(String shareId) {
		// 通过Id获取share
		return diskShareDao.getShareById(shareId);
	}

	@Override
	public List<DiskShare> getShareByUser(String userId) {
		// 查询用户的分享记录
		return diskShareDao.getShareByUser(userId);
	}

	@Override
	public Ipage getShareByUserPage(Ipage ipage) throws Exception {
		if(ipage.getCurrPageNo() > 0){
			ipage.setSearchStart((ipage.getCurrPageNo()-1)*ipage.getPageNumber());
		}
		List<DiskShare> ShareList = new ArrayList<DiskShare>();
		ShareList=diskShareDao.getShareByUserPage(ipage);
		//emply = emplyDao.getEmplyListPage(ipage);
		//int allRecord = Integer.parseInt(emplyDao.getEmplyListCount(ipage));
		int allRecord = Integer.parseInt(diskShareDao.getShareByUserCount(ipage));
		int totalpage = allRecord % ipage.getPageNumber() == 0 ? allRecord / ipage.getPageNumber() : allRecord
				/ ipage.getPageNumber() + 1;
		ipage.setTotalPage(totalpage);
		ipage.setDataList(ShareList);
		ipage.setAllRecord(allRecord);
		return ipage;
	}

	@Override
	public int updateShareById(DiskShare share) {
		// TODO Auto-generated method stub
		return diskShareDao.updateShareById(share);
	}

}
