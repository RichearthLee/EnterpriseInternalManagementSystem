<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@ page import="com.mftcc.interior.cred.bean.CredBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/view/head.jsp" %>

</head>

<body>
<section class="content">
	<form  class="form-horizontal" id="addUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">新增客户</h3>

        </div>
		<div class="box-body">
			<div class="form-group">
				
				<label class="col-sm-2 control-label">客户类型</label>
				<div class="col-sm-4">
					<select id="cusSort" name="cusSort" class="form-control ">
						<option value="学生">学生</option>
						<option value="公司职员">公司职员</option>
						<option value="个体经营户">个体经营户</option>

						
					</select> 
				</div>
			</div>
			<input class="form-control" id="credState" type="hidden"name="credState"  value="0"  >
			
			<div class="form-group">
				<label class="col-sm-2 control-label">客户姓名</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="cusName" name="cusName" placeholder="">
				</div>
				
				<label class="col-sm-2 control-label">手机号码</label>
				<div class="col-sm-4">
				
					<input type="text" value="" class="form-control" id="cusPhone" name="cusPhone" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">身份证号</label>
				<div class="col-sm-4">
					<input id="cusIdNumber" name="cusIdNumber" class="form-control " type="text">
				</div>
			</div>
			
				 
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="submit"
					class="btn btn-success pull-center" style="width: 65px;">确定</button>
				<label>&#12288;</label>
				<button type="button" id="cancel" class="btn btn-default"
					style="width: 65px;">取消</button>
			</div>
		</div>
	</div>	
	</form>
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	

	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	

	
	$("#cancel").click(function(){
		window.location.href = "customerlist";
	});
	
	
	 $("#submit").click(function(){
	 	/* if($("#cusName").val() == "" || $("#cusPhone").val()=="" || $("#cusIdNumber").val() == "" )
		{
			layer.msg("客户姓名、手机号码、身份证号不能为空",{time:1500,offset:'30%'});
			return false;
		}
		 */
		 //验证姓名为2-4位
		 var regName = /^[\u4E00-\u9FA5]{2,4}$/;
		 if(!regName.test($("#cusName").val())){
			 layer.msg("姓名格式为2-4位",{time:1500,offset:'30%'});
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
		$.post("<%=request.getContextPath()%>/AddCred.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("增加新用户失败",{time:1500,offset:'30%'});
				return false;
				
			}
			else if(data.errorcode == 2){
				layer.tips("此用户号已经存在","#cusNo",{time:1000});
				return false;
			}
			else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#cusPhone",{time:1000});
			}
			else{
				layer.msg("客户新增成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/customerlist";				
				});
			}
		}, "json");
		return false;
	}); 

	
	
</script>
</body>
</html>