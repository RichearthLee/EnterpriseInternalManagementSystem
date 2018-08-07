<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mftcc.interior.disk.bean.DiskInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>diskList</title>
<%@include file="/WEB-INF/view/head.jsp" %>

<style>
	.diskname{
		background-repeat:no-repeat;
	background-image:url(<%=request.getContextPath()%>/disk/img/disk.png);
	
	}
	
	.diskname strong{
	padding-left:40px;
	text-shadow:-3px 3px 3px  #3333338c;
	font-size: 15px;
	}
	
	.enterdisk{
	background-repeat:no-repeat;
	background-image:url(<%=request.getContextPath()%>/disk/img/enterDisk.png);
	}
	
	.enterdisk a{
	padding-left:35px;
	}
	
</style>

</head>
<body>

    <section class="content-header">
    <form id="select-form"> 
    <div class="col-sm-4">
		<button id="newDisk" type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>新增网盘</button>
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
					<th>网盘名称</th>
					<th>创建者</th>
					<th>网盘信息</th>
					<th>进入网盘</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	<script>
	var pageNumber=10;
	$(function(){
		var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/disk/getGroupDiskListPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		        	console.log("p:",p);
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
			
			},"json");
	});
	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		var diskVo="";
		$.each(data,function(i,o){
			    diskVo=o.id+","+o.privContext;
				htmlstr += "<tr>"
					+	"<td class='diskname'> <strong>"+o.diskname+"</strong></td>"
					+	"<td >"+o.creatUserName+"</td>"
					+	"<td>总容量："+o.totalSize+" | 已用："+o.usedSize+"</td>"
					+	"<td class='enterdisk'><a  href='javascript:void(0);' onclick='diskDetail(\""+diskVo+"\");' >进入</a></td>"
					+   "</tr>"
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/disk/getGroupDiskListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;
		console.log("slect",ipage);
		$.post("<%=request.getContextPath()%>/disk/getGroupDiskListPage.json",ipage,function(data){
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
	function diskDetail(diskVo){
		window.location.href = "enterGroupDisk?diskVo="+diskVo;
	}
	
	
	$("#newDisk").click(function(){
		window.location.href = "addDiskGroupJsp";
	});
	
	<%--  function Emplydelete(id)
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
	</script>