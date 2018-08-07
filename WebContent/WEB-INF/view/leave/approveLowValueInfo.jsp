<%@page import="java.util.Map"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>approveInfo</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>

<section class="content">
	<form  class="form-horizontal" id="approveInfo-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">审核低值易损信息</h3>

        </div>
        	<input class="form-control" id="opNo1" type="hidden"name="opNo1"  value="<%=session.getAttribute("opNo1")%>" >
        	<input class="form-control" id="opName1" type="hidden"name="opName1"  value="<%=session.getAttribute("opName1")%>" >
        	<input class="form-control" id="approveLowValueNo" type="hidden"name="approveLowValueNo"  value="${approveLowValue.approveLowValueNo}" >
        	<input class="form-control" id="lowValueApplyNo" type="hidden"name="lowValueApplyNo"  value="${approveLowValue.lowValueApplyNo}" >
       		<div class="form-group">
				<label class="col-sm-2 control-label">员工姓名</label>
				<div class="col-sm-4">
					<input id="opName" name="opName" class="form-control "
						type="text" value="${approveLowValue.opName}" readonly>
				</div>
				<label class="col-sm-2 control-label">所在部门</label>
				<div class="col-sm-4">
					<input id="brName" name="brName" class="form-control "
						type="text" value="${approveLowValue.brName}" readonly>
				</div>
				
			</div>
        
		
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">申请类型</label>
				<div class="col-sm-4">
					<input id="applyType" name="applyType" class="form-control "
						type="text" value="${approveLowValue.applyType}" readonly>
				</div>
				
				
				<label class="col-sm-2 control-label">申请时间</label>
				<div class="col-sm-4">
					
					<input type="text"  class="form-control" id="applyTime" name="applyTime" 
					value="${approveLowValue.applyTime}" readonly   >
				</div>
				
			</div>
			
			<div class="form-group">
				
				<label class="col-sm-2 control-label">请假详情</label>
				<div class="col-sm-4">
					<input id="applyDetail" name="applyDetail"
						class="form-control " type="text" value="${approveLowValue.applyDetail}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">审核时间</label>
				<div class="col-sm-4">
					<input id="approveTime" name="approveTime" class="form-control " type="text" readonly>
				</div>
			</div>
			<div class="box-body" id="useredit" >
		
		     	<div class="form-group">
		     	<label class="col-sm-2 control-label">审核详情</label>
				<div class="col-sm-4">
					<input id="approveDetail" name="approveDetail" class="form-control "
						type="text" value="${approveLowValue.approveDetail}">
				</div>
				
				
						
				</div>
				
				
			</div>
			
			 <div class="box-footer" style="text-align: left;">
				<button type="button" id="pass"
					class="btn btn-success pull-center" style="width: 65px;">通过</button>
				<label>&#12288;</label>
				<button type="button" id="nopass" class="btn btn-default"
					style="width: 65px;">拒绝</button>
				<button type="reset" id="cancel" class="btn btn-default"
					style="width: 65px;">返回</button>
				
			</div>
				 
			
				 
			
		</div>
	</div>	
	</form>
	
	</section>






	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
	
	//获取当前时间
	function approveTime(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    return h+"-"+m+"-"+d;
}
 
document.getElementById("approveTime").value = approveTime();
	
	
	
	
	
	$("#cancel").click(function(){
		window.location.href = "approvelowvalue";
	});
	
	
	 $("#pass").click(function(){
		
		
		/*  +"&"+$.param({leaveState:046}) */
		 
		var mydata =$("#approveInfo-Form").serialize();
		console.log("mydata:"+mydata);
		$.post("<%=request.getContextPath()%>/updateApproveLowValue.json" ,mydata, function(data){
			if(data.errorcode == 0)
			{
				layer.msg("审核失败",{time:1500,offset:'30%'});
				return false;
				
			}else if(data.errorcode == 2){
				layer.tips("此用户号已经存在","#id",{time:1000});
				return false;
			}else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#tel",{time:1000});
			}else{
				layer.msg("审核通过成功",{time:1500,offset:'30%'},function(){
					window.location.href ="<%=request.getContextPath()%>/approvelowvalue";				
				});
			}
		}, "json");
		return false;
	}); 
	
	 $("#nopass").click(function(){
			
		/*  +'&'+$.param({leaveState:047}) */
			var mydata = $("#approveInfo-Form").serialize();
			$.post("<%=request.getContextPath()%>/updateApproveLowValue1.json" ,mydata , function(data){
				if(data.errorcode == 0)
				{
					layer.msg("审核失败",{time:1500,offset:'30%'});
					return false;
					
				}else if(data.errorcode == 2){
					layer.tips("此用户号已经存在","#id",{time:1000});
					return false;
				
				}else{
					layer.msg("审核不通过",{time:1500,offset:'30%'},function(){
						window.location.href ="<%=request.getContextPath()%>/approvelowvalue";				
					});
				}
			}, "json");
			return false;
		}); 

		
	</script>
	
	
</body>
</html>