<%@page import="com.mftcc.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mftcc.interior.pact.bean.PactInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/WEB-INF/view/head.jsp" %>
<style type="text/css">
.form-control{
	width: 130px;
    display: inline-block;
}
</style>
</head>
<body >
	<section class="content">
	<div class="row">
    <form class="form-horizontal" id="select-form">
    	<div class="box-body" style="padding-left: 10px;padding-bottom: 0px;padding-right: 10px;padding-top: 0px;">
    		<div class="form-group" style="margin-left: 0px;margin-right: 0px;min-width: 910px;">
    			
                	<label class="control-label">产品类别</label>
    				<select class="form-control input-sm"  name="parm0">
    					<option value="">全部</option>
						<option>小贷</option>
						<option>担保</option>
						<option>p2p</option>
						<option>小贷+移动端</option>
						<option>p2p+移动端</option>
						<option>定制化小贷</option>
						<option>定制化p2p</option>
						<option>源代码</option>
					</select>
    				&#12288;
    		
                  	<label class=" control-label">查询内容</label>
    				<input type="text" class="form-control input-sm"  name="parm1" placeholder="客户名称" >
    				&#12288;
    			
	    			<label class="control-label">签约时间</label>
	    			<input type="text" class="form-control dateSelect input-sm" style="width: 90px;"  name="parm2" placeholder="开始时间">
	    			<label class="control-label">—</label>
	    			<input type="text" class="form-control dateSelect input-sm" style="width: 90px;" name="parm3" placeholder="结束时间">
    				&#12288;
    			
    				<button type="submit" class="btn btn-default" id="select-btn">开始查询</button> 
    				&#12288;&#12288;
    			
    				 <button  type="button" class="btn btn-default" onclick="onExportPact();"><span class="glyphicon glyphicon-export" ></span>导出记录</button>
    			
    		</div>
    	</div>
      </form>
      </div>

	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>产品类别</th>
					<th>签约时间</th>
					<th>合同金额</th>
					<th>客户返款金额</th>
					<th>已收金额</th>
					<th>收款比例</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
			<tfoot>
			
			</tfoot>
		</table>
		<div id="page-div" style="text-align: right;"></div>
	</div>
	
	</section>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<script>
	var pageNumber=9;
	
	function tbodyInit(data){
		var allPactFee = 0;
		var allPactfeeYet = 0;
		var allCustomerMoney = 0;
		var allPercent = '';
		$("tbody").empty();
		$("tfoot").empty();
		var htmlstr = "";
		$.each(data,function(i,o){
			allPactFee = allPactFee + parseFloat(o.pactFee);
			allPactfeeYet = allPactfeeYet + parseFloat(o.payfeeYet);
			allCustomerMoney += parseFloat(o.customerMoney);
			htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.productName+"</td>"
					+	"<td>"+o.pactDate+"</td>"
					+	"<td>"+o.pactFee+"</td>"
					+	"<td>"+o.customerMoney+"</td>"
					+	"<td>"+o.payfeeYet+"</td>"
					+	"<td>"+o.paypercent+"%</td>"
					+	"</tr>";
		});
		$("tbody").html(htmlstr);
		if(allPactFee == 0){
			allPercent = '100%';			
		}else{
			allPercent = (allPactfeeYet*100/(allPactFee - allCustomerMoney) + allPercent).substr(0,5) + '%';
		}
		htmlstr = "<tr>"
			+	"<td style='font-weight: bold;color: royalblue;'>统计</td>"
			+	"<td></td>"
			+	"<td></td>"
			+	"<td style='font-weight: bold;color: royalblue;'>"+allPactFee+"</td>"
			+	"<td style='font-weight: bold;color: royalblue;'>"+allCustomerMoney +"</td>"
			+	"<td style='font-weight: bold;color: royalblue;'>"+allPactfeeYet+"</td>"
			+	"<td style='font-weight: bold;color: royalblue;'>"+allPercent +"</td>"
			+	"</tr>";
		$("tfoot").html(htmlstr); 
	};
	$(function(){
		$(".dateSelect").datepicker();
		var ipage = $("#select-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getPactStaPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			
		},"json");
	});
	function getdata(page){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getPactStaPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
			
		},"json");
	};
	$("#select-form").submit(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getPactStaPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
		},"json");
		return false;
	});
	
	function onExportPact(){
		var index = layer.open({
			content : "正在导出合同信息，请勿关闭或刷新页面"
		});
		var ipage = $("#select-form").serialize();
		$.post("<%=request.getContextPath()%>/exportExcelPactStatic.json",ipage,function(data){
			layer.close(index);
			if(data.errorcode == 'error'){
				layer.msg("导出合同信息出错",{time:1500});
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

