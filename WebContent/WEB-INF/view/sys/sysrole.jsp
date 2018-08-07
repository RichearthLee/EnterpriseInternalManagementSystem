<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/view/head.jsp" %>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>SysRole</title>
	
	</head>
		</head>
	<body class="body_bg">
 			
 		 <div class="col-md-12 column">
				<div class="btn-div">
					<div class="col-xs-2 column">
 			    			<button type="button" class="btn btn-primary" onclick="addSysRole();">新增角色</button>
					</div>
				</div>
				<form id="formsearch" >
					<div class=" col-sm-3 input-group pull-right column" >
					  <input type="text" name="parm1" id="parm1" class="form-control" placeholder="角色">
		            
		              <span class="input-group-btn">
		                <button type="submit" name="search"  class="btn btn-default">
		                <i class="fa fa-search"></i>
		                </button>
		              </span>
					</div>
				 </form>
		</div>
	<p class="p_blank">&nbsp;</p>
			
	<div class="row">
    <div class="col-md-3">
      <div class="box box-solid box-default">
        <div class="box-header">
          <h3 class="box-title">角色 </h3>
        </div> 
        <div class="box-body">
			<div >
				<table  class="table table-striped table-hover">
					<tbody ></tbody>
				</table>
				<div id="page-div" class="pull-right"></div>
		 	</div>     
		 </div> 
      </div> 
    </div>
    
    
    <div class="col-md-4">
      <div class="box box-solid box-primary">
        <div class="box-header">
          <h3 class="box-title">所有菜单权限</h3>
        </div><!-- /.box-header -->
        <div class="box-body">
      <ul  id="allTreeDemo" class="ztree"></ul>
        </div><!-- /.box-body -->
      </div><!-- /.box -->
    </div>
    <div class="col-md-4">
      <div class="box box-solid box-info">
        <div class="box-header">
          <h3 class="box-title">已分配菜单权限</h3>
        </div><!-- /.box-header -->
        <div class="box-body">
         <ul  id="treeDemo" class="ztree"></ul>
        </div><!-- /.box-body -->
      </div><!-- /.box -->
    </div>
    <div class="clearfix"></div>
  
  
  
</body>



</html>

<!--添加角色弹框开始 -->
  <div id="addsysorg" class="hide">
		<div class="col-xs-12">
								<br/><br/>
				 <div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-5">角色名称 </label>

				<div class="col-sm-9">
						<input class="col-xs-8" type="text"  placeholder="请输入角色名称"   id="roleName"
							name="roleName" />
				</div>
			</div>
 
			</div>
			
		</div>
	  </div>
<!--添加角色弹框  结束-->
	
	
		 
		    

