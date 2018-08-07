<%@page import="com.mftcc.interior.sys.bean.*"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, List<SysMenu>> menuMap = (Map<String, List<SysMenu>>) request.getAttribute("menuMap");
SysUser user = (SysUser) request.getSession().getAttribute("sysuser");
%>
<%!
private String outMenuHtml(SysMenu menu, Map menuMap){
	StringBuffer strb = new StringBuffer();
	
	String rightIconHtml = "";
	String leftIconHtml = "<i class=\"fa fa-list-ul\"></i> ";
	String liClass = "";
	
	StringBuffer childHtml = new StringBuffer();
	List<SysMenu> childMenuList = (List<SysMenu>) menuMap.get(menu.getMenuNo());
	if (childMenuList != null) {
		rightIconHtml = "<i class=\"fa fa-angle-left pull-right\"></i>";
		leftIconHtml = "<i class=\"fa fa-link\"></i> ";
		liClass = StringUtil.isEmpty(menu.getMenuParent()) ? "treeview" : "";
		childHtml.append("<ul class=\"treeview-menu\">");
		for (SysMenu childMenu : childMenuList) {
			// 注意递归调用。
			childHtml.append(outMenuHtml(childMenu, menuMap));
		}
		childHtml.append("</ul>");
	}
	strb.append("<li class=\"" + liClass + "\">");
	strb.append("<a href=\"javascript:void(0);\" onclick=\"javascript:loadUrl('" + StringUtil.killNull(menu.getMenuUrl()) + "');\">");
	strb.append(leftIconHtml + "<span>" + menu.getMenuName() + "</span>" + rightIconHtml +"</a>");
	strb.append(childHtml);
	strb.append("</li>");
	
	return strb.toString();
}
%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>微金时代 | 管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="head.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="javascript:void(0);" class="logo hidden-xs">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini" onclick="toWarning()">微金</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg" onclick="toWarning()"><b>微金时代</b> 管理系统</span>
    </a>



 <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Navbar Right Menu -->
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- Messages: style can be found in dropdown.less-->
        <li class="dropdown messages-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-envelope-o"></i>
            <span class="label label-success">4</span>
          </a>
          <ul class="dropdown-menu">
            <li class="header">You have 4 messages</li>
            <li>
              <!-- inner menu: contains the actual data -->
              <ul class="menu">
                <li><!-- start message -->
                  <a href="#">
                    <div class="pull-left">
                      <img src="/mftccManager/AdminLTE/img/user1-128x128.jpg" class="img-circle" alt="User Image">
                    </div>
                    <h4>
                      Sender Name
                      <small><i class="fa fa-clock-o"></i> 5 mins</small>
                    </h4>
                    <p>Message Excerpt</p>
                  </a>
                </li><!-- end message -->
                ...
              </ul>
            </li>
            <li class="footer"><a href="#">See All Messages</a></li>
          </ul>
        </li>
        <!-- Notifications: style can be found in dropdown.less -->
        <li class="dropdown notifications-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-bell-o"></i>
            <span class="label label-warning">10</span>
          </a>
          <ul class="dropdown-menu">
            <li class="header">You have 10 notifications</li>
            <li>
              <!-- inner menu: contains the actual data -->
              <ul class="menu">
                <li>
                  <a href="#">
                    <i class="ion ion-ios-people info"></i> Notification title
                  </a>
                </li>
                ...
              </ul>
            </li>
            <li class="footer"><a href="#">View all</a></li>
          </ul>
        </li>
        <!-- Tasks: style can be found in dropdown.less -->
        <li class="dropdown tasks-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-flag-o"></i>
            <span class="label label-danger">9</span>
          </a>
          <ul class="dropdown-menu">
            <li class="header">You have 9 tasks</li>
            <li>
              <!-- inner menu: contains the actual data -->
              <ul class="menu">
                <li><!-- Task item -->
                  <a href="#">
                    <h3>
              		       设计按钮
                      <small class="pull-right">20%</small>
                    </h3>
                    <div class="progress xs">
                      <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                        <span class="sr-only">20% Complete</span>
                      </div>
                    </div>
                  </a>
                </li><!-- end task item -->
                ...
              </ul>
            </li>
            <li class="footer">
              <a href="#">View all tasks</a>
            </li>
          </ul>
        </li>
        
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="/mftccManager/AdminLTE/img/user1-128x128.jpg" class="user-image"  >
          	   欢迎您， <span class="hidden-xs"><%=user.getOpName() %></span>
          </a>
          <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header">
              <img src="/mftccManager/AdminLTE/img/user1-128x128.jpg" class="img-circle" alt="User Image">
              <p>
               <%=user.getOpName() %> - <%=user.getBrName() %>
                <small><%=user.getUserNo() %></small>
              </p>
            </li>
            <!-- Menu Body -->
            <li class="user-body" hidden>
              <div class="col-xs-4 text-center">
                <a href="#">Followers</a>
              </div>
              <div class="col-xs-4 text-center">
                <a href="#">Sales</a>
              </div>
              <div class="col-xs-4 text-center">
                <a href="#">Friends</a>
              </div>
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left">
                <a href="javascript:void(0);" id="updateSysUser" class="btn btn-default btn-flat">设置</a>
              </div>
              <div class="pull-right">
                <a href="logout" class="btn btn-default btn-flat">退出</a>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>



    <!-- Header Navbar -->
   <!--  <nav class="navbar navbar-static-top" role="navigation"> </nav> -->
      <!-- Sidebar toggle button-->
      <!-- <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">切换导航栏</span>
      </a> -->
      
      <!-- Navbar Right Menu -->
      <!-- <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            Menu Toggle Button
            <a href="javascript:void(0);" class="dropdown-toggle" id="updateUser">
              <span>修改密码</span>
            </a>
          </li>
          <li class="dropdown">
            Menu Toggle Button
            <a href="logout" class="dropdown-toggle">
              <span>退出系统</span>
            </a>
          </li>
        </ul>
      </div> -->
   
    
    
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <div class="slimScrollDiv">
    <section class="sidebar">

      <!-- search form (Optional) -->
