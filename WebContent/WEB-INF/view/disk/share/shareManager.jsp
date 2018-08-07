<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mftcc.interior.disk.bean.DiskShare"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>UserList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>
<body>

    <section class="content-header">
    <form id="select-form">
    <div class="col-sm-4">
		<button id="newUser" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增分享</button>
	</div>
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="用户姓名、电话、身份证号码">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
	</section>
	<section class="content">
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
				    <th>分享的文件</th>
					<th>分享时间</th>
					<th>分享地址</th>
					<th>验证码</th>
					<th>有效期</th>
					<th>状态</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	</section>
	
<!--修改的弹框 -->
<div id="add" class="hide">
 
 	<form class="form-horizontal"   id="pform" method="post">
		<div class="col-xs-12">
			<br/><br/>
				<input id="ShareId" type="hidden" name="shareId"/>				
						
				 <div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-5">验证码</label>

				<div class="col-sm-9">
						<input class="col-xs-6" type="text"  placeholder="请输入验证码"   id="sharePwd"
							name="sharePwd" />
						<div class="warning"   id="eorr">*必填</div>
					 
				</div>
			</div>

				<div class="form-group" >
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1"> 失效时间 </label>

				<div class="col-sm-9">
					<input type="text"  class="col-xs-6"   id="shareEnTime"  
						name="shareEnTime" placeholder="请选择时间" />
					<div class='warning'>*必填</div>
				</div>
			</div>
		 

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-2"> 状态 </label>

				<div class="col-sm-9">
					<select id="shareStatus" name="shareStatus" class="col-xs-6 ">
					    <option  value="011">无效</option>
						<option value="pwd">有效</option>
				    </select>
					<!-- <input type="text" placeholder="请输入备注信息" class="col-xs-6  "
						name="shareStatus" /> -->
				</div>
			</div>
			
		 
		</div>
	</form>  
</div>

<!--入库  结束-->
	
	
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	<script>
	var pageNumber=10;
	
	$(function(){
		console.log("jiedian");
		var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/share/getShareListPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		        	
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			
			},"json");
	});
	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		$.each(data,function(i,o){
			if(o.email == null){
				o.email = '';
			}
			var share=o.shareId+","+o.sharePwd+","+o.shareEnTime+","+o.shareStatus;
			if(o.shareStatus=="pwd")
				{
				o.shareStatus="有效";
				}else{
					o.shareStatus="无效";
				}
			
				htmlstr += "<tr>"
					+	"<td>"+o.fileName+"</td>"
					+	"<td>"+o.shareStTime+"</td>"
					+	"<td>"+o.shareUrl+"</td>"
					+	"<td>"+o.sharePwd+"</td>"
					+	"<td>"+o.shareEnTime+"</td>"
					+	"<td>"+o.shareStatus+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='update(\""+share+"\");' >修改</a></td>"
					+	"<td><a href='javascript:void(0);' onclick='Emplydelete(\""+o.shareId+"\");' >删除</a></td></tr>"
					+   "</tr>"
		});
		$("tbody").html(htmlstr);
	};

	
	
	
	function getdata(page){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/share/getShareListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;
		
		$.post("<%=request.getContextPath()%>/share/getShareListPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			},"json");
		return false;
	});
	<%-- function toDetail(id){
		window.location.href = "getEmply?id="+id;
	}
	$("#newUser").click(function(){
		window.location.href = "addemply";
	});
	
	function Emplydelete(id)
	{
		
		
		layer.confirm('你确定删除员工？',{btn:['确定','取消']},function(){
	 		$.post("<%=request.getContextPath()%>/deleteEmply.json" ,{id:id}, function(data){
				if(data.errorcode == 0)
				{
					layer.msg("删除用户失败",{time:1500,offset:'30%'});
					return false;
					
				}else{
					layer.alert("删除用户成功",{title:"提示"},function(){
						window.location.href ="emplyList";				
					});
				}
			}, "json");
		})  
		

	} --%>
	
	//更新分享信息
	function update(share) {
		document.getElementById("pform").reset();
		$('#add').removeClass("hide");
		console.log("share",share);
		
		var result=share.split(",");
		console.log("id:",result[0]);
        $('#ShareId').val(result[0]);
        $('#shareEnTime').val(result[2]);
        $('#sharePwd').val(result[1]);
        var opts = document.getElementById("shareStatus");
        for(var i=0;i<opts.options.length;i++){
            if(result[3]==opts.options[i].value){
                   opts.options[i].selected = 'selected';
              }
           }
        
		userModel = layer.open({
			type : 1,
			shade : [ 0.5 ],
			title : '更新分享信息',
			area : [ '500px', '350px' ], //显示空间
			content : $('#add'), //捕获的元素
			btn : [ '确定', '取消' ],
			yes : function(index, layero) {
			
		 	$.post("<%=request.getContextPath()%>/share/updataShare.json", $("#pform").serialize(),
						function(data) {
							
							if(data.errorcode == 0)
							{
								layer.msg("更新失败",{time:1500,offset:'30%'});
								return false;
								
							}else{
								layer.close(index);
								$('#add').addClass("hide");
								layer.msg("更新成功",{time:1500,offset:'30%'},function(){
									window.location.href ="shareManager";				
								});
							}
							 
						},"json");

			},
			cancel : function(index) {
				layer.close(index);
				$('#add').addClass("hide"); //取消继续隐藏
			},
		});
	};
	
 
	
	 
	
	
	</script>
	

	
</body>
