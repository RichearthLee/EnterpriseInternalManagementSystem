<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/AdminLTE/css/AdminLTE.min.css">

<!-- Font Awesome -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->
<!-- <link href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/Font-Awesome-4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/ionicons-master/css/ionicons.min.css">

<!-- iCheck -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/square/blue.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
      page. However, you can choose any other skin. Make sure you
      apply the skin class to the body tag so the changes take effect.
-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/AdminLTE/css/skins/skin-blue.min.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- 传说可以解决IE低版本的兼容问题的外链 -->
<!-- <script src="http://haiqiancun.com/file/demo/custom.modernizr.js"></script>  -->

<!-- datepicker -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/AdminLTE/plugins/datepicker/datepicker3.css">

<!-- //zTree_v3 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<!-- 开关 Switch 控件 -->
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/bootstrap/switch/css/bootstrap-switch.min.css" />

<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>

<style>
.input-group-btn-vertical {
  position: relative;
  white-space: nowrap;
  width: 1%;
  vertical-align: middle;
  display: table-cell;
}
.input-group-btn-vertical > .btn {
  display: block;
  float: none;
  width: 100%;
  max-width: 100%;
  padding: 11px;
  margin-left: -1px;
  position: relative;
  border-radius: 0;
}
.input-group-btn-vertical > .btn:first-child {
  border-top-right-radius: 4px;
}
.input-group-btn-vertical > .btn:last-child {
  margin-top: -2px;
  border-bottom-right-radius: 4px;
}
.input-group-btn-vertical i{
  position: absolute;
  top: 3px;
  left: 4px;
}
.spinner-label{
padding-top:12px;
}
/*下面是分页的样式  */
*{ margin:0; padding:0; list-style:none;}
a{ text-decoration:none;}
a:hover{ text-decoration:none;}
#page-div{padding: 15px 20px;text-align: left;color: #ccc;}
#page-div a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
#page-div a:hover{text-decoration: none;border: 1px solid #428bca;}
#page-div span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
#page-div span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
#page-div select{
	display: inline-block;
    color: #428bca;
    display: inline-block;
    height: 25px;
    line-height: 25px;
    padding: 0 10px;
    border: 1px solid #ddd;
    margin: 0 2px;
    border-radius: 4px;
    vertical-align: middle;
}
#page-div select:hover{
	text-decoration: none;
    border: 1px solid #428bca;
}
</style>