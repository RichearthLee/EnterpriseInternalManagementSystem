package com.mftcc.interior.disk.bean;

/**
 * @author lw
 * 管理网盘信息的类
 */
public class DiskInfo {
	private Long id;
	private String user_id;
	private long totalSize;
	private long usedSize;
	private int	 fileNumber;
	private String diskname;
	private String diskStatus;
	private String privContext;
	private String creatUserName;
	
	
	public String getCreatUserName() {
		return creatUserName;
	}
	public void setCreatUserName(String creatUserName) {
		this.creatUserName = creatUserName;
	}
	public String getPrivContext() {
		return privContext;
	}
	public void setPrivContext(String privContext) {
		this.privContext = privContext;
	}
	public String getDiskStatus() {
		return diskStatus;
	}
	public void setDiskStatus(String diskStatus) {
		this.diskStatus = diskStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public long getUsedSize() {
		return usedSize;
	}
	public void setUsedSize(long usedSize) {
		this.usedSize = usedSize;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	public int getFileNumber() {
		return fileNumber;
	}

	public String getDiskname() {
		return diskname;
	}
	public void setDiskname(String diskname) {
		this.diskname = diskname;
	}
}
