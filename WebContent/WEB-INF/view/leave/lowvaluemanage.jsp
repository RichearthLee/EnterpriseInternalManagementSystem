<%@page import="java.util.Map"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>leaveList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>
<section class="content-header">
    <form id="select-form">
    <div class="col-sm-4">
		
		<button id="addleave" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>添加低值易损品</button></a>
	</div>
	
	<div class="col-sm-4">
	</div>
	
      </form>
	</section>
	<section class="content">
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>类型</th>
					<th>数量</th>
					<th>修改时间</th>
					<th>管理</th>
				
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
	var pageNumber=10;
	$(function(){
		var ipage ="currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getManageListPage.json",ipage,function(data){
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
	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		/* 生成页面信息 */
		$.each(data,function(i,o){
				htmlstr += "<tr>"
					+	"<td>"+o.lowValueName+"</td>"
					+	"<td>"+o.lowValueAmount+"</td>"
					+	"<td>"+o.lowValueDate+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='update(\""+o.lowValueNo+"\");' >管理</a></td>"
					
	
		});
		$("tbody").html(htmlstr);
	};

	
	
	
	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getManageListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	

	
	
	function update(lowValueNo){
		return window.location.href="lowvalueup?lowValueNo="+lowValueNo;
	}
	
	$("#addleave").click(function(){
		window.location.href = "addleave";
	});
		
	</script>
	
	
</body>
</html>