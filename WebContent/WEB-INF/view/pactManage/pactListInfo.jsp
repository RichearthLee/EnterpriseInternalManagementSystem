<%@page import="java.util.List"%>
<%@page import="com.mftcc.interior.pact.bean.PactListInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pactFullinfo</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>

<section class="content-header">
	<div class="col-sm-4">
		<button id="newpact" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增合同</button>
		<button id="newcustomer" type="button" class="btn btn-info" style="display: none;"><span class="glyphicon glyphicon-plus"></span>新增客户</button>
	</div>
	<div class="col-sm-2">
	</div>
	<form id="selectPact-form">
	<div class="col-sm-2">
		<select name="softType" id="softType" onchange="selectPact()" class="form-control">
			<option value="">全部</option>
			<option value="1">收款逾期合同</option>
		</select>
	</div>
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="客户名称、产品、客户经理">
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
					<th>客户名称</th>
					<th style="min-width: 100px;">联系人</th>
					<th style="min-width: 100px;">产品类别</th>
					<th style="min-width: 90px;">合同金额</th>
					<th style="min-width: 90px;">支付进度</th>
					<th style="min-width: 90px;">签约时间</th>
					<th style="min-width: 90px;">客户经理</th>
					<th style="min-width: 50px;">操作</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/pactManager/pactList.js"></script>
	<script>
	$(function(){
		var requestId = '${requestId}';
		if(requestId == '2'){
			$("#softType").val(1);
		}
		var contextPath1 = '<%=request.getContextPath()%>';
		InitPactList(contextPath1);
	});

	</script>
</body>
</html>