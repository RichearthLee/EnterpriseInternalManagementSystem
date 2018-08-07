<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.mftcc.interior.cred.bean.CredBean"%>
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
          <h3 class="box-title">修改客户信息</h3>

        </div>
			<div class="box-body" id="useredit" >
			<div class="form-group">
				<label class="col-sm-2 control-label">客户类型</label>
				<div class="col-sm-4">
					<%-- <input id="cusSort" name="cusSort"  readonly="readonly" class="form-control" 
						type="text" value="${cus.cusSort}"> --%>
										
				<select id="cusdSort" name="cusSort" class="form-control ">
						<option <c:if test="${cus.cusSort eq '001'}">selected</c:if>value="学生">学生</option>
						<option <c:if test="${cus.cusSort eq '002'}">selected</c:if>value="公司职员">公司职员</option>
						<option <c:if test="${cus.cusSort eq '003'}">selected</c:if>value="个体经营户">个体经营户</option>
				</select> 		
						
						
				</div>
				
				
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">客户姓名</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="cusName" type="text" name="cusName"  value="${cus.cusName}"  >	
						
				</div>
			
				<label class="col-sm-2 control-label">手机号码</label>
				<div class="col-sm-4">
			
					<input class="form-control" id="cusPhone" type="text" name="cusPhone"   value="${cus.cusPhone}" >
				</div>
				
				
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">身份证号</label>
				<div class="col-sm-4">
					<input id="cusIdNumber" name="cusIdNumber" class="form-control "
						type="text" value="${cus.cusIdNumber}">
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
		<input class="form-control hidden" id="cusNo" type="text" name="cusNo"   value="${cus.cusNo}" >
	</form>
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/component/jedate/jquery.jedate.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/component/jedate/skin/jedate.css">
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	
	

	$("#cancel").click(function(){
		window.location.href = "customerlist";
	});
	
	
	
	 $("#submit").click(function(){
		//验证姓名为2-4位
		 var regName = /^[\u4E00-\u9FA5]{2,4}$/;
		 if(!regName.test($("#cusName").val())){
			 layer.msg("姓名格式为2-4位汉字",{time:1500,offset:'30%'});
			 return false;
		 }
		//验证手机号码
		var regPhone=/^1[0-9]{10}$/;
		 if(!regPhone.test($("#cusPhone").val())){
			 layer.msg("手机号码为11位",{time:1500,offset:'30%'});
			 return false;
		 }
		//校验身份证号格式
		 var regIdNumber=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
		   if(!regIdNumber.test($("#cusIdNumber").val())){
			 layer.msg("身份证号为15位或18位",{time:1500,offset:'30%'});
			 return false;
		 }  
			
		
		var mydata = $("#addUser-Form").serialize();
		$.post("<%=request.getContextPath()%>/updateCus.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("增加新用户失败",{time:1500,offset:'30%'});
				return false;
				
			}
			else if(data.errorcode == 2){
				layer.tips("此用户号已经存在","#id",{time:1000});
				return false;
			}
			else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#tel",{time:1000});
			}
			else{
				layer.msg("修改用户成功",{time:1500,offset:'30%'},function(){
					window.location.href ="<%=request.getContextPath()%>/customerlist";				
				});
			}
		}, "json");
		return false;
	}); 
</script>
</body>
</html>