<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>employeeListInfo</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<section class="content-header">
	<div class="col-sm-4">
		<button id="newcustomer" type="button" class="btn btn-info">
		<span class="glyphicon glyphicon-plus"></span>新增客户</button>
		
	</div>
	<div class="col-sm-2">
	</div>
	<form id="selectPact-form">
 
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="客户姓名">
              <span class="input-group-btn">
                <button type="submit" name="search"  class="btn btn-default"><i class="fa fa-search"></i>
                
                </button>
                
              </span>
               <select id="select" style="width:150px;height:21px;" onchange="a()">
        <option value="1">7天未联系</option>
        <option value="2">15天未联系</option>
        <option value="3">30天未联系</option>
        </select>
        </div>
        <div style="text-align: center;margin-top:10px;">
       
        	
        	</div>
      </form>
</section>

<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>客户名称</th>
					<th style="min-width: 100px;">客户类型</th>
					<th style="min-width: 90px;">详细地址</th>
					<th style="min-width: 50px;">操作</th>
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
	$.post(contextPath+"/getcustomerinfo.json",ipage,function(data){
		
		
		
		tbodyInit(data.ipage.dataList);
		
		},"json");
};
function tbodyInit(data){
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		htmlstr += "<tr>"
				+	"<td>"+o.customerName+"</td>"		
				+	"<td>"+o.customerType+"</td>"
				+	"<td>"+o.location+"</td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.customerId+"\");' >修改</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDelete (\""+o.customerId+"\");' >删除</a></td>"

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
$("#newemployee").click(function(){
	window.location.href = "employeeInfoadd";
});
$("#newcustomer").click(function(){
	window.location.href = "customerAdd";
});
function toDetail (employeeId) {
	layer.load();
	window.location.href = contextPath+"/customerinfoupdate/" + employeeId ;
	
}
function toDelete (employeeId) {
	
	layer.load();
	if(confirm("确认删除吗")){
		window.location.href = contextPath+"/employeeinfodelete/" + employeeId ;
		layer.msg("删除成功",{time:1500});
	} else{
			  layer.msg("已取消",{time:1500},function(){layer.closeAll("loading");});
		  return;
			  }
}
 

</script>