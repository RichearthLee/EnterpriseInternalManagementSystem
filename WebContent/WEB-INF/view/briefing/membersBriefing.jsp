<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成员简报</title>
<%@include file="/WEB-INF/view/head.jsp" %>
    <%@include file="/WEB-INF/view/bottom.jsp" %>
<style>
#page-div{
  position: absolute;
   bottom: 35px;
  right: 60px;
 
}

#td{
position:relative;
  top:11px;
}  

</style>
</head>

<body>
    <section class="content-header"> 

    	 <form id="selectPact-form">
        <div class="col-sm-3 input-group">
          <input id="search-input" type="text" name="searchFiled" class="form-control" placeholder="简报编号">
              <span class="input-group-btn">
                <button type="submit" id="search-btn" name="search"  class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
        </form> 
        
      
    </section>
    
    <section  class="content">
      <table class="table table-striped no-footer table-hover ">
			<thead>
				<tr>
					<th style="min-width:100px;">成员</th>
					<th style="min-width:100px;">简报编号</th>
					<th style="min-width:100px;">名称</th>
					<th style="min-width:100px;">类型</th>
					<th style="min-width: 100px;">简报日期</th>
					<th style="min-width: 90px;">状态</th>
					<th style="min-width: 90px;">操作</th>

				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table> 
		
		<form id="selectPact-form">
		
		<div id="page-div" style="text-align: right;">
        </div>
       
        </form>
    </section>
	<script>

	</script>
</body>

</html>
<script>
$(function(){
	var requestId = '${requestId}';
	if(requestId == '2'){
		$("#softType").val(1);
	}
	var contextPath1 = '<%=request.getContextPath()%>';
	InitEmployeeList(contextPath1);
});

$("button").css("height",$("input").css("height"));
$("tbody").html(function(){
	return "";
});
/* $("#bts").click(function(){
	alert($("input").css("height"));
	$("#bts").css("height",$("input").css("height"));
}); */
var pageNumber=11;
var contextPath;
function InitEmployeeList(getcontextPath){
	contextPath = getcontextPath;
	var ipage =$("input").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/getBriefingInfoPage.json",ipage,function(data){
		
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
			    +	"<td>"+o.briefingUser+"</td>"
			    +	"<td>"+o.briefingId+"</td>"
			    +	"<td>"+o.briefingName+"</td>"
			    +	"<td>"+o.briefingType+"</td>"
				+	"<td>"+o.briefingCreatetime+"</td>"		
				+	"<td id=\"td\" class=\"label label-warning\">"+o.briefingState+"</td>"				
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.briefingId+"\");' >查看</a></td>"
	 			+	"<td><a href='javascript:void(0);' onclick='toDelete (\""+o.briefingId+"\");' >删除</a></td>"
	 			+	"</tr>";
	});
	$("tbody").html(htmlstr);
}
 

function getdata(page){
	var ipage =$("input").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/getBriefingInfoPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};

 $("#search-btn").click(function(){
	/* var ipage = $("input").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json"); */
}); 

$("#selectPact-form").submit(function(){
	var ipage = $("#selectPact-form").serialize() +"&briefingId="+ $("#search-input").val();	
	$.post(contextPath+"/getBriefingUnreadInfoById.json",ipage,function(data){
	
		tbodyInit(data.ipage.dataList);
		},"json");
	return false;
	
});
 
function toDetail (briefingId) {
	layer.load();
	window.location.href = contextPath+"/queryBriefingInfoById/" + briefingId ;
	
}
function toDelete (briefingId) {
	
	layer.load();
	if(confirm("确认删除吗")){
		window.location.href = contextPath+"/briefinginfodelete/" + briefingId ;
		layer.msg("删除成功",{time:1500});
	} else{
			  layer.msg("已取消",{time:1500},function(){layer.closeAll("loading");});
		  return;
			  }
}
 

</script>