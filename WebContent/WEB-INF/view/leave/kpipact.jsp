<%@page import="java.util.Map"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>leaveList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>
<section class="content-header">
    <form id="select-form">
   
	
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="姓名,日期">
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
					<th>日期</th>
					<th>姓名</th>
					<th>客户名称</th>
					<th>合同提成</th>
					<th>预期提成</th>
					
				    
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	
	
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	
	<script type="text/javascript">
	var pageNumber=10;
	$(function(){
		var ipage ="currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getPactKpiListPage.json",ipage,function(data){
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
		/* 生成页面信息 */
		$.each(data,function(i,o){
				htmlstr += "<tr>"
					+	"<td>"+o.yearMonth+"</td>"
					+	"<td>"+o.opName+"</td>"
					+	"<td>"+o.pactCompany+"</td>"
					+	"<td>"+o.bonusFact+"</td>"
					+	"<td>"+o.bonus+"</td>"
					/* +	"<td><a href='javascript:void(0);' onclick='update(\""+o.opNo+"\",\""+o.yearMonth+"\");' >修改</a></td>" */
		});																//\ ""+o.leaveNo+"\"  \'"+o.empNo+"','"+o.yearMonth+"' \
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getPactKpiListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getPactKpiListPage.json",ipage,function(data){
			
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
	
	
	
	
	function update(empNo,yearMonth){
	 	
		return window.location.href='pactKpiInfo?empNo=' + empNo + '&yearMonth=' + yearMonth;
	}
	
	
	
	
	 
	
	</script>
	
	
</body>
</html>