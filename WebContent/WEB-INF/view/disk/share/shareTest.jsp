<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <title>微金时代 | 管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="/WEB-INF/view/head.jsp" %>
  
  <style type="text/css">
  		#mediate{
		  		display: block;
				position: absolute;
				top: 25%;
				left:25%;
				right:25%;}
		.verify-form{
				margin: 0 auto;
				width: 460px;
				height:300px;
				text-align: left;
				font-size: 14px;
				position: relative;
				background: #fff;
				border-radius: 4px;
				box-shadow: 0px 0px 20px 10px rgba(171,198,235,.3);}
		.title{
				background-color: #3c8dbc;
				color: #fff;
				border-top-left-radius: 5px;
				border-top-right-radius: 5px;
				height:100px;}
		.verify-property {
				height: 60px;
				line-height: 60px;
				margin: 10px 0;
				font-size: 14px;
				float:left;
			}
		.userInfo{
				width: 48px;
				height: 48px;
				overflow: hidden;
				/*border-radius: 60px;
				 background-color:#15cac7; */
				background-image:url(<%=request.getContextPath()%>/disk/img/shareTest1.png);
				float:left;
				margin: 20px;
		}
		.ac-close {
				padding: 40px 30px 80px;}
		.verify-input{
				float: left;
				width: 100%;
				color: #666}
		.verify-input .pickpw {
				padding: 0 0 15px;
				color: #666}
		.inputPwd {
				width: 280px;
				border: 1px solid #f2f2f2;
				padding: 8px 10px;
				height: 34px;
				line-height: 19px;
				background: #f2f2f2;
				border-radius: 4px;
				float:left;
			}
		.buttonShare{
				border-radius: 4px;
				margin-left: 18px;
				height: 34px;
				line-height: 34px;
				border: 1px solid #3c8dbc;
				background: #3c8dbc;
				padding-left: 10px;
				float:left;
				width:100px;
				color:#fff;
		}
		.buttonShare:hover{
				
				background: #367fa9;
		}
		.result{
				color:red;
				
		}
		
		.share-title{
		position:fixed; 
		color:#fff;
		float:left;
		text-align: center;
		font-size: 30px;
		left:45%;
		}
  </style>
  
</head>
<body  class="hold-transition skin-blue sidebar-mini fixed">
<div class="wrapper">
 <header class="main-header">

    <!-- Logo -->
    <a href="javascript:void(0);" class="logo hidden-xs">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini" onclick="toWarning()">微金</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg" onclick="toWarning()"><b>微金时代</b> 管理系统</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">切换导航栏</span>
      </a>
      <div class="share-title">
	  	<span>分享中心</span>
	  </div>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <!-- Menu Toggle Button -->
            <a href="/mftccManager/main" class="dropdown-toggle" id="updateUser">
              <span>主页面</span>
            </a>
          </li>
          <li class="dropdown">
            <!-- Menu Toggle Button -->
            <a href="/mftccManager/logout" class="dropdown-toggle">
              <span>退出系统</span>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</div>
 <div id="mediate">
	 <div class="banner"></div>
	 <div class="verify-form">
	 	<form id="verify-pwd" action="<%=request.getContextPath()%>/share/shareTest" method="post">
	 		<div class="title">
		 		<div class="userInfo"></div>
		 		<span class="verify-property">
		 			<strong>${shareUser.opName }</strong>
		 			给你分享了文件
		 		</span>
	 		</div>
	 		<div class="verify-input ac-close ">
	 			<dl class="pickpw ">
		 			<dt>请输入提取密码：</dt></br>
					<dd >
					    <input type="hidden" value="${shareId}" name="shareId"/>
						<input class="inputPwd" id="zawr7PqJ" tabindex="1" type="text" name="sharePwd"/>
						
						<input class="buttonShare" id="verifySubmit" type="submit" value="提取文件"/>
						<!-- <a class="getShare" href="javascript:;"  title="提取文件">提取文件</a> -->
					   
					</dd>
					 <c:if test="${shareResult=='0' }">
					    <p class="result">验证码错误</p>
					  </c:if>
	 			</dl>
	 		</div>
	 	</form>
	 </div>
 </div>
 <%@include file="/WEB-INF/view/bottom.jsp" %>
 <script type="text/javascript">
<%--  $("#verifySubmit").click(function(){
		var mydata = $("#verify-pwd").serialize();
		$.post("<%=request.getContextPath()%>/share/shareTest.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("验证码错误，请重新输入",{time:1500,offset:'30%'});
				return false;
				
			}else{
				
			}
		}, "json");
		return false;
	}); --%>

 </script>
</body>
</html>