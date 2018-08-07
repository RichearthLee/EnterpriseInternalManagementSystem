<%@page import="com.mftcc.common.util.MathExtend"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="com.mftcc.interior.sys.bean.SysUser"%>
<%@page import="com.mftcc.interior.pact.bean.PayProgressInfo"%>
<%@page import="com.mftcc.interior.pact.bean.PactInfo"%>
<%@page import="com.mftcc.interior.pact.bean.CustomerInfo"%>
<%@page import="com.mftcc.interior.pact.bean.PaymentPlan"%>
<%@page import="com.mftcc.interior.pact.bean.PactFileInfo"%>
<%@page import="com.mftcc.interior.pact.bean.dbBean.PactInvoice"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../head.jsp" %>
<title>pactFullInfo</title>
<style type="text/css">
	body {
		background: transparent;
	}
	.attribute{
		text-align: right;
		
	}

	.div-group {
		font-size: 15px;
		line-height: 25px;
		margin-bottom: 5px;
		position: relative;
		display: block;
	}
	.labell {
		float: left;
		width: 30%;
		text-align: right;
		white-space: nowrap;
	}
	.con {
		margin-left: 35%;
		width:65%;
		padding: 0 10px;
		word-wrap: break-word;
	}
	.pactInfo {
		border: 0px;
		height: 23px;
	}
	
	.pactInfoEdit {
		border: 1px solid #b2b6be;
		height: 23px;
	}

	
</style>
</head>
<body>
<% List<PayProgressInfo> payProgressList = (List<PayProgressInfo>)request.getAttribute("payProgressList");
   PactInfo pactInfo = (PactInfo)request.getAttribute("pactInfo");
   List<PaymentPlan> paymentList = (List<PaymentPlan>)request.getAttribute("payment");
   List<PactFileInfo> pactFileList = (List<PactFileInfo>)request.getAttribute("pactFile");
   CustomerInfo customerInfo = (CustomerInfo)request.getAttribute("customer");
   String pactId = pactInfo.getPactId();
   String payPercent = pactInfo.getPaypercent().replace("%", "");
   List<SysUser> userList = (List<SysUser>)request.getAttribute("userList");
   Map<String, String> userMap = (Map<String, String>)request.getAttribute("userMap");
   String beforesale = "";
   String focusPersion = "";
   String[] str = pactInfo.getBeforesale().split(",");
  
	for(int i=0;i<str.length; i++){
		   if(i == (str.length -1)){
		   		beforesale = beforesale + userMap.get(str[i]);
		   }else{
				beforesale = beforesale + userMap.get(str[i]) + ",";
		   }
	   }
	if(beforesale.equals("")){
		beforesale = "无售前技术人员";
	}
   
   str = pactInfo.getFocusPersion().split(",");
   for(int i=0;i<str.length; i++){
	   if(i == (str.length -1)){
		   focusPersion = focusPersion + userMap.get(str[i]);
	   }else{
		   focusPersion = focusPersion + userMap.get(str[i]) + ",";
	   }
   }
