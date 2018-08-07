package com.mftcc.interior.cred.bean;

public class constellation {

	private Integer showapi_res_code;
	private Integer showapi_res_error;
	private String star;
	private String day;
	public Integer getShowapi_res_code() {
		return showapi_res_code;
	}
	public void setShowapi_res_code(Integer showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}
	public Integer getShowapi_res_error() {
		return showapi_res_error;
	}
	public void setShowapi_res_error(Integer showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "constellation [showapi_res_code=" + showapi_res_code
				+ ", showapi_res_error=" + showapi_res_error + ", star=" + star
				+ ", day=" + day + "]";
	}
	public constellation(Integer showapi_res_code,Integer showapi_res_error,String star,String day) {
		super();
		// TODO Auto-generated constructor stub
		this.showapi_res_code = showapi_res_code;
		this.showapi_res_error = showapi_res_error;
		this.star = star;
		this.day = day;
	}
	
}
