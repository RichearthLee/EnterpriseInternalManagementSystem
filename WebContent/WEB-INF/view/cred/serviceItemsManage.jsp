<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.loadJSON.js"></script>
<title></title>
</head>
<script type="text/javascript">
var searchFiled="";//搜索字段
var pageNumber=10;//页面显示条数
var curPage=1;
var loadIndex;
var dataArray=new Array();//
var selectMerchantName="";//接收从ifram弹出框返回的merchantName
var selectMerchantNo="";//接收从ifram 弹出框返回的merchantNo
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
	var ipage =searchFiled+"&currPageNo="+currPageNo+"&pageNumber="+pageNumber;
	loadIndex=layer.load();
	$.post('<%=request.getContextPath()%>/getServiceItemsPage.json',ipage,function(data){
		layer.close(loadIndex);
		$("#page-div").createPage({
			limits:[10,20,30,40],
	        pageSize:pageNumber,
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
<%-- function getdata(page){
	var ipage ='searchFiled='+searchFiled + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post("<%=request.getContextPath()%>/getServiceItemsListPage.json",ipage,function(data){
		tbodyInit(data.data.dataList);
	},"json");
}; --%>
//创建列表
function tbodyInit(data){
	$("#dataContent").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
		var thisItemName=o.itemName;
		if(o.itemName.length>8){
			thisItemName=o.itemName.substring(0,7);
			thisItemName+="...";
		}
		dataArray[i]=o;
		var item = JSON.stringify(o);
		htmlstr	+=	'<tr><td title=\''+o.itemName+'\'>'+thisItemName+'</td>'
				+   '<td>'+o.itemNo+'</td>'
				+	'<td>'+o.merchantName+'</td>';
		if(o.openFlag=="0"){
			htmlstr	+=	'<td>自动生成</td>';
		}else{
			htmlstr	+=	'<td>手动输入</td>';
		}
		htmlstr	+=	'<td style="text-align:right"><span style="margin-right:50px">'+o.deftAmt+'</span></td>';
		htmlstr	+=	'<td style="text-align:right"><span style="margin-right:50px">'+o.deftAmt+'</span></td>';
		if(o.sts=="0"){
			htmlstr	+=	'<td>禁用</td>';
		}else{
			htmlstr	+=	'<td>可用</td>';
		}
		htmlstr	+=	'<td>'+o.level+'</td>';
		htmlstr	+=	'<td>'+o.grade+'%</td>';
		htmlstr += '<td><a href="javascript:void(0)" onclick="updateThis(dataArray['+i+'],\''+o.cifSts+'\',true)">详情</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="viewScore(dataArray['+i+'])">客户评价</a></td></tr>';
		//if(o.ifAppear==true){
			//htmlstr += '<td><a href="javascript:void(0)" onclick="updateThis(dataArray['+i+'],\''+o.cifSts+'\',true)">详情</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="viewScore(dataArray['+i+'])">客户评价</a></td></tr>';
		//}else{
			//htmlstr +='<td><a href="javascript:void(0)" onclick="updateThis(dataArray['+i+'],\''+o.cifSts+'\',false)">详情</a>&nbsp&nbsp;'
				//	+ '<a href="javascript:void(0)" onclick="delThis(\''+o.itemNo+'\')">删除</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="viewScore(dataArray['+i+'])">客户评价</a></td></tr>';
		//}
	});
	$("#dataContent").html(htmlstr);
};
//更新服务
function updateThis(jsons,cifSts,ifAppear){
	selectMerchantName="";
	selectMerchantNo="";
	document.getElementById("updateForm").reset();//表单清空
	$("#updateForm").loadJSON(jsons);//loadJSON插件
	$("#upSts").val(jsons.sts);
	$("#upOpenFlag").val(jsons.openFlag);
	$("#upLevel").val(jsons.level);
	$("#thisItemName").val(jsons.itemName);
	$("#thisMerchantName").val(jsons.merchantName);
	$("#thisMerchantNo").val(jsons.merchantNo);
	if(jsons.sts=="0"){
		$("#showUpSts").html("禁用");
	}else{
		$("#showUpSts").html("可用");
	}
	if(jsons.openFlag=="0"){
		$("#showOpenFlag").html("自动生成");
	}else{
		$("#showOpenFlag").html("手动输入");
	}
	$("#showItemName").html(jsons.itemName);
	$("#showItemType").html(jsons.itemType);
	$("#showLevel").html(jsons.level);
	$("#showMerchantName").html(jsons.merchantName);
	$("#showMerchantNo").html(jsons.merchantNo);
	$("#showItemNo").html(jsons.itemNo);
	$("#showCreatDate").html(jsons.createDate);
	$("#showTlrname").html(jsons.tlrname);
	$("#showItemNo").html(jsons.itemNo);
	if(ifAppear==true){//已经被使用过的服务
		$(".inputHidden").hide();
		$(".htmlHidden").show();
	}else{//没有使用过的服务
		$(".inputHidden").show();
		$(".htmlHidden").hide();
	}
	if(cifSts=="true"){//判断是否可以修改禁用
		$("#modalSts").hide();
		$("#htmlSts").show();
	}else{
		$("#modalSts").show();
		$("#htmlSts").hide();
	}
	$("#updateModal").modal('show');
}
//修改服务信息的提交
function updateSubmit(){
	var thisBackInputNo=$("#thisMerchantNo").val();
	for(var i=0;i<$("#updateForm .AddValidat").length;i++){
		var dom=$("#updateForm .AddValidat")[i];
		var text=dom.parentNode.parentNode.previousElementSibling.innerHTML;
		if(dom.value==null||dom.value==""){
			 layer.alert(text+"不能为空！",{title:"提示",icon:5});
			 return false;
		}
	}
	if(selectMerchantName==$("#upMerchantName").val()){//如果提交时和选择的服务商名字相同，则提交选择的服务商编号
		$("#upMerchantNo").val(selectMerchantNo);
		alert(selectMerchantName);
	}else{//否则提交为空，   用作service层的判断，如果服务商名字没有更改，则服务商编号取thisMerchantName的参数值
		$("#upMerchantNo").val("");
	}
	$.ajax({
		url:'<%=request.getContextPath()%>/updateServItem.json',
		data:$("#updateForm").serialize(),
		dataType:'json',
		type:'POST',
		success:function(data){
			 if(data.errorcode=="0"){
				  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
					  creatPaging(curPage);//执行刷新
                     layer.close(index);//关闭弹框
                      $("#updateModal").modal('hide');//隐藏model
				  });
			  }else{
				  $("#upMerchantNo").val(thisBackInputNo);
				  layer.alert(data.errormessage,{title:"提示",icon:5});
			  }
		}
	})
}
//删除服务
function delThis(itemNo){
	layer.confirm('确定要删除此服务吗？', {
		  btn: ['确定','返回'] //按钮
		}, function(){
			$.ajax({
				url:'<%=request.getContextPath()%>/deleteServiceByItemNo.json',
				data:'itemNo='+itemNo,
				dataType:'json',
				type:'POST',
				success:function(data){
					 if(data.errorcode=="0"){
						  layer.alert(data.errormessage,{title:"提示",icon:1},function(index){
							  getdata(curPage);//执行刷新
		                      layer.close(index);//关闭弹框
						  });
					  }else{
						  layer.alert(data.errormessage,{title:"提示",icon:5});
					  }
				}
			});
		}, function(index){
			layer.close(index);//关闭弹框
		});
}
function showAddModal(){
	 document.getElementById("addForm").reset();
	selectMerchantName="";
	selectMerchantNo="";
	$("#addMerchantName").val("");
	$("#addModal").modal('show');
}
//输入框验证并 提交新增服务表单
function validat(){
	for(var i=0;i<$("#addForm .AddValidat").length;i++){
		var dom=$("#addForm .AddValidat")[i];
		var text=dom.parentNode.parentNode.previousElementSibling.innerHTML;
		if(dom.value==null||dom.value==""){
			 layer.alert(text+"不能为空！",{title:"提示",icon:5});
			 return false;
		}
	}
	if($("#addMerchantName").val()==selectMerchantName){
		$("#addMerchantNo").val(selectMerchantNo);
	}else{
		$("#addMerchantNo").val("");
	}
	var formData=$("#addForm").serialize();
	$.ajax({
		url:'<%=request.getContextPath()%>/addServiceItem.json',
		dataType:'json',
		data:formData,
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
//点击model里的关闭按钮时的操作
function closeModal(id){
	document.getElementById(id).reset();
}
//获取所有的服务商名称
function getMcsName(){
	 layer.open({
	        type: 2,
	        title: '选择服务商',
	        offset:'50px',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['30%' , '80%'],
	        content: '<%=request.getContextPath()%>/jumpMerchantSelectList'
	    });
}
//弹出框选择完服务商名称之后的操作
function selected(merchantName,merchantNo){
	selectMerchantName=merchantName;
	selectMerchantNo=merchantNo;
	$("#upMerchantName").val(merchantName);
	$("#addMerchantName").val(merchantName);
	layer.closeAll('iframe');
}

//输入优惠价时只能是小数点和数字
function onkeyPres(dom){
	if(!dom.value.match(/^[\+\-]?\d*?\.?\d*?$/))dom.value=dom.t_value;
	else dom.t_value=dom.value;
	if(dom.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))dom.o_value=dom.value;
}
function ONkeyU(dom){
	if(!dom.value.match(/^[\+\-]?\d*?\.?\d*?$/))dom.value=dom.t_value;
	else dom.t_value=dom.value;
	if(dom.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))dom.o_value=dom.value
}
function onblr(dom){
	if(!dom.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))dom.value=dom.o_value;
	else{if(dom.value.match(/^\.\d+$/))dom.value=0+dom.value;
	if(dom.value.match(/^\.$/))dom.value=0;dom.o_value=dom.value}
}
//查看客户评价
function viewScore(item){
	layer.open({
        type: 2,
        title: item.merchantName+"  "+item.itemName+'——客户评价',
        offset:'25px',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['70%' , '90%'],
        content: '<%=request.getContextPath()%>/jumpServiceItemScoreList?itemNo='+item.itemNo+'&itemName='+item.itemName+'&merchantName='+item.merchantName
    });
	$(".layui-layer-max").hide();//页面样式问题，不允许放大缩小
	$(".layui-layer-min").hide();
}
</script>
<body>
    <section class="content-header">
    <form id="select-form">
		<div class="col-sm-3">
			<button type="button" class="btn btn-primary" onclick="showAddModal()">
			<span class="glyphicon glyphicon-plus"></span>新增
			</button>
		</div>
		<div class="col-sm-6">
		</div>
	    <div class="col-sm-3 input-group">
	      <input type="text" id="searchFiled" class="form-control" placeholder="服务名称、服务商" name="searchFiled"/>
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
					<th>服务名称</th>
					<th>服务编号</th>
					<th>服务商</th>
					<th>账号生成方式</th>
					<th width="10%" align="left">默认单价(元)</th>
					<th width="10%" align="left">实际单价(元)</th>
					<th>状态</th>
					<th>级别</th>
					<th>好评度</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="dataContent">

			</tbody>
		</table>
	<div id="page-div" style="text-align: right;margin-top: 20px"></div>
	</section>
	
   <!-- 模态框（Modal） -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><span style="font-weight:bolder;">
               		 <span class="glyphicon glyphicon-th-large"></span>&nbsp;服务详情</span>
                </h4>
            </div>
            <div class="modal-body" style="overflow: auto;height:430px">
            <form class="form-horizontal" role="form"  id="updateForm" name="updateForm">
            				<input type="hidden" id="thisMerchantNo" name="thisMerchantNo"/><!--原始值-->
            				<input type="hidden" id="thisItemName" name="thisItemName"><!--原始值-->
            				<input type="hidden" id="thisMerchantName" name="thisMerchantName"><!--原始值-->
            				<div class="form-group">
								<label  class="col-sm-4 control-label">服务商编号</label>
								<div>
									<div hidden="hidden"><input type="text" class="form-control" id="upMerchantNo" name="merchantNo"></div>
									<div class="col-sm-6" id="htmlMerchantNo"><span style="font-size: 16px;vertical-align:-8px" id="showMerchantNo"></span></div>
								</div>
							</div>
							<div class="form-group" id="modalMerchantName">
								<label class="col-sm-4 control-label">服务商名称</label>
								<div class="inputHidden">
									<div style="float: left;margin-left: 2.9%;width: 38%;">
										<input name="merchantName" id="upMerchantName"
											class="AddValidat" type="text" style="width: 100%;height:35px; padding-left:8px"
											placeholder="请输入服务商名称">
									</div>
										<button type="button" class="btn btn-default"
											id="selectMerchantName" onclick="getMcsName()">
											<span class="glyphicon glyphicon-chevron-down"></span>
										</button>
								</div>
								<div class="col-sm-6 htmlHidden"><span style="font-size: 16px;vertical-align:-8px" id="showMerchantName"></span></div>
							</div>
							<div class="form-group">
								<label  class="col-sm-4 control-label">服务编号</label>
								<div>
								<div hidden="hidden"><input type="text" class="form-control" name="itemNo" readonly="readonly"></div>
								<div div class="col-sm-6"><span style="font-size: 16px;vertical-align:-8px" id="showItemNo"></span></div>	
								</div>
							</div>
							<div class="form-group" id="modalItemName">
								<label class="col-sm-4 control-label">服务名称</label>
								<div>
								<div class="col-sm-6 inputHidden">
									<input type="text" class="form-control AddValidat"  id="upItemName"
										name="itemName" placeholder="请输入服务名称">
								</div>
								<div class="col-sm-6 htmlHidden"><span style="font-size: 16px;vertical-align:-8px" id="showItemName"></span></div>
								</div>
							</div>
							<div class="form-group" id="modalItemType">
								<label  class="col-sm-4 control-label">服务类型</label>
								<div>
								<div class="col-sm-6 inputHidden">
									<input type="text" class="form-control AddValidat" id="upItemType" 
										name="itemType" placeholder="请输入服务类型（如：短信  DX）">
								</div>
								<div  class="col-sm-6 htmlHidden"><span style="font-size: 16px;vertical-align:-8px" id="showItemType"></span></div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">
									服务功能描述
								</label>
								<div>
								<div class="col-sm-6">
								<textarea class="form-control" name="remarks" cols="20" rows="2" placeholder="请输入描述"></textarea>
								</div>
								</div>
							</div>
							<div class="form-group"  id="modalOpenFlag">
								<label class="col-sm-4 control-label">
									账号生成方式
								</label>
								<div>
								<div class="col-sm-6 inputHidden">
								<select class="form-control" name="openFlag" id="upOpenFlag">
								<option value="1">手动输入</option>
								<option value="0">自动生成</option>
								</select>
								</div>
								<div class="col-sm-6 htmlHidden"><span style="font-size: 16px;vertical-align:-8px" id="showOpenFlag"></span></div>
								</div>
							</div>
							<div class="form-group" id="modalLevel">
								<label class="col-sm-4 control-label">
									优先级别
								</label>
								<div>
								<div class="col-sm-6 inputHidden">
								<select class="form-control" name="level" id="upLevel">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								</select>
								</div>
								<div class="col-sm-6 htmlHidden"><span style="font-size: 16px;vertical-align:-8px" id="showLevel"></span></div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">默认单价</label>
								<div>
									<div style="float: left;margin-left: 2.9%;width: 38%;">
										<input name="deftAmt"
											class="AddValidat" type="text" style="width: 100%;height:35px; padding-left:10px" placeholder="请输入默认单价（元）"
											 size="2" t_value="" o_value="" onkeypress="onkeyPres(this)" onkeyup="ONkeyU(this)" onblur="onblr(this)">
									</div>
										<span class="input-group-addon" style="width:10px;height: 35px">元</span>
								</div>	
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">服务状态</label>
								<div>
								<div class="col-sm-6"  id="modalSts">
									<select class="form-control" name="sts" id="upSts">
								<option value="1">可用</option>
								<option value="0">禁用</option>
								</select>
								</div>
								<div class="col-sm-6" id="htmlSts"><span style="font-size: 16px;vertical-align:-8px" id="showUpSts"></span></div>
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-4 control-label">创建时间</label>
								<div>
								<div div class="col-sm-6"><span style="font-size: 16px;vertical-align:-8px" id="showCreatDate"></span></div>		
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-4 control-label">操作员</label>
								<div>
								<div div class="col-sm-6"><span style="font-size: 16px;vertical-align:-8px" id="showTlrname"></span></div>		
								</div>
							</div>
							
						</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"  onclick="closeModal('updateForm')">关闭</button>
                <button type="button" class="btn btn-primary" onclick='updateSubmit()'>修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
    <!-- 模态框（Modal） -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><span style="font-weight:bolder;">
               		 <span class="glyphicon glyphicon-th-large"></span>&nbsp;新增服务</span>
                </h4>
            </div>
            <div class="modal-body">
            <form class="form-horizontal" role="form"  id="addForm" name="addForm">
            				<input type="hidden" id="addMerchantNo" name="merchantNo"/>
            				<div class="form-group">
								<label class="col-sm-4 control-label">服务商名称</label>
								<div>
									<div style="float: left;margin-left: 2.9%;width: 38%;">
										<input name="merchantName" id="addMerchantName"
											class="AddValidat" type="text" style="width: 100%;height:35px; padding-left:8px"
											placeholder="请输入服务商名称">
									</div>
										<button type="button" class="btn btn-default"
											id="selectMerchantName" onclick="getMcsName()">
											<span class="glyphicon glyphicon-chevron-down"></span>
										</button>
								</div>		
							</div>
            				<div class="form-group">
								<label class="col-sm-4 control-label">服务名称</label>
								<div>
								<div class="col-sm-6">
									<input type="text" class="form-control AddValidat"
										name="itemName" placeholder="请输入服务名称">
								</div>
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-4 control-label">服务类型</label>
								<div>
								<div class="col-sm-6">
									<input type="text" class="form-control AddValidat" 
										name="itemType" placeholder="请输入服务类型（如：短信  DX）">
								</div>
								</div>
							</div>
							<div class="form-group"">
								<label class="col-sm-4 control-label">
									账号生成方式
								</label>
								<div>
								<div class="col-sm-6">
								<select class="form-control" name="openFlag">
								<option value="1">手动输入</option>
								<option value="0">自动生成</option>
								</select>
								</div>
								</div>
							</div>
							<div class="form-group"">
								<label class="col-sm-4 control-label">
									优先级别
								</label>
								<div>
								<div class="col-sm-6">
								<select class="form-control" name="level">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								</select>
								</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">默认单价</label>
								<div>
									<div style="float: left;margin-left: 2.9%;width: 38%;">
										<input name="deftAmt"
											class="AddValidat" type="text" style="width: 100%;height:35px; padding-left:10px" placeholder="请输入默认单价（元）"
											 size="2" t_value="" o_value="" onkeypress="onkeyPres(this)" onkeyup="ONkeyU(this)" onblur="onblr(this)">
									</div>
										<span class="input-group-addon" style="width:10px;height: 35px">元</span>
								</div>
							</div>
							<div class="form-group"">
								<label class="col-sm-4 control-label">服务状态</label>
								<div>
								<div class="col-sm-6">
									<select class="form-control" name="sts">
								<option value="1">可用</option>
								<option value="0">禁用</option>
								</select>
								</div>
								</div>
							</div>
							<div class="form-group"">
								<label class="col-sm-4 control-label">
									备注
								</label>
								<div>
								<div class="col-sm-6">
								<textarea class="form-control" name="remarks" cols="20" rows="2" placeholder="请输入备注"></textarea>
								</div>
							</div>
							</div>
						</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('addForm')">关闭</button>
                <button type="button" class="btn btn-primary" onclick='validat()'>新增</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>