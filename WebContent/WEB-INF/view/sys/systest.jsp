<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title> </title>
<style type="text/css">
 
 
</style>
</head>
<body>


	<div style="margin-top: 10px"></div>
		<h2 align="center" >redis </h2>
	   <h3>
 	 		 <a href='javascript:void(0);' onclick='redis();' >redis查询</a>
 	 		 <br/> <br/>
 	 		  <a href='javascript:void(0);' onclick='redislist();' >redislist查询</a>
 	 		   <br/> <br/>
 	 		    <a href='javascript:void(0);' onclick='redismap();' >redismap查询</a>
 	 		   <br/> <br/>
 	 		   <a href='javascript:void(0);' onclick='addList();' >addList查询</a>
 	 		   <br/>
 	 		   <br/>
 	 		   <a href='javascript:void(0);' onclick='addSet();' >addList查询</a>
 	 		   <br/>
 	 		    <br/>
 	 		   <a href='javascript:void(0);' onclick='addObject();' >addList查询</a>
 	 		   <br/>
 	 		   <br/>
 	 		   <a href='javascript:void(0);' onclick='getDicRedis();' >getDicRedis查询</a>
 	 		   <br/>
 	 		    <br/>
 	 		   <a href='javascript:void(0);' onclick='getSysDicInRedis();' >getSysDicInRedis查询</a>
 	 		   <br/>
 	 		   
		 </h3>
</body>
</html>
<script type="text/javascript">
	
function redis () {
	layer.load();
	window.location.href = contextPath+"/redis2/";
	
}	 

function redislist () {
	layer.load();
	
	$.ajax({
		url:"<%=request.getContextPath()%>/getlist.json",
	//	data:{opNo:opNo,password:password},
		dataType:"json",
		type:"post",
		success:function(data){
			 layer.msg(data.data,  {time:2000} );
			 alert(data.data.name)
		},
		error:function(e){
			 layer.msg('系统出错！',  {time:2000} );
			    
		}
	});  
	
}	 
	function redismap () {
		
		$.ajax({
			url:"<%=request.getContextPath()%>/addMap.json",
		//	data:{opNo:opNo,password:password},
			dataType:"json",
			type:"post",
			success:function(data){
				 layer.msg(data,  {time:2000} );
				 alert(data.smap)
			},
			error:function(e){
				 layer.msg('系统出错！',  {time:2000} );
				    
			}
		});  
		
	}	 
		
function addList () {
		
		$.ajax({
			url:"<%=request.getContextPath()%>/addList.json",
			dataType:"json",
			type:"post",
			success:function(data){
				 layer.msg(data,  {time:2000} );
			},
			error:function(e){
				 layer.msg('系统出错！',  {time:2000} );
			}
		});  
		
	}	 
		
	function addSet () {
		
		$.ajax({
			url:"<%=request.getContextPath()%>/addSet.json",
			dataType:"json",
			type:"post",
			success:function(data){
				 layer.msg(data,  {time:2000} );
			},
			error:function(e){
				 layer.msg('系统出错！',  {time:2000} );
			}
		});  
		
	}	 
	

	function addObject () {
		
		$.ajax({
			url:"<%=request.getContextPath()%>/addObject.json",
			dataType:"json",
			type:"post",
			success:function(data){
				 layer.msg(data,  {time:2000} );
			},
			error:function(e){
				 layer.msg('系统出错！',  {time:2000} );
			}
		});  
		
	}	

	
function getDicRedis() {
		

	   $.ajax({
		  	url:"<%=request.getContextPath()%>/getDicRedis.json",
		  //	data:{dicType:dicType},
		  	dataType:"json",
		  	type:"post",
		  	success:function(data){
		  		if(data.falg=="true"){
		  			 layer.msg('数据字典成功！',  {time:500} );
		  		}
		  		if(data.falg=="fail"){
		  			 layer.msg('数据字典失败！',  {time:500} );
		  		}
		  		
		 		  },
		  	error:function(){
		  		   layer.msg('数据字典加载失败！',  {time:500} );
		  	}
		  });    // ajax 结束
		
	}	

function getSysDicInRedis() {
	

	   $.ajax({
		  	url:"<%=request.getContextPath()%>/getSysDicInRedis.json",
		  //	data:{dicType:dicType},
		  	dataType:"json",
		  	type:"post",
		  	success:function(data){
		  		if(data.falg=="true"){
		  			 layer.msg('数据字典成功！',  {time:500} );
		  		}
		  		if(data.falg=="fail"){
		  			 layer.msg('数据字典失败！',  {time:500} );
		  		}
		  		
		 		  },
		  	error:function(){
		  		   layer.msg('数据字典加载失败！',  {time:500} );
		  	}
		  });    // ajax 结束
		
	}	

	


	</script>