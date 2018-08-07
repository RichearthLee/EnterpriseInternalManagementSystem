<%@page import="com.mftcc.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mftcc.interior.pact.bean.listBean.TichengPactBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/WEB-INF/view/head.jsp" %>
<style type="text/css">
	.textright{
		/* text-align: right; */
	}
	.textcenter{
		/* text-align: center; */
	}
	.form-control{
		width: 90px;
    	display: inline-block;
    	background: white;
	}
</style>
</head>
<body >
<section class="content">
<div id="select-div">
    <form class="form-horizontal" id="select-form">
    		<div class="form-group" style="margin-left: 0px;margin-right: 0px;margin-bottom: 0px;min-width: 910px;">
    				<label class="control-label"  style="margin-left: 300px;">选择日期</label>
	    			<input type="text" class="form-control dateSelect input-sm" style="width: 90px;background: white;" id="parm0" name="parm0" placeholder="开始日期" readonly="readonly">
	    			<label class="control-label">—</label>
	    			<input type="text" class="form-control dateSelect input-sm" style="width: 90px;background: white;" id="parm1" name="parm1" placeholder="结束日期" readonly="readonly">
    				&#12288;
    				<button type="button" class="btn btn-default" id="select-btn">开始查询</button> 
    				&#12288;&#12288;
    			<button  type="button" class="btn btn-default" onclick="exportTichengPact()"><span class="glyphicon glyphicon-export" ></span>导出记录</button>
    	    </div>
      </form>
 </div>
	<div id="showTable-div" style="overflow-x:auto;">
		<table style="min-width: 1620px;" class="table table-hover">
			<thead>
				<tr>
					<th class="textcenter" style="width: 200px;">客户名称</th>
					<th class="textcenter" style="width: 90px;">签约时间</th>
					<th class="textcenter" style="width: 90px;">合同金额</th>
					<th class="textcenter" style="width: 110px;">客户返款金额</th>
					<th class="textcenter" style="width: 104px;">实际已收金额</th>
					<th class="textcenter" style="width: 110px;">最近付款金额</th>
					<th class="textcenter" style="width: 110px;">最近付款时间</th>
					<th class="textcenter" style="width: 80px;">付款比例</th>
					<th class="textcenter" style="width: 110px;">达到80%时间</th>
					<th class="textcenter" style="width: 120px;">达到100%时间</th>
					<th class="textcenter" style="width: 80px;">售前人员</th>
					<th class="textcenter" style="width: 80px;">金额</th>
					<th class="textcenter" style="width: 80px;">售前人员</th>
					<th class="textcenter" style="width: 80px;">金额</th>
					<th class="textcenter" style="width: 80px;">售后人员</th>
					<th class="textcenter" style="width: 80px;">金额</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<!-- <div id="page-div" style="text-align: right;"></div> -->
	</div>
	</section>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<script>
var pageNumber=10;
$(function(){
	$("#parm0").val('${dateNow}');
	$("#parm1").val('${dateNow}');
	$(".dateSelect").datepicker({minViewMode:1,format:"yyyy-mm"});
	var height = $(window).height();
	height = height - $("#select-div").outerHeight() - 30;
	$("#showTable-div").css('min-height', height);
	$(window).on("resize", function(){
		var newheight = $(window).height()- $("#select-div").outerHeight() - 30;
		$("#showTable-div").css('min-height', height);
	});
	
	var ipage = $("#select-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post("<%=request.getContextPath()%>/getTichengQuarterPactList.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
	},"json");
});

function tbodyInit(data){
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		htmlstr += "<tr>"
				+	"<td>"+o.customerName+"</td>"
				+	"<td>"+o.pactDate+"</td>"
				+	"<td>"+o.pactFee+"</td>"
				+	"<td>"+o.customerMoney+"</td>"
				+	"<td>"+o.payfeeYet+"</td>"
				+	"<td>"+o.lastMoney+"</td>"
				+	"<td>"+o.lastDate+"</td>"
				+	"<td>"+o.paypercent+"%</td>"
				+	"<td>"+o.date80Percent+"</td>"
				+	"<td>"+o.date100Percent+"</td>"	
				+	"<td>"+o.beforesale1+"</td>"
				+	"<td>"+o.beforesaleTicheng1+"</td>"
				+	"<td>"+o.beforesale2+"</td>"
				+	"<td>"+o.beforesaleTicheng2+"</td>"
				+	"<td>"+o.aftersale+"</td>"
				+	"<td>"+o.aftersaleTicheng+"</td>"
				+	"</tr>";
	});
	$("tbody").html(htmlstr);
};

$("#select-btn").click(function(){
	var startdate = $("#parm0").val(); 
	var enddate = $("#parm1").val();
	//开始时间大于结束时间时
	if(startdate > enddate){
		layer.msg('请选择正确的日期间隔',{time:2500,offset:'100px'});
		return;
	}
	var ipage = $("#select-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post("<%=request.getContextPath()%>/getTichengQuarterPactList.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
	},"json");
});

function exportTichengPact(){
	var index = layer.open({
		content : "正在导出合同信息，请勿关闭或刷新页面"
	});
	var ipage = $("#select-form").serialize();
	$.post("<%=request.getContextPath()%>/exportTichengQuarterPact.json",ipage,function(data){
		layer.close(index);
		if(data.errorcode == 'error'){
			layer.msg("导出信息出错",{time:1500});
		}else if(data.errorcode == '0'){
			layer.msg("不存在符合条件的记录",{time:1500});	
		}else {
			window.open("<%=request.getContextPath()%>/download?filePath="+data.destFile);
		}
	},"json");
};
</script>
</body>
</html>

