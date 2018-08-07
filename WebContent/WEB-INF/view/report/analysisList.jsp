<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<div class="btn btn-app" onclick="orgPercent()">
			<img alt="客户区域分布" src="<%=request.getContextPath()%>/report1/image/zhankai.png" />
			<div>客户区域分布</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function orgPercent(){
	window.location.href="orgPercent";
}
</script>
</html>