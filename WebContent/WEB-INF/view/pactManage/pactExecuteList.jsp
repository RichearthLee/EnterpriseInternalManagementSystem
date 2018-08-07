<%@page import="java.util.Map"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.mftcc.interior.pact.bean.PactInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pactExecuteList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>

    <section class="content-header">
    <form id="search-form">
	<div class="col-sm-8">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="客户、产品、客户经理">
              <span class="input-group-btn">
                <button type="button" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
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
					<th>客户经理</th>
					<th>签约时间</th>
					<th>实施时间</th>
					<th>实施人员</th>
					<th>操作</th>
					
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script type="text/javascript">
	var pageNumber=10;
	
	$(function(){
		
		var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getPactExecutePage.json",ipage,function(data){
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
			if(o.executeDate == null){
				htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.productName+"</td>"
					+	"<td>"+o.marketerName+"</td>"
					+	"<td>"+o.pactDate+"</td>"
					+	"<td>"+"尚未实施"+"</td>"
					+	"<td>"+"尚未实施"+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='executeConfirm(\""+o.pactId+"\");' >实施确认</a></td>"
					+	"</tr>";
			}else{
				htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.productName+"</td>"
					+	"<td>"+o.marketerName+"</td>"
					+	"<td>"+o.pactDate+"</td>"
					+	"<td>"+o.executeDate+"</td>"
					+	"<td>"+o.executeName+"</td>"
					+	"<td></td>"
					+	"</tr>";
			}
			
		});
		$("tbody").html(htmlstr);
	}

	function getdata(page){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getPactExecutePage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
			
		},"json");
	};
	$("#search-form").submit(function(){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getPactExecutePage.json",ipage,function(data){
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
	function executeConfirm(pactId){
		window.location.href = "executeConfirm/" +pactId;
	}
	</script>
</body>
</html>