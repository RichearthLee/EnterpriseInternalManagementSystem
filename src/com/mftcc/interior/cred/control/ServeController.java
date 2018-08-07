package com.mftcc.interior.cred.control;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mftcc.interior.api.util.Json;
import com.mftcc.interior.api.util.showIpaddress;
import com.mftcc.interior.api.util.showcityid;
import com.mftcc.interior.api.util.showconstellation;
import com.mftcc.interior.api.util.showphone;
import com.mftcc.interior.api.util.showweather;


@Controller
public class ServeController {
	
	 //查询手机号服务
	@RequestMapping(value={"/phone"})
	public String phone(){
		return "/cred/service/phone";
	} 
 
	@RequestMapping(value={"/phoneselect"})
	@ResponseBody
	public Map<String, Object> phonetest (String uphone , Map<String, Object> map) throws Exception{
		//Map<String, Object> smap = new HashMap<String, Object>;
		showphone ph = new showphone();
		String result=	 ph.selectphone(uphone);
		Json json=new Json();
		StringBuffer pBuffer = json.jsonshow(result);
		map.put("data", pBuffer);
		System.out.println("查询结果是："+pBuffer);
		return map;
	} 
	
	 //查询天气服务
		@RequestMapping(value={"/weather"})
		public String weather(){
			return "/cred/service/weather";
		} 
	 
		@RequestMapping(value={"/weatherselect"})
		@ResponseBody
		public Map<String, Object> weathertest (String uweather , Map<String, Object> map) throws Exception{
			//Map<String, Object> smap = new HashMap<String, Object>;
			showweather wea= new showweather();
			String result=   wea.selectweather(uweather);
			Json json=new Json();
			StringBuffer wBuffer = json.jsonshow(result);
			map.put("data", wBuffer);
			System.out.println("查询结果是："+wBuffer);
			return map;
		} 

		//查询IP地址服务
				@RequestMapping(value={"/Ipaddress"})
				public String Ipaddress(){
					return "/cred/service/Ipaddress";
				} 
			 
				@RequestMapping(value={"/Ipaddressselect"})
				@ResponseBody
				public Map<String, Object> Ipaddresstest (String uIpaddress , Map<String, Object> map) throws Exception{
					//Map<String, Object> smap = new HashMap<String, Object>;
					showIpaddress Ip= new showIpaddress();
					String result=   Ip.selectIpaddress(uIpaddress);
					Json json=new Json();
					StringBuffer IBuffer = json.jsonshow(result);
					map.put("data", IBuffer);
					System.out.println("查询结果是："+IBuffer);
					return map;
				} 
				//查询星座运势服务
				@RequestMapping(value={"/constellation"})
				public String constellation(){
					return "/cred/service/constellation";
				} 
			 
				@RequestMapping(value={"/constellationselect"})
				@ResponseBody
				public Map<String, Object> constellationtest (String uconstellation , Map<String, Object> map) throws Exception{
					//Map<String, Object> smap = new HashMap<String, Object>;
					showconstellation Ip= new showconstellation();
					String result=   Ip.selectconstellation(uconstellation);
					Json json=new Json();
					StringBuffer IBuffer = json.jsonshow(result);
					map.put("data", IBuffer);
					System.out.println("查询结果是："+IBuffer);
					return map;
				} 
				//查询城市身份证服务
				@RequestMapping(value={"/cityid"})
				public String cityid(){
					return "/cred/service/cityid";
				} 
			 
				@RequestMapping(value={"/cityidselect"})
				@ResponseBody
				public Map<String, Object> cityidtest (String ucityid , Map<String, Object> map) throws Exception{
					//Map<String, Object> smap = new HashMap<String, Object>;
					showcityid Ip= new showcityid();
					String result=   Ip.selectcityid(ucityid);
					Json json=new Json();
					StringBuffer IBuffer = json.jsonshow(result);
					map.put("data", IBuffer);
					System.out.println("查询结果是："+IBuffer);
					return map;
				} 
				
}
