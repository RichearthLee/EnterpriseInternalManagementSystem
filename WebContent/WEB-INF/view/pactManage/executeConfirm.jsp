<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.mftcc.interior.pact.bean.PactInfo" %>
<%PactInfo pactInfo = (PactInfo)request.getAttribute("data"); 
	String pactId = pactInfo.getPactId();%>	
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<title>execute Confirm</title>

</head>
<body style="min-height: 410px;">
<section class="content">
	<form id="executeform" class="form-horizontal">
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">公司名称：<%=pactInfo.getCustomerName()%></h3>
			<input id="pactId" name="pactId" value="<%=pactId%>" style="display: none;">
		</div>
		<div class="box-body">
			<div class="form-group">
				<label>实施时间:</label>
				<input type="text" id="executeDate" name="executeDate"
					class="form-control">
			</div>
			<!-- /.form group -->
			
			<div class="form-group">
				<label>实施情况说明:</label>
				<textarea id="executeResult" name="executeResult" class="form-control" rows="3"
					 placeholder="请录入实施结果相关说明" maxlength="200"></textarea>
			</div>
			<!-- /.form group -->
			
			<div class="form-group">
				<button id="upload" class="btn btn-info" type="button">
				<span class="glyphicon glyphicon-upload"></span>上传实施确认表</button>
				<i  id="executeFilepath-i" class="fa fa-fw fa-file-text-o" style="display: none;"></i>
				<input id="executeFilepath" name="executeFilepath" style="display: none;">
				<br />
			</div>
			<!-- /.form group -->
		</div>
		<!-- /.box-body -->
		<div class="box-footer" >
            <button type="button" id="submit" class="btn btn-success">确定</button>&#12288;
            <button type="button" id="cancel" class="btn btn-default">取消</button>
        </div>
	</div>
	</form>
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript">
	$(function(){
		$("#executeDate").datepicker();
	});
	$("#cancel").click(function(){
		<%-- var file = $("#executeFilepath").val();
		if(file != ''){
			$.post("<%=request.getContextPath()%>/deleteFile?destFile="+file);
		} --%>
		window.location.href = "<%=request.getContextPath()%>/pactexecute";
	});
	$("#submit").click(function(){
		if($("#executeDate").val() == "")
		{
			layer.msg("请确认实施时间",{time:1000,offset:'25%'});
			return false;
		}
		var mydata = $("form").serialize();
	    //var mydataa= $("form").serializeArray(); 传入json，control层也可以接收到数据
	    $.post("<%=request.getContextPath()%>/executeConfirm.json",  mydata , function(data) {
	    	if (data.errorcode == 0) {
				alert(data.errormessage);
				return false;
			}else{
				alert("实施确认成功");
				window.location.href = "<%=request.getContextPath()%>/pactexecute";
			}
	    	
		}, "json");
		
		return false;	
	});
	
	$("#upload").on("click", function () {
		uploadOneFile(function (file) {
			//alert(file);
			if (file) {
				//alert(file);
				$("#executeFilepath-i").css({ "display": "inline" });
				$("#executeFilepath-i").attr({"title":file});
				$("#executeFilepath").val(file);
			}
		});
	});
	
	</script>
</body>
</html>