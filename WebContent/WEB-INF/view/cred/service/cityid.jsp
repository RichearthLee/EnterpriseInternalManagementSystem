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
		          <input type="text"  id="cityid" class="form-control" placeholder="城市名">
		              <span class="input-group-btn">
		                
		              </span>
		        </div>
		      <br><br>
		<a href='javascript:void(0);' onclick='toSeclet();' >查询</a>
		      
	  <div id="cityidresult" class="hide">
		      <br> 
		       <h2  ></h2>
			<h3 id="num"></h3>
			<h3 id="prov"></h3>
			<h3 id="areaCode"></h3>
			<h3 id="name"></h3>
			<h3 id="cityCode"></h3>
			<h3 id="postCode"></h3>
			<h3 id="provCode"></h3>
			<h3 id="city"></h3>
	 </div>
		 
</body>
</html>
<script type="text/javascript">

	
	function toSeclet ( ) {
	  	var da = $("#cityid").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/cityidselect.json",
				data:{ucityid:da},			 
				dataType:"json",
				type:"POST",
				success:function(data){
					 $('#cityidresult').removeClass("hide");
					//alert(data.data);
					$("h2").html(data.data);
					 
				},
				error:function(){
					 layer.msg('查询失败 ！',  {time:500} );
				}
			});
		   
		}
  
	
	</script>





