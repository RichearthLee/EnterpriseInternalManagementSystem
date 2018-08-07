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
  
  .frame-all {
	background: #edf1f5;
	height: 100%;
	width:100%;
	position: absolute;}
	
	#layoutApp .frame-main {
	width: 980px;
	margin: 0 auto;
	margin-top: 78px;
	position: relative;
	background: #3c8dbc;
	border: 1px solid #dfe7f1;
	border-radius: 5px;
	}
	
	.ShareUser{
	width: auto;
	height:50px;
	text-align: left;
	font-size: 20px;
	color:#fff;
	margin-top:15px;
	margin-left:10px;
	}
	.frame-content {
	width: auto;
	background: #fff;
	/* margin-right: 229px; */
	margin-bottom: 0px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	min-height: 500px;
	height:auto;}
	
	#layoutAside {
	width: 215px;
	margin: 0 5px;
	position: absolute;
	top: 0;
	right: 2px;}
	
	.module-share-header {
	border-bottom: 1px solid #f1f5fb;}
	
	.module-share-file-main {}
	
	.module-share-header .slide-show-header {
	padding: 15px 10px;
	position: relative;}
	
	.slide-show-header {
	padding: 20px 20px 10px;}
	.module-share-header .slide-show-header .slide-show-left {
	float: left;
	*width: 360px;
	margin: 10px 0;}
	
	.module-share-header .slide-show-right {
	float: right;
	margin: 10px 0;
	width: 270px;
	text-align: right;}
	
	.module-share-header .slide-show-other-infos {
	text-align: right;
	margin-top: 70px;
	position: relative;
	z-index: 40;
	*width: 100%;}
	
	.slide-show-other-infos {
	*position: relative;
	*z-index: 2;}
	
	.module-share-top-bar {
	margin-right: -3px;}
	
	.g-button-blue {
	border: 1px solid #3c8dbc;
	background-color: #3c8dbc;
	height: 32px;
	color:#fff;
	font-size: 12px;
	border-radius: 4px;
	line-height: 32px;
	padding-left: 10px;
	float:left;
	
	}
	
	.g-button {
	text-decoration: none;
	color: #3c8dbc;
	border: 1px solid #c0d9fe;
	border-radius: 4px;
	height: 32px;
	line-height: 32px;
	padding-left: 10px;
	font-size: 12px;
	border-radius: 4px;
	float:right;}
	
	.g-button-right {
	position: relative;
	right: -1px;
	display: block;
	height: 29px;
	padding-right: 15px;
	padding-left:32px;
	line-height: 29px;
	text-align: center;
	cursor: pointer;
	*display: inline;
	*line-height: 29px;
	*zoom: 1;}
	
	.save{
	background-repeat:no-repeat;
	background-image:url(<%=request.getContextPath()%>/disk/img/saveShare.png);}
	
	 .download{
	background-repeat:no-repeat;
	background-image:url(<%=request.getContextPath()%>/disk/img/loadSgm.png);}
	
	 .text {
	font-size: 13px;
	margin-left: 2px;
	margin-right: 2px;
	vertical-align: baseline;
	*display: inline;
	*zoom: 1;}
	
   .share-file-info {
	background-image: url(<%=request.getContextPath()%>/disk/img/timeShare.png);
	background-repeat:no-repeat;
	float: left;
	padding-left: 20px;}
	
	.share-valid-check {
	float: left;
	margin-left: 40px;}
	
	.module-share-file-main .share-file-viewer {
	margin-top: 10px;
	margin-bottom: 10px;
	padding: 0 10px;
	position: relative;}
	
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




<div id="layoutApp" class="frame-all">
	<div id="layoutMain" class="frame-main">
	    <div class="ShareUser">
	     分享人:<strong>${shareUser.opName }</strong>
	    </div>
		<div class="frame-content">
			<div class="module-share-header">
				<div class="slide-show-header clearfix">
					<div class="slide-show-left">
						<h5 class="file-name" style="color:#3c8dbc" title="">
							<em></em>
							${diskFile.name}
						</h5>
					</div>
					<div class="slide-show-right">
						<div class="module-share-top-bar g-clearfix">
							<a class="g-button-blue" data-button-id="b1" data-button-index="1" href="javascript:;" title="保存到网盘">
								<span class="g-button-right save">
									<em class="icon icon-save-disk" title="保存到网盘"></em>
									<span class="text" style="width: auto;">保存到网盘</span>
								</span>
							</a>
							<a class="g-button" data-button-id="b3" data-button-index="2" href="<%=request.getContextPath()%>/share/download/${diskFile.id}" title="下载(769KB)">
								<span class="g-button-right download">
									<em class="icon icon-download" title="下载(769KB)"></em>
									<span class="text" style="width: auto;">下载(${diskFile.size })</span>
								</span>
							</a>
						</div>
					</div>
					<div class="slide-show-other-infos">
						<div class="share-file-info">
							<span>${share.shareStTime }</span>
						</div>
						<div class="share-valid-check">失效时间：永久有效</div>
					</div>
				</div>
			</div>
			<div class="module-share-file-main">
				<div class="share-file-viewer"></div>
			</div>
		</div>
		<!-- <div id="layoutAside" class="module-aside aside"></div> -->
	</div>
</div>






 <%@include file="/WEB-INF/view/bottom.jsp" %>
 <script type="text/javascript">
 </script>
</body>
</html>