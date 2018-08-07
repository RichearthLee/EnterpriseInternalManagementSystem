package com.mftcc.interior.cus.bean;

public class message {

	private String cusId;
	private String userId;
	//这个userId2暂时当做对应的跟踪消息的id
	private String userId2;
	private String receive;
	private String date;
	private String content;
	private String looked;
	private String newMsg;
	private String type;
	//以下两个属性在对应的数据库表中不存在，是从别的表查出来的
	private String cusName;
	private String userName;
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId2() {
		return userId2;
	}
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLooked() {
		return looked;
	}
	public void setLooked(String looked) {
		this.looked = looked;
	}
	public String getNewMsg() {
		return newMsg;
	}
	public void setNewMsg(String newMsgs) {
		this.newMsg = newMsgs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public message(String cusId, String userId, String userId2, String receive,
			String date, String content, String looked, String newMsg,
			String type, String cusName, String userName) {
		super();
		this.cusId = cusId;
		this.userId = userId;
		this.userId2 = userId2;
		this.receive = receive;
		this.date = date;
		this.content = content;
		this.looked = looked;
		this.newMsg = newMsg;
		this.type = type;
		this.cusName = cusName;
		this.userName = userName;
	}
	public message(String cusId, String userId, String userId2, String receive,
			String date, String content, String looked, String newMsg,
			String type) {
		super();
		this.cusId = cusId;
		this.userId = userId;
		this.userId2 = userId2;
		this.receive = receive;
		this.date = date;
		this.content = content;
		this.looked = looked;
		this.newMsg = newMsg;
		this.type = type;
	}
	public message() {
		super();
	}
}
