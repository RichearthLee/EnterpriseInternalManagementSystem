<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>search</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/css/PssParamEntrance.css" />
<style type="text/css">
	body {
			background-color: #F9F9F9;
			margin:0; padding:0;
			}
</style>
</head>
<body>
 	<div id="base" class="">
		<div class="btn btn-app" onclick="employeeLeaveReport()">
			<img alt="员工请假查询" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>员工请假查询</div>
		</div>
		<div class="btn btn-app" onclick="cusInfoReport()">
			<img alt="客户基本信息查询" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>客户基本信息查询</div>
		</div>
		
		<div class="btn btn-app" onclick="pactEndWarning()">
			<img alt="合同到期预警" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>合同到期预警</div>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      <div id="u4" class="ax_default label">
          <p><a href="<%=request.getContextPath()%>/PmsUserFilter">客户基本信息查询</a></p>
      </div>
      <div id="u5" class="ax_default label">
        <div id="u5_text" class="text ">
          <p>
          	<a href="<%=request.getContextPath()%>/countLeaveDay">提取计算员工请假数据 </a></br>
          	<a href="<%=request.getContextPath()%>/cusReport/selectCusAndLinkMan">提取客户数据</a></br>
          	<a href="<%=request.getContextPath()%>/pactReport/selectPactRuarter">合同季度数据提取</a></br>
          	<a href="<%=request.getContextPath()%>/pactReport/exchangePactInfo">合同</a></br></br>
			
          </p>
        </div>
      </div>
    </div>
</body>
<script type="text/javascript">
function employeeLeaveReport(){
	window.location.href="employeeReport/employeeLeaveReport";
}
function cusInfoReport(){
	window.location.href="cusReport/cusInfoReport";
}
function pactEndWarning(){
	window.location.href="pactReport/pactEndWarning";
}



function newFile(){
	window.location.href="newFile";
}
</script>
</html>