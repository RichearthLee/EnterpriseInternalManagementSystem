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
<title>权重详情</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改权重信息</h3>

        </div>
       
			<div class="box-body" id="useredit" >
			<div class="form-group">
			<label class="col-sm-2 control-label">请假分数权重</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="leaveMarksFlag"  value="${flag.leaveMarksFlag}"  >	
						
				</div>
				
				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">迟到得分权重</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="lateMarksFlag"  value="${flag.lateMarksFlag}" >	
						
				</div>
			
				
				
			</div>
			<div class="form-group">
				
			
				<label class="col-sm-2 control-label">早退得分权重</label>
				<div class="col-sm-4">
			
					<input class="form-control"  type="text" name="eleaveMarksFlag"   value="${flag.eleaveMarksFlag}" >
				</div>
				
				
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">旷工得分权重</label>
				<div class="col-sm-4">
					<input  name="neglectMarksFlag" class="form-control "
						type="text" value="${flag.neglectMarksFlag}">
				</div>
				
				
				
				
			</div>
			
			<div class="form-group">
				
							
				<label class="col-sm-2 control-label">加班得分权重</label>
				<div class="col-sm-4">
					
					<input type="text"  class="form-control"  name="extraWorkMarksFlag" value="${flag.extraWorkMarksFlag}">
				</div>
				
			</div>
			
			
			
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">出差得分权重</label>
				<div class="col-sm-4">
					<input  name="goOutMarksFlag"
						class="form-control " type="text" value="${flag.goOutMarksFlag}">
				</div>
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">工作时长得分权重</label>
				<div class="col-sm-4">
					<input  name="workTimeMarksFlag"
						class="form-control " type="text" value="${flag.workTimeMarksFlag}">
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
	

	
	
	$("#cancel").click(function(){
		window.location.href = "kpiattendence";
	});
	
	
	
	
	 
	 $("#submit").click(function(){
		 if($("#leaveMarksFlag").val() == "" || $("#lateMarksFlag").val()=="" || $("#eleaveMarksFlag").val() == "" || $("#workTimeMarksFlag").val() == ""|| $("#extraWorkMarksFlag").val() == ""|| $("#goOutMarksFlag").val() == "" || $("#neglectMarksFlag").val() == "")
		{
			layer.msg("不能为空",{time:1500,offset:'30%'});
			return false;
		}
		
		var mydata = $("#addLeave-Form").serialize();
	
		$.post("<%=request.getContextPath()%>/updateflag.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("修改失败",{time:1500,offset:'30%'});
				return false;
				
			}else if(data.errorcode == 2){
				layer.tips("修改失败","#id",{time:1000});
				return false;
			}else if(data.errorcode == 3){
				layer.tips("修改失败","#tel",{time:1000});
			}else{
				layer.msg("修改权重成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/kpiattendence";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>