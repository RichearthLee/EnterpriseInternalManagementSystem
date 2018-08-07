<%@page import="com.mftcc.interior.sys.bean.SysRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@ page import="com.mftcc.interior.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>

<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body >
	<%List<SysRole> roleList = (List<SysRole>)request.getAttribute("roleList"); %>
	<section class="content">
	<form  class="form-horizontal" id="addUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">增加新用户</h3>

        </div>
		<div class="box-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">用户号</label>
				<div class="col-sm-4">
					<input id="userNo" name="userNo" class="form-control"
						type="text" placeholder="字母、数字或下划线(4-18位)">
				</div>
				<label class="col-sm-2 control-label">用户姓名</label>
				<div class="col-sm-4">
					<input id="name" name="name" class="form-control"
						type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">用户密码</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="字母、数字或下划线">
				</div>
				<label class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-4">
					<input id="phone" name="phone" class="form-control "
						type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-4">
					<input id="email" name="email" class="form-control "
						type="email">
				</div>
				<label class="col-sm-2 control-label">部门</label>
				<div class="col-sm-4">
					<input id="department" name="department"
						class="form-control " type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">角色</label>
				<div class="col-sm-4">
					<input style="display: none;" id="roles" name="roles" type="text">
					<div class="input-group">
							<input id="roleName" class="form-control " type="text" readonly="readonly" style="background: transparent;">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default " id="roles-Btn">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 </button>
                    		</span>
					</div>
				</div>
				<label class="col-sm-2 control-label">可用标志</label>
				<div class="col-sm-4">
					<select id="useFlag" name="useFlag" class="form-control ">
						<option value="1">可用</option>
						<option value="0">不可用</option>
					</select> 
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
	
	<div class="box-body" id="roles-select" style="display: none;">
		<div class="form-group">
		<%for(int i=0 ;i<roleList.size(); i++) {%>
			<div class="checkbox">
				<label> <input type="checkbox" name="roleSelect"
					 value="<%=roleList.get(i).getRoleNo()%>" data-rolename="<%=roleList.get(i).getRoleName() %>"><%=roleList.get(i).getRoleName() %>[<%=roleList.get(i).getRoleNo() %>] 
				</label>
			</div>
		<%} %>
		</div>
	</div>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<!-- iCheck - plugins -->
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
		$("#roles-Btn").click(function(){
			layer.open({
				type : 1,
				area : [ '300px', '280px' ],
				title : '请给客户选择角色', //不显示标题
				btn : [ '确定', '取消' ],
				content : $("#roles-select"), //捕获的元素
				success : function(lay, index)
				{
				},
				yes: function(index){
					
					var length = $("input:checkbox:checked").length;
					if(length == 0)
					{
						layer.tips("请选择角色","#roles-Btn",{time:1000});
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
			window.location.href = "userListView";
		});
		
		
		 $("#submit").click(function(){
			if($("#userNo").val() == "" || $("#password").val()=="" || $("#roles").val() == "" || $.trim($("#name").val()) == "" || $("#phone").val() == "")
			{
				layer.msg("用户号、姓名、密码、角色、联系电话不能为空",{time:1500,offset:'30%'});
				return false;
			}
			var reg = /^\w{4,18}$/;
			if( reg.test($("#userNo").val()))
			{
			}
			else{
				layer.tips("用户号格式不正确","#userNo",{time:1000});
				return false;
			}
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
			var mydata = $("#addUser-Form").serialize();
			$.post("<%=request.getContextPath()%>/userAdd.json" ,mydata , function(data){
				if(data.errorcode == 0)
				{
					layer.msg("增加新用户失败",{time:1500,offset:'30%'});
					return false;
					
				}else if(data.errorcode == 2){
					layer.tips("此用户号已经存在","#userNo",{time:1000});
					return false;
				}else if(data.errorcode == 3){
					layer.tips("此联系电话已经存在","#phone",{time:1000});
				}else{
					layer.msg("增加新用户成功",{time:1500,offset:'30%'},function(){
						window.location.href ="userListView";				
					});
				}
			}, "json");
			return false;
		}); 
	</script>
</body>
</html>
