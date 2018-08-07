<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mftcc.interior.pact.bean.PayProgressInfo" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<%@include file="/WEB-INF/view/head.jsp" %>
<body>
	<section class="content">
		<form id="payprogressform" class="form-horizontal">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">款项信息</h3>
				</div>
				
				<div class="box-body" id="show-div">
					<div class="form-group">
						<label class="col-sm-2 control-label">客户名称:</label> 
						<label	class="col-sm-10 control-label" style="text-align: left;">${payProgress.customerName}</label>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">第几笔收款:</label>
						<label	class="col-sm-4 control-label" style="text-align: left;">${payProgress.payyet}</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">金额:</label>
						<label	class="col-sm-4 control-label" style="text-align: left;">${payProgress.proceedMoney}</label>
						<label class="col-sm-2 control-label" style="text-align: right;">客户返款:</label>
						<label	class="col-sm-4 control-label" style="text-align: left;">${payProgress.customerMoney}</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">汇款日期:</label>
						<label	class="col-sm-4 control-label" style="text-align: left;">${payProgress.remitDate }</label>
						<label class="col-sm-2 control-label" style="text-align: right;">汇款人:</label>
						<label	class="col-sm-4 control-label" style="text-align: left;">${payProgress.remitPersion }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">银行网点:</label>
						<label class="col-sm-10 control-label" style="text-align: left;">${payProgress.remitBank }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">相关说明:</label>
						<label class="col-sm-10 control-label" style="text-align: left;">${payProgress.description }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">收款凭证:</label>
						<div class="col-sm-10">
							<c:if test="${not empty payProgress.receiptPath }">
								<a class="fa fa-fw fa-file-text-o control-label" target="_blank" href="<%=request.getContextPath()%>/download?filePath=${payProgress.receiptPath}" title="${payProgress.receiptPath}"></a>
							</c:if>
						</div>
					</div>
					<div style="text-align: center;margin-top: 20px;">

						<button type="button" id="edit-btn"
							class="btn btn-success pull-center" style="width: 65px;">编辑</button>
						<label>&#12288;</label>
						<button type="button" id="back-btn" class="btn btn-default"
							style="width: 65px;">返回</button>
					</div>
				</div>
				
				<div class="box-body" id="edit-div" style="display: none;">
					<div class="form-group">
						<label class="col-sm-2 control-label">客户名称：</label> 
						<label	class="col-sm-10 control-label" style="text-align: left;">${payProgress.customerName}</label>
					</div>
					<div>
						<input name="pactId" value="${payProgress.pactId }"	style="display: none;">
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">第几笔收款:</label>
						<div class="col-sm-4">
							<input id="payyet" name="payyet" value="${payProgress.payyet} "
								class="form-control notnull-input" readonly="readonly"
								style="width: 80%;">
						</div>
						<label class="col-sm-2 control-label" style="text-align: right;">金额:</label>
						<div class="col-sm-4">
							<input id="marketMoney" name="marketMoney"
								value="${payProgress.marketMoney} "
								class="form-control notnull-input"
								style="width: 80%;display: inline;"> <font color="red">*</font>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">汇款日期:</label>
						<div class="col-sm-4">
							<input id="remitDate" name="remitDate"
								value="${payProgress.remitDate }"
								class="form-control notnull-input"
								style="width: 80%;display: inline;" maxlength="20"> <font
								color="red">*</font>
						</div>
						<label class="col-sm-2 control-label" style="text-align: right;">汇款人:</label>
						<div class="col-sm-4">
							<input id="remitPersion" name="remitPersion"
								value="${payProgress.remitPersion }"
								class="form-control notnull-input"
								style="width: 80%;display: inline;" maxlength="50"> <font
								color="red">*</font>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" style="text-align: right;">银行网点:</label>
						<div class="col-sm-10">
							<input id="remitBank" name="remitBank"
								value="${payProgress.remitBank }"
								class="form-control notnull-input"
								style="width: 92%;display: inline;" maxlength="100"> <font
								color="red">*</font>
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">相关说明:</label>
						<div class="col-sm-10">
							<textarea id="description" name="description"
								class="form-control" value="${payProgress.description }"
								rows="2" placeholder="200字以内..." style="width:92%;"
								maxlength="200"></textarea>
						</div>
					</div>
					<div>
						<label class="col-sm-6 control-label">请上传收款凭证</label>
						<button class="btn btn-info" type="button" id="upload">
							<span class="glyphicon glyphicon-upload"></span>上传
						</button>
						<i id="receiptPath-i" class="fa fa-fw fa-file-text-o"
							style="display: none;"></i> <input name="receiptPath"
							id="receiptPath" style="border:none;display: none;"
							readonly="readonly">
					</div>
					<div style="height: 20px;"></div>
					<div style="text-align: center;">

						<button type="button" id="submit-btn"
							class="btn btn-success pull-center" style="width: 65px;">确定</button>
						<label>&#12288;</label>
						<button type="button" id="cancel-btn" class="btn btn-default"
							style="width: 65px;">返回</button>
					</div>
				</div>
			</div>
		</form>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript">
		
		$(function(){
			$("#remitDate").datepicker();
			//判断是否已经财务确认，1代表已经确认过  2表示未确认
			if('${payProgress.financialResult}' == '1'){
				$("#edit-btn").hide();
			}
			
			if('${requestId}' == '1'){
				$("#edit-btn").hide();
				$("#back-btn").hide();
			}
			
			var receiptPath = '${payProgress.receiptPath}';
			if(receiptPath == null || receiptPath == ""){
				
			}else{
				$("#receiptPath-i").css({ "display": "inline" });
				$("#receiptPath-i").attr({"title":receiptPath});
				$("#receiptPath").val(receiptPath);
			}
		});
		
		$("#edit-btn").click(function(){
			 $("#show-div").hide();
			 $("#edit-div").show();
		});
		
		 $("#cancel-btn").click(function(){
			 $("#show-div").show();
			 $("#edit-div").hide();
		});
		 
		 $("#back-btn").click(function(){
			 window.location.href = "<%=request.getContextPath()%>/payProgress/getpayProgressListView";
		 });
		 
		$("#submit-btn").click(function(){
			var marketMoney = $("#marketMoney").val();
			if($.isNumeric(marketMoney) && marketMoney>0){
			}else{
				layer.tips("请输入正确的合同金额","#marketMoney",{time:1000});
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
			
			var mydata = $("form").serialize();
			 $.post("<%=request.getContextPath()%>/payProgress/updatePayProgress.json",  mydata , function(data) {
			    	if (data.errorcode == 0) {
						layer.msg('修改失败',{time:1500});
					}else{
						layer.msg("修改款项记录成功",{time:1000},function(){
							 //parent.window.location.reload();
							window.location.href = window.location.href;
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