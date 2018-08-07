package com.mftcc.interior.disk.bean;

public class DiskShare {
	private String shareId;
	private String shareUrl;
	private String shareIsDel;
	private String shareStTime;
	private String shareEnTime;
	private long fileId;
	private String shareStatus;
	private String user_id;
	private String sharePwd;
	private String fileName;
	private String fileType;
	


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getSharePwd() {
		return sharePwd;
	}
	public void setSharePwd(String sharePwd) {
		this.sharePwd = sharePwd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getShareIsDel() {
		return shareIsDel;
	}
	public void setShareIsDel(String shareIsDel) {
		this.shareIsDel = shareIsDel;
	}
	public String getShareStTime() {
		return shareStTime;
	}
	public void setShareStTime(String shareStTime) {
		this.shareStTime = shareStTime;
	}
	public String getShareEnTime() {
		return shareEnTime;
	}
	public void setShareEnTime(String shareEnTime) {
		this.shareEnTime = shareEnTime;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public String getShareStatus() {
		return shareStatus;
	}
	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}
}
