<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@ page import="com.mftcc.interior.oa.leave.bean.LeaveBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/view/head.jsp" %>

</head>

<body>
<section class="content">
	<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		
		<div class="box-body">
		
			
				 
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="submit"
					class="btn btn-success pull-center" style="width: 200px;">请假申请</button>
				<label>&#12288;</label>
				
			</div>
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="submit1"
					class="btn btn-success pull-center" style="width: 200px;">低值易损品申请</button>
				<label>&#12288;</label>
				
			</div>
		</div>
	</div>	
	</form>
	
	</section>
	
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	$("#submit").click(function(){
		window.location.href = "listleave";
	});
	$("#submit1").click(function(){
		window.location.href = "listlowvalue";
	}); 
	
	
</script>
</body>
</html>