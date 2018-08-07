<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/report/include1/common.jsp"%>
<%@ include file="/WEB-INF/view/report/include1/pub_view_table.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Insert title here</title>
<!-- <script type="text/javascript">
	$(function() {
		myCustomScrollbar({
			obj : "#content",//页面内容绑定的id
			url : "MfBusApplyActionAjax_findApprovalStsListByPageAjax.action",//列表数据查询的url
			tableId : "tableapprovalstsquerylist",//列表数据查询的table编号
			tableType : "thirdTableTag",//table所需解析标签的种类
			pageSize : 30//加载默认行数(不填为系统默认行数)
		});
	});
	function getDetailPage(obj,url){		
		top.LoadingAnimate.start();		
		window.location.href=url;			
	};
	
</script> -->
</head>
<body class="overflowHidden">
	<div class="container">
		<div class="row clearfix bg-white tabCont">
			<div class="col-md-12 column">
				<div class="btn-div top-title">员工请假查询</div>
				<div class="search-div">
					<jsp:include page="/WEB-INF/view/report/include1/mySearch.jsp"/>
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div id="content" class="table_content"  style="height: auto;">
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/report/include1/PmsUserFilter.jsp"%>
<!-- 	<script type="text/javascript">
	filter_dic =[{
					"optCode":"applySts",
					"optName":"申请状态",
					"parm":[{"optName":"未提交","optCode":"0"},
							{"optName":"审批中","optCode":"1"},
							{"optName":"审批通过","optCode":"2"},
							{"optName":"退回","optCode":"3"},
							{"optName":"通过","optCode":"4"},
							{"optName":"否决","optCode":"5"}],
					"dicType":"y_n"}];
	</script> -->
</body>
<!-- <script type="text/javascript">
	/*我的筛选加载的json*/
	filter_dic = [ {
		"optName" : "申请金额",
		"parm" : [],
		"optCode" : "appAmt",
		"dicType" : "num"
	},{
		"optName" : "登记日期",
		"parm" : [],
		"optCode" : "appTime",
		"dicType" : "date"
	},{
		"optName" : "审批状态",
		"parm" : ${appStsJsonArray},
		"optCode" : "appSts",
		"dicType" : "y_n"
	}, {
		"optName" : "产品种类",
		"parm" : ${kindTypeJsonArray},
		"optCode" : "kindNo",
		"dicType" : "y_n"
	}];
</script> -->
</html>