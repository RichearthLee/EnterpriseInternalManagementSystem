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
<title>低值易损物品申请详情</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改低值易损物品申请信息</h3>

        </div>
       
			<div class="box-body" id="useredit" >
			<div class="form-group">
				<label class="col-sm-2 control-label">申请类型</label>
				<div class="col-sm-4">
				
				<select id="lowValueType" name="lowValueType" class="form-control ">
						<option <c:if test="${lowvalue.lowValueType eq '050'}">selected</c:if>value="050">铅笔</option>
						<option <c:if test="${lowvalue.lowValueType eq '051'}">selected</c:if> value="051">中性笔</option>
						<option <c:if test="${lowvalue.lowValueType eq '052'}">selected</c:if> value="052">A3打印纸</option>
						<option <c:if test="${lowvalue.lowValueType eq '053'}">selected</c:if> value="053">B2打印纸</option>
						<option <c:if test="${lowvalue.lowValueType eq '054'}">selected</c:if> value="054">A4打印纸</option>
						<option <c:if test="${lowvalue.lowValueType eq '055'}">selected</c:if> value="055">鼠标</option>
						<option <c:if test="${lowvalue.lowValueType eq '056'}">selected</c:if> value="056">键盘</option>
						
				</select> 		
						
						
				</div>
				
				
			</div>
			 <input class="form-control" id="lowValueNo" type="hidden"name="lowValueNo"  value="${lowvalue.lowValueNo}" >
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">申请数量</label>
				<div class="col-sm-4">
					<input id="lowValueApplyAmount" name="lowValueApplyAmount" class="form-control "
						type="text" value="${lowvalue.lowValueApplyAmount}">
				</div>
				
				
				<label class="col-sm-2 control-label">申请时间</label>
				<div class="col-sm-4">
					
					<input type="text"  class="form-control" id="lowValueApplyTime" name="lowValueApplyTime" >
				</div>
				
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">申请详情</label>
				<div class="col-sm-4">
					<input id="lowValueApplyDetail" name="lowValueApplyDetail"
						class="form-control " type="text" value="${lowvalue.lowValueApplyDetail}">
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
	function lowValueApplyTime(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    return h+"-"+m+"-"+d;
}
 
document.getElementById("lowValueApplyTime").value = lowValueApplyTime();
	

	
	
	
	
	
	$("#cancel").click(function(){
		window.location.href = "listlowvalue";
	});
	
	
	
	
	 
	 $("#submit").click(function(){
		 if($("#lowValueApplyAmount").val() == "" || $("#lowValueApplyDetail").val()==""  )
		{
			layer.msg("开始时间、结束时间、总时长、请假详情不能为空",{time:1500,offset:'30%'});
			return false;
		}
		
		var mydata = $("#addLeave-Form").serialize();
	
		$.post("<%=request.getContextPath()%>/updateLowValue.json" ,mydata , function(data){
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
				layer.msg("修改成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/listlowvalue";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>