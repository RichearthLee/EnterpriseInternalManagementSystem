<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
</head>
<body>
		        <div class="col-sm-3 input-group">
		          <input type="text"  id="constellation" class="form-control" placeholder="例如:baiyang">
		              <span class="input-group-btn">
		                
		              </span>
		        </div>
		      <br><br>
		<a href='javascript:void(0);' onclick='toSelect();' >查询</a>
		      
	  <div id="constellationresult" class="hide">
		      <br> 
		       <h2  ></h2>
			<h3 id="showapi_res_code"></h3>
			<h3 id="showapi_res_error"></h3>
			<h3 id="star"></h3>
			<h3 id="day"></h3>
	 </div>
		 
</body>
</html>
<script type="text/javascript">

	
	function toSelect ( ) {
	  	var da = $("#constellation").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/constellationselect.json",
				data:{uconstellation:da},			 
				dataType:"json",
				type:"POST",
				success:function(data){
					 $('#constellationresult').removeClass("hide");
					//alert(data.data);
					$("h2").html(data.data);
					 
				},
				error:function(){
					 layer.msg('查询失败 ！',  {time:500} );
				}
			});
		   
		}
  
	
	</script>





