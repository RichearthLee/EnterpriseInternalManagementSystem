<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title></title>
</head>
<script type="text/javascript">
var searchFiled="";//搜索字段
var pageNumber=5;//页面显示条数
$(function(){
	creatPaging(1);
	$("#search-btn").click(function(){
		searchFiled=$("#select-form").serialize();
		creatPaging(1);
		return false;
	});
})
//创建分页插件并初始化列表
function creatPaging(currPageNo){
	var ipage =searchFiled+"&currPageNo="+currPageNo+"&pageNumber="+pageNumber;
	$.post("<%=request.getContextPath()%>/getMerchantListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:currPageNo,
	        backFn:function(p){
	            getdata(p);//存在删除操作时要换成createPaging方法
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
}
//翻页查询数据
function getdata(page){
	var ipage =searchFiled+"&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post("<%=request.getContextPath()%>/getMerchantListPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
	},"json");
};
//创建列表
function tbodyInit(data){
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		htmlstr += '<tr><td>'+o.merchantNo+'</td>';
		htmlstr	+=	'<td>'+o.merchantName+'</td>'
				+	'<td><a href="javascript:void(0)" onclick="selectDone(\''+o.merchantName+'\',\''+o.merchantNo+'\')">选择</a></td>';
	});
	$("tbody").html(htmlstr);
};
//点击选择之后
function selectDone(merchantName,merchantNo){
	parent.selected(merchantName,merchantNo);
}
</script>
<body>
    <section class="content-header">
    <form id="select-form">
		<div class="col-sm-8">
		</div>
	    <div class="col-sm-4 input-group">
	      <input type="text" id="searchFiled" class="form-control" placeholder="服务商名称" name="searchFiled"/>
	          <span class="input-group-btn">
	            <button type="button" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
	            </button>
	          </span>
	    </div>
      </form>
	</section>
	<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>服务商名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="dataContent">

			</tbody>
		</table>
	<div id="page-div" style="text-align: right;margin-top: 20px"></div>
	</section>
</body>
</html>