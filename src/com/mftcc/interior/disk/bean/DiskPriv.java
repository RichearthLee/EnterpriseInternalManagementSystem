package com.mftcc.interior.disk.bean;

public class DiskPriv {
	private long id;
	private String privUserId;
	private long privDiskId;
	private long privFileId;
	private String privContext;
	private String privType;

	public String getPrivUserId() {
		return privUserId;
	}
	public void setPrivUserId(String privUserId) {
		this.privUserId = privUserId;
	}
	public long getPrivDiskId() {
		return privDiskId;
	}
	public void setPrivDiskId(long privDiskId) {
		this.privDiskId = privDiskId;
	}
	public long getPrivFileId() {
		return privFileId;
	}
	public void setPrivFileId(long privFileId) {
		this.privFileId = privFileId;
	}
	public String getPrivContext() {
		return privContext;
	}
	public void setPrivContext(String privContext) {
		this.privContext = privContext;
	}
	public String getPrivType() {
		return privType;
	}
	public void setPrivType(String privType) {
		this.privType = privType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
