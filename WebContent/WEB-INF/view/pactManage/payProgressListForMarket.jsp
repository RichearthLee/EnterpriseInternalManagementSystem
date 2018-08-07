<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.mftcc.interior.pact.bean.PayProgressInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pactProgressList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>

  <section class="content-header">
	<div class="col-sm-8">
	</div>
	<form id="search-form">
        <div class="col-sm-4 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="客户名称、产品">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
	</section>
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>产品类别</th>
					<th>第几笔支付</th>
					<th>提交申请时间</th>
					<th>金额</th>
					<th>客户返款</th>
					<th>确认时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>

	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
	var pageNumber=10;
	$(function(){
		var ipage = $("#search-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
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
			var result = "";
			var receiptPath = "";
			var confirm = "";
			if(o.financialResult == 1 ){
				result +=o.financialDate;
			}else{
				result += "未确认";
			}
			confirm = "<a href='javaScript:void(0);' onclick='onDetail(\""+o.pactId+"\",\""+o.payyet+"\");'>详情</a>";	
			if(o.receiptPath == null || o.receiptPath == "" ){
			}else{
				receiptPath = '<a class="fa fa-fw fa-file-text-o" target="_blank" href="<%=request.getContextPath()%>/download?filePath=' +o.receiptPath+ '"title="'+o.receiptPath+'">';
			}
			htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.productName+"</td>"
					+	"<td>"+o.payyet+"</td>"
					+	"<td>"+o.marketDate+"</td>"
					+	"<td>"+o.proceedMoney+"</td>"
					+	"<td>"+o.customerMoney+"</td>"
					+	"<td>"+result+"</td>"
					+	"<td>"+confirm+"</td>"
					+   "</tr>";
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = $("#search-form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
			
		},"json");
	};

	$("#search-form").submit(function(){
		var ipage = $("#search-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
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
	
	function onDetail(pactId,payyet){
		window.location.href = 'getPayProgressView?pactId='+pactId+'&payyet='+payyet;
	};

	</script>
</body>
</html>