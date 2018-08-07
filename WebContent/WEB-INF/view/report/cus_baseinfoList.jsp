<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工考勤查询</title>
</head>
<body>
<section class="content-header">
	<div class="col-sm-2">
	</div>
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
			<thead>
				<tr>
					<th>员工姓名</th>
					<th style="min-width: 100px;">所属部门名称</th>
					<th style="min-width: 90px;">电话</th>
					<th style="min-width: 100px;">事假天数</th>
					<th style="min-width: 90px;">病假天数</th>
					<th style="min-width: 90px;">带薪年假天数</th>
					<th style="min-width: 50px;">倒休假天数</th>
					<th style="min-width: 100px;">婚假天数</th>
					<th style="min-width: 90px;">丧假天数</th>
					<th style="min-width: 100px;">探亲假天数</th>
					<th style="min-width: 90px;">产假天数</th>
					<th style="min-width: 90px;">无薪请假天数</th>
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
	$.post(contextPath+"/getEmployeeInfoPage.json",ipage,function(data){
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
				+	"<td>"+o.employeeName+"</td>"		
				+	"<td>"+o.employeePhone+"</td>"
				+	"<td>"+o.employeeEmail+"</td>"
				+	"<td>"+o.employeeDepartment+"</td>"
				+	"<td>"+o.employeeDepartmentId+"</td>"
				+	"<td>"+o.employeeMarks+"</td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.employeeId+"\");' >修改</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDelete (\""+o.employeeId+"\");' >删除</a></td>"

	 			+	"</tr>";
	});
	$("tbody").html(htmlstr);
}
 

function getdata(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/getEmployeeInfoPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};
 $("#search-btn").click(function(){
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
}); 

$("#selectPact-form").submit(function(){
	var ipage = $("#selectPact-form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getEmployeeInfoPage.json",ipage,function(data){
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

function toDetail (employeeId) {
	layer.load();
	window.location.href = contextPath+"/updateEmployeeInfoById/" + employeeId ;
	
}
</script>