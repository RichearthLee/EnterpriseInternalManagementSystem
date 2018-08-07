<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
</head>
<body>
		        <div class="col-sm-3 input-group">
		          <input type="text"  id="weather" class="form-control" placeholder="地区名">
		              <span class="input-group-btn">
		                
		              </span>
		        </div>
		      <br><br>
		<a href='javascript:void(0);' onclick='toSeclet();' >查询</a>
		      
	  <div id="weatherresult" class="hide">
		      <br> 
		       <h2></h2>
			<h3 id="time"></h3>
			
			<h3 id="cityInfo"></h3>
			<h3 id="c4"></h3>
			<h3 id="c5"></h3>
			<h3 id="c6"></h3>
			<h3 id="c7"></h3>
			<h3 id="c8"></h3>
			<h3 id="c9"></h3>
			<h3 id="c2"></h3>
			<h3 id="c3"></h3>
			
			<h3 id="now"></h3>
			<h3 id="temperature"></h3><h3 id="nowweather"></h3>
			<h3 id="wind_direction"></h3><h3 id="wind_power"></h3>
			<h3 id="nowaqi"></h3><h3 id="temperature_time"></h3>
			
			<h3 id="f1"></h3>
			<h3 id="day"></h3><h3 id="weekday"></h3>
			<h3 id="day_weather"></h3><h3 id="day_air_temperature"></h3>
			<h3 id="sun_begin_end"></h3><h3 id="air_press"></h3>
			<h3 id="night_weather"></h3><h3 id="night_air_temperature"></h3>
			<h3 id="day_wind_power"></h3><h3 id="night_wind_power"></h3>
			<h3 id="day_wind_direction"></h3><h3 id="night_wind_direction"></h3>
			<h3 id="ziwaixian"></h3><h3 id="jiangshui"></h3>
			<h3 id="yh"></h3>
			<h3 id="zs"></h3><h3 id="cl"></h3>
			<h3 id="travel"></h3><h3 id="ag"></h3>
			<h3 id="beauty"></h3><h3 id="pj"></h3>
			<h3 id="dy"></h3><h3 id="nl"></h3>
			<h3 id="pk"></h3><h3 id="uv"></h3>
			<h3 id="aqi"></h3><h3 id="gj"></h3>
			<h3 id="ac"></h3><h3 id="mf"></h3>
			<h3 id="ls"></h3><h3 id="glass"></h3>
			<h3 id="xq"></h3><h3 id="clothes"></h3>
			<h3 id="sports"></h3><h3 id="hc"></h3>
			<h3 id="comfort"></h3><h3 id="wash_car"></h3>
			<h3 id="cold"></h3>
			
			<h3 id="f4"></h3>
			<h3 id="f5"></h3>
			<h3 id="f6"></h3>
			<h3 id="f7"></h3>
	 </div>
		 
