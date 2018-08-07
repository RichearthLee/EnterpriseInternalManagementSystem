<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title>部门添加</title>
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
		<h2 align="center" >新增部门 </h2>
		
		 <form  class="form-horizontal" id="userform">
			<div class="box-bady">
		 
				<br/><br/>
				 <div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>机构名称    </label> 
					<div class="col-sm-4" >
						<input class="form-control myinput" id="brName"  name="brName" maxlength="50" placeholder="">
					</div>

				</div>
				
			 
			  <div class="form-group">
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>选择上一级机构</label>
						<div class="col-sm-4" >
							<input class="form-control myinput" id="upOneName" readonly="readonly" >
							<input  id="upOne" hidden="hidden" name="upOne" >
						</div>
					
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>机构类型</label>
						<div class="col-sm-4" >
       						 <select id="brType" class="form-control myinput"  name="brType">
       						 </select> 
						</div>
						
			 			 
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>联系电话</label>
					<div class="col-sm-4">
						<input id="brTel" name="brTel"
							class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>邮政编码</label>
					<div class="col-sm-4">
						<input id="brPost" name="brPost"
							class="form-control myinput">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>所在地区</label>
					<div class="col-sm-4">
						<input id="brArea" name="brArea"
							class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>联系地址</label>
					<div class="col-sm-4">
						<input id="brAddr" name="brAddr" class="form-control myinput">
					</div>
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
				selectdicType("org","brType");
		});
	 

		// 鼠标按下事件     弹出部门树
	  $("#upOneName").mousedown(function(){
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
				var upOne = treeNode.id;
				var upOneName = treeNode.name;
				$("#upOneName").val(upOneName); 
				$("#upOne").val(upOne); 
					 layer.close(index);  
					 $('#orgtree').addClass("hide");
				return false;
			};
	 });
		
		 	 
	$("#cancel").click(function(){
		window.location.href = "<%=request.getContextPath()%>/sysorg";
	});
	
	$("#submit").click(function(){
		var mydata = $("form").serialize() ;  
		
	  	$.ajax({
			type:"POST",
			data:mydata,
			url :"<%=request.getContextPath()%>/insertSysOrg.json",
			dataType : "json",
			success : function(data) {
				if(data.flag=="true"){layer.msg("保存完成！",{time:1500});}
				else {layer.msg("机构保存失败！",{time:1500}); }
				},
			error : function() {
				layer.msg("机构保存失败！",{time:1500});
			}
		});  
		     	
	 	<%--   window.location.href = "<%=request.getContextPath()%>/sysorg";   --%>
	}); 
	
 
	   
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