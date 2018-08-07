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
		          <input type="text"  id="phone" class="form-control" placeholder="手机号">
		              <span class="input-group-btn">
		                
		              </span>
		        </div>
		      <br><br>
		<a href='javascript:void(0);' onclick='toSeclet();' >查询</a>
		      
	  <div id="phoneresult" class="hide">
		      <br> 
		       <h2></h2>
			<h3 id="num"></h3>
			<h3 id="prov"></h3>
			<h3 id="areaCode"></h3>
			<h3 id="name"></h3>
			<h3 id="cityCode"></h3>
			<h3 id="postCode"></h3>
			<h3 id="provCode"></h3>
			<h3 id="type"></h3>
			<h3 id="city"></h3>
	 </div>
		 
</body>
</html>
<script type="text/javascript">

	
	function toSeclet ( ) {
	  	var da = $("#phone").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/phoneselect.json",
				data:{uphone:da},			 
				dataType:"json",
				type:"POST",
				
				success:function(data){
					 $('#phoneresult').removeClass("hide");
					 var obj = eval ("(" + data.data + ")");
					//alert(obj.showapi_res_body.num);
					//$("h2").html(data.data);
					$("#num").html("num:"+obj.showapi_res_body.num);
					$("#prov").html("prov:"+obj.showapi_res_body.prov);
					$("#areaCode").html("areaCode:"+obj.showapi_res_body.areaCode);
					$("#name").html("name:"+obj.showapi_res_body.name);
					$("#cityCode").html("cityCode:"+obj.showapi_res_body.cityCode);
					$("#postCode").html("postCode:"+obj.showapi_res_body.postCode);
					$("#provCode").html("provCode:"+obj.showapi_res_body.provCode);
					$("#type").html("type:"+obj.showapi_res_body.type);
					$("#city").html("city:"+obj.showapi_res_body.city);
				
				},
				error:function(){
					 layer.msg('查询失败 ！',  {time:500} );
				}
			});
		   
		}
  
	
	</script>





