<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>微金时代 | 欢迎您登录内部管理系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport"content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
<link rel="stylesheet"  href="<%=request.getContextPath()%>/AdminLTE/login/css/login.css"/>
<%-- <%@include file="/WEB-INF/view/head.jsp" %> --%>

</head>
<body class="login" mycollectionplug="bind">
		<div class="login_m">
			<div class="login_logo"><img src="<%=request.getContextPath()%>/AdminLTE/login/img/logo6.png" ></div>
			<div class="login_boder">
				<form id="form">
					<div class="login_padding" id="login_model">
						<h2>用户名</h2>
						<label>
	          	<input type="text" id="opNo" class="txt_input txt_input2" placeholder="手机号码">
	         </label>
						<h2>密码</h2>
						<label>
	    				<input type="password" name="password" id="password" class="txt_input" placeholder="密码" >
	  				</label>
	
						<p class="forgot">
							<a id="iforget" href="javascript:void(0);">忘记密码</a>
						</p>
						<div class="rem_sub">
							<div class="rem_sub_l">
								<input type="checkbox" name="checkbox" id="save_me">
								<label for="checkbox">记住密码</label>
							</div>
							<label>
	      				<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
	    				</label>
						</div>
					</div>
				</form>
			</div>
			
</body>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<!-- iCheck - plugins -->
<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
 	<script src="<%=request.getContextPath()%>/plugins/md5/jQuery-md5.js"></script>
	<script>
		$(function( ) {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			$("#form").submit(function(){
				var layerIndex = layer.load(0, {
					time: 2000
				});
				var opNo, password;
				opNo = $("#opNo").val();
				password = $("#password").val();
				
  				if ($.trim(password)) {
 					password = $.md5(password);
 				}
				$.post("checkLogin.json", {
					"opNo" : opNo,
					"password" : password
				}, function(data) {
					layer.close(layerIndex);
					if (data.flag=="fail") {
						if(data.msg==null){data.mes="非法登录"}
						layer.alert(data.msg,{title:"提示"});
						return;
					}
					
		 			if(data.flag=="success"){
		 				
		 				window.location.href = "main";}
						}, "json");
						
						return false;	// 阻止表单自身的提交
			});
			
			
			
		});
	</script>

</html>
