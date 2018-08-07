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
<title>请假详情</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改请假信息</h3>

        </div>
       
			<div class="box-body" id="useredit" >
			<div class="form-group">
				<label class="col-sm-2 control-label">请假类型</label>
				<div class="col-sm-4">
					<%-- <input id="leaveType" name="leaveType"  readonly="readonly" class="form-control" 
						type="text" value="${leave.leaveType}"> --%>
										
				<select id="leaveType" name="leaveType" class="form-control ">
						<option <c:if test="${leave.leaveType eq '016'}">selected</c:if>value="016">病假</option>
						<option <c:if test="${leave.leaveType eq '011'}">selected</c:if> value="011">事假</option>
						<option <c:if test="${leave.leaveType eq '012'}">selected</c:if> value="012">婚假</option>
						<option <c:if test="${leave.leaveType eq '017'}">selected</c:if> value="017">产假</option>
						<option <c:if test="${leave.leaveType eq '018'}">selected</c:if> value="018">倒休假</option>
						<option <c:if test="${leave.leaveType eq '015'}">selected</c:if> value="015">陪产假</option>
						<option <c:if test="${leave.leaveType eq '014'}">selected</c:if> value="014">带薪年假</option>
						<option <c:if test="${leave.leaveType eq '013'}">selected</c:if> value="013">丧假</option>
						
				</select> 		
						
						
				</div>
				
				
			</div>
			 <input class="form-control" id="leaveNo" type="hidden"name="leaveNo"  value="${leave.leaveNo}" >
			<div class="form-group">
				<label class="col-sm-2 control-label">请假开始时间</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="inpstart" type="text" name="leaveBeginTime" placeholder="开始日期" value="${leave.leaveBeginTime}"  readonly>	
						
				</div>
			
				<label class="col-sm-2 control-label">请假结束时间</label>
				<div class="col-sm-4">
			
					<input class="form-control" id="inpend" type="text" name="leaveEndTime" placeholder="结束日期"  value="${leave.leaveEndTime}" readonly>
				</div>
				
				
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">请假总时长</label>
				<div class="col-sm-4">
					<input id="leaveSum" name="leaveSum" class="form-control "
						type="text" value="${leave.leaveSum}">
				</div>
				
				
				<label class="col-sm-2 control-label">申请时间</label>
				<div class="col-sm-4">
					
					<input type="text"  class="form-control" id="leaveApplyTime" name="leaveApplyTime" >
				</div>
				
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">请假详情</label>
				<div class="col-sm-4">
					<input id="leaveDetail" name="leaveDetail"
						class="form-control " type="text" value="${leave.leaveDetail}">
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery.jedate.js"></script>
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	
	//获取当前时间
	function leaveApplyTime(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    return h+"-"+m+"-"+d;
}
 
document.getElementById("leaveApplyTime").value = leaveApplyTime();
	
var start = {
	    format: 'YYYY-MM-DD hh:mm',
	    minDate: $.nowDate(0), //设定最小日期为当前日期
	    festival:true,
	    //isinitVal:true,
	   // maxDate: $.nowDate(0), //最大日期
	    choosefun: function(elem,datas){
	        end.minDate = datas; //开始日选好后，重置结束日的最小日期
	    },
	   okfun:function (elem,datas) {
	        alert(datas)
	   }
	};
	var end = {
	    format: 'YYYY-MM-DD hh:mm',
	    minDate: $.nowDate(0), //设定最小日期为当前日期
	    festival:true,
	    //isinitVal:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	    choosefun: function(elem,datas){
	        start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
	    
	    }
	};
	$("#inpstart").jeDate(start);
	$("#inpend").jeDate(end);
	
	
	
	
	
	
	$("#cancel").click(function(){
		window.location.href = "listleave";
	});
	
	
	
	
	 
	 $("#submit").click(function(){
		 if($("#leaveBegintime").val() == "" || $("#leaveEndtime").val()=="" || $("#leaveSum").val() == ""  || $("#leaveDetail").val() == "")
		{
			layer.msg("开始时间、结束时间、总时长、请假详情不能为空",{time:1500,offset:'30%'});
			return false;
		}
		
		var mydata = $("#addLeave-Form").serialize();
	
		$.post("<%=request.getContextPath()%>/updateLeave.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("增加新用户失败",{time:1500,offset:'30%'});
				return false;
				
			}else if(data.errorcode == 2){
				layer.tips("此用户号已经存在","#id",{time:1000});
				return false;
			}else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#tel",{time:1000});
			}else{
				layer.msg("请假申请成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/getAllLeave";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>