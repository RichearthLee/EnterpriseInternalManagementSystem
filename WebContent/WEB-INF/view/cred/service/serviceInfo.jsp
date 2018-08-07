<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.mftcc.interior.cred.bean.ServiceBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户详情</title>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body style="min-height: 300px;">

	<section class="content">
	<form  class="form-horizontal" id="addUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">修改服务信息</h3>

        </div>
			<div class="box-body" id="useredit" >
			
			<div class="form-group">
				<label class="col-sm-2 control-label">服务编号</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="serviceNumber" type="text" name="serviceNumber"  value="${service.serviceNumber}"  >	
						
				</div>
			
				<label class="col-sm-2 control-label">服务名称</label>
				<div class="col-sm-4">
			
					<input class="form-control" id="serviceName" type="text" name="serviceName"   value="${service.serviceName}" >
				</div>
				
				
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">服务商</label>
				<div class="col-sm-4">
					<input id="serviceProvider" name="serviceProvider" class="form-control "
						type="text" value="${service.serviceProvider}">
				</div>
			</div>
		<div class="form-group">
				<label class="col-sm-2 control-label">服务状态</label>
				<div class="col-sm-4">
				<select id="serviceState" name="serviceState" class="form-control ">
						<option <c:if test="${service.serviceState eq '001'}">selected</c:if>value="可用">可用</option>
						<option <c:if test="${service.serviceState eq '002'}">selected</c:if>value="禁用">禁用</option>
				</select> 		
				</div>
			</div>
				 <div class="form-group">
				
				<label class="col-sm-2 control-label">服务价格</label>
				<div class="col-sm-4">
					<input id="servicePrice" name="servicePrice" class="form-control "
						type="text" value="${service.servicePrice}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="serviceDate" type="text" name="serviceDate" placeholder="创建时间" value="${service.serviceDate}"  readonly>	
						
				</div>
			</div>
			
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="submit"
					class="btn btn-success pull-center" style="width: 65px;">确定</button>
				<label>&#12288;</label>
				<button type="reset" id="cancel" class="btn btn-default"
					style="width: 65px;">取消</button>
			</div>
		</div>
	</div>	
		<input class="form-control hidden" id="serviceNo" type="text" name="serviceNo"   value="${service.serviceNo}" >
	</form>
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery.jedate.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/component/jedate/skin/jedate.css">
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	var time = {
		    format: 'YYYY-MM-DD hh:mm',
		    minDate: $.nowDate(0), //设定最小日期为当前日期
		  
		    festival:true,
		    //isinitVal:true,
		    maxDate: '2099-06-16 23:59', //最大日期
		    choosefun: function(elem,datas){
		        start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		    
		    }
		};
		$("#serviceDate").jeDate(time);
	

	$("#cancel").click(function(){
		window.location.href = "servicelist";
	});
	
	
	
	 $("#submit").click(function(){
		 var regName = /^[\u4E00-\u9FA5]{2,10}$/;
		 if(!regName.test($("#serviceName").val())){
			 layer.msg("服务名称格式为2-10位",{time:1500,offset:'30%'});
			 return false;
		 }
		 //验证服务单价是正整数且小数位不超过2位
		var regPrice = /^\d+(\.\d{1,2})?$/;
		 if(!regPrice.test($("#servicePrice").val())){
			 layer.msg("服务单价是正整数且小数位不超过2位",{time:1500,offset:'30%'});
			 return false;
		 }
			
		
		var mydata = $("#addUser-Form").serialize();
		$.post("<%=request.getContextPath()%>/updateService.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("修改服务失败",{time:1500,offset:'30%'});
				return false;
				
			}
			else if(data.errorcode == 2){
				layer.tips("此服务已经存在","#id",{time:1000});
				return false;
			}
			else if(data.errorcode == 3){
				layer.tips("此服务已经存在","#tel",{time:1000});
			}
			else{
				layer.msg("修改服务成功",{time:1500,offset:'30%'},function(){
					window.location.href ="<%=request.getContextPath()%>/servicelist";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>