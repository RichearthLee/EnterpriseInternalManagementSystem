<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
		<%@page import="com.mftcc.interior.employee.bean.EmployeeInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<% EmployeeInfo employeeInfo = (EmployeeInfo)request.getAttribute("employeeInfo"); %>

<title>员工添加</title>
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
				<input  id="employeeId"  name="employeeId"	value="<%=employeeInfo.getEmployeeId()%>"  type="hidden" >
				
				 <div class="form-group">
					<label class="col-sm-2 control-label">员工姓名</label>
					
					<div class="col-sm-4" >
						<input class="form-control myinput" id="employeeName"  name="employeeName" 
						value="<%=employeeInfo.getEmployeeName()%>"  maxlength="50" placeholder="">
					</div>
						<label class="col-sm-2 control-label">备 &nbsp; 注</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="employeeMarks" name="employeeMarks"
						value="<%=employeeInfo.getEmployeeMarks() %>"   maxlength="50" placeholder="">
					</div>
				</div>
			 
			  <div class="form-group">
					<label class="col-sm-2 control-label">部  &nbsp;  门</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="employeeDepartment" name="employeeDepartment" 
						value="<%=employeeInfo.getEmployeeDepartment() %>"  maxlength="50" placeholder="">
					</div>
				
		 		<label class="col-sm-2 control-label">部门编号</label>
					<div class="col-sm-4" >
						<input class="form-control myinput"  id="employeeDepartmentId" name="employeeDepartmentId"
						value="<%=employeeInfo.getEmployeeDepartmentId() %>" maxlength="50" placeholder="">
					</div>
					</div>
					
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-4">
						<input id="employeePhone" name="employeePhone"
						value="<%=employeeInfo.getEmployeePhone() %>"	class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">联系邮箱</label>
					<div class="col-sm-4">
						<input id="employeeEmail" name="employeeEmail" 
						value="<%=employeeInfo.getEmployeeEmail() %>"class="form-control myinput">
					</div>
				</div>
	 
		</div>
			
		
	<div class="box-footer" style="text-align: center;">
		<button type="button" id="submit" class="btn btn-success pull-center" style="width: 65px;">保存</button>
		<label>&#12288;</label>
		<button type="button" id="cancel" class="btn btn-default" style="width: 65px;">取消</button>
	</div>
	</form> 
 
	<%@include file='../bottom.jsp' %>
	
</body>
</html>



<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	
	
<script type="text/javascript">
 
 
 
	$("#cancel").click(function(){
		window.location.href = "<%=request.getContextPath()%>/employeeListInfo";
	});
	
	$("#submit").click(function(){
    	 	if($("#employeeDepartment").val() == "" || $("#employeeName").val() == "" || $("#employeePhone").val() == 
			"" || $("#employeeEmail").val() == "" || $("#employeeDepartmentId").val() == "")
		{	
			layer.msg("不能为空",{time:1500});
			return false;
		}
		if($("#employeeName").val().length > 50 || $("#employeeEmail").val().length > 20 || $("#employeeDepartment").val().length > 20 || $("#employeePhone").val().length > 15)
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
		$.post("<%=request.getContextPath()%>/employeeinfoupdate.json",  mydata , function(data) {
		}, "json"); 
		layer.msg("保存完成！",{time:1500},function(){
			window.location.href = "<%=request.getContextPath()%>/employeeListInfo";
		});
		
	}); 
	</script>