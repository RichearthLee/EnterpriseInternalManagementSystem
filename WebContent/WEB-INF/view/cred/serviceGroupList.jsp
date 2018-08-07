<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title></title>
<script type="text/javascript">
var searchFiled="";//搜索字段
var pageNumber=10;//页面显示条数
var curPage=1;
var itemNoStr="";
var pid='${pgroup.serialno}';
var level='${pgroup.level}';

var g=new Array();
$(function(){
	creatPaging(1);
	$("#search-btn").click(function(){
		searchFiled=$("#select-form").serialize();
		creatPaging(1);
		return false;
	});
})
//创建分页插件并初始化列表（只有在初始化和点击查询之后调用）
function creatPaging(currPageNo){
	curPage=currPageNo;//更改当前页数
	//var ipage =searchFiled+"&currPageNo="+currPageNo+"&pageNumber="+pageNumber;
	var levalInt=0;
	if(!level){
		levalInt=0;
	}else{
		levalInt=level*1+1;
	}
	
	var ipage ={
			searchFiled : $('#searchFiled').val(),
			currPageNo : currPageNo,
			pageNumber : pageNumber,
			parm4 : levalInt,
			parm5 : pid
	};
	$.post("<%=request.getContextPath()%>/getCurServiceGroupListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:currPageNo,
	        backFn:function(p){
	        	creatPaging(p);//存在删除操作时，不使用getdata方法，重新生成翻页插件
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
}
//翻页查询数据
function getdata(page){
	var ipage =searchFiled + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post("<%=request.getContextPath()%>/getCurServiceGroupListPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
	},"json");
};
//创建列表
function tbodyInit(data){
	$("#dataContent").empty();
	var htmlstr = "";
	var sts='';
	$.each(data,function(i,o){
		g[i]=o;
		if(o.sts=='Y'){
			sts='启用';
		}else{
			sts='禁用';
		}
		htmlstr+='<tr id=\''+o.serialno+'\'><td>'+o.businessCode+'</td>'
				+'<td>'+o.businessName+'</td>'
				+'<td>'+sts+'</td>'
				+'<td>'+o.tlrname+'</td>'
				+'<td>'+o.createDate+'</td>'
				+'<td>'
				+'<a href="javascript:void(0)" onclick="peizhi(g['+i+'])">配置</a>&nbsp;&nbsp;'
				+'<a href="javascript:void(0)" onclick="deleteThis(\''+o.serialno+'\')">删除</a>&nbsp;&nbsp;'
				+'<a href="<%=request.getContextPath()%>/serviceGroupManage?pid='+o.serialno+'">子列表</a>'
				+'</td></tr>';
	});
	$("#dataContent").html(htmlstr);
};
function peizhi(item){
	document.getElementById("updateForm").reset();
	$("#updateModal").modal('show');
	$("#businessCode").val(item.businessCode);
	$("#businessName").val(item.businessName);
	$("#sts").val(item.sts);
	$("#serialno").val(item.serialno);
	var itemNos=item.itemNos;
	itemNoStr='';
	for(var i=0;i<itemNos.length;i++){
		if(i<itemNos.length-1){
			itemNoStr+=itemNos[i]+",";
		}else{
			itemNoStr+=itemNos[i];
		}
	}
	$("#itemNos").val(itemNoStr);
}
function deleteThis(serialno){
	layer.confirm('确定要删除此服务组吗？', {
		  btn: ['确定','返回'] //按钮
		}, function(){
			$.ajax({
				url:'<%=request.getContextPath()%>/deleteServiceGroup.json',
				data:'serialno='+serialno,
				dataType:'json',
				type:'post',
				success:function(data){
					if(data.errorcode=="0"){
						  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
							  creatPaging(1);//执行刷新
		                      layer.close(index);//关闭弹框
						  });
					  }else{
						  layer.alert(data.errormessage,{title:"提示",icon:5});
					  }
				}
			})
		});
}
function showAddModal(){
	document.getElementById("addForm").reset();
	$("#addModal").modal('show');
}
function saveForm(){
	var flag="0";
	$("#addForm").find(".AddValidat").each(function(){
		if(null==$(this).val()||$(this).val()==""){
			flag="1";
			return false;
		}
	})
	if(flag=="1"){
		layer.alert("保存失败，请填写完整资料再保存！",{title:"提示",icon:5});
		return false;
	}
	var formData=$("#addForm").serialize();
	$.ajax({
		url:'<%=request.getContextPath()%>/addServiceGroup.json',
		dataType:'json',
		data : formData,
		type:'POST',
		success:function(data){
			if(data.errorcode=="0"){
				  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
					  	creatPaging(1);//执行刷新
						document.getElementById("addForm").reset();
                    layer.close(index);//关闭弹框
                    $("#addModal").modal('hide');
				  });
			  }else{
				  layer.alert(data.errormessage,{title:"提示",icon:5});
			  }
		}
	})
}
function pzServiceItem(){
	layer.open({
        type: 2,
        title: '配置服务',
        offset:'20px',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['40%' , '90%'],
        content: '<%=request.getContextPath()%>/getServiceListForGroup?itemNoStr='+itemNoStr
    });
}
function selectDone(s){
	$("#itemNos").val(s);
	layer.alert("配置成功，点击页面保存按钮后生效！",{title:"提示",icon:1},function(index){
		layer.closeAll('iframe');
		layer.close(index);//关闭弹框
	});
}
function saveForm1(){
	var formData=$("#updateForm").serialize();
	$.ajax({
		url:'<%=request.getContextPath()%>/updateServiceGroup.json',
		data:formData,
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.errorcode=="0"){
				layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
					  creatPaging(1);//执行刷新
					  document.getElementById("updateForm").reset();
	                  layer.close(index);//关闭弹框
	                  $("#updateModal").modal('hide');
				});
			  }else{
				  layer.alert(data.errormessage,{title:"提示",icon:5});
			  }
		}
	})
}
</script>
</head>
<body>
	<section class="content-header">
		<div style="padding: 0px 0 8px 0px;">
			<a href="${pageContext.request.contextPath}/serviceGroupManage">服务分组/</a>
			<c:if test="${!empty parents }">
			<c:forEach items="${parents}" var="ps" varStatus="vs">
				<a href="${pageContext.request.contextPath}/serviceGroupManage?pid=${ps.serialno }">${ps.businessName }/</a>
			</c:forEach>
			</c:if>
		</div>
	    <form id="select-form row">
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary" onclick="showAddModal()">
				<span class="glyphicon glyphicon-plus"></span>新增
				</button>
			</div>
			<div class="col-sm-6">
			</div>
		    <div class="col-sm-3 input-group">
		      <input type="text" id="searchFiled" class="form-control" placeholder="智能搜索" name="searchFiled"/>
		          <span class="input-group-btn">
		            <button type="button" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
		            </button>
		          </span>
		    </div>
	      </form>
	</section>
	<section class="content">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>服务组编码</th>
					<th>服务组名称</th>
					<th>状态</th>
					<th>创建人</th>
					<th>创建日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="dataContent">

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;margin-top: 20px"></div>
	</section>
	 <!-- 模态框（Modal） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel"><span style="font-weight:bolder;">
	               		 <span class="glyphicon glyphicon-th-large"></span>&nbsp;新增服务组</span>
	                </h4>
	            </div>
	            <div class="modal-body">
		            <form class="form-horizontal" role="form"  id="addForm" name="addForm">
		            	<c:if test="${!empty pgroup }">
							<div class="form-group">
								<label class="col-sm-4 control-label">上级分组</label>
								<div>
									<div class="col-sm-6">
										<input type="text" class="form-control" readonly="readonly" value="${pgroup.businessName }">
										<input type="hidden" class="form-control " name="pid" value="${pgroup.serialno }">
										<input type="hidden" class="form-control " name="level" value="${pgroup.level }">
									</div>
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label class="col-sm-4 control-label">业务名称</label>
							<div>
								<div class="col-sm-6">
									<input type="text" class="form-control AddValidat" name="businessName" placeholder="请输入业务名称">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">业务编码</label>
							<div>
								<div class="col-sm-6">
									<input type="text" class="form-control AddValidat"  name="businessCode" placeholder="请输入业务编码">
								</div>
							</div>
						</div>
						<div class="form-group"">
							<label class="col-sm-4 control-label"> 是否可用 </label>
							<div>
								<div class="col-sm-6">
									<select class="form-control" name="sts">
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
								</div>
							</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick='saveForm()'>保存</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
   
    <!-- 模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel"><span style="font-weight:bolder;">
	               		 <span class="glyphicon glyphicon-th-large"></span>&nbsp;服务组配置</span>
	                </h4>
	            </div>
	            <div class="modal-body">
	            <form class="form-horizontal" role="form"  id="updateForm" name="updateForm">
	            				<input type="hidden" id="serialno" name="serialno">
	            				<div class="form-group">
									<label class="col-sm-4 control-label">业务名称</label>
									<div>
									<div class="col-sm-6">
										<input type="text" class="form-control AddValidat" id="businessName"
											name="businessName" placeholder="请输入业务名称">
									</div>
									</div>
								</div>
								<div class="form-group" style="display: none;">
									<label  class="col-sm-4 control-label">业务编码</label>
									<div>
									<div class="col-sm-6">
										<input type="text" class="form-control AddValidat" id="businessCode"
											name="businessCode" placeholder="请输入业务编码">
									</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">
										是否可用
									</label>
									<div>
									<div class="col-sm-6">
									<select class="form-control" name="sts" id="sts">
									<option value="Y">是</option>
									<option value="N">否</option>
									</select>
									</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">
										配置服务项
									</label>
									<div>
									<div class="col-sm-6">
										<button type="button" class="btn btn-default btn-block" onclick="pzServiceItem()" id="pzItems">配置</button>
										<input type="hidden" name="itemNos" id="itemNos">
									</div>
									</div>
								</div>
							</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick='saveForm1()'>保存</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
</body>
</html>