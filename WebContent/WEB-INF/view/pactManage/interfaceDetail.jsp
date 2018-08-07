<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%-- <%@ page import="com.mftcc.method.bean.paramBean"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情页面</title>
<%@include file="../head.jsp" %>
<style type="text/css">
	.margintop-10
	{
		margin-top:10px;
	}
 	.textStyle
	{
		margin-right:5px;
		margin-left:5px;
		border-color:#D9EDF7;
	} 
	.glyRed
	{
		color:#800000;
	}
	.btn-param
	{
		background-color:#D9EDF7;
		margin-right:5px;
		margin-left:5px;
	}
	.iconparam
	{
		font-size:0.5px;
	}
</style>
</head>
<body>
<div class="row">
	<div class="col-sm-6">
			<div class="panel panel-default  panel-info">
  			<div class="panel-heading">
    			<h3 class="panel-title">功能描述</h3>
 			 </div>
  			<div class="panel-body">
  			 <textarea name="DIVCSS5" cols="10" rows="4"> 
        		
  			  </textarea> 
  			</div>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="panel panel-default panel-info">
  			<div class="panel-heading">
    			<h3 class="panel-title">返回值描述</h3>
 			 </div>
  			<div class="panel-body">
    			<textarea name="DIVCSS5" cols="10" rows="4"> 
        		
  			   </textarea> 
  			</div>
		</div>
	</div>
</div>
<!-- 参数列表 -->
<div class="panel panel-default panel-info">
  <!-- Default panel contents -->
  <div class="panel-heading">参数列表</div>
  <!-- Table -->
  <div class="panel-body pre-scrollable" >
  <table class="table">
   <thead>
				<tr>
					<th>序号</th>
					<th style="min-width: 100px;">类型</th>
					<th style="min-width: 100px;">名称</th>
					<th style="min-width: 90px;">参数描述</th>				
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="parm"   items="${data}"   varStatus="status">
				<tr>
				 	<td>${parm.parampos}</td>
					<td>${parm.paramtype}</td>
					<td>${parm.paramname}</td>
					<td>${parm.description}</td>	
				</tr>
			</c:forEach>
			</tbody>		
  </table>
  </div>
  
</div>
<!-- 接口测试 -->
<div class="panel panel-default panel-info">
  <!-- Default panel contents -->
  <div class="panel-heading">接口测试</div>
      <div class="panel-body">
     <div class="row margintop-10" >
 		<div class="col-md-9">请求路径：<span id="url">http://<%=request.getRemoteAddr()%>:<%=request.getServerPort()%>${methodBean[0].url}.json</span></div>
  		<div class="col-md-3"><button type="button" class="btn btn-primary" onclick="testBtn();">测试按钮</button> </div>
	 </div>

	<div id="selectEach">
	    <%int var1=0; %>
	    	 <c:forEach var="parm"   items="${plist}"   varStatus="status">
	 			 <div class="row margintop-10" >
	 			   <div class="col-md-9">
					<%var1++;%>
				     <label><input type="checkbox" ><input id="<%=String.valueOf(var1) %>n"  class="paramName" type="text" value="${parm.name}" class="textStyle">:<input  type="text" value="${parm.type}" class="textStyle"> <input id="<%=String.valueOf(var1) %>v" class="paramValue" type="text" placeholder="填写参数值" class="textStyle"></label><br>
				  </div>
				  <div class="col-md-3">
				  </div>
				</div>
			</c:forEach>
	</div>


    				                                                     
  	  </div>
</div>
<!--返回结果  -->
<div class="panel panel-default panel-info">
  <!-- Default panel contents -->	
  <div class="panel-heading">返回结果</div>
      <div class="panel-body">
    		<textarea  id="returnResult" rows="5" cols="3" ></textarea>
  	  </div>
</div>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<script type="text/javascript">

/*   function toAlert() {
	layer.alert("参数类型由string变成了map",{
		icon:1,
		skin: 'layer-ext-moon'
	});
}  */ 

function testBtn(){
	debugger;
	url=$("#url").html();	
	var str="{";
	var var1=<%=var1%>;
	var var2=0;

	for (var i = 0; i < var1; i++){
		var2=i+1;
		if(document.getElementById(var2+"v").value!=""){
			str=str+document.getElementById(var2+"n").value+":\""+document.getElementById(var2+"v").value+"\",";
		}
	} 

	if (str.length > 1) {
        str = str.substr(0, str.length - 1);
    }
	str=str+"}";
	 
	 	 var tt = eval('(' + str + ')'); 
	
		
	 
	$.ajax({
		url: url,
		data:tt,  			
			/* licenseno:"110105016832269", */  //测试;			
		
		dataType: 'json', 
		type: "POST",
		async: true,
		/* timeout: 10000,  */ //超时时间设置为10秒；
		success: function(data) {	
			debugger;
			if(data.errorcode == "11111") {
				//处理业务
				
			     var result=data.data;
				var toStr=JSON.stringify(result);
					$("#returnResult").val(toStr);								

			}													
			else {
				alert('申请失败，请重试');
				return false;
			}
		},
		error: function(xhr, type, errorThrown) {
			
			alert(xhr.readyState);
		}
	});
}
		
	</script>

</body>
</html>