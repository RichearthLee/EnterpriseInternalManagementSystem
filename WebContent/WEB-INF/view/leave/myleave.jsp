<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>myleave</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<section class="content-header">
	<form id="selectPact-form">
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="员工姓名">
              <span class="input-group-btn">
                <button type="submit" name="search"  class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
</section>

<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>月份</th> 
					<th>无薪请假总时长</th>
					<th>总请假时长</th>	
				</tr>
			</thead>
			<tbody>
			
			   <c:forEach items="${countlist }" var="countlist">
			   <tr>
					<th>${countlist.yearmonth }</th>
					<th>${countlist.sumdays }</th>
					<th>${countlist.sumdays }</th>
				</tr>
			  </c:forEach>
			
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<%-- <script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script> --%>

	
</body>
</html>
	
