<%@page import="java.util.List"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/head.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/zTree_v3/js/jquery-1.4.4.min.js"></script>
<%@include file="/WEB-INF/view/bottom.jsp" %>
    
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<style>
			body {
			background-color: white;
			margin:0; padding:0;
			text-align: center;
			}
			div, p, table, th, td {
				list-style:none;
				margin:0; padding:0;
				color:#333; font-size:12px;
				font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
			}
			#testIframe {margin-left: 10px;}
	  </style>
		<title>SysOrg</title>
	</head>
	<body class="body_bg">
	<div class="col-md-12 column">
				<div class="btn-div">
					<div class="col-xs-2 column">
							<button type="button" class="btn btn-primary" onclick="addSysOrg();">新增部门</button>
			    			<button type="button" class="btn btn-primary" onclick="addSysUser();">新增员工</button>
					</div>
				</div>
				<form id="formsearch" >
					<div class=" col-sm-3 input-group pull-right column"   >
						  <input type="text" name="parm1" id="parm1" class="form-control" placeholder="用户姓名">
			              <span class="input-group-btn">
			                <button type="submit" name="search"  class="btn btn-default">
			                <i class="fa fa-search"></i>
			                </button>
			              </span>
					</div>
				 </form>
				

	</div>
	<p class="p_blank">&nbsp;</p>
	
			<div class="zTreeDemoBackground col-xs-3">
					<ul  id="treeDemo" class="ztree"></ul>
			</div>
			<div class="col-xs-9">
		 
						<table border=0   align="left"  class="table table-striped no-footer table-hover "
								 role="grid" aria-describedby="dynamic-table_info" >
							<thead>
								<tr>
									<th style="min-width: 50px;">账号</th>
									<th style="min-width: 50px;">姓名</th>
									<th style="min-width: 40px;">性别</th>
									<th style="min-width: 60px;">手机号</th>
									<th style="min-width: 70px;">部门名称</th>
									<th style="min-width: 50px;">职位</th>
									<th style="min-width: 50px;">角色</th>
									<th style="min-width: 40px;">状态</th>
								</tr>
							</thead>
						  <tbody ></tbody>
						</table>
						<div id="page-div" class="pull-right">
							 </div>
		 		 </div>  
		 		 
	
</body>

<!--   添加部门  弹框-->
<div id="addsysorgwin" class="hide">
 		
		 <form  class="form-horizontal" id="userform">
			<div class="box-bady">
		 
				<br/><br/>
				 <div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>机构名称    </label> 
					<div class="col-sm-3" >
						<input class="form-control myinput" id="brName"  name="brName" maxlength="50" placeholder="">
					</div>

				</div>
				
			 
			  <div class="form-group">
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>选择上一级机构</label>
						<div class="col-sm-3" >
							<input class="form-control myinput" id="upOneName" readonly="readonly" >
							<input  id="upOne" hidden="hidden" name="upOne" >
						</div>
					
						<label class="col-sm-2 control-label">
						<font color="#FF0000">*</font>机构类型</label>
						<div class="col-sm-3" >
       						 <select id="brType" class="form-control myinput"  name="brType">
       						 </select> 
						</div>
						
			 			 
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>联系电话</label>
					<div class="col-sm-3">
						<input id="brTel" name="brTel"
							class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>邮政编码</label>
					<div class="col-sm-3">
						<input id="brPost" name="brPost"
							class="form-control myinput">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>所在地区</label>
					<div class="col-sm-3">
						<input id="brArea" name="brArea"
							class="form-control myinput">
					</div>
					
					<label class="col-sm-2 control-label">
					<font color="#FF0000">*</font>联系地址</label>
					<div class="col-sm-3">
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
	
 
 			<!-- 选择上一级部门树 -->
 		<div id="orgtree" class="hide">
 			<div class="zTreeDemoBackground col-xs-3">
					<ul  id="treeSelectUpOne" class="ztree"></ul>
			</div>
		 </div>
		 
		 </div>
<!--   添加部门 弹框结束 -->

</html>
    
<script type="text/javascript">

 var zTreeObj;
