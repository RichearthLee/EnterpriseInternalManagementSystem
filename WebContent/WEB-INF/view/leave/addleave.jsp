<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@ page import="com.mftcc.interior.oa.leave.bean.LeaveBean"%>
<%@ page import="com.mftcc.interior.sys.bean.SysUser"%>
 <%@ page import="javax.servlet.http.HttpSession"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/view/head.jsp" %>

</head>

<body>
<section class="content">
	<form  class="form-horizontal" id="addLeave-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">请假单填写</h3>

        </div>
		<div class="box-body">

		
	
			<div class="form-group">
				<label class="col-sm-2 control-label">用户</label>
				<div class="col-sm-4">
					<input class="form-control" id="opName" type="text" name="opName" value="<%=session.getAttribute("opName")%>"  readonly>
				</div>
	
				<label class="col-sm-2 control-label">部门</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="brName" type="text" name="brName" value="<%=session.getAttribute("brName")%>" readonly>
				</div>
			</div>
		
		
		
			<div class="form-group">
				
				<label class="col-sm-2 control-label">请假类型</label>
				<div class="col-sm-4">
					<select id="leaveType" name="leaveType" class="form-control ">
						<!-- <option value="016">病假</option>
						<option value="011">事假</option>
						<option value="012">婚假</option>
						<option value="017">产假</option>
						<option value="018">倒休假</option>
						<option value="015">陪产假</option>
						<option value="014">带薪年假</option>
						<option value="013">丧假</option> -->
						
					</select> 
				</div>
			</div>
			<!--表中隐藏数据  -->
			<input class="form-control" id="leaveState" type="hidden"name="leaveState"  value="048"  >
			<input class="form-control" id="opNo" type="hidden"name="opNo"  value="<%=session.getAttribute("opNo")%>"  >
			<input class="form-control" id="brNo" type="hidden"name="brNo"  value="<%=session.getAttribute("brNo")%>"  >
			
			<div class="form-group">
				<label class="col-sm-2 control-label">开始时间</label>
				<div class="col-sm-4">
					<input class="form-control" id="inpstart" type="text" name="leaveBeginTime" placeholder="开始日期" value=""  readonly>
				</div>
	
				<label class="col-sm-2 control-label">结束时间</label>
				<div class="col-sm-4">
					
					<input class="form-control" id="inpend" type="text" name="leaveEndTime" placeholder="结束日期"  value="" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">请假时长</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="leaveSum" name="leaveSum" placeholder="字母、数字或下划线">
				</div>
				
				<label class="col-sm-2 control-label">申请时间</label>
				<div class="col-sm-4">
				
					<input type="text" value="" class="form-control" id="leaveApplytime" name="leaveApplyTime" readonly>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">详情</label>
				<div class="col-sm-4">
					<input id="leaveDetail" name="leaveDetail" class="form-control " type="text">
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
	$(function(){
		// 下拉列表加载数据字典，第一个参数是 数据字典类型，第二个参数是输入框的绑定的id
		selectdicType("leave","leaveType");
});

	
	
	//获取当前时间
	function leaveApplytime(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    return h+"-"+m+"-"+d;
}
 
document.getElementById("leaveApplytime").value = leaveApplytime();
	
	
	

		    
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
		    maxDate: '2099-06-16 23:59', //最大日期
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
		console.log("mydata:"+mydata);
		$.post("<%=request.getContextPath()%>/AddLeave.json" ,mydata , function(data){
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