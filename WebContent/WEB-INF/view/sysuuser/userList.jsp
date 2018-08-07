<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mftcc.interior.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UserList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>

    <section class="content-header">
    <form id="select-form">
    <div class="col-sm-4">
		<button id="newUser" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增用户</button>
	</div>
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="用户号、姓名、电话">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
	</section>
	<section class="content">
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>用户号</th>
					<th>姓名</th>
					<th>角色</th>
					<th>联系电话</th>
					<th>邮箱</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
	var pageNumber=10;
	$(function(){
		
		var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getUserListPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			
			},"json");
	});
	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		$.each(data,function(i,o){
			if(o.email == null){
				o.email = '';
			}
				htmlstr += "<tr>"
					+	"<td>"+o.userNo+"</td>"
					+	"<td>"+o.name+"</td>"
					+	"<td>"+o.roles+"</td>"
					+	"<td>"+o.phone+"</td>"
					+	"<td>"+o.email+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.userNo+"\");' >详情</a></td></tr>"
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getUserListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getUserListPage.json",ipage,function(data){
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
	function toDetail(userNo){
		window.location.href = "userFullView?userNo=" +userNo;
	}
	$("#newUser").click(function(){
		window.location.href = "userAddView";
	});
	</script>
</body>
</html>