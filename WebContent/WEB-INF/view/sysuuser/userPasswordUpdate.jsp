<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.mftcc.interior.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body class="hold-transition login-page">
	
	<div class="login-box">
		<div class="login-logo">
			
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<!-- <p class="login-box-msg">请登录您的账户</p> -->

			<!-- <form id="form"> -->
				
				<div class="form-group has-feedback">
					<label>输入原密码</label>
					<input type="password" id="oldpassword" class="form-control" placeholder="原密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label>输入新密码</label>
					<input type="password" id="newpassword" name="password" class="form-control" placeholder="新密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label>输入新密码</label>
					<input type="password" id="newpassword1" class="form-control" placeholder="新密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<%-- <input name="userNo" value="<%=user.getUserNo()%>" style="display: none;"> --%>
				<div class="row">
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="button" class="btn btn-primary btn-block btn-flat" id="submit">确定</button>
					</div>
					<!-- /.col -->
				</div>
			<!-- </form> -->
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<!-- iCheck - plugins -->
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
		$("#submit").click(function(){
			var oldpassword = $("#oldpassword").val();
			var newpassword = $("#newpassword").val();
			var newpassword1 = $("#newpassword1").val();
			if(oldpassword == "" || newpassword == "" || newpassword1=="")
			{
				layer.alert("请输入密码");
				return false;
			}
			if(newpassword != newpassword1)
			{
				layer.alert("两次新密码不匹配");
				return false;
			}
			//alert(oldpassword);
			 //var mydata = $("#form").serialize();
			 $.post("UpdateUserPassword.json", "oldpassword="+encodeURIComponent(oldpassword) + "&newpassword="+encodeURIComponent(newpassword), function(data) {
			    	if (data.errorcode == 0) {
			    		layer.alert("修改密码失败");
						return false;
					}else if(data.errorcode == 2){
						layer.alert("原密码输入不正确");
					}else{
						layer.alert("修改成功，下次登录请使用新密码",function(){
							 var index = parent.layer.getFrameIndex(window.name);
//							 parent.window.location.reload();
		 					 parent.layer.close(index);
						});
						
					}
				}, "json");
		});
	</script>
</body>
</html>
