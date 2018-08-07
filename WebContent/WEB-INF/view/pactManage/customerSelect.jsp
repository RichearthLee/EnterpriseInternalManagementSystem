<%@page import="java.util.List"%>
<%@page import="com.mftcc.interior.pact.bean.CustomerInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pactFullinfo</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<%
	List<CustomerInfo> customerList = (List<CustomerInfo>)request.getAttribute("customerInfos");
%>
<section class="content" style="min-height: 0px;">
<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>操作</th>
					<th>客户名称</th>
				</tr>
			</thead>
			<tbody>
			<%for(int i=customerList.size()-1 ; i >= 0 ; i--) {
				CustomerInfo customer = customerList.get(i);
			%>
				<tr id="customerInfo_<%=customer.getCustomerId()%>">
					<td><input type="radio"  name="customerRadio" value="<%=customer.getCustomerId()%>"></td>
					<td>
						<input type="text" name="customerName" val="true"  value="<%=customer.getCustomerName()%>" style="display: none;">
						<%=customerList.get(i).getCustomerName() %>
					</td>
					<td style="display: none;"><input type="text" name="linkman" val="true"  value="<%=customer.getLinkman()%>" ></td>
					<td style="display: none;"><input type="text" name="customerId" val="true"  value="<%=customer.getCustomerId()%>" ></td>
					<td style="display: none;"><input type="text" name="email" val="true"  value="<%=customer.getEmail()%>" ></td>
					<td style="display: none;"><input type="text" name="location" val="true"  value="<%=customer.getLocation()%>" ></td>
					<td style="display: none;"><input type="text" name="mobilephone" val="true"  value="<%=customer.getMobilephone()%>" ></td>
					<td style="display: none;"><input type="text" name="tetephone" val="true"  value="<%=customer.getTetephone()%>" ></td>
					<td style="display: none;"><input type="text" name="linkmanTitle" val="true"  value="<%=customer.getLinkmanTitle()%>" ></td>
				</tr>
				<%} %>
				

			</tbody>
		</table>
		<div style="text-align: right;">
			<button type="button" class="btn btn-default" id="submit">确定</button>
			<button type="button" class="btn btn-default" id="cancel">取消</button>
		</div>
		</div>
</section>
	<%@include file='../bottom.jsp' %>
	<script
		src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
		
		$("#submit").click(function(){
			var data = $('input[name="customerRadio"]:checked').val();
			if (!data) {
				layer.msg("请选择一个客户",{time:1000});
				return;
			}
			var index = parent.layer.getFrameIndex(window.name);
			var mydata = $("#customerInfo_"+data+"").find('input[val="true"]');
			var customerbean = {};
			mydata.each(function(i){
				customerbean[$(this).attr('name')] = $(this).val();
			});
			top.customerbean = customerbean;
			//alert(customerbean.customerName);
			//parent.$("#customerName").val("haha");
			parent.layer.close(index);
		});
		$("#cancel").click(function(){
			
			var index = parent.layer.getFrameIndex(window.name);
			
			parent.layer.close(index);
		});
		
	</script>
</body>
</html>