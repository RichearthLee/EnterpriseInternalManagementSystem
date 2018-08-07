<%@page import="com.mftcc.interior.sys.bean.SysRole"%>
<%@page import="com.mftcc.interior.sys.bean.SysUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.mftcc.interior.*"%>
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
<body style="min-height: 300px;">
	<%SysUser user = (SysUser)request.getAttribute("user"); 
	List<SysRole> roleList = (List<SysRole>)request.getAttribute("roleList");
	 Map<String , SysRole> roleMap = (Map<String , SysRole>)request.getAttribute("roleMap"); 
	 String[] roleStr = user.getRoleNo().split(",");
	 String roleAll ="";
	 for(int j=0; j<roleStr.length ; j++){
		 SysRole role =  roleMap.get(roleStr[j]);
			if (role != null) {
				roleAll = roleAll + role.getRoleName()+" ";
			}
	 } %>
	<section class="content" style="min-height: 300px;">
	<form  class="form-horizontal" id="updateUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">用户信息</h3>

        </div>
        <%-- <div class="box-body" id="usershow">
			<div class="form-group">
				<label class="col-sm-2 control-label ">用户姓名</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="nameLabel"><%=user.getOpName()%></label>
				<label class="col-sm-2 control-label ">用户号</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;"><%=user.getUserNo()%>
				</label>
				
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">角色</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="rolesLabel"><%=roleAll%></label>
				<label class="col-sm-2 control-label ">联系电话</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="phoneLabel"><%=user.getMobile()%></label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">邮箱</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="emailLabel">${user.email }</label>
				<label class="col-sm-2 control-label ">部门</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="departmentLabel"><%=user.getDepartment()%></label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">可用标志</label>
				<label class="col-sm-4 control-label font-normal" style="text-align: left;" id="useflagLabel"><%if(user.getUseFlag().equals("1")) {%>可用
					<%}else{ %>不可用<%} %>
				</label>
				<!-- <div class="col-sm-6">
					<label class="col-sm-4 control-label">用户密码</label>
					<label class="col-sm-8 control-label" style="text-align: left;" id="passwordLabel"></label>
				</div> -->
			</div>
			<div class="form-group">
				<label class="font-normal"></label>
			</div>
			<div class="box-footer" style="text-align: center;">
				<button type="button" id="edit"
					class="btn btn-success pull-center" style="width: 65px;">编辑</button>	
				<label>&#12288;</label>
				<button type="button" id="back" class="btn btn-default"
					style="width: 65px;">返回</button>
			</div>
		</div>
	
		<div class="box-body" id="useredit" style="display: none;">
			<div class="form-group">
				<label class="col-sm-2 control-label">用户姓名</label>
				<div class="col-sm-4">
					<input id="name" name="name" class="form-control" 
						type="text" value="<%=user.getName()%>">
				</div>
				<label class="col-sm-2 control-label">用户号</label>
				<div class="col-sm-4">
					<input id="userNo" name="userNo" class="form-control"
						type="text" value="<%=user.getUserNo()%>" readonly="readonly">
				</div>
				
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">角色</label>
				<div class="col-sm-4">
					<input style="display: none;" id="roles" name="roles" type="text" value="<%=user.getRoles()%>">
					<div class="input-group">
							<input id="roleName"  class="form-control " type="text" value="<%=roleAll %>" readonly="readonly">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default " id="roles-Btn">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 </button>
                    		</span>
					</div>
				</div>
				<label class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-4">
					<input id="phone" name="phone" class="form-control "
						type="text" value="<%=user.getPhone()%>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-4">
					<input id="email" name="email" class="form-control "
						type="email" value="<%=user.getEmail()%>">
				</div>
				<label class="col-sm-2 control-label">部门</label>
				<div class="col-sm-4">
					<input id="department" name="department"
						class="form-control " type="text" value="<%=user.getDepartment()%>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">可用标志</label>
				<div class="col-sm-4">
					<select id="useFlag" name="useFlag" class="form-control ">
						<option value="1" <%if(user.getUseFlag().equals("1")){ %> selected="selected" <%} %>>可用</option>
						<option value="2" <%if(user.getUseFlag().equals("0")){ %> selected="selected" <%} %>>不可用</option>
					</select> 
				</div>
				<label class="col-sm-2 control-label">用户密码</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="不修改密码不需编辑">
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
	</form> --%>
	
	
	<div class="box-body" id="roles-select" style="display: none;">
		<div class="form-group">
		<%for(int i=0 ;i<roleList.size(); i++) {%>
			<div class="checkbox">
				<label> <input type="checkbox" name="roleSelect"
					 value="<%=roleList.get(i).getRoleNo()%>" data-roleName="<%=roleList.get(i).getRoleName() %>"><%=roleList.get(i).getRoleName() %>[<%=roleList.get(i).getRoleNo() %>] 
				</label>
			</div>
		<%} %>
	</div>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
	
		$("#edit").click(function(){
			$("#usershow").hide();
			$("#useredit").show();
		});
		$("#back").click(function(){
			window.location.href = "userListView";
		});
		$("#roles-Btn").click(function(){
			layer.open({
				type : 1,
				area : [ '300px', '280px' ],
				title : '请给客户选择角色', //不显示标题
				btn : [ '确定', '取消' ],
				content : $("#roles-select"), //捕获的元素
				success : function(lay, index)
				{
					$('input:checkbox').each(function () {
				        $(this).attr('checked',false);
					});
				},
				yes: function(index){
					
					var length = $("input:checkbox:checked").length;
					if(length == 0)
					{
						layer.msg("请选择角色",{time:1000});
						return false;
					}else{
						var roles ="";
						var roleName = "";
						$("input:checkbox:checked").each(function(i){
							if(i == length-1){
								roles = roles + $(this).val();
								roleName = roleName + $(this).data("rolename");
							}else{
								roles = roles + $(this).val() + ",";
								roleName = roleName + $(this).data("rolename") + ",";
							}
							
						});
						$("#roles").val(roles);
						$("#roleName").val(roleName);
						layer.close(index);
					}
							
				},
				end : function(index) {
					layer.close(index);
				}
			});
		});
		
		
	 $("#cancel").click(function(){
			window.location.href = window.location.href;
		}); 
		 
		 
		 $("#submit").click(function(){
			 
			if($("#password").val().length > 0){
				var reg1 = /^\w{1,30}$/;
				if(!reg1.test($("#password").val()))
				{
					layer.tips("密码格式不正确","#password",{time:1000});
					return false;
				}
				if($("#password").val().length >30)
				{
					layer.tips("密码长度过长,应小于30位","#password",{time:1000});
					return false;
				} 
			}
			if( $("#roles").val() == "" ||  $.trim($("#name").val()) == "" || $("#phone").val()== "")
			{
				layer.msg("姓名、角色、联系电话不能为空",{time:1500,offset:'30%'});
				return false;
			}
			
			if($("#name").val().length > 18)
			{
				layer.tips("用户姓名过长","#name",{time:1000});
				return false;
			}
			if($("#department").val().length > 19)
			{
				layer.tips("部门名称过长","#department",{time:1000});
				return false;
			}
			if($("#phone").val().length > 19)
			{
				layer.tips("联系电话过长","#phone",{time:1000});
				return false;
			}	
			var mydata = $("#updateUser-Form").serialize();
			$.post("<%=request.getContextPath()%>/userUpdate.json" ,mydata , function(data){
				if(data.errorcode == 0)
				{
					layer.msg("修改用户信息失败",{time:1500,offset:'30%'});
					
					return false;
					
				}else if(data.errorcode == 2){
					if(data.userNo != $("#userNo").val()){
						layer.tips("此联系电话已经存在","#phone",{time:1000});
						return false;
					}else{
						layer.msg("修改用户信息成功",{time:1500,offset:'30%'},function(){
							window.location.href = window.location.href;	
						});
					}
					
				}else{
					layer.msg("修改用户信息成功",{time:1500,offset:'30%'},function(){
						window.location.href = window.location.href;	
					});
				}
			}, "json");
			return false;
		}); 
	</script>
</body>
</html>
