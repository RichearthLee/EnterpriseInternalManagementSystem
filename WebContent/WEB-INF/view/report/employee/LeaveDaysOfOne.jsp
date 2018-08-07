<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工请假信息列表</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>


<section class="content">
		<table class="table table-hover">
		<%-- 当前登陆用户${sessionScope.user.userNo} --%>
			<thead>
				<tr>
					<th></th>
					<th style="min-width: 50px;">请假类型</th>
					<th style="min-width: 50px;">请假起始时间</th>
					<th style="min-width: 50px;">请假结束时间</th>
					<th style="min-width: 20px;">请假时长</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${leaveBeans}" var="leaveBeans">
					<tr>
						<th></th>
						<th style=" font-size:14px;font-weight: normal;">${leaveBeans.leaveType}</th>
						<th style=" font-size:14px;font-weight: normal;">${leaveBeans.leaveBeginTime}</th>
						<th style=" font-size:14px;font-weight: normal;">${leaveBeans.leaveEndTime}</th>
						<th style=" font-size:14px;font-weight: normal;">${leaveBeans.leaveSum}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
</body>
</html>
