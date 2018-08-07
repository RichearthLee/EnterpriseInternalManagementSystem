<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mftcc.interior.pact.bean.PayProgressInfo" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<title>add payprogress</title>
<style type="text/css">
.form-horizontal.form-group{
	padding-left: 0px;
	padding-right: 0px;
}
</style>
</head>



<body>
	<% PayProgressInfo payProgress = (PayProgressInfo)request.getAttribute("payProgressData");
		String pactId = payProgress.getPactId();
		String payYet = payProgress.getPayyet();
	%>
	<form action="" id="payprogressform" class="form-horizontal">
		<div style="height: 30px;" class="form-group">
			<input name="pactId" value="<%=pactId%>" style="display: none;">
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="text-align: right;">第几笔收款:</label>
			<div class="col-sm-4">
				<input id="payyet" name="payyet" value="<%=payYet%>" class="form-control notnull-input" readonly="readonly" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="text-align: right;">金额:</label>
			<div class="col-sm-4">
				<input id="proceedMoney" name="proceedMoney" class="form-control notnull-input" style="width: 80%;display: inline;">
				<font color="red">*</font>
			</div>
			<label class="col-sm-2 control-label" style="text-align: right;">客户返款:</label>
			<div class="col-sm-4">
				<input id="customerMoney" name="customerMoney" class="form-control notnull-input" style="width: 80%;display: inline;">
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="text-align: right;">汇款日期:</label>
			<div class="col-sm-4">
				<input id="remitDate" name="remitDate"  class="form-control notnull-input" style="width: 80%;display: inline;" maxlength="20">
				<font color="red">*</font>
			</div>
			<label class="col-sm-2 control-label" style="text-align: right;">汇款人:</label>
			<div class="col-sm-4">
				<input id="remitPersion" name="remitPersion" class="form-control notnull-input" style="width: 80%;display: inline;" maxlength="50">
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="text-align: right;">银行网点:</label>
			<div class="col-sm-10">
				<input id="remitBank" name="remitBank"  class="form-control notnull-input"  style="width: 92%;display: inline;" maxlength="100">
				<font color="red">*</font>
			</div>
			
		</div>
		<div class="form-group">
				<label class="col-sm-2 control-label">相关说明:</label>
				<div class="col-sm-10">
					<textarea id="description" name="description" class="form-control"
						rows="2" placeholder="200字以内..." style="width:92%;" maxlength="200"></textarea>
				</div>
		</div>
		<div >
			<label class="col-sm-6 control-label">请上传收款凭证</label>
			<button class="btn btn-info" type="button" id="upload">
				<span class="glyphicon glyphicon-upload"></span>上传
			</button>
			<i  id="receiptPath-i" class="fa fa-fw fa-file-text-o" style="display: none;"></i>
			<input name="receiptPath" id="receiptPath" style="border:none;display: none;" readonly="readonly" >
		</div>
		<div style="height: 20px;"></div>
		<div >
			<div class="col-sm-6"></div>
			<button type="button" id="submit" class="btn btn-success pull-center"
				style="width: 65px;">确定</button>
			<!-- <label>&#12288;</label>
			<button type="button" id="cancel" class="btn btn-default" style="width: 65px;">返回</button> -->
		</div>
	</form>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript">
		<%-- $("#cancel").click(function(){
			window.location.href = "<%=request.getContextPath()%>/pactListInfo";
		}); --%>
		var destFile = '';
		$(function(){
			$("#remitDate").datepicker();
		});
		$("#submit").click(function(){
			var proceedMoney = $("#proceedMoney").val();
			if($.isNumeric(proceedMoney) && proceedMoney>0){
			}else{
				layer.tips("请输入正确的收款金额","#proceedMoney",{time:1000});
				return false;
			}
			var customerMoney = $("#customerMoney").val();
			if($.isNumeric(customerMoney)){
			}else{
				layer.tips("请输入正确的客户返款金额","#customerMoney",{time:1000});
				return false;
			}
			var notnullResult = true;
			$(".notnull-input").each(function(){
				var value = $.trim($(this).val());
				if(value == ''){
					notnullResult = false;
					return false;
				}
			});
			if(!notnullResult){
				layer.msg("必填项不能为空");
				return false;
			}
			/* if(destFile == ''){
				layer.tips('请上传附件','#upload',{time:1000});
				return false;
			} */
			var mydata = $("form").serialize();
			 $.post("<%=request.getContextPath()%>/payProgress/payprogressinsert.json",  mydata , function(data) {
			    	if (data.errorcode == 0) {
						alert(data.errormessage);
					}else{
						layer.msg("增加款项记录成功",{time:1000},function(){
							 parent.window.location.reload();
							// window.returnValue = true;
						});
					}
			    	
				}, "json");
			
				return false;
		});
		$("#upload").on("click", function () {
			uploadOneFile(function (file) {
				if (file) {
					destFile = file;
					$("#receiptPath-i").css({ "display": "inline" });
					$("#receiptPath-i").attr({"title":file});
					$("#receiptPath").val(file);
				}
			});
		});
	</script>
</body>
</html>