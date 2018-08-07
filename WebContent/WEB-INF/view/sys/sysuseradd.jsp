<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title>员工添加</title>
<style type="text/css">
	.form-horizontal .form-group{
		margin-right: 0px;
		margin-left: 0px;
	}
	.myinput{
		width:100%;
		display:inline;
	}
	.mybtn{
		text-align: left;
		border-color: white;
		background: white;
		box-shadow: none;
		padding:0px 0px;
	}
	
			/* 页面字体样式 */
		body,td,input {
			font-family: Arial;
			font-size: 12px;
		}
		/* 正常信息样式 */
		div.ok {
			color: #006600;
		}
		 .ztmin{
			width:60px;
		}
		 .ztmid{
			width:90px;
		}
		 .ztmax{
			width:120px;
		}
		/* 警告信息样式 */
		div.warning {
			color: #FF0000;
		}
</style>
</head>
<body>


	<div style="margin-top: 10px"></div>
		<h2 align="center" >新增员工 </h2>
	 
		 <form  class="form-horizontal" id="userform">
			<div class="box-bady">
		 
				<br/><br/>
				 <div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>员工姓名    </label> 
					<div class="col-sm-4" >
						<input class="form-control myinput" id="opName"  name="opName" maxlength="50" placeholder="">
					</div>
						<label class="col-sm-2 control-label layui-form-label">
						<font color="#FF0000">*</font>状 &nbsp; 态</label>
					<div class="col-sm-4 layui-input-block" >
						 <input id="opSts" name="opSts" type="checkbox" data-size="small">
						</div> 
				</div>
				
			 
			  <div class="form-group">
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>部  &nbsp;门</label>
						<div class="col-sm-4" >
							<input class="form-control myinput" id="brName" readonly="readonly" name="brName"  placeholder="">
							 <input  class="hidden" id="brNo" name="brNo">
						</div>
					
			 			<label class="col-sm-2 control-label">
			 			<font color="#FF0000">*</font>设置角色</label>
						<div class="col-sm-4" >
							<input readonly="readonly" class="form-control myinput"  id="roleNo" name="roleNo" maxlength="50" placeholder="">
						</div>
				</div>
				
				   <div class="form-group">
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>登录账号</label>
						<div class="col-sm-4" >
							<input class="form-control myinput" id="opNo" name="opNo" maxlength="50" placeholder="">
							<input class="hidden" id="loginUser"  name="loginUser">
						</div>
						<div id="opNowarning" class="">
							<strong > </strong>
						</div>
					</div>
					
			 			<label class="col-sm-2 control-label">
				 			<font color="#FF0000">*</font>密码</label>
							<div class="col-sm-4" >
							<input class="form-control myinput" type="password" id="password" name="password" maxlength="50" placeholder="">
							</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>数据权限</label>
						<div class="col-sm-4" >
       						 <select id="roleDataType" class="form-control myinput"  name="roleDataType">
       						 </select> 
						</div>
						
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>职位</label>
					<div class="col-sm-4">
							 <select id="position" class="form-control myinput"  name="position">
							</select>
					</div>
				</div>
				
				  <div class="form-group">
						<label class="col-sm-2 control-label">员工编号</label>
						<div class="col-sm-4" >
							<input class="form-control myinput" id="userNo" name="userNo" maxlength="50" placeholder="">
						</div>
					
			 			<label class="col-sm-2 control-label">身份证</label>
						<div class="col-sm-4" >
							<input class="form-control myinput"  id="idNo" name="idNo" maxlength="50" placeholder="">
						</div>
				</div>
				
				 <div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						 <div class="col-sm-4" >
 						<input id="sex" name="sex" type="checkbox" data-size="small">
						</div> 
			 			<label class="col-sm-2 control-label">生日</label>
						<div class="col-sm-4" >
							<input class="form-control myinput" type="date"   id="birthday" name="birthday" maxlength="50" placeholder="">
						</div>
				</div>
	 
					
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>手机号码</label>
					<div class="col-sm-4">
						<input id="mobile" name="mobile"
							class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>联系邮箱</label>
					<div class="col-sm-4">
						<input id="email" name="email" class="form-control myinput">
					</div>
				</div>
				
	 			<div align="right">说明：带*号的为必填项信息，请填写完整。</div>
	 
		</div>
			
		
	<div class="box-footer" style="text-align: center;">
		<button type="button" id="submit" class="btn btn-success pull-center" style="width: 65px;">确定</button>
		<label>&#12288;</label>
		<button type="button" id="cancel" class="btn btn-default" style="width: 65px;">取消</button>
	</div>
	
	</form> 
 
 
 		<div id="orgtree" class="hide">
 			<div class="zTreeDemoBackground col-xs-3">
					<ul  id="treeDemo" class="ztree"></ul>
			</div>
		 </div>
		 
	
