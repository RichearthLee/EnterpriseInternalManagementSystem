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
          <input type="text" name="searchFiled" class="form-control" placeholder="日期">
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
					<th>日期</th> 
					<th>工作时数</th>
					<th>迟到次数</th>
					<th>早退次数</th>
					<th>加班时数</th>
					<th>出差次数</th>
					<th>旷工次数</th>
					<th>工作日统计</th>	
				</tr>
			</thead>
			<tbody>
			
				 <c:forEach items="${attendencelist}" var="attendencelist">
			   <tr>
					<th>${attendencelist.yearMonth }</th>
					<th>${attendencelist.workTimeTotal }</th>
					<th>${attendencelist.lateTimes }</th>
					<th>${attendencelist.eLeaveTimes }</th>
					<th>${attendencelist.sExtraWork }</th>
					<th>${attendencelist.goOut }</th>
					<th>${attendencelist.neglect }</th>
					<th>${attendencelist.attendenceDays }</th>
				</tr>
			  </c:forEach>
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	
	
</body>
</html>
	
