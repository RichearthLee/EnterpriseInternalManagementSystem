<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <%@include file="/WEB-INF/view/head.jsp" %>
    <%@include file="/WEB-INF/view/bottom.jsp" %>
<style>
#td{
position:relative;
  top:11px;
}  
#td1{
position:relative;
  top:11px;
}  


#page-div{
  position: absolute;
  bottom: 35px;
  right: 60px;
}


 select{
  width:200px;
  height:37px;
  border-radius:5px;
  margin:10px;
  
  }
#img_1{

  margin-left:20px;
}
</style>
</head>
  <body>
    <section class="content-header">  
      <div id="dp_1" class="droplist">
        <select id="input">
          <option selected value="待处理">待处理</option>
          <option value="已处理">已处理</option>
        </select>
      </div>   
    </section>
    
    
 
    <section  class="content">
      <table class="table table-striped no-footer table-hover ">
			<thead>
				<tr>
				    <th style="min-width: 100px;">简报日期</th>
				    <th style="min-width:100px;">简报编号</th>
				    <th style="min-width:100px;">名称</th>
					<th style="min-width:100px;">审评人</th>
					<th style="min-width:100px;">类型</th>					
					<th style="min-width: 90px;">状态</th>
					<th style="min-width: 90px;">操作</th>

				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table> 
    <div id="page-div" style="text-align: right;">
    </section>
    

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
/* $("#bts").click(function(){
	alert($("input").css("height"));
	$("#bts").css("height",$("input").css("height"));
}); */
var pageNumber=11;
var contextPath;

function InitEmployeeList(getcontextPath){
	contextPath = getcontextPath;
	var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/getBriefingReviewedInfoPage.json",ipage,function(data){
		
		tbodyInit(data.ipage.dataList);
		
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		
		},"json");
};

function InitBriefingList(getcontextPath){
	contextPath = getcontextPath;
	var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/getBriefingResolvedInfoPage.json",ipage,function(data){
		
		tbodyInit1(data.ipage.dataList);
		
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata1(p);
        	}
		});
		
		},"json");
};

function tbodyInit(data){
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		htmlstr += "<tr>"
			    +	"<td>"+o.briefingCreatetime+"</td>"	
			    +	"<td>"+o.briefingId+"</td>"
			    +	"<td>"+o.briefingName+"</td>"
			    +	"<td>"+o.briefingReviewer+"</td>"
			    +	"<td>"+o.briefingType+"</td>"					
				+	"<td id=\"td1\" class=\"label label-danger\">"+"待处理"+"</td>"				
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.briefingId+"\");' >查看</a></td>"
	 			
	 			+	"</tr>";
	});
	$("tbody").html(htmlstr);
}

function tbodyInit1(data){
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		htmlstr += "<tr>"
			    +	"<td>"+o.briefingCreatetime+"</td>"	
			    +	"<td>"+o.briefingId+"</td>"
			    +	"<td>"+o.briefingName+"</td>"
			    +	"<td>"+o.briefingReviewer+"</td>"
			    +	"<td>"+o.briefingType+"</td>"		
				+	"<td id=\"td\" class=\"label label-success\">"+"已解决"+"</td>"				
	 			+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.briefingId+"\");' >查看</a></td>" 			
	 			+	"</tr>";
	});
	$("tbody").html(htmlstr);
}
 

function getdata(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+ "&currPageNo="+page;
	$.post(contextPath+"/getBriefingReviewedInfoPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};

function getdata1(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+ "&currPageNo="+page;
	$.post(contextPath+"/getBriefingResolvedInfoPage.json",ipage,function(data){
		tbodyInit1(data.ipage.dataList);
		
	},"json");
};


/*  $("#search-btn").click(function(){
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
	$.post(contextPath+"/getBriefingReviewedInfoPage.json",ipage,function(data){
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
 */
$("#dp_1").change(function(){
	var op=$("#dp_1 option:selected").val();
	
	if($("#dp_1 option:selected").val()=="待处理"){
		var requestId = '${requestId}';
		if(requestId == '2'){
			$("#softType").val(1);
		}
		var contextPath1 = '<%=request.getContextPath()%>';
		InitEmployeeList(contextPath1);
	}
	if($("#dp_1 option:selected").val()=="已处理"){
		var requestId = '${requestId}';
		if(requestId == '2'){
			$("#softType").val(1);
		}
		var contextPath1 = '<%=request.getContextPath()%>';
		InitBriefingList(contextPath1);
		
		
	}
	
	
		
});

function toDetail (briefingId) {
	layer.load();
	window.location.href = contextPath+"/queryBriefingReviewedInfoById/" + briefingId ;
	
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