</body>
</html>
<script type="text/javascript">
	
	//获取列表选项的调用函数
	 
	$(function(){
				// 下拉列表加载数据字典，第一个参数是 数据字典类型，第二个参数是输入框的绑定的id
				selectdicType("grant","roleDataType");
				selectdicType("position","position");
		});
	 

		// 鼠标按下事件     弹出部门树
	  $("#brName").mousedown(function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var setting = { 
			data: {
				simpleData: { enable: true 	}
				},
			callback: {
			 	onClick: zTreeOnClick  //单击事件  
			} 
		 };
			
		var zNodes = <%=session.getAttribute("sysorgjson")%>;
	 
		$(document).ready(function(){
		    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		 });
		
		$('#orgtree').removeClass("hide");
		
		 var index =  layer.open({
				type : 1,
				shade : false,
				title : '选择部门',
				area : [ '500px', '350px' ], //显示空间
				content : $('#orgtree'), //捕获的元素
				cancel : function(index) {
					layer.close(index);
					$('#orgtree').addClass("hide"); //取消继续隐藏
				} 
			});
		 
		  function zTreeOnClick(event, treeId, treeNode ) {
				var brNo = treeNode.id;
				var brName = treeNode.name;
				$("#brNo").val(brNo); 
				$("#brName").val(brName); 
					 layer.close(index);  
					 $('#orgtree').addClass("hide");
				return false;
			};
	 });
		
			// 严重用户名是否被注册
	  
	
	 
		  
			  //alert(num)
		 	 $("#opNo").blur(function(){
		 		var opNo = $('#opNo').val();
				  var num = opNo.length;
				  
				  $("strong").empty();
				  
		 		if(num!=0){
			   	 	$.ajax({
						type:"POST",
						data:{opNo:opNo},
						url :"<%=request.getContextPath()%>/getSysUseropNo.json",
						dataType : "json",
						success : function(data) {
							 $("#opNowarning").removeClass(); 
								if(data.data=="false"){// 用户名有重复的，不可以使用
									var str = "恭喜，该用户名可注册 ";
									  $("#opNowarning").addClass("ok");
								}else {  	// 用户名没有重复的，可以使用
									var str = " 该用户名已被注册";
									  $("#opNowarning").addClass("warning");
								}
								$("strong").html(str);
							},
						error : function() {
							layer.msg("操作失败！",{time:500});
						}
					});  
		 		  }    // if 结束
			 
		 	 });
		 
			
 
 
	$("#cancel").click(function(){
		window.location.href = "<%=request.getContextPath()%>/sysorg";
	});
	
	$("#submit").click(function(){
		var mydata = $("form").serialize() ;  
		
	  	$.ajax({
			type:"POST",
			data:mydata,
			url :"<%=request.getContextPath()%>/insertSysUser.json",
			dataType : "json",
			success : function(data) {
				if(data.flag=="true"){layer.msg("保存完成！",{time:500});}
				else {layer.msg("用户保存失败！",{time:500}); }
				},
			error : function() {
				layer.msg("用户保存失败！",{time:500});
			}
		});  
		     	
	  window.location.href = "<%=request.getContextPath()%>/sysorg";  
	}); 
	
 
	    $('[name="sex"]').bootstrapSwitch({  
            onText:"女",  
            offText:"男",  
            offColor:"success",  
            onColor:"info",  
            size:"small",  
            onSwitchChange:function(event,state){  
                if(state==true){  
                    $('#sex').val("0");  
                }else{  
                    $('#sex').val("1");  
                }  
            }  
        })
        
            $('[name="opSts"]').bootstrapSwitch({  
            onText:"禁用",  
            offText:"启用",  
            onColor:"info",  
            offColor:"success",  
            size:"small",  
            onSwitchChange:function(event,state){  
                if(state==true){  //onText
                    $('#opSts').val("0");  
                }else{  
                    $('#opSts').val("1");  
                }  
            }  
        })
          
	    
	</script>