var setting = { 
		
		view: {
			 addHoverDom: addHoverDom,		// 用于当鼠标移动到节点上时，显示用户自定义控件。
			removeHoverDom: removeHoverDom,   	// 用于当鼠标移出节点时，隐藏用户自定义控件。
		 	
				},
		edit: {		//设置编辑/删除 按钮
		 //	 enable: true,   //设置 zTree 是否处于编辑状态
			 editNameSelectAll: true,
			/*  showRemoveBtn: showRemoveBtn,		//设置是否显示删除名称按钮,
			 showRenameBtn: showRenameBtn , 	// 	 设置是否显示编辑名称按钮  
			 removeTitle: "删除部门",
             renameTitle: "编辑部门"   */
			},
		data: {
			simpleData: {
				enable: true
			          }
			},
		callback: {
			  //用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
		 	
		//  	onRename: zTreeOnRename,    // 编辑部门
		 	
	    //	beforeRemove: beforeRemove,   // 删除前
		//	beforeRename: beforeRename,
		//	onRemove: onRemove,
			
		//    beforeEditName: beforeEditName,    // 编辑前
			 onClick: zTreeOnClick   //单击事件  
		} 
	};

	var zNodes = <%=session.getAttribute("sysorgjson")%>;
 
	$(document).ready(function(){
	    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	 });
	
	 
	// 编辑前
	function beforeEditName(treeId, treeNode) {
		
		return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
	}
	
	
	// 编辑部门
	function zTreeOnRename(event, treeId, treeNode, isCancel) {
		alert(treeNode.tId + ", " + treeNode.name);
		 layer.msg("= ",  {time:1000} );
	}
	
	//  删除部门
	function beforeRemove(treeId, treeNode) {
 		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	
	
	// 鼠标右击节点  点击之后查询该部门的用户
	function zTreeOnClick(event, treeId, treeNode) {
		var brNo = treeNode.id;
			var ipage =  "parm0="+brNo + "&pageNumber="+pageNumber;	
			$.post(contextPath+"/getSysUserPage.json",ipage,function(data){
				$("#page-div").createPage({
			        pageCount:data.ipage.totalPage,
			        current:1,
			        backFn:function(p){
			            getdata(p);
		        	}
				});
				tbodyInit(data.ipage.dataList);
				},"json");
			return false;
	};
	 
 
			// 鼠标经过执行
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}
 
 
	 
		//  鼠标经过
	function addHoverDom(treeId, treeNode) {
	 	
	 	   var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息  
	 	 
	 	  if (treeNode.editNameFlag || $("#removeBtn_"+treeNode.tId).length>0) return;  
	     if (treeNode.editNameFlag || $("#editBtn_"+treeNode.tId).length>0) return;  
	     
	     var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.tId +
	     "' title='删除部门 ' onfocus='this.blur();'></span>"; //定义添加按钮  
	     sObj.after(removeStr); //加载添加按钮  
	     
	     var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId +
	     "' title='修改部门' onfocus='this.blur();'></span>"; //定义添加按钮  
	     sObj.after(editStr); //加载添加按钮  
	     
	     var editbtn = $("#editBtn_"+treeNode.tId);    //  编辑按钮id
	     var removebtn = $("#removeBtn_"+treeNode.tId);   //   删除编辑按钮id
	 
			 // 修改部门开始
	       $(editbtn).click(function(){
	    	   
	    	   
	    	   alert("修改成功 "+ treeId);
	    	   alert("修改成功 "+ treeNode.tName);
	    	    alert("修改成功 "+treeNode.tId);
	    	   
	    	
						 
					$(function(){
						// 下拉列表加载数据字典，第一个参数是 数据字典类型，第二个参数是输入框的绑定的id
						//selectdicType("org","brType");
			        
				    });	 
					
					// 鼠标按下事件     弹出部门树
					  $("#upOneName").mousedown(function(){
							var treeObjSelectUpOne = $.fn.zTree.getZTreeObj("treeSelectUpOne");
							var settingSelectUpOne = { 
							data: {
								simpleData: { enable: true 	}
								},
							callback: {
							 	onClick: zTreeOnClick  //单击事件  
								} 
						 	 };
					 
							$(document).ready(function(){
							    zTreeObjSelectUpOne = $.fn.zTree.init($("#treeSelectUpOne"), settingSelectUpOne, zNodes);
							 });
							
							$('#orgtree').removeClass("hide");
							
							//  给弹框内容赋值 
							
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
					 });  // 鼠标按下事件结束
						
					
					$('#addsysorgwin').removeClass("hide");    // 添加部门弹框显示
					
					 var index =  layer.open({
							type : 1,
							shade : 0.4,
							title : '修改部门',
							offset:'50px',
					        maxmin: true,
					        shadeClose: true, //点击遮罩关闭层
					        area : ['70%' , '65%'],
					 	// 	area : [ '850px', '450px' ], //显示空间    宽 与 高
					 	  	btn: ['修改', '取消'],
							content : $('#addsysorgwin'), //捕获的元素
							yes : function(index, layero) {  
								var mydata = $("form").serialize() ;  
								
							  	$.ajax({
									type:"POST",
									data:mydata,
									url :"<%=request.getContextPath()%>/updateSysOrg.json",
									dataType : "json",
									success : function(data) {
										if(data.flag=="true"){
											layer.msg("修改成功！",{time:1500});


												} else {
											layer.msg("机构修改失败！",{time:1500}); }
										},
									error : function() {
										layer.msg("机构修改失败！",{time:1500});
									} 
								});    // ajax 结束
							  	layer.close(index);
								$('#addsysorgwin').addClass("hide"); //取消继续隐藏	
							},
							cancel : function(index) {
								layer.close(index);
								$('#addsysorgwin').addClass("hide"); //取消继续隐藏
							} 
						});
					 

	    	   
	    	   
	       });  //  修改部门结束
	       
	}
		
	
 	function removeHoverDom(treeId, treeNode) {
			//	 鼠标移出时执行		
		$("#editBtn_"+treeNode.tId).unbind().remove();
		$("#removeBtn_"+treeNode.tId).unbind().remove();
		
	}; 
	

