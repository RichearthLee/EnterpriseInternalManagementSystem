<%@page import="com.mftcc.interior.sys.bean.SysUser"%>
<%@page import="java.util.List"%>
<%@page import="com.mftcc.interior.*"%>
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
	List<SysUser> userList = (List<SysUser>)request.getAttribute("sysuser");
%>
<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>操作</th>
					<th>名称</th>
					
					
				</tr>
			</thead>
			<tbody>
			<%for(int i=userList.size()-1 ; i >= 0 ; i--) {
				SysUser user = userList.get(i);
			%>
				<tr id="userInfo_<%=user.getUserNo()%>">
					<td><input type="radio"  name="userRadio" value="<%=user.getOpNo()%>"></td>
					<td>
						<input type="text" name="name" val="true"  value="<%=user.getOpName()%>" style="display: none;">
						<%=user.getOpName() %>
					</td>
					<td style="display: none;"><input type="text" name="userNo" val="true"  value="<%=user.getUserNo()%>" ></td>
				</tr>
				<%} %>
				

			</tbody>
		</table>
		<button class="btn btn-default" id="submit" type="button">确定</button>
		<button class="btn btn-default" id="cancel" type="button">取消</button>
	</div>

	<%@include file='../bottom.jsp' %>
	<script
		src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
	
		//vat tr=....
		// tr.find('input[val="true"]')
		$("#submit").click(function(){
			var data = $('input[name="userRadio"]:checked').val();
			if(!data){
				layer.alert("请选择人员");
				return;
			}
			var index = parent.layer.getFrameIndex(window.name);
			var mydata = $("#userInfo_"+data+"").find('input[val="true"]');
			var user = {};
			mydata.each(function(i){
				user[$(this).attr('name')] = $(this).val();
			});
			top.user = user;
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