</body>
</html>
<script type="text/javascript">

	
	function toSeclet ( ) {
	  	var da = $("#weather").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/weatherselect.json",
				data:{uweather:da},			 
				dataType:"json",
				type:"POST",
				success:function(data){
					 $('#weatherresult').removeClass("hide");
					 var obj = eval ("(" + data.data + ")");
					//alert(data.data);
					//$("h2").html(data.data);
					$("#time").html("天气更新时间: "+obj.showapi_res_body.time);
					$("#cityInfo").html("cityInfo: ");
					$("#c4").html(obj.showapi_res_body.cityInfo.c4);
					$("#c5").html(obj.showapi_res_body.cityInfo.c5);
					$("#c6").html(obj.showapi_res_body.cityInfo.c6);
					$("#c7").html(obj.showapi_res_body.cityInfo.c7);
					$("#c8").html(obj.showapi_res_body.cityInfo.c8);
					$("#c9").html(obj.showapi_res_body.cityInfo.c9);
					$("#c2").html(obj.showapi_res_body.cityInfo.c2);
					$("#c3").html(obj.showapi_res_body.cityInfo.c3);
					
					$("#now").html("即时天气: ");
					$("#temperature").html("温度: "+obj.showapi_res_body.now.temperature);
					$("#nowweather").html("天气: "+obj.showapi_res_body.now.weather);
					$("#wind_direction").html("风向: "+obj.showapi_res_body.now.wind_direction);
					$("#wind_power").html("风力: "+obj.showapi_res_body.now.wind_power);
					$("#nowaqi").html("空气质量: "+obj.showapi_res_body.now.aqi);
					$("#temperature_time").html("更新时间: "+obj.showapi_res_body.now.temperature_time);
					
					$("#f1").html("当天天气情况:");//f1
					$("#day").html("日期: "+obj.showapi_res_body.f1.day);
					$("#weekday").html("星期: "+obj.showapi_res_body.f1.weekday);
					
					$("#day_weather").html("白天天气: "+obj.showapi_res_body.f1.day_weather);
					$("#day_air_temperature").html("白天平均温度: "+obj.showapi_res_body.f1.day_air_temperature);
					$("#sun_begin_end").html("太阳起始: "+obj.showapi_res_body.f1.sun_begin_end);
					$("#air_press").html("气压: "+obj.showapi_res_body.f1.air_press);
					$("#night_weather").html("晚上天气: "+obj.showapi_res_body.f1.night_weather);
					$("#night_air_temperature").html("晚上平均温度: "+obj.showapi_res_body.f1.night_air_temperature);
					//$("#night_weather_pic").html("晚上天气图: "+obj.showapi_res_body.f1.night_weather_pic);
					
					$("#day_wind_power").html("白天风力: "+obj.showapi_res_body.f1.day_wind_power);
					$("#night_wind_power").html("晚上风力: "+obj.showapi_res_body.f1.night_wind_power);
					
					$("#day_wind_direction").html("白天风向: "+obj.showapi_res_body.f1.day_wind_direction);
					$("#night_wind_direction").html("晚上风向: "+obj.showapi_res_body.f1.night_wind_direction);
					
					$("#ziwaixian").html("紫外线: "+obj.showapi_res_body.f1.ziwaixian);
					$("#jiangshui").html("降水: "+obj.showapi_res_body.f1.jiangshui);
					//$("#day_weather_pic").html("白天天气图: "+obj.showapi_res_body.f1.day_weather_pic);
					
					$("#yh").html("约会: "+obj.showapi_res_body.f1.index.yh.desc+"  "+obj.showapi_res_body.f1.index.yh.title);
					$("#zs").html("中暑: "+obj.showapi_res_body.f1.index.zs.desc+"  "+obj.showapi_res_body.f1.index.zs.title);
					$("#cl").html("乘凉: "+obj.showapi_res_body.f1.index.cl.desc+"  "+obj.showapi_res_body.f1.index.cl.title);
					$("#travel").html("旅行: "+obj.showapi_res_body.f1.index.travel.desc+"  "+obj.showapi_res_body.f1.index.travel.title);
					$("#ag").html("过敏: "+obj.showapi_res_body.f1.index.ag.desc+"  "+obj.showapi_res_body.f1.index.ag.title);
					$("#beauty").html("化妆: "+obj.showapi_res_body.f1.index.beauty.desc+"  "+obj.showapi_res_body.f1.index.beauty.title);
					$("#pj").html("啤酒: "+obj.showapi_res_body.f1.index.pj.desc+"  "+obj.showapi_res_body.f1.index.pj.title);
					$("#dy").html("钓鱼: "+obj.showapi_res_body.f1.index.dy.desc+"  "+obj.showapi_res_body.f1.index.dy.title);
					$("#nl").html("耐力: "+obj.showapi_res_body.f1.index.nl.desc+"  "+obj.showapi_res_body.f1.index.nl.title);
					$("#pk").html("天空: "+obj.showapi_res_body.f1.index.pk.desc+"  "+obj.showapi_res_body.f1.index.pk.title);
					$("#uv").html("辐射: "+obj.showapi_res_body.f1.index.uv.desc+"  "+obj.showapi_res_body.f1.index.uv.title);
					$("#aqi").html("空气质量: "+obj.showapi_res_body.f1.index.aqi.desc+"  "+obj.showapi_res_body.f1.index.aqi.title);
					$("#gj").html("逛街: "+obj.showapi_res_body.f1.index.gj.desc+"  "+obj.showapi_res_body.f1.index.gj.title);
					$("#ac").html("空调: "+obj.showapi_res_body.f1.index.ac.desc+"  "+obj.showapi_res_body.f1.index.ac.title);
					$("#mf").html("毛发: "+obj.showapi_res_body.f1.index.mf.desc+"  "+obj.showapi_res_body.f1.index.mf.title);
					$("#ls").html("晾晒: "+obj.showapi_res_body.f1.index.ls.desc+"  "+obj.showapi_res_body.f1.index.ls.title);
					$("#glass").html("眼镜: "+obj.showapi_res_body.f1.index.glass.desc+"  "+obj.showapi_res_body.f1.index.glass.title);
					$("#xq").html("心情: "+obj.showapi_res_body.f1.index.xq.desc+"  "+obj.showapi_res_body.f1.index.xq.title);
					$("#clothes").html("穿衣: "+obj.showapi_res_body.f1.index.clothes.desc+"  "+obj.showapi_res_body.f1.index.clothes.title);
					$("#sports").html("运动: "+obj.showapi_res_body.f1.index.sports.desc+"  "+obj.showapi_res_body.f1.index.sports.title);
					$("#hc").html("划船: "+obj.showapi_res_body.f1.index.hc.desc+"  "+obj.showapi_res_body.f1.index.hc.title);
					$("#comfort").html("舒适: "+obj.showapi_res_body.f1.index.comfort.desc+"  "+obj.showapi_res_body.f1.index.comfort.title);
					$("#wash_car").html("洗车: "+obj.showapi_res_body.f1.index.wash_car.desc+"  "+obj.showapi_res_body.f1.index.wash_car.title);
					$("#cold").html("感冒: "+obj.showapi_res_body.f1.index.cold.desc+"  "+obj.showapi_res_body.f1.index.cold.title);
					
					
					//$("#f2").html(obj.showapi_res_body.f2);
					//$("#f3").html(obj.showapi_res_body.f3);
					//$("#f4").html(obj.showapi_res_body.f4);
					//$("#f5").html(obj.showapi_res_body.f5);
					//$("#f6").html(obj.showapi_res_body.f6);
					//$("#f7").html(obj.showapi_res_body.f7);
					 
				},
				error:function(){
					 layer.msg('查询失败 ！',  {time:500} );
				}
			});
		   
		}
  
	
	</script>





