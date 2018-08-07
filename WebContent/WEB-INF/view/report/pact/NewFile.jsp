<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/js/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/js/highcharts-more.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/js/jquery-1.11.2.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
</div>
	<script type="text/javascript">
		$(function(){ chart = new Highcharts.Chart({
            chart: {
                renderTo: 'tu3',
				borderWidth:3,
				borderColor:'#E5E5E5',
				borderRadius:0
				
            },
			credits:{
				enabled:false
			},
            title: {
                text: '贷款金额统计表',
                style:{color:'#3d4750',fontSize:'14px',fontWeight:'bold'}
            },
            legend:{
            	itemStyle:{color: '#696969',
							fontWeight: 'bold',
							fontSize: '12px',
							fontFamily: '微软雅黑'
            		}
            },
            xAxis: {
				gridLineWidth: 1,
				lineColor: '#000',
				tickColor: '#000',
                categories: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
				// x轴坐标文本
				labels: {
					style: {
						color: '#3d4750',
						fontWeight: 'bold',
						fontSize: '12px',
						fontFamily: '微软雅黑'
					}
				}
				
			},
			yAxis: {
				minorTickInterval: 'auto',
				lineColor: '#000',
				lineWidth: 0.5,
				gridLineWidth: 1,
				lineWidth: 1,
				title: {
					style: {
						color: '#696969',
						fontWeight: 'bold',
						fontSize: '12px',
						fontFamily: '微软雅黑'
					},
			 		text: '金额(万元)'
				}
       		},
	        tooltip: {
		        formatter: function() {
					var s;
					if (this.point.name) { // the pie chart
						s = ''+this.point.name +': '+ this.y +' fruits';
					} else {
						s = ''+this.x  +': '+ this.y;
					}
						return s;
                }
            },
            series: [{
     	       type: 'column',
               name: '余额',
               data: [3, 2, 1, 3, 4,7,8,9,11,5,3,6]
            	}, 
				{
                type: 'spline',
                name: '新增',
                data: [3, 2.67, 3, 6.33, 3.33,3.01,5,4.2,6,2.2,4.1,3.3],
                marker: {
                	lineWidth: 2,
                	lineColor: Highcharts.getOptions().colors[3],
                	fillColor: 'white'
                }
            }]
        });})
    </script>
</body>
</html>