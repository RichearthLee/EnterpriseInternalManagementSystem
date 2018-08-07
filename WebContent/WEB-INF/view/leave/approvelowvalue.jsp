<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工申请信息列表</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<section class="content-header">
	<form id="select-form">
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="员工姓名、类型、状态">
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
					<th>申请人</th>
					<th>申请类型</th>
					<th>申请数量</th>
					<th>申请时间</th>
					<th>申请状态</th>
					<th>审核</th>
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
	});

	</script>
	<script type="text/javascript">
	var pageNumber=10;
	$(function(){
		var ipage ="currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getApproveLowValueListPage.json",ipage,function(data){
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
					+	"<td>"+o.opName+"</td>"
					+	"<td class =\""+o.applyType+"\"/>"
					+	"<td>"+o.lowValueApplyAmount+"</td>"
					+	"<td>"+o.applyTime+"</td>"
					+	"<td class =\""+o.approveState+"\"/>"
					+	"<td><a href='javascript:void(0);' onclick='state(\""+o.approveLowValueNo+"\");' >查看详情</a></td></tr>"
					selectdicValue(o.applyType); 
				    selectdicValue(o.approveState); 
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getApproveLowValueListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getApproveLowValueListPage.json",ipage,function(data){
			
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
	function state(approveLowValueNo){
		return window.location.href="approveLowValueInfo?approveLowValueNo="+approveLowValueNo;
	}
	</script>
</body>
</html>
	
