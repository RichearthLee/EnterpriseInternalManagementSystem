<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=EDGE">
        <title>ECharts</title>
       <!--  <link rel="stylesheet" type="text/css" href="css/main.css"/> -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/report1/jedate/jquery-1.7.2.js"></script>
        <!-- <script src="js/jquery-1.9.1.min.js"></script> -->
        <script src="<%=request.getContextPath()%>/report1/map/js/echarts.min.js"></script>
        <script src="<%=request.getContextPath()%>/report1/map/js/china.js"></script>
        
        <script src="<%=request.getContextPath()%>/report1/map/js/province/beijing.js"></script>
        <script src="<%=request.getContextPath()%>/report1/map/js/province/shanghai.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/neimenggu.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/taiwan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/xianggang.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/aomen.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/chongqing.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/tianjin.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/xinjiang.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/beijing.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/ningxia.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/qinghai.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/gansu.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/guangxi.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/hainan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/sichuan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/guizhou.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/yunnan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/xizang.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/shanxi1.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/guangdong.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/hunan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/hubei.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/henan.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/shandong.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/jiangxi.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/fujian.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/liaoning.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/jilin.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/heilongjiang.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/jiangsu.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/zhejiang.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/anhui.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/shanxi.js"></script>
	    <script src="<%=request.getContextPath()%>/report1/map/js/province/hebei.js"></script>
        <style>#china-map {width:1000px; height: 1000px;margin: auto;}</style>
       	<style type="text/css">
       	body {
			background-color: #F9F9F9;
			margin:0; padding:0;
		}
       	</style>
    </head>
    <body>        

        <div id="china-map"></div>
        <div id="box"></div>
        <div id="box-title"></div>
        
        <script>
        function randomData() {  
            return Math.round(Math.random()*500);  
        }	
            /* var myChart = echarts.init(document.getElementById('china-map')); */
            var mydata=[
                        {name: '北京',value: 123},  
                        {name: '天津',value: randomData()},  
                        {name: '上海',value: randomData()},  
                        {name: '重庆',value: randomData()},  
                        {name: '河北',value: randomData()},  
                        {name: '河南',value: randomData()},  
                        {name: '云南',value: randomData()},  
                        {name: '辽宁',value: randomData()},  
                        {name: '黑龙江',value: randomData()},  
                        {name: '湖南',value: randomData()},  
                        {name: '安徽',value: randomData()},  
                        {name: '山东',value: randomData()},  
                        {name: '新疆',value: randomData()},  
                        {name: '江苏',value: randomData()},  
                        {name: '浙江',value: randomData()},  
                        {name: '江西',value: randomData()},  
                        {name: '湖北',value: randomData()},  
                        {name: '广西',value: randomData()},  
                        {name: '甘肃',value: randomData()},  
                        {name: '山西',value: randomData()},  
                        {name: '内蒙古',value: randomData()},  
                        {name: '陕西',value: randomData()},  
                        {name: '吉林',value: randomData()},  
                        {name: '福建',value: randomData()},  
                        {name: '贵州',value: randomData()},  
                        {name: '广东',value: randomData()},  
                        {name: '青海',value: randomData()},  
                        {name: '西藏',value: randomData()},  
                        {name: '四川',value: randomData()},  
                        {name: '宁夏',value: randomData()},  
                        {name: '海南',value: randomData()},  
                        {name: '台湾',value: randomData()},  
                        {name: '香港',value: randomData()},  
                        {name: '澳门',value: randomData()}  
                    ]
            /* var guangdongData = [  
                                 {name: '广州市',value:randomData()},
                                 {name: '佛山市',value:randomData()},  
                                 {name: '肇庆市',value:randomData()},
                                 {name: '韶关市',value:randomData()},  
                                 {name: '清远市',value:randomData()},
                                 {name: '云浮市',value:randomData()},  
                                 {name: '茂名市',value:randomData()},
                                 {name: '湛江市',value:randomData()},  
                                 {name: '江门市',value:randomData()},
                                 {name: '东莞市',value:randomData()},  
                                 {name: '阳江市',value:randomData()},
                                 {name: '深圳市',value:randomData()},  
                                 {name: '惠州市',value:randomData()},
                                 {name: '河源市',value:randomData()},  
                                 {name: '汕尾市',value:randomData()},
                                 {name: '揭阳市',value:randomData()},  
                                 {name: '河源市',value:randomData()},
                                 {name: '梅州市',value:randomData()},  
                                 {name: '潮州市',value:randomData()},
                                 {name: '汕头市',value:randomData()},  
                                 {name: '中山市',value:randomData()},
                                 {name: '珠海市',value:randomData()}  
                             ];   */
            function drawMap(drawMapType,data){
            	 var option = {
                         tooltip: {
//                             show: false //不显示提示标签
                             formatter: '{b}', //提示标签格式
                             backgroundColor:"#ff7f50",//提示标签背景颜色
                             textStyle:{color:"#fff"} //提示标签字体颜色
                         },
                      
                         series: [{
                             type: 'map',
                             mapType: drawMapType,
                             label: {
                                 normal: {
                                     show: true,//显示省份标签
                                     textStyle:{color:"#c71585"}//省份标签字体颜色
                                 },    
                                 emphasis: {//对应的鼠标悬浮效果
                                     show: true,
                                     textStyle:{color:"#800080"}
                                 } 
                             },
                             itemStyle: {
                                 normal: {
                                     borderWidth: .5,//区域边框宽度
                                     borderColor: '#009fe8',//区域边框颜色
                                     areaColor:"#ffefd5",//区域颜色
                                 },
                                 emphasis: {
                                     borderWidth: .5,
                                     borderColor: '#4b0082',
                                     areaColor:"#ffdead",
                                 }
                             },
                             data:data
                         },],
                        
                         /* {  
                             name: 'iphone4',  
                             type: 'map',  
                             mapType: 'china',  
                             itemStyle:{  
                                 normal:{label:{show:true}},  
                                 emphasis:{label:{show:true}}  
                             },  
                             data:[  
                                 {name: '北京',value: randomData()},  
                                 {name: '天津',value: randomData()},  
                                 {name: '上海',value: randomData()},  
                                 {name: '重庆',value: randomData()},  
                                 {name: '河北',value: randomData()},  
                                 {name: '安徽',value: randomData()},  
                                 {name: '新疆',value: randomData()},  
                                 {name: '浙江',value: randomData()},  
                                 {name: '江西',value: randomData()},  
                                 {name: '山西',value: randomData()},  
                                 {name: '内蒙古',value: randomData()},  
                                 {name: '吉林',value: randomData()},  
                                 {name: '福建',value: randomData()},  
                                 {name: '广东',value: randomData()},  
                                 {name: '西藏',value: randomData()},  
                                 {name: '四川',value: randomData()},  
                                 {name: '宁夏',value: randomData()},  
                                 {name: '香港',value: randomData()},  
                                 {name: '澳门',value: randomData()}  
                             ]
                         } */
                         
                     };
            	 echarts.dispose(document.getElementById('china-map'));//先去掉原来的地图 
            	 var myChart = echarts.init(document.getElementById('china-map'));
            	 myChart.setOption(option);
            	 myChart.on('mouseover', function (params) {
                     var dataIndex = params.dataIndex;
                     console.log(params);
                 });
            	 myChart.on("dblclick", function(params) { 
            		 debugger;
            		 drawMap(params.name, data);  
                 }); 
            	/*  myChart.on('onclick', function (params) {//点击事件
                     if (params.componentType === 'series') {
                          var provinceName =params.name;
                          $('#box').css('display','block');
                          $("#box-title").html(provinceName);

                         }
            	 	}); */
            }
           
            window.onload=function(){
            	drawMap('china',mydata);
            }
           
        </script>
    </body>
</body>
</html>