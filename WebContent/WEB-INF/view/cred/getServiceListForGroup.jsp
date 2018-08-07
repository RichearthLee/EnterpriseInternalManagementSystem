<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title></title>
<script type="text/javascript">
var itemNoStr='${itemNoStr}';
var itemArray=itemNoStr.split(",");
$(function(){
	getTableDate();
	//全选按钮功能 
	$('#selectClick').click(function() {
		var float = this.checked;
			$("input[name='select-checkbox']").each(function() {
				this.checked = float;
			});		
	});
})
function getTableDate(){
	$.ajax({
		url:'<%=request.getContextPath()%>/getServiceItemsForGroup.json',
		data:'searchFiled='+$("#searchFiled").val(),
		dataType:'json',
		type:'post',
		success:function(data){
			$("#dataContent").empty();
			var htmlstr = "";
			$.each(data.dataList,function(i,o){
				htmlstr+='<tr><td><input type="checkBox" value=\''+o.itemNo+'\' name="select-checkbox"';
						if(isCon(itemArray,o.itemNo)){
							htmlstr+='checked="checked"';
						}
				htmlstr+='></td>'
						+'<td>'+o.itemNo+'</td>'
						+'<td>'+o.merchantName+'</td>'
						+'<td>'+o.itemName+'</td>';
			})
			$("#dataContent").html(htmlstr);
		}
	})
}
function savePz(){
	var s='';
	$('[name="select-checkbox"]:checked').each(function(i,o){
		s+=$(this).val()+",";
	})
	parent.selectDone(s);
}
function isCon(arr, val){
	for(var i=0; i<arr.length; i++){
		if(arr[i] == val)
			return true;
	}
	return false;
}
</script>
</head>
<body>
<section class="content-header">
    <form id="select-form">
		<div style="width:50%;float:left;">
			<button type="button" class="btn btn-primary" onclick="savePz()">
			<span class="glyphicon glyphicon-cog">&nbsp;</span>配置
			</button>
		</div>
	    <div class="input-group" style="width:50%">
	      <input type="text" id="searchFiled" class="form-control" placeholder="智能搜索" name="searchFiled"/>
	          <span class="input-group-btn">
	            <button type="button" name="search" id="search-btn" class="btn btn-default" onclick="getTableDate()"><i class="fa fa-search"></i>
	            </button>
	          </span>
	    </div>
      </form>
	</section>
	<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectClick"></th>
					<th>服务项编号</th>
					<th>服务商名称</th>
					<th>服务名称</th>
				</tr>
			</thead>
			<tbody id="dataContent">

			</tbody>
		</table>
		<div style="float:right;padding-right: 20px">
			<button type="button" class="btn btn-primary" onclick="savePz()">
			<span class="glyphicon glyphicon-cog">&nbsp;</span>配置
			</button>
		</div>
	</section>
</body>
</html>