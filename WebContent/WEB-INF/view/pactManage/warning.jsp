<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.mftcc.interior.sys.bean.SysUser"%>
<%@  page import="com.mftcc.interior.pact.bean.RoleBean"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/WEB-INF/view/head.jsp" %>
<link href="<%=request.getContextPath()%>/plugins/mftccManager/css/portal_index.css" rel="stylesheet">
</head>
<body style="background-color: #ecf0f5;">
	<section class="content">
		<c:if test="${not empty payProgressCount }">
		<li class="block" id="block_1" title="款项确认" index="16" onclick="toPayProgressList()"
			style="margin-left: 6px; margin-right: 6px;">
			<div class="img">
				<p>
					<img src="<%=request.getContextPath() %>/plugins/mftccManager/shareimg/waittodo/16.png">
				</p>
				<div class="count count2" id="count_1">
					<table cellspacing="0">
						<tbody>
							<tr>
								<td class="count_left"></td>
								<td class="count_center">${payProgressCount }</td>
								<td class="count_right"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<a class="icon-text" href="javascript: void(0)"><span>款项确认</span>
			</a>
		</li>
		</c:if>
		<c:if test="${not empty pactInvoiceCount}">
		<li class="block" id="block_2" title="发票确认" index="16" onclick="toPactInvoiceList()"
			style="margin-left: 6px; margin-right: 6px;">
			<div class="img">
				<p>
					<img src="<%=request.getContextPath() %>/plugins/mftccManager/shareimg/waittodo/41.png">
				</p>
				<div class="count count2" id="count_2">
					<table cellspacing="0">
						<tbody>
							<tr>
								<td class="count_left"></td>
								<td class="count_center">${pactInvoiceCount }</td>
								<td class="count_right"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<a class="icon-text" href="javascript: void(0)"><span>发票确认</span>
			</a>
		</li>
		</c:if>
		<c:if test="${not empty pactExecuteCount }">
		<li class="block" id="block_3" title="实施确认" index="16" onclick="toPactExecute()"
			style="margin-left: 6px; margin-right: 6px;">
			<div class="img">
				<p>
					<img src="<%=request.getContextPath() %>/plugins/mftccManager/shareimg/waittodo/52.png">
				</p>
				<div class="count count2" id="count_3">
					<table cellspacing="0">
						<tbody>
							<tr>
								<td class="count_left"></td>
								<td class="count_center">${pactExecuteCount }</td>
								<td class="count_right"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<a class="icon-text" href="javascript: void(0)"><span>实施确认</span>
			</a>
		</li>
		</c:if>
		<c:if test="${not empty pactCount }">
		<li class="block" id="block_4" title="收款逾期合同" index="16" onclick="toPactList()"
			style="margin-left: 6px; margin-right: 6px;">
			<div class="img">
				<p>
					<img src="<%=request.getContextPath() %>/plugins/mftccManager/shareimg/waittodo/13.png">
				</p>
				<div class="count count2" id="count_3">
					<table cellspacing="0">
						<tbody>
							<tr>
								<td class="count_left"></td>
								<td class="count_center">${pactCount }</td>
								<td class="count_right"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<a class="icon-text" href="javascript: void(0)"><span>收款逾期合同</span>
			</a>
		</li>
		</c:if>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript">
	function toPayProgressList(){
		window.location.href = contextPath +"/payProgress/payprogressList";		
	};
	
	function toPactInvoiceList(){
		window.location.href = contextPath +"/pactInvoice/getInvoiceListView";	
	};
	
	function toPactExecute(){
		window.location.href = contextPath + "/pactexecute";	
	};
	
	function toPactList(){
		window.location.href = contextPath+ "/pactListInfo?requestId=2";	
	};
	</script>
</body>
</html>
