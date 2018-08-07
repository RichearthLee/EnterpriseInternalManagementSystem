package com.mftcc.interior.disk.bean;

public class FilePriv {
	private long id;
	private long fileId;
	private String userId;
	private String filePriv;
	private String privType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public String getFilePriv() {
		return filePriv;
	}
	public void setFilePriv(String filePriv) {
		this.filePriv = filePriv;
	}
	public String getPrivType() {
		return privType;
	}
	public void setPrivType(String privType) {
		this.privType = privType;
	}
}