%>
<!--    <div class="content-wrapper"> -->
   <section class="content" style="padding-top: 0px;padding-right: 0px;padding-left: 0px;padding-bottom: 0px;">
	<div class="row">
        <div class="col-md-6 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-yellow"><i class="glyphicon glyphicon-user"></i></span>

            <div class="info-box-content">
              <span class="info-box-text" id="customerName-span"><%=customerInfo.getCustomerName()%></span>
              <span class="info-box-number" ><%=customerInfo.getLocation()%></span>
            </div>
          </div>
        </div>
        <!-- /.col -->
       

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-6 col-sm-6 col-xs-12">
          <div class="info-box" >
           <span class="info-box-icon bg-aqua ">
			<i class="ion" style="margin-top: 15px;" >
          		<input type="text" class="knob" value="<%=payPercent%>" data-width="60" data-height="60" data-thickness="0.3" data-fgColor="white" data-border="2px" data-bgColor="aqua" data-readonly="true">
			</i>
           </span>
           
            <div class="info-box-content">
              <span class="info-box-text">合同金额：<%=pactInfo.getPactFee()%></span>
              <% if ("0".equals(pactInfo.getCustomerMoney())) { %>
              <span class="info-box-number">已收金额：<%=pactInfo.getPayfeeYet()%></span>
              <% } else { %>
              <span class="info-box-number">已收金额：<%=MathExtend.add(pactInfo.getPayfeeYet(), pactInfo.getCustomerMoney())%> （客户返款：<%=pactInfo.getCustomerMoney()%>）</span>
              <% } %>
               <span class="info-box-number">已开发票：<%=pactInfo.getInvoiceMoneyYet()%> </span>
              <%--  <span class="info-box-number">支付进度：<%=pactInfo.getPaypercent()%></span> --%>
              <%-- <div >
              		<button type="button"  class="btn btn-info btn-sm" onclick="addPayProgress();">
						<span class="glyphicon glyphicon-plus"></span> 增加收款记录
					</button>&#12288;
					<button type="button"  class="btn btn-info btn-sm" onclick="addInvoice();">
						<span class="glyphicon glyphicon-plus"></span> 发票申请
					</button>
					 <%if(pactInfo.getPaycountYet().equals("0") && pactInfo.getPactState().equals("1")) {%>
					 <form  id="PactState" style="display: inline;"> 
						<input name="pactId" value="<%=pactInfo.getPactId()%>" style="display: none;" type="text">
						<input name="pactState" value="2" style="display: none;" type="text">
						
						<button type="button" id="updatePactState" class="btn btn-default btn-sm pull-right" >
							<span class="glyphicon glyphicon-forward"></span>直接进入实施
						</button>
					 </form>
					<%} %> 
				</div> --%>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
      </div>
	<div class="row">
		<div class="col-sm-6">

			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title" style="font-weight: bold;"><%=pactInfo.getProductName() %>产品</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>


				<div class="box-body" style="display: block;">
					
					<form  id="pactAndCus">
					<input name="pactId" value="<%=pactInfo.getPactId()%>" style="display: none;">
					<input name="customerId" value="<%=customerInfo.getCustomerId()%>" style="display: none;">
					
					<div class="div-group">
						<div class="labell">客户名称:</div>
						<div class="con"><input id="customerName" name="customerName"
								value="<%=customerInfo.getCustomerName()%>" class="pactInfo part1Edit" style="width: 90%;"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">合同名:</div>
						 <div class="con">
						<input	name="pactName" id="pactName" value="<%=pactInfo.getPactName()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">合同金额:</div>
						 <div class="con">
						<input	name="pactFee" id="pactFee" value="<%=pactInfo.getPactFee()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">客户返款金额:</div>
						 <div class="con">
						<input	name="customerMoney" id="customerMoney" value="<%=pactInfo.getCustomerMoney()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">确认支付笔数:</div>
						<div class="con"><%=pactInfo.getPaycountYet()%></div>
					</div>
					<div class="div-group">
						<div class="labell">合同签约时间:</div>
						<div class="con"><%=pactInfo.getPactStartDate()%></div>
					</div>
					<div class="div-group">
						<div class="labell">收费服务开始时间:</div>
						<div class="con">
							<input id="feeDate1" class="pactInfo" value="<%=pactInfo.getFeeDate()%>" readonly="readonly">
							<input id="feeDate" name="feeDate"
								value="<%=pactInfo.getFeeDate()%>" class="pactInfo part1Edit"
								readonly="readonly" style="display: none;">
						</div>
					</div>

					<div class="div-group">
						<div class="labell">客户经理:</div>
						<div class="con"><%=userMap.get(pactInfo.getMarketerId())%></div>
					</div>
					<div class="div-group">
						<div class="labell">售前技术人员:</div>
						<div class="con">
							<input id="beforesaleName" 
								value="<%=beforesale%>" class="pactInfo part1Edit userSelect"
								readonly="readonly">
							<input id="beforesale" name="beforesale" style="display: none;" value="<%=pactInfo.getBeforesale()%>">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">售后人员:</div>
						<div class="con">
							<label id="aftersaleName" style="font-weight: normal;">${empty pactInfo.aftersale ? '无售后':pactInfo.aftersale}</label>
							<select id="aftersale" name="aftersale" style="display: none;width: 153px;">
								<option value="无售后">无售后</option>
								<option value="售后">售后</option>
								<option value="开发">开发</option>
							</select>
						</div>
					</div>

					<div class="div-group">
						<div class="labell">合同关注人员:</div>
						<div class="con"><input id="focusPersionName" 
								value="<%=focusPersion%>" class="pactInfo part1Edit"
								readonly="readonly" style="width: 80%;">
								<input id="focusPersion" name="focusPersion" type="text" style="display: none;" value="<%=pactInfo.getFocusPersion()%>">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">客户联系人:</div>
						<div class="con"><input id="linkman" name="linkman"
								value="<%=customerInfo.getLinkman()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">联系人职务:</div>
						<div class="con">
							<input id="linkmanTitle" name="linkmanTitle" value="<%=customerInfo.getLinkmanTitle()%>" 
							class="pactInfo part1Edit" readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">办公电话:</div>
						<div class="con"><input id="tetephone" name="tetephone"
								value="<%=customerInfo.getTetephone()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">手机号码:</div>
						<div class="con"><input id="mobilephone" name="mobilephone"
								value="<%=customerInfo.getMobilephone()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					<div class="div-group">
						<div class="labell">邮箱地址:</div>
						<div class="con"><input id="email" name="email"
								value="<%=customerInfo.getEmail()%>" class="pactInfo part1Edit"
								readonly="readonly">
						</div>
					</div>
					 <div class="div-group" >
						<div class="labell">合同附件:</div>
						<div class="con" id="pactfile-div">
							<%if(pactFileList.size() > 0) {%>
						
							<%for(int i=0;i<pactFileList.size(); i++) {%>
							<a  class="fa fa-fw fa-file-text-o" target="_blank" href="<%=request.getContextPath()%>/download?filePath=<%=pactFileList.get(i).getPactFilepath()%>" title="<%=pactFileList.get(i).getPactFilepath()%>">
							</a>
						<%} %>	
						<%} else{%><lable>无</lable><%} %>
						</div>
					</div>
					<div class="div-group">
						<div class="labell" >合同相关描述:</div>
						<div class="con">
						
							<label id="description1" style="font-weight:normal;"><%if(pactInfo.getDescription().equals("")) {%>无<%} else{%><%=pactInfo.getDescription() %><%} %></label>
							<textarea id="description" name="description" class="form-control" placeholder="200字以内" maxlength="200"
								rows="3" readonly="readonly" style="background: transparent;width: 90%;display: none;"><%=pactInfo.getDescription() %></textarea>
						</div>
					</div>
					</form>
					
				
					
					<div>
						<div class="col-sm-3">
							<button  id="upload" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-upload"></span>上传附件
							</button>
						</div>
						<button id="btnUpdatePactInfo" class="btn btn-info btn-sm">
							<span class="glyphicon glyphicon-edit"></span> 编辑
						</button>
						<button id="btnSavePactInfo" style="display: none;" class="btn btn-info btn-sm">
							<span class="glyphicon glyphicon-floppy-save"></span> 保存
						</button>
						<!-- <button id="btnCancelPactInfo" style="display: none;" class="btn btn-info btn-sm">
							<span class="glyphicon glyphicon-floppy-save"></span> 取消
						</button> -->
					</div>



					
					
				</div>

			</div>
			<div class="box  box-success">
				<div class="box-header with-border">
					<h3 class="box-title" style="font-weight: bold;">实施结果反馈</h3>&#12288;
					<%if(pactInfo.getPaycountYet().equals("0") && pactInfo.getPactState().equals("1")) {%>
						<a href="javaScript:void(0);" onclick="updatePactState(this);">直接进入实施</a> 
					
					<%} %> 
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="box-body" style="display: block;">
					<%if(pactInfo.getExecuteStartDate() == null) {%>
					<h4 style="font-weight: bold;">没有实施结果反馈，可能尚未实施或在实施中</h4>
					<%} else{%>
					<form id="updateExecuteResult" class="form-horizontal">
					<div class="form-group" style="margin-bottom: 0px;">
					
						<label class="col-sm-4 control-label" style="font-weight: normal;">实施人员:</label> 
							<label class="col-sm-8 control-label" style="text-align: left;font-weight: normal;"><%=userMap.get(pactInfo.getExecutePersion())%></label>
					</div>
					<div class="form-group" style="margin-bottom: 0px;">
						<label class="col-sm-4 control-label" style="font-weight: normal;">实施时间:</label> <label
							class="col-sm-8 control-label" style="text-align: left;font-weight: normal;"><%=pactInfo.getExecuteStartDate()%></label>
					</div>
					<div class="form-group" style="margin-bottom: 0px;">
							<label class="col-sm-4 control-label" style="font-weight: normal;">实施反馈表:</label>
							<label class="col-sm-2 control-label" style="text-align: left;font-weight: normal;" >
							<%if(!StringUtil.isEmpty(pactInfo.getExecuteFilepath() )) {%>
								<a  class="fa fa-fw fa-file-text-o" target="_blank" href="<%=request.getContextPath()%>/download?filePath=<%=pactInfo.getExecuteFilepath()%>" title="<%=pactInfo.getExecuteFilepath()%>">
								</a>
							<%} else{%>无<%} %>
							</label>
					</div>
					<div class="form-group" style="margin-bottom: 0px;">
						<label class="col-sm-4 control-label" style="font-weight: normal;">实施相关情况描述:</label>
						<label class="col-sm-8 control-label" style="text-align: left;font-weight: normal;"><%if(StringUtil.isEmpty(pactInfo.getExecuteResult())) {%>无<%} else{%><%=pactInfo.getExecuteResult() %><%} %></label>
					</div>
					
						<input name="pactId" value="<%=pactId%>" style="display: none;" type="text">
						<div class="form-group" style="margin-bottom: 0px;">
							<label class="col-sm-4 control-label" style="font-weight: normal;">市场反馈结果:</label>
							<label class="col-sm-8 control-label" style="text-align: left;font-weight: normal;" id="customerResultLabel">
								<%if(StringUtil.isEmpty(pactInfo.getCustomerResult())){%>无<%} else{%><%=pactInfo.getCustomerResult()%><%} %>
							</label>
							<div class="col-sm-8" id="customerResultDiv" style="display: none;">
								<textarea id="customerResult" name="customerResult"
									class="form-control" rows="3"  maxlength="200" placeholder="200字以内"
									style="background: transparent;" ><%=StringUtil.killNull(pactInfo.getCustomerResult())%></textarea>
							</div>
						</div>
						<div class="form-group" style="margin-bottom: 0px;">
							<button type="button" id="btnUpdateExecute" class="btn btn-info btn-sm" style="margin-left: 200px;">
								<span class="glyphicon glyphicon-edit"></span>编辑
							</button>
							<button type="button" id="btnSaveExecute" class="btn btn-info btn-sm" style="display: none;margin-left: 200px;">
								<span class="glyphicon glyphicon-floppy-save"></span>保存
							</button>
						</div>
					</form>
					<%} %>
				
			</div>
			</div>

			
		</div>
		<div class="col-sm-6">

			<div class="box  box-success">
				<div class="box-header with-border">
					<h3 class="box-title" style="font-weight: bold;">收款计划</h3>
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="box-body" style="display: block;">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>预计第几笔收款</th>
								<th>收款比例</th>
								<th>收款时间</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < paymentList.size(); i++) {
						%>
							<tr>
								<td><%=paymentList.get(i).getPaymentCount()%></td>
								<td><%=paymentList.get(i).getPaymentPercent()%></td>
								<td><%if(paymentList.get(i).getPaymentStartDate().equals("2")){%>实施完成后<%=paymentList.get(i).getPaymentDay() %>天内
									<%} else{%><%=paymentList.get(i).getPaymentDay()%>前
									<%} %>
								</td>
								<%if(paymentList.get(i).getPaymentState().equals("1")) {%>
									<td class="text-aqua">未收</td>
									<%} else if(paymentList.get(i).getPaymentState().equals("2")) {%>
									<td>已收</td><%}
									else if(paymentList.get(i).getPaymentState().equals("3")) {%>
									<td class="text-yellow">部分收取</td><%}%>
								
							</tr>
							<%
							}
						%>

						</tbody>
					</table>
				</div>

			</div>
			<div class="box box-success ">
				<div class="box-header with-border">
					<h3 class="box-title" style="font-weight: bold;">款项记录</h3>&#12288;
					<a href="javaScript:void(0);" onclick="addPayProgress();">新增记录</a> 
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="box-body" style="display: block;">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>汇款时间</th>
								<th>金额</th>
								<th>客户返款</th>
								<th>凭证</th>
								<th>确认时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="payProgress" items="${payProgressList }">
							<tr>
								<td>
									<c:out value="${payProgress.remitDate }"></c:out>
								</td>
								<td>
									<c:out value="${payProgress.proceedMoney }"></c:out>
								</td>
								<td>
									<c:out value="${payProgress.customerMoney }"></c:out>
								</td>
								<td>
									<c:if test="${not empty payProgress.receiptPath}">
										<a class="fa fa-fw fa-file-text-o" target="_blank" href="<%=request.getContextPath()%>/download?filePath=${payProgress.receiptPath}" title="${payProgress.receiptPath}"></a>
									</c:if>
								</td>
								<td>
									<c:out value="${empty payProgress.financialDate?'未确认' : payProgress.financialDate }"></c:out>
								</td>
								<td>
									<a href="javaScript:void(0);" onclick="toPayprogressDetail('${payProgress.pactId}','${payProgress.payyet}');">详情</a>
								</td>
							</tr>
						</c:forEach>
					
						</tbody>
					</table>
				</div>
			</div>
			<div class="box  box-success">
				<div class="box-header with-border">
					<h3 class="box-title" style="font-weight: bold;">发票记录</h3>&#12288;
					<a href="javaScript:void(0);" onclick="addInvoice();">新增记录</a> 
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="box-body" style="display: block;">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>申请时间</th>
								<th>金额</th>
								<th>确认时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="pactInvoice-tbody">
						<c:forEach var="pactInvoice" items="${pactInvoiceList }">
							<tr>
								<td>
									<c:out value="${pactInvoice.marketDate }"></c:out>
								</td>
								<td>
									<c:out value="${pactInvoice.invoiceMoney }"></c:out>
								</td>
								<td>
									<c:out value="${empty pactInvoice.confirmDate?'未确认' : pactInvoice.confirmDate }"></c:out>
								</td>
								<td>
									<a href="javaScript:void(0);" onclick="toInvoiceDetail('${pactInvoice.id}');">详情</a>
								</td>
							</tr>
						</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
			

		</div>
	</div>
	<div class="box-body" id="user-select" style="display: none;">
		<div class="form-group">
			<div class="checkbox" id="noSaleBefore-div" style="display: none;">
				<label> 
					<input type="checkbox" name="userSelect" value="" data-username="无售前技术人员">无售前技术人员
				</label>
			</div>
		<%for(int i=0 ;i<userList.size(); i++) {%>
			<div class="checkbox">
				<label> <input type="checkbox" name="userSelect"
					 value="<%=userList.get(i).getOpNo()%>" data-username="<%=userList.get(i).getOpName() %>"><%=userList.get(i).getOpName() %>
				</label>
			</div>
		<%} %>
		</div>
	</div>
	<div class="box"  id="invoiceAdd-div" style="display: none;">
		<form id="invoiceAdd-form" class="form-horizontal">
		<div class="box-body">
		<input name="pactId" value="${pactInfo.pactId }" style="display: none;">
		<input name="pactFee" value="${pactInfo.pactFee }" style="display: none;">
		<input name="pactName" value="${pactInfo.pactName }" style="display: none;">
		<input name="invoiceMoneyYet" value="${pactInfo.invoiceMoneyYet }" style="display: none;">
		<input name="customerName" value="${customer.customerName }" style="display: none;">
		<input name="customerId" value="${customer.customerId }" style="display: none;">
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">发票抬头：</label>
			<div class="col-sm-4">
				<input name="invoiceTitle" value="${customer.customerName }" class="form-control invoice-input" type="text">
			</div>
			<label class="col-sm-2 control-label">发票金额：</label>
			<div class="col-sm-4">
				<input name="invoiceMoney" id="invoiceMoneyAdd" class="form-control invoice-input" type="text">
			</div>
		</div>
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">是否付款：</label>
			<div class="col-sm-4">
				<select name="payOrNot"  class="form-control invoice-input" >
					<option value="收到发票后付款">收到发票后付款</option>
					<option value="款已付补开发票">款已付补开发票</option>
				</select>
			</div>
			<label class="col-sm-2 control-label">开发票单位：</label>
			<div class="col-sm-4">
				<select name="invoiceCompany"  class="form-control invoice-input" >
					<option value="微金时代">微金时代</option>
					<option value="鼎信华铭">鼎信华铭</option>
				</select>
			</div>
			
		</div>
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">联系人：</label>
			<div class="col-sm-4">
				<input name="linkman" value="${customer.linkman }" class="form-control invoice-input" type="text">
			</div>
			<label class="col-sm-2 control-label">联系电话：</label>
			<div class="col-sm-4">
				<input name="phone" value="${customer.tetephone }" class="form-control invoice-input" type="text">
			</div>
			
		</div>
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">邮寄地址：</label>
			<div class="col-sm-10">
				<input name="location" maxlength="100" class="form-control invoice-input" type="text">
			</div>
		</div>
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">开票内容：</label>
			<div class="col-sm-10">
				<input name="reasion" maxlength="100" class="form-control invoice-input" type="text">
			</div>
		</div>
		<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-10">
				<input name="description" maxlength="100" class="form-control invoice-input" type="text">
			</div>
		</div>
		</div>
		</form>
	</div>
	</section>
