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
		          <input type="text"  id="Ipaddress" class="form-control" placeholder="ip地址">
		              <span class="input-group-btn">
		                
		              </span>
		        </div>
		      <br><br>
		<a href='javascript:void(0);' onclick='toSelect();' >查询</a>
		      
	  <div id="Ipaddressresult" class="hide">
		      <br> 
		       <h2  ></h2>
			<h3 id="ret"></h3>
			<h3 id="msg"></h3>
			<h3 id="log_id"></h3>
			<h3 id="area"></h3>
			<h3 id="country"></h3>
			<h3 id="long_ip"></h3>
			<h3 id="city"></h3>
			<h3 id="ip"></h3>
			<h3 id="isp"></h3>
			<h3 id="region_id"></h3>
			<h3 id="region"></h3>
			<h3 id="country_id"></h3>
			<h3 id="city_id"></h3>
	 </div>
		 
</body>
</html>
<script type="text/javascript">

	
	function toSelect ( ) {
	  	var da = $("#Ipaddress").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/Ipaddressselect.json",
				data:{uIpaddress:da},			 
				dataType:"json",
				type:"POST",
				success:function(data){
					 $('#Ipaddressresult').removeClass("hide");
					//alert(data.data);
					$("h2").html(data.data);
					 
				},
				error:function(){
					 layer.msg('查询失败 ！',  {time:500} );
				}
			});
		   
		}
  
	
	</script>





