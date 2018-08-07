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
	width: 170px;
    display: inline-block;
}
</style>
</head>
<body >
<section class="content">
	<div class="row">
    <form class="form-horizontal" id="select-form">
    	<div class="box-body" style="padding-left: 10px;padding-bottom: 0px;padding-right: 10px;padding-top: 0px;">
    		<div class="form-group" style="margin-left: 0px;margin-right: 0px;min-width: 930px;">
    			
                <label class="control-label" style="margin-left: 140px;">查询内容</label>
               
    			<input type="text" class="form-control input-sm"  name="parm0" placeholder="客户名称、客户经理" >
    			
    			
	    		<label class="control-label" style="margin-left: 50px;">收款截止时间</label>
	    		
	    		<input type="text" class="form-control dateSelect input-sm" name="parm1" placeholder="收款时间">
    			
    			&#12288;
    			<button type="submit" class="btn btn-default" id="select-btn">开始查询</button> 
    			&#12288;&#12288;
    			<button  type="button" class="btn btn-default" onclick="onExportPactWarning();"><span class="glyphicon glyphicon-export" ></span>导出记录</button>
    			
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
					<th>合同金额</th>
					<th>计划收款比例</th>
					<th>收款截止时间</th>
					<th>客户经理</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		
		</table>
		<div id="page-div" style="text-align: right;"></div>
	</div>
	
	</section>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<script src="<%=request.getContextPath()%>/js/pactManager/pactWarning.js"></script>
<script>
	$(function(){
		$(".dateSelect").datepicker();
		var path1 = '<%=request.getContextPath()%>';
		InitPactWarning(path1);
	});

</script>
</body>
</html>

