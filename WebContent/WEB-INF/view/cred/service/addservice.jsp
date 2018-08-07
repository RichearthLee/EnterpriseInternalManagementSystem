<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@ page import="com.mftcc.interior.cred.bean.ServiceBean"%>
<%@ page import="com.mftcc.interior.sys.bean.SysUser"%>
 <%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addservice</title>
<%@ include file="/WEB-INF/view/head.jsp" %>

</head>

<body>
<section class="content">
	<form  class="form-horizontal" id="addUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">新增服务</h3>

        </div>
		<div class="box-body">
			
			<!-- <input class="form-control" id="credState" type="hidden"name="credState"  value="0"  > -->
			
			<div class="form-group">
				<label class="col-sm-2 control-label">服务编号</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="serviceNumber" name="serviceNumber" placeholder="">
				</div>
				
				<label class="col-sm-2 control-label">服务名称</label>
				<div class="col-sm-4">
				
					<input type="text" value="" class="form-control" id="serviceName" name="serviceName" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">服务商</label>
				<div class="col-sm-4">
					<input id="serviceProvider" name="serviceProvider" class="form-control " type="text">
				</div>
			</div>
			<div class="form-group">
				
				<label class="col-sm-2 control-label">服务状态</label>
				<div class="col-sm-4">
					<select id="serviceState" name="serviceState" class="form-control ">
						<option value="可用">可用</option>
						<option value="禁用">禁用</option>
					</select> 
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">服务价格</label>
				<div class="col-sm-4">
					<input id="servicePrice" name="servicePrice" class="form-control " type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">创建日期</label>
				<div class="col-sm-4">
					<input id="serviceDate" name="serviceDate" class="form-control " type="text" placeholder="创建日期" value=""  readonly>
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
	 	/* if($("#cusName").val() == "" || $("#cusPhone").val()=="" || $("#cusIdNumber").val() == "" )
		{
			layer.msg("客户姓名、手机号码、身份证号不能为空",{time:1500,offset:'30%'});
			return false;
		}
		 */
		 //验证服务编号为全英文
		/*  var regNumber = new RegExp("^[a-zA-Z]+$");
		 if(!regNumcer.test($("#serviceNumber").val())){
			 layer.msg("服务编号为全英文",{time:1500,offset:'30%'});
			 return false;
		 } */
		 //验证服务名称为2-10位[\u4e00-\u9fa5_a-zA-Z0-9_]{4,10}
		 var regName = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{1,10}$/;
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
		$.post("<%=request.getContextPath()%>/AddService.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				layer.msg("增加新服务失败",{time:1500,offset:'30%'});
				return false;
				
			}
			else if(data.errorcode == 2){
				layer.tips("此服务已经存在","#serviceNo",{time:1000});
				return false;
			}
			else if(data.errorcode == 3){
				layer.tips("此联系电话已经存在","#cusPhone",{time:1000});
			}
			else{
				layer.msg("服务新增成功",{time:1500,offset:'30%'},
					
						
						function(){
					window.location.href ="<%=request.getContextPath()%>/servicelist";				
				});
			}
		}, "json");
		return false;
	}); 

	
	
</script>
</body>
</html>