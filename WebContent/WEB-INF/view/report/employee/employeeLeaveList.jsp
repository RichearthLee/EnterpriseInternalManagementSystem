<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工请假信息列表</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<section class="content-header">
	<form id="selectPact-form">
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="员工姓名">
              <span class="input-group-btn">
                <button type="submit" name="search"  class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
</section>

<section class="content">
		<table class="table table-hover">
		<%-- 当前登陆用户${sessionScope.user.userNo} --%>
			<thead>
				<tr>
					<th>姓名</th>
					<th style="min-width: 50px;">部门</th>
					<th style="min-width: 50px;">电话</th>
					<th style="min-width: 20px;">事假</th>
					<th style="min-width: 20px;">婚假</th>
					<th style="min-width: 30px;">丧假</th>
					<th style="min-width: 20px;">带薪年假</th>
					<th style="min-width: 20px;">陪产假</th>
					<th style="min-width: 20px;">病假</th>
					<th style="min-width: 20px;">产假</th>
					<th style="min-width: 20px;">倒休假</th>
					<th style="min-width: 50px;">无薪请假总天数</th>
					<th style="min-width: 50px;">请假总天数</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
	$(function(){
		var requestId = '${requestId}';
		if(requestId == '2'){
			$("#softType").val(1); 
		}
		var contextPath1 = '<%=request.getContextPath()%>';
		InitEmployeeList(contextPath1);
	});

	</script>
</body>
</html>

<script type="text/javascript">


var pageNumber=10;
var contextPath;
function InitEmployeeList(getcontextPath){
	contextPath = getcontextPath;
	var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/getLeaveDaysList.json",ipage,function(data){
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
		htmlstr += "<tr>"
				+	"<td>"+o.op_name+"</td>"		
				+	"<td>"+o.br_name+"</td>"
				+	"<td>"+o.mobile+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.casual+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.marriage+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.bereavement+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.annual+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.paternity+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.sick+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.maternity+"</td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.exchange+"</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.sumdays+"</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.op_no+"\");' >"+o.sumdays+"</a></td>"
	 			

	 			+	"</tr>";
	});
	$("tbody").html(htmlstr);
}
 

function getdata(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/getLeaveDaysList.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};
/*  $("#search-btn").click(function(){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
}); */ 

$("#selectPact-form").submit(function(){
	var ipage = $("#selectPact-form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getLeaveDaysList.json",ipage,function(data){
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

/* 查询 */
/* function selectPact(){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
	
}; */
function toDetail (op_no) {
	layer.load();
	window.location.href = contextPath+"/searchLeaveDaysByno/"+op_no;
	
}

</script>