<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>SysUser</title>
	<%@include file="/WEB-INF/view/head.jsp" %>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	</head>
	<body class="body_bg">
 			
 
		<section class="content-header">
			<div class="col-sm-4">
				<button id="newsysuser" type="button" class="btn btn-info">
				<span class="glyphicon glyphicon-plus"></span>新增用户</button>
			</div>
			<div class="col-sm-2">
			</div>
			<form id="selectUser-form">
		 
		        <div class="col-sm-3 input-group">
		          <input type="text" name="searchFiled" class="form-control" placeholder="用户姓名">
		              <span class="input-group-btn">
		                <button type="submit" name="search"  class="btn btn-default"><i class="fa fa-search"></i>
		                </button>
		              </span>
		        </div>
		      </form>
		</section>
		
		<section class="content">
				<table class="table table-hover">
					<thead>
						<tr>
							<th style="min-width: 100px;">账号</th>
							<th style="min-width: 90px;">姓名</th>
							<th style="min-width: 100px;">性别</th>
							<th style="min-width: 90px;">手机号</th>
							<th style="min-width: 90px;">部门名称</th>
							<th style="min-width: 50px;">职位</th>
							<th style="min-width: 50px;">角色</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div id="page-div" style="text-align: right;">
				</div>
			</section>
			
			
</body>
</html>

<script type="text/javascript">
		$(function(){
			var contextPath1 = '<%=request.getContextPath()%>';
			InitSysUserList(contextPath1);
			
			
		});
	var pageNumber=10;
	var contextPath;
	function InitSysUserList(getcontextPath){
		contextPath = getcontextPath;
		var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post(contextPath+"/getSysUserPage.json",ipage,
				function(data){
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
function tbodyInit(data){
	
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		
		if(o.sex=="1"){
			o.sex="男";
		}else{
			if(o.sex=="0"){
				o.sex="女";
			}else{
			o.sex="未定义";}
		}
		htmlstr += "<tr>"
				+	"<td>"+o.opNo+"</td>"		
				+	"<td>"+o.opName+"</td>"
				+	"<td>"+o.sex+"</td>"
				+	"<td>"+o.mobile+"</td>"
				+	"<td>"+o.brName+"</td>"
				+	"<td class =\""+o.position+"\"/>"
				+	"<td>"+o.roleName+"</td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.opNo+"\");' >修改</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDelete (\""+o.opNo+"\");' >删除</a></td>"
	 			+	"</tr>";

	 selectdicValue(o.position);  // 用数据字典进行替换
	 
	});
	$("tbody").html(htmlstr);
 }
	 
	//selectdicValue("031");
 //	alert("--" +selectdicValue("031") );
		
		
		

function getdata(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/getSysUserPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};

$("#selectUser-form").submit(function(){
	var ipage = $("#selectUser-form").serialize() + "&pageNumber="+pageNumber;	
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

 /* 
 
  $.post(contextPath+"/getdic.json","dicKey="+dicKey,
					function(data){
			  var dat = data.sysDictionaryList;
				getDicValue =  dat[0].dicValue;
				 alert("v." + getDicValue);
				},"json");
		  
 
$("#newemployee").click(function(){
	window.location.href = "employeeInfoadd";
});
$("#newcustomer").click(function(){
	window.location.href = "customerAdd";
});
function toDetail (employeeId) {
	layer.load();
	window.location.href = contextPath+"/updateEmployeeInfoById/" + employeeId ;
	
}
function toDelete (employeeId) {
	
	layer.load();
	if(confirm("确认删除吗")){
		window.location.href = contextPath+"/employeeinfodelete/" + employeeId ;
		layer.msg("删除成功",{time:1500});
	} else{
			  layer.msg("已取消",{time:1500});
		  return;
			  }
}
  */

</script> 