/* 	//编辑部门
	function editSysOrg(treeNode){
		top.sysOrg="";	
		top.window.openBigForm('SysOrgAction_getById.action?brNo='+treeNode.brNo+'&pId='+treeNode.pId,'编辑部门',function(){
			if(top.sysOrg!=""){
				resetTree();
			}
		});	
	}
	
	// 删除节点
	function removeTreeNode(treeNode) {

		alert("删除操作");
		if (treeNode.children && treeNode.children.length > 0) {
			var msg = treeNode.name+"，包含子机构，\n\n请删除子机构后，再删除！";
			alert(msg,0);
		} else {
			//alert("确认删除部门："+treeNode.name,2,function(){
			alert(top.getMessage("CONFIRM_DELETE"),2,function(){
				$.ajax({
					type : "POST",
					data:{ajaxData:treeNode.brNo},
					url : "SysOrgActionAjax_delAjax.action",
					dataType : "json",
					success : function(data) {
						if(data.flag=="success"){
							alert(data.msg,1);
							zTree.removeNode(treeNode);
						}else{
							alert(data.msg,0);
						}
					},
					error : function(xmlhq, ts, err) {
						alert(top.getMessage("FAILED_DELETE"),0);
					}
				});
			});
		}
	}
		 */
	
		
			
			
			 
		 
		 
		 
		 
 
	 
	function addSysUser(){
		window.location.href = "addSysUser";

	}
		 
		 
		 
		 // 添加部门
	function addSysOrg(){
			 
		$(function(){
			// 下拉列表加载数据字典，第一个参数是 数据字典类型，第二个参数是输入框的绑定的id
			selectdicType("org","brType");
			
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
        
        
	    });	 
		
		// 鼠标按下事件     弹出部门树
		  $("#upOneName").mousedown(function(){
				var treeObjSelectUpOne = $.fn.zTree.getZTreeObj("treeSelectUpOne");
				var settingSelectUpOne = { 
				data: {
					simpleData: { enable: true 	}
					},
				callback: {
				 	onClick: zTreeOnClick  //单击事件  
					} 
			 	 };
				
		 
				$(document).ready(function(){
				    zTreeObjSelectUpOne = $.fn.zTree.init($("#treeSelectUpOne"), settingSelectUpOne, zNodes);
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
		 });  // 鼠标按下事件结束
			
		
		$('#addsysorgwin').removeClass("hide");    // 添加部门弹框显示
		
		 var index =  layer.open({
				type : 1,
				shade : 0.4,
				title : '新增部门',
				offset:'50px',
		        maxmin: true,
		        shadeClose: true, //点击遮罩关闭层
		        area : ['70%' , '65%'],
		 	// 	area : [ '850px', '450px' ], //显示空间    宽 与 高
		 	  	btn: ['保存', '取消'],
				content : $('#addsysorgwin'), //捕获的元素
				yes : function(index, layero) {  
					var mydata = $("form").serialize() ;  
					
				  	$.ajax({
						type:"POST",
						data:mydata,
						url :"<%=request.getContextPath()%>/insertSysOrg.json",
						dataType : "json",
						success : function(data) {
							if(data.flag=="true"){
								layer.msg("保存完成！",{time:1500});
						        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
									} else {
								layer.msg("机构保存失败！",{time:1500}); }
							},
						error : function() {
							layer.msg("机构保存失败！",{time:1500});
						} 
					});    // ajax 结束
				  	layer.close(index);
					$('#addsysorgwin').addClass("hide"); //取消继续隐藏	
				},
				cancel : function(index) {
					layer.close(index);
					$('#addsysorgwin').addClass("hide"); //取消继续隐藏
				} 
			});
		 

	}    //  添加部门结束
	
	
	
	
		// 查询按钮 	在该部门查找 
	$("#formsearch").submit(function(){
		
		var parm1 =	$("#parm1").val();
		var sNodes = treeObj.getSelectedNodes();
		alert("--"+sNodes);
		if (sNodes.length > 0) {
			var tId = sNodes[0].tId;
		}
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
  

		var ipage ="parm1="+parm1+ "currPageNo=1&pageNumber="+pageNumber;	
		$.post(contextPath+"/getSysUserPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			},"json");
		return false;
	});
	

 
	var pageNumber=10;
	var contextPath;
	function InitSysUserList(getcontextPath){
		
		contextPath = getcontextPath;
		var data = "currPageNo=1&pageNumber="+pageNumber;
		 
		$.post(contextPath+"/getSysUserPage.json",data,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
		 	tbodyInit(data.ipage.dataList);
			
			},"json");
	};


	
	function getdata(page){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		
		$.post(contextPath+"/getSysUserPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
			
		},"json");
	};

	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		
		$.each(data,function(i,o){
			if(o.sex=="1"){o.sex="男";}else{
				if(o.sex=="0"){o.sex="女";}else{ o.sex="未定义";}	}
			
			if(o.opSts=="1"){ var sts = " <span class=\"label label-sm label-success\"> 在职 </span> " ;
			  }else{ var sts =  '<span class="\label label-sm  label-warning\">离职</span>'; }
			
			htmlstr += "<tr>"
					+	"<td>"+o.opNo+"</td>"		
					+	"<td>"+o.opName+"</td>"
					+	"<td>"+o.sex+"</td>"
					+	"<td>"+o.mobile+"</td>"
					+	"<td>"+o.brName+"</td>"
					+	"<td class =\""+o.position+"\"/>"
					+	"<td>"+o.roleName+"</td>"
	 				+   "<td>"+sts+"</td>"
	 	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.opNo+"\");' >修改</a></td>"
		 			+	"</tr>";
		 			 selectdicValue(o.position);  // 用数据字典进行替换
		});
		$("tbody").html(htmlstr);
		 
	}
	
	$(document).ready(function(){
		var contextPath1 = '<%=request.getContextPath()%>';
	 	InitSysUserList(contextPath1);  // 初始化页面表单列表
	  });

</script> 

	
	 