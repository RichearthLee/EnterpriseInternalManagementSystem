package com.mftcc.interior.cred.bean;

import com.mftcc.interior.api.enums.Method;


public class weather {
	private Integer showapi_res_code;
	private String c6;
	private String c5;
	private String c4;
	private String c3;
	private String c9;
	private String c8;
	private String c7;
	public Integer getShowapi_res_code() {
		return showapi_res_code;
	}
	public void setShowapi_res_code(Integer showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}
	public String getC6() {
		return c6;
	}
	public void setC6(String c6) {
		this.c6 = c6;
	}
	public String getC5() {
		return c5;
	}
	public void setC5(String c5) {
		this.c5 = c5;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC9() {
		return c9;
	}
	public void setC9(String c9) {
		this.c9 = c9;
	}
	public String getC8() {
		return c8;
	}
	public void setC8(String c8) {
		this.c8 = c8;
	}
	public String getC7() {
		return c7;
	}
	public void setC7(String c7) {
		this.c7 = c7;
	}
	@Override
	public String toString() {
		return "weather [showapi_res_code=" + showapi_res_code + ", c6=" + c6
				+ ", c5=" + c5 + ", c4=" + c4 + ", c3=" + c3 + ", c9=" + c9
				+ ", c8=" + c8 + ", c7=" + c7 + "]";
	}
	public weather(Integer showapi_res_code,String c6,String c5,String c4,String c3,String c9,String c8,String c7) {
		super();
		this.showapi_res_code=showapi_res_code;
		this.c6=c6;
		this.c5=c5;
		this.c4=c4;
		this.c3=c3;
		this.c9=c9;
		this.c8=c8;
		this.c7=c7;
	}
	
}
