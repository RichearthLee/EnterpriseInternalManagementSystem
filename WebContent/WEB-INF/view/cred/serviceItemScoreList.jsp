<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title></title>
</head>
<script type="text/javascript">
var pageNumber=10;//页面显示条数
var curPage=1;
var score="";
var itemNo="${basicServiceItems.itemNo}";
$(function(){
	creatPaging(1);
})
//创建分页插件并初始化列表（只有在初始化和点击查询之后调用）
function creatPaging(currPageNo){
	curPage=currPageNo;//更改当前页数
	var ipage ="currPageNo="+currPageNo+"&pageNumber="+pageNumber+"&parm0="+itemNo+"&parm1="+score;
	$.post("<%=request.getContextPath()%>/getServiceScoreListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:currPageNo,
	        backFn:function(p){
// 	        	window.scrollTo(0,0);//滚动条的控制
				document.getElementById("content").scrollTop=0;
	        	creatPaging(p);//存在删除操作时，不使用getdata方法，重新生成翻页插件
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
}
function tbodyInit(data){
	$("#dataContent").empty();
	var htmlstr = "";
	if(data==null||data==''||data.length==0){
		htmlstr="<tr><td align='center'>暂无数据</td></tr>";
	}else{
		$.each(data,function(i,o){
			var temp="";//评分
			if(o.score=="0"||o.score=="1"||o.score=="2"){//差评
				temp="差评："+o.score+"分";
			}else if(o.score=="3"||o.score=="4"){
				temp="中评："+o.score+"分";
			}else if(o.score=="5"){
				temp="好评："+o.score+"分";
			}
			var telHtml="";
			if(o.contact_tel!=null&&o.contact_tel!=""){
				telHtml="<span class='glyphicon glyphicon-earphone'></span>&nbsp;&nbsp;<span>"+o.contact_tel+"</span>";
			}
			htmlstr+="<tr>"
			+"<td width='21%'>" 
			+"<p><span class='glyphicon glyphicon-user'></span>&nbsp;&nbsp;<a class='showCif' href='javascript:void(0)' onclick='showCifInfo(\""+o.cif_account+"\")'>"+o.cif_account+"</a></p>"
			+"</td>"
			+"<td width='79%'>"
			+"<p style='font-size:13px;'>"+o.content+"</p>"
			+"<span style='color: #999;font-size: 10px;float:right;'>"+temp+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+telHtml+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+o.create_date+"</span>"
			+"</td>"
			+"</tr>";
		})
	}
	$("#dataContent").html(htmlstr);
}
function getScoreList(flag){
	score=flag;
	creatPaging(1);
}
function showCifInfo(cifAccount){
	parent.layer.open({
        type: 2,
        title:cifAccount+'客户信息',
        offset:'25px',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['75%' , '93%'],
        content: '<%=request.getContextPath()%>/accountDetailsView?cifAccount='+cifAccount
    });
}
</script>
<style type="text/css">
.showCif{
color:black;
font-size:10px;
}
</style>
<body>
    <section class="content-header">
    <span style="float: right;">好评度:<span style="color: red;"><span style="font-size: 25px;">${grade }</span>%</span></span>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#homeList" data-toggle="tab" onclick='getScoreList("")'>
				全部评价(${scoreCount})
			</a>
		</li>
		<li><a href="#homeList" data-toggle="tab" onclick='getScoreList("2")'>好评( ${hScoreCount })</a></li>
		<li><a href="#homeList" data-toggle="tab" onclick='getScoreList("1")'>中评( ${zScoreCount })</a></li>
		<li><a href="#homeList" data-toggle="tab" onclick='getScoreList("0")'>差评( ${cScoreCount })</a></li>
	</ul>
	</section>
	<section class="content" style="padding-top: 0px;height:416px;overflow: auto;" id="content">
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="homeList">
			<table class="table table-hover">
			<tbody id="dataContent">
			
			</tbody>
			</table>
		</div>
	</div>
	</section>
	 <section class="content-footer" style="background-color: #F8F8F8;">
   <div id="page-div" style="text-align: right;"></div>
	</section>
</body>
</html>