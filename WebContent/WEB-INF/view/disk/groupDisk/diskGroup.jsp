<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mftcc.interior.disk.bean.DiskInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>diskList</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/disk/css/diskGroup.css"/>
<%@include file="/WEB-INF/view/head.jsp" %>

<style>
.fileArea{
 border-top: 0px;
}


</style>

</head>
<body>
    
    <section class="tools">
    	<div id="disk_tool">
    		<div id="disk_tool_button"></div>
    		<div id="disk_tool_input"></div>
    	</div>
    </section>
    
    
	<section class="path">
		<div id="file_path">
   			<div id="path_wrap">
     			<div id="root" onclick="openRoot(${homeId})"><span>我的网盘</span>:</div>
     			<div id="children_path"></div>
   			</div>
   		</div>
  
		
	</section>
	<section class="content">
	
	
	<div id="folder">
		<table class="table table-hover">
			 <thead>
				<tr>
					<th>文件</th>
					<th>大小</th>
					<th>更新人</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="folderArea">
			</tbody>
			<tbody id="fileArea"></tbody>
		</table>
		<div id="page-div" style="text-align: right;"></div>
	</div>
	</section>
	
	
<!--解密的弹框 -->
<div id="unLock" class="hide">
 
 	<form class="form-horizontal"   id="pform" method="post">
		<div class="col-xs-12">
			<br/><br/>	
			 <div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-5">文件密码:</label>

			<div class="col-sm-9">
				<input class="col-xs-6" type="text"  placeholder="请输入密码"   id="password"
					name="password" />
				<div class="warning"   id="eorr">*必填</div>
				 
			</div>
			</div>		 
		</div>
	</form>  
</div>

<!--解密结束-->
	
<!-- 分享的弹框 -->
<div id="shareFile" class="hide">
 
 	<form class="form-horizontal"   id="pform" method="post">
		<div class="col-xs-12">
			<br/><br/>	
			 <div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-5">文件密码:</label>

			<div class="col-sm-9">
				<input class="col-xs-6" type="text"  placeholder="请输入密码"   id="password"
					name="password" />
				<div class="warning"   id="eorr">*必填</div>
				 
			</div>
			</div>		 
		</div>
	</form>  