<!--       <form action="#" method="get" class="sidebar-form"> -->
<!--         <div class="input-group"> -->
<!--           <input type="text" name="q" class="form-control" placeholder="Search..."> -->
<!--               <span class="input-group-btn"> -->
<!--                 <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i> -->
<!--                 </button> -->
<!--               </span> -->
<!--         </div> -->
<!--       </form> -->
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
<%--         <li class="header">欢迎欢迎 <span class="text-green"><%=user.getOpName() %></span>来啦！</li>
 --%>        <!-- Optionally, you can add icons to the links -->
        <%
        if (menuMap == null) {
        	out.println("<li><a><i class=\"fa fa-user-times\"></i> <span>尚未登录或当前用户没有权限</span></a></li>");
        } else {
            List<SysMenu> firstMenuList = menuMap.get("");
            if (firstMenuList == null || firstMenuList.size() == 0) {
                out.println("<li><a><i class=\"fa fa-user-times\"></i> <span>没有可用权限</span></a></li>");
            } else {
	            for (SysMenu menu : firstMenuList){
	                out.println(outMenuHtml(menu, menuMap));
	            }
            }
        }
        %>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" id="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <!-- Your Page Content Here -->

		<iframe name="mainFrame" id="mainFrame" frameborder="0" scrolling="auto"
			width="100%" >
		</iframe>
	</section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
<!--       Anything you want -->
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2018 <a href="http://www.mftcc.cn/" target="_blank">微金时代</a>.</strong> All rights reserved.
  </footer>

  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<%@include file="bottom.jsp" %>
<script type="text/javascript">
$(function(){
	/* 框架加载后自动调整高度 */
	$("#mainFrame").on("load", function(){
		layer.close(loadingIndex);
//	 	var ifm = document.getElementById("mainFrame");
		var ifm = this;
		reHeightIfm(ifm);
	});
	$(window).on("resize", function(){
		var ifm = $("#mainFrame")[0];
		reHeightIfm(ifm);
	});
	loadUrl("/sysWarningView");
});
var loadingIndex;
function loadUrl (url) {
	if (!url || url === "null") {
		console.log("url不正确，不执行跳转。");
		return;
	}
	var contextUrl = '<%=request.getContextPath()%>' + url;
	console.log("loadUrl:" + contextUrl);
	loadingIndex = layer.load(0, {
		time: 8000
	});
	$("#mainFrame").attr("src", contextUrl);
}
function reHeightIfm(ifm) {
	try{
		var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;

		if (ifm != null && subWeb != null) {
			var window_height = $(window).height();
			var ifm_height = window_height -35 - $('.main-header').outerHeight() - $('.main-footer').outerHeight();
			$(ifm).css('min-height', ifm_height);
		}
	} catch (ex) {
		console.log("捕获iframe自适应发生异常:" + ex);
	}
}

$("#updateSysUser").click(function(){
	layer.open({
        type: 2,
        title: '修改密码',
        maxmin: true,
        area : ['800px' , '400px'],
        offset: 20,
        content: '<%=request.getContextPath()%>/UpdateUserPasswordView'
    });
});

function toWarning(){
	loadUrl("/warningView");
};
</script>
</body>
</html>