<script type="text/javascript">
	

	var zTreeObjall;
	var settingall = { 
			view: {
				/* addHoverDom: addHoverDom,		// 用于当鼠标移动到节点上时，显示用户自定义控件。
				removeHoverDom: removeHoverDom,  */	// 用于当鼠标移出节点时，隐藏用户自定义控件。
			 	selectedMulti: true
					},
			edit: {		//设置编辑/删除 按钮
				 enable: true,   //设置 zTree 是否处于编辑状态
				showRemoveBtn: true,		//设置是否显示删除名称按钮,
				showRenameBtn: true , 	// 	 设置是否显示编辑名称按钮  
				removeTitle: "删除菜单",
	            renameTitle: "编辑菜单"  
				},
			check: {
					enable: true
				},
			data: {
				simpleData: {
					enable: true
				}
				},
			callback: {
				  //用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
			 	onClick: zTreeOnClick , //单击事件  
			 	beforeRemove: zTreeBeforeRemove,
			 	beforeRename: zTreeBeforeRename,
			 	onRename: zTreeOnRename
			} 
		};

		$(document).ready( function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/sysroletree.json",
				dataType:"json",
			  	type:"post",
			  	success:function(data){
			  		var zNodesall = data;
			  		zTreeObj = $.fn.zTree.init($("#allTreeDemo"), settingall, zNodesall);
			 	},
			  	error:function(){
			  		   layer.msg('菜单加载失败！',  {time:500} );
			  	}
			});
			
			
		});
		 
		
		function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
			return true;
		}
		function zTreeOnRename(event, treeId, treeNode, isCancel) {
			alert(treeNode.tId + ", " + treeNode.name);
			 layer.msg("= ",  {time:1000} );
		}
		
		// 鼠标右击节点  点击之后查询该部门的用户
		function zTreeOnClick(event, treeId, treeNode) {
			 
				return false;
		};
		 
				//  删除节点
		function zTreeBeforeRemove(treeId, treeNode) {
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}
	 
				
		$(function(){
			InitSysRoleList();
		});
	 
		var pageNumber=10;
		var contextPath;
		function InitSysRoleList(){
			var parm1= $('#parm1').val();
			var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
				$.ajax({
				url:"<%=request.getContextPath()%>/getSysRolePage.json"	,
				data:ipage,			 
				dataType:"json",
				type:"post",
				success:function(data){
					$("#page-div").createPage({
				        pageCount:data.ipage.totalPage,
				        current:1,
				        backFn:function(p){
				            getdata(p);
			        	}
					});
					
					tbodyInit(data.ipage.dataList);
				},
				error:function(){
					 layer.msg('角色列表查询失败 ！',  {time:500} );
				}
				});
		}
		function getdata(page){
			var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
			$.ajax({
				url:"<%=request.getContextPath()%>/getSysRolePage.json"	,
				data:ipage,
				dataType:"json",
				type:"post",
				success:function(data){
					tbodyInit(data.ipage.dataList);
				},
				error:function(){
					 layer.msg('角色查询失败！',  {time:500} );
				}
				});
			
		}

	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		$.each(data,function(i,o){
			htmlstr += "<tr>"
					+	"<td><a href='javascript:void(0);' onclick='getmenu(\""+o.roleNo+"\");' > "+o.roleName+"</a> </td>"
		 			+	"</tr>";
		});
		
		$("tbody").html(htmlstr);
		
	}
	 
	function getmenu(roleNo) {
		
		$.ajax({
			url:"<%=request.getContextPath()%>/getSysMenuBySysRole.json",
		 	data:{roleNo:roleNo},
			dataType:"json",
		  	type:"post",
		  	success:function(data){
		  		var zNodes =data;
		  		  if(data==""){
		  			 layer.msg('未给角色分配任何菜单权限！',  {time:1000} );
		  			}  
		  		 zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		  		
		 	},
		  	error:function(){
		  		   layer.msg('已分配菜单加载失败！',  {time:500} );
		  	}
		});
		
	}

	
	var zTreeObj;
	var setting = { 
			view: {
				/* addHoverDom: addHoverDom,		// 用于当鼠标移动到节点上时，显示用户自定义控件。
				removeHoverDom: removeHoverDom,  */	// 用于当鼠标移出节点时，隐藏用户自定义控件。
			 	selectedMulti: true
					},
			edit: {		//设置编辑/删除 按钮
				 enable: true,   //设置 zTree 是否处于编辑状态
				showRemoveBtn: true,		//设置是否显示删除名称按钮,
				showRenameBtn: true , 	// 	 设置是否显示编辑名称按钮  
				removeTitle: "删除菜单",
	            renameTitle: "编辑菜单"  
				},
				check: {
					enable: true
				},
			data: {
				simpleData: {
					enable: true
				}
				},
			callback: {
				  //用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
			 	onClick: zTreeOnClick , //单击事件  
			 	beforeRemove: zTreeBeforeRemove,
			 	beforeRename: zTreeBeforeRename,
			 	onRename: zTreeOnRename
			} 
		};
	
 
	//  添加角色
	function addSysRole(){
		
			 $('#addsysorg').removeClass("hide");
				
			 index = layer.open({
					type : 1,
					shade : [ 0.5 ],
					title : '添加角色',
					area : [ '400px', '250px' ], //显示空间
					content : $('#addsysorg'), //捕获的元素
					btn : [ '添加', '取消' ],
					yes : function(index, layero) {
						var roleName = $('#roleName').val();
						$.ajax({
							url:"<%=request.getContextPath()%>/insertSysRole.json",
						 	data:{roleName:roleName},
							dataType:"json",
						  	type:"post",
						  	success:function(data){
						  		  if(data.flag=="true"){
						  			 layer.msg('添加成功！',  {time:1000} );
						  			InitSysRoleList();
						  			}  
						 	},
						  	error:function(){
						  		   layer.msg('添加失败！',  {time:500} );
						  	}
							
						});
						layer.close(index);
						$('#addsysorg').addClass("hide");
									 
					},
					cancel : function(index) {
						layer.close(index);
						$('#addsysorg').addClass("hide"); //取消继续隐藏
					},
				});
			};
			
		 

	 
	
	
	</script>
     	    