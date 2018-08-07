<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>

    <section class="content-header">
    <form id="search-form">
    <div class="col-sm-4">
		<!-- <button id="newUser" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增用户</button> -->
	</div>
	<div class="col-sm-4">
	</div>
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" id="searchFiled" class="form-control" placeholder="客户名称、客户经理">
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
					<th>客户名称</th>
					<th>发票金额</th>
					<th>客户经理</th>
					<th>提交时间</th>
					<th>确认时间</th>
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
		var ipage = "searchFiled="+ $("#searchFiled").val() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post(contextPath+"/pactInvoice/getInvoicePage.json",ipage,function(data){
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
			if(o.confirmDate == null || o.confirmDate == ''){
				var confirm = '未确认';
				htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.invoiceMoney+"</td>"
					+	"<td>"+o.marketName+"</td>"
					+	"<td>"+o.marketDate+"</td>"
					+	"<td>"+confirm+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.id+"\");' >详情</a></td></tr>";
			}else{
				htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.invoiceMoney+"</td>"
					+	"<td>"+o.marketName+"</td>"
					+	"<td>"+o.marketDate+"</td>"
					+	"<td>"+o.confirmDate+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.id+"\");' >详情</a></td></tr>";
				
			}
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "searchFiled="+ $("#searchFiled").val()+ "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post(contextPath+"/pactInvoice/getInvoicePage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#search-form").submit(function(){
		var ipage = "searchFiled="+ $("#searchFiled").val() + "&pageNumber="+pageNumber;	
		$.post(contextPath+"/pactInvoice/getInvoicePage.json",ipage,function(data){
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
	function toDetail(id){
		window.location.href = contextPath+"/pactInvoice/getOneInvoiceInfo?id=" +id;
	}
	
	</script>
</body>
</html>