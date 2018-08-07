<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mftcc.method.bean.methodBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th style="min-width: 100px;">方法名称</th>
					<th style="min-width: 100px;">请求路径</th>
					<th style="min-width: 90px;">状态</th>
					<th style="min-width: 50px;">操作</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;"></div>
</section>
		<%@include file="/WEB-INF/view/bottom.jsp" %>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/pactManager/interfaceTest.js"></script> 
	 <script type="text/javascript">
	 $(function(){
			var contextPath1 = '<%=request.getContextPath()%>';
			InitPactList(contextPath1);
		});
	
	 </script>
		
</body>
</html>