</div>
	
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	<script>
	
	var file_template = 
		"<tr class='file'>" +
			
			"<td class='file_name' title='双击重命名'>"+
				"<div class='file_icon'>"+
					"<span class='lock'></span>"+
					"<span class='share'></span>"+
				"</div>" +
				"<a class='file_link' href='javascript:void(0);' onclick='openFile(this)'></a>"+
			"</td>" +
			"<td class='file_size' title='文件大小'></td>" +
			"<td class='file_user' title='更新人'></td>" +
			"<td class='file_time' title='更新时间'></td>" +
			"<td class='file_op' title='操作'  >"+
				"<div class='Op dropdown' >"+
					"<div class='fileOp ' data-toggle='dropdown'>"+
					"</div>"+
					"<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>"+
					
					"</ul>"+
				"</div>"+
			"</td>" +
		"</tr>";
		
		var disk_tools="<a class='tool_button'>"+
		"<span class='tool_png'></span>"+
		"<span class='tool_name'></span>"+
		"</a>";
		
		var file_tool2="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='share(this)'>分享文件</a>"+
		"</li>";
		var file_tool3="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='download(this)'>下载</a>"+
		"</li>";
		var file_tool40="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='lock(this)'>加密</a>"+
		"</li>";
		var file_tool41="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='updatLock(this)'>修改密码</a>"+
		"</li>";
		var file_tool42="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='delLock(this)'>删除密码</a>"+
		"</li>";
		var file_tool5="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='movFile(this)'>移动</a>"+
		"</li>";
		var file_tool6="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='rename(this)'>重命名</a>"+
		"</li>";
		var file_tool7="<li role='presentation'>"+
		"<a class='toolLink' role='menuitem' tabindex='-1' href='delFile(this)'>删除</a>"+
		"</li>";
	
	$(function(){
		var priv=${privContext};
		console.log("priv:",priv);
		createDiskTools(priv);
		var fileVo = "privContext="+${privContext}+ "&fatherId="+${homeId};
		 ListFile(fileVo);
		 
	});
	
	//根据获取父节点下的子文件
	function ListFile(fileVo)
	{
		 $.ajax({
				url:"<%=request.getContextPath()%>/disk/listGroupDiskFiles.json",
				data: fileVo,
				type:"POST",
				dataType:"json",
				async:false,
				processData:false,
	            success: function (data) {
	                 if (data.mgs=="0") {
	                	 layer.msg("密码错误",{time:1500,offset:'30%'});
							return false;
	                 } else {
	                	createPath(data.fatherFolder);
	         			tbodyInit(data.myFiles,data.fatherId,data.privContext);
	                 }
	             },
				error:function(){
					alert("error!!!");
				}
			});
	};
	
	
	
	//创建路径
	function createPath(data){
		var cPath = $("#children_path");
		cPath.html("");
		$.each(data,function(i,o){
		
		cPath.prepend($("<span/>").
				data("file_id",o.id).
				addClass("lock_"+o.isLock).
				html(o.name));
		cPath.prepend(">");
		});
		
	};
	
	//创建顶上的工具栏
	function createDiskTools(priv)
	{
		
		var  tool,cTools=$("#disk_tool_button");
		$("#disk_tool_button").empty();
		var result = priv;
		result=result+"";
		console.log("result:",result)
		/* data.charAt(0) */
		if(result.charAt(0)=="1"){
			tool=$(disk_tools);
			tool.addClass("upload");
			tool.find(".tool_png").addClass("upload_png");
			tool.find(".tool_name").html("上传");
			cTools.append(tool);
		}
		if(result.charAt(1)=="1"){
			tool=$(disk_tools);
			tool.addClass("newFolder");
			tool.find(".tool_png").addClass("newFolder_png")
			tool.find(".tool_name").html("新建文件夹");
			cTools.append(tool);
		}
		
	};

	//将文件按列表列起来
	function tbodyInit(data,fatherId,priv){
		
		$("#folder").data("folder_id",fatherId);
		$("tbody").empty();
		var htmlstr = "";
		priv=priv+"";
		var tool, file,area,folderArea = $("#folderArea"),fileArea = $("#fileArea");
		$.each(data,function(i,o){
			
			file = $(file_template);
			if(o.type == "adir"){
				file.addClass("folder");
				area=folderArea;
				o.size="-";
				if(priv.charAt(4)=="1"){
					if(o.isLock=="0"){
						tool=$(file_tool40);
						tool.addClass("lockFile");
						tool.find(".toolLink").data("file_id",o.id);
						file.find(".dropdown-menu").append(tool);
					}else{
						tool=$(file_tool41);
						tool.addClass("updLockFile");
						tool.find(".toolLink").data("file_id",o.id);
						file.find(".dropdown-menu").append(tool);
						tool=$(file_tool42);
						tool.addClass("delLockFile");
						tool.find(".toolLink").data("file_id",o.id);
						file.find(".dropdown-menu").append(tool);
					}
				}
				
				
			}else{
				area=fileArea;
				if(priv.charAt(2)=="1"){
					tool=$(file_tool2);
					tool.addClass("shareFile");
					tool.find(".toolLink").data("file_id",o.id);
					file.find(".dropdown-menu").append(tool);
				}
				if(priv.charAt(3)=="1"){
					tool=$(file_tool3);
					tool.addClass("downloadFile");
					tool.find(".toolLink").data("file_id",o.id);
					file.find(".dropdown-menu").append(tool);
				}
				
			}
		   file.find(".file_icon").
				addClass(o.type+" lock_" + o.isLock + " share_" + o.isShare).
				attr("title",o.name);
		    file.find(".file_link").data("file_id",o.id).data("file_lock",o.isLock).data("file_share",o.isShare);
			file.find(".file_link").html(o.name);
			file.find(".file_size").html(o.size);
			file.find(".file_user").html(o.CreateUser);
			file.find(".file_time").html(o.createDate);
			
			if(priv.charAt(5)=="1"){
				tool=$(file_tool5);
				tool.addClass("moveFile");
				tool.find(".toolLink").data("file_id",o.id);
				file.find(".dropdown-menu").append(tool);
			}
			if(priv.charAt(6)=="1"){
				tool=$(file_tool6);
				tool.addClass("renameFile");
				tool.find(".toolLink").data("file_id",o.id);
				file.find(".dropdown-menu").append(tool);
			}
			if(priv.charAt(7)=="1"){
				tool=$(file_tool7);
				tool.addClass("delFile");
				tool.find(".toolLink").data("file_id",o.id);
				file.find(".dropdown-menu").append(tool);
			}
			area.append(file);
		});
	};
	
	//文件解密
	function unlockFile(f){
		document.getElementById("pform").reset();
		$("#unLock").removeClass("hide");
		var id=$(f).data("file_id");
		console.log("解密id：",id);
		userModel = layer.open({
			type : 1,
			shade : [ 0.5 ],
			title : '文件解密',
			area : [ '500px', '350px' ], //显示空间
			content : $('#unLock'), //捕获的元素
			btn : [ '确定', '取消' ],
			yes : function(index, layero) {
				var fileVo=$("#pform").serialize()+"&fatherId="+id;
				$.post("<%=request.getContextPath()%>/disk/listGroupDiskFiles.json",fileVo,
						function(data) {
							
							if(data.errorcode == 0)
							{
								layer.msg("密码错误",{time:1500,offset:'30%'});
								return false;
								
							}else{
								layer.close(index);
								$('#unLock').addClass("hide");
								createPath(data.fatherFolder);
			         			tbodyInit(data.myFiles,data.fatherId);
							}
							 
						},"json");
			},
			cancel : function(index) {
				layer.close(index);
				$("#unLock").addClass("hide"); //取消继续隐藏
			},
		});
	};
	
	//点击文件，进入文件内部
	function openFile(f){ 
		console.log("click");
		if($(f).data("file_lock")=="1"){
			unlockFile(f);
		}else{
		var id=$(f).data("file_id");
		var fileVo ="&fatherId="+id;
		ListFile(fileVo);}
	}; 
    
	
	//点击文件目录
	$("#file_path").delegate("#children_path span","click",function(){
		var id=$(this).data("file_id");
		var fileVo ="&fatherId="+id;
		ListFile(fileVo);
	});
	
	function openRoot(homeid)
	{
		var fileVo ="&fatherId="+homeid;
		ListFile(fileVo);
	}
	
	function share(f){
		
	}

	
	</script>