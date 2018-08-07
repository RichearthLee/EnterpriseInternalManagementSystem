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
<title>service</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>

	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
<section class="content-header">
    <form id="select-form"> 
    <div class="col-sm-4">
		
		<button id="addservice" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增</button></a>
	</div>
	
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="">
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
					<th>服务编码</th>
					<th>服务名称</th>
					<th>服务商</th>
					<th>状态</th>
					<th>单价</th>
					<th>创建日期</th>
					<th>查询</th>
					<!-- <th>编辑</th>
					<th>删除</th> -->
				
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
		$.post("<%=request.getContextPath()%>/getServiceListPage.json",ipage,function(data){
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
					+	"<td>"+o.serviceNumber+"</td>"
					+	"<td>"+o.serviceName+"</td>"
					+	"<td>"+o.serviceProvider+"</td>"
					+	"<td>"+o.serviceState+"</td>"
					+	"<td>"+o.servicePrice+"</td>"
					+	"<td>"+o.serviceDate+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='api(\""+o.serviceNumber+"\");' >查询</a></td>"
					/* +	"<td><a href='javascript:void(0);' onclick='update(\""+o.serviceNo+"\");' >编辑</a></td>"
					+	"<td><a href='javascript:void(0);' onclick='delet(\""+o.serviceNo+"\");' >删除</a></td></tr>" */
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getServiceListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getServiceListPage.json",ipage,function(data){
		
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
	
	
	function delet(serviceNo){
		layer.confirm('确定要删除此客户吗？', {
			  btn: ['确定','返回'] //按钮
			}, function(){
				$.ajax({
					url:'<%=request.getContextPath()%>/deleteService.json',
					data:'serviceNo='+serviceNo,
					dataType:'json',
					type:'post',
					success:function(data){
						if(data.errorcode=="0"){
							  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
								  window.location.href = "<%=request.getContextPath()%>/servicelist";
			                      layer.close(index);//关闭弹框
							  });
						  }else{
							  layer.alert(data.errormessage,{title:"提示",icon:5});
						  }
					}
				})
			});
	}
	
	 function update(serviceNo){
		return window.location.href="serviceInfo?serviceNo="+serviceNo;
	}  
	
	
	$("#addservice").click(function(){
		window.location.href = "addservice";
	});
	function api(serviceNumber){
		
		window.location.href="serviceNumber?serviceNumber="+serviceNumber;
	}  
		
		
	</script>
	
	
</body>
</html>