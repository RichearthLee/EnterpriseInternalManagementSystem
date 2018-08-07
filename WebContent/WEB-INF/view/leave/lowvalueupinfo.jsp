<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.mftcc.interior.oa.leave.bean.LeaveBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>低值易损品管理</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改低值易损品数量</h3>

        </div>
       
			<div class="box-body" id="useredit" >
			<div class="form-group">
			<label class="col-sm-2 control-label">名称</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="lowValueName"  value="${amount.lowValueName}" readonly >	
				</div>
			<label class="col-sm-2 control-label">编号</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="lowValueNo"  value="${amount.lowValueNo}" readonly >	
				</div>	
				
			</div>
			
			<div class="form-group">
			
			<label class="col-sm-2 control-label">修改时间</label>
				<div class="col-sm-4">
					<input id="lowValueDate" name="lowValueDate" class="form-control " type="text" readonly>
				</div>
			
				<label class="col-sm-2 control-label">数量</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="lowValueAmount"  value="${amount.lowValueAmount}" >	
						
				</div>
			
				
				
			</div>
			
				 
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="submit"
					class="btn btn-success pull-center" style="width: 65px;">确定</button>
				<label>&#12288;</label>
				<button type="reset" id="cancel" class="btn btn-default"
					style="width: 65px;">重置</button>
			</div>
		</div>
	</div>	
	</form>
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	
	//获取当前时间
	function lowValueDate(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    return h+"-"+m+"-"+d;
}
 
document.getElementById("lowValueDate").value = lowValueDate();
	
	
	
	$("#cancel").click(function(){
		window.location.href = "lowvaluemanage";
	});
	
	
	
	
	 
	 $("#submit").click(function(){
		 if($("#lowValueAmount").val() == "" )
		{
			layer.msg("不能为空",{time:1500,offset:'30%'});
			return false;
		}
		
		var mydata = $("#addLeave-Form").serialize();
	
		$.post("<%=request.getContextPath()%>/updatelowvalueamount.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("修改失败0",{time:1500,offset:'30%'});
				return false;
				
			}else if(data.errorcode == 2){
				layer.tips("修改失败1","#id",{time:1000});
				return false;
			}else if(data.errorcode == 3){
				layer.tips("修改失败2","#tel",{time:1000});
			}else{
				layer.msg("修改成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/lowvaluemanage";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>