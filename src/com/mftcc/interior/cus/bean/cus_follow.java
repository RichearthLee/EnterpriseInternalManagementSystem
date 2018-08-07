package com.mftcc.interior.cus.bean;

public class cus_follow {
	private String feedid;
	private String cusid;
	private String userid;
	private String feedback;
	private String feedtype;
	private String date;
	private String location;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFeedid() {
		return feedid;
	}
	public void setFeedid(String feedid) {
		this.feedid = feedid;
	}
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getFeedtype() {
		return feedtype;
	}
	public void setFeedtype(String feedtype) {
		this.feedtype = feedtype;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public cus_follow(String feedid, String cusid, String userid,
			String feedback, String feedtype, String date, String location) {
		super();
		this.feedid = feedid;
		this.cusid = cusid;
		this.userid = userid;
		this.feedback = feedback;
		this.feedtype = feedtype;
		this.date = date;
		this.location = location;
	}
	public cus_follow() {
		super();
	}
}
