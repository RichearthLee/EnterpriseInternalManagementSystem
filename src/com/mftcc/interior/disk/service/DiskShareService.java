package com.mftcc.interior.disk.service;

import java.util.List;

import com.mftcc.interior.disk.bean.DiskShare;
import com.mftcc.method.bean.Ipage;

public interface DiskShareService {
	public int addShare(DiskShare share); 
	public int updateShareUrl(String url);
	public int updateShareStatus(String Status);
	public DiskShare getShareById(String shareId);
	public List<DiskShare> getShareByUser(String userId);
	public Ipage getShareByUserPage(Ipage ipage) throws Exception;
	public int updateShareById(DiskShare share);
	

}
