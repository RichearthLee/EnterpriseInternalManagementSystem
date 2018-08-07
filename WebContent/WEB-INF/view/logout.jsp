<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>微金时代 | 您已安全登出</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@include file="head.jsp"%>
<script type="text/javascript">
function logout(){
	// 为了兼容“/mftccManager”和“/”直接设定为根目录的情况。
	window.location.href=window.location.origin+'<%=request.getContextPath() %>';
}
</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="/mftccManager"><b>微金时代</b>内部管理系统</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">您已安全登出，可以关闭浏览器</p>

			<div class="row">
				<div class="col-xs-4"></div>
				<!-- /.col -->
				<div class="col-xs-4">
					<a href="javascript:void(0);" onclick="logout();"
						class="btn btn-block btn-flat">重新登录</a>
				</div>
				<!-- /.col -->
			</div>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<%@include file="bottom.jsp"%>
</body>
</html>