<!-- 	</div> -->

	<%@include file='../bottom.jsp' %>
    <!--  <script src="<%=request.getContextPath()%>/pactManagejs/pactFullInfo.js"></script> -->
    <script src="<%=request.getContextPath()%>/AdminLTE/plugins/knob/jquery.knob.js"></script>
	<script>
	var layerLoadIndex = layer.load();
	function updatePactState(obj){
		layer.open({
		    content: '尚未收款，确认进入实施?',
		   // offset: 50,
		    btn: ['确认', '取消']
		    ,yes: function(){
		    	var mydata = 'pactId='+ '${pactInfo.pactId }' + '&pactState=2';
				$.post("<%=request.getContextPath()%>/pactinfoupdate.json" ,mydata , function(data){
				if(data.errorcode == 0)
				{
					layer.alert("设置失败");
				}else{
					//$("#updatePactState").hide();
					$(obj).hide();
					layer.alert("此合同已可以实施");
				}
				}, "json");
		    },cancel: function(){ //或者使用btn2
		        //按钮【按钮二】的回调
		    }
		});
	};
	
	
	
	function addInvoice(){
		layer.open({
			type : 1,
			maxmin: true,
			area : [ '80%', '400px' ],
			title : '增加发票申请', //不显示标题
			btn : [ '确定', '取消' ],
			content : $("#invoiceAdd-div"), //捕获的元素
			success : function(lay, index)
			{
			},
			yes: function(index){
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
				var invoiceMoney = $("#invoiceMoneyAdd").val();
				if($.isNumeric(invoiceMoney) && invoiceMoney >=0){
					
				}else{
					layer.tips('请填写正确金额',"#invoiceMoneyAdd",{time:2000});
					return;
				}
				var mydata = $("#invoiceAdd-form").serialize();
				$.post(contextPath+"/pactInvoice/invoiceAdd.json",mydata,
					function(data){
					if(data.errorcode == 0){
						layer.msg('增加失败',{time:1500});
					}else{
						layer.msg('增加成功',{time:1500},function(){
							var htmlstr = '<tr><td>'+ data.dateNow +'</td>'
										+'<td>' + $("#invoiceMoneyAdd").val()+ '</td>'
										+'<td>未确认</td></tr>';
							$("#pactInvoice-tbody").append(htmlstr);
							layer.close(index);
							//window.location.href = window.location.href;
						});
					}
				},"json");
				
			},
			end : function(index) {
				layer.close(index);
			}
		});

	};
	$("#upload").on("click",function(){
		uploadOneFile(function (file) {
			// 获取到上传文件保存后的地址。
			if(file){
				var pactId = '<%=pactInfo.getPactId()%>';
				var mydata = "pactFilepath="+file+"&pactId="+pactId;
				$.post("<%=request.getContextPath()%>/addPactFilepath.json",mydata,function(data){
					if(data.errorcode == 0){
						layer.alert("上传失败");
					}else{
						var htmlStr = "";
						htmlStr = '<a  class="fa fa-fw fa-file-text-o" target="_blank" '
								+	'href="<%=request.getContextPath()%>/download?filePath=' +file
								+	'\"title=\"'+file+'\"></a>';
						$("#pactfile-div>lable").remove();
						$("#pactfile-div").append(htmlStr);
					}
				},"json");
				
			}
		});
	});
	$("#feeDate").datepicker();
	$("#focusPersionName").click(function(){
		if ($("#focusPersionName").attr("readonly")) {
			return;
		}
		layer.open({
			type : 1,
			area : [ '300px', '280px' ],
			title : '请选择合同关注人', //不显示标题
			btn : [ '确定', '取消' ],
			content : $("#user-select"), //捕获的元素
			success : function(lay, index)
			{
				$("input[type=checkbox]").attr("checked",false);
			},
			yes: function(index){
				
					var length = $("input:checkbox:checked").length;
					var focusPersion ="";
					var focusPersionName = "";
					$("input:checkbox:checked").each(function(i){
						if(i == length-1){
							focusPersion = focusPersion + $(this).val();
							focusPersionName = focusPersionName + $(this).data("username");
						}else{
							focusPersion = focusPersion + $(this).val() + ",";
							focusPersionName = focusPersionName + $(this).data("username") + ",";
						}
						
					});
					$("#focusPersion").val(focusPersion);
					$("#focusPersionName").val(focusPersionName);
					layer.close(index);
				
						
			},
			end : function(index) {
				layer.close(index);
			}
		});
	});
	
	$("#beforesaleName").click(function(){
		if ($("#beforesaleName").attr("readonly")) {
			return;
		}
		layer.open({
			type : 1,
			area : [ '300px', '280px' ],
			title : '请选择售前技术人员', //不显示标题
			btn : [ '确定', '取消' ],
			content : $("#user-select"), //捕获的元素
			success : function(lay, index)
			{
				$("input[type=checkbox]").attr("checked",false);
				$("#noSaleBefore-div").css('display','block');
			},
			yes: function(index){
				
				var length = $("input:checkbox:checked").length;
				
					var beforeSale ="";
					var beforeSaleName = "";
					$("input:checkbox:checked").each(function(i){
						if(i == length-1){
							beforeSale = beforeSale + $(this).val();
							beforeSaleName = beforeSaleName + $(this).data("username");
						}else{
							beforeSale = beforeSale + $(this).val() + ",";
							beforeSaleName = beforeSaleName + $(this).data("username") + ",";
						}
						
					});
					$("#beforesale").val(beforeSale);
					$("#beforesaleName").val(beforeSaleName);
					$("#noSaleBefore-div").css('display','none');
					layer.close(index);
				
						
			},
			end : function(index) {
				$("#noSaleBefore-div").css('display','none');
				layer.close(index);
			}
		});
	});
	
	 $(function () {
		layer.close(layerLoadIndex);
		 	//日期选择器
			
		    /* jQueryKnob */
		    $(".knob").knob({
		      draw: function () {
		        // "tron" case
		        if (this.$.data('skin') == 'tron') {

		          var a = this.angle(this.cv)  // Angle
		              , sa = this.startAngle          // Previous start angle
		              , sat = this.startAngle         // Start angle
		              , ea                            // Previous end angle
		              , eat = sat + a                 // End angle
		              , r = true;

		          this.g.lineWidth = this.lineWidth;

		          this.o.cursor
		          && (sat = eat - 0.3)
		          && (eat = eat + 0.3);

		          if (this.o.displayPrevious) {
		            ea = this.startAngle + this.angle(this.value);
		            this.o.cursor
		            && (sa = ea - 0.3)
		            && (ea = ea + 0.3);
		            this.g.beginPath();
		            this.g.strokeStyle = this.previousColor;
		            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
		            this.g.stroke();
		          }

		          this.g.beginPath();
		          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
		          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
		          this.g.stroke();

		          this.g.lineWidth = 2;
		          this.g.beginPath();
		          this.g.strokeStyle = this.o.fgColor;
		          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
		          this.g.stroke();

		          return false;
		        }
		      }
		    });
	 });
	
	function addPayProgress(){
		layer.open({
	        type: 2,
	        title: '增加收款记录',
	        maxmin: true,
	        area : ['90%' , '480px'],
	        offset: 50,
	        content: '<%=request.getContextPath()%>/payProgress/payprogressadd/<%=pactId%>',
	        scrollbar: false
	    });
	};
	
	function toPayprogressDetail(pactId,payyet){
		layer.open({
			title: false,
			area : ['900px' , '400px'],
			type : 2,
			maxmin : true,
			content : contextPath + '/payProgress/getPayProgressView?requestId=1&pactId='+ pactId + '&payyet=' + payyet
		});
	};
	
	function toInvoiceDetail(invoiceId){
		layer.open({
			title: false,
			area : ['900px' , '570px'],
			type : 2,
			maxmin : true,
			content : contextPath + '/pactInvoice/getOneInvoiceInfo?requestId=1&id='+ invoiceId
		});
	};
	
	$("#updatePactState").click(function(){
	
			layer.open({
			    content: '尚未收款，确认进入实施?',
			    offset: 50,
			    btn: ['确认', '取消']
			    ,yes: function(){
			    	var mydata = $("#PactState").serialize();
					$.post("<%=request.getContextPath()%>/pactinfoupdate.json" ,mydata , function(data){
					if(data.errorcode == 0)
					{
						layer.alert("设置失败",{offset:50});
					}else{
						$("#updatePactState").hide();
						layer.alert("此合同已可以实施",{offset:50});
					}
					}, "json");
			    },cancel: function(){ //或者使用btn2
			        //按钮【按钮二】的回调
			    }
			});
	});
	var customerResult;
	var feeDate;
	var beforesaleName;
	var tetephone;
	var mobilephone;
	var email;
	var description;
	var linkman;
	var pactName;
	var linkmanTitle;
	var customerName;
	var aftersale;
	$("#btnUpdateExecute").click(function(){
		$(this).hide();
		$("#btnSaveExecute").show();
		$("#customerResultDiv").css({ "display": "inline" });
		$("#customerResultLabel").css({ "display": "none" });
		customerResult = $("#customerResult").val();
	});
	//更新客户反馈结果
	$("#btnSaveExecute").click(function(){
		$(this).hide();
		
		var mydata = $("#updateExecuteResult").serialize();
		$.post("<%=request.getContextPath()%>/pactinfoupdate.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				alert("修改客户反馈结果失败");
				$("#customerResultDiv").val(customerResult);
				$("#customerResultLabel").text(customerResult);
				
			}else{
				$("#customerResultLabel").css({ "display": "inline" });
				$("#customerResultDiv").css({ "display": "none" });
				$("#customerResultLabel").text($("#customerResult").val());
				alert("修改成功");
			}
		}, "json");
		$("#btnUpdateExecute").show();
		
	});
	$("#btnUpdatePactInfo").click(function(){
		$(this).hide();
		$("#btnSavePactInfo").show();
		//$("#btnCancelPactInfo").show();
		//$("#feeDate").addClass("date_picker");
		//$(".date_picker").datepicker();
		$(".part1Edit").removeClass("pactInfo");
		$(".part1Edit").addClass("pactInfoEdit");
		$(".part1Edit").removeAttr("readonly");
		$("#feeDate1").css({ "display": "none" });
		$("#feeDate").css({ "display": "inline" });
		$("#description1").css({ "display": "none" });
		$("#description").removeAttr("readonly");
		$("#description").css({ "display": "block" });
		$("#aftersaleName").hide();
		$("#aftersale").show();
		feeDate = $("#feeDate").val();
		beforesaleName = $("#beforesaleName").val();
		tetephone = $("#tetephone").val();
		mobilephone = $("#mobilephone").val();
		email = $("#email").val();
		linkman = $("#linkman").val();
		description = $("#description").val();
		pactName = $("#pactName").val();
		linkmanTitle = $("#linkmanTitle").val();
		customerName = $("#customerName").val();
		aftersale = $("#aftersaleName").text();
	});
	//更新客户和合同信息
	$("#btnSavePactInfo").click(function(){
		$(this).hide();
		//$("#feeDate").removeClass("date_picker");
		$("#feeDate1").css({ "display": "inline" });
		$("#feeDate").css({ "display": "none" });
		$("#description1").css({ "display": "inline-block" });
		$("#description").css({ "display": "none" });
		$("#btnUpdatePactInfo").show();
		$(".part1Edit").removeClass("pactInfoEdit");
		$(".part1Edit").addClass("pactInfo");
		$(".part1Edit").attr({readonly: "true"});
		$("#description").attr({readonly: "true"});
		$("#aftersaleName").css({ "display": "inline-block" });
		$("#aftersale").css({ "display": "none" });
		var mydata = $("#pactAndCus").serialize();
		$.post("<%=request.getContextPath()%>/updatePactAndCus.json" ,mydata , function(data){
			if(data.errorcode == 0)
			{
				alert("修改信息失败");
				$("#feeDate").val(feeDate);
				$("#feeDate1").val(feeDate);
				$("#beforesaleName").val(beforesaleName);
				$("#tetephone").val(tetephone);
				$("#mobilephone").val(mobilephone);
				$("#linkman").val(linkman);
				$("#linkmanTitle").val(linkmanTitle);
				$("#email").val(email);
				$("#description").val(description);
				$("#description1").text(description);
				$("#pactName").val(pactName);
				$("#customerName").val(customerName);
			}else if(data.errorcode == 1){
				$("#aftersaleName").text($("#aftersale").val());
				$("#feeDate1").val($("#feeDate").val());
				$("#description1").text($("#description").val());
				$("#phone_span").text($("#tetephone").val());
				$("#customerName-span").text($("#customerName").val());
				alert("修改成功");
			}
		}, "json");
		
	});
	</script>
</body>
</html>