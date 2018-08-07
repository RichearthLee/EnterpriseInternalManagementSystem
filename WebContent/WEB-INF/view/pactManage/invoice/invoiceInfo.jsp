<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<style type="text/css">
	.font-normal{
		font-weight: normal;
	}
</style>
<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body >
	
	<section class="content" >
	<form  class="form-horizontal" >
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">发票信息</h3>

        </div>
        <div class="box-body" id="show-div">
			<div class="form-group">
				<label class="col-sm-2 control-label ">客户名称:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.customerName }</label>
				<label class="col-sm-2 control-label ">合同金额:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.pactFee }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">已开发票金额:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoiceMoneyYet }</label>
				<label class="col-sm-2 control-label ">此次开发票金额:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoiceMoney }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">此次占合同百分比:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoicePercent }%</label>
				<label class="col-sm-2 control-label ">是否付款:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.payOrNot }</label>
				
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">开票单位:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoiceCompany }</label>
				<label class="col-sm-2 control-label ">发票抬头:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoiceTitle }</label>
				
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">联系人:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${ pactInvoice.linkman}</label>
				<label class="col-sm-2 control-label ">联系电话:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.phone}</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">邮寄地址:</label>
				<label class="col-sm-10 control-label font-normal" style="text-align: left;" >${pactInvoice.location }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">开票内容:</label>
				<label class="col-sm-10 control-label font-normal" style="text-align: left;" >${pactInvoice.reasion  }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">备注:</label>
				<label class="col-sm-10 control-label font-normal" style="text-align: left;" >${pactInvoice.description  }</label>
			</div>
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="confirm-btn"
					class="btn btn-success" style="width: 65px;">确认</button>	
				<button type="button" id="edit-btn"
					class="btn btn-success" style="width: 65px;">编辑</button>
				<label>&#12288;</label>
				<button type="button" id="back-btn" class="btn btn-default"
					style="width: 65px;">返回</button>
			</div>
		<!--show-div end  -->
		</div>
		
		<div class="box-body" id="edit-div" style="display: none;">
			<input name="id" value="${pactInvoice.id }" style="display: none;">
			<input name="pactFee" value="${pactInvoice.pactFee }" style="display: none;">
			<div class="form-group">
				<label class="col-sm-2 control-label ">客户名称:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.customerName }</label>
				<label class="col-sm-2 control-label ">合同金额:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.pactFee }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">已开发票金额:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" >${pactInvoice.invoiceMoneyYet }</label>
				<label class="col-sm-2 control-label ">此次开发票金额:</label>
				<div class="col-sm-4">
					<input name="invoiceMoney" id="invoiceMoney" class="form-control invoice-input" value="${pactInvoice.invoiceMoney }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">此次占合同百分比:</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="invoicePercent-lab">${pactInvoice.invoicePercent }%</label>
				<label class="col-sm-2 control-label ">是否付款:</label>
				<div class="col-sm-4">
				<select name="payOrNot"  class="form-control" >
					<option value="收到发票后付款" <c:if test="${pactInvoice.payOrNot == '收到发票后付款' }"> selected="selected" </c:if> >收到发票后付款</option>
					<option value="款已付补开发票" <c:if test="${pactInvoice.payOrNot == '款已付补开发票' }"> selected="selected" </c:if> >款已付补开发票</option>
				</select>
			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">开票单位:</label>
				<div class="col-sm-4">
					<select name="invoiceCompany"  class="form-control" >
						<option value="微金时代" <c:if test="${pactInvoice.invoiceCompany == '微金时代' }"> selected="selected" </c:if> >微金时代</option>
						<option value="鼎信华铭" <c:if test="${pactInvoice.invoiceCompany == '鼎信华铭' }"> selected="selected" </c:if> >鼎信华铭</option>
					</select>
				</div>
				<label class="col-sm-2 control-label ">发票抬头:</label>
				<div class="col-sm-4">
					<input name="invoiceTitle" value="${pactInvoice.invoiceTitle }" class="form-control invoice-input" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">联系人:</label>
				<div class="col-sm-4">
					<input class=" form-control invoice-input" name="linkman" value="${ pactInvoice.linkman}">
				</div>
				<label class="col-sm-2 control-label ">联系电话:</label>
				<div class="col-sm-4">
					<input class="form-control invoice-input" value="${pactInvoice.phone}" name="phone">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">邮寄地址:</label>
				<div class="col-sm-10">
					<input class="form-control invoice-input" name="location" value="${pactInvoice.location }" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">开票内容:</label>
				<div class="col-sm-10">
					<input class="form-control invoice-input" name="reasion" value="${pactInvoice.reasion  }" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">备注:</label>
				<div class="col-sm-10">
					<input class="form-control" name="description" value="${pactInvoice.description  }" >
				</div>
			</div>
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="save-btn"
					class="btn btn-success" style="width: 65px;">保存</button>	
				<label>&#12288;</label>
				<button type="button" id="cancel-btn" class="btn btn-default"
					style="width: 65px;">取消</button>
			</div>
		<!-- edit-div end  -->
		</div>
	
		
	</div>	
	</form>
	
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
		$(function(){
			//获取请求的来源
			var requestId = '${requestId}';
			//1表示来自合同详情中的请求，只需要展示信息，不需要任何操作按钮
			if(requestId == '1'){
				$("#confirm-btn").css('display','none');
				$("#edit-btn").css('display','none');
				$("#back-btn").css('display','none');
			}else{
				var roles = '${user.roles}';
				var confirmDate1 = '${pactInvoice.confirmDate}';
				//判断是否已经确认过
				if(confirmDate1 == null || confirmDate1 == ''){
					//未确认的发票
					//如果是当前系统角色是市场人员，则可编辑
					if(roles == '002'){
						$("#confirm-btn").css('display','none');
					}else{
						//如果包含财务角色，则可发票确认
						if(roles.indexOf('003') >= 0 ){
							$("#edit-btn").css('display','none');
						}else{
							$("#confirm-btn").css('display','none');
							$("#edit-btn").css('display','none');
						}
					}
				}else{
					//确认过的发票信息不需要操作按钮
					$("#confirm-btn").css('display','none');
					$("#edit-btn").css('display','none');
				}
			}
		});
		$("#confirm-btn").click(function(){
			var mydata = 'id='+ '${pactInvoice.id}' + '&pactId=' + '${pactInvoice.pactId}' +'&invoiceMoney='+ '${pactInvoice.invoiceMoney}';
			$.post(contextPath+"/pactInvoice/invoiceConfirm.json",mydata,
				function(data){
					if(data.errorcode == 0 ){
						layer.msg('确认失败',{time:1500});
					}else{
						layer.msg('确认成功',{time:1000},function(){
							window.location.href = contextPath+'/pactInvoice/getInvoiceListView';
						});
					}
			},"json");
		});
		
		$("#edit-btn").click(function(){
			$("#show-div").hide();
			$("#edit-div").show();
		});
		
		$("#back-btn").click(function(){
			window.location.href = contextPath+'/pactInvoice/getInvoiceListView';

		});
		$("#cancel-btn").click(function(){
			$("#show-div").show();
			$("#edit-div").hide();
		});
		
		$("#invoiceMoney").blur(function(){
			var invoiceMoney = $("#invoiceMoney").val();
			if($.isNumeric(invoiceMoney) && invoiceMoney >=0){
				
			}else{
				layer.tips('请填写正确金额',"#invoiceMoney",{time:2000});
				return;
			}
			var pactFee = '${pactInvoice.pactFee}';
			var percent = (100*invoiceMoney)/pactFee + '';
			percent = percent.substring(0,percent.indexOf(".") + 2) + '%';
			$("#invoicePercent-lab").text(percent);
		});
		
		$("#save-btn").click(function(){
			var result = true;
			$(".invoice-input").each(function(){
				if($(this).val() == ""){
					result = false;
					return;
				}
			});
			if(!result){
				layer.msg('请将信息填写完整',{time:2000});
				return;
			}
			var invoiceMoney = $("#invoiceMoney").val();
			if($.isNumeric(invoiceMoney) && invoiceMoney >=0){
				
			}else{
				layer.tips('请填写正确金额',"#invoiceMoney",{time:2000});
				return;
			}
			
			var mydata = $("form").serialize();
			$.post(contextPath+'/pactInvoice/updateInvoice.json',mydata,function(data){
				if(data.errorcode == '1'){
					layer.msg('修改成功',{time:1500},function(){
						window.location.href = window.location.href;
					});
				}else{
					layer.msg('修改失败',{time:2000});
				}
			},'json');
		});
		
	</script>
</body>
</html>
