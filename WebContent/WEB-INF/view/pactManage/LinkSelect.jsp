<%@page import="java.util.List"%>
<%@page import="com.mftcc.interior.cus.bean.cusLinkman"%>
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
	List<cusLinkman> linkManList = (List<cusLinkman>)request.getAttribute("linkManList");
%>
<section class="content" style="min-height: 0px;">
<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>操作</th>
					<th>联系人名称</th>
				</tr>
			</thead>
			<tbody>
			<%for(int i=linkManList.size()-1 ; i >= 0 ; i--) {
				cusLinkman linkman = linkManList.get(i);
			%>
				<tr id="linkman_<%=linkman.getLinkId()%>">
					<td><input type="radio"  name="linkmanRadio" value="<%=linkman.getLinkId()%>"></td>
					<td>
						<input type="text" name="linkName" val="true"  value="<%=linkman.getLinkName()%>" style="display: none;">
						<%=linkManList.get(i).getLinkName() %>
					</td>
					<td style="display: none;"><input type="text" name="linkName" val="true"  value="<%=linkman.getLinkName()%>" ></td>
					<td style="display: none;"><input type="text" name="linkId" val="true"  value="<%=linkman.getLinkId()%>" ></td>
					<td style="display: none;"><input type="text" name="email" val="true"  value="<%=linkman.getEmail()%>" ></td>
					<td style="display: none;"><input type="text" name="phone" val="true"  value="<%=linkman.getPhone()%>" ></td>
					<td style="display: none;"><input type="text" name="telphone" val="true"  value="<%=linkman.getTelphone()%>" ></td>
					<td style="display: none;"><input type="text" name="title" val="true"  value="<%=linkman.getTitle()%>" ></td>
					<td style="display: none;"><input type="text" name="marketerId" val="true"  value="<%=linkman.getMarketerId()%>" ></td>
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
			var data = $('input[name="linkmanRadio"]:checked').val();
			if (!data) {
				layer.msg("请选择一个联系人",{time:1000});
				return;
			}
			var index = parent.layer.getFrameIndex(window.name);
			var mydata = $("#linkman_"+data+"").find('input[val="true"]');
			var linkbean = {};
			mydata.each(function(i){
				linkbean[$(this).attr('name')] = $(this).val();
			});
			top.linkbean = linkbean;
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