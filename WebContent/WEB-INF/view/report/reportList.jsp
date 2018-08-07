<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	body {
		background-color: #F9F9F9;
		margin:0; padding:0;
	}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/css/PssParamEntrance.css" />
</head>
<body>
	<div>
		<div class="btn btn-app" onclick="pactUserQuarterNumReport()">
			<img alt="员工合同签约季度表" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>员工合同签约季度表</div>
		</div>
		<div class="btn btn-app" onclick="employeeLeaveReport()">
			<img alt="合同明细表" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>合同明细表</div>
		</div>
		<div class="btn btn-app" onclick="employeeLeaveReport()">
			<img alt="业务经营月统计报表" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>业务经营月统计报表</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function pactUserQuarterNumReport(){
	window.location.href="pactReport/pactUserQuarterNumReport";
}
</script>
</html>