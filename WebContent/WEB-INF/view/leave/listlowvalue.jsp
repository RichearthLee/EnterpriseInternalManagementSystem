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
		
		<button id="addlowvalue" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>低值易损物品申请</button></a>
	</div>
	
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="类型，状态">
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
					<th>申请类型</th>
					<th>申请数量</th>
					<th>申请时间</th>
					<th>状态</th>
					<th>编辑</th>
					<th>删除</th>
				
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
		$.post("<%=request.getContextPath()%>/getLowValueListPage.json",ipage,function(data){
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
					+	"<td class =\""+o.lowValueType+"\"/>"
					+	"<td>"+o.lowValueApplyAmount+"</td>"
					+	"<td>"+o.lowValueApplyTime+"</td>"
					+	"<td class =\""+o.lowValueApplyState+"\"/>"
					+	"<td><a href='javascript:void(0);' onclick='update(\""+o.lowValueNo+"\");' >修改</a></td>"
					+	"<td><a href='javascript:void(0);' onclick='delet(\""+o.lowValueNo+"\");' >删除</a></td></tr>"
					
					 selectdicValue(o.lowValueType);
					 selectdicValue(o.lowValueApplyState);  // 用数据字典进行替换
	
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getLowValueListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	
	$("#search-btn").click(function(){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
		$.post(contextPath+"/getLowValueListPage.json",ipage,function(data){
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
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getLowValueListPage.json",ipage,function(data){
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
	
	
	function delet(lowValueNo){
		layer.confirm('确定要删除此申请吗？', {
			  btn: ['确定','返回'] //按钮
			}, function(){
				$.ajax({
					url:'<%=request.getContextPath()%>/deleteLowValue.json',
					data:'lowValueNo='+lowValueNo,
					dataType:'json',
					type:'post',
					success:function(data){
						if(data.errorcode=="0"){
							  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
								  window.location.href = "<%=request.getContextPath()%>/listlowvalue";
			                      layer.close(index);//关闭弹框
							  });
						  }else{
							  layer.alert(data.errormessage,{title:"提示",icon:5});
						  }
					}
				})
			});
	}
	
	function update(lowValueNo){
		return window.location.href="lowValueInfo?lowValueNo="+lowValueNo;
	}
	
	$("#addlowvalue").click(function(){
		window.location.href = "addlowvalue";
	});
		
	</script>
	
	
</body>
</html>