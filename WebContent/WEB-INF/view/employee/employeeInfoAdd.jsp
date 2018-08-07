<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<title>客户添加</title>
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
	<div style="margin-top: 20px"></div>
		 <form  class="form-horizontal" id="form">
			<div class="box-bady">
		 
				<br/><br/>
				 <div class="form-group">
					<label class="col-sm-2 control-label">客户姓名</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="customerName"  name="customerName" maxlength="50" placeholder="">
					</div>
						<label class="col-sm-2 control-label">客户类型</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="customerType" name="customerType" maxlength="50" placeholder="">
					</div>
				</div>
			 
			  <div class="form-group">
					<label class="col-sm-2 control-label">详细地址</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="location" name="location" maxlength="50" placeholder="">
					</div>	
			</div>
					
	
	 
		</div>
			
		
	<div class="box-footer" style="text-align: center;">
		<button type="button" id="submit" class="btn btn-success pull-center" style="width: 65px;">确定</button>
		<label>&#12288;</label>
		<button type="button" id="cancel" class="btn btn-default" style="width: 65px;">取消</button>
	</div>
	</form> 
 
	<%@include file='../bottom.jsp' %>
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
 
 
	$("#cancel").click(function(){
		window.location.href = "<%=request.getContextPath()%>/employeeListInfo";
	});
	
 
	$("#submit").click(function(){
    	 	if($("#employeeDepartment").val() == "" || $("#customerName").val() == "" || $("#employeePhone").val() == 
			"" || $("#employeeEmail").val() == "" || $("#employeeDepartmentId").val() == "")
		{	
			layer.msg("不能为空",{time:1500});
			return false;
		}
		if($("#customerName").val().length > 50 || $("#employeeEmail").val().length > 20 || $("#employeeDepartment").val().length > 20 || $("#employeePhone").val().length > 15)
		{
			layer.msg("存在内容长度过长的输入",{time:1500});
			return false;
		} 
		 var emailValue=$("#employeeEmail").val();
		  if (!isEmail(emailValue))
		  {
			  layer.msg("邮箱格式不正确",{time:1500});
		   return false;
		  }
	 
		 function isEmail(str){
		       var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		       return reg.test(str);
		   }
		 
		  var PhoneValue=$("#employeePhone").val();
		    if (!isPhone(PhoneValue))
		  {
			  layer.msg("手机号格式不正确 ",{time:1500});
		   return false;
		  }  
	  
		 function isPhone(str){
			 var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证规则
		       return reg.test(str);
		   }
  
	  
		 var mydata = $("form").serialize() ;
		// alert($("form").serialize());
		$.post("<%=request.getContextPath()%>/employeeinfoinsert.json",  mydata , function(data) {
		}, "json"); 
		layer.msg("保存完成！",{time:1500},function(){
			window.location.href = "<%=request.getContextPath()%>/employeeListInfo";
		});
		
	}); 
	
	
	</script>
</body>
</html>