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
<title>考勤统计详情</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改考勤统计信息</h3>

        </div>
       
			<div class="box-body" id="useredit" >
			<div class="form-group">
				<label class="col-sm-2 control-label">员工姓名</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="empName"  value="${attendence.empName}"  readonly>	
						
				</div>
				<label class="col-sm-2 control-label">员工部门</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="brName"  value="${attendence.brName}"  readonly>	
						
				</div>
										
				
						
				</div>
				
				
			</div>
			 <input class="form-control" id="leaveNo" type="hidden"name="leaveNo"  value="${leave.leaveNo}" >
			<div class="form-group">
				<label class="col-sm-2 control-label">考勤月份</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="yearMonth"  value="${attendence.yearMonth}"  readonly>	
						
				</div>
				<label class="col-sm-2 control-label">标准工时</label>
				<div class="col-sm-4">
			
					<input class="form-control"  type="text"  value="198:00" readonly>
				</div>
				
				
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">工作时数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="workTimeTotal"  value="${attendence.workTimeTotal}"  >	
						
				</div>
				
				
				<label class="col-sm-2 control-label">迟到次数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="lateTimes"  value="${attendence.lateTimes}"  >	
						
				</div>
				
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">早退次数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="eLeaveTimes"  value="${attendence.eLeaveTimes}"  >	
						
				</div>
				<label class="col-sm-2 control-label">加班时数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="sExtraWork"  value="${attendence.sExtraWork}"  >	
						
				</div>
				
				
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">旷工次数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="neglect"  value="${attendence.neglect}"  >	
						
				</div>
				<label class="col-sm-2 control-label">出勤天数</label>
				<div class="col-sm-4">
					
					<input class="form-control"  type="text" name="attendenceDays"  value="${attendence.attendenceDays}"  >	
						
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
	<script>
	
	
	
	
	
	
	
	
	$("#cancel").click(function(){
		window.location.href = "attendence";
	});
	
	
	
	
	 
	 $("#submit").click(function(){
		 if($("#workTimeTotal").val() == "" || $("#lateTimes").val()==""|| $("#neglect").val()==""|| $("#attendenceDays").val()=="" || $("#eLeaveTimes").val() == ""  || $("#sExtraWork").val() == "")
		{
			layer.msg("工作时长、迟到次数、早退次数、加班时长、旷工次数、出勤天数不能为空",{time:1500,offset:'30%'});
			return false;
		}
		
		var mydata = $("#addLeave-Form").serialize();
	
		$.post("<%=request.getContextPath()%>/updateAttendence.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("修改考勤信息失败",{time:1500,offset:'30%'});
				return false;
				
			}else if(data.errorcode == 2){
				layer.tips("此用户号已经存在","#id",{time:1000});
				return false;
			}else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#tel",{time:1000});
			}else{
				layer.msg("修改成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/attendence";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>