package com.mftcc.interior.cus.bean;

public class CusReview {

	private String evaId;
	private String userId;
	private String cusId;
	private String feedId;
	private String evaContent;
	//这个userName属性在数据库中没有，是特意为显示姓名准备的
	//到目前为止还没有发生错误
	private String userName;
	public CusReview() {
		super();
	}
	public CusReview(String evaId, String userId, String cusId, String feedId,
			String evaContent) {
		super();
		this.evaId = evaId;
		this.userId = userId;
		this.cusId = cusId;
		this.feedId = feedId;
		this.evaContent = evaContent;
	}
	public CusReview(String evaId, String userId, String cusId, String feedId,
			String evaContent, String userName) {
		super();
		this.evaId = evaId;
		this.userId = userId;
		this.cusId = cusId;
		this.feedId = feedId;
		this.evaContent = evaContent;
		this.userName = userName;
	}
	public String getEvaId() {
		return evaId;
	}
	public void setEvaId(String evaId) {
		this.evaId = evaId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feeId) {
		this.feedId = feeId;
	}
	public String getEvaContent() {
		return evaContent;
	}
	public void setEvaContent(String evaContent) {
		this.evaContent = evaContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
