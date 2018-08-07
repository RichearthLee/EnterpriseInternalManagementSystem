<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<title>Add Customer</title>
<style type="text/css">
	.form-horizontal .form-group{
		margin-right: 0px;
		margin-left: 0px;
	}
	.myinput{
		width:80%;
		display:inline;
	}
	.mybtn{
		text-align: left;
		border-color: white;
		background: white;
		box-shadow: none;
		padding:0px 0px;
	}
</style>
</head>
<body>
	<div style="height: 50px;">
	</div>
	<div style="min-height: 400px;" >
		 <form action="" class="form-horizontal" id="form">
			<div class="box-bady">
				<div class="form-group">
					<label class="col-sm-2 control-label">客户名称</label>
					<div class="col-sm-4">
						<input id="customerName" name="customerName" class="form-control myinput">
						<font color="red" >*</font>
					</div>

					<label class="col-sm-2 control-label">公司地址</label>
					<div class="col-sm-4">
						<input id="location" name="location" class="form-control myinput">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户联系人详情</label>
					<div class="col-sm-4">
						<input id="linkman" name="linkman" class="form-control myinput">
					</div>
					
				</div>
				
				
		</div>
			
	
		
	<div class="box-footer" style="text-align: center;">
		<button type="submit" id="submit" class="btn btn-success pull-center" style="width: 65px;">确定</button>
		<label>&#12288;</label>
		<button id="back" type="button" class="btn btn-default" style="width: 65px;">取消</button>
		
		
	</div>
	</form> 
	
	</div>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script
		src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>

	<script type="text/javascript">
	$("#submit").click(function(){
		
		if($("#customerName").val() == "")
		{
			alert("客户名称不能为空");
			return false;
		}
	    var mydata = $("form").serialize();
	    //var mydataa= $("form").serializeArray(); 传入json，control层也可以接收到数据
	    $.post("customerinfoinsert.json",  mydata , function(data) {
	    	if (data.errorcode == 0) {
				alert(data.errormessage);
				return false;
			}else{
				window.location.href = "pactListInfo";
			}
	    	
		}, "json");
		
		return false;	
	}); 
	$("#back").click(function(){
		window.location.href = "<%=request.getContextPath()%>/pactListInfo";
	}
			
	);
	
	</script>
</body>
</html>