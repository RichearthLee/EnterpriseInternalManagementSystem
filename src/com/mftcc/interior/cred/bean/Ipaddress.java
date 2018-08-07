package com.mftcc.interior.cred.bean;

public class Ipaddress {
	
	private String ret;
	private String msg;
	private String log_id;
	private String area;
	private String country;
	private String long_ip;
	private String city;
	private String ip;
	private String isp;
	private String region_id;
	private String region;
	private String country_id;
	private String city_id;
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLong_ip() {
		return long_ip;
	}
	public void setLong_ip(String long_ip) {
		this.long_ip = long_ip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	@Override
	public String toString() {
		return "Ipaddress [ret=" + ret + ", msg=" + msg + ", log_id=" + log_id
				+ ", area=" + area + ", country=" + country + ", long_ip="
				+ long_ip + ", city=" + city + ", ip=" + ip + ", isp=" + isp
				+ ", region_id=" + region_id + ", region=" + region
				+ ", country_id=" + country_id + ", city_id=" + city_id + "]";
	}
	public Ipaddress(String ret,String msg,String log_id,String area,String country,String long_ip,String city,String ip,String isp,String region_id,String region,String country_id,String city_id) {
		super();
		// TODO Auto-generated constructor stub
		this.ret = ret;
		this.msg = msg;
		this.log_id = log_id;
		this.area = area;
		this.country = country;
		this.long_ip = long_ip;
		this.city = city;
		this.ip = ip;
		this.isp = isp;
		this.region_id = region_id;
		this.region = region;
		this.country_id = country_id;
		this.city_id = city_id;
	}
	
	
	
 

}
