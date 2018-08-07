package com.mftcc.interior.cred.bean;

public class phone {
	
	private Integer showapi_res_code;
	private Integer num;
	private String prov;
	private Integer ret_code;
	private String areaCode;
	private String name;
	private String cityCode;
	private String postCode;
	private String provCode;
	private String city;
	private Integer type;
	public Integer getShowapi_res_code() {
		return showapi_res_code;
	}
	public void setShowapi_res_code(Integer showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public Integer getRet_code() {
		return ret_code;
	}
	public void setRet_code(Integer ret_code) {
		this.ret_code = ret_code;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvCode() {
		return provCode;
	}
	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "phone [showapi_res_code=" + showapi_res_code + ", num=" + num
				+ ", prov=" + prov + ", ret_code=" + ret_code + ", areaCode="
				+ areaCode + ", name=" + name + ", cityCode=" + cityCode
				+ ", postCode=" + postCode + ", provCode=" + provCode
				+ ", city=" + city + ", type=" + type + "]";
	}
	public phone() {
		 
	}
	public phone(Integer showapi_res_code, Integer num, String prov,
			Integer ret_code, String areaCode, String name, String cityCode,
			String postCode, String provCode, String city, Integer type) {
		super();
		this.showapi_res_code = showapi_res_code;
		this.num = num;
		this.prov = prov;
		this.ret_code = ret_code;
		this.areaCode = areaCode;
		this.name = name;
		this.cityCode = cityCode;
		this.postCode = postCode;
		this.provCode = provCode;
		this.city = city;
		this.type = type;
	}
	
	
 

}
