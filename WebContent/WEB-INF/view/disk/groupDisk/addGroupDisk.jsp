<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<%@page import="com.mftcc.interior.disk.bean.DiskInfo"%>
<%@page import="com.mftcc.interior.sys.bean.SysUser"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>

<%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body >
	<%List<SysUser> userList = (List<SysUser>)request.getAttribute("userList"); 
	%>
	<section class="content">
	<form  class="form-horizontal" id="addUser-Form">
	<div class="box">
		<div class="box-header with-border">
          <h3 class="box-title">增加新网盘</h3>

        </div>
		<div class="box-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">网盘名字</label>
				<div class="col-sm-4">
					<input id="diskname" name="diskname" class="form-control"
						type="text" placeholder="网盘名字">
				</div>
				
				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">网盘总容量</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="totalSize"
						name="totalSize" placeholder="网盘容量">
				</div>
			</div>
			
			<div class="form-group">

				<label class="col-sm-2 control-label">网盘成员</label>
				<div class="col-sm-4">
					<div class="input-group" style="width:80%;">
					       <input id="beforesale" name="persons" type="hidden">
							<input id="beforesaleName"  type="text"
								class="form-control" style="background: white" readonly="readonly">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default " id="beforesaleBtn">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 </button>
                    		</span>
						</div>
				</div>
				
			</div>
			
			<div class="form-group" >

				<label class="col-sm-2 control-label" >成员权限</label>
		        <div id="diskpriv"></div>
				
				
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
	
	<div class="box-body" id="user-select" style="display: none;">
		<div class="form-group">
			<div class="checkbox" id="noSaleBefore-div" style="display: none;">
				<label> 
					<input type="checkbox" name="userSelect" value="" data-username="无网盘成员">无网盘成员
				</label>
			</div>
		<%for(int i=0 ;i<userList.size(); i++) {%>
			<div class="checkbox">
				<label> 
					<input type="checkbox" name="userSelect" value="<%=userList.get(i).getOpNo()%>" data-username="<%=userList.get(i).getOpName() %>"><%=userList.get(i).getOpName() %>
				</label>
			</div>
		<%} %>
		</div>
	</div>
	
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<!-- iCheck - plugins -->
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	<script>
		
		
		$("#cancel").click(function(){
			window.location.href = "diskGroupList";
		});
		
		
		 $("#submit").click(function(){
			var mydata = $("#addUser-Form").serialize();
			layer.confirm('你确定添加群组网盘？',{btn:['确定','取消']},function(){
			$.post("<%=request.getContextPath()%>/disk/addGroupDisk.json" ,mydata , function(data){
				if(data.errorcode == 0)
				{
					layer.msg("增加网盘失败",{time:1500,offset:'30%'});
					return false;
					
				}else{
					layer.msg("增加网盘成功",{time:1500,offset:'30%'},function(){
						window.location.href ="diskGroupList";				
					});
				}
			}, "json");})
			return false;
		});
		 
		 
		 $("#beforesaleBtn").click(function(){
			 layer.open({
					type : 1,
					area : [ '300px', '280px' ],
					title : '请选择网盘成员', //不显示标题
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
							var htmlstr="";
							$("input:checkbox:checked").each(function(i){
								htmlstr +='<div class="col-sm-4">'+$(this).data("username")+'<input type="checkbox" value="1" name="'+$(this).val()+'1'+'"/>上传<input type="checkbox" value="1" name="'+$(this).val()+'2'+'"/>新建文件夹</div>';

							});
							$("#diskpriv").html(htmlstr);
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
		
		 
		 
	</script>
</body>
</html>