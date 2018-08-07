<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script	src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css"/>
</head>
<body>
		<form id='fForm' class="form-actions form-horizontal" action="<%=request.getContextPath()%>/uploadpackbaseinf" 
		      encType="multipart/form-data"  method="post">
				 <div class="control-group">
					<label class="control-label">上传文件:</label>
					<div class="controls">
						<input type="file"  name="file" style="width:550">							
						
						
					</div>
					<div class="controls">
					<label class="control-label">版本:</label>
						<input name="packVersion" type ="text" value="">
					</div>
					<div class="controls">
					<label class="control-label">产品编号:</label>
						<input name="productId" type ="text" value="">
					</div>
					<div class="controls">
					<label class="control-label">类型:</label>
						<input name="packType" type ="text" value="">
					</div>
					<div class="controls">
					<label class="control-label">客户ID:</label>
						<input name="customerId" type ="text" value="">
					</div>
					<div id='show' >
					<label class="control-label">上传进度:</label>
					<div class="controls">
						<div class="progress">
  							<div class="progress-bar progress-bar-striped" id="proBar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
   							
  							</div>
						</div>

					</div>
					</div>
				</div>
			
				 <div class="control-group">
					<div class="controls">
					<button type="submit" id="subbut" class="btn">submit</button>
					</div>
				</div>
		</form>
		
</body>
</html>
<script >
$(document).ready(function(){
	document.getElementById("show").style.display="none";
	$('#subbut').bind('click',
			function(){		
				$('#fForm').submit();
				document.getElementById("show").style.display="";
				var eventFun = function(){					
		    		$.ajax({
	    				type: 'POST',
	    				url: '<%=request.getContextPath()%>/processproaw.json',
	    				data: {},
	    				dataType: 'json',
	    				success : function(data){
	    					data = data.proInfo;
		    					$('#proBar').attr('style','width: '+data.rate+'%');
			    				$('#proBar').empty();
					    		$('#proBar').append(data.show);	
					    		if(data.rate == 100){
					    			
					    			window.clearInterval(intId);
					    			document.getElementById("show").style.display="none";
					    		}	
	    		}});};
	    		var intId = window.setInterval(eventFun,500);
	});
});